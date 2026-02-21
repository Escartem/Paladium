package fr.paladium.palashop.server.shop.dto.offer;

import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.IBuyable;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.NonNull;

public class ShopOffer implements IBuyable {
  private final ShopOfferType type;
  
  private final String id;
  
  private final String name;
  
  private final String thumbnail;
  
  private final String description;
  
  private final ShopRarity rarity;
  
  private final Long discount;
  
  private final String[] conditions;
  
  private final Boolean uniquePurchase;
  
  private final Boolean available;
  
  private final List<IShopItem> shopItems;
  
  private List<ConditionalBuyableObject<IShopItem>> conditionalShopItems;
  
  public String toString() {
    return "ShopOffer(type=" + getType() + ", id=" + getId() + ", name=" + getName() + ", thumbnail=" + getThumbnail() + ", description=" + getDescription() + ", rarity=" + getRarity() + ", discount=" + getDiscount() + ", conditions=" + Arrays.deepToString((Object[])getConditions()) + ", uniquePurchase=" + getUniquePurchase() + ", available=" + getAvailable() + ", shopItems=" + getShopItems() + ", conditionalShopItems=" + getConditionalShopItems() + ")";
  }
  
  public ShopOffer(ShopOfferType type, String id, String name, String thumbnail, String description, ShopRarity rarity, Long discount, String[] conditions, Boolean uniquePurchase, Boolean available, List<IShopItem> shopItems, List<ConditionalBuyableObject<IShopItem>> conditionalShopItems) {
    this.type = type;
    this.id = id;
    this.name = name;
    this.thumbnail = thumbnail;
    this.description = description;
    this.rarity = rarity;
    this.discount = discount;
    this.conditions = conditions;
    this.uniquePurchase = uniquePurchase;
    this.available = available;
    this.shopItems = shopItems;
    this.conditionalShopItems = conditionalShopItems;
  }
  
  public ShopOfferType getType() {
    return this.type;
  }
  
  public String getId() {
    return this.id;
  }
  
  public ShopRarity getRarity() {
    return this.rarity;
  }
  
  public Long getDiscount() {
    return this.discount;
  }
  
  public String[] getConditions() {
    return this.conditions;
  }
  
  public Boolean getUniquePurchase() {
    return this.uniquePurchase;
  }
  
  public Boolean getAvailable() {
    return this.available;
  }
  
  public List<IShopItem> getShopItems() {
    return this.shopItems;
  }
  
  public List<ConditionalBuyableObject<IShopItem>> getConditionalShopItems() {
    return this.conditionalShopItems;
  }
  
  @NonNull
  public ShopOffer copy() {
    return new ShopOffer(this.type, this.id, this.name, this.thumbnail, this.description, this.rarity, this.discount, this.conditions, this.uniquePurchase, this.available, new ArrayList<>(this.shopItems), (this.conditionalShopItems == null) ? null : new ArrayList<>(this.conditionalShopItems));
  }
  
  @NonNull
  public ShopOffer setConditionalShopItems(List<ConditionalBuyableObject<IShopItem>> conditionalShopItems) {
    this.conditionalShopItems = conditionalShopItems;
    return this;
  }
  
  public String getUniqueId() {
    return this.id;
  }
  
  @NonNull
  public String getName() {
    return TTT.format(this.name, new Object[0]);
  }
  
  @NonNull
  public String getDescription() {
    return TTT.format(this.description, new Object[0]);
  }
  
  public String getThumbnail() {
    return this.thumbnail;
  }
  
  public Long getPrice() {
    throw new UnsupportedOperationException("Offer does not have direct price, please use BuyableObject instead");
  }
  
  public Long getSubscription() {
    return Long.valueOf(0L);
  }
  
  public boolean isSubscription() {
    return false;
  }
  
  public Boolean isUniquePurchase() {
    return this.uniquePurchase;
  }
  
  public Boolean isAvailable() {
    return this.available;
  }
  
  public enum ShopOfferType {
    HORIZONTAL, DEFAULT;
    
    public boolean equals(@NonNull String name) {
      if (name == null)
        throw new NullPointerException("name is marked non-null but is null"); 
      return name().equalsIgnoreCase(name);
    }
    
    public static boolean is(@NonNull String name, @NonNull ShopOfferType value) {
      if (name == null)
        throw new NullPointerException("name is marked non-null but is null"); 
      if (value == null)
        throw new NullPointerException("value is marked non-null but is null"); 
      return value.equals(name);
    }
    
    public static ShopOfferType from(@NonNull String name) {
      if (name == null)
        throw new NullPointerException("name is marked non-null but is null"); 
      for (ShopOfferType type : values()) {
        if (type.name().equalsIgnoreCase(name))
          return type; 
      } 
      return null;
    }
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.id });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    ShopOffer other = (ShopOffer)obj;
    if (!this.id.equals(other.id))
      return false; 
    if ((this.shopItems == null && other.shopItems != null) || (this.shopItems != null && other.shopItems == null) || this.shopItems.size() != other.shopItems.size())
      return false; 
    if (this.conditionalShopItems != null && other.conditionalShopItems != null) {
      if (this.conditionalShopItems.size() != other.conditionalShopItems.size())
        return false; 
      boolean equals = true;
      for (ConditionalBuyableObject<IShopItem> item : other.conditionalShopItems) {
        if (!this.conditionalShopItems.contains(item)) {
          equals = false;
          break;
        } 
      } 
      for (ConditionalBuyableObject<IShopItem> item : this.conditionalShopItems) {
        if (!other.conditionalShopItems.contains(item)) {
          equals = false;
          break;
        } 
      } 
      return equals;
    } 
    if (this.shopItems != null && other.shopItems != null) {
      boolean equals = true;
      for (IShopItem item : other.shopItems) {
        if (!this.shopItems.contains(item)) {
          equals = false;
          break;
        } 
      } 
      for (IShopItem item : this.shopItems) {
        if (!other.shopItems.contains(item)) {
          equals = false;
          break;
        } 
      } 
      return equals;
    } 
    return this.id.equals(other.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\dto\offer\ShopOffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */