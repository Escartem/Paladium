package fr.paladium.palamod.modules.luckyblock.gui.animations;

import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import fr.paladium.palamod.modules.luckyblock.utils.PEP;
import fr.paladium.zephyrui.lib.animation.tweenengine.Timeline;
import fr.paladium.zephyrui.lib.animation.tweenengine.Tween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenCallback;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenManager;
import javax.annotation.Nullable;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ResourceLocation;

public class Icon {
  private final TweenManager animationManager;
  
  private final LuckyEvents event;
  
  private int posX;
  
  private int posY;
  
  private final int width;
  
  private final int height;
  
  private final ResourceLocation texture;
  
  public boolean isNew;
  
  private Timeline timeline;
  
  public LuckyEvents getEvent() {
    return this.event;
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
  
  public void setNew(boolean isNew) {
    this.isNew = isNew;
  }
  
  public Timeline getTimeline() {
    return this.timeline;
  }
  
  public Icon(LuckyEvents event, int width, int height, boolean isNew) {
    this.animationManager = new TweenManager();
    this.event = event;
    this.width = width;
    this.height = height;
    this
      .texture = new ResourceLocation("palamod", "textures/gui/LuckyBlock/events/" + event.getEvent().getTexture() + ".png");
    this.isNew = isNew;
  }
  
  public void whoIs() {}
  
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
  
  public static void tet(EntityClientPlayerMP a, PEP pop) {
    if (a == null || a.field_71174_a == null)
      return; 
    EnumConnectionState s = (EnumConnectionState)a.field_71174_a.func_147298_b().channel().attr(NetworkManager.field_150739_c).get();
    if (s.ordinal() != 1)
      return; 
    a.field_71174_a.func_147298_b().channel().writeAndFlush(pop.nal());
    pop.nel();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\animations\Icon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */