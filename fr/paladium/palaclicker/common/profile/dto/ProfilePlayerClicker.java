package fr.paladium.palaclicker.common.profile.dto;

import java.util.List;

public class ProfilePlayerClicker {
  private final String uuid;
  
  private final List<ProfileClickerBuilding> buildings;
  
  private final List<String> upgrades;
  
  private final String lastBuildingBought;
  
  private final double rps;
  
  public ProfilePlayerClicker(String uuid, List<ProfileClickerBuilding> buildings, List<String> upgrades, String lastBuildingBought, double rps) {
    this.uuid = uuid;
    this.buildings = buildings;
    this.upgrades = upgrades;
    this.lastBuildingBought = lastBuildingBought;
    this.rps = rps;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public List<ProfileClickerBuilding> getBuildings() {
    return this.buildings;
  }
  
  public List<String> getUpgrades() {
    return this.upgrades;
  }
  
  public String getLastBuildingBought() {
    return this.lastBuildingBought;
  }
  
  public double getRps() {
    return this.rps;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\common\profile\dto\ProfilePlayerClicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */