package fr.paladium.palamod.modules.design.modules.legendarypower;

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
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import fr.paladium.palamod.util.PaladiumColorCode;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.ZonedDateTime;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

@Widget(type = RenderGameOverlayEvent.ElementType.TEXT, post = false)
public class WidgetLegendaryPower extends AWidget {
  private ZonedDateTime start = null;
  
  private ZonedDateTime end = null;
  
  public void init(DrawingContext context) {
    setBoundingBox(53.0D, 80.0D, 4.0D, 0.0D);
  }
  
  public void draw(DrawingContext context) {
    double size = ModuleLegendaryPower.getInstance().size();
    Minecraft mc = context.getMinecraft();
    double ratio = context.getHeight() / context.getWidth();
    setRelativeHeight(this.relativeWidth / ratio);
    this.start = TimeUtil.fromLong(mc.field_71439_g.getEntityData().func_74763_f("legendary_power"));
    this.end = TimeUtil.fromLong(mc.field_71439_g.getEntityData().func_74763_f("legendary_power")).plusMinutes(5L);
    if (this.start == null || this.end == null)
      return; 
    Duration duration = Duration.between(TimeUtil.nowZoned(), this.end);
    if (duration.getSeconds() < 0L || duration.toMinutes() > 5L)
      return; 
    float pourcent = (float)duration.toMillis() / (float)Duration.between(this.start, this.end).toMillis() * 100.0F;
    preDraw(context, ModuleLegendaryPower.getInstance());
    GL11.glPushMatrix();
    GL11.glTranslated(getX() + getWidth() / 2.0D - width(3.5D) * 8.0D, getY() + getHeight() / 2.0D - width(3.5D) * 8.0D, 0.0D);
    GL11.glScaled(width(3.5D), width(3.5D), 0.0D);
    GL11.glTranslated(-(getX() + getWidth() / 2.0D - width(3.5D) * 8.0D), -(getY() + getHeight() / 2.0D - width(3.5D) * 8.0D), 0.0D);
    GuiUtils.renderItemStackIntoGUI(new ItemStack((Item)ItemsRegister.BUCKET_SULFURIC), (int)(getX() + getWidth() / 2.0D - width(3.5D) * 8.0D), (int)(getY() + getHeight() / 2.0D - width(3.5D) * 8.0D));
    GL11.glPopMatrix();
    int fontHeight = GuiUtils.getFontHeight(context.getMinecraft(), Fonts.MONTSERRAT_BOLD.getFont(), 40);
    drawCenteredStringWithShadow(context.getMinecraft(), (duration.toMillis() <= 0L) ? "" : (new DecimalFormat("#.#")).format(duration.toMillis() / 1000.0D), getX() + getWidth() / 2.0D, getY() + getHeight() / 2.0D - (fontHeight / 2), (duration.toMillis() < 1000L) ? PaladiumColorCode.ERROR_COLOR : Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 40);
    GL11.glPushMatrix();
    GL11.glTranslated(getX(), getY(), 0.0D);
    GL11.glScaled(1.0D / size, 1.0D / size, 1.0D);
    GL11.glTranslated(-getX(), -getY(), 0.0D);
    GuiUtils.drawProgressBarCircle(getX() + getWidth() * size / 2.0D, getY() + getHeight() * size / 2.0D, (float)width(50.0D * size), (float)width(47.0D * size), 1.0F - pourcent / 100.0F, Color.WHITE, new Color(100, 100, 100));
    GL11.glPopMatrix();
  }
  
  public void drawEdit(DrawingContext context) {
    double size = ModuleLegendaryPower.getInstance().size();
    double ratio = context.getHeight() / context.getWidth();
    setRelativeHeight(this.relativeWidth / ratio);
    long duration = 10000L - System.currentTimeMillis() % 10000L;
    preDraw(context, ModuleLegendaryPower.getInstance());
    GL11.glPushMatrix();
    GL11.glTranslated(getX() + getWidth() / 2.0D - width(3.5D) * 8.0D, getY() + getHeight() / 2.0D - width(3.5D) * 8.0D, 0.0D);
    GL11.glScaled(width(3.5D), width(3.5D), 0.0D);
    GL11.glTranslated(-(getX() + getWidth() / 2.0D - width(3.5D) * 8.0D), -(getY() + getHeight() / 2.0D - width(3.5D) * 8.0D), 0.0D);
    GuiUtils.renderItemStackIntoGUI(new ItemStack((Item)ItemsRegister.BUCKET_SULFURIC), (int)(getX() + getWidth() / 2.0D - width(3.5D) * 8.0D), (int)(getY() + getHeight() / 2.0D - width(3.5D) * 8.0D));
    GL11.glPopMatrix();
    int fontHeight = GuiUtils.getFontHeight(context.getMinecraft(), Fonts.MONTSERRAT_BOLD.getFont(), 40);
    drawCenteredStringWithShadow(context.getMinecraft(), (duration <= 0L) ? "" : (new DecimalFormat("#.#")).format(duration / 1000.0D), getX() + getWidth() / 2.0D, getY() + getHeight() / 2.0D - (fontHeight / 2), (duration < 1000L) ? PaladiumColorCode.ERROR_COLOR : Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 40);
    GL11.glPushMatrix();
    GL11.glTranslated(getX(), getY(), 0.0D);
    GL11.glScaled(1.0D / size, 1.0D / size, 1.0D);
    GL11.glTranslated(-getX(), -getY(), 0.0D);
    GuiUtils.drawProgressBarCircle(getX() + getWidth() * size / 2.0D, getY() + getHeight() * size / 2.0D, (float)width(50.0D * size), (float)width(47.0D * size), (float)(10000L - duration) / 10000.0F, Color.WHITE, new Color(100, 100, 100));
    GL11.glPopMatrix();
  }
  
  private void drawCenteredStringWithShadow(Minecraft mc, String text, double x, double y, Color color, FontObj font, int fontSize) {
    float scaleFactor = Math.min(mc.field_71443_c, 1920) / 1920.0F;
    fontSize = (int)(fontSize * scaleFactor);
    FontRenderer fontrender = new FontRenderer(font.getFontRegular(), font.getFontBold());
    fontrender.drawString(text, color, (float)x - (GuiUtils.getStringWidth(mc, text, font, fontSize) / 2), (float)y, 10.0F, mc.field_71443_c / 1920.0F + fontSize / 80.0F, ModuleLegendaryPower.getInstance().isTextShadow(), new Color(0, 0, 0, 150));
  }
  
  public boolean canDraw(DrawingContext context) {
    return (CommonModule.getInstance().getConfig().getServerType() != ServerType.LOBBY || Constants.MOD_ENV == Constants.Environment.DEV);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\design\modules\legendarypower\WidgetLegendaryPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */