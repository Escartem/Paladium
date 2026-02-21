package fr.paladium.palaclicker.common.network.packet.upgrade;

import fr.paladium.palaclicker.PalaClickerMod;
import fr.paladium.palaclicker.server.config.upgrade.dto.ClickerUpgrade;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketClickerUpgradeData extends ForgePacket {
  public void processServer(EntityPlayerMP player) {
    reply(new BBPacketClickerUpgradeDataObject(PalaClickerMod.getServer().getUpgradeConfig().getUpgradeList()));
  }
  
  public class BBPacketClickerUpgradeDataObject {
    private final List<ClickerUpgrade> upgradeList;
    
    public BBPacketClickerUpgradeDataObject(List<ClickerUpgrade> upgradeList) {
      this.upgradeList = upgradeList;
    }
    
    public List<ClickerUpgrade> getUpgradeList() {
      return this.upgradeList;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\common\network\packe\\upgrade\BBPacketClickerUpgradeData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */