package fr.paladium.palamod.pulsar.configs;

import cpw.mods.fml.common.Loader;
import fr.paladium.palamod.pulsar.pulse.PulseMeta;
import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class MConfigs implements IConfiguration {
  private static Configuration config;
  
  private final String confPath;
  
  private final String description;
  
  public MConfigs(String confName, String description) {
    this
      .confPath = Loader.instance().getConfigDir().toString() + File.separator + confName + ".cfg";
    this.description = description;
  }
  
  public void load() {
    config = new Configuration(new File(this.confPath));
    config.load();
  }
  
  public boolean isModuleEnabled(PulseMeta meta) {
    return config.get(this.description, meta.getId(), meta.isEnabled(), meta.getDescription())
      .getBoolean(meta.isEnabled());
  }
  
  public void flush() {
    if (config.hasChanged())
      config.save(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\pulsar\configs\MConfigs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */