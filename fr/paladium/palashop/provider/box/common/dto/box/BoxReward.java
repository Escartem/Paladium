package fr.paladium.palashop.provider.box.common.dto.box;

import fr.paladium.palashop.server.shop.dto.ShopRarity;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import java.util.Objects;

public class BoxReward {
  private Type type;
  
  private Integer weight;
  
  private IShopItem shopItem;
  
  public String toString() {
    return "BoxReward(type=" + getType() + ", weight=" + getWeight() + ", shopItem=" + getShopItem() + ")";
  }
  
  public BoxReward(Type type, Integer weight, IShopItem shopItem) {
    this.type = type;
    this.weight = weight;
    this.shopItem = shopItem;
  }
  
  public Type getType() {
    return this.type;
  }
  
  public Integer getWeight() {
    return this.weight;
  }
  
  public IShopItem getShopItem() {
    return this.shopItem;
  }
  
  public ShopRarity getRarity() {
    if (this.shopItem != null)
      return (this.shopItem.getRarity() == ShopRarity.RARE) ? ShopRarity.COMMON : this.shopItem.getRarity(); 
    if (this.type == Type.BOOST_LEGENDARY)
      return ShopRarity.EPIC; 
    return ShopRarity.COMMON;
  }
  
  public ShopRarity getShopItemRarity() {
    if (this.shopItem != null)
      return this.shopItem.getRarity(); 
    return ShopRarity.COMMON;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.shopItem, this.type });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    BoxReward other = (BoxReward)obj;
    return (Objects.equals(this.shopItem, other.shopItem) && this.type == other.type);
  }
  
  public enum Type {
    SHOP_ITEM(100, 7500.0F),
    BOOST_EPIC(300, 9000.0F),
    BOOST_LEGENDARY(600, 10500.0F),
    FREE_SPIN(100, 7500.0F);
    
    Type(int wheelCount, float duration) {
      this.wheelCount = wheelCount;
      this.duration = duration;
    }
    
    private final int wheelCount;
    
    private final float duration;
    
    public int getWheelCount() {
      return this.wheelCount;
    }
    
    public float getDuration() {
      return this.duration;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\dto\box\BoxReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */