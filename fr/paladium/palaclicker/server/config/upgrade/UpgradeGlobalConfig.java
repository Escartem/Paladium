package fr.paladium.palaclicker.server.config.upgrade;

import fr.paladium.palaclicker.server.config.upgrade.dto.ClickerUpgrade;
import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ConfigFile(path = "clicker/upgrade.json", blocking = true)
public class UpgradeGlobalConfig implements IConfig {
  public List<ClickerUpgrade> getUpgradeList() {
    return this.upgradeList;
  }
  
  private final List<ClickerUpgrade> upgradeList = new ArrayList<>();
  
  public boolean isValid() {
    return (this.upgradeList != null && !this.upgradeList.isEmpty());
  }
  
  public void onLoaded() {}
  
  public void onReloaded() {}
  
  public void onFailed() {}
  
  public Optional<ClickerUpgrade> getUpgrade(String id) {
    return this.upgradeList.stream().filter(building -> building.getId().equals(id)).findAny();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\server\confi\\upgrade\UpgradeGlobalConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */