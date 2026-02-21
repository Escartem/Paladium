package fr.paladium.palaclicker.common.network.packet.upgrade;

import fr.paladium.palaclicker.PalaClickerMod;
import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.palaclicker.server.config.upgrade.dto.ClickerUpgrade;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSide;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSPacketClickerUpgradeBuy extends ForgePacket {
  @PacketData(PacketSide.CLIENT)
  private String upgradeId;
  
  public CSPacketClickerUpgradeBuy(String upgradeId) {
    this.upgradeId = upgradeId;
  }
  
  public CSPacketClickerUpgradeBuy() {}
  
  public void processServer(EntityPlayerMP player) {
    Optional<ClickerUpgrade> upgrade = PalaClickerMod.getServer().getUpgradeConfig().getUpgrade(this.upgradeId);
    if (!upgrade.isPresent())
      return; 
    PlayerClickerData data = PlayerClickerData.get((Entity)player);
    if (data == null)
      return; 
    double price = ((ClickerUpgrade)upgrade.get()).getPrice();
    if (data.getPoints() < price || !((ClickerUpgrade)upgrade.get()).checkConditions(data))
      return; 
    data.addPoints(-price);
    data.addUpgrade(((ClickerUpgrade)upgrade.get()).getId());
    reply(Boolean.valueOf(false));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\common\network\packe\\upgrade\CSPacketClickerUpgradeBuy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */