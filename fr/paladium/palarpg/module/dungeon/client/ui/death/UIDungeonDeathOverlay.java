package fr.paladium.palarpg.module.dungeon.client.ui.death;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import fr.paladium.paladiumui.kit.button.PriceButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.popup.UIYesNoPopup;
import fr.paladium.palaforgeutils.lib.scheduler.FMLClientScheduler;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palarpg.module.dungeon.client.ui.respawn.UIDungeonRespawn;
import fr.paladium.palarpg.module.dungeon.common.network.death.BBPacketRPGDungeonDeathRespawnPersonal;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonGlobalConfig;
import fr.paladium.palarpg.module.dungeon.server.manager.DungeonManager;
import fr.paladium.palashop.client.ui.impl.pb.UIShopPBPopup;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.BaseTween;
import fr.paladium.zephyrui.lib.animation.tweenengine.Timeline;
import fr.paladium.zephyrui.lib.animation.tweenengine.Tween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.UIDataOverlay;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.interaction.UIDataOverlayInteraction;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.render.UIDataOverlayRender;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.LongSignal;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.util.ResourceLocation;

@UIData(background = false)
@UIDataOverlay(active = true, render = @UIDataOverlayRender(post = true, always = true), interaction = @UIDataOverlayInteraction(active = true, cancelClick = false, cancelKeyboard = false, cancelScroll = false))
public class UIDungeonDeathOverlay extends UI {
  private static final ResourceLocation SHADER = new ResourceLocation("shaders/post/desaturate.json");
  
  private final TweenAnimator slideAnimator;
  
  private final TweenAnimator appearAnimator;
  
  private final int remainingPlayers;
  
  private final int remainingRespawns;
  
  private ShaderUniform uniform;
  
  public UIDungeonDeathOverlay() {
    this.slideAnimator = TweenAnimator.create(0.0F);
    this.appearAnimator = TweenAnimator.create(0.0F);
    this.remainingPlayers = ((Integer)DungeonWorld.getClient().map(world -> Integer.valueOf(world.getAlivePlayers().size())).orElse(Integer.valueOf(0))).intValue();
    this.remainingRespawns = ((Integer)DungeonPlayer.getClient().map(DungeonPlayer::getRemainingRespawns).orElse(Integer.valueOf(0))).intValue();
  }
  
  public void init() {
    this.slideAnimator.setValue(0.0F);
    this.appearAnimator.setValue(0.0F);
    this.appearAnimator.sequence(1000.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).setCallback(tween -> {
          if (this.remainingPlayers > 0) {
            Timeline timeline = Timeline.createSequence();
            timeline.pushPause(2000.0F);
            timeline.push(Tween.to(this.slideAnimator, 0, 500.0F).target(1.0F).ease((TweenEquation)TweenEquations.QUART_INOUT));
            this.slideAnimator.setTimeline(timeline);
            this.slideAnimator.start();
          } 
        }).start();
    FMLClientScheduler.getInstance().add(new Runnable[] { () -> {
            if (OpenGlHelper.func_148822_b() && OpenGlHelper.field_148824_g) {
              EntityRenderer entityRenderer = (Minecraft.func_71410_x()).field_71460_t;
              if (entityRenderer.func_147706_e() != null && !entityRenderer.func_147706_e().func_148022_b().equals(SHADER.toString())) {
                entityRenderer.func_147706_e().func_148021_a();
                entityRenderer.field_147707_d = null;
              } 
              if (entityRenderer.func_147706_e() == null) {
                Minecraft mc = Minecraft.func_71410_x();
                try {
                  entityRenderer.field_147707_d = new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), SHADER);
                  entityRenderer.field_147707_d.func_148026_a(mc.field_71443_c, mc.field_71440_d);
                  List<?> listShaders = (List)ObfuscationReflectionHelper.getPrivateValue(ShaderGroup.class, entityRenderer.field_147707_d, new String[] { "listShaders", "field_148031_d" });
                  if (listShaders != null && !listShaders.isEmpty())
                    this.uniform = ((Shader)listShaders.get(0)).func_148043_c().func_147991_a("Saturation"); 
                } catch (Exception e) {
                  e.printStackTrace();
                  if (entityRenderer.func_147706_e() != null)
                    entityRenderer.func_147706_e().func_148021_a(); 
                  entityRenderer.field_147707_d = null;
                } 
              } 
            } 
          } });
    SoundUtils.ENDERDRAGON_GROWL.playClientSound(0.5F, 1.0F);
    ContainerNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .body(container -> {
          ((ImageNode)ImageNode.create(0.0D, 0.0D, 1920.0D, 1080.0D).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/death/background.png")).linear().blocking()).color(Color.WHITE.copyAlpha(0.0F)).onAnimate(())).animate(this.slideAnimator).animate(this.appearAnimator).attach(container);
          ((ImageNode)ImageNode.create(0.0D, 0.0D, 1920.0D, 1080.0D).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/death/foreground.png")).linear().blocking()).color(Color.WHITE.copyAlpha(0.0F)).onAnimate(())).animate(this.slideAnimator).attach(container);
          ((TextNode)TextNode.create(960.0D, 438.0D).text(Text.create("défaite", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 36.0F, Color.decode("#E13429").copyAlpha(0.0F)))).onAnimate(())).anchorX(Align.CENTER).animate(this.slideAnimator).animate(this.appearAnimator).attach(container);
          FlexNode.horizontal(960.0D, 490.0D, 150.0D).margin(52.0D).align(Align.CENTER).body(()).onAnimate(()).anchorX(Align.CENTER).animate(this.slideAnimator).attach(container);
          LongSignal priceSignal = LongSignal.of(-1L);
          DungeonManager.getGlobalConfig().thenAccept(());
          ImageNode.create(1920.0D, 888.0D, 313.0D, 153.0D).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/death/respawn.png")).nearest()).body(()).onAnimate(()).visible(()).animate(this.slideAnimator).attach(container);
        }).visible(n -> !ZUI.isOpen(UIDungeonRespawn.class))
      .attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    this.slideAnimator.update();
    this.appearAnimator.update();
    if (this.uniform != null)
      if (this.slideAnimator.getValue() > 0.0F) {
        this.uniform.func_148090_a(Math.max(0.0F, this.slideAnimator.getValue() * 0.42F));
      } else {
        this.uniform.func_148090_a(Math.max(0.0F, 1.0F - this.appearAnimator.getValue()));
      }  
  }
  
  public boolean close() {
    if (OpenGlHelper.field_148824_g) {
      EntityRenderer entityRenderer = (Minecraft.func_71410_x()).field_71460_t;
      if (entityRenderer.func_147706_e() != null && entityRenderer.func_147706_e().func_148022_b().equals(SHADER.toString())) {
        entityRenderer.func_147706_e().func_148021_a();
        entityRenderer.field_147707_d = null;
      } 
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\death\UIDungeonDeathOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */