package fr.paladium.palashop.provider.box.client.overlay;

import com.google.common.util.concurrent.AtomicDouble;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.scheduler.FMLClientScheduler;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.provider.box.client.overlay.animation.ShopBoxSpinTweenEquation;
import fr.paladium.palashop.provider.box.client.overlay.node.ShopBoxBoostNode;
import fr.paladium.palashop.provider.box.client.overlay.node.ShopBoxItemNode;
import fr.paladium.palashop.provider.box.client.overlay.node.ShopBoxRewardNode;
import fr.paladium.palashop.provider.box.client.ui.UIShopBoxPage;
import fr.paladium.palashop.provider.box.client.utils.EntityBoxState;
import fr.paladium.palashop.provider.box.common.dto.box.BoxReward;
import fr.paladium.palashop.provider.box.common.entity.EntityBox;
import fr.paladium.palashop.provider.box.common.network.server.CSPacketBoxKeepAlive;
import fr.paladium.palashop.provider.box.common.network.server.CSPacketBoxNext;
import fr.paladium.palashop.provider.box.common.network.server.CSPacketBoxReward;
import fr.paladium.palashop.provider.box.common.network.server.CSPacketBoxStop;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
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
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.debug.UIDataDebug;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.bezier.Bezier;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.vecmath.Vector2d;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.core.event.CustomInstructionKeyframeEvent;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;

@UIDataDebug(hotreload = false)
@UIData(background = false, projection = false)
public class UIShopBoxOverlay extends UI {
  public void setRandomSeed(long randomSeed) {
    this.randomSeed = randomSeed;
  }
  
  public void setOpening(boolean opening) {
    this.opening = opening;
  }
  
  public void setMask(boolean mask) {
    this.mask = mask;
  }
  
  public void setGlowStart(long glowStart) {
    this.glowStart = glowStart;
  }
  
  public void setLastKeepAlive(long lastKeepAlive) {
    this.lastKeepAlive = lastKeepAlive;
  }
  
  public void setWaitingLock(Object waitingLock) {
    this.waitingLock = waitingLock;
  }
  
  private static final Resource RIGHT_CLICK_RESOURCE = Resource.of(new ResourceLocation("palashop", "textures/overlay/boxes/action/right_click.png")).linear();
  
  private static final Resource GLOW_RESOURCE = Resource.of(new ResourceLocation("palashop", "textures/overlay/boxes/glow/legendary.png")).linear();
  
  private final EntityBox box;
  
  private final EntityBoxState state;
  
  private final Queue<Runnable> scheduledTasks;
  
  private final IntegerSignal spinSignal;
  
  private final IntegerSignal wheelSignal;
  
  private final IntegerSignal rewardSignal;
  
  private final IntegerSignal keyCountSignal;
  
  private final IntegerSignal spinCountSignal;
  
  private final IntegerSignal freeSpinSpignal;
  
  private final ListSignal<BoxReward> claimedRewardsSignal;
  
  private final TweenAnimator wheelAnimator;
  
  private final TweenAnimator boostAnimator;
  
  private final TweenAnimator claimedRewardGlowAnimator;
  
  private final TweenAnimator claimedRewardMoveAnimator;
  
  private final TweenAnimator claimedRewardSlideAnimator;
  
  private final TweenAnimator claimedRewardAppearAnimator;
  
  private final TweenAnimator transitionAnimator;
  
  private long randomSeed;
  
  private boolean opening;
  
  private boolean mask;
  
  private long glowStart;
  
  private long lastKeepAlive;
  
  private Object waitingLock;
  
  public EntityBox getBox() {
    return this.box;
  }
  
  public EntityBoxState getState() {
    return this.state;
  }
  
  public Queue<Runnable> getScheduledTasks() {
    return this.scheduledTasks;
  }
  
  public IntegerSignal getSpinSignal() {
    return this.spinSignal;
  }
  
  public IntegerSignal getWheelSignal() {
    return this.wheelSignal;
  }
  
  public IntegerSignal getRewardSignal() {
    return this.rewardSignal;
  }
  
  public IntegerSignal getKeyCountSignal() {
    return this.keyCountSignal;
  }
  
  public IntegerSignal getSpinCountSignal() {
    return this.spinCountSignal;
  }
  
  public IntegerSignal getFreeSpinSpignal() {
    return this.freeSpinSpignal;
  }
  
  public ListSignal<BoxReward> getClaimedRewardsSignal() {
    return this.claimedRewardsSignal;
  }
  
  public TweenAnimator getWheelAnimator() {
    return this.wheelAnimator;
  }
  
  public TweenAnimator getBoostAnimator() {
    return this.boostAnimator;
  }
  
  public TweenAnimator getClaimedRewardGlowAnimator() {
    return this.claimedRewardGlowAnimator;
  }
  
  public TweenAnimator getClaimedRewardMoveAnimator() {
    return this.claimedRewardMoveAnimator;
  }
  
  public TweenAnimator getClaimedRewardSlideAnimator() {
    return this.claimedRewardSlideAnimator;
  }
  
  public TweenAnimator getClaimedRewardAppearAnimator() {
    return this.claimedRewardAppearAnimator;
  }
  
  public TweenAnimator getTransitionAnimator() {
    return this.transitionAnimator;
  }
  
  public long getRandomSeed() {
    return this.randomSeed;
  }
  
  public boolean isOpening() {
    return this.opening;
  }
  
  public boolean isMask() {
    return this.mask;
  }
  
  public long getGlowStart() {
    return this.glowStart;
  }
  
  public long getLastKeepAlive() {
    return this.lastKeepAlive;
  }
  
  public Object getWaitingLock() {
    return this.waitingLock;
  }
  
  public UIShopBoxOverlay(@NonNull EntityBox box) {
    if (box == null)
      throw new NullPointerException("box is marked non-null but is null"); 
    this.box = box;
    this.state = box.getClientState();
    this.scheduledTasks = new ConcurrentLinkedQueue<>();
    this.spinSignal = new IntegerSignal(0);
    this.wheelSignal = new IntegerSignal(0);
    this.rewardSignal = new IntegerSignal(0);
    this.keyCountSignal = new IntegerSignal(0);
    this.spinCountSignal = new IntegerSignal(0);
    this.freeSpinSpignal = new IntegerSignal(0);
    this.claimedRewardsSignal = new ListSignal(new ArrayList());
    this.wheelAnimator = TweenAnimator.create(0.0F);
    this.boostAnimator = TweenAnimator.create(0.0F);
    this.claimedRewardGlowAnimator = TweenAnimator.create(0.0F);
    this.claimedRewardMoveAnimator = TweenAnimator.create(0.0F);
    this.claimedRewardSlideAnimator = TweenAnimator.create(0.0F);
    this.claimedRewardAppearAnimator = TweenAnimator.create(0.0F);
    this.transitionAnimator = TweenAnimator.create(0.0F);
  }
  
  public void init() {
    this.state.getPlayer().getEventSet().clear();
    this.state.getPlayer().subscribe(value -> {
          FMLClientScheduler.getInstance().add(new Runnable[] { this::init });
          return false;
        });
    getNodeList().clear();
    if (this.waitingLock != null) {
      synchronized (this.waitingLock) {
        this.waitingLock.notifyAll();
        this.waitingLock = null;
      } 
      return;
    } 
    if (this.state.isActive() || this.state.isWaiting())
      if (this.state.isWaiting() || !this.state.isFast()) {
        start();
      } else {
        startFast();
      }  
    if (!this.state.isWaiting())
      initClosed(); 
  }
  
  private void startFast() {
    LindwormAnimatable animatable = (LindwormAnimatable)this.box.getBoxData().getResource().getLindwormModel().getOrCreateAnimatable((Entity)this.box);
    if (animatable == null || animatable.getController() == null) {
      (new CSPacketBoxStop(this.box.func_145782_y())).send();
      return;
    } 
    List<BoxReward> sortedRewards = new LinkedList<>();
    for (List<BoxReward> rewards : (Iterable<List<BoxReward>>)this.state.getRewards().getOrDefault())
      sortedRewards.addAll((Collection<? extends BoxReward>)rewards.stream().filter(reward -> (reward.getType() == BoxReward.Type.SHOP_ITEM)).collect(Collectors.toCollection(LinkedList::new))); 
    Collections.sort(sortedRewards, (a, b) -> Integer.compare(b.getShopItemRarity().ordinal(), a.getShopItemRarity().ordinal()));
    ShopRarity highestRarity = sortedRewards.isEmpty() ? ShopRarity.COMMON : ((BoxReward)sortedRewards.get(0)).getShopItem().getRarity();
    animatable.forceAnimation("fast_" + highestRarity.name().toLowerCase(), false);
    animatable.getController().registerCustomInstructionListener(instruction -> {
          if (instruction.instructions.contains("open")) {
            this.opening = true;
            FMLClientScheduler.getInstance().add(new Runnable[] { () });
          } else if (instruction.instructions.contains("close")) {
            this.transitionAnimator.sequence(700.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).setCallback(()).start();
          } 
        });
    this.transitionAnimator.sequence(1000.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
    playSound(this.box.getBoxData().getResource().getSound().getFast().getSound(highestRarity));
  }
  
  private void start() {
    this.randomSeed = (new Random()).nextLong();
    int currentSpin = ((Integer)this.state.getCurrentSpin().getOrDefault()).intValue();
    int totalFreeSpin = 0;
    int currentFreeSpin = 0;
    for (int i = 0; i < this.state.getRewards().size(); i++) {
      long spinCount = ((List)this.state.getRewards().get(i)).stream().filter(reward -> (reward.getType() == BoxReward.Type.FREE_SPIN)).count();
      totalFreeSpin = (int)(totalFreeSpin + spinCount);
      if (i < currentSpin)
        currentFreeSpin = (int)(currentFreeSpin + spinCount); 
    } 
    this.spinSignal.set(Integer.valueOf(currentSpin));
    this.freeSpinSpignal.set(Integer.valueOf(currentFreeSpin));
    this.spinCountSignal.set(Integer.valueOf(this.state.getSpin() - totalFreeSpin));
    LindwormAnimatable animatable = (LindwormAnimatable)this.box.getBoxData().getResource().getLindwormModel().getOrCreateAnimatable((Entity)this.box);
    if (animatable.getCurrentAnimation() == null || "idle".equals(animatable.getCurrentAnimation().getName()))
      if (this.state.isWaiting()) {
        animatable.forceAnimation("idle_common", true);
      } else {
        animatable.forceAnimation("open", false);
        animatable.getCurrentAnimation().setCallback(a -> a.forceAnimation("idle_common", true));
        playSound(this.box.getBoxData().getResource().getSound().getOpen());
      }  
    this.transitionAnimator.sequence(this.state.isWaiting() ? 0.0F : 1000.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).setCallback(t1 -> (new Thread((), "UIShopBoxOverlay-#1/" + this.box)).start())
      
      .start();
  }
  
  private void startSpin(int spinIndex, int rewardIndex, float duration) {
    LindwormAnimatable animatable = (LindwormAnimatable)this.box.getBoxData().getResource().getLindwormModel().getOrCreateAnimatable((Entity)this.box);
    if (spinIndex >= this.state.getSpin()) {
      (new Thread(() -> {
            try {
              Thread.sleep(1000L);
            } catch (Exception exception) {}
            animatable.forceAnimation("close", false);
            playSound(this.box.getBoxData().getResource().getSound().getClose());
            this.transitionAnimator.sequence(1000.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).setCallback(()).start();
          }"UIShopBoxOverlay-Stop-#1/" + this.box)).start();
      return;
    } 
    if (rewardIndex >= this.state.getCount(spinIndex)) {
      startSpin(spinIndex + 1, 0, duration);
      return;
    } 
    if (spinIndex != ((Integer)this.spinSignal.getOrDefault()).intValue()) {
      (new Thread(() -> {
            try {
              Thread.sleep(1000L);
            } catch (Exception exception) {}
            animatable.forceAnimation("close", false);
            playSound(this.box.getBoxData().getResource().getSound().getClose());
            this.transitionAnimator.sequence(1000.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).setCallback(()).start();
          }"UIShopBoxOverlay-Next-#1/" + this.box)).start();
      return;
    } 
    this.spinSignal.set(Integer.valueOf(spinIndex));
    this.rewardSignal.set(Integer.valueOf(rewardIndex));
    BoxReward reward = getReward(spinIndex, rewardIndex);
    if (reward.getRarity() == ShopRarity.COMMON || reward.getRarity() == ShopRarity.RARE) {
      animatable.forceAnimation("idle_common", true);
      playSound(this.box.getBoxData().getResource().getSound().getSpin().getSound(ShopRarity.COMMON));
    } 
    if (((Integer)this.wheelSignal.getOrDefault()).intValue() <= 0)
      this.wheelSignal.set(Integer.valueOf(BoxReward.Type.SHOP_ITEM.getWheelCount() - 7)); 
    float random = (float)(Math.random() / 2.0D);
    if (random > 0.43F)
      random = 0.43F; 
    Random randomGen = getRandom(spinIndex, rewardIndex);
    boolean isLeft = randomGen.nextBoolean();
    double closeLegendaryLeft = randomGen.nextDouble();
    if (reward.getType() == BoxReward.Type.SHOP_ITEM && reward.getShopItem().getRarity() == ShopRarity.EPIC && closeLegendaryLeft <= 0.35D && 
      random < 0.43F && randomGen.nextBoolean())
      random = 0.43F; 
    if (isLeft)
      random = -random; 
    float value = this.wheelAnimator.getValue();
    Timeline spinTimeline = Timeline.createSequence();
    spinTimeline.push(Tween.to(this.wheelAnimator, 0, duration).target(((Integer)this.wheelSignal.getOrDefault()).intValue() + random).ease((TweenEquation)new ShopBoxSpinTweenEquation()));
    if (reward.getType() != BoxReward.Type.SHOP_ITEM || (reward.getShopItem().getRarity() != ShopRarity.COMMON && reward.getShopItem().getRarity() != ShopRarity.RARE)) {
      (new Thread(() -> {
            try {
              Thread.sleep((long)duration + 700L - 3800L);
              playSound(this.box.getBoxData().getResource().getSound().getSpin().getRiser());
            } catch (Exception exception) {}
          }"UIShopBoxOverlay-Riser/" + this.box)).start();
      spinTimeline.push(Tween.to(this.wheelAnimator, 0, 700.0F).target(((Integer)this.wheelSignal.getOrDefault()).intValue()).ease((TweenEquation)TweenEquations.SINE_OUT));
    } 
    this.wheelAnimator.clear();
    this.wheelAnimator.setValue(value);
    this.wheelAnimator.setTimeline(spinTimeline);
    this.wheelAnimator.setCallback(t1 -> {
          long slideDuration = this.claimedRewardsSignal.isEmpty() ? 0L : 500L;
          if (reward.getType() == BoxReward.Type.SHOP_ITEM) {
            (new Thread((), "UIShopBoxOverlay-Reward/" + this.box)).start();
          } else {
            playSound(this.box.getBoxData().getResource().getSound().getAction().getSound(reward.getType()));
            if (reward.getType() != BoxReward.Type.FREE_SPIN) {
              animatable.forceAnimation((reward.getType() == BoxReward.Type.BOOST_EPIC) ? "common_epic" : "epic_legendary", false);
              animatable.getCurrentAnimation().setCallback(());
            } 
            Timeline boostTimeline = Timeline.createSequence();
            boostTimeline.push(Tween.to(this.boostAnimator, 0, 1000.0F).target(1.0F).ease((TweenEquation)TweenEquations.QUART_INOUT));
            if (reward.getType() == BoxReward.Type.BOOST_EPIC) {
              boostTimeline.push(Tween.to(this.boostAnimator, 0, 1000.0F).target(0.0F).ease((TweenEquation)TweenEquations.QUART_INOUT));
            } else {
              boostTimeline.push(Tween.to(this.boostAnimator, 0, 2500.0F).target(2.0F).ease((TweenEquation)TweenEquations.QUART_INOUT));
              boostTimeline.push(Tween.to(this.boostAnimator, 0, 1000.0F).target(3.0F).ease((TweenEquation)TweenEquations.QUART_INOUT));
            } 
            this.boostAnimator.clear();
            this.boostAnimator.setTimeline(boostTimeline);
            this.boostAnimator.setCallback(()).start();
          } 
        }).start();
  }
  
  private void initClosed() {
    getNodeList().clear();
    TextNode.create(960.0D, 520.0D)
      .text(Text.create("vous possédez".toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 44.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach(this);
    FlexNode.horizontal(960.0D, 608.0D, 44.0D)
      .margin(13.0D)
      .align(Align.CENTER)
      .body(flex -> {
          ItemStack keyItem = new ItemStack(this.box.getBoxData().getItem());
          int count = ((Integer)this.keyCountSignal.getOrDefault()).intValue();
          TextNode.create(0.0D, 0.0D).text(Text.create("x" + count, TextInfo.create((IFont)PaladiumFont.MONTSERRAT_EXTRA_BOLD, 44.0F, Color.WHITE))).attach(flex);
          ImageNode.create(0.0D, 0.0D, flex.h(), flex.h()).resource(this.box.getBoxData().getResource().getItemResource()).attach(flex);
          TextNode.create(0.0D, 0.0D).text(Text.create(keyItem.func_82833_r().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_EXTRA_BOLD, 28.0F, Color.WHITE))).attach(flex);
        }).watch((Signal)this.keyCountSignal, this::exists, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).anchorX(Align.CENTER)
      .attach(this);
    ContainerNode.create(0.0D, 725.0D, 1920.0D, 46.0D)
      .body(container -> FlexNode.horizontal(container.dw(2.0D), 0.0D, container.h()).margin(100.0D).align(Align.CENTER).body(()).anchorX(Align.CENTER).attach(container))
      
      .watch((Signal)this.keyCountSignal, this::exists, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).zindex(100)
      .attach(this);
  }
  
  private void initFastOpened(List<BoxReward> sortedRewards) {
    getNodeList().clear();
    if ((!this.state.isActive() && !this.state.isWaiting()) || !this.state.isFast())
      return; 
    ContainerNode.create(121.0D, 154.0D, 1678.0D, 775.0D)
      .overflow(OverflowProperty.HIDDEN)
      .body(container -> {
          TextNode.create(container.dw(2.0D), 184.0D).text(Text.create("résultat de votre ouverture".toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_EXTRA_BOLD, 29.0F, Color.WHITE))).anchorX(Align.CENTER).attach(container);
          FlexNode.horizontal(container.dw(2.0D), 295.0D, 142.0D).margin(25.0D).align(Align.CENTER).body(()).anchorX(Align.CENTER).attach(container);
          FlexNode.horizontal(container.dw(2.0D), 295.0D, 142.0D).margin(25.0D).align(Align.CENTER).body(()).anchorX(Align.CENTER).attach(container);
          FlexNode.horizontal(container.dw(2.0D), 462.0D, 142.0D).margin(25.0D).align(Align.CENTER).body(()).anchorX(Align.CENTER).attach(container);
          FlexNode.horizontal(container.dw(2.0D), 462.0D, 142.0D).margin(25.0D).align(Align.CENTER).body(()).anchorX(Align.CENTER).attach(container);
        }).visible(node -> !this.mask)
      .zindex(100)
      .attach(this);
    TextNode.create(165.0D, 900.0D)
      .text(Text.create("box ouverte par ".toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 36.0F, Color.WHITE)).add(Text.create(((String)this.state.getPlayer().getOrDefault()).toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_EXTRA_BOLD, 36.0F, Color.WHITE))))
      .zindex(100)
      .attach(this);
  }
  
  private void initOpened() {
    getNodeList().clear();
    if (!this.state.isActive() && !this.state.isWaiting())
      return; 
    if (this.state.isWaiting()) {
      TextNode.create(960.0D, 480.0D)
        .text(Text.create("Ouverture en cours...", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 60.0F, Color.WHITE)))
        .anchorX(Align.CENTER)
        .attach(this);
      TextNode.create(960.0D, 560.0D)
        .text(Text.create("Une ouverture est en cours par " + (String)this.state.getPlayer().getOrDefault(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 45.0F, ColorConstant.LIGHT_GRAY)))
        .anchorX(Align.CENTER)
        .attach(this);
    } else {
      ContainerNode.create(121.0D, 154.0D, 1678.0D, 775.0D)
        .overflow(OverflowProperty.HIDDEN)
        .body(container -> {
            AtomicLong lastSound = new AtomicLong(0L);
            FlexNode.horizontal(20.0D, 268.0D, 284.0D).margin(20.0D).align(Align.CENTER).body(()).onDraw(()).attach(container);
          }).visible(node -> !this.mask)
        .attach(this);
    } 
    Text claimedRewardsText = Text.create(("ce que " + (String)this.state.getPlayer().getOrDefault() + " a déjà obtenu").toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_EXTRA_BOLD, 20.0F, Color.WHITE));
    TextNode.create(165.0D, 824.0D)
      .text(claimedRewardsText)
      .zindex(100)
      .attach(this);
    ContainerNode.create(165.0D + claimedRewardsText.aw(45.0D), 801.0D, 1799.0D - 165.0D + claimedRewardsText.aw(45.0D), 63.0D)
      .overflow(OverflowProperty.HIDDEN)
      .body(container -> {
          for (int i = 0; i < this.claimedRewardsSignal.size(); i++) {
            final int index = i;
            BoxReward reward = (BoxReward)this.claimedRewardsSignal.get(this.claimedRewardsSignal.size() - 1 - index);
            ShopBoxRewardNode.create(2.0D + container.ah(11.0D) * i, 2.0D, container.ah(-4.0D), container.ah(-4.0D)).reward(reward).mask(container).onRender(new NodeRenderCallback<Node>() {
                  @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
                  public void pre(@NonNull Node node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
                    if (node == null)
                      throw new NullPointerException("node is marked non-null but is null"); 
                    if (context == null)
                      throw new NullPointerException("context is marked non-null but is null"); 
                    node.x(node.getDefaultX() + container.ah(11.0D) * UIShopBoxOverlay.this.claimedRewardSlideAnimator.getValue());
                    if (index == 0) {
                      GL11.glPushMatrix();
                      GL11.glTranslated(node.dw(2.0D), node.dh(2.0D), 0.0D);
                      GL11.glScaled(UIShopBoxOverlay.this.claimedRewardAppearAnimator.getValue(), UIShopBoxOverlay.this.claimedRewardAppearAnimator.getValue(), 0.0D);
                      GL11.glTranslated(-node.dw(2.0D), -node.dh(2.0D), 0.0D);
                    } 
                  }
                  
                  @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
                  public void post(@NonNull Node node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
                    if (node == null)
                      throw new NullPointerException("node is marked non-null but is null"); 
                    if (context == null)
                      throw new NullPointerException("context is marked non-null but is null"); 
                    if (index == 0)
                      GL11.glPopMatrix(); 
                  }
                  
                  public void apply(@NonNull Node node, double mouseX, double mouseY, float partialTicks) {
                    if (node == null)
                      throw new NullPointerException("node is marked non-null but is null"); 
                  }
                }).visible(()).attach(container);
          } 
        }).watch((Signal)this.claimedRewardsSignal, this::exists, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(this);
    TextNode.create(165.0D, 900.0D)
      .text(Text.create("box ouverte par ".toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 36.0F, Color.WHITE)).add(Text.create(((String)this.state.getPlayer().getOrDefault()).toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_EXTRA_BOLD, 36.0F, Color.WHITE))))
      .zindex(100)
      .attach(this);
    FlexNode.horizontal(1755.0D, 906.0D, 29.0D)
      .margin(23.0D)
      .align(Align.CENTER)
      .body(flex -> {
          TextNode.create(0.0D, 0.0D).text(Text.create("ouvertures restantes".toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 20.0F, Color.WHITE))).attach(flex);
          Text text = Text.create(String.valueOf(((Integer)this.spinCountSignal.getOrDefault()).intValue() + ((Integer)this.freeSpinSpignal.getOrDefault()).intValue() - ((Integer)this.spinSignal.getOrDefault()).intValue()), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 20.0F, Color.WHITE));
          RectNode.create(0.0D, 0.0D, Math.max(text.aw(16.0D), flex.h()), flex.h()).color(ColorConstant.PRIMARY).effect((NodeEffect)RoundedNodeEffect.create(6.0F)).body(()).attach(flex);
        }).anchorX(Align.END)
      .zindex(100)
      .watch((Signal)this.spinSignal, this::exists, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.spinCountSignal, this::exists, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.freeSpinSpignal, this::exists, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    this.scheduledTasks.forEach(Runnable::run);
    this.scheduledTasks.clear();
    this.wheelAnimator.update();
    this.boostAnimator.update();
    this.transitionAnimator.update();
    this.claimedRewardGlowAnimator.update();
    this.claimedRewardMoveAnimator.update();
    this.claimedRewardSlideAnimator.update();
    this.claimedRewardAppearAnimator.update();
    if (this.transitionAnimator.getValue() > 0.0F) {
      GL11.glTranslated(960.0D, 540.0D, 0.0D);
      GL11.glScaled(1.0D, (1.0F - this.transitionAnimator.getValue()), 0.0D);
      GL11.glTranslated(-960.0D, -540.0D, 0.0D);
    } 
    if (!this.opening) {
      DrawUtils.RESOURCE.drawImage(0.0D, 0.0D, 1920.0D, 1080.0D, this.box.getBoxData().getResource().getWaitingOverlay().getBackgroundResource());
      return;
    } 
    DrawUtils.RESOURCE.drawImage(0.0D, 0.0D, 1920.0D, 1080.0D, this.box.getBoxData().getResource().getOpeningOverlay().getBackgroundResource());
  }
  
  public void postDraw(double mouseX, double mouseY, float ticks) {
    if (!this.opening) {
      DrawUtils.RESOURCE.drawImage(0.0D, 0.0D, 1920.0D, 1080.0D, this.box.getBoxData().getResource().getWaitingOverlay().getForegroundResource());
    } else {
      DrawUtils.RESOURCE.drawImage(0.0D, 0.0D, 1920.0D, 1080.0D, this.box.getBoxData().getResource().getOpeningOverlay().getForegroundResource());
      if (!this.state.isWaiting() && !this.state.isFast())
        DrawUtils.RESOURCE.drawImage(0.0D, 0.0D, 1920.0D, 1080.0D, this.box.getBoxData().getResource().getOpeningOverlay().getCursorResource()); 
    } 
  }
  
  public void update() {
    ItemStack keyItem = new ItemStack(this.box.getBoxData().getItem());
    if (keyItem != null && keyItem.func_77973_b() != null && (Minecraft.func_71410_x()).field_71439_g != null) {
      int count = InventoryUtils.getItemCount((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g, keyItem);
      this.keyCountSignal.set(Integer.valueOf(count));
    } 
    if (this.state.isActive() && !this.state.isWaiting() && System.currentTimeMillis() - this.lastKeepAlive >= 5000L) {
      (new CSPacketBoxKeepAlive(this.box.func_145782_y())).send();
      this.lastKeepAlive = System.currentTimeMillis();
    } 
  }
  
  public boolean close() {
    this.state.stop();
    Minecraft.func_71410_x().func_147118_V().func_147690_c();
    return true;
  }
  
  public void playSound(@NonNull ResourceLocation location) {
    if (location == null)
      throw new NullPointerException("location is marked non-null but is null"); 
    playSound(location, 1.5F);
  }
  
  public void playSound(@NonNull ResourceLocation location, float volume) {
    if (location == null)
      throw new NullPointerException("location is marked non-null but is null"); 
    this.scheduledTasks.add(() -> {
          EntityLivingBase entityLivingBase = (Minecraft.func_71410_x()).field_71451_h;
          Predicate<SoundRecord> predicate = ();
          if (!predicate.test(null))
            return; 
          SoundRecord.create(location).position((float)this.box.field_70165_t, (float)this.box.field_70163_u, (float)this.box.field_70161_v).attenuation(ISound.AttenuationType.LINEAR).volume(volume).build().play().condition(predicate);
        });
  }
  
  public void stopSounds() {
    Minecraft.func_71410_x().func_147118_V().func_147690_c();
  }
  
  private boolean exists() {
    return (this.box != null && this.box.func_70089_S());
  }
  
  private BoxReward getReward(int spin, int reward) {
    return ((List<BoxReward>)this.state.getRewards().get(spin)).get(reward);
  }
  
  private Random getRandom(int spin, int reward) {
    Random random = new Random(this.randomSeed + (new Random(spin)).nextLong() + (new Random(reward)).nextLong());
    random.nextInt();
    return random;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\client\overlay\UIShopBoxOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */