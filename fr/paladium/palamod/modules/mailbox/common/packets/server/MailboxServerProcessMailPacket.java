package fr.paladium.palamod.modules.mailbox.common.packets.server;

import com.google.common.base.Charsets;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.mailbox.client.pojo.Mail;
import fr.paladium.palamod.modules.mailbox.client.utils.InventoryUtils;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palamod.util.ItemStackSerializer;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import retrofit2.Response;

public class MailboxServerProcessMailPacket implements IMessage {
  public void setMailId(String mailId) {
    this.mailId = mailId;
  }
  
  public void setAction(EnumMailActions action) {
    this.action = action;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof MailboxServerProcessMailPacket))
      return false; 
    MailboxServerProcessMailPacket other = (MailboxServerProcessMailPacket)o;
    if (!other.canEqual(this))
      return false; 
    Object this$mailId = getMailId(), other$mailId = other.getMailId();
    if ((this$mailId == null) ? (other$mailId != null) : !this$mailId.equals(other$mailId))
      return false; 
    Object this$action = getAction(), other$action = other.getAction();
    return !((this$action == null) ? (other$action != null) : !this$action.equals(other$action));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof MailboxServerProcessMailPacket;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $mailId = getMailId();
    result = result * 59 + (($mailId == null) ? 43 : $mailId.hashCode());
    Object $action = getAction();
    return result * 59 + (($action == null) ? 43 : $action.hashCode());
  }
  
  public String toString() {
    return "MailboxServerProcessMailPacket(mailId=" + getMailId() + ", action=" + getAction() + ")";
  }
  
  public MailboxServerProcessMailPacket(String mailId, EnumMailActions action) {
    this.mailId = mailId;
    this.action = action;
  }
  
  private String mailId = "";
  
  private EnumMailActions action;
  
  public String getMailId() {
    return this.mailId;
  }
  
  public EnumMailActions getAction() {
    return this.action;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.mailId = ByteBufUtils.readUTF8String(buf);
    this.action = EnumMailActions.valueOf(ByteBufUtils.readUTF8String(buf));
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.mailId);
    ByteBufUtils.writeUTF8String(buf, this.action.toString());
  }
  
  public MailboxServerProcessMailPacket() {}
  
  public static class Handler implements IMessageHandler<MailboxServerProcessMailPacket, IMessage> {
    public IMessage onMessage(MailboxServerProcessMailPacket message, MessageContext ctx) {
      PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
            try {
              String mailId = message.mailId;
              EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
              Response<Mail> call = ApiServices.Http.getMailboxService().getEmail(mailId).execute();
              if (call.code() != 200)
                return; 
              Mail mail = (Mail)call.body();
              String uniqueId = entityPlayerMP.func_110124_au().toString();
              if (ForgeEnv.isDev())
                uniqueId = UUID.nameUUIDFromBytes(("OfflinePlayer:" + entityPlayerMP.func_70005_c_()).getBytes(Charsets.UTF_8)).toString(); 
              switch (message.action) {
                case READ:
                  readEmail(mail, (EntityPlayer)entityPlayerMP, uniqueId);
                  break;
                case DELETE:
                  deleteEmail(mail, (EntityPlayer)entityPlayerMP, uniqueId);
                  break;
                case ACCEPT:
                  acceptEmail(mail, (EntityPlayer)entityPlayerMP, uniqueId);
                  break;
                case DENY:
                  denyEmail(mail, (EntityPlayer)entityPlayerMP, uniqueId);
                  break;
                case RECUP:
                  recupEmail(mail, (EntityPlayer)entityPlayerMP, uniqueId);
                  break;
              } 
            } catch (IOException e) {
              e.printStackTrace();
            } 
          }0L);
      return null;
    }
    
    private void readEmail(Mail mail, EntityPlayer player, String uniqueId) throws IOException {
      ApiServices.Http.getMailboxService().readEmail(mail.getId(), uniqueId).execute();
    }
    
    private void acceptEmail(Mail mail, EntityPlayer player, String uniqueId) throws IOException {
      ApiServices.Http.getMailboxService().deleteEmail(mail.getId(), uniqueId).execute();
      MinecraftServer.func_71276_C().func_71187_D().func_71556_a((ICommandSender)MinecraftServer.func_71276_C(), mail.getInviteCommand().replace("{PLAYER}", player.func_70005_c_()));
      (new Notification(Notification.NotificationType.SUCCESS, "Vous avez bien accepté l'invitation", "mailbox")).send((EntityPlayerMP)player);
    }
    
    private void denyEmail(Mail mail, EntityPlayer player, String uniqueId) throws IOException {
      ApiServices.Http.getMailboxService().deleteEmail(mail.getId(), uniqueId).execute();
      (new Notification(Notification.NotificationType.INFO, "Vous avez bien refusé l'invitation", "mailbox")).send((EntityPlayerMP)player);
    }
    
    private void deleteEmail(Mail mail, EntityPlayer player, String uniqueId) throws IOException {
      ApiServices.Http.getMailboxService().deleteEmail(mail.getId(), uniqueId).execute();
      (new Notification(Notification.NotificationType.INFO, "Vous avez bien supprimé ce mail", "mailbox")).send((EntityPlayerMP)player);
    }
    
    private void recupEmail(Mail mail, EntityPlayer player, String uniqueId) throws IOException {
      int sizeNeeded = mail.getEncodedItems().size();
      if (!InventoryUtils.hasSpaceInventory(player, new ItemStack(Item.func_150898_a(Blocks.field_150357_h)), sizeNeeded * 64)) {
        (new Notification(Notification.NotificationType.ERROR, "Vous devez avoir " + sizeNeeded + " slots de libre", "mailbox")).send((EntityPlayerMP)player);
        return;
      } 
      Response<String> call = ApiServices.Http.getMailboxService().consumesItem(mail.getId(), uniqueId).execute();
      if (!call.isSuccessful())
        return; 
      for (String encodedItem : mail.getEncodedItems()) {
        ItemStack is = ItemStackSerializer.read(new String(Base64.getDecoder().decode(encodedItem)));
        if (is == null)
          continue; 
        InventoryUtils.giveOrDropitems(player, is.func_77946_l());
      } 
      (new Notification(Notification.NotificationType.SUCCESS, "Vous avez bien récupéré vos items", "mailbox")).send((EntityPlayerMP)player);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\common\packets\server\MailboxServerProcessMailPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */