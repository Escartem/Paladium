package fr.paladium.palaclicker.common.network.packet.building;

import fr.paladium.palaclicker.server.config.building.dto.ClickerBuilding;
import java.util.List;

public class BBPacketClickerBuildingDataObject {
  private final List<ClickerBuilding> buildingList;
  
  public BBPacketClickerBuildingDataObject(List<ClickerBuilding> buildingList) {
    this.buildingList = buildingList;
  }
  
  public List<ClickerBuilding> getBuildingList() {
    return this.buildingList;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\common\network\packet\building\BBPacketClickerBuildingData$BBPacketClickerBuildingDataObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */