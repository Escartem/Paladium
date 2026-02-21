package fr.paladium.palaclicker.common.network.packet.building;

import fr.paladium.palaclicker.PalaClickerMod;
import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.palaclicker.server.config.building.dto.ClickerBuilding;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSide;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSPacketClickerBuildingBuy extends ForgePacket {
  @PacketData(PacketSide.CLIENT)
  private String buildingId;
  
  public CSPacketClickerBuildingBuy(String buildingId) {
    this.buildingId = buildingId;
  }
  
  public CSPacketClickerBuildingBuy() {}
  
  public void processServer(EntityPlayerMP player) {
    Optional<ClickerBuilding> building = PalaClickerMod.getServer().getBuildingConfig().getBuilding(this.buildingId);
    if (!building.isPresent())
      return; 
    PlayerClickerData data = PlayerClickerData.get((Entity)player);
    if (data == null)
      return; 
    int count = data.getBuildingCount(((ClickerBuilding)building.get()).getId());
    double price = ((ClickerBuilding)building.get()).getPrice(count);
    if (data.getPoints() < price || count >= 99)
      return; 
    data.addPoints(-price);
    data.addBuilding(((ClickerBuilding)building.get()).getId());
    data.setLastBuildingBought(((ClickerBuilding)building.get()).getId());
    reply(Boolean.valueOf(false));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\common\network\packet\building\CSPacketClickerBuildingBuy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */