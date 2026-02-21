package fr.paladium.palarpg.module.profile.client.ui.kit.skilltree.container;

import fr.paladium.palarpg.module.profile.common.skilltree.RPGSkillTreePosition;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import lombok.NonNull;
import org.lwjgl.opengl.GL11;

class null implements NodeRenderCallback<TextNode> {
  @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
  public void pre(@NonNull TextNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (skillTreePosition == RPGSkillTreePosition.LEFT || skillTreePosition == RPGSkillTreePosition.RIGHT) {
      GL11.glTranslated(node.getX() + node.dw(2.0D), node.getY() + node.dh(2.0D), 0.0D);
      GL11.glRotated((skillTreePosition == RPGSkillTreePosition.LEFT) ? 90.0D : -90.0D, 0.0D, 0.0D, 1.0D);
      GL11.glTranslated(-(node.getX() + node.dw(2.0D)), -(node.getY() + node.dh(2.0D)), 0.0D);
    } 
  }
  
  public void apply(@NonNull TextNode node, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\clien\\ui\kit\skilltree\container\RPGProfileSkillTreeMapContainer$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */