package fr.paladium.palamod.modules.palaboss.common.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.palaboss.PPalaBoss;
import fr.paladium.palamod.modules.palaboss.common.utils.BossLocation;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.List;
import java.util.Random;

public class PalabossConfig {
  public void setLocationList(List<BossLocation> locationList) {
    this.locationList = locationList;
  }
  
  private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
  
  private List<BossLocation> locationList;
  
  public List<BossLocation> getLocationList() {
    return this.locationList;
  }
  
  public BossLocation getLocation() {
    Random random = new Random();
    if (this.locationList == null || this.locationList.isEmpty())
      return null; 
    int index = random.nextInt(this.locationList.size());
    return this.locationList.get(index);
  }
  
  @SideOnly(Side.SERVER)
  public void save() {
    try {
      File file = PPalaBoss.configFile;
      if (!file.exists()) {
        Files.createDirectories(file.getParentFile().toPath(), (FileAttribute<?>[])new FileAttribute[0]);
        file.createNewFile();
      } 
      Files.write(file.toPath(), GSON.toJson(this).getBytes(), new java.nio.file.OpenOption[0]);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\config\PalabossConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */