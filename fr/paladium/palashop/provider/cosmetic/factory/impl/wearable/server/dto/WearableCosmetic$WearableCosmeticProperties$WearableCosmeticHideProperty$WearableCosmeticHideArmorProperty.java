package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.dto;

public class WearableCosmeticHideArmorProperty {
  private boolean helmet;
  
  private boolean chestplate;
  
  private boolean leggings;
  
  private boolean boots;
  
  public void setHelmet(boolean helmet) {
    this.helmet = helmet;
  }
  
  public void setChestplate(boolean chestplate) {
    this.chestplate = chestplate;
  }
  
  public void setLeggings(boolean leggings) {
    this.leggings = leggings;
  }
  
  public void setBoots(boolean boots) {
    this.boots = boots;
  }
  
  public String toString() {
    return "WearableCosmetic.WearableCosmeticProperties.WearableCosmeticHideProperty.WearableCosmeticHideArmorProperty(helmet=" + isHelmet() + ", chestplate=" + isChestplate() + ", leggings=" + isLeggings() + ", boots=" + isBoots() + ")";
  }
  
  public WearableCosmeticHideArmorProperty() {}
  
  public WearableCosmeticHideArmorProperty(boolean helmet, boolean chestplate, boolean leggings, boolean boots) {
    this.helmet = helmet;
    this.chestplate = chestplate;
    this.leggings = leggings;
    this.boots = boots;
  }
  
  public boolean isHelmet() {
    return this.helmet;
  }
  
  public boolean isChestplate() {
    return this.chestplate;
  }
  
  public boolean isLeggings() {
    return this.leggings;
  }
  
  public boolean isBoots() {
    return this.boots;
  }
  
  public void hideAll() {
    this.helmet = true;
    this.chestplate = true;
    this.leggings = true;
    this.boots = true;
  }
  
  public void showAll() {
    this.helmet = false;
    this.chestplate = false;
    this.leggings = false;
    this.boots = false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\server\dto\WearableCosmetic$WearableCosmeticProperties$WearableCosmeticHideProperty$WearableCosmeticHideArmorProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */