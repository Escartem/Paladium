package fr.paladium.palashop.provider.box.client.overlay;

import fr.paladium.palashop.provider.box.common.dto.box.BoxReward;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.utils.bezier.Bezier;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import javax.vecmath.Vector2d;
import lombok.NonNull;
import org.lwjgl.opengl.GL11;

class null implements NodeRenderCallback<Node> {
  @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
  public void pre(@NonNull Node node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    GL11.glPushMatrix();
    GL11.glTranslated(boxItemNode.getAbsoluteX(), boxItemNode.getAbsoluteY(), 0.0D);
    if (reward.getType() == BoxReward.Type.SHOP_ITEM) {
      if (reward.getShopItem().getRarity() == ShopRarity.LEGENDARY) {
        float value = UIShopBoxOverlay.access$200(UIShopBoxOverlay.this).getValue();
        float glowValue = UIShopBoxOverlay.access$300(UIShopBoxOverlay.this).getValue();
        if (UIShopBoxOverlay.access$400(UIShopBoxOverlay.this) == 0L)
          UIShopBoxOverlay.access$402(UIShopBoxOverlay.this, System.currentTimeMillis()); 
        long glowDuration = System.currentTimeMillis() - UIShopBoxOverlay.access$400(UIShopBoxOverlay.this);
        float additionalValue = (float)((Math.sin(6.283185307179586D * ((glowDuration + 1100L) % 3000L) / 1500.0D) + 1.0D) / 10.0D);
        double startX = boxItemNode.getDefaultX();
        double startY = boxItemNode.getDefaultY();
        double endX = 500.0D - boxItemNode.getAbsoluteX();
        double endY = 700.0D - boxItemNode.getAbsoluteY();
        double centerX = (startX + endX) / 2.0D;
        double centerY = (startY + endY) / 2.0D;
        Vector2d position = Bezier.quadratic(value, new Vector2d(startX, startY), new Vector2d(endX, endY), new Vector2d(centerX, centerY));
        GL11.glPushMatrix();
        GL11.glTranslated(position.x, position.y, 0.0D);
        GL11.glTranslated(node.dw(2.0D), node.dh(2.0D), 0.0D);
        GL11.glScaled((1.0D + additionalValue + glowValue * 0.5D) * (1.0D - value), (1.0D + additionalValue + glowValue * 0.5D) * (1.0D - value), 0.0D);
        GL11.glTranslated(-node.dw(2.0D), -node.dh(2.0D), 0.0D);
        UIShopBoxOverlay.access$500().prepareBind();
        double resourceWidth = UIShopBoxOverlay.access$500().getWidth();
        double resourceHeight = UIShopBoxOverlay.access$500().getHeight();
        double imageWidth = boxItemNode.aw(90.0D);
        double imageHeight = resourceHeight * imageWidth / resourceWidth;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, glowValue);
        DrawUtils.RESOURCE.drawImage(node.dw(2.0D) - imageWidth / 2.0D, node.dh(2.0D) - imageHeight / 2.0D, imageWidth, imageHeight, UIShopBoxOverlay.access$500());
        DrawUtils.RESOURCE.drawImage(node.dw(2.0D) - imageWidth / 2.0D, node.dh(2.0D) - imageHeight / 2.0D, imageWidth, imageHeight, UIShopBoxOverlay.access$500());
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      } else {
        float value = UIShopBoxOverlay.access$200(UIShopBoxOverlay.this).getValue();
        double startX = boxItemNode.getDefaultX();
        double startY = boxItemNode.getDefaultY();
        double endX = 500.0D - boxItemNode.getAbsoluteX();
        double endY = 700.0D - boxItemNode.getAbsoluteY();
        double centerX = (startX + endX) / 2.0D;
        double centerY = (startY + endY) / 2.0D;
        Vector2d position = Bezier.quadratic(value, new Vector2d(startX, startY), new Vector2d(endX, endY), new Vector2d(centerX, centerY));
        GL11.glPushMatrix();
        GL11.glTranslated(position.x, position.y, 0.0D);
        GL11.glTranslated(node.dw(2.0D), node.dh(2.0D), 0.0D);
        GL11.glScaled(1.0D - value, 1.0D - value, 0.0D);
        GL11.glTranslated(-node.dw(2.0D), -node.dh(2.0D), 0.0D);
      } 
    } else {
      float value = UIShopBoxOverlay.access$600(UIShopBoxOverlay.this).getValue();
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      if (value < 1.0F) {
        GL11.glTranslated(node.dw(2.0D), node.dh(2.0D), 0.0D);
        GL11.glScaled(1.0D + value * 0.5D, 1.0D + value * 0.5D, 0.0D);
        GL11.glTranslated(-node.dw(2.0D), -node.dh(2.0D), 0.0D);
      } else if (value >= 1.0F && value <= 2.0F) {
        GL11.glTranslated(node.dw(2.0D), node.dh(2.0D), 0.0D);
        GL11.glScaled(1.5D, 1.5D, 0.0D);
        GL11.glRotated((value - 1.0F) * 360.0D * 5.0D, 0.0D, 1.0D, 0.0D);
        GL11.glTranslated(-node.dw(2.0D), -node.dh(2.0D), 0.0D);
      } else if (value > 2.0F) {
        GL11.glTranslated(node.dw(2.0D), node.dh(2.0D), 0.0D);
        GL11.glScaled(1.0D + (3.0F - value) * 0.5D, 1.0D + (3.0F - value) * 0.5D, 0.0D);
        GL11.glTranslated(-node.dw(2.0D), -node.dh(2.0D), 0.0D);
      } 
    } 
  }
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
  public void post(@NonNull Node node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (reward.getType() == BoxReward.Type.SHOP_ITEM) {
      GL11.glPopMatrix();
    } else {
      GL11.glEnable(2884);
      GL11.glPopMatrix();
    } 
    GL11.glPopMatrix();
  }
  
  public void apply(@NonNull Node node, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\client\overlay\UIShopBoxOverlay$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */