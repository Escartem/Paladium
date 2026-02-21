package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.server.data;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.tiles.TileEntityChristmasWreath;

public class WreathData {
  private Location location;
  
  private TileEntityChristmasWreath wreath;
  
  public void setLocation(Location location) {
    this.location = location;
  }
  
  public void setWreath(TileEntityChristmasWreath wreath) {
    this.wreath = wreath;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof WreathData))
      return false; 
    WreathData other = (WreathData)o;
    if (!other.canEqual(this))
      return false; 
    Object this$location = getLocation(), other$location = other.getLocation();
    if ((this$location == null) ? (other$location != null) : !this$location.equals(other$location))
      return false; 
    Object this$wreath = getWreath(), other$wreath = other.getWreath();
    return !((this$wreath == null) ? (other$wreath != null) : !this$wreath.equals(other$wreath));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof WreathData;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $location = getLocation();
    result = result * 59 + (($location == null) ? 43 : $location.hashCode());
    Object $wreath = getWreath();
    return result * 59 + (($wreath == null) ? 43 : $wreath.hashCode());
  }
  
  public String toString() {
    return "WreathData(location=" + getLocation() + ", wreath=" + getWreath() + ")";
  }
  
  public WreathData(Location location, TileEntityChristmasWreath wreath) {
    this.location = location;
    this.wreath = wreath;
  }
  
  public Location getLocation() {
    return this.location;
  }
  
  public TileEntityChristmasWreath getWreath() {
    return this.wreath;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\server\data\WreathData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */