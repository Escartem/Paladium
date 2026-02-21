package fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic;

import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryElement;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.function.Consumer;
import lombok.NonNull;

public interface ICosmeticClient extends ICosmetic, IShopInventoryElement {
  Resource getThumbnail();
  
  @NonNull
  <T extends IShopInventoryElement> T onReceived(@NonNull Consumer<T> paramConsumer);
  
  @NonNull
  <T extends IShopInventoryElement> T onLoaded(@NonNull Consumer<T> paramConsumer);
  
  boolean isReceived();
  
  boolean isLoaded();
  
  default String getProvider() {
    return CosmeticProvider.getInstance().getId();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\client\dto\cosmetic\ICosmeticClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */