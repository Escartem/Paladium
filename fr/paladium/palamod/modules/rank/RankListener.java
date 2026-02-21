package fr.paladium.palamod.modules.rank;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;

public class RankListener {
  private static final String PERMISSION = "palamod.emoji";
  
  private static final HashMap<String, String> REPLACEMENTS = new HashMap<>();
  
  private static Field messageField = null;
  
  static {
    REPLACEMENTS.put("<3", "§c❤");
    REPLACEMENTS.put(":heart:", "§c❤");
    REPLACEMENTS.put(":star:", "§e❤");
    REPLACEMENTS.put(":yes:", "§a✔");
    REPLACEMENTS.put(":no:", "§c✖");
    REPLACEMENTS.put(":arrow:", "➜");
    REPLACEMENTS.put(":typing:", "✎ ..");
    REPLACEMENTS.put(":shrug:", "¯\\_(ツ)_/¯");
    REPLACEMENTS.put(":tableflip:", "(╯°□°)╯︵ ┻━┻");
    REPLACEMENTS.put(":unflip:", "┬─┬ノ( º _ ºノ)");
    REPLACEMENTS.put(":uwu:", "(ꈍ ω ꈍ)");
    REPLACEMENTS.put(":owo:", "(✧ω✧)");
    REPLACEMENTS.put(":leny:", "( ͡° ͜ʖ ͡°)");
    REPLACEMENTS.put(":java:", "☕");
    REPLACEMENTS.put(":peace:", "✌");
    try {
      messageField = ServerChatEvent.class.getDeclaredField("message");
      messageField.setAccessible(true);
    } catch (NoSuchFieldException noSuchFieldException) {}
  }
  
  public static void register() {
    RankListener listener = new RankListener();
    FMLCommonHandler.instance().bus().register(listener);
    MinecraftForge.EVENT_BUS.register(listener);
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
  public void onPlayerChat(ServerChatEvent event) {
    EntityPlayerMP player = event.player;
    if (!BukkitUtils.hasPermission((Entity)player, "palamod.emoji") || messageField == null)
      return; 
    try {
      String originalMessage = event.message;
      String modifiedMessage = replaceEmojis(originalMessage);
      if (!originalMessage.equals(modifiedMessage))
        messageField.set(event, modifiedMessage); 
    } catch (IllegalAccessException illegalAccessException) {}
  }
  
  private String replaceEmojis(String message) {
    StringBuilder sb = new StringBuilder(message);
    for (Map.Entry<String, String> entry : REPLACEMENTS.entrySet()) {
      int index;
      while ((index = sb.indexOf(entry.getKey())) != -1)
        sb.replace(index, index + ((String)entry.getKey()).length(), entry.getValue()); 
    } 
    return sb.toString();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\rank\RankListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */