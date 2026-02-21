package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.managers;

import fr.paladium.common.CommonModule;
import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects.ServerLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects.configs.JulyConfig;
import java.io.File;
import java.util.List;
import java.util.Optional;

public class JulyConfigManager {
  private static final String CONFIG_NAME = "july_lb_config.json";
  
  private static JulyConfigManager instance;
  
  private JulyConfig config;
  
  public JulyConfig getConfig() {
    return this.config;
  }
  
  public JulyConfigManager() {
    instance = this;
    loadConfig();
  }
  
  public static JulyConfigManager getInstance() {
    if (instance == null)
      instance = new JulyConfigManager(); 
    return instance;
  }
  
  public Optional<DoubleLocation> getLocation(List<ServerLocation> serverLocationList) {
    String serverName = CommonModule.getInstance().getConfig().getServerName();
    return getLocation(serverName, serverLocationList);
  }
  
  public Optional<DoubleLocation> getLocation(String serverName, List<ServerLocation> serverLocationList) {
    return serverLocationList.stream()
      .filter(serverLocation -> serverLocation.getServerName().equalsIgnoreCase(serverName))
      .map(ServerLocation::getLocation)
      .findFirst();
  }
  
  public void loadConfig() {
    File file = new File(PalaMod.conf, "july_lb_config.json");
    try {
      this.config = (JulyConfig)JsonConfigLoader.loadConfig(file, JulyConfig.class);
    } catch (Exception e) {
      this.config = new JulyConfig();
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\managers\JulyConfigManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */