package fr.paladium.palarpg.module.dungeon.client.ui.choice;

import com.google.common.cache.CacheBuilder;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.scheduler.FMLClientScheduler;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundNode;
import fr.paladium.palarpg.module.dungeon.client.ui.choice.node.DungeonChoicePathHoverNode;
import fr.paladium.palarpg.module.dungeon.client.ui.choice.node.DungeonChoicePathLineNode;
import fr.paladium.palarpg.module.dungeon.client.ui.choice.node.DungeonChoicePathNode;
import fr.paladium.palarpg.module.dungeon.client.ui.utils.DungeonSynchronizedUI;
import fr.paladium.palarpg.module.dungeon.common.network.choice.CSPacketRPGDungeonChoiceNext;
import fr.paladium.palarpg.module.dungeon.common.network.ui.sync.BBPacketRPGDungeonSynchronizedUIClose;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.generator.DungeonGenerator;
import fr.paladium.palarpg.module.dungeon.server.config.room.DungeonRoomConfig;
import fr.paladium.permissionbridge.common.data.PermissibleEntity;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.BaseTween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.opengl.modifier.GLRotation;
import fr.paladium.zephyrui.lib.opengl.modifier.GLVector;
import fr.paladium.zephyrui.lib.opengl.transform.glto.GLTORotating;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.resource.ResourceBuilder;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.TransformNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.hover.HoverElement;
import fr.paladium.zephyrui.lib.ui.node.hover.impl.NodeHoverElement;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.draggable.DraggableProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.vecmath.Vector4f;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@UIData(background = false)
public class UIDungeonChoice extends DungeonSynchronizedUI {
  private final DungeonWorld world;
  
  private final DungeonGenerator generator;
  
  private final AtomicBoolean visible;
  
  private final TweenAnimator closingAnimator;
  
  private final TweenAnimator transitionAnimator;
  
  private final BooleanSignal leaderSignal;
  
  private final BooleanSignal waitingSignal;
  
  private final Signal<DungeonRoomConfig> nextRoomSignal;
  
  private final Signal<DungeonGenerator.DungeonRoomPath> selectedPathSignal;
  
  private final DungeonRoomConfig currentRoom;
  
  private boolean closing;
  
  public UIDungeonChoice() {
    this.world = DungeonWorld.getClient().get();
    this.generator = this.world.getGenerator();
    this.visible = new AtomicBoolean(false);
    this.closingAnimator = TweenAnimator.create(0.0F);
    this.transitionAnimator = TweenAnimator.create(0.0F);
    this.leaderSignal = new BooleanSignal(this.world.isLeader((Entity)(Minecraft.func_71410_x()).field_71439_g));
    this.waitingSignal = new BooleanSignal(false);
    this.nextRoomSignal = new Signal();
    this.selectedPathSignal = new Signal();
    this.currentRoom = ((DungeonGenerator.DungeonRoomPath)this.world.getPaths().getLast()).getRoom();
    setMain(this.world.getLeader().getUuidString());
  }
  
  public void init() {
    super.init();
    this.closing = false;
    this.visible.set(false);
    this.transitionAnimator.setValue(0.0F);
    this.transitionAnimator.sequence(500.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).setCallback(t1 -> {
          this.visible.set(true);
          FMLClientScheduler.getInstance().add(new Runnable[] { () });
        }).start();
    ContainerNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .body(container -> {
          BackgroundNode.create().body(()).attach(container);
          TextNode.create(80.0D, 46.0D).text(Text.create("selection des salles", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 59.0F, Color.WHITE).shadow(0.0F, 0.0F)).modifier(TextModifier.UPPER_CASE)).attach(container);
          ((TextButtonNode)TextButtonNode.create(1621.0D, 976.0D).text("choisir").onClick(()).onUpdate(())).width(245.0D).hoverLines(()).enabled(()).attach(container);
        }).visible(node -> this.visible.get())
      .attach((UI)this);
    ContainerNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .body(container -> {
          if (this.nextRoomSignal.getOrDefault() == null)
            return; 
          DungeonRoomConfig nextRoom = (DungeonRoomConfig)this.nextRoomSignal.getOrDefault();
          ResourceBuilder builder = ResourceBuilder.create().async().nearest().cache(CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build());
          if (nextRoom.getType() != DungeonRoomConfig.DungeonRoomType.MINIBOSS && nextRoom.getType() != DungeonRoomConfig.DungeonRoomType.BOSS) {
            ((TextNode)TextNode.create(container.dw(2.0D), 460.0D).text(Text.create("entrée", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 20.0F, Color.decode("#BDA992").copyAlpha(this.closingAnimator.getValue())))).onAnimate(())).animate(this.closingAnimator).anchorX(Align.CENTER).attach(container);
            FlexNode.horizontal(container.dw(2.0D), 510.0D, 80.0D).align(Align.CENTER).body(()).anchorX(Align.CENTER).attach(container);
          } else {
            ((ImageNode)ImageNode.create(0.0D, 0.0D, 1920.0D, 1080.0D).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/choice/boss/first.png")).linear()).onAnimate(())).animate(this.closingAnimator).attach(container);
            ((ImageNode)ImageNode.create(0.0D, 0.0D, 1920.0D, 1080.0D).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/choice/boss/second.png")).linear()).color(Color.WHITE.copyAlpha(0.0F)).onAnimate(())).animate(this.closingAnimator).attach(container);
            Color color = Color.decode("#E13429");
            ((TextNode)TextNode.create(container.dw(2.0D), 450.0D).text(Text.create(nextRoom.getName().replace("é", "e").replace("è", "e").replace("ê", "e"), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 130.0F, color.copyAlpha(0.0F)).shadow().shadow(0.0F, 4.0F))).onAnimate(())).animate(this.closingAnimator).anchorX(Align.CENTER).attach(container);
            ((ImageNode)ImageNode.create(container.dw(2.0D), -container.dh(2.0D)).resource(builder.of(new ResourceLocation("palarpg", "textures/gui/choice/icons/" + nextRoom.getType().name().toLowerCase() + ".png"))).onAnimate(())).height(container.h() * 2.0D).animate(this.closingAnimator).anchorX(Align.CENTER).attach(container);
          } 
        }).watch(this.nextRoomSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).visible(node -> (this.nextRoomSignal.getOrDefault() != null))
      .zindex(100)
      .attach((UI)this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    if (this.visible.get())
      DrawUtils.SHAPE.drawRect(getRelativeX(0.0D), getRelativeY(0.0D), getViewportWidth(), getViewportHeight(), BackgroundNode.BACKGROUND_COLOR); 
  }
  
  public void postDraw(double mouseX, double mouseY, float ticks) {
    this.closingAnimator.update();
    this.transitionAnimator.update();
    DrawUtils.SHAPE.drawRect(getRelativeX(0.0D), getRelativeY(0.0D), getViewportWidth(), getViewportHeight(), BackgroundNode.BACKGROUND_COLOR.copyAlpha((this.closingAnimator.getValue() > 2.0F) ? (3.0F - this.closingAnimator.getValue()) : this.closingAnimator.getValue()));
    DrawUtils.SHAPE.drawRect(getRelativeX(0.0D), getRelativeY(0.0D), getViewportWidth(), getViewportHeight(), BackgroundNode.BACKGROUND_COLOR.copyAlpha(this.transitionAnimator.getValue()));
  }
  
  public boolean close() {
    if (this.currentRoom == ((DungeonGenerator.DungeonRoomPath)((DungeonWorld)DungeonWorld.getClient().get()).getPaths().getLast()).getRoom())
      return false; 
    if (((Boolean)this.leaderSignal.getOrDefault()).booleanValue()) {
      (new BBPacketRPGDungeonSynchronizedUIClose(UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g), getClass().getName(), false)).send();
      onRemoteClosed();
    } 
    return this.closing;
  }
  
  public void update() {
    if (!DungeonWorld.getClient().isPresent() || ((DungeonWorld)DungeonWorld.getClient().get()).getState() != DungeonWorld.DungeonState.STARTED) {
      ZUI.close((UI)this, true);
      (new Notification(Notification.NotificationType.ERROR, "Impossible de charger le donjon", "paladium")).send();
      return;
    } 
    this.leaderSignal.set(Boolean.valueOf(this.world.isLeader((Entity)(Minecraft.func_71410_x()).field_71439_g)));
    setMain(this.world.getLeader().getUuidString());
  }
  
  public void onRemoteClosed() {
    if (this.visible.get() && this.closingAnimator.getValue() == 0.0F && this.transitionAnimator.getValue() == 0.0F) {
      this.nextRoomSignal.set(((DungeonGenerator.DungeonRoomPath)((DungeonWorld)DungeonWorld.getClient().get()).getPaths().getLast()).getRoom());
      this.closingAnimator.sequence(1000.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).setCallback(t -> {
            this.visible.set(false);
            (new Thread(())).start();
          }).start();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\choice\UIDungeonChoice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */