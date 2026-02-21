package fr.paladium.palashop.provider.box.client.render.ui;

import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.provider.box.common.dto.shop.BoxShopItem;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import lombok.NonNull;
import net.minecraft.item.ItemStack;

public class BoxShopRenderer implements ShopElementRenderer<BoxShopItem> {
  public void render(@NonNull BoxShopItem element, @NonNull Node container) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    if (container == null)
      throw new NullPointerException("container is marked non-null but is null"); 
    ItemStack item = element.getItemStack();
    if (item == null)
      return; 
    double size = container.getHeight() * 0.7D;
    double x = container.dw(2.0D) - size / 2.0D;
    double y = container.dh(2.0D) - size / 2.0D;
    ItemNode.create(x, y, size)
      .item(item)
      .attach(container);
  }
  
  @NonNull
  public Class<BoxShopItem> getElementType() {
    return BoxShopItem.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\client\rende\\ui\BoxShopRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */