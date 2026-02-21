package fr.paladium.palarpg.module.profile.client.ui.overlay;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.scheduler.FMLClientScheduler;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.BaseTween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.resource.dto.ResourceData;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.UIDataOverlay;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.render.UIDataOverlayRender;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@UIData(background = false)
@UIDataOverlay(active = true, render = @UIDataOverlayRender(post = true))
public class UIOverlayRPGLevelUp extends UI {
  private static final Resource GRADIENT = Resource.of(new ResourceLocation("palarpg", "textures/profile/levelup/gradient.png"));
  
  private static final TextInfo LEVEL_INFO = TextInfo.create((IFont)PaladiumFont.MINECRAFT, 46.0F, Color.WHITE);
  
  private static final TextInfo LEVELUP_INFO = TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 58.0F, Color.WHITE);
  
  private static final SoundRecord LEVEL_UP_SOUND = SoundRecord.create("palarpg", "textures/profile/levelup/sound/levelup.ogg").build();
  
  private final int level;
  
  private final BooleanSignal loadedSignal;
  
  private final TweenAnimator tweenAnimator;
  
  private final Resource levelResource;
  
  public UIOverlayRPGLevelUp(Integer level) {
    this.level = level.intValue();
    this.loadedSignal = new BooleanSignal();
    this.levelResource = getLevelBackground();
    this
      
      .tweenAnimator = TweenAnimator.create().sequence(1500.0F, 1.0F, (TweenEquation)TweenEquations.CUBIC_INOUT).push(2000.0F, 1.0F).push(1500.0F, 0.0F, (TweenEquation)TweenEquations.CUBIC_INOUT).setCallback(anim -> {
          ZUI.close(this);
          FMLClientScheduler.getInstance().add(new Runnable[] { () });
        });
  }
  
  public void init() {
    ContainerNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .onInit(container -> {
          if (!((Boolean)this.loadedSignal.getOrDefault()).booleanValue())
            return; 
          LEVEL_UP_SOUND.play();
          this.tweenAnimator.start();
          ((ImageNode)ImageNode.create(0.0D, 0.0D).resource(GRADIENT).color(Color.WHITE.copyAlpha(0.0F)).size(1920.0D, 975.0D).onDraw(())).attach(container);
          boolean isGif = this.levelResource.getDecoder() instanceof fr.paladium.zephyrui.lib.resource.dto.decoder.impl.GifResourceDecoder;
          double y = isGif ? -125.0D : 0.0D;
          double width = isGif ? 475.0D : this.levelResource.getWidth();
          double height = isGif ? 475.0D : this.levelResource.getHeight();
          ((ImageNode)ImageNode.create(960.0D, y).resource(this.levelResource).anchor(Align.CENTER, Align.END).size(width, height).body(()).onDraw(())).attach(container);
        }).watch((Signal)this.loadedSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD }).attach(this);
    GRADIENT.prepareBind();
    this.levelResource.prepareBind();
    (new Thread(() -> {
          while (!GRADIENT.isLoaded() && !this.levelResource.isLoaded()) {
            try {
              Thread.sleep(10L);
            } catch (InterruptedException e) {
              e.printStackTrace();
            } 
          } 
          FMLClientScheduler.getInstance().add(new Runnable[] { () }, );
        }"UIOverlayRPGLevelUp-loadtextures")).start();
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    this.tweenAnimator.update();
  }
  
  private Resource getLevelBackground() {
    if (this.level < 10)
      return Resource.of(new ResourceLocation("palarpg", "textures/profile/levelup/level_under_10.png")); 
    if (this.level < 20)
      return Resource.of(new ResourceLocation("palarpg", "textures/profile/levelup/level_under_20.gif")); 
    if (this.level < 30)
      return Resource.of(new ResourceLocation("palarpg", "textures/profile/levelup/level_under_30.gif")); 
    if (this.level < 40)
      return Resource.of(new ResourceLocation("palarpg", "textures/profile/levelup/level_under_40.gif")); 
    if (this.level < 50)
      return Resource.of(new ResourceLocation("palarpg", "textures/profile/levelup/level_under_50.gif")); 
    if (this.level < 60)
      return Resource.of(new ResourceLocation("palarpg", "textures/profile/levelup/level_under_60.gif")); 
    if (this.level < 70)
      return Resource.of(new ResourceLocation("palarpg", "textures/profile/levelup/level_under_70.gif")); 
    if (this.level < 80)
      return Resource.of(new ResourceLocation("palarpg", "textures/profile/levelup/level_under_80.gif")); 
    if (this.level < 90)
      return Resource.of(new ResourceLocation("palarpg", "textures/profile/levelup/level_under_90.gif")); 
    if (this.level < 100)
      return Resource.of(new ResourceLocation("palarpg", "textures/profile/levelup/level_under_100.gif")); 
    return Resource.of(new ResourceLocation("palarpg", "textures/profile/levelup/level_100.gif"));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\clien\\ui\overlay\UIOverlayRPGLevelUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */