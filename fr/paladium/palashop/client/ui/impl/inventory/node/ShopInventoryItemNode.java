package fr.paladium.palashop.client.ui.impl.inventory.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryElement;
import fr.paladium.palashop.client.ui.impl.inventory.node.render.InventoryElementThumbnailRendererNode;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ShopRarityConstant;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.CircleNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;

public class ShopInventoryItemNode extends Node {
  private IShopInventoryElement element;
  
  private String type;
  
  public IShopInventoryElement getElement() {
    return this.element;
  }
  
  public String getType() {
    return this.type;
  }
  
  protected ShopInventoryItemNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    enabled(node -> (this.element != null && this.type != null));
  }
  
  @NonNull
  public static ShopInventoryItemNode create(double x, double y, double width, double height) {
    return new ShopInventoryItemNode(x, y, width, height);
  }
  
  public void init(UI ui) {
    float borderRadius = (float)(getHeight() * 0.0423D);
    RectNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .color(ColorConstant.LIGHT_DARK)
      .effect((NodeEffect)RoundedNodeEffect.create(borderRadius))
      .body(rect -> {
          RectNode.create(0.0D, 0.0D, rect.getWidth(), rect.getHeight() * 0.647D).color(ColorConstant.GRAY).effect((NodeEffect)RoundedNodeEffect.create(borderRadius)).attach(rect);
          if (this.element != null)
            InventoryElementThumbnailRendererNode.create(0.0D, 0.0D, rect.getWidth(), rect.getHeight() * 0.647D).data("inventory_thumbnail", this.element).provider(this.element.getProvider()).attach(rect); 
          double titleWidth = rect.getWidth() * 0.913D;
          double titleMargin = rect.dw(2.0D) - titleWidth / 2.0D;
          double titleHeight = rect.getHeight() - rect.getHeight() * 0.647D - titleMargin * 2.0D;
          if (this.element != null) {
            ImageNode.create(aw(-titleMargin), titleMargin).resource(ShopRarityConstant.getResource(this.element.getRarity())).width(getWidth() * 0.2D).anchorX(Align.END).attach(rect);
          } else {
            CircleNode.create(aw(-titleMargin) - getWidth() * 0.2D, titleMargin, getWidth() * 0.2D).color(Color.LOADING).attach(rect);
          } 
          ((RectNode)RectNode.create(titleMargin, rect.ah(-(titleHeight + titleMargin)), titleWidth, titleHeight).color(ColorConstant.DARK).effect((NodeEffect)RoundedNodeEffect.create(borderRadius)).body(()).onDraw(())).attach(rect);
        }).attach(this);
  }
  
  @NonNull
  public final ShopInventoryItemNode element(@NonNull IShopInventoryElement element, @NonNull String type) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    this.element = element;
    this.type = type;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\inventory\node\ShopInventoryItemNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */