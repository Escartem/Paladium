package fr.paladium.palarpg.module.dungeon.client.ui.respawn;

import fr.paladium.paladiumui.kit.button.PriceButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.popup.UIYesNoPopup;
import fr.paladium.palaforgeutils.lib.time.UniversalTimeUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundNode;
import fr.paladium.palarpg.module.dungeon.client.ui.utils.DungeonSynchronizedUI;
import fr.paladium.palarpg.module.dungeon.common.network.death.BBPacketRPGDungeonDeathRespawnTeam;
import fr.paladium.palashop.client.ui.impl.pb.UIShopPBPopup;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.opengl.modifier.GLScale;
import fr.paladium.zephyrui.lib.opengl.modifier.GLVector;
import fr.paladium.zephyrui.lib.opengl.transform.glto.GLTOScaling;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.TransformNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.LongSignal;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@UIData(background = false, closeable = false)
public class UIDungeonRespawn extends DungeonSynchronizedUI {
  private final long price;
  
  private final long timeEnd;
  
  private final LongSignal timeLeft;
  
  private final TweenAnimator appearAnimator;
  
  private final TweenAnimator transitionAnimator;
  
  public UIDungeonRespawn(Long price) {
    setMain(UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g));
    this.price = price.longValue();
    this.timeEnd = UniversalTimeUtils.now() + 7000L;
    this.timeLeft = new LongSignal(6L);
    this.appearAnimator = TweenAnimator.create(0.0F);
    this.transitionAnimator = TweenAnimator.create(0.0F);
  }
  
  public void init() {
    super.init();
    this.appearAnimator.setValue(0.0F);
    this.appearAnimator.sequence(1000.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
    ContainerNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .body(container -> {
          ((ImageNode)ImageNode.create(0.0D, 0.0D, container.w(), container.h()).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/respawn/background.png")).linear().blocking()).color(Color.WHITE.copyAlpha(0.0F)).onAnimate(())).animate(this.appearAnimator).attach((UI)this);
          ((BackgroundNode)BackgroundNode.create().borderColor(BackgroundNode.BORDER_COLOR.copyAlpha(0.0F)).backgroundColor(BackgroundNode.BACKGROUND_COLOR.copyAlpha(0.0F)).onAnimate(())).animate(this.transitionAnimator).attach(container);
          ContainerNode.create(0.0D, 66.0D, 1920.0D, 204.0D).body(()).onAnimate(()).animate(this.transitionAnimator).attach(container);
          ((ImageNode)((ImageNode)ImageNode.create(container.dw(2.0D), container.dh(2.0D)).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/respawn/timer/" + this.timeLeft.getOrDefault() + ".png")).linear().blocking()).color(Color.WHITE.copyAlpha(0.0F)).onInit(())).onAnimate(())).animate(this.appearAnimator).animate(this.transitionAnimator).watch((Signal)this.timeLeft).anchor(Align.CENTER).attach(container);
          ((PriceButtonNode)PriceButtonNode.create(769.0D, 821.0D, 383.0D, 57.0D).margin(20.0D).price(this.price).title("revivre l'équipe").description("temps restant " + this.timeLeft.getOrDefault() + "...").titleInfo(TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 15.0F, Color.WHITE).shadow(new Color(0.0F, 0.0F, 0.0F, 0.4F)).shadow(1.0F, 1.0F)).onWatch(())).onClick(()).effect(()).watch((Signal)this.timeLeft).attach(container);
        }).attach((UI)this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(getRelativeX(0.0D), getRelativeY(0.0D), getViewportWidth(), getViewportHeight(), BackgroundNode.BACKGROUND_COLOR.copyAlpha(this.transitionAnimator.getValue()));
    this.timeLeft.set(Long.valueOf(Math.max(0L, Math.min(6L, (this.timeEnd - UniversalTimeUtils.now()) / 1000L))));
    if (((Long)this.timeLeft.getOrDefault()).longValue() <= 0L && this.transitionAnimator.getValue() == 0.0F)
      this.transitionAnimator.sequence(1000.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).start(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\respawn\UIDungeonRespawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */