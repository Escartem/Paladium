package fr.paladium.palamod.modules.luckyblock.gui.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketAlarmClock;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityAlarmClock;
import net.minecraft.util.ResourceLocation;

public class UIAlarmClock extends UI {
  private static final ResourceLocation BACKGROUND = new ResourceLocation("palamod", "textures/gui/LuckyBlock/june/alarmclock_bg.png");
  
  private static final float BG_WIDTH = 46.666668F;
  
  private static final float BG_HEIGHT = 82.96296F;
  
  private final TileEntityAlarmClock tileEntityAlarmClock;
  
  public UIAlarmClock(TileEntityAlarmClock tileEntityAlarmClock) {
    super(null, "palamod:ui_alarmclock");
    this.tileEntityAlarmClock = tileEntityAlarmClock;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    addNode((ANode)new MinecraftCloseNode(width(69.333336F), height(13.01852F), width(1.5F), height(2.8F)));
    float nodeWidth = width(31.383335F);
    addNode((new UIAlarmClockButtonNode(true, nodeWidth, height(39.98148F), width(UIAlarmClockButtonNode.BUTTON_WIDTH), height(UIAlarmClockButtonNode.BUTTON_HEIGHT))).setCallback(node -> {
            if (this.tileEntityAlarmClock.getHourTens() == 2) {
              this.tileEntityAlarmClock.setHourTens(0);
            } else {
              this.tileEntityAlarmClock.setHourTens(this.tileEntityAlarmClock.getHourTens() + 1);
            } 
            if (this.tileEntityAlarmClock.getHourTens() == 2 && this.tileEntityAlarmClock.getHourOnes() > 3)
              this.tileEntityAlarmClock.setHourOnes(0); 
            PalaMod.getNetwork().sendToServer((IMessage)new PacketAlarmClock(this.tileEntityAlarmClock.field_145851_c, this.tileEntityAlarmClock.field_145848_d, this.tileEntityAlarmClock.field_145849_e, this.tileEntityAlarmClock.getHourTens(), this.tileEntityAlarmClock.getHourOnes(), this.tileEntityAlarmClock.getMinuteTens(), this.tileEntityAlarmClock.getMinuteOnes()));
            this.field_146297_k.field_71439_g.func_85030_a("random.click", 0.5F, 3.0F);
          }));
    addNode((new UIAlarmClockButtonNode(false, nodeWidth, height(64.88148F), width(UIAlarmClockButtonNode.BUTTON_WIDTH), height(UIAlarmClockButtonNode.BUTTON_HEIGHT))).setCallback(node -> {
            if (this.tileEntityAlarmClock.getHourTens() == 0) {
              this.tileEntityAlarmClock.setHourTens(2);
            } else {
              this.tileEntityAlarmClock.setHourTens(this.tileEntityAlarmClock.getHourTens() - 1);
            } 
            PalaMod.getNetwork().sendToServer((IMessage)new PacketAlarmClock(this.tileEntityAlarmClock.field_145851_c, this.tileEntityAlarmClock.field_145848_d, this.tileEntityAlarmClock.field_145849_e, this.tileEntityAlarmClock.getHourTens(), this.tileEntityAlarmClock.getHourOnes(), this.tileEntityAlarmClock.getMinuteTens(), this.tileEntityAlarmClock.getMinuteOnes()));
            this.field_146297_k.field_71439_g.func_85030_a("random.click", 0.5F, 3.0F);
          }));
    nodeWidth = width(40.033333F);
    addNode((new UIAlarmClockButtonNode(true, nodeWidth, height(39.98148F), width(UIAlarmClockButtonNode.BUTTON_WIDTH), height(UIAlarmClockButtonNode.BUTTON_HEIGHT))).setCallback(node -> {
            if ((this.tileEntityAlarmClock.getHourTens() == 2 && this.tileEntityAlarmClock.getHourOnes() == 3) || this.tileEntityAlarmClock.getHourOnes() == 9) {
              this.tileEntityAlarmClock.setHourOnes(0);
            } else {
              this.tileEntityAlarmClock.setHourOnes(this.tileEntityAlarmClock.getHourOnes() + 1);
            } 
            PalaMod.getNetwork().sendToServer((IMessage)new PacketAlarmClock(this.tileEntityAlarmClock.field_145851_c, this.tileEntityAlarmClock.field_145848_d, this.tileEntityAlarmClock.field_145849_e, this.tileEntityAlarmClock.getHourTens(), this.tileEntityAlarmClock.getHourOnes(), this.tileEntityAlarmClock.getMinuteTens(), this.tileEntityAlarmClock.getMinuteOnes()));
            this.field_146297_k.field_71439_g.func_85030_a("random.click", 0.5F, 3.0F);
          }));
    addNode((new UIAlarmClockButtonNode(false, nodeWidth, height(64.88148F), width(UIAlarmClockButtonNode.BUTTON_WIDTH), height(UIAlarmClockButtonNode.BUTTON_HEIGHT))).setCallback(node -> {
            if (this.tileEntityAlarmClock.getHourOnes() == 0) {
              if (this.tileEntityAlarmClock.getHourTens() == 2) {
                this.tileEntityAlarmClock.setHourOnes(3);
              } else {
                this.tileEntityAlarmClock.setHourOnes(9);
              } 
            } else {
              this.tileEntityAlarmClock.setHourOnes(this.tileEntityAlarmClock.getHourOnes() - 1);
            } 
            PalaMod.getNetwork().sendToServer((IMessage)new PacketAlarmClock(this.tileEntityAlarmClock.field_145851_c, this.tileEntityAlarmClock.field_145848_d, this.tileEntityAlarmClock.field_145849_e, this.tileEntityAlarmClock.getHourTens(), this.tileEntityAlarmClock.getHourOnes(), this.tileEntityAlarmClock.getMinuteTens(), this.tileEntityAlarmClock.getMinuteOnes()));
            this.field_146297_k.field_71439_g.func_85030_a("random.click", 0.5F, 3.0F);
          }));
    nodeWidth = width(51.88333F);
    addNode((new UIAlarmClockButtonNode(true, nodeWidth, height(39.98148F), width(UIAlarmClockButtonNode.BUTTON_WIDTH), height(UIAlarmClockButtonNode.BUTTON_HEIGHT))).setCallback(node -> {
            if (this.tileEntityAlarmClock.getMinuteTens() == 5) {
              this.tileEntityAlarmClock.setMinuteTens(0);
            } else {
              this.tileEntityAlarmClock.setMinuteTens(this.tileEntityAlarmClock.getMinuteTens() + 1);
            } 
            PalaMod.getNetwork().sendToServer((IMessage)new PacketAlarmClock(this.tileEntityAlarmClock.field_145851_c, this.tileEntityAlarmClock.field_145848_d, this.tileEntityAlarmClock.field_145849_e, this.tileEntityAlarmClock.getHourTens(), this.tileEntityAlarmClock.getHourOnes(), this.tileEntityAlarmClock.getMinuteTens(), this.tileEntityAlarmClock.getMinuteOnes()));
            this.field_146297_k.field_71439_g.func_85030_a("random.click", 0.5F, 3.0F);
          }));
    addNode((new UIAlarmClockButtonNode(false, nodeWidth, height(64.88148F), width(UIAlarmClockButtonNode.BUTTON_WIDTH), height(UIAlarmClockButtonNode.BUTTON_HEIGHT))).setCallback(node -> {
            if (this.tileEntityAlarmClock.getMinuteTens() == 0) {
              this.tileEntityAlarmClock.setMinuteTens(5);
            } else {
              this.tileEntityAlarmClock.setMinuteTens(this.tileEntityAlarmClock.getMinuteTens() - 1);
            } 
            PalaMod.getNetwork().sendToServer((IMessage)new PacketAlarmClock(this.tileEntityAlarmClock.field_145851_c, this.tileEntityAlarmClock.field_145848_d, this.tileEntityAlarmClock.field_145849_e, this.tileEntityAlarmClock.getHourTens(), this.tileEntityAlarmClock.getHourOnes(), this.tileEntityAlarmClock.getMinuteTens(), this.tileEntityAlarmClock.getMinuteOnes()));
            this.field_146297_k.field_71439_g.func_85030_a("random.click", 0.5F, 3.0F);
          }));
    nodeWidth = width(60.533333F);
    addNode((new UIAlarmClockButtonNode(true, nodeWidth, height(39.98148F), width(UIAlarmClockButtonNode.BUTTON_WIDTH), height(UIAlarmClockButtonNode.BUTTON_HEIGHT))).setCallback(node -> {
            if (this.tileEntityAlarmClock.getMinuteOnes() == 9) {
              this.tileEntityAlarmClock.setMinuteOnes(0);
            } else {
              this.tileEntityAlarmClock.setMinuteOnes(this.tileEntityAlarmClock.getMinuteOnes() + 1);
            } 
            PalaMod.getNetwork().sendToServer((IMessage)new PacketAlarmClock(this.tileEntityAlarmClock.field_145851_c, this.tileEntityAlarmClock.field_145848_d, this.tileEntityAlarmClock.field_145849_e, this.tileEntityAlarmClock.getHourTens(), this.tileEntityAlarmClock.getHourOnes(), this.tileEntityAlarmClock.getMinuteTens(), this.tileEntityAlarmClock.getMinuteOnes()));
            this.field_146297_k.field_71439_g.func_85030_a("random.click", 0.5F, 3.0F);
          }));
    addNode((new UIAlarmClockButtonNode(false, nodeWidth, height(64.88148F), width(UIAlarmClockButtonNode.BUTTON_WIDTH), height(UIAlarmClockButtonNode.BUTTON_HEIGHT))).setCallback(node -> {
            if (this.tileEntityAlarmClock.getMinuteOnes() == 0) {
              this.tileEntityAlarmClock.setMinuteOnes(9);
            } else {
              this.tileEntityAlarmClock.setMinuteOnes(this.tileEntityAlarmClock.getMinuteOnes() - 1);
            } 
            PalaMod.getNetwork().sendToServer((IMessage)new PacketAlarmClock(this.tileEntityAlarmClock.field_145851_c, this.tileEntityAlarmClock.field_145848_d, this.tileEntityAlarmClock.field_145849_e, this.tileEntityAlarmClock.getHourTens(), this.tileEntityAlarmClock.getHourOnes(), this.tileEntityAlarmClock.getMinuteTens(), this.tileEntityAlarmClock.getMinuteOnes()));
            this.field_146297_k.field_71439_g.func_85030_a("random.click", 0.5F, 3.0F);
          }));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    GuiUtils.drawImageTransparent(width(26.666666F), height(8.51852F), BACKGROUND, width(46.666668F), height(82.96296F), false);
    float txtHeight = height(47.98148F);
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, String.valueOf(this.tileEntityAlarmClock.getHourTens()), width(35.533333F), txtHeight, Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 1000);
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, String.valueOf(this.tileEntityAlarmClock.getHourOnes()), width(44.233334F), txtHeight, Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 1000);
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, String.valueOf(this.tileEntityAlarmClock.getMinuteTens()), width(56.033333F), txtHeight, Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 1000);
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, String.valueOf(this.tileEntityAlarmClock.getMinuteOnes()), width(64.73334F), txtHeight, Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 1000);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\june\UIAlarmClock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */