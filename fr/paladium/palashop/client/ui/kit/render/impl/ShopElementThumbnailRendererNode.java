package fr.paladium.palashop.client.ui.kit.render.impl;

import fr.paladium.palashop.client.ui.kit.render.ShopElementRendererNode;
import fr.paladium.palashop.server.shop.dto.IBuyable;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;

public class ShopElementThumbnailRendererNode extends ShopElementRendererNode {
  protected ShopElementThumbnailRendererNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    type("shop_thumbnail");
    orElse(node -> {
          if (getObject() instanceof IBuyable && ((IBuyable)getObject()).getThumbnail() != null) {
            ImageNode imageNode = (ImageNode)ImageNode.create(node.dw(2.0D), node.dh(2.0D)).resource(Resource.of(((IBuyable)getObject()).getThumbnail())).stretch(ImageNode.StretchType.CONTAIN).width(node.getWidth()).height(node.getHeight()).anchorX(Align.CENTER).anchorY(Align.CENTER).attach((Node)node);
            if (node.getParent() instanceof fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode) {
              RoundedNodeEffect effect = (RoundedNodeEffect)node.getParent().getEffect(RoundedNodeEffect.class);
              if (effect != null)
                imageNode.effect((NodeEffect)effect); 
            } 
          } 
        });
  }
  
  @NonNull
  public static ShopElementThumbnailRendererNode create(double x, double y, double width, double height) {
    return new ShopElementThumbnailRendererNode(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\kit\render\impl\ShopElementThumbnailRendererNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */