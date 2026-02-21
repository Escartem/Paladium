package fr.paladium.palamod.modules.shop.client.ui.node;

import fr.paladium.palamod.modules.shop.data.ShopItem;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import javax.annotation.Nullable;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class ShopSlotNode extends ImageNode {
  private static final Resource SLOT = Resource.of(new ResourceLocation("palamod", "textures/gui/shop/rework/slot.png"));
  
  private static final Resource SLOT_HOVER = Resource.of(new ResourceLocation("palamod", "textures/gui/shop/rework/slot_hover.png"));
  
  private final ShopItemNode itemNode = ShopItemNode.create(0.0D, 0.0D, 0.0D);
  
  protected ShopSlotNode(double x, double y) {
    super(x, y);
    resource(SLOT, SLOT_HOVER);
    linear(false);
    hoverDuration(100L);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    body(() -> this.itemNode.attach((Node)this));
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    super.draw(mouseX, mouseY, ticks);
    double itemSize = (this.itemNode.getItemStack() != null) ? (getWidth() * 0.6D) : (getWidth() * 0.4D);
    this.itemNode
      .size(itemSize, itemSize)
      .x(dw(2.0D) - itemSize / 2.0D)
      .y(dh(2.0D) - itemSize / 2.0D);
  }
  
  public static ShopSlotNode create(double x, double y) {
    return new ShopSlotNode(x, y);
  }
  
  public ShopSlotNode item(@Nullable ShopItem item) {
    this.itemNode.itemOrInterrog(item);
    return this;
  }
  
  public ShopItem getShopItem() {
    return this.itemNode.getItemStack();
  }
  
  public static Resource getSlotHover() {
    return SLOT_HOVER;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\clien\\ui\node\ShopSlotNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */