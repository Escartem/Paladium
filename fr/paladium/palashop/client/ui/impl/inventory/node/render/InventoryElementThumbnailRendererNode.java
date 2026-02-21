package fr.paladium.palashop.client.ui.impl.inventory.node.render;

import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryElement;
import fr.paladium.palashop.client.ui.kit.render.ShopElementRendererNode;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;

public class InventoryElementThumbnailRendererNode extends ShopElementRendererNode {
  protected InventoryElementThumbnailRendererNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    type("inventory_thumbnail");
    orElse(node -> {
          IShopInventoryElement element = (IShopInventoryElement)getObject();
          if (element.getThumbnail() == null)
            return; 
          ImageNode.create(dw(2.0D), 0.0D).resource(element.getThumbnail()).height(getHeight()).anchorX(Align.CENTER).attach((Node)this);
        });
  }
  
  @NonNull
  public static InventoryElementThumbnailRendererNode create(double x, double y, double width, double height) {
    return new InventoryElementThumbnailRendererNode(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\inventory\node\render\InventoryElementThumbnailRendererNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */