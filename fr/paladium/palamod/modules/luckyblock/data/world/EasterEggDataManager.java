package fr.paladium.palamod.modules.luckyblock.data.world;

import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palamod.PalaMod;
import java.io.File;

public class EasterEggDataManager {
  private static final String CONFIG_NAME = "easter_egg_data.json";
  
  private static EasterEggDataManager instance;
  
  private EasterEggData data;
  
  public EasterEggData getData() {
    return this.data;
  }
  
  public EasterEggDataManager() {
    instance = this;
    loadConfig();
  }
  
  public static EasterEggDataManager getInstance() {
    if (instance == null)
      instance = new EasterEggDataManager(); 
    return instance;
  }
  
  public void updateCode(String code) {
    this.data.setCode(code);
    saveConfig();
  }
  
  public void updateUsername(String username) {
    this.data.setUnlockerUsername(username);
    saveConfig();
  }
  
  public void resetCode() {
    this.data.setCode(null);
    this.data.setUnlockerUsername(null);
    saveConfig();
  }
  
  public void loadConfig() {
    File file = new File(PalaMod.conf, "easter_egg_data.json");
    try {
      this.data = (EasterEggData)JsonConfigLoader.loadConfig(file, EasterEggData.class);
    } catch (Exception e) {
      e.printStackTrace();
      this.data = new EasterEggData();
    } 
  }
  
  public void saveConfig() {
    File file = new File(PalaMod.conf, "easter_egg_data.json");
    try {
      JsonConfigLoader.saveConfig(file, this.data);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\data\world\EasterEggDataManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */