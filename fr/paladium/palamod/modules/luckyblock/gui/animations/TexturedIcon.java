package fr.paladium.palamod.modules.luckyblock.gui.animations;

import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.zephyrui.lib.animation.tweenengine.Timeline;
import fr.paladium.zephyrui.lib.animation.tweenengine.Tween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenCallback;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenManager;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class TexturedIcon {
  private final TweenManager animationManager;
  
  private final ResourceLocation texture;
  
  private int posX;
  
  private int posY;
  
  private final int width;
  
  private final int height;
  
  private Timeline timeline;
  
  public ResourceLocation getTexture() {
    return this.texture;
  }
  
  public int getPosX() {
    return this.posX;
  }
  
  public void setPosX(int posX) {
    this.posX = posX;
  }
  
  public int getPosY() {
    return this.posY;
  }
  
  public void setPosY(int posY) {
    this.posY = posY;
  }
  
  public int getWidth() {
    return this.width;
  }
  
  public int getHeight() {
    return this.height;
  }
  
  public Timeline getTimeline() {
    return this.timeline;
  }
  
  public TexturedIcon(ResourceLocation texture, int width, int height) {
    this.animationManager = new TweenManager();
    this.texture = texture;
    this.width = width;
    this.height = height;
  }
  
  public void update() {
    this.animationManager.update(1.0F);
  }
  
  public void draw() {
    GuiUtils.drawImageTransparent(this.posX, this.posY, this.texture, this.width, this.height);
  }
  
  public void move(int slide, @Nullable TweenCallback callback) {
    this
      
      .timeline = Timeline.createSequence().beginSequence().push(Tween.to(this, 2, 400.0F).target((getPosY() + slide))).end();
    this.timeline.setCallback(callback);
    this.timeline.start(this.animationManager);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\animations\TexturedIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */