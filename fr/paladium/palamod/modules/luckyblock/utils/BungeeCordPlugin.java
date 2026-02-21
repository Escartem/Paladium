package fr.paladium.palamod.modules.luckyblock.utils;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeeCordPlugin extends JavaPlugin implements PluginMessageListener {
  public static final String COMMAND_TP_COORDS = "TPCoords";
  
  public void onEnable() {
    getServer().getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");
    getServer().getMessenger().registerIncomingPluginChannel((Plugin)this, "BungeeCord", this);
  }
  
  public void onDisable() {
    getServer().getMessenger().unregisterOutgoingPluginChannel((Plugin)this);
    getServer().getMessenger().unregisterIncomingPluginChannel((Plugin)this);
  }
  
  public void onPluginMessageReceived(String channel, Player player, byte[] message) {
    if (!"BungeeCord".equals(channel))
      return; 
    ByteArrayDataInput in = ByteStreams.newDataInput(message);
    String subchannel = in.readUTF();
    if ("TPCoords".equals(subchannel)) {
      short len = in.readShort();
      byte[] msgbytes = new byte[len];
      in.readFully(msgbytes);
      DataInputStream msgin = new DataInputStream(new ByteArrayInputStream(msgbytes));
      Bukkit.getScheduler().runTaskLater((Plugin)this, () -> {
            try {
              Player pl = Bukkit.getPlayer(msgin.readUTF());
              World bukkitWorld = pl.getWorld();
              Location location = new Location(bukkitWorld, msgin.readDouble(), msgin.readDouble(), msgin.readDouble());
              pl.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
            } catch (Exception exception) {}
          }20L);
    } 
  }
  
  public static class BungeeCordCommandTPServerCoords {
    private final String playerUUID;
    
    private final String server;
    
    private final double x;
    
    private final double y;
    
    private final double z;
    
    public BungeeCordCommandTPServerCoords(String playerUUID, String server, double x, double y, double z) {
      this.playerUUID = playerUUID;
      this.server = server;
      this.x = x;
      this.y = y;
      this.z = z;
    }
    
    public String getPlayerUUID() {
      return this.playerUUID;
    }
    
    public String getServer() {
      return this.server;
    }
    
    public double getX() {
      return this.x;
    }
    
    public double getY() {
      return this.y;
    }
    
    public double getZ() {
      return this.z;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\BungeeCordPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */