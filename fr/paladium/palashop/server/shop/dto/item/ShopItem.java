package fr.paladium.palashop.server.shop.dto.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.paladium.palashop.common.provider.dto.IShopProvider;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import lombok.NonNull;

public class ShopItem implements IShopItem {
  @JsonIgnore
  private transient IShopProvider<? extends IShopItem> providerInstance;
  
  private final String parent;
  
  private final String template;
  
  private final String id;
  
  private final String provider;
  
  private final String name;
  
  private final String thumbnail;
  
  private final String description;
  
  private final ShopRarity rarity;
  
  private final Long price;
  
  private final Float discount;
  
  private final Long subscription;
  
  private final String[] conditions;
  
  private final Boolean uniquePurchase;
  
  private final Boolean available;
  
  private final Boolean buyable;
  
  private final ExecutionType executionType;
  
  private final String[] executions;
  
  private final Integer dailyWeight;
  
  private final Map<String, Object> additionalData;
  
  public String toString() {
    return "ShopItem(providerInstance=" + getProviderInstance() + ", parent=" + getParent() + ", template=" + getTemplate() + ", id=" + getId() + ", provider=" + getProvider() + ", name=" + getName() + ", thumbnail=" + getThumbnail() + ", description=" + getDescription() + ", rarity=" + getRarity() + ", price=" + getPrice() + ", discount=" + getDiscount() + ", subscription=" + getSubscription() + ", conditions=" + Arrays.deepToString((Object[])getConditions()) + ", uniquePurchase=" + getUniquePurchase() + ", available=" + getAvailable() + ", buyable=" + getBuyable() + ", executionType=" + getExecutionType() + ", executions=" + Arrays.deepToString((Object[])getExecutions()) + ", dailyWeight=" + getDailyWeight() + ", additionalData=" + getAdditionalData() + ")";
  }
  
  public ShopItem(IShopProvider<? extends IShopItem> providerInstance, String parent, String template, String id, String provider, String name, String thumbnail, String description, ShopRarity rarity, Long price, Float discount, Long subscription, String[] conditions, Boolean uniquePurchase, Boolean available, Boolean buyable, ExecutionType executionType, String[] executions, Integer dailyWeight, Map<String, Object> additionalData) {
    this.providerInstance = providerInstance;
    this.parent = parent;
    this.template = template;
    this.id = id;
    this.provider = provider;
    this.name = name;
    this.thumbnail = thumbnail;
    this.description = description;
    this.rarity = rarity;
    this.price = price;
    this.discount = discount;
    this.subscription = subscription;
    this.conditions = conditions;
    this.uniquePurchase = uniquePurchase;
    this.available = available;
    this.buyable = buyable;
    this.executionType = executionType;
    this.executions = executions;
    this.dailyWeight = dailyWeight;
    this.additionalData = additionalData;
  }
  
  public IShopProvider<? extends IShopItem> getProviderInstance() {
    return this.providerInstance;
  }
  
  public String getParent() {
    return this.parent;
  }
  
  public String getTemplate() {
    return this.template;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getProvider() {
    return this.provider;
  }
  
  public ShopRarity getRarity() {
    return this.rarity;
  }
  
  public Long getPrice() {
    return this.price;
  }
  
  public Float getDiscount() {
    return this.discount;
  }
  
  public Long getSubscription() {
    return this.subscription;
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
  
  public Boolean getBuyable() {
    return this.buyable;
  }
  
  public ExecutionType getExecutionType() {
    return this.executionType;
  }
  
  public String[] getExecutions() {
    return this.executions;
  }
  
  public Integer getDailyWeight() {
    return this.dailyWeight;
  }
  
  public Map<String, Object> getAdditionalData() {
    return this.additionalData;
  }
  
  public ShopItem(IShopProvider<? extends IShopItem> providerInstance, String parent, String template, String id, String provider, String name, String thumbnail, String description, ShopRarity rarity, Long price, Long subscription, String[] conditions, Boolean uniquePurchase, Boolean available, Boolean buyable, ExecutionType executionType, String[] executions, Integer dailyWeight, Map<String, Object> additionalData) {
    this(providerInstance, parent, template, id, provider, name, thumbnail, description, rarity, price, Float.valueOf(0.0F), subscription, conditions, uniquePurchase, available, buyable, executionType, executions, dailyWeight, additionalData);
  }
  
  public void setProviderInstance(@NonNull IShopProvider<? extends IShopItem> providerInstance) {
    if (providerInstance == null)
      throw new NullPointerException("providerInstance is marked non-null but is null"); 
    this.providerInstance = providerInstance;
  }
  
  @NonNull
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
  
  public boolean isSubscription() {
    return (this.subscription.longValue() > 0L);
  }
  
  public Boolean isUniquePurchase() {
    return this.uniquePurchase;
  }
  
  public Boolean isAvailable() {
    return this.available;
  }
  
  public Boolean isBuyable() {
    return this.buyable;
  }
  
  public boolean isDaily() {
    return (this.dailyWeight.intValue() > 0);
  }
  
  public boolean hasAdditionalData(@NonNull String key) {
    if (key == null)
      throw new NullPointerException("key is marked non-null but is null"); 
    return this.additionalData.containsKey(key);
  }
  
  public Object getAdditionalData(@NonNull String key) {
    if (key == null)
      throw new NullPointerException("key is marked non-null but is null"); 
    return this.additionalData.get(key);
  }
  
  public boolean equals(Object o) {
    if (this == o)
      return true; 
    if (!(o instanceof ShopItem))
      return false; 
    ShopItem shopItem = (ShopItem)o;
    return (Objects.equals(this.id, shopItem.id) && Objects.equals(this.provider, shopItem.provider));
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.id, this.provider });
  }
  
  public enum ExecutionType {
    COMMAND, ITEM;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\dto\item\ShopItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */