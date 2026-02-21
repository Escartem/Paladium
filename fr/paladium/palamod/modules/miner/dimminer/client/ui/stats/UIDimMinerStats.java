package fr.paladium.palamod.modules.miner.dimminer.client.ui.stats;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.progress.ProgressNode;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.modules.miner.dimminer.common.data.DimMinerPlayer;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
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
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.FloatSignal;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.vecmath.Vector4f;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@UIData(background = false)
public class UIDimMinerStats extends UI {
  private final DimMinerPlayer player;
  
  private TweenAnimator animator;
  
  private FloatSignal signal;
  
  public UIDimMinerStats(@NonNull DimMinerPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    this.player = player;
  }
  
  public void init() {
    if (this.player == null) {
      ZUI.close(this);
      return;
    } 
    this.animator = TweenAnimator.create(0.0F).sequence(1500.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
    this.signal = new FloatSignal(this.animator.getValue());
    ImageNode.create(926.0D, 75.0D, 69.0D, 69.0D)
      .resource(Resource.of(new ResourceLocation("palamod", "textures/gui/miner/stats/icon.png")))
      .attach(this);
    TextNode.create(960.0D, 144.0D)
      .text(Text.create("session finie", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 48.0F, Color.WHITE).shadow().shadow(0.0F, 4.0F)))
      .anchorX(Align.CENTER)
      .attach(this);
    TextNode.create(960.0D, 273.0D)
      .text(Text.create("résumé de votre session", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach(this);
    BackgroundNode.create(148.0D, 338.0D, 548.0D, 404.0D)
      .radius(4.0D, 10.0D)
      .body(container -> {
          JobType jobType = JobType.MINER;
          JobsPlayer jobsPlayer = JobsPlayer.get((Entity)(Minecraft.func_71410_x()).field_71439_g);
          int level = jobsPlayer.getLevel(jobType);
          double experience = jobsPlayer.getExperience(jobType) - JobExpUtils.getNeededXpForLvl(level);
          double nextLevelExperience = (JobExpUtils.getNeededXpForLvl(level + 1) - JobExpUtils.getNeededXpForLvl(level));
          if (experience > nextLevelExperience)
            experience = nextLevelExperience; 
          ImageNode.create(12.0D, 12.0D, 50.0D, 50.0D).resource(Resource.of(new ResourceLocation("palamod", "textures/gui/miner/stats/double_xp.png")).nearest()).attach(container);
          TextNode.create(80.0D, 37.0D).text(Text.create("Récap. Métier mineur", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, new Color(134, 134, 134)))).anchorY(Align.CENTER).attach(container);
          ImageNode.create(container.dw(2.0D) - 116.0D, container.dh(2.0D) - 116.0D, 233.0D, 233.0D).resource(Resource.of(new ResourceLocation("palamod", "textures/gui/miner/stats/job_miner.png")).linear()).attach(container);
          TextNode.create(container.dw(2.0D) - 235.0D, 360.0D).text(Text.create("lvl ", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 16.0F, Color.WHITE).shadow().shadow(0.0F, 2.0F)).add(Text.create(Integer.valueOf(level), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 22.0F, Color.WHITE).shadow().shadow(0.0F, 2.0F).lineHeight(-2.0F))).verticalAlign(Align.END)).anchorY(Align.END).attach(container);
          TextNode.create(container.dw(2.0D) + 235.0D, 342.0D).text(Text.create("+" + String.format("%,.0f", new Object[] { Double.valueOf(jobsPlayer.getExperience(jobType) - this.player.getJobExperience()) }).replace(",", ".").replace(" ", ".") + " xp", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 7.0F, ColorConstant.GREEN)).verticalAlign(Align.END)).anchor(Align.END).attach(container);
          TextNode.create(container.dw(2.0D) + 235.0D, 360.0D).text(Text.create((level == Integer.MAX_VALUE) ? "niveau max" : String.format("%,.0f/%,.0f", new Object[] { Double.valueOf(experience), Double.valueOf(nextLevelExperience) }).replace(",", ".").replace(" ", "."), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 10.0F, Color.WHITE)).verticalAlign(Align.END)).anchor(Align.END).attach(container);
          ProgressNode.create(container.dw(2.0D) - 235.0D, 370.0D, 470.0D, 15.0D).padding(4.0D).color(ColorConstant.PRIMARY).progress((level == Integer.MAX_VALUE) ? 1.0F : (float)(experience / nextLevelExperience)).attach(container);
        }).attach(this);
    BackgroundNode.create(741.0D, 338.0D, 1032.0D, 211.0D)
      .radius(4.0D, 10.0D)
      .body(container -> {
          ImageNode.create(12.0D, 12.0D, 50.0D, 50.0D).resource(Resource.of(new ResourceLocation("palamod", "textures/gui/miner/stats/ore.png")).nearest()).attach(container);
          TextNode.create(80.0D, 37.0D).text(Text.create("Minerais minés", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, new Color(134, 134, 134)))).anchorY(Align.CENTER).attach(container);
          FlexNode.horizontal(12.0D, 86.0D, 98.0D).align(Align.CENTER).margin(31.0D).body(()).attach(container);
        }).attach(this);
    BackgroundNode.create(741.0D, 594.0D, 1032.0D, 148.0D)
      .radius(4.0D, 10.0D)
      .body(container -> {
          ImageNode.create(12.0D, container.dh(2.0D) - 25.0D, 50.0D, 50.0D).resource(Resource.of(new ResourceLocation("palamod", "textures/gui/miner/stats/time_played.png")).nearest()).attach(container);
          TextNode.create(80.0D, container.dh(2.0D)).text(Text.create("Temps passé dans la dimension", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, new Color(134, 134, 134)))).anchorY(Align.CENTER).attach(container);
          long timePlayed = Math.max(0L, this.player.getTimePlayed() * 60L);
          long days = TimeUnit.SECONDS.toDays(timePlayed);
          long hours = TimeUnit.SECONDS.toHours(timePlayed) % 24L;
          long minutes = TimeUnit.SECONDS.toMinutes(timePlayed) % 60L;
          long seconds = TimeUnit.SECONDS.toSeconds(timePlayed) % 60L;
          String time = String.format("%02d:%02d:%02d:%02d", new Object[] { Long.valueOf(days), Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds) });
          TextNode.create(container.aw(-15.0D), container.dh(2.0D)).text(Text.create(time, TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 43.0F, Color.WHITE).shadow().shadow(0.0F, 4.0F))).anchor(Align.END, Align.CENTER).attach(container);
        }).attach(this);
    BackgroundNode.create(148.0D, 787.0D, 1625.0D, 138.0D)
      .radius(4.0D, 10.0D)
      .body(container -> {
          ImageNode.create(12.0D, container.dh(2.0D) - 25.0D, 50.0D, 50.0D).resource(Resource.of(new ResourceLocation("palamod", "textures/gui/miner/stats/block.png")).nearest()).attach(container);
          TextNode.create(80.0D, container.dh(2.0D)).text(Text.create("Nombre de blocks minés au total", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, new Color(134, 134, 134)))).anchorY(Align.CENTER).attach(container);
          TextNode.create(container.aw(-15.0D), container.dh(2.0D)).text(Text.create(String.format("%,d", new Object[] { Long.valueOf(this.player.getMinedBlocks()) }).replace(",", ".").replace(" ", "."), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 43.0F, new Color(203, 53, 68)).shadow().shadow(0.0F, 4.0F))).anchor(Align.END, Align.CENTER).attach(container);
        }).attach(this);
  }
  
  public void drawBackground(double mouseX, double mouseY, float ticks) {
    Color color = new Color(30, 30, 35);
    ScaledResolution scaledResolution = new ScaledResolution(Minecraft.func_71410_x(), (Minecraft.func_71410_x()).field_71443_c, (Minecraft.func_71410_x()).field_71440_d);
    DrawUtils.SHAPE.drawRect(0.0D, 0.0D, scaledResolution.func_78327_c(), scaledResolution.func_78324_d(), color.copyAlpha(0.81F).toGradient(color, new Vector4f(0.5F, 1.0F, 0.5F, 0.0F)));
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    this.animator.update();
    this.signal.set(Float.valueOf(this.animator.getValue()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\clien\\ui\stats\UIDimMinerStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */