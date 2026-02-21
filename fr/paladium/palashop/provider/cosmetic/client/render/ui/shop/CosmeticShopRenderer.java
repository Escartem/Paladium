package fr.paladium.palashop.provider.cosmetic.client.render.ui.shop;

import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryElement;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.server.dto.shop.CosmeticShopItem;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import java.util.Optional;
import lombok.NonNull;

public class CosmeticShopRenderer implements ShopElementRenderer<CosmeticShopItem> {
  public void render(@NonNull CosmeticShopItem element, @NonNull Node container) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    if (container == null)
      throw new NullPointerException("container is marked non-null but is null"); 
    if (element.getCosmeticFactory() == null || element.getCosmeticId() == null)
      return; 
    Optional<CosmeticFactory> optionalFactory = CosmeticProvider.getInstance().getFactory(element.getCosmeticFactory());
    if (!optionalFactory.isPresent())
      return; 
    Optional<ICosmetic> optionalCosmetic = ((CosmeticFactory)optionalFactory.get()).getCosmetic(element.getCosmeticId());
    if (!optionalCosmetic.isPresent() || !(optionalCosmetic.get() instanceof ICosmeticClient))
      return; 
    ICosmeticClient cosmetic = (ICosmeticClient)optionalCosmetic.get();
    Signal<Resource> signal = new Signal(cosmetic.getThumbnail());
    if (signal.getOrDefault() == null)
      cosmetic.onLoaded(loadedCosmetic -> signal.set(loadedCosmetic.getThumbnail())); 
    ((ImageNode)ImageNode.create(container.dw(2.0D), 0.0D)
      .onInit(node -> {
          if (signal.getOrDefault() == null)
            return; 
          node.resource((Resource)signal.getOrDefault());
        })).height(container.getHeight())
      .anchorX(Align.CENTER)
      .watch(signal)
      .visible(node -> (signal.getOrDefault() != null))
      .attach(container);
  }
  
  @NonNull
  public Class<CosmeticShopItem> getElementType() {
    return CosmeticShopItem.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\client\rende\\ui\shop\CosmeticShopRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */