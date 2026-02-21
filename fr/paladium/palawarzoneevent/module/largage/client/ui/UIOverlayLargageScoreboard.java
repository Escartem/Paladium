package fr.paladium.palawarzoneevent.module.largage.client.ui;

import fr.paladium.chronos.server.api.pojo.Planned;
import fr.paladium.chronos.server.runnables.PlannedStatued;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.ButtonNode;
import fr.paladium.paladiumui.kit.button.IconButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palawarzoneevent.module.largage.common.action.server.LargageServerAction;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.UIDataOverlay;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.interaction.UIDataOverlayInteraction;
import fr.paladium.zephyrui.lib.ui.core.hook.property.UIProperty;
import fr.paladium.zephyrui.lib.ui.core.hook.property.UIPropertyHook;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.property.draggable.DraggableProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import net.minecraft.util.ResourceLocation;

@UIData(active = true, anchorX = Align.END, background = false)
@UIDataGuiscale(active = true)
@UIDataOverlay(active = true, interaction = @UIDataOverlayInteraction(active = true, cancelClick = true, cancelKeyboard = false, cancelScroll = false))
public class UIOverlayLargageScoreboard extends UI {
  private static final Resource TIMER_LOGO = Resource.of((ResourceLocation)MCResource.of("palawarzoneevent", "textures/gui/largage/timer.png")).nearest();
  
  private static final Resource POSITION_LOGO = Resource.of((ResourceLocation)MCResource.of("palawarzoneevent", "textures/gui/largage/position.png")).nearest();
  
  private static final Resource NOT_HIDDEN = Resource.of((ResourceLocation)MCResource.of("palawarzoneevent", "textures/gui/largage/not_hidden.png")).nearest();
  
  private static final Resource HIDDEN = Resource.of((ResourceLocation)MCResource.of("palawarzoneevent", "textures/gui/largage/hidden.png")).nearest();
  
  private static final TextInfo TITLE_INFO = TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 24.0F, new Color(255, 60, 60)).shadow().shadow(new Color(168, 8, 8)).shadow(0.0F, 2.0F);
  
  private static final TextInfo SUB_TITLE_INFO = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 24.0F, Color.WHITE);
  
  private final Signal<DoubleLocation> approximatedLocation = new Signal();
  
  public Signal<DoubleLocation> getApproximatedLocation() {
    return this.approximatedLocation;
  }
  
  private final Signal<Planned> runningPlannedEvent = new Signal(null);
  
  public Signal<Planned> getRunningPlannedEvent() {
    return this.runningPlannedEvent;
  }
  
  private final Signal<PlannedStatued> nextPlannedEvent = new Signal(null);
  
  private BooleanSignal hiddenSignal;
  
  public Signal<PlannedStatued> getNextPlannedEvent() {
    return this.nextPlannedEvent;
  }
  
  public BooleanSignal getHiddenSignal() {
    return this.hiddenSignal;
  }
  
  private final TweenAnimator animator = TweenAnimator.create(1.0F);
  
  public TweenAnimator getAnimator() {
    return this.animator;
  }
  
  private final double defaultBackgroundHeight = 460.0D;
  
  public double getDefaultBackgroundHeight() {
    getClass();
    return 460.0D;
  }
  
  @UIProperty
  private double x = 1500.0D;
  
  public double getX() {
    return this.x;
  }
  
  @UIProperty
  private double y = 305.0D;
  
  public double getY() {
    return this.y;
  }
  
  @UIProperty
  private boolean hidden = false;
  
  public boolean isHidden() {
    return this.hidden;
  }
  
  public void init() {
    this.hiddenSignal = new BooleanSignal(this.hidden);
    this.animator.setValue(this.hidden ? 0.0F : 1.0F);
    BackgroundNode.create(this.x, this.y, 380.0D, 460.0D)
      .radius(10.0D, 27.0D)
      .body(bg -> ContainerNode.create(0.0D, 0.0D, bg.w(), bg.h()).body(()).onDraw(()).effect(MaskNodeEffect::create).attach(bg))
      
      .onDraw((node, mouseX, mouseY, partialTicks) -> {
          getClass();
          double newHeight = (460.0D - 80.0D) * this.animator.getValue();
          node.height(newHeight + 80.0D);
        }).onDragEnd(node -> {
          this.x = node.getX();
          this.y = node.getY();
          UIPropertyHook.save(this);
        }).draggable(DraggableProperty.screen())
      .attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    this.animator.update();
  }
  
  public static void setRunningPlannedEvent(Planned planned) {
    UIOverlayLargageScoreboard ui = (UIOverlayLargageScoreboard)ZUI.getUI(UIOverlayLargageScoreboard.class);
    if (ui != null)
      ui.getRunningPlannedEvent().set(planned); 
  }
  
  public static void setNextPlannedEvent(PlannedStatued planned) {
    UIOverlayLargageScoreboard ui = (UIOverlayLargageScoreboard)ZUI.getUI(UIOverlayLargageScoreboard.class);
    if (ui != null)
      ui.getNextPlannedEvent().set(planned); 
  }
  
  public static void setApproximatedLocation(double x, double y, double z) {
    UIOverlayLargageScoreboard ui = (UIOverlayLargageScoreboard)ZUI.getUI(UIOverlayLargageScoreboard.class);
    if (ui != null && x != 0.0D && y != 0.0D && z != 0.0D) {
      ui.getApproximatedLocation().set(new DoubleLocation(x, y, z));
      return;
    } 
    ui.getApproximatedLocation().set(null);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\clien\\ui\UIOverlayLargageScoreboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */