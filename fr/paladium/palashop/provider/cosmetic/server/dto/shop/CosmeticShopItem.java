package fr.paladium.palashop.provider.cosmetic.server.dto.shop;

import fr.paladium.palashop.common.provider.dto.IShopProvider;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.item.ShopItem;
import java.util.Map;
import lombok.NonNull;

public class CosmeticShopItem extends ShopItem {
  private final String cosmeticId;
  
  private final String cosmeticFactory;
  
  private final Map<String, Object> renderData;
  
  private String mappedName;
  
  public void setMappedName(String mappedName) {
    this.mappedName = mappedName;
  }
  
  public String toString() {
    return "CosmeticShopItem(cosmeticId=" + getCosmeticId() + ", cosmeticFactory=" + getCosmeticFactory() + ", renderData=" + getRenderData() + ", mappedName=" + getMappedName() + ")";
  }
  
  public String getCosmeticId() {
    return this.cosmeticId;
  }
  
  public String getCosmeticFactory() {
    return this.cosmeticFactory;
  }
  
  public Map<String, Object> getRenderData() {
    return this.renderData;
  }
  
  public String getMappedName() {
    return this.mappedName;
  }
  
  public CosmeticShopItem(IShopProvider<? extends IShopItem> providerInstance, String parent, String template, String id, String provider, String name, String thumbnail, String description, ShopRarity rarity, Long price, Float discount, Long subscription, String[] conditions, Boolean uniquePurchase, Boolean available, Boolean buyable, ShopItem.ExecutionType executionType, String[] executions, Integer dailyWeight, Map<String, Object> additionalData, String cosmeticId, String cosmeticFactory, Map<String, Object> renderData) {
    super(providerInstance, parent, template, id, provider, name, thumbnail, description, rarity, price, discount, subscription, conditions, uniquePurchase, available, buyable, executionType, executions, dailyWeight, additionalData);
    this.cosmeticId = cosmeticId;
    this.cosmeticFactory = cosmeticFactory;
    this.renderData = renderData;
  }
  
  @NonNull
  public String getName() {
    return (this.mappedName != null) ? this.mappedName : super.getName();
  }
  
  public Object getRenderData(String key, Object defaultValue) {
    return this.renderData.getOrDefault(key, defaultValue);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\server\dto\shop\CosmeticShopItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */