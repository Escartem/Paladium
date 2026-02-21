package fr.paladium.palaconfiguration.server.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.paladium.palaconfiguration.server.strategy.ConfigExclusionStrategy;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import fr.paladium.palaconfiguration.server.utils.json.adapter.ItemStackAdapter;
import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import fr.paladium.permissionbridge.common.data.PermissibleEntity;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

public class ConfigurationModUtils {
  private static final String INFORMATION_MESSAGE = "§7Informations sur §e%s§7 :";
  
  private static final String HIDDEN_MESSAGE = "§cCette configuration est cachée !";
  
  private static final String NULL_MESSAGE = "§cCette configuration est nulle !";
  
  private static final Gson GSON = (new GsonBuilder()).serializeNulls().setPrettyPrinting().setLenient().registerTypeAdapter(ItemStack.class, new ItemStackAdapter()).setExclusionStrategies(new ExclusionStrategy[] { (ExclusionStrategy)new ConfigExclusionStrategy() }).create();
  
  public static void sendJsonObject(ICommandSender sender, Object object) {
    sendJsonObject(sender, null, object);
  }
  
  public static void sendJsonObject(ICommandSender sender, String name, Object object) {
    if (object == null) {
      ChatUtils.sendColoredMessage(sender, new String[] { "§cCette configuration est nulle !" });
      return;
    } 
    if (object instanceof fr.paladium.palaconfiguration.server.system.IConfig && !(sender instanceof MinecraftServer)) {
      ConfigFile configFile = object.getClass().<ConfigFile>getAnnotation(ConfigFile.class);
      if (configFile != null && configFile.hidden()) {
        ChatUtils.sendColoredMessage(sender, new String[] { "§cCette configuration est cachée !" });
        return;
      } 
    } 
    ChatUtils.sendColoredMessage(sender, new String[] { "§8§m---------------------------------" });
    try {
      String json = GSON.toJson(object);
      String[] messages = json.split("\n");
      ChatUtils.sendColoredMessage(sender, new String[] { "§8§m---------------------------------" });
      ChatUtils.sendColoredMessage(sender, new String[] { String.format("§7Informations sur §e%s§7 :", new Object[] { name }) });
      for (String message : messages) {
        ChatUtils.sendColoredMessage(sender, new String[] { message });
      } 
    } catch (Exception e) {
      ChatUtils.sendColoredMessage(sender, new String[] { object.toString() });
      e.printStackTrace();
    } 
    ChatUtils.sendColoredMessage(sender, new String[] { "§8§m---------------------------------" });
  }
  
  public static List<ICommandSender> getPlayersWithPermission(String permission) {
    List<ICommandSender> senders = new ArrayList<>();
    PlayerUtils.getOnlinePlayers().stream().filter(player -> PermissionManager.inst().hasPermission(PermissibleEntity.from((Entity)player), permission)).forEach(senders::add);
    senders.add(MinecraftServer.func_71276_C());
    return senders;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\serve\\utils\ConfigurationModUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */