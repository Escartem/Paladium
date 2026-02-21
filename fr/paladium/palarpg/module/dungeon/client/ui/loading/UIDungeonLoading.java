package fr.paladium.palarpg.module.dungeon.client.ui.loading;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.scheduler.FMLClientScheduler;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundElementNode;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundNode;
import fr.paladium.palarpg.module.dungeon.client.ui.utils.DungeonSynchronizedUI;
import fr.paladium.palarpg.module.dungeon.common.network.start.CSPacketRPGDungeonReady;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.BaseTween;
import fr.paladium.zephyrui.lib.animation.tweenengine.Timeline;
import fr.paladium.zephyrui.lib.animation.tweenengine.Tween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.resource.ResourceBuilder;
import fr.paladium.zephyrui.lib.resource.dto.ResourceData;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import java.util.EnumMap;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

@UIData(background = false)
public class UIDungeonLoading extends DungeonSynchronizedUI {
  private static final ResourceBuilder RESOURCE_BUILDER = ResourceBuilder.create().blocking().linear();
  
  private final Map<DungeonLoadingState, Resource> resourceMap;
  
  private final TweenAnimator transitionAnimator;
  
  private final TweenAnimator stateAnimator;
  
  private final TweenAnimator loadingAnimator;
  
  private final Signal<DungeonLoadingState> stateSignal;
  
  private final boolean enoughMemory;
  
  private boolean finished;
  
  private boolean ready;
  
  public UIDungeonLoading() {
    this.resourceMap = new EnumMap<>(DungeonLoadingState.class);
    this.transitionAnimator = TweenAnimator.create(0.0F);
    this.stateAnimator = TweenAnimator.create(1.0F);
    this.loadingAnimator = TweenAnimator.create(0.0F);
    this.stateSignal = new Signal();
    this.enoughMemory = ((Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory()) / 1024L / 1024L > 500L);
    setMain(UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g));
  }
  
  public void init() {
    super.init();
    if (this.resourceMap.isEmpty())
      for (DungeonLoadingState state : DungeonLoadingState.values()) {
        Resource resurce = RESOURCE_BUILDER.of(!this.enoughMemory ? state.getPng() : state.getGif());
        resurce.prepareBind();
        this.resourceMap.put(state, resurce);
      }  
    ContainerNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .body(container -> {
          BackgroundNode.create().attach(container);
          FlexNode.horizontal(container.aw(-70.0D), 64.0D, 52.0D).margin(10.0D).align(Align.CENTER).body(()).anchorX(Align.END).attach(container);
        }).visible(node -> (this.transitionAnimator.getValue() >= 1.0F && this.transitionAnimator.getValue() <= 3.0F))
      .attach((UI)this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    this.transitionAnimator.update();
    this.stateAnimator.update();
    this.loadingAnimator.update();
    if (this.transitionAnimator.getValue() >= 1.0F && this.transitionAnimator.getValue() <= 3.0F) {
      if (this.stateSignal.getOrDefault() == null)
        setState(DungeonLoadingState.SEARCHING); 
      DrawUtils.SHAPE.drawRect(getRelativeX(0.0D), getRelativeY(0.0D), getViewportWidth(), getViewportHeight(), BackgroundNode.BACKGROUND_COLOR);
    } 
  }
  
  public void postDraw(double mouseX, double mouseY, float ticks) {
    if (this.transitionAnimator.getValue() >= 1.0F && this.transitionAnimator.getValue() <= 3.0F && this.stateSignal.isPresent()) {
      DungeonWorld dungeonWorld = DungeonWorld.getClient().get();
      DungeonLoadingState state = (DungeonLoadingState)this.stateSignal.getOrDefault();
      float f = this.stateAnimator.getValue();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, f);
      DrawUtils.RESOURCE.drawImage(0.0D, 0.0D, 1920.0D, 1080.0D, this.resourceMap.get(state));
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      Color color = Color.decode("#F6F9E8");
      DrawUtils.TEXT.drawText(70.0D, 38.0D, Text.create(state.getTitle(), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 58.0F, color)));
      DrawUtils.TEXT.drawText(70.0D, 923.0D, Text.create(this.finished ? ("en attente des joueurs " + dungeonWorld.getReady().size() + "/" + Math.max(dungeonWorld.getOnlinePlayers().size(), dungeonWorld.getReady().size())) : ("chargement" + StringUtils.repeat(".", (int)(System.currentTimeMillis() / 500L % 4L))), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 24.0F, color)));
      DrawUtils.SHAPE.drawRect(70.0D, 980.0D, 1780.0D, 47.0D, Color.decode("#323232"));
      DrawUtils.SHAPE.drawRect(70.0D, 1022.0D, 1780.0D, 5.0D, Color.decode("#323232").darker(0.4F));
      DrawUtils.SHAPE.drawRect(70.0D, 980.0D, (1780.0F * this.loadingAnimator.getValue()), 47.0D, color);
      DrawUtils.SHAPE.drawRect(70.0D, 1022.0D, (1780.0F * this.loadingAnimator.getValue()), 5.0D, color.darker(0.4F));
      if (this.finished && !this.ready && dungeonWorld.getState() == DungeonWorld.DungeonState.READY) {
        this.ready = true;
        (new CSPacketRPGDungeonReady()).send();
      } 
    } 
    float value = this.transitionAnimator.getValue();
    float opacity = (value < 1.0F) ? value : ((value < 2.0F) ? (2.0F - value) : ((value < 3.0F) ? (value - 2.0F) : (4.0F - value)));
    DrawUtils.SHAPE.drawRect(getRelativeX(0.0D), getRelativeY(0.0D), getViewportWidth(), getViewportHeight(), BackgroundNode.BACKGROUND_COLOR.copyAlpha(opacity));
  }
  
  public boolean close() {
    return false;
  }
  
  public void update() {
    if (!DungeonWorld.getClient().isPresent() || ((DungeonWorld)DungeonWorld.getClient().get()).getState() == DungeonWorld.DungeonState.WAITING) {
      ZUI.close((UI)this, true);
      (new Notification(Notification.NotificationType.ERROR, "Impossible de charger le donjon", "paladium")).send();
    } 
  }
  
  @NonNull
  private UIDungeonLoading setState(@NonNull DungeonLoadingState state) {
    if (state == null)
      throw new NullPointerException("state is marked non-null but is null"); 
    this.stateSignal.set(state);
    this.loadingAnimator.sequence((float)state.getDuration(), 0.9F / (DungeonLoadingState.values()).length * (state.ordinal() + 1), (TweenEquation)TweenEquations.SINE_INOUT);
    this.loadingAnimator.setCallback(loadingTween -> FMLClientScheduler.getInstance().add(new Runnable[] { () })).start();
    return this;
  }
  
  @NonNull
  public UIDungeonLoading start() {
    Timeline transitionTimeline = Timeline.createSequence();
    transitionTimeline.push(Tween.to(this.transitionAnimator, 0, 1000.0F).target(1.0F).ease((TweenEquation)TweenEquations.QUART_INOUT));
    transitionTimeline.push(Tween.to(this.transitionAnimator, 0, 1000.0F).target(2.0F).ease((TweenEquation)TweenEquations.QUART_INOUT));
    transitionTimeline.pushPause(20000.0F);
    this.transitionAnimator.setTimeline(transitionTimeline);
    this.transitionAnimator.start();
    return this;
  }
  
  @NonNull
  public UIDungeonLoading stop() {
    this.loadingAnimator.sequence(1000.0F, 1.0F, (TweenEquation)TweenEquations.QUART_OUT).start();
    Timeline transitionTimeline = Timeline.createSequence();
    transitionTimeline.push(Tween.to(this.transitionAnimator, 0, 1000.0F).target(3.0F).ease((TweenEquation)TweenEquations.QUART_INOUT));
    transitionTimeline.push(Tween.to(this.transitionAnimator, 0, 1000.0F).target(4.0F).ease((TweenEquation)TweenEquations.QUART_INOUT));
    this.transitionAnimator.setTimeline(transitionTimeline);
    this.transitionAnimator.setCallback(tween -> {
          ZUI.close((UI)this, true);
          for (Resource resource : this.resourceMap.values()) {
            ResourceData resourceData = resource.getResourceData();
            if (resourceData.getTextureId() != null)
              for (int id : resourceData.getTextureId())
                GL11.glDeleteTextures(id);  
            resourceData.getDecoder().clear(resourceData);
          } 
          this.resourceMap.clear();
          System.gc();
        }).start();
    return this;
  }
  
  public enum DungeonLoadingState {
    SEARCHING("Recherche du donjon", new ResourceLocation("palarpg", "textures/gui/loading/search.gif"), (String)new ResourceLocation("palarpg", "textures/gui/loading/search.png"), 3000L),
    BUILDING("Construction des salles", new ResourceLocation("palarpg", "textures/gui/loading/build.gif"), (String)new ResourceLocation("palarpg", "textures/gui/loading/build.png"), 3000L),
    SPAWNING("Apparition des mobs", new ResourceLocation("palarpg", "textures/gui/loading/spawn.gif"), (String)new ResourceLocation("palarpg", "textures/gui/loading/spawn.png"), 3000L);
    
    DungeonLoadingState(String title, ResourceLocation gif, ResourceLocation png, long duration) {
      this.title = title;
      this.gif = gif;
      this.png = png;
      this.duration = duration;
    }
    
    private final String title;
    
    private final ResourceLocation gif;
    
    private final ResourceLocation png;
    
    private final long duration;
    
    public String getTitle() {
      return this.title;
    }
    
    public ResourceLocation getGif() {
      return this.gif;
    }
    
    public ResourceLocation getPng() {
      return this.png;
    }
    
    public long getDuration() {
      return this.duration;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\loading\UIDungeonLoading.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */