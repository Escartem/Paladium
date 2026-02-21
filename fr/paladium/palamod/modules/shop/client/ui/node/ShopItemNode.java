package fr.paladium.palamod.modules.shop.client.ui.node;

import fr.paladium.palamod.modules.shop.data.ShopItem;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class ShopItemNode extends Node {
  private ShopItem shopItem = null;
  
  private static final Resource INTERROG = Resource.of(new ResourceLocation("palamod", "textures/gui/shop/rework/interrog.png"));
  
  protected ShopItemNode(double x, double y, double size) {
    super(x, y, size, size);
  }
  
  public void draw(double itemStack, double mouseY, float ticks) {
    if (this.shopItem == null) {
      DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), INTERROG);
      return;
    } 
    DrawUtils.ITEM.drawItem(getX(), getY(), getWidth(), this.shopItem.toItemStack());
  }
  
  public static ShopItemNode create(double x, double y, double size) {
    return new ShopItemNode(x, y, size);
  }
  
  public ShopItemNode itemOrInterrog(@Nullable ShopItem itemStack) {
    this.shopItem = itemStack;
    return this;
  }
  
  @Nullable
  public ShopItem getItemStack() {
    return this.shopItem;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\clien\\ui\node\ShopItemNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */