package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.managers;

import fr.paladium.common.CommonModule;
import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.objects.AugustConfig;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects.ServerLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.io.File;
import java.util.List;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayer;

public class AugustConfigManager {
  private static final String CONFIG_NAME = "august_lb_config.json";
  
  public static final String LOCATION_NOT_FOUND_MESSAGE = "&cAucun emplacement n'a été trouvé pour le serveur: &a%s";
  
  public static final String TELEPORTATION_MESSAGE = "&aVous avez été téléporté à l'emplacement du serveur: &c%s";
  
  private static AugustConfigManager instance;
  
  private AugustConfig config;
  
  public AugustConfig getConfig() {
    return this.config;
  }
  
  public AugustConfigManager() {
    instance = this;
    loadConfig();
  }
  
  public static AugustConfigManager getInstance() {
    if (instance == null)
      instance = new AugustConfigManager(); 
    return instance;
  }
  
  public void teleport(EntityPlayer player, String serverName, List<ServerLocation> locations) {
    Optional<DoubleLocation> result = getLocation(serverName, locations);
    if (!result.isPresent()) {
      MonthlyUtils.sendMessage(player, new String[] { "&cAucun emplacement n'a été trouvé pour le serveur: &a%s" });
      return;
    } 
    MonthlyUtils.sendMessage(player, new String[] { String.format("&aVous avez été téléporté à l'emplacement du serveur: &c%s", new Object[] { serverName }) });
    ((DoubleLocation)result.get()).teleportServer(player);
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
    File file = new File(PalaMod.conf, "august_lb_config.json");
    try {
      this.config = (AugustConfig)JsonConfigLoader.loadConfig(file, AugustConfig.class);
    } catch (Exception e) {
      this.config = new AugustConfig();
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\server\managers\AugustConfigManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */