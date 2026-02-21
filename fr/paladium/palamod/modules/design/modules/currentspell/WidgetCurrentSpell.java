package fr.paladium.palamod.modules.design.modules.currentspell;

import fr.paladium.helios.client.utils.DrawingContext;
import fr.paladium.helios.module.utils.abstracts.AWidget;
import fr.paladium.helios.module.utils.annotations.Widget;
import fr.paladium.lib.apollon.fontV2.FontObj;
import fr.paladium.lib.apollon.fontV2.FontRenderer;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import fr.paladium.palamod.util.PaladiumColorCode;
import fr.paladium.palamod.util.TimeUtils;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@Widget(type = RenderGameOverlayEvent.ElementType.TEXT, post = false)
public class WidgetCurrentSpell extends AWidget {
  public void init(DrawingContext context) {
    setBoundingBox(89.0D, 75.0D, 10.0D, 4.0D);
  }
  
  public void draw(DrawingContext context) {
    Minecraft mc = context.getMinecraft();
    if (ClientManager.getCurrentSpell() < 0 || !ClientManager.getSpellDelay().containsKey(mc.field_71439_g.func_110124_au()) || !((Map)ClientManager.getSpellDelay().get(mc.field_71439_g.func_110124_au())).containsKey(Integer.valueOf(ClientManager.getCurrentSpell())))
      return; 
    preDraw(context, ModuleCurrentSpell.getInstance());
    ZonedDateTime lastUse = TimeUtil.fromLong(((Long)((Map)ClientManager.getSpellDelay().get(mc.field_71439_g.func_110124_au())).get(Integer.valueOf(ClientManager.getCurrentSpell()))).longValue());
    Duration duration = Duration.between(TimeUtil.nowZoned(), lastUse.plusMinutes(Spells.values()[ClientManager.getCurrentSpell()].getSpell().getCooldown()));
    long delay = duration.getSeconds();
    int maxDelay = Spells.values()[ClientManager.getCurrentSpell()].getSpell().getCooldown() * 60;
    int pourcentDelay = (int)((float)delay / maxDelay * 100.0F);
    if (pourcentDelay > 100)
      pourcentDelay = 100; 
    if (pourcentDelay < 0)
      pourcentDelay = 0; 
    float progress = pourcentDelay / 100.0F;
    if (progress < 0.0F) {
      progress = 0.0F;
    } else if (progress > 1.0F) {
      progress = 1.0F;
    } 
    long seconds = duration.getSeconds();
    if (seconds < 0L)
      seconds = 0L; 
    drawCenteredStringWithShadow(mc, Spells.values()[ClientManager.getCurrentSpell()].getSpell().getName(), getX() + width(50.0D), getY(), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 30);
    drawCenteredStringWithShadow(mc, TimeUtils.getDurationString((int)seconds), getX() + width(50.0D), getY() + height(50.0D), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 0);
    GuiUtils.drawRect(getX(), getY() + height(90.0D), getX() + getWidth(), getY() + height(100.0D), new Color(100, 100, 100));
    GuiUtils.drawRect(getX(), getY() + height(90.0D), getX() + getWidth() * progress, getY() + height(100.0D), PaladiumColorCode.ERROR_COLOR);
  }
  
  public void drawEdit(DrawingContext context) {
    Minecraft mc = context.getMinecraft();
    preDraw(context, ModuleCurrentSpell.getInstance());
    float progress = 0.25F;
    drawCenteredStringWithShadow(mc, Spells.values()[0].getSpell().getName(), getX() + width(50.0D), getY(), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 30);
    drawCenteredStringWithShadow(mc, TimeUtils.getDurationString(90), getX() + width(50.0D), getY() + height(50.0D), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 0);
    GuiUtils.drawRect(getX(), getY() + height(90.0D), getX() + getWidth(), getY() + height(100.0D), new Color(100, 100, 100));
    GuiUtils.drawRect(getX(), getY() + height(90.0D), getX() + getWidth() * 0.25D, getY() + height(100.0D), PaladiumColorCode.ERROR_COLOR);
  }
  
  private void drawCenteredStringWithShadow(Minecraft mc, String text, double x, double y, Color color, FontObj font, int fontSize) {
    float scaleFactor = Math.min(mc.field_71443_c, 1920) / 1920.0F;
    fontSize = (int)(fontSize * scaleFactor);
    FontRenderer fontrender = new FontRenderer(font.getFontRegular(), font.getFontBold());
    fontrender.drawString(text, color, (float)x - (GuiUtils.getStringWidth(mc, text, font, fontSize) / 2), (float)y, 10.0F, mc.field_71443_c / 1920.0F + fontSize / 80.0F, ModuleCurrentSpell.getInstance().isTextShadow(), new Color(0, 0, 0, 150));
  }
  
  public boolean canDraw(DrawingContext context) {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\design\modules\currentspell\WidgetCurrentSpell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */