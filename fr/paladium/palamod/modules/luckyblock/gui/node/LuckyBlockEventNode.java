package fr.paladium.palamod.modules.luckyblock.gui.node;

import fr.paladium.lib.apollon.animation.TweenAnimator;
import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.opengl.GLContext;
import fr.paladium.palaforgeutils.lib.box.BoundingBox;
import fr.paladium.palamod.modules.luckyblock.gui.UILuckyBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

public class LuckyBlockEventNode extends AClickableNode {
  private final ResourceLocation texture;
  
  private final boolean linearInterpolation;
  
  private final TweenAnimator animator;
  
  private BoundingBox scissor;
  
  public ResourceLocation getTexture() {
    return this.texture;
  }
  
  public boolean isLinearInterpolation() {
    return this.linearInterpolation;
  }
  
  public TweenAnimator getAnimator() {
    return this.animator;
  }
  
  public BoundingBox getScissor() {
    return this.scissor;
  }
  
  public LuckyBlockEventNode(double x, double y, double size, ResourceLocation texture, boolean linearInterpolation) {
    super(x, y, size, size);
    this.texture = texture;
    this.linearInterpolation = linearInterpolation;
    this.animator = TweenAnimator.create((float)y);
    this.scissor = BoundingBox.create(0.0D, 0.0D, 0.0D, 0.0D);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    this.animator.update();
    this.y = this.animator.getValue();
    GLContext.scissor(() -> {
          if (this.ui instanceof UILuckyBlock) {
            UILuckyBlock parent = (UILuckyBlock)this.ui;
            double centerY = parent.y + parent.height(22.5F) + parent.height(22.6385F);
            double dist = Math.min(1.0D, Math.abs(this.y + height(50.0F) - centerY) / parent.height(30.0F));
            (new Color(1.0F, 1.0F, 1.0F, (float)(1.0D - dist))).bind();
          } 
          GuiUtils.drawImageTransparent(this.x, this.y, this.texture, this.width, this.height, this.linearInterpolation);
          Color.WHITE.bind();
        }this.scissor
        
        .getMinX(), this.scissor.getMinY(), this.scissor.getMaxX() - this.scissor.getMinX(), this.scissor.getMaxY() - this.scissor.getMinY());
  }
  
  public void update(Minecraft mc, int mouseX, int mouseY) {
    double lastY = this.y;
    super.update(mc, mouseX, mouseY);
    if (this.ui instanceof UILuckyBlock) {
      UILuckyBlock parent = (UILuckyBlock)this.ui;
      double centerY = parent.y + parent.height(22.5F) + parent.height(22.6385F);
      if (this.y >= centerY && lastY < centerY)
        Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F)); 
    } 
  }
  
  public LuckyBlockEventNode setScissor(BoundingBox scissor) {
    this.scissor = scissor;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\node\LuckyBlockEventNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */