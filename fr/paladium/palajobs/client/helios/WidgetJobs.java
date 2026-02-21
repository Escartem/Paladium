package fr.paladium.palajobs.client.helios;

import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.helios.client.utils.DrawingContext;
import fr.paladium.helios.module.utils.abstracts.AWidget;
import fr.paladium.helios.module.utils.annotations.Widget;
import fr.paladium.lib.apollon.fontV2.FontObj;
import fr.paladium.lib.apollon.fontV2.FontRenderer;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palajobs.client.ui.utils.advancement.JobAdvancement;
import fr.paladium.palajobs.core.jobs.AbstractJob;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.translate.common.texttotranslate.TTT;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@Widget(type = RenderGameOverlayEvent.ElementType.TEXT, post = false)
public class WidgetJobs extends AWidget {
  public void init(DrawingContext context) {
    setBoundingBox(89.0D, 88.0D, 10.0D, 5.0D);
  }
  
  public void draw(DrawingContext context) {
    Minecraft mc = context.getMinecraft();
    JobsPlayer data = JobsPlayer.get((Entity)mc.field_71439_g);
    if (data == null)
      return; 
    AbstractJob job = JobsManager.getInstance().getJobByType(data.getLastUpdatedJob()).get();
    preDraw(context, ModuleJobs.getInstance());
    JobAdvancement advancement = new JobAdvancement(data, job);
    drawStringWithShadow(mc, "0", getX(), getY(), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 30);
    drawStringWithShadow(mc, GuiUtils.formatNumber((long)advancement.getNextLevelExperience()), getX() + getWidth() - GuiUtils.getStringWidth(mc, GuiUtils.formatNumber((long)advancement.getNextLevelExperience()), Fonts.MONTSERRAT_BOLD.getFont(), 30), getY(), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 30);
    double progress = advancement.getProgressBarValue() / 100.0D;
    if (progress < 0.0D) {
      progress = 0.0D;
    } else if (progress > 1.0D) {
      progress = 1.0D;
    } 
    GuiUtils.drawRect(getX(), getY() + height(50.0D), getX() + getWidth(), getY() + height(60.0D), new Color(100, 100, 100));
    GuiUtils.drawRect(getX(), getY() + height(50.0D), getX() + getWidth() * Math.min(progress, 1.0D), getY() + height(60.0D), Color.decode("#2980B9"));
    GuiUtils.drawRect(getX(), getY() + height(50.0D), getX() + context.width(0.1D), getY() + height(70.0D), Color.WHITE);
    GuiUtils.drawRect(getX() + getWidth() - context.width(0.1D), getY() + height(50.0D), getX() + getWidth(), getY() + height(70.0D), Color.WHITE);
    drawCenteredStringWithShadow(mc, TTT.format(data.getLastUpdatedJob().getName(), new Object[0]), getX() + width(50.0D), getY(), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 30);
    String expStr = formatDouble(Math.min(advancement.getExperience(), advancement.getNextLevelExperience()));
    if (ModuleJobs.getInstance().getDisplayType().equals("percentage"))
      expStr = formatDouble(progress * 100.0D) + "%"; 
    drawCenteredStringWithShadow(mc, expStr, getX() + width(50.0D), getY() + height(80.0D), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 30);
  }
  
  private String formatDouble(double value) {
    if (value % 1.0D != 0.0D)
      return String.format("%.1f", new Object[] { Double.valueOf(value) }); 
    return String.format("%.0f", new Object[] { Double.valueOf(value) });
  }
  
  private void drawStringWithShadow(Minecraft mc, String text, double x, double y, Color color, FontObj font, int fontSize) {
    float scaleFactor = Math.min(mc.field_71443_c, 1920) / 1920.0F;
    fontSize = (int)(fontSize * scaleFactor);
    FontRenderer fontrender = new FontRenderer(font.getFontRegular(), font.getFontBold());
    fontrender.drawString(text, color, (float)x, (float)y, 10.0F, mc.field_71443_c / 1920.0F + fontSize / 80.0F, true, new Color(0, 0, 0, 150));
  }
  
  private void drawCenteredStringWithShadow(Minecraft mc, String text, double x, double y, Color color, FontObj font, int fontSize) {
    float scaleFactor = Math.min(mc.field_71443_c, 1920) / 1920.0F;
    fontSize = (int)(fontSize * scaleFactor);
    FontRenderer fontrender = new FontRenderer(font.getFontRegular(), font.getFontBold());
    fontrender.drawString(text, color, (float)x - (GuiUtils.getStringWidth(mc, text, font, fontSize) / 2), (float)y, 10.0F, mc.field_71443_c / 1920.0F + fontSize / 80.0F, true, new Color(0, 0, 0, 150));
  }
  
  public boolean canDraw(DrawingContext context) {
    return (CommonModule.getInstance().getConfig().getServerType() == ServerType.FACTION || CommonModule.getInstance().getConfig().getServerType() == ServerType.FARMLAND || CommonModule.getInstance().getConfig().getServerType() == ServerType.MINAGE || CommonModule.getInstance().getConfig().getServerType() == ServerType.RPG);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\helios\WidgetJobs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */