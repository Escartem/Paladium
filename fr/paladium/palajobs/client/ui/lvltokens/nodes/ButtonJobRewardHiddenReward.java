package fr.paladium.palajobs.client.ui.lvltokens.nodes;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ButtonJobRewardHiddenReward extends AClickableNode {
  private static final ResourceLocation QUESTION = new ResourceLocation("palajobs", "textures/gui/tokens/question.png");
  
  private float scale = 1.0F;
  
  public ButtonJobRewardHiddenReward(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    double drawX = this.x;
    if (drawX < this.ui.width(20.0F))
      drawX = this.ui.width(20.0F); 
    if (drawX > this.ui.width(73.0F))
      drawX = this.ui.width(73.0F); 
    GL11.glPushMatrix();
    GL11.glTranslated(drawX + this.width / 2.0D, this.y + this.height / 2.0D, 0.0D);
    GL11.glScaled(this.scale, this.scale, 1.0D);
    GL11.glTranslated(-(drawX + this.width / 2.0D), -(this.y + this.height / 2.0D), 0.0D);
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, Color.decode("#1a1a1f").darker(0.3F));
    GuiUtils.drawImageTransparent(this.x + width(31), this.y + height(27), QUESTION, width(40), height(40));
    GL11.glPopMatrix();
  }
  
  public float getScale() {
    return this.scale;
  }
  
  public void setScale(float scale) {
    this.scale = scale;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\lvltokens\nodes\ButtonJobRewardHiddenReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */