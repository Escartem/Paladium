package fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data;

import fr.paladium.palashop.server.shop.dto.ShopRarity;
import java.util.Arrays;

public class CosmeticProperties {
  private String name;
  
  private ShopRarity rarity;
  
  private String[] behaviors;
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setRarity(ShopRarity rarity) {
    this.rarity = rarity;
  }
  
  public void setBehaviors(String[] behaviors) {
    this.behaviors = behaviors;
  }
  
  public String toString() {
    return "CosmeticProperties(name=" + getName() + ", rarity=" + getRarity() + ", behaviors=" + Arrays.deepToString((Object[])getBehaviors()) + ")";
  }
  
  public CosmeticProperties() {
    this.name = null;
    this.rarity = null;
    this.behaviors = new String[0];
  }
  
  public CosmeticProperties(String name, ShopRarity rarity, String[] behaviors) {
    this.name = null;
    this.rarity = null;
    this.behaviors = new String[0];
    this.name = name;
    this.rarity = rarity;
    this.behaviors = behaviors;
  }
  
  public String getName() {
    return this.name;
  }
  
  public ShopRarity getRarity() {
    return this.rarity;
  }
  
  public String[] getBehaviors() {
    return this.behaviors;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\dto\cosmetic\data\CosmeticProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */