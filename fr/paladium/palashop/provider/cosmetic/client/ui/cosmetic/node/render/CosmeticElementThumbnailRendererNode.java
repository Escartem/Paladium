package fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.node.render;

import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryElement;
import fr.paladium.palashop.client.ui.kit.render.ShopElementRendererNode;
import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import lombok.NonNull;

public class CosmeticElementThumbnailRendererNode extends ShopElementRendererNode {
  protected CosmeticElementThumbnailRendererNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    type("cosmetic_thumbnail");
    orElse(node -> {
          ICosmeticClient cosmetic = (ICosmeticClient)getObject();
          Signal<Resource> signal = new Signal(cosmetic.getThumbnail());
          if (signal.getOrDefault() == null)
            cosmetic.onLoaded(()); 
          ((ImageNode)ImageNode.create(dw(2.0D), 0.0D).onInit(())).height(getHeight()).anchorX(Align.CENTER).watch(signal).visible(()).attach((Node)this);
        });
  }
  
  @NonNull
  public static CosmeticElementThumbnailRendererNode create(double x, double y, double width, double height) {
    return new CosmeticElementThumbnailRendererNode(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\clien\\ui\cosmetic\node\render\CosmeticElementThumbnailRendererNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */