package fr.paladium.palawarzoneevent.module.warzone.server.config;

import com.google.gson.annotations.SerializedName;
import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palawarzoneevent.module.warzone.WarzoneModule;
import fr.paladium.palawarzoneevent.module.warzone.server.phase.WarzonePhase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarzoneConfig {
  public static final String PATH = "palawarzoneevent/warzone.json";
  
  @SerializedName("defaults")
  private final List<String> defaultCommands = new ArrayList<>();
  
  public List<String> getDefaultCommands() {
    return this.defaultCommands;
  }
  
  @SerializedName("phases")
  private final Map<String, WarzonePhase> warzonePhases = new HashMap<>();
  
  public Map<String, WarzonePhase> getWarzonePhases() {
    return this.warzonePhases;
  }
  
  public WarzonePhase getWarzonePhaseByName(String phase) {
    return this.warzonePhases.get(phase);
  }
  
  public void save() {
    try {
      JsonConfigLoader.saveConfig(WarzoneModule.getServer().getConfigFilePath(), this);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\server\config\WarzoneConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */