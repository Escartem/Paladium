package fr.paladium.palamod.modules.paladium.common.items.boost.hud;

import fr.paladium.helios.client.utils.DrawingContext;
import fr.paladium.helios.module.utils.abstracts.AWidget;
import fr.paladium.helios.module.utils.annotations.Widget;
import fr.paladium.lib.apollon.fontV2.FontObj;
import fr.paladium.lib.apollon.fontV2.FontRenderer;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Widget
public class WidgetBoost extends AWidget {
  public boolean canDraw(DrawingContext arg0) {
    return (ModuleBoost.getInstance().getBoostDoubleXp() > System.currentTimeMillis() || 
      ModuleBoost.getInstance().getBoostMinerFou() > System.currentTimeMillis());
  }
  
  public void draw(DrawingContext context) {
    int fontHeight = GuiUtils.getFontHeight(context.getMinecraft(), Fonts.MONTSERRAT_BOLD.getFont(), 70);
    long durationDoubleXp = ModuleBoost.getInstance().getBoostDoubleXp() - System.currentTimeMillis();
    if (durationDoubleXp > 0L) {
      GuiUtils.renderScaledItemStackIntoGUI(new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION), getX(), getY() + height(-20.0D), (float)width(0.8999999761581421D));
      GuiUtils.drawStringWithCustomFont(context.getMinecraft(), convertSecondsToHMmSs(durationDoubleXp / 1000L), getX() + width(20.0D), getY() + height(0.0D), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), fontHeight);
    } 
    long durationMiner = ModuleBoost.getInstance().getBoostMinerFou() - System.currentTimeMillis();
    if (durationMiner > 0L) {
      GuiUtils.renderScaledItemStackIntoGUI(new ItemStack((Item)ItemsRegister.POTION_MINER_FOU), getX(), getY() + height(60.0D), (float)width(1.0D));
      GuiUtils.drawStringWithCustomFont(context.getMinecraft(), convertSecondsToHMmSs(durationMiner / 1000L), getX() + width(20.0D), getY() + height(85.0D), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), fontHeight);
    } 
  }
  
  public void drawEdit(DrawingContext context) {
    int fontHeight = GuiUtils.getFontHeight(context.getMinecraft(), Fonts.MONTSERRAT_BOLD.getFont(), 70);
    long durationDoubleXp = 3600000L;
    if (durationDoubleXp > 0L) {
      GuiUtils.renderScaledItemStackIntoGUI(new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION), getX(), getY() + height(-20.0D), (float)width(0.8999999761581421D));
      GuiUtils.drawStringWithCustomFont(context.getMinecraft(), convertSecondsToHMmSs(durationDoubleXp / 1000L), getX() + width(20.0D), getY() + height(0.0D), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), fontHeight);
    } 
    long durationMiner = 86400000L;
    if (durationMiner > 0L) {
      GuiUtils.renderScaledItemStackIntoGUI(new ItemStack((Item)ItemsRegister.POTION_MINER_FOU), getX(), getY() + height(60.0D), (float)width(1.0D));
      GuiUtils.drawStringWithCustomFont(context.getMinecraft(), convertSecondsToHMmSs(durationMiner / 1000L), getX() + width(20.0D), getY() + height(85.0D), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), fontHeight);
    } 
  }
  
  public void init(DrawingContext arg0) {
    setBoundingBox(1.0D, 50.0D, 8.0D, 3.0D);
  }
  
  private void drawCenteredStringWithShadow(Minecraft mc, String text, double x, double y, Color color, FontObj font, int fontSize) {
    float scaleFactor = Math.min(mc.field_71443_c, 1920) / 1920.0F;
    fontSize = (int)(fontSize * scaleFactor);
    FontRenderer fontrender = new FontRenderer(font.getFontRegular(), font.getFontBold());
    fontrender.drawString(text, color, (float)x - (GuiUtils.getStringWidth(mc, text, font, fontSize) / 2), (float)y, 10.0F, mc.field_71443_c / 1920.0F + fontSize / 80.0F, true, new Color(0, 0, 0, 150));
  }
  
  public static String convertSecondsToHMmSs(long seconds) {
    long s = seconds % 60L;
    long m = seconds / 60L % 60L;
    long h = seconds / 3600L % 24L;
    long j = seconds / 3600L / 24L;
    if (j >= 1L)
      return String.format("%dJ %02d:%02d:%02d", new Object[] { Long.valueOf(j), Long.valueOf(h), Long.valueOf(m), Long.valueOf(s) }); 
    return String.format("%02d:%02d:%02d", new Object[] { Long.valueOf(h), Long.valueOf(m), Long.valueOf(s) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\boost\hud\WidgetBoost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */