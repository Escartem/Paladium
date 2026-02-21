package fr.paladium.palamod.modules.luckyblock.gui.luckypass.node;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.opengl.GLContext;
import fr.paladium.palaforgeutils.lib.box.BoundingBox;
import fr.paladium.palamod.modules.luckyblock.gui.luckypass.UILuckyPass;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

public class LuckyPassEventNode extends AClickableNode {
  private final LuckyType type;
  
  private final ResourceLocation texture;
  
  private final boolean linearInterpolation;
  
  private BoundingBox scissor;
  
  public LuckyPassEventNode(double x, double y, double size, ResourceLocation texture, boolean linearInterpolation) {
    super(x, y, size, size);
    this.texture = texture;
    this.type = null;
    this.linearInterpolation = linearInterpolation;
    this.scissor = BoundingBox.create(0.0D, 0.0D, 0.0D, 0.0D);
  }
  
  public LuckyPassEventNode(double x, double y, double size, LuckyType type, boolean linearInterpolation) {
    super(x, y, size, size);
    this.texture = null;
    this.type = type;
    this.linearInterpolation = linearInterpolation;
    this.scissor = BoundingBox.create(0.0D, 0.0D, 0.0D, 0.0D);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GLContext.scissor(() -> {
          Color animatedColor = Color.WHITE;
          if (this.ui instanceof UILuckyPass) {
            UILuckyPass parent = (UILuckyPass)this.ui;
            double centerY = parent.y + parent.height(50.0F);
            double dist = Math.min(1.0D, Math.abs(this.y + height(50.0F) - centerY) / parent.height(30.0F));
            animatedColor = new Color(1.0F, 1.0F, 1.0F, (float)(1.0D - dist));
          } 
          animatedColor.bind();
          if (this.texture != null) {
            GuiUtils.drawImageTransparent(this.x, this.y, this.texture, this.width, this.height, this.linearInterpolation);
          } else {
            GuiUtils.renderScaledItemStackIntoGUI(LuckyType.Util.getIconFrom(this.type), this.x, this.y, (float)this.width / 16.0F, animatedColor);
          } 
          Color.WHITE.bind();
        }this.scissor
        
        .getMinX(), this.scissor.getMinY(), this.scissor.getMaxX() - this.scissor.getMinX(), this.scissor.getMaxY() - this.scissor.getMinY());
  }
  
  public void update(Minecraft mc, int mouseX, int mouseY) {
    double lastY = this.y;
    super.update(mc, mouseX, mouseY);
    if (this.ui instanceof UILuckyPass) {
      UILuckyPass parent = (UILuckyPass)this.ui;
      double centerY = parent.y + parent.height(50.0F);
      if (this.y >= centerY && lastY < centerY)
        Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F)); 
    } 
  }
  
  public LuckyPassEventNode setScissor(BoundingBox scissor) {
    this.scissor = scissor;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\luckypass\node\LuckyPassEventNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */