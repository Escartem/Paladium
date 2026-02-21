package fr.paladium.palashop.provider.box.client.overlay;

import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import lombok.NonNull;
import org.lwjgl.opengl.GL11;

class null implements NodeRenderCallback<Node> {
  @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
  public void pre(@NonNull Node node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    node.x(node.getDefaultX() + container.ah(11.0D) * UIShopBoxOverlay.access$000(UIShopBoxOverlay.this).getValue());
    if (index == 0) {
      GL11.glPushMatrix();
      GL11.glTranslated(node.dw(2.0D), node.dh(2.0D), 0.0D);
      GL11.glScaled(UIShopBoxOverlay.access$100(UIShopBoxOverlay.this).getValue(), UIShopBoxOverlay.access$100(UIShopBoxOverlay.this).getValue(), 0.0D);
      GL11.glTranslated(-node.dw(2.0D), -node.dh(2.0D), 0.0D);
    } 
  }
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
  public void post(@NonNull Node node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (index == 0)
      GL11.glPopMatrix(); 
  }
  
  public void apply(@NonNull Node node, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\client\overlay\UIShopBoxOverlay$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */