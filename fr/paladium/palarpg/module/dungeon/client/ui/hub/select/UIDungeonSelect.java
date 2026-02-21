package fr.paladium.palarpg.module.dungeon.client.ui.hub.select;

import com.google.gson.JsonObject;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.button.impl.BackButtonNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundElementNode;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundNode;
import fr.paladium.palarpg.client.ui.kit.separator.GradientSeparatorNode;
import fr.paladium.palarpg.client.ui.kit.textfield.TextFieldNode;
import fr.paladium.palarpg.common.api.ItemsRegister;
import fr.paladium.palarpg.module.dungeon.DungeonModule;
import fr.paladium.palarpg.module.dungeon.client.ui.hub.UIDungeonHub;
import fr.paladium.palarpg.module.dungeon.client.ui.hub.select.node.DungeonSelectItemSlotNode;
import fr.paladium.palarpg.module.dungeon.client.ui.hub.select.node.DungeonSelectLevelNode;
import fr.paladium.palarpg.module.dungeon.client.ui.hub.select.node.DungeonSelectLevelRewardsNode;
import fr.paladium.palarpg.module.dungeon.common.network.hub.BBPacketRPGDungeonHubGetEntity;
import fr.paladium.palarpg.module.dungeon.common.network.hub.BBPacketRPGDungeonHubSetLevel;
import fr.paladium.palarpg.module.dungeon.common.utils.RomanNumberFormatter;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonConfig;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelConfig;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelEntitiesConfig;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelLootsConfig;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.RPGEntityData;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.BaseTween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.transition.Transition;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.entity.EntityNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.textfield.TextFieldNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.grid.GridNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class UIDungeonSelect extends UI {
  private final boolean leader;
  
  private final List<DungeonConfig> dungeons;
  
  private final Signal<DungeonWorld> world;
  
  private final TweenAnimator animator;
  
  private final Signal<DungeonConfig> selectedDungeon;
  
  private final Signal<DungeonLevelConfig> selectedLevel;
  
  private final StringSignal searchSignal;
  
  private boolean closing;
  
  private double scrollX;
  
  private double targetScrollX;
  
  private double maxScrollX;
  
  public boolean isLeader() {
    return this.leader;
  }
  
  public List<DungeonConfig> getDungeons() {
    return this.dungeons;
  }
  
  public Signal<DungeonWorld> getWorld() {
    return this.world;
  }
  
  public TweenAnimator getAnimator() {
    return this.animator;
  }
  
  public Signal<DungeonConfig> getSelectedDungeon() {
    return this.selectedDungeon;
  }
  
  public Signal<DungeonLevelConfig> getSelectedLevel() {
    return this.selectedLevel;
  }
  
  public StringSignal getSearchSignal() {
    return this.searchSignal;
  }
  
  public boolean isClosing() {
    return this.closing;
  }
  
  public double getScrollX() {
    return this.scrollX;
  }
  
  public double getTargetScrollX() {
    return this.targetScrollX;
  }
  
  public double getMaxScrollX() {
    return this.maxScrollX;
  }
  
  public UIDungeonSelect(boolean leader, @NonNull List<DungeonConfig> dungeons, @NonNull DungeonWorld world) {
    if (dungeons == null)
      throw new NullPointerException("dungeons is marked non-null but is null"); 
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    this.leader = leader;
    this.dungeons = dungeons;
    this.world = Signal.of(world.setWorld((World)(Minecraft.func_71410_x()).field_71441_e));
    this.animator = TweenAnimator.create(0.0F);
    this.selectedDungeon = new Signal();
    this.selectedLevel = new Signal();
    this.searchSignal = new StringSignal();
  }
  
  public void init() {
    setTransition(new UIDungeonSelectTransition());
    BackgroundNode.create()
      .onMouseScroll((node, mouseX, mouseY, value) -> {
          if (this.maxScrollX <= 0.0D || value == 0)
            return; 
          double speed = Keyboard.isKeyDown(29) ? 2.0D : 1.0D;
          this.targetScrollX = Math.min(this.targetScrollX + ((value > 0) ? 30 : -30) * speed, 0.0D);
          if (this.targetScrollX <= -this.maxScrollX)
            this.targetScrollX = -this.maxScrollX; 
        }).onDraw((node, mouseX, mouseY, partialTicks) -> {
          if (this.maxScrollX <= 0.0D)
            return; 
          this.targetScrollX = Math.min(Math.max(this.targetScrollX, -this.maxScrollX), 0.0D);
          if (this.targetScrollX != this.scrollX)
            this.scrollX = lerpByFramerate(this.scrollX, this.targetScrollX, 0.2D, 0.2D, true); 
        }).attach(this);
    ((ImageNode)ImageNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/select/foreground.png")).linear())
      .onDraw((node, mouseX, mouseY, partialTicks) -> node.color(this.selectedDungeon.isPresent() ? ((DungeonConfig)this.selectedDungeon.getOrDefault()).getColor().copyAlpha((float)(this.animator.getValue() * (0.5D + (Math.sin(6.283185307179586D * (System.currentTimeMillis() % 8000L) / 4000.0D) + 1.0D) / 4.0D))) : Color.TRANSPARENT)))
      .attach(this);
    ((TextNode)TextNode.create(62.0D, 38.0D)
      .onInit(node -> node.text(Text.create("sélection du " + (this.selectedDungeon.isPresent() ? "palier" : "donjon"), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 58.0F, Color.decode("#F6F9E8"))))))
      .watch(this.selectedDungeon)
      .attach(this);
    BackButtonNode.create(1780.0D, 70.0D)
      .onClick((node, mouseX, mouseY, clickType) -> ZUI.close(this))
      .attach(this);
    CloseButtonNode.create(1837.0D, 70.0D)
      .onClick((node, mouseX, mouseY, clickType) -> ZUI.close(this, true))
      .attach(this);
    ContainerNode.create(28.0D, 191.0D, 1864.0D, 861.0D)
      .body(container -> {
          this.maxScrollX = (458 * this.dungeons.size()) - container.aw(-33.0D);
          List<DungeonConfig> sortedDungeons = (List<DungeonConfig>)this.dungeons.stream().sorted(Comparator.comparing(DungeonConfig::getIndex)).collect(Collectors.toCollection(java.util.LinkedList::new));
          for (int i = 0; i < sortedDungeons.size(); i++) {
            DungeonConfig dungeon = sortedDungeons.get(i);
            RectNode.create((33 + i * 458), 0.0D, 430.0D, 763.0D).color(new Color(23, 23, 23)).body(()).onDraw(()).visible(()).effect(()).attach(container);
          } 
        }).attach(this);
    RectNode.create(530.0D, 191.0D, 1322.0D, 763.0D)
      .color(new Color(23, 23, 23))
      .body(rect -> {
          DungeonConfig dungeon = (DungeonConfig)this.selectedDungeon.getOrDefault();
          if (dungeon == null)
            return; 
          int maxLevel = ((DungeonWorld)this.world.getOrDefault()).getMaxLevel((DungeonConfig)this.selectedDungeon.getOrDefault());
          int maxDungeonLevel = dungeon.getLevels().stream().mapToInt(DungeonLevelConfig::getLevel).max().orElse(2147483647);
          this.selectedLevel.silent().set(null);
          dungeon.getLevelOrInfinite(maxLevel).ifPresent(());
          if (this.selectedLevel.getOrDefault() == null)
            this.selectedLevel.set(dungeon.getLevels().stream().min(Comparator.comparing(DungeonLevelConfig::getLevel)).orElse(null)); 
          if (this.selectedLevel.getOrDefault() == null) {
            this.closing = true;
            ZUI.close(this);
            (new Notification(Notification.NotificationType.ERROR, "Impossible de charger le donjon", "paladium")).send();
            return;
          } 
          Color color = dungeon.getColor();
          TextNode.create(34.0D, 32.0D).text(Text.create("votre palier", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 20.0F, Color.WHITE))).attach(rect);
          FlexNode.horizontal(460.0D, 37.0D, 20.0D).margin(10.0D).align(Align.CENTER).body(()).anchorX(Align.END).attach(rect);
          TextFieldNode.create(38.0D, 79.0D, 405.0D, 30.0D).placeholder("Rechercher").onChange(()).attach(rect);
          AtomicInteger lastLevel = new AtomicInteger(-1);
          AtomicBoolean scrollUpdated = new AtomicBoolean(false);
          AtomicReference<Node> selectedNode = new AtomicReference<>(null);
          ContainerNode.create(34.0D, 113.0D, 413.0D, rect.ah(-113.0D)).overflow(OverflowProperty.SCROLL).scrollbar((ScrollbarNode)ScrollbarNode.create(430.0D, 20.0D, rect.ah(-113.0D) - 40.0D, 3.0D)).body(()).onScrollEnd(()).onRender(()).attach(rect);
          RectNode.create(rect.aw(-837.0D), 0.0D, 837.0D, rect.getHeight()).color(Color.BLACK.copyAlpha(0.17F)).body(()).watch(this.world, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD }).watch(this.selectedLevel, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(rect);
          GradientSeparatorNode.horizontal(0.0D, 0.0D, rect.getWidth(), 3.0D).color(color, color.darker(0.3F).copyAlpha(0.0F)).attach(rect);
          GradientSeparatorNode.horizontal(0.0D, rect.ah(-3.0D), rect.getWidth(), 3.0D).color(color, color.darker(0.3F).copyAlpha(0.0F)).attach(rect);
        }).onDraw((node, mouseX, mouseY, partialTicks) -> node.x(node.getDefaultX() + (1920.0D - node.getDefaultX()) * (1.0F - this.animator.getValue())))
      .visible(node -> this.selectedDungeon.isPresent())
      .watch(this.selectedDungeon, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(this);
  }
  
  public void update() {
    if (this.closing)
      return; 
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(((DungeonWorld)this.world.getOrDefault()).getWorld());
    if (!optionalDungeonWorld.isPresent()) {
      ZUI.close(this);
      return;
    } 
    this.world.set(optionalDungeonWorld.get());
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    this.animator.update();
    DrawUtils.SHAPE.drawRect(0.0D, 0.0D, 1920.0D, 1080.0D, BackgroundNode.BACKGROUND_COLOR);
  }
  
  public boolean close() {
    if (!getData().active())
      return true; 
    if (this.selectedDungeon.isPresent() && !this.closing) {
      this.animator.sequence(500.0F, 0.0F, (TweenEquation)TweenEquations.QUART_INOUT).setCallback(tween -> this.selectedDungeon.set(null)).start();
      return false;
    } 
    getData().setActive(false);
    ZUI.open((UI)new UIDungeonHub(this.leader, this.dungeons));
    return false;
  }
  
  private static class UIDungeonSelectTransition extends Transition {
    public UIDungeonSelectTransition() {
      super(new UIDungeonSelectInTransition(null), new UIDungeonSelectOutTransition(null));
    }
    
    private static class UIDungeonSelectInTransition extends Transition.In {
      private UIDungeonSelectInTransition() {}
      
      public void init(@NonNull UI ui) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
      }
      
      public void start() {
        start(getAnimator().sequence(500.0F, 1.0F, (TweenEquation)TweenEquations.QUART_OUT).getTimeline());
      }
      
      public void pre(@NonNull UI ui, double mouseX, double mouseY) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
        GL11.glPushMatrix();
        GL11.glTranslated((-1920.0F * (1.0F - getAnimator().getValue())), 0.0D, 0.0D);
      }
      
      public void post(@NonNull UI ui, double mouseX, double mouseY) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
        GL11.glPopMatrix();
      }
    }
    
    private static class UIDungeonSelectOutTransition extends Transition.Out {
      private UIDungeonSelectOutTransition() {}
      
      public void init(@NonNull UI ui) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
      }
      
      public void start() {
        start(getAnimator().sequence(500.0F, 0.0F, (TweenEquation)TweenEquations.QUART_IN).getTimeline());
      }
      
      public void pre(@NonNull UI ui, double mouseX, double mouseY) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
        GL11.glPushMatrix();
        GL11.glTranslated((-1920.0F * (1.0F - getAnimator().getValue())), 0.0D, 0.0D);
      }
      
      public void post(@NonNull UI ui, double mouseX, double mouseY) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
        GL11.glPopMatrix();
      }
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\hub\select\UIDungeonSelect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */