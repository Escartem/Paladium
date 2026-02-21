package fr.paladium.pet.server.config.global;

import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import fr.paladium.pet.server.PetServerProxy;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@ConfigFile(path = "pet/global_config.json", blocking = true)
public class GlobalConfig implements IConfig {
  private double experienceMultiplier;
  
  private long connectionIntervalMillis;
  
  private double experiencePerConnection;
  
  private double experiencePerPetCapture;
  
  private int happinessLoss;
  
  private int maxPetNameLength;
  
  private HashSet<String> whitelistedDamageEntities;
  
  private List<HappinessValue> happinessValues;
  
  public double getExperienceMultiplier() {
    return this.experienceMultiplier;
  }
  
  public long getConnectionIntervalMillis() {
    return this.connectionIntervalMillis;
  }
  
  public double getExperiencePerConnection() {
    return this.experiencePerConnection;
  }
  
  public double getExperiencePerPetCapture() {
    return this.experiencePerPetCapture;
  }
  
  public int getHappinessLoss() {
    return this.happinessLoss;
  }
  
  public int getMaxPetNameLength() {
    return this.maxPetNameLength;
  }
  
  public HashSet<String> getWhitelistedDamageEntities() {
    return this.whitelistedDamageEntities;
  }
  
  public List<HappinessValue> getHappinessValues() {
    return this.happinessValues;
  }
  
  public static GlobalConfig get() {
    return PetServerProxy.getInstance().getGlobalConfig();
  }
  
  public boolean isValid() {
    return (this.experienceMultiplier > 0.0D);
  }
  
  public void onLoaded() {
    this.happinessValues.sort(Comparator.comparingInt(HappinessValue::getMinLevel));
  }
  
  public void onReloaded() {}
  
  public void onFailed() {}
  
  public HappinessValue getHappinessValue(int level) {
    for (HappinessValue value : this.happinessValues) {
      if (level >= value.getMinLevel() && level <= value.getMaxLevel())
        return value; 
    } 
    return null;
  }
  
  public int getMaxHappiness(int level) {
    HappinessValue value = getHappinessValue(level);
    if (value == null)
      return 0; 
    return value.getMaxHappiness();
  }
  
  public double getHappinessMultiplier(int happiness) {
    if (happiness <= 0)
      return 0.0D; 
    return happiness / 100.0D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\config\global\GlobalConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */