package fr.paladium.palaclicker.server.config.building;

import fr.paladium.palaclicker.server.config.building.dto.ClickerBuilding;
import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@ConfigFile(path = "clicker/building.json", blocking = true)
public class BuildingGlobalConfig implements IConfig {
  public List<ClickerBuilding> getBuildingList() {
    return this.buildingList;
  }
  
  private final List<ClickerBuilding> buildingList = new LinkedList<>();
  
  public boolean isValid() {
    return (this.buildingList != null && !this.buildingList.isEmpty());
  }
  
  public void onLoaded() {
    for (int i = 0; i < this.buildingList.size(); i++)
      ((ClickerBuilding)this.buildingList.get(i)).setIndex(i); 
  }
  
  public void onReloaded() {
    onLoaded();
  }
  
  public void onFailed() {}
  
  public Optional<ClickerBuilding> getBuilding(String id) {
    return this.buildingList.stream().filter(building -> building.getId().equals(id)).findAny();
  }
  
  public Optional<ClickerBuilding> getBuilding(int index) {
    return this.buildingList.stream().filter(building -> (building.getIndex() == index)).findAny();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\server\config\building\BuildingGlobalConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */