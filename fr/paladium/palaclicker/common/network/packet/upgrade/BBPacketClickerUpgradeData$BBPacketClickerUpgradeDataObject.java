package fr.paladium.palaclicker.common.network.packet.upgrade;

import fr.paladium.palaclicker.server.config.upgrade.dto.ClickerUpgrade;
import java.util.List;

public class BBPacketClickerUpgradeDataObject {
  private final List<ClickerUpgrade> upgradeList;
  
  public BBPacketClickerUpgradeDataObject(List<ClickerUpgrade> upgradeList) {
    this.upgradeList = upgradeList;
  }
  
  public List<ClickerUpgrade> getUpgradeList() {
    return this.upgradeList;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\common\network\packe\\upgrade\BBPacketClickerUpgradeData$BBPacketClickerUpgradeDataObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */