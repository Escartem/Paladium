package fr.paladium.pet.client.ui.utils.data;

import fr.paladium.pet.server.config.global.GlobalConfig;

public class ConfigClientData {
  private static ConfigClientData DATA;
  
  private int experiencePerConnection;
  
  private int happinessLoss;
  
  public ConfigClientData(int experiencePerConnection, int happinessLoss) {
    this.experiencePerConnection = experiencePerConnection;
    this.happinessLoss = happinessLoss;
  }
  
  public int getExperiencePerConnection() {
    return this.experiencePerConnection;
  }
  
  public int getHappinessLoss() {
    return this.happinessLoss;
  }
  
  public static ConfigClientData of(GlobalConfig config) {
    return new ConfigClientData(
        (int)config.getExperiencePerConnection(), config
        .getHappinessLoss());
  }
  
  public static void set(ConfigClientData data) {
    DATA = data;
  }
  
  public static ConfigClientData get() {
    if (DATA == null)
      DATA = new ConfigClientData(0, 0); 
    return DATA;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\u\\utils\data\ConfigClientData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */