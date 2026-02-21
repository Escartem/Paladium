package fr.paladium.palamod.modules.mailbox.server.commands;

import com.google.common.base.Charsets;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.common.api.dto.UUID;
import fr.paladium.palamod.modules.enderchest.serial.itemstack.ItemStackSerializer;
import fr.paladium.palamod.modules.mailbox.PMailbox;
import fr.paladium.palamod.modules.mailbox.client.pojo.EnumMailSide;
import fr.paladium.palamod.modules.mailbox.client.pojo.EnumMailType;
import fr.paladium.palamod.modules.mailbox.client.pojo.inputs.SendMail;
import fr.paladium.palamod.modules.mailbox.rabbitmq.packets.MailboxPersonnalNotificationPacket;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandGive;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import retrofit2.Response;

public class ShopMailData {
  public static final char SEPARATOR = '|';
  
  private static final String DEFAULT_MESSAGE = "Merci pour votre commande !";
  
  private String target = null;
  
  private List<ItemStack> items = new ArrayList<>();
  
  private String orderId = null;
  
  private String customMessage = "Merci pour votre commande !";
  
  public ArrayList<String> getEncodedItems() {
    ArrayList<String> encodedItems = new ArrayList<>();
    if (this.items == null || this.items.isEmpty())
      return encodedItems; 
    for (ItemStack itemStack : this.items)
      encodedItems.add(Base64.getEncoder().encodeToString(ItemStackSerializer.write(itemStack).getBytes())); 
    return encodedItems;
  }
  
  private String parseUsername(String[] args) {
    if (args.length < 1)
      return null; 
    return args[0];
  }
  
  private String parseOrderId(String[] args) {
    if (args.length < 2)
      return null; 
    return args[1];
  }
  
  public boolean isValid() {
    return ((this.target != null && this.items == null) || this.items.isEmpty() || this.orderId != null);
  }
  
  public void sendMail(ICommandSender sender) {
    PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
          try {
            UUID uniqueId = getUniqueId();
            if (uniqueId == null)
              return; 
            SendMail sendMail = new SendMail("#" + this.orderId, this.customMessage, this.target, uniqueId.toString(), "paladium", "paladium", getEncodedItems(), "", EnumMailType.ITEM, EnumMailSide.ALL, -1, true, false);
            Response<String> call = ApiServices.Http.getMailboxService().sendEmail(sendMail).execute();
            if (call.code() != 201)
              return; 
            (new MailboxPersonnalNotificationPacket(sendMail.getSenderName(), sendMail.getRecipientName(), sendMail.getObject())).send(PMailbox.rabbit);
          } catch (Exception e) {
            e.printStackTrace();
          } 
        }0L);
  }
  
  private UUID offlineUUID(String username) {
    return UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes(Charsets.UTF_8));
  }
  
  private UUID getUniqueIdFromUsername() throws Exception {
    if (ForgeEnv.isDev())
      return offlineUUID(this.target); 
    Response<UUID> call = ApiServices.Http.getMinecraftApi().getUUIDFromUsername(this.target).execute();
    if (call.code() != 200)
      return null; 
    UUID uuidFromMojang = (UUID)call.body();
    String formattedUUID = uuidFromMojang.getId().replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5");
    return UUID.fromString(formattedUUID);
  }
  
  public UUID getUniqueId() throws Exception {
    UUID uniqueId = getUniqueIdFromTarget();
    if (uniqueId == null)
      uniqueId = getUniqueIdFromUsername(); 
    return uniqueId;
  }
  
  private UUID getUniqueIdFromTarget() {
    try {
      return UUID.fromString(this.target);
    } catch (Exception e) {
      return null;
    } 
  }
  
  public static ShopMailData fromString(ICommandSender sender, String[] args) {
    String data = String.join(" ", (CharSequence[])args);
    ShopMailData mailData = new ShopMailData();
    String[] parts = data.split("\\|");
    if (parts.length < 2)
      return null; 
    try {
      mailData.target = mailData.parseUsername(parts);
      mailData.orderId = mailData.parseOrderId(parts);
      mailData.items = mailData.parseItems(sender, parts);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } 
    return mailData;
  }
  
  private ItemStack parseItemStack(ICommandSender sender, String itemString) throws Exception {
    String[] itemArgs = itemString.split(" ");
    if (itemArgs.length < 3)
      return null; 
    Item item = CommandGive.func_147179_f(sender, itemArgs[0]);
    if (item == null)
      return null; 
    int quantity = CommandBase.func_71532_a(sender, itemArgs[1], 1, 64);
    int metadata = CommandBase.func_71526_a(sender, itemArgs[2]);
    ItemStack stack = new ItemStack(item, quantity, metadata);
    if (itemArgs.length < 4)
      return stack; 
    String unformulatedText = CommandBase.func_147178_a(sender, itemArgs, 3).func_150260_c();
    NBTBase nbt = JsonToNBT.func_150315_a(unformulatedText);
    if (!(nbt instanceof NBTTagCompound))
      return null; 
    stack.func_77982_d((NBTTagCompound)nbt);
    return stack;
  }
  
  private List<ItemStack> parseItems(ICommandSender sender, String[] parts) {
    List<ItemStack> items = new ArrayList<>();
    for (int i = 2; i < parts.length; i++) {
      try {
        ItemStack itemStack = parseItemStack(sender, parts[i]);
        if (itemStack == null) {
          if (i == parts.length - 1)
            this.customMessage = parts[i]; 
        } else {
          items.add(itemStack);
        } 
      } catch (Exception silent) {
        if (i == parts.length - 1)
          this.customMessage = parts[i]; 
      } 
    } 
    return items;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\server\commands\ShopMailData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */