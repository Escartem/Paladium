package fr.paladium.palaclicker.common.network.packet.building;

import fr.paladium.palaclicker.PalaClickerMod;
import fr.paladium.palaclicker.server.config.building.dto.ClickerBuilding;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketClickerBuildingData extends ForgePacket {
  public void processServer(EntityPlayerMP player) {
    reply(new BBPacketClickerBuildingDataObject(PalaClickerMod.getServer().getBuildingConfig().getBuildingList()));
  }
  
  public class BBPacketClickerBuildingDataObject {
    private final List<ClickerBuilding> buildingList;
    
    public BBPacketClickerBuildingDataObject(List<ClickerBuilding> buildingList) {
      this.buildingList = buildingList;
    }
    
    public List<ClickerBuilding> getBuildingList() {
      return this.buildingList;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\common\network\packet\building\BBPacketClickerBuildingData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */