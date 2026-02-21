package fr.paladium.palashop.provider.cosmetic.client.render.ui.inventory;

import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.node.render.CosmeticElementThumbnailRendererNode;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.util.Optional;
import lombok.NonNull;

public class CosmeticInventoryThumbnailRenderer implements ShopElementRenderer<ICosmeticClient> {
  public void render(@NonNull ICosmeticClient element, @NonNull Node container) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    if (container == null)
      throw new NullPointerException("container is marked non-null but is null"); 
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.me();
    if (element.getFactory() == null || element.getId() == null || cosmeticPlayer == null)
      return; 
    CosmeticElementThumbnailRendererNode.create(0.0D, 0.0D, container.getWidth(), container.getHeight())
      .provider(CosmeticProvider.getInstance().getId())
      .object(element)
      .attach(container);
    ((RectNode)RectNode.create(13.0D, 13.0D, 30.0D, 30.0D)
      .color(ColorConstant.PRIMARY)
      .effect((NodeEffect)RoundedNodeEffect.create(7.0F))
      .body(equippedRect -> ImageNode.create(equippedRect.dw(2.0D), equippedRect.dh(2.0D)).resource(ResourceConstant.ICON_SUCCESS).width(equippedRect.dw(2.0D)).anchor(Align.CENTER).attach((Node)equippedRect)))
      
      .visible(node -> {
          Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = cosmeticPlayer.getOutfit().get(element.getFactory().getId());
          return (optionalEquippedCosmetic.isPresent() && ((CosmeticPlayer.EquippedCosmetic)optionalEquippedCosmetic.get()).has((ICosmetic)element));
        }).attach(container);
  }
  
  @NonNull
  public Class<ICosmeticClient> getElementType() {
    return ICosmeticClient.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\client\rende\\ui\inventory\CosmeticInventoryThumbnailRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */