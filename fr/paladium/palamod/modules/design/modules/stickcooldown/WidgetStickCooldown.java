package fr.paladium.palamod.modules.design.modules.stickcooldown;

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
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import fr.paladium.palamod.modules.paladium.common.items.ItemStickBase;
import fr.paladium.palamod.util.PaladiumColorCode;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.ZonedDateTime;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

@Widget(type = RenderGameOverlayEvent.ElementType.TEXT, post = false)
public class WidgetStickCooldown extends AWidget {
  private ItemStack item;
  
  private ZonedDateTime start = null;
  
  private ZonedDateTime end = null;
  
  public void init(DrawingContext context) {
    setBoundingBox(48.0D, 80.0D, 4.0D, 0.0D);
  }
  
  public void draw(DrawingContext context) {
    double size = ModuleStickCooldown.getInstance().size();
    Minecraft mc = context.getMinecraft();
    if (!ModuleStickCooldown.getInstance().isShowAllSticks()) {
      double ratio = context.getHeight() / context.getWidth();
      setRelativeHeight(this.relativeWidth / ratio);
      if (mc.field_71439_g.func_70694_bm() != null && mc.field_71439_g.func_70694_bm().func_77973_b() instanceof ItemStickBase) {
        ItemStickBase itemStick = (ItemStickBase)mc.field_71439_g.func_70694_bm().func_77973_b();
        this.item = mc.field_71439_g.func_70694_bm();
        this.start = TimeUtil.fromLong(mc.field_71439_g.getEntityData().func_74763_f("timer-" + itemStick.getType()));
        this.end = TimeUtil.fromLong(mc.field_71439_g.getEntityData().func_74763_f("timer-" + itemStick.getType())).plusSeconds(itemStick.cooldown);
      } 
      if (this.item == null || this.start == null || this.end == null)
        return; 
      Duration initialCooldown = Duration.between(this.start, this.end);
      Duration durationCooldown = Duration.between(TimeUtil.nowZoned(), this.start);
      long initialDuration = initialCooldown.toMillis() - 1000L;
      long duration = durationCooldown.toMillis() - 1000L;
      if (duration <= 0L && (mc.field_71439_g.func_70694_bm() == null || !(mc.field_71439_g.func_70694_bm().func_77973_b() instanceof ItemStickBase)))
        return; 
      if (duration < 0L)
        duration = 0L; 
      preDraw(context, ModuleStickCooldown.getInstance());
      drawStickCooldown(mc, getX(), getY(), size, this.item, duration, initialDuration);
    } else {
      preDraw(context, ModuleStickCooldown.getInstance());
      double oy = 0.0D;
      for (int i = 0; i < 8; i++) {
        ItemStack stack = ItemStickBase.getItem(i);
        ZonedDateTime start = TimeUtil.fromLong(mc.field_71439_g.getEntityData().func_74763_f("timer-" + i));
        ZonedDateTime end = TimeUtil.fromLong(mc.field_71439_g.getEntityData().func_74763_f("timer-" + i)).plusSeconds(((ItemStickBase)stack.func_77973_b()).cooldown);
        Duration initialCooldown = Duration.between(start, end);
        Duration durationCooldown = Duration.between(TimeUtil.nowZoned(), start);
        long initialDuration = initialCooldown.toMillis() - 1000L;
        long duration = durationCooldown.toMillis() - 1000L;
        if (duration > 0L) {
          drawStickCooldown(mc, getX(), getY() + oy, size, stack, duration, initialDuration);
          oy += width(112.5D);
        } 
      } 
      setRelativeHeight(oy / context.getHeight() * 100.0D);
    } 
  }
  
  private void drawStickCooldown(Minecraft mc, double x, double y, double size, ItemStack item, long duration, long initialDuration) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + width(50.0D) - width(3.5D) * 8.0D, y + width(50.0D) - width(3.5D) * 8.0D, 0.0D);
    GL11.glScaled(width(3.5D), width(3.5D), 0.0D);
    GL11.glTranslated(-(x + width(50.0D) - width(3.5D) * 8.0D), -(y + width(50.0D) - width(3.5D) * 8.0D), 0.0D);
    GuiUtils.renderItemStackIntoGUI(item, (int)(x + width(50.0D) - width(3.5D) * 8.0D), (int)(y + width(50.0D) - width(3.5D) * 8.0D));
    GL11.glPopMatrix();
    int fontHeight = GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_BOLD.getFont(), 40);
    drawCenteredStringWithShadow(mc, (duration <= 0L) ? "" : (new DecimalFormat("#.#")).format(duration / 1000.0D), x + width(50.0D), y + width(50.0D) - (fontHeight / 2), (duration < 1000L) ? PaladiumColorCode.ERROR_COLOR : Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 40);
    GL11.glPushMatrix();
    GL11.glTranslated(x, y, 0.0D);
    GL11.glScaled(1.0D / size, 1.0D / size, 1.0D);
    GL11.glTranslated(-x, -y, 0.0D);
    GuiUtils.drawProgressBarCircle(x + getWidth() * size / 2.0D, y + getWidth() * size / 2.0D, (float)width(50.0D * size), (float)width(47.0D * size), (duration <= 0L) ? 0.0F : ((float)(initialDuration - duration) / (float)initialDuration), Color.WHITE, new Color(100, 100, 100));
    GL11.glPopMatrix();
  }
  
  public void drawEdit(DrawingContext context) {
    double size = ModuleStickCooldown.getInstance().size();
    Minecraft mc = context.getMinecraft();
    if (!ModuleStickCooldown.getInstance().isShowAllSticks()) {
      double ratio = context.getHeight() / context.getWidth();
      setRelativeHeight(this.relativeWidth / ratio);
      long initialDuration = 30000L;
      long duration = System.currentTimeMillis() % 30000L;
      if (duration < 0L)
        duration = 0L; 
      preDraw(context, ModuleStickCooldown.getInstance());
      drawStickCooldown(mc, getX(), getY(), size, ItemStickBase.getItem(0), duration, 30000L);
    } else {
      preDraw(context, ModuleStickCooldown.getInstance());
      double oy = 0.0D;
      for (int i = 0; i < 8; i++) {
        ItemStack stack = ItemStickBase.getItem(i);
        long initialDuration = 30000L;
        long duration = System.currentTimeMillis() % 30000L;
        if (duration < 0L)
          duration = 0L; 
        drawStickCooldown(mc, getX(), getY() + oy, size, stack, duration, 30000L);
        oy += width(112.5D);
      } 
      setRelativeHeight(oy / context.getHeight() * 100.0D);
    } 
  }
  
  private void drawCenteredStringWithShadow(Minecraft mc, String text, double x, double y, Color color, FontObj font, int fontSize) {
    float scaleFactor = Math.min(mc.field_71443_c, 1920) / 1920.0F;
    fontSize = (int)(fontSize * scaleFactor);
    FontRenderer fontrender = new FontRenderer(font.getFontRegular(), font.getFontBold());
    fontrender.drawString(text, color, (float)x - (GuiUtils.getStringWidth(mc, text, font, fontSize) / 2), (float)y, 10.0F, mc.field_71443_c / 1920.0F + fontSize / 80.0F, ModuleStickCooldown.getInstance().isTextShadow(), new Color(0, 0, 0, 150));
  }
  
  public boolean canDraw(DrawingContext context) {
    return (CommonModule.getInstance().getConfig().getServerType() != ServerType.LOBBY || Constants.MOD_ENV == Constants.Environment.DEV);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\design\modules\stickcooldown\WidgetStickCooldown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */