package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.render.ui.cosmetic;

import fr.paladium.palashop.common.utils.player.FakeEntityPlayerMP;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.ui.node.impl.design.entity.EntityNode;
import fr.paladium.zephyrui.lib.ui.node.property.position.PositionProperty;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

class null implements NodeRenderCallback<EntityNode> {
  public void apply(EntityNode node, double mouseX, double mouseY, float partialTicks) {}
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
  public void pre(@NonNull EntityNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (node.getParent().getParent().getPosition() == PositionProperty.RELATIVE) {
      node.getUi().startMask(node.getX(), node.getY(), node.getWidth(), node.getHeight());
    } else {
      node.entity((EntityLivingBase)new FakeEntityPlayerMP((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
    } 
  }
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
  public void post(@NonNull EntityNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (node.getParent().getParent().getPosition() == PositionProperty.RELATIVE)
      node.getUi().stopMask(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\client\rende\\ui\cosmetic\EmoteCosmeticCosmeticThumbnailRenderer$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */