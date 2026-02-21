package fr.paladium.palamod.modules.palaboss.common.utils;

public class BossLocation {
  private int x;
  
  private int y;
  
  private int z;
  
  public BossLocation(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getZ() {
    return this.z;
  }
  
  public String format() {
    return "x: " + this.x + ", y: " + this.y + ", z: " + this.z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\commo\\utils\BossLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */