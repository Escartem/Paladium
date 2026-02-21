package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.managers;

import fr.paladium.common.CommonModule;
import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects.ServerLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.config.SeptemberConfig;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.io.File;
import java.util.List;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayer;

public class SeptemberConfigManager {
  private static final String CONFIG_NAME = "september_lb_config.json";
  
  public static final String LOCATION_NOT_FOUND_MESSAGE = "&cAucun emplacement n'a été trouvé pour le serveur: &a%s";
  
  public static final String TELEPORTATION_MESSAGE = "&aVous avez été téléporté à l'emplacement du serveur: &c%s";
  
  private static SeptemberConfigManager instance;
  
  private SeptemberConfig config;
  
  public SeptemberConfig getConfig() {
    return this.config;
  }
  
  public SeptemberConfigManager() {
    instance = this;
    loadConfig();
  }
  
  public static SeptemberConfigManager getInstance() {
    if (instance == null)
      instance = new SeptemberConfigManager(); 
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
    File file = new File(PalaMod.conf, "september_lb_config.json");
    try {
      this.config = (SeptemberConfig)JsonConfigLoader.loadConfig(file, SeptemberConfig.class);
    } catch (Exception e) {
      this.config = new SeptemberConfig();
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\managers\SeptemberConfigManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */