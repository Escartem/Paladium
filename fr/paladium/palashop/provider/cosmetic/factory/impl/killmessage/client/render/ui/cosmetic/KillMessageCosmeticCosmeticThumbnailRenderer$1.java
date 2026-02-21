package fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.render.ui.cosmetic;

import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import lombok.NonNull;
import org.lwjgl.opengl.GL11;

class null implements NodeRenderCallback<RectNode> {
  public void apply(RectNode node, double mouseX, double mouseY, float partialTicks) {}
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
  public void pre(@NonNull RectNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    UI ui = container.getUi();
    int finalWidth = (int)ui.getAbsoluteWidth(container.getWidth());
    int finalHeight = (int)ui.getAbsoluteHeight(container.getHeight());
    int x = (int)ui.getAbsoluteX(container.getAbsoluteX());
    int y = (int)(ui.getHeight() - ui.getAbsoluteY(container.getAbsoluteY()) + finalHeight);
    GL11.glPushAttrib(524288);
    GL11.glEnable(3089);
    GL11.glScissor(x, y, finalWidth + 1, finalHeight);
  }
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
  public void post(@NonNull RectNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    GL11.glDisable(3089);
    GL11.glPopAttrib();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killmessage\client\rende\\ui\cosmetic\KillMessageCosmeticCosmeticThumbnailRenderer$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */