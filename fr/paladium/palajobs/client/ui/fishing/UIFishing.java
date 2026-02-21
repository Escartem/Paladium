package fr.paladium.palajobs.client.ui.fishing;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.animation.AnimatedObject;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.label.MinecraftTitleNodeLabel;
import fr.paladium.lib.apollon.nodes.progressbar.MinecraftProgressBarNode;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.lib.aurelienribon.tweenengine.BaseTween;
import fr.paladium.lib.aurelienribon.tweenengine.TweenEquation;
import fr.paladium.lib.aurelienribon.tweenengine.TweenEquations;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.core.fishing.FishingCategory;
import fr.paladium.palajobs.core.fishing.FishingSection;
import fr.paladium.palajobs.core.fishing.FishingSectionType;
import fr.paladium.palajobs.core.packets.client.CSPacketFishingInteraction;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

public class UIFishing extends UI {
  private static final ResourceLocation SPACEBAR_TEXTURE = new ResourceLocation("palajobs", "textures/gui/fishing/space.png");
  
  private static final ResourceLocation SPACEBAR_PRESSED_TEXTURE = new ResourceLocation("palajobs", "textures/gui/fishing/space_pressed.png");
  
  private final FishingCategory category;
  
  private long lastCursorUpdate;
  
  private AnimatedObject cursorValue;
  
  private long end;
  
  private double sectionBaseX;
  
  public UIFishing(FishingCategory category) {
    this.category = category;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    if (this.cursorValue == null) {
      this.field_146297_k.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation(SoundUtils.NOTE_PIANO.getSoundName()), 0.5F));
      this.cursorValue = new AnimatedObject();
      this.cursorValue.setValue((float)Math.random());
      this.cursorValue.createSequence()
        .push((float)this.category.getDuration() * (1.0F - this.cursorValue.getValue()), 1.0F, (TweenEquation)TweenEquations.easeNone)
        .push((float)this.category.getDuration(), 0.0F, (TweenEquation)TweenEquations.easeNone)
        .push((float)this.category.getDuration(), 1.0F, (TweenEquation)TweenEquations.easeNone)
        .push((float)this.category.getDuration(), 0.0F, (TweenEquation)TweenEquations.easeNone)
        .push((float)this.category.getDuration(), 1.0F, (TweenEquation)TweenEquations.easeNone)
        .start();
      this.cursorValue.getTimeline().addCallback(4, base -> {
            this.field_146297_k.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation(SoundUtils.ITEM_BREAK.getSoundName()), 5.0F));
            this.field_146297_k.func_147108_a(null);
          });
      this.lastCursorUpdate = System.currentTimeMillis();
      this.end = System.currentTimeMillis() + this.category.getDuration() * 4L + (long)((float)this.category.getDuration() * (1.0F - this.cursorValue.getValue()));
    } 
    addNode((ANode)new MinecraftTitleNodeLabel(width(23.0F), height(27.59F), "pêche"));
    double barX = width(23.0F);
    double barY = height(53.33F);
    double barWidth = width(53.0F);
    addNode((ANode)new MinecraftProgressBarNode(barX, barY, barWidth, Color.WHITE));
    double sectionTotalWidth = 0.0D;
    for (FishingSection section : this.category.getSections())
      sectionTotalWidth += barWidth * (section.getPercentage() / 100.0F); 
    this.sectionBaseX = barX + (barWidth - sectionTotalWidth) * Math.random();
    double sectionOffsetX = 0.0D;
    for (FishingSection section : this.category.getSections()) {
      double sectionWidth = barWidth * (section.getPercentage() / 100.0F);
      addNode((ANode)(new MinecraftProgressBarNode(this.sectionBaseX + sectionOffsetX, barY, sectionWidth, section.getColor())).setMin(0.0F).setMax(1.0F).setValue(1.0F));
      sectionOffsetX += sectionWidth;
    } 
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    BackgroundHelper.createMinecraft(width(58.54F), height(50.0F), true);
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    double barX = width(23.0F);
    double barY = height(53.33F);
    double barWidth = width(53.0F);
    double strWidth = GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, "poisson " + this.category.getName(), width(50.0F), height(40.18F), Color.WHITE, Fonts.PIXEL_NES.getFont(), 140);
    ResourceLocation texture = new ResourceLocation("palajobs", "textures/gui/fishing/icons/" + this.category.getRarity() + ".png");
    GuiUtils.drawImageTransparent(width(50.0F) - strWidth / 2.0D - width(2.0F) - width(2.24F), height(39.6F), texture, width(2.24F), width(2.24F), false);
    GuiUtils.drawImageTransparent(width(50.0F) + strWidth / 2.0D + width(2.0F), height(39.6F), texture, width(2.24F), width(2.24F), false);
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, "appuie sur     au bon moment", width(50.0F), height(46.0F), Color.decode("#41414E"), Fonts.PIXEL_NES.getFont(), 20);
    ResourceLocation spacebar = (this.field_146297_k.field_71439_g.field_70173_aa % 20 < 10) ? SPACEBAR_TEXTURE : SPACEBAR_PRESSED_TEXTURE;
    GuiUtils.drawImageTransparent(width(47.8F), height(45.9F), spacebar, width(2.44F), height(1.48F), false);
    float timeRemaining = Math.max(0.0F, (float)(this.end - System.currentTimeMillis()) / 1000.0F);
    GuiUtils.drawSplittedString(this.field_146297_k, String.format("%.1f", new Object[] { Float.valueOf(timeRemaining) }), barX + barWidth, height(60.0F), Color.decode("#41414E"), Fonts.PIXEL_NES.getFont(), 20, this.field_146294_l, TextAlign.RIGHT);
    List<FishingSection> showedSections = new ArrayList<>();
    for (int i = 0; i < this.category.getSections().size(); i++) {
      FishingSection section = this.category.getSections().get(i);
      if (!showedSections.contains(section)) {
        double ox = barX + (width(25.0F) * (i / 2));
        double oy = (height(65.0F) + height(3.5F) * (i % 2));
        GuiUtils.drawRect(ox, oy, ox + width(1.0F), oy + width(1.0F), section.getColor());
        ox += width(1.5F);
        ox += GuiUtils.drawStringWithCustomFont(this.field_146297_k, (section.getType() == FishingSectionType.MULTIPLIER) ? ("x" + ((Float)section.getValue()).intValue()) : "LVL-UP", ox, oy + height(0.2F), Color.WHITE, Fonts.PIXEL_NES.getFont(), 40);
        ox += width(0.5F);
        GuiUtils.drawStringWithCustomFont(this.field_146297_k, (section.getType() == FishingSectionType.MULTIPLIER) ? ("recevez " + ((Float)section.getValue()).intValue() + " poisson" + ((((Float)section.getValue()).intValue() > 1) ? "s" : "")).toUpperCase() : "essayez un poisson de tier supérieur".toUpperCase(), ox, oy + height(0.5F), Color.WHITE, Fonts.MONTSERRAT_MEDIUM.getFont(), 0);
        showedSections.add(section);
      } 
    } 
    long now = System.currentTimeMillis();
    this.cursorValue.getTimeline().update((float)(now - this.lastCursorUpdate));
    this.lastCursorUpdate = now;
    double cursorX = barX + barWidth * this.cursorValue.getValue();
    double cursorY = barY + height(2.87F);
    double cursorWidth = width(0.4F);
    double cursorHeight = height(8.0F);
    GuiUtils.drawRect(cursorX - cursorWidth / 2.0D, cursorY - cursorHeight / 2.0D, cursorX + cursorWidth / 2.0D, cursorY + cursorHeight / 2.0D, Color.WHITE.darker(0.5F));
    GuiUtils.drawRect(cursorX - cursorWidth / 2.0D, cursorY - cursorHeight / 2.0D, cursorX + cursorWidth / 2.0D, cursorY - height(0.8F) + cursorHeight / 2.0D, Color.WHITE);
  }
  
  protected void func_73869_a(char c, int keyCode) {
    if (keyCode == 57) {
      float value = this.cursorValue.getValue();
      double barX = width(23.0F);
      double barWidth = width(53.0F);
      double cursorX = barX + barWidth * value;
      FishingSection focusedSection = null;
      double sectionOffsetX = 0.0D;
      for (FishingSection section : this.category.getSections()) {
        double sectionWidth = barWidth * (section.getPercentage() / 100.0F);
        double sectionX = this.sectionBaseX + sectionOffsetX;
        if (cursorX > sectionX && cursorX < sectionX + sectionWidth) {
          focusedSection = section;
          break;
        } 
        sectionOffsetX += sectionWidth;
      } 
      if (focusedSection != null) {
        this.field_146297_k.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("random.orb"), 0.5F));
        PalaJobs.proxy.getNetwork().sendToServer((IMessage)new CSPacketFishingInteraction(this.category.getSections().indexOf(focusedSection)));
        this.field_146297_k.func_147108_a(null);
      } else {
        this.field_146297_k.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation(SoundUtils.ITEM_BREAK.getSoundName()), 5.0F));
        this.field_146297_k.func_147108_a(null);
      } 
    } 
    if (keyCode == 1)
      this.field_146297_k.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation(SoundUtils.ITEM_BREAK.getSoundName()), 5.0F)); 
    super.func_73869_a(c, keyCode);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\fishing\UIFishing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */