package fr.paladium.palamod.modules.luckyblock.utils;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class PlayerUtils {
  private static final String prefix = "§6[§eLuckyBlock§6] ";
  
  public static String getPrefix() {
    return "§6[§eLuckyBlock§6] ";
  }
  
  public static String _n = "​​​";
  
  public static void sendToAll(String message) {
    List<EntityPlayerMP> players = (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b;
    if (players != null)
      players.forEach(player -> sendMessage((EntityPlayer)player, message)); 
  }
  
  public static void sendMessage(EntityPlayer p, String message) {
    p.func_145747_a((IChatComponent)new ChatComponentText("§6[§eLuckyBlock§6] " + message));
  }
  
  public static void sendHelpMessage(EntityPlayer player, int page) {
    player.func_146105_b((IChatComponent)new ChatComponentText("§8§m------------§8[§e" + page + "§8]§m------------"));
    for (int i = page * 10; i < Math.min((page + 1) * 10, (LuckyEvents.values()).length); i++) {
      LuckyEvents event = LuckyEvents.values()[i];
      ChatComponentText chatComponentText3 = new ChatComponentText(getPrefix() + "§e[" + event.ordinal() + "] §7" + event.getEvent().getName() + " (" + event.getEvent().getRarity() + ")");
      chatComponentText3
        .func_150255_a((new ChatStyle())
          .func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§6/luckyblock §e" + event
                .ordinal())))
          .func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/luckyblock " + event
              .ordinal())));
      player.func_146105_b((IChatComponent)chatComponentText3);
    } 
    ChatComponentText chatComponentText1 = new ChatComponentText("§e«");
    chatComponentText1.func_150255_a((new ChatStyle())
        .func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/luckyblock list " + (page - 1)))
        
        .func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§bPage précédente"))));
    ChatComponentText chatComponentText2 = new ChatComponentText("§e»");
    chatComponentText2.func_150255_a((new ChatStyle())
        .func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/luckyblock list " + (page + 1)))
        
        .func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§bPage suivante"))));
    IChatComponent chatComponentText = (new ChatComponentText("§8§m-------§8[")).func_150257_a((IChatComponent)chatComponentText1).func_150258_a("§8]§m--------§8[").func_150257_a((IChatComponent)chatComponentText2).func_150258_a("§8]§m-------");
    player.func_146105_b(chatComponentText);
  }
  
  public static void sendActionBar(EntityPlayerMP player, String message) {
    player.func_145747_a((IChatComponent)new ChatComponentText(message));
  }
  
  public static void dropItemStackAtEntity(Entity entity, ItemStack item) {
    dropItemStack(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, item);
  }
  
  public static void dropItemStack(Entity entity, double x, double y, double z, ItemStack item) {
    dropItemStack(entity.field_70170_p, x, y, z, item);
  }
  
  public static void dropItemStack(World world, double x, double y, double z, ItemStack item) {
    if (!world.field_72995_K) {
      EntityItem entityitem = new EntityItem(world, x, y + 1.0D, z, item);
      entityitem.field_145804_b = 10;
      world.func_72838_d((Entity)entityitem);
    } 
  }
  
  public static boolean removeItemStackFromInventory(EntityPlayer player, ItemStack stack) {
    int index = -1;
    for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
      ItemStack currentItem = player.field_71071_by.field_70462_a[i];
      if (currentItem != null && currentItem.func_77969_a(stack) && ItemStack.func_77970_a(currentItem, stack)) {
        index = i;
        break;
      } 
    } 
    if (index != -1) {
      player.field_71071_by.func_70298_a(index, stack.field_77994_a);
      return true;
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\PlayerUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */