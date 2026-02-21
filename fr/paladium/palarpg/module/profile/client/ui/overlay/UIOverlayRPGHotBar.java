package fr.paladium.palarpg.module.profile.client.ui.overlay;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.dungeon.client.listener.DungeonClientDeathListener;
import fr.paladium.palarpg.module.profile.client.ui.kit.hotbar.ExpGainNode;
import fr.paladium.palarpg.module.profile.client.ui.kit.hotbar.TimedStatsDisplayNode;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import fr.paladium.palarpg.module.profile.server.manager.RPGLevelManager;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.AStatCapability;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatCapabilityMutator;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.TimedStatCapabilityMutator;
import fr.paladium.palavanillagui.client.ui.overlay.UIHotbarOverlay;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.UIDataOverlay;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.render.UIDataOverlayRender;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.MapSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.DoubleSignal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@UIData(background = false, anchorX = Align.CENTER, anchorY = Align.END)
@UIDataOverlay(active = true, render = @UIDataOverlayRender(post = true, type = RenderGameOverlayEvent.ElementType.ALL))
@UIDataGuiscale(active = true, limited = false)
public class UIOverlayRPGHotBar extends UI {
  private final DoubleSignal expGainSignal = new DoubleSignal(0.0D);
  
  public DoubleSignal getExpGainSignal() {
    return this.expGainSignal;
  }
  
  private final TweenAnimator expAnimator = TweenAnimator.create(0.0F);
  
  public TweenAnimator getExpAnimator() {
    return this.expAnimator;
  }
  
  private final MapSignal<EnumStats, List<TimedStatCapabilityMutator<?>>> timedStatSignal = new MapSignal(new HashMap<>());
  
  public MapSignal<EnumStats, List<TimedStatCapabilityMutator<?>>> getTimedStatSignal() {
    return this.timedStatSignal;
  }
  
  private double lastExp = 0.0D;
  
  public double getLastExp() {
    return this.lastExp;
  }
  
  private double lastExpPercentage = 0.0D;
  
  public double getLastExpPercentage() {
    return this.lastExpPercentage;
  }
  
  public void init() {
    ContainerNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .body(container -> {
          FlexNode.horizontal(642.5D, 870.0D, 60.0D).align(Align.END).margin(7.0D).body(()).anchorY(Align.END).watch((Signal)this.timedStatSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(container);
          ExpGainNode.create(1272.5D, 915.0D).watch((Signal)this.expGainSignal).attach(container);
          ImageNode.create(960.0D, 935.0D).resource(Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/profile/hotbar/empty_bar.png")).nearest()).width(635.0D).height(25.0D).onInit(()).anchorX(Align.CENTER).attach(container);
          ImageNode.create(960.0D, 965.0D).resource(Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/profile/hotbar/empty_bar.png")).nearest()).width(635.0D).height(25.0D).onInit(()).anchorX(Align.CENTER).attach(container);
        }).visible(node -> !DungeonClientDeathListener.isDead)
      .attach(this);
  }
  
  public void update() {
    if ((Minecraft.func_71410_x()).field_71439_g == null)
      return; 
    UIHotbarOverlay hotbarOverlay = (UIHotbarOverlay)ZUI.getUI(UIHotbarOverlay.class);
    if (hotbarOverlay != null)
      hotbarOverlay.setToolHightlightOffset((Minecraft.func_71410_x()).field_71439_g.field_71075_bZ.field_75098_d ? -72.0D : 20.0D); 
    RPGStatPlayerData statData = (RPGStatPlayerData)RPGPlayer.getData((Entity)(Minecraft.func_71410_x()).field_71439_g, "stats");
    if (statData != null) {
      Map<EnumStats, List<TimedStatCapabilityMutator<?>>> timedStatMutators = new HashMap<>();
      for (AStatCapability<?> capability : (Iterable<AStatCapability<?>>)statData.getCapabilities()) {
        for (StatCapabilityMutator<?> mutator : (Iterable<StatCapabilityMutator<?>>)capability.getMutators()) {
          if (mutator instanceof TimedStatCapabilityMutator) {
            EnumStats statEnum = EnumStats.fromStatName(capability.getName());
            List<TimedStatCapabilityMutator<?>> timeds = timedStatMutators.getOrDefault(statEnum, new ArrayList<>());
            timeds.add((TimedStatCapabilityMutator)mutator);
            timedStatMutators.put(statEnum, timeds);
          } 
        } 
      } 
      this.timedStatSignal.set(timedStatMutators);
    } 
    if (RPGPlayer.getData((Entity)(Minecraft.func_71410_x()).field_71439_g, "profile") != null) {
      RPGProfilePlayerData profile = (RPGProfilePlayerData)RPGPlayer.getData((Entity)(Minecraft.func_71410_x()).field_71439_g, "profile");
      if (profile.getProgressionPercentage() != this.lastExpPercentage) {
        if (this.lastExpPercentage > profile.getProgressionPercentage())
          this.expAnimator.clear(); 
        this.lastExpPercentage = profile.getProgressionPercentage();
        this.expAnimator.sequence(1000.0F, (float)this.lastExpPercentage, (TweenEquation)TweenEquations.QUART_INOUT).start();
      } 
      double expDiff = profile.getTotalExp() - this.lastExp;
      if (expDiff <= 0.0D)
        return; 
      if (expDiff == profile.getTotalExp()) {
        this.lastExp = profile.getTotalExp();
        return;
      } 
      if (profile.getTotalExp() > this.lastExp) {
        double lastExpDiff = ((Double)this.expGainSignal.getOrDefault()).doubleValue();
        this.expGainSignal.set(Double.valueOf(expDiff));
        if (lastExpDiff == expDiff)
          this.expGainSignal.publish(); 
        this.lastExp = profile.getTotalExp();
      } 
    } 
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    this.expAnimator.update();
  }
  
  public boolean close() {
    UIHotbarOverlay hotbarOverlay = (UIHotbarOverlay)ZUI.getUI(UIHotbarOverlay.class);
    if (hotbarOverlay != null)
      hotbarOverlay.setToolHightlightOffset(0.0D); 
    return super.close();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\clien\\ui\overlay\UIOverlayRPGHotBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */