package fr.paladium.palaclicker.common.profile.dto;

public class ProfileClickerBuilding {
  private final String name;
  
  private final double production;
  
  private final int quantity;
  
  public ProfileClickerBuilding(String name, double production, int quantity) {
    this.name = name;
    this.production = production;
    this.quantity = quantity;
  }
  
  public String getName() {
    return this.name;
  }
  
  public double getProduction() {
    return this.production;
  }
  
  public int getQuantity() {
    return this.quantity;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\common\profile\dto\ProfileClickerBuilding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */