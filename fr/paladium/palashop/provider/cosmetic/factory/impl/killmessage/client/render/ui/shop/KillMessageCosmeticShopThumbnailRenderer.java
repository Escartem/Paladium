package fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.render.ui.shop;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.KillMessageCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.dto.KillMessageCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.server.dto.shop.CosmeticShopItem;
import fr.paladium.zephyrui.lib.ui.node.Node;
import java.util.Optional;
import lombok.NonNull;

public class KillMessageCosmeticShopThumbnailRenderer implements ShopElementRenderer<CosmeticShopItem> {
  @SideOnly(Side.CLIENT)
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
    if (!optionalCosmetic.isPresent() || !(optionalCosmetic.get() instanceof KillMessageCosmeticClient))
      return; 
    KillMessageCosmeticClient cosmetic = (KillMessageCosmeticClient)optionalCosmetic.get();
    ShopElementRenderer<ICosmeticClient> renderer = ((CosmeticFactory)optionalFactory.get()).getRenderer("cosmetic_thumbnail", cosmetic);
    if (renderer == null || !renderer.getElementType().isAssignableFrom(cosmetic.getClass()) || !renderer.isValid(cosmetic))
      return; 
    renderer.render(cosmetic, container);
  }
  
  public boolean isValid(@NonNull CosmeticShopItem element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    return (element.getCosmeticFactory() != null && element.getCosmeticFactory().equals(KillMessageCosmeticFactory.ID));
  }
  
  @NonNull
  public Class<CosmeticShopItem> getElementType() {
    return CosmeticShopItem.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killmessage\client\rende\\ui\shop\KillMessageCosmeticShopThumbnailRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */