package fr.paladium.palamod.modules.homefinder.common.tiles.objects;

import fr.paladium.palamod.modules.homefinder.common.entities.EntityFakePlayer;
import fr.paladium.palamod.modules.homefinder.common.tiles.TileEntityHomeFinder;
import fr.paladium.palamod.modules.homefinder.common.utils.Location;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class LocationSaver {
  private String playerName;
  
  private UUID uniqueId;
  
  private LocationType locationType;
  
  private Location location;
  
  private String homeName;
  
  private EntityFakePlayer fakePlayer;
  
  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }
  
  public void setUniqueId(UUID uniqueId) {
    this.uniqueId = uniqueId;
  }
  
  public void setLocationType(LocationType locationType) {
    this.locationType = locationType;
  }
  
  public void setLocation(Location location) {
    this.location = location;
  }
  
  public void setHomeName(String homeName) {
    this.homeName = homeName;
  }
  
  public void setFakePlayer(EntityFakePlayer fakePlayer) {
    this.fakePlayer = fakePlayer;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof LocationSaver))
      return false; 
    LocationSaver other = (LocationSaver)o;
    if (!other.canEqual(this))
      return false; 
    Object this$playerName = getPlayerName(), other$playerName = other.getPlayerName();
    if ((this$playerName == null) ? (other$playerName != null) : !this$playerName.equals(other$playerName))
      return false; 
    Object this$uniqueId = getUniqueId(), other$uniqueId = other.getUniqueId();
    if ((this$uniqueId == null) ? (other$uniqueId != null) : !this$uniqueId.equals(other$uniqueId))
      return false; 
    Object this$locationType = getLocationType(), other$locationType = other.getLocationType();
    if ((this$locationType == null) ? (other$locationType != null) : !this$locationType.equals(other$locationType))
      return false; 
    Object this$location = getLocation(), other$location = other.getLocation();
    if ((this$location == null) ? (other$location != null) : !this$location.equals(other$location))
      return false; 
    Object this$homeName = getHomeName(), other$homeName = other.getHomeName();
    if ((this$homeName == null) ? (other$homeName != null) : !this$homeName.equals(other$homeName))
      return false; 
    Object this$fakePlayer = getFakePlayer(), other$fakePlayer = other.getFakePlayer();
    return !((this$fakePlayer == null) ? (other$fakePlayer != null) : !this$fakePlayer.equals(other$fakePlayer));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof LocationSaver;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $playerName = getPlayerName();
    result = result * 59 + (($playerName == null) ? 43 : $playerName.hashCode());
    Object $uniqueId = getUniqueId();
    result = result * 59 + (($uniqueId == null) ? 43 : $uniqueId.hashCode());
    Object $locationType = getLocationType();
    result = result * 59 + (($locationType == null) ? 43 : $locationType.hashCode());
    Object $location = getLocation();
    result = result * 59 + (($location == null) ? 43 : $location.hashCode());
    Object $homeName = getHomeName();
    result = result * 59 + (($homeName == null) ? 43 : $homeName.hashCode());
    Object $fakePlayer = getFakePlayer();
    return result * 59 + (($fakePlayer == null) ? 43 : $fakePlayer.hashCode());
  }
  
  public String getPlayerName() {
    return this.playerName;
  }
  
  public UUID getUniqueId() {
    return this.uniqueId;
  }
  
  public LocationType getLocationType() {
    return this.locationType;
  }
  
  public Location getLocation() {
    return this.location;
  }
  
  public String getHomeName() {
    return this.homeName;
  }
  
  public EntityFakePlayer getFakePlayer() {
    return this.fakePlayer;
  }
  
  public LocationSaver(String playerName, UUID uniqueId, TileEntityHomeFinder homeFinder, LocationType locationType, Location location, World world, String homeName) {
    this.playerName = playerName;
    this.uniqueId = uniqueId;
    this.locationType = locationType;
    this.location = location;
    this.homeName = homeName;
    this.fakePlayer = new EntityFakePlayer(playerName, uniqueId, homeFinder, this, location, world);
  }
  
  public LocationSaver(String playerName, UUID uniqueId, TileEntityHomeFinder homeFinder, LocationType locationType, Location location, World world) {
    this.playerName = playerName;
    this.uniqueId = uniqueId;
    this.locationType = locationType;
    this.location = location;
    this.homeName = null;
    this.fakePlayer = new EntityFakePlayer(playerName, uniqueId, homeFinder, this, location, world);
  }
  
  public void spawnFakePlayer(World world) {
    IChunkProvider provider = world.func_72863_F();
    int chunkX = (int)this.location.getX() >> 4;
    int chunkZ = (int)this.location.getZ() >> 4;
    provider.func_73158_c(chunkX, chunkZ);
    int y = this.location.getBlockY();
    while (!world.func_147437_c(this.location.getBlockX(), y, this.location.getBlockZ()) && !world.func_147437_c(this.location.getBlockX(), y + 1, this.location.getBlockZ()) && y < 255)
      y++; 
    world.func_72838_d((Entity)this.fakePlayer);
  }
  
  public String toString() {
    return "LocationSaver [playerName=" + this.playerName + ", uniqueId=" + this.uniqueId + ", locationType=" + this.locationType + ", location=" + this.location + ", homeName=" + this.homeName + ", fakePlayer=" + this.fakePlayer + "]";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\common\tiles\objects\LocationSaver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */