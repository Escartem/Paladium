package fr.paladium.palarpg.module.dungeon.server.bukkit;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palarpg.module.dungeon.DungeonModule;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

@SideOnly(Side.SERVER)
public class DungeonBukkitImpl {
  private static Plugin plugin;
  
  public static void load() throws Exception {
    try {
      PluginManager manager = Bukkit.getPluginManager();
      Plugin plugin = manager.getPlugin("PalaCore");
      if (plugin == null)
        throw new RuntimeException("PalaCore plugin not found"); 
      DungeonBukkitImpl.plugin = plugin;
      DungeonBukkitImpl.plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
    } catch (Exception e) {
      throw e;
    } 
  }
  
  public static void connect(@NonNull EntityPlayerMP player, @NonNull String server) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (server == null)
      throw new NullPointerException("server is marked non-null but is null"); 
    try {
      Player bukkitPlayer = Bukkit.getPlayer(player.func_110124_au());
      if (bukkitPlayer == null) {
        DungeonModule.logger.error("Bukkit player not found for " + player.func_70005_c_(), new Object[0]);
        return;
      } 
      ByteArrayDataOutput out = ByteStreams.newDataOutput();
      out.writeUTF("Connect");
      out.writeUTF(server);
      bukkitPlayer.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    } catch (Exception e) {
      e.printStackTrace();
      DungeonModule.logger.error("Failed to connect player " + player.func_70005_c_() + " to server " + server, new Object[0]);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\bukkit\DungeonBukkitImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */