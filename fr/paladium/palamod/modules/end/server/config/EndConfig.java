package fr.paladium.palamod.modules.end.server.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.paladium.palamod.util.BlockLocation;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class EndConfig {
  public List<BlockLocation> hole = new ArrayList<>();
  
  public List<BlockLocation> portal = new ArrayList<>();
  
  public List<BlockLocation> endportal = new ArrayList<>();
  
  public List<BlockLocation> crystals = new ArrayList<>();
  
  public List<BlockLocation> spawns = new ArrayList<>();
  
  public List<BlockLocation> dragon = new ArrayList<>();
  
  public List<BlockLocation> egg = new ArrayList<>();
  
  public void save(File file) {
    try {
      if (!file.exists()) {
        file.createNewFile();
        System.out.println("[End] Creating file " + file.getAbsolutePath());
      } 
      Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
      FileWriter writer = new FileWriter(file);
      gson.toJson(this, writer);
      writer.close();
      System.out.println("[End] Saving file " + file.getAbsolutePath());
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\server\config\EndConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */