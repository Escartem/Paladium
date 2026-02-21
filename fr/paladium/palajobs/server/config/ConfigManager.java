package fr.paladium.palajobs.server.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.utils.forge.location.BlockLocation;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
  private static ConfigManager instance;
  
  private final Map<JobType, BlockLocation> bosses;
  
  public ConfigManager() {
    instance = this;
    this.bosses = new HashMap<>();
    for (JobType type : JobType.values())
      this.bosses.put(type, new BlockLocation(0, 0, 0)); 
  }
  
  public void setInstance() {
    instance = this;
  }
  
  public void save() {
    Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
    String content = gson.toJson(this);
    File file = new File(PalaJobs.getServer().getConfigDirectory().getAbsolutePath(), "palajobs.json");
    try {
      FileWriter writer = new FileWriter(file, false);
      writer.write(content);
      writer.close();
    } catch (Exception error) {
      error.printStackTrace();
    } 
  }
  
  public Map<JobType, BlockLocation> getBosses() {
    return this.bosses;
  }
  
  public static void setInstance(ConfigManager instance) {
    ConfigManager.instance = instance;
  }
  
  public static ConfigManager getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\server\config\ConfigManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */