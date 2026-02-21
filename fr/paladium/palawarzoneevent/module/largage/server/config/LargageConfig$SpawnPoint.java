package fr.paladium.palawarzoneevent.module.largage.server.config;

public class SpawnPoint {
  private final float x;
  
  private final float z;
  
  public String toString() {
    return "LargageConfig.SpawnPoint(x=" + getX() + ", z=" + getZ() + ")";
  }
  
  public float getX() {
    return this.x;
  }
  
  public float getZ() {
    return this.z;
  }
  
  public SpawnPoint(float x, float z) {
    this.x = x;
    this.z = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\server\config\LargageConfig$SpawnPoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */