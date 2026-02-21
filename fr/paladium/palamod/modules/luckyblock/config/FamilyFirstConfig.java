package fr.paladium.palamod.modules.luckyblock.config;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class FamilyFirstConfig {
  private int x;
  
  private int y;
  
  private int z;
  
  private Configuration config;
  
  public static FamilyFirstConfig instance;
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getZ() {
    return this.z;
  }
  
  public static FamilyFirstConfig getInstance() {
    return instance;
  }
  
  public FamilyFirstConfig(File location) {
    initConfigs(location);
    instance = this;
  }
  
  public void initConfigs(File location) {
    File configFile = new File(location + "/" + "palamod" + "_FAMILY_FIRST.cfg");
    this.config = new Configuration(configFile);
    this.x = this.config.get("FF", "X", 0).getInt();
    this.y = this.config.get("FF", "Y", 0).getInt();
    this.z = this.config.get("FF", "Z", 0).getInt();
    if (this.config.hasChanged())
      this.config.save(); 
  }
  
  public void setX(int x) {
    this.config.get("FF", "X", 0).set(x);
    this.x = x;
    if (this.config.hasChanged())
      this.config.save(); 
  }
  
  public void setY(int x) {
    this.config.get("FF", "Y", 0).set(x);
    this.y = x;
    if (this.config.hasChanged())
      this.config.save(); 
  }
  
  public void setZ(int x) {
    this.config.get("FF", "Z", 0).set(x);
    this.z = x;
    if (this.config.hasChanged())
      this.config.save(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\config\FamilyFirstConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */