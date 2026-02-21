package fr.paladium.palahologram.common.hologram;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;

public class HologramBuilder {
  private DoubleLocation location;
  
  private boolean spacing$set;
  
  private double spacing$value;
  
  private boolean followPlayerY$set;
  
  private boolean followPlayerY$value;
  
  private boolean followPlayerX$set;
  
  private boolean followPlayerX$value;
  
  private boolean angle$set;
  
  private double angle$value;
  
  public HologramBuilder location(DoubleLocation location) {
    this.location = location;
    return this;
  }
  
  public HologramBuilder spacing(double spacing) {
    this.spacing$value = spacing;
    this.spacing$set = true;
    return this;
  }
  
  public HologramBuilder followPlayerY(boolean followPlayerY) {
    this.followPlayerY$value = followPlayerY;
    this.followPlayerY$set = true;
    return this;
  }
  
  public HologramBuilder followPlayerX(boolean followPlayerX) {
    this.followPlayerX$value = followPlayerX;
    this.followPlayerX$set = true;
    return this;
  }
  
  public HologramBuilder angle(double angle) {
    this.angle$value = angle;
    this.angle$set = true;
    return this;
  }
  
  public Hologram build() {
    double spacing$value = this.spacing$value;
    if (!this.spacing$set)
      spacing$value = Hologram.access$000(); 
    boolean followPlayerY$value = this.followPlayerY$value;
    if (!this.followPlayerY$set)
      followPlayerY$value = Hologram.access$100(); 
    boolean followPlayerX$value = this.followPlayerX$value;
    if (!this.followPlayerX$set)
      followPlayerX$value = Hologram.access$200(); 
    double angle$value = this.angle$value;
    if (!this.angle$set)
      angle$value = Hologram.access$300(); 
    return new Hologram(this.location, spacing$value, followPlayerY$value, followPlayerX$value, angle$value);
  }
  
  public String toString() {
    return "Hologram.HologramBuilder(location=" + this.location + ", spacing$value=" + this.spacing$value + ", followPlayerY$value=" + this.followPlayerY$value + ", followPlayerX$value=" + this.followPlayerX$value + ", angle$value=" + this.angle$value + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palahologram\common\hologram\Hologram$HologramBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */