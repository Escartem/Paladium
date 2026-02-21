package fr.paladium.common.combat;

import fr.paladium.common.CommonModule;
import fr.paladium.helios.client.utils.DrawingContext;
import fr.paladium.helios.module.utils.abstracts.AWidget;
import fr.paladium.helios.module.utils.annotations.Widget;
import fr.paladium.lib.apollon.fontV2.FontObj;
import fr.paladium.lib.apollon.fontV2.FontRenderer;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import java.text.DecimalFormat;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

@Widget(type = RenderGameOverlayEvent.ElementType.TEXT, post = false)
public class WidgetCombatTag extends AWidget {
  private final Color RED = Color.decode("#E74C3C");
  
  public void init(DrawingContext context) {
    setBoundingBox(48.0D, 10.0D, 4.0D, 0.0D);
  }
  
  public void draw(DrawingContext context) {
    double size = ModuleCombatTag.getInstance().size();
    double ratio = context.getHeight() / context.getWidth();
    setRelativeHeight(this.relativeWidth / ratio);
    long duration = CommonModule.getInstance().getCombatTag().getEnd() - System.currentTimeMillis() + CommonModule.getInstance().getCombatTag().getTimeOffset();
    if (duration < 0L || !CommonModule.getInstance().getCombatTag().inFight())
      duration = 0L; 
    preDraw(context, ModuleCombatTag.getInstance());
    GuiUtils.drawImageTransparent(getX() + width(25.0D), getY() + height(25.0D), 0.0D, 0.0D, width(50.0D), height(50.0D), width(50.0D), height(50.0D), ModuleCombatTag.getInstance().getIcon(), Color.WHITE, 0.5F);
    int fontHeight = GuiUtils.getFontHeight(context.getMinecraft(), Fonts.MONTSERRAT_BOLD.getFont(), 40);
    drawCenteredStringWithShadow(context.getMinecraft(), (duration <= 0L) ? "" : (new DecimalFormat("#.#")).format(duration / 1000.0D), getX() + getWidth() / 2.0D, getY() + getHeight() / 2.0D - (fontHeight / 2), (duration < 1000L) ? this.RED : Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 40);
    GL11.glPushMatrix();
    GL11.glTranslated(getX(), getY(), 0.0D);
    GL11.glScaled(1.0D / size, 1.0D / size, 1.0D);
    GL11.glTranslated(-getX(), -getY(), 0.0D);
    GuiUtils.drawProgressBarCircle(getX() + getWidth() * size / 2.0D, getY() + getHeight() * size / 2.0D, (float)width(50.0D * size), (float)width(47.0D * size), (duration <= 0L) ? 0.0F : ((float)(30000L - duration) / 30000.0F), Color.WHITE, new Color(100, 100, 100));
    GL11.glPopMatrix();
  }
  
  public void drawEdit(DrawingContext context) {
    double size = ModuleCombatTag.getInstance().size();
    double ratio = context.getHeight() / context.getWidth();
    setRelativeHeight(this.relativeWidth / ratio);
    long duration = 10000L - System.currentTimeMillis() % 10000L;
    if (duration < 0L || !CommonModule.getInstance().getCombatTag().inFight())
      duration = 0L; 
    preDraw(context, ModuleCombatTag.getInstance());
    GuiUtils.drawImageTransparent(getX() + width(25.0D), getY() + height(25.0D), 0.0D, 0.0D, width(50.0D), height(50.0D), width(50.0D), height(50.0D), ModuleCombatTag.getInstance().getIcon(), Color.WHITE, 0.5F);
    int fontHeight = GuiUtils.getFontHeight(context.getMinecraft(), Fonts.MONTSERRAT_BOLD.getFont(), 40);
    drawCenteredStringWithShadow(context.getMinecraft(), (duration <= 0L) ? "" : (new DecimalFormat("#.#")).format(duration / 1000.0D), getX() + getWidth() / 2.0D, getY() + getHeight() / 2.0D - (fontHeight / 2), (duration < 1000L) ? this.RED : Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 40);
    GL11.glPushMatrix();
    GL11.glTranslated(getX(), getY(), 0.0D);
    GL11.glScaled(1.0D / size, 1.0D / size, 1.0D);
    GL11.glTranslated(-getX(), -getY(), 0.0D);
    GuiUtils.drawProgressBarCircle(getX() + getWidth() * size / 2.0D, getY() + getHeight() * size / 2.0D, (float)width(50.0D * size), (float)width(47.0D * size), (duration <= 0L) ? 0.0F : ((float)(30000L - duration) / 30000.0F), Color.WHITE, new Color(100, 100, 100));
    GL11.glPopMatrix();
  }
  
  private void drawCenteredStringWithShadow(Minecraft mc, String text, double x, double y, Color color, FontObj font, int fontSize) {
    float scaleFactor = Math.min(mc.field_71443_c, 1920) / 1920.0F;
    fontSize = (int)(fontSize * scaleFactor);
    FontRenderer fontrender = new FontRenderer(font.getFontRegular(), font.getFontBold());
    fontrender.drawString(text, color, (float)x - (GuiUtils.getStringWidth(mc, text, font, fontSize) / 2), (float)y, 10.0F, mc.field_71443_c / 1920.0F + fontSize / 80.0F, true, new Color(0, 0, 0, 150));
  }
  
  public boolean canDraw(DrawingContext context) {
    return CommonModule.getInstance().getCombatTag().inFight();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\combat\WidgetCombatTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */