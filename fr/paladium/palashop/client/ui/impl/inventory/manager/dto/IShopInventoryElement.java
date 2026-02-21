package fr.paladium.palashop.client.ui.impl.inventory.manager.dto;

import fr.paladium.palashop.server.shop.dto.ShopRarity;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.function.Consumer;
import lombok.NonNull;

public interface IShopInventoryElement {
  String getId();
  
  String getName();
  
  Resource getThumbnail();
  
  String getProvider();
  
  ShopRarity getRarity();
  
  @NonNull
  <T extends IShopInventoryElement> T onReceived(@NonNull Consumer<T> paramConsumer);
  
  @NonNull
  <T extends IShopInventoryElement> T onLoaded(@NonNull Consumer<T> paramConsumer);
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\inventory\manager\dto\IShopInventoryElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */