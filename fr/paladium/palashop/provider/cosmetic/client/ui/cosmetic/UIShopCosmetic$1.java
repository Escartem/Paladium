package fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic;

import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.property.position.PositionProperty;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import lombok.NonNull;
import org.lwjgl.opengl.GL11;

class null implements NodeRenderCallback<RectNode> {
  public void apply(@NonNull RectNode node, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
  }
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
  public void pre(@NonNull RectNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (node.getPosition() != PositionProperty.ABSOLUTE)
      return; 
    GL11.glPushMatrix();
    GL11.glTranslated(node.getX() + node.dw(2.0D), node.getY() + node.dh(2.0D), 0.0D);
    GL11.glScaled(0.6D, 0.6D, 1.0D);
    GL11.glTranslated(-(node.getX() + node.dw(2.0D)), -(node.getY() + node.dh(2.0D)), 0.0D);
  }
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
  public void post(@NonNull RectNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (node.getPosition() != PositionProperty.ABSOLUTE)
      return; 
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\clien\\ui\cosmetic\UIShopCosmetic$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */