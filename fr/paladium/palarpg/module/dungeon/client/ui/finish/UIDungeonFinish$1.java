package fr.paladium.palarpg.module.dungeon.client.ui.finish;

import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import org.lwjgl.opengl.GL11;

class null implements NodeRenderCallback<Node> {
  public void apply(Node node, double mouseX, double mouseY, float partialTicks) {}
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
  public void pre(Node node, InternalContext context, double mouseX, double mouseY, float partialTicks) {
    GL11.glPushMatrix();
    GL11.glTranslated(node.ax(node.dw(2.0D)), node.ay(node.dh(2.0D)), 0.0D);
    GL11.glRotated(-45.0D * (1.0D - Math.max(0.0F, Math.min(1.0F, pathAnimator.getValue() - index))), 0.0D, 0.0D, 1.0D);
    GL11.glScaled(Math.min(1.0F, Math.max(0.0F, pathAnimator.getValue() - index)), Math.min(1.0F, Math.max(0.0F, pathAnimator.getValue() - index)), 1.0D);
    GL11.glTranslated(-node.ax(node.dw(2.0D)), -node.ay(node.dh(2.0D)), 0.0D);
  }
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
  public void post(Node node, InternalContext context, double mouseX, double mouseY, float partialTicks) {
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\finish\UIDungeonFinish$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */