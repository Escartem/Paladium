package fr.paladium.palashop.server.shop.dto.item;

import fr.paladium.palashop.common.provider.dto.IShopProvider;
import fr.paladium.palashop.server.shop.dto.IBuyable;
import lombok.NonNull;

public interface IShopItem extends IBuyable {
  IShopProvider<? extends IShopItem> getProviderInstance();
  
  void setProviderInstance(@NonNull IShopProvider<? extends IShopItem> paramIShopProvider);
  
  String getParent();
  
  String getTemplate();
  
  String getProvider();
  
  Float getDiscount();
  
  Boolean isBuyable();
  
  Integer getDailyWeight();
  
  boolean isDaily();
  
  ShopItem.ExecutionType getExecutionType();
  
  String[] getExecutions();
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\dto\item\IShopItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */