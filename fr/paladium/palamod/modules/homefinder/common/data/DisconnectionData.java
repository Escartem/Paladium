package fr.paladium.palamod.modules.homefinder.common.data;

import fr.paladium.palamod.modules.homefinder.common.managers.DisconnectionDataManager;
import fr.paladium.palamod.modules.homefinder.common.tiles.TileEntityHomeFinder;
import fr.paladium.palamod.modules.homefinder.common.tiles.objects.LocationSaver;
import fr.paladium.palamod.modules.homefinder.common.tiles.objects.LocationType;
import fr.paladium.palamod.modules.homefinder.common.utils.Location;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class DisconnectionData {
  private String path;
  
  private String playerName;
  
  private UUID uniqueId;
  
  private Location location;
  
  private boolean cancelled;
  
  public void setPath(String path) {
    this.path = path;
  }
  
  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }
  
  public void setUniqueId(UUID uniqueId) {
    this.uniqueId = uniqueId;
  }
  
  public void setLocation(Location location) {
    this.location = location;
  }
  
  public void setCancelled(boolean cancelled) {
    this.cancelled = cancelled;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof DisconnectionData))
      return false; 
    DisconnectionData other = (DisconnectionData)o;
    if (!other.canEqual(this))
      return false; 
    if (isCancelled() != other.isCancelled())
      return false; 
    Object this$path = getPath(), other$path = other.getPath();
    if ((this$path == null) ? (other$path != null) : !this$path.equals(other$path))
      return false; 
    Object this$playerName = getPlayerName(), other$playerName = other.getPlayerName();
    if ((this$playerName == null) ? (other$playerName != null) : !this$playerName.equals(other$playerName))
      return false; 
    Object this$uniqueId = getUniqueId(), other$uniqueId = other.getUniqueId();
    if ((this$uniqueId == null) ? (other$uniqueId != null) : !this$uniqueId.equals(other$uniqueId))
      return false; 
    Object this$location = getLocation(), other$location = other.getLocation();
    return !((this$location == null) ? (other$location != null) : !this$location.equals(other$location));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof DisconnectionData;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + (isCancelled() ? 79 : 97);
    Object $path = getPath();
    result = result * 59 + (($path == null) ? 43 : $path.hashCode());
    Object $playerName = getPlayerName();
    result = result * 59 + (($playerName == null) ? 43 : $playerName.hashCode());
    Object $uniqueId = getUniqueId();
    result = result * 59 + (($uniqueId == null) ? 43 : $uniqueId.hashCode());
    Object $location = getLocation();
    return result * 59 + (($location == null) ? 43 : $location.hashCode());
  }
  
  public String toString() {
    return "DisconnectionData(path=" + getPath() + ", playerName=" + getPlayerName() + ", uniqueId=" + getUniqueId() + ", location=" + getLocation() + ", cancelled=" + isCancelled() + ")";
  }
  
  public String getPath() {
    return this.path;
  }
  
  public String getPlayerName() {
    return this.playerName;
  }
  
  public UUID getUniqueId() {
    return this.uniqueId;
  }
  
  public Location getLocation() {
    return this.location;
  }
  
  public boolean isCancelled() {
    return this.cancelled;
  }
  
  public DisconnectionData(String path, String playerName, UUID uniqueId, Location location, boolean cancelled) {
    this.path = path;
    this.playerName = playerName;
    this.uniqueId = uniqueId;
    this.location = location;
    this.cancelled = cancelled;
  }
  
  public DisconnectionData(EntityPlayer player) {
    this.playerName = player.getDisplayName();
    this.uniqueId = player.func_110124_au();
    this.location = new Location(player.field_70165_t, player.field_70163_u, player.field_70161_v);
    this.cancelled = false;
  }
  
  public void save(World world) {
    DisconnectionDataManager.getInstance().addDisconnection(this.playerName, this.uniqueId, world, this.location, this.cancelled);
  }
  
  public boolean teleportAtSpawn(EntityPlayer player) {
    World world = player.field_70170_p;
    ChunkCoordinates spawnPoint = world.func_72861_E();
    try {
      Player bukkitPlayer = Bukkit.getPlayer(player.func_110124_au());
      if (bukkitPlayer != null && bukkitPlayer.isOnline()) {
        World bukkitWorld = bukkitPlayer.getWorld();
        Location location = new Location(bukkitWorld, spawnPoint.field_71574_a, spawnPoint.field_71572_b, spawnPoint.field_71573_c);
        bukkitPlayer.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
      } 
    } catch (Exception e1) {
      player.func_70634_a(spawnPoint.field_71574_a, spawnPoint.field_71572_b, spawnPoint.field_71573_c);
    } 
    updateLocation(player, this.cancelled);
    this.cancelled = false;
    return true;
  }
  
  public void updateLocation(EntityPlayer player, boolean cancelled) {
    this.location = new Location(player.field_70165_t, player.field_70163_u, player.field_70161_v);
    this.cancelled = cancelled;
  }
  
  public boolean isOnRadius(Location location, int radius) {
    return (this.location.distanceTo(location) <= radius);
  }
  
  public LocationSaver toLocationSaver(TileEntityHomeFinder finder, World world) {
    return new LocationSaver(this.playerName, this.uniqueId, finder, LocationType.DISCONNECTION, this.location, world);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\common\data\DisconnectionData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */