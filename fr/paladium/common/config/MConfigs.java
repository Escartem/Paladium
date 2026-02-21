package fr.paladium.common.config;

import cpw.mods.fml.common.Loader;
import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class MConfigs implements IConfiguration {
  private Configuration config;
  
  private final String confPath;
  
  private final String description;
  
  public Configuration getConfig() {
    return this.config;
  }
  
  public MConfigs(String confName, String description) {
    this
      .confPath = Loader.instance().getConfigDir().toString() + File.separator + confName + ".cfg";
    this.description = description;
  }
  
  public void load() {
    this.config = new Configuration(new File(this.confPath));
    this.config.load();
  }
  
  public void flush() {
    if (this.config.hasChanged())
      this.config.save(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\config\MConfigs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */