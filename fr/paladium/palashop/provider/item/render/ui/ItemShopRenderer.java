package fr.paladium.palashop.provider.item.render.ui;

import fr.paladium.lib.apollon.utils.opengl.GLContext;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.provider.item.dto.ItemShopItem;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeDrawCallback;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ItemShopRenderer implements ShopElementRenderer<ItemShopItem> {
  private static final RenderItem itemRenderer = new RenderItem();
  
  public void render(@NonNull ItemShopItem element, @NonNull Node container) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    if (container == null)
      throw new NullPointerException("container is marked non-null but is null"); 
    ItemStack item = element.getItemStack();
    if (item == null)
      return; 
    final double size = container.getHeight() * 0.7D;
    final double x = container.dw(2.0D) - size / 2.0D;
    final double y = container.dh(2.0D) - size / 2.0D;
    ((ItemNode)ItemNode.create(x, y, size)
      .item(item)
      .onDraw(new NodeDrawCallback<ItemNode>() {
          @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
          public void pre(@NonNull ItemNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
            if (node == null)
              throw new NullPointerException("node is marked non-null but is null"); 
            if (context == null)
              throw new NullPointerException("context is marked non-null but is null"); 
            context.cancel(() -> apply(node, mouseX, mouseY, partialTicks));
          }
          
          @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
          public void post(@NonNull ItemNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
            if (node == null)
              throw new NullPointerException("node is marked non-null but is null"); 
            if (context == null)
              throw new NullPointerException("context is marked non-null but is null"); 
          }
          
          public void apply(@NonNull ItemNode node, double mouseX, double mouseY, float partialTicks) {
            if (node == null)
              throw new NullPointerException("node is marked non-null but is null"); 
            Minecraft mc = Minecraft.func_71410_x();
            if (mc.field_71466_p == null || mc.func_110434_K() == null || ItemShopRenderer.itemRenderer == null || node.getItem() == null || node.getItem().func_77973_b() == null)
              return; 
            Color.reset();
            GL11.glPushAttrib(1048575);
            GLContext.matrix(() -> {
                  GL11.glTranslated(x, y, 0.0D);
                  GL11.glScaled(size / 16.0D, size / 16.0D, 1.0D);
                  Color.reset();
                  GL11.glEnable(32826);
                  GL11.glPushMatrix();
                  GL11.glTexParameteri(3553, 10241, 9728);
                  GL11.glTexParameteri(3553, 10240, 9728);
                  ItemShopRenderer.itemRenderer.field_77023_b = -49.0F;
                  ItemShopRenderer.itemRenderer.field_77024_a = true;
                  ItemShopRenderer.itemRenderer.func_82406_b(mc.field_71466_p, mc.func_110434_K(), node.getItem(), 0, 0);
                  if (node.isStackSize())
                    ItemShopRenderer.itemRenderer.func_77021_b(mc.field_71466_p, mc.func_110434_K(), node.getItem(), 0, 0); 
                  GL11.glPopMatrix();
                  GL11.glDisable(32826);
                });
            GL11.glPopAttrib();
            Color.reset();
          }
        })).attach(container);
  }
  
  @NonNull
  public Class<ItemShopItem> getElementType() {
    return ItemShopItem.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\item\rende\\ui\ItemShopRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */