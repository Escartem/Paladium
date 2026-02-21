package fr.paladium.achievement.core.helios;

import fr.paladium.helios.client.utils.DrawingContext;
import fr.paladium.helios.module.utils.abstracts.AWidget;
import fr.paladium.helios.module.utils.annotations.Widget;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@Widget(type = RenderGameOverlayEvent.ElementType.HOTBAR, post = true, border = false, background = false)
public class WidgetAchievement extends AWidget {
  private ResourceLocation icon;
  
  public void init(DrawingContext context) {
    setBoundingBox(25.0D, 20.0D, 50.0D, 10.0D);
    this.icon = new ResourceLocation("achievement", "textures/gui/home/icon.png");
  }
  
  public void draw(DrawingContext context) {
    if (ModuleAchievement.getInstance().getText() == null || ModuleAchievement.getInstance().getText().isEmpty())
      return; 
    if (ModuleAchievement.getInstance().getEnd() <= System.currentTimeMillis()) {
      ModuleAchievement.getInstance().setText(null);
      return;
    } 
    long time = ModuleAchievement.getInstance().getEnd() - System.currentTimeMillis();
    long percent = 100L;
    long duration = 5000L;
    long fadeInDuration = 500L;
    long fadeOutDuration = 1500L;
    if (time < 5000L && time > 4500L) {
      percent = 100L - (time - 4500L) * 100L / 500L;
    } else if (time <= 1500L) {
      percent = 100L - 100L * (1500L - time) / 1500L;
    } else {
      percent = 100L;
    } 
    int opacity = (int)(255L * percent / 100L);
    GuiUtils.drawImageTransparent(getX() + getWidth() / 2.0D - width(5.0D), getY() - getHeight() / 2.0D - width(8.0D), 0.0D, 0.0D, width(10.0D), width(10.0D), width(10.0D), width(10.0D), this.icon, Color.WHITE, opacity / 255.0F, false);
    Minecraft mc = context.getMinecraft();
    int fontHeight = GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_BOLD.getFont(), 40);
    Color titleColor = new Color(255, 209, 116, opacity);
    GuiUtils.drawSplittedString(mc, "Achievement débloqué".toUpperCase(), getX() + getWidth() / 2.0D, getY() - fontHeight / 2.0D + height(2.0D), titleColor.darker(0.3F), Fonts.MONTSERRAT_BOLD.getFont(), 80, getWidth(), TextAlign.CENTER);
    GuiUtils.drawSplittedString(mc, "Achievement débloqué".toUpperCase(), getX() + getWidth() / 2.0D, getY() - fontHeight / 2.0D, titleColor, Fonts.MONTSERRAT_BOLD.getFont(), 80, getWidth(), TextAlign.CENTER);
    Color subTitleColor = new Color(255, 255, 255, opacity);
    GuiUtils.drawSplittedString(mc, ModuleAchievement.getInstance().getText().toUpperCase(), getX() + getWidth() / 2.0D, getY() + getHeight() / 2.0D - fontHeight / 2.0D + height(4.0D), subTitleColor.darker(0.3F), Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 100, getWidth(), TextAlign.CENTER);
    GuiUtils.drawSplittedString(mc, ModuleAchievement.getInstance().getText().toUpperCase(), getX() + getWidth() / 2.0D, getY() + getHeight() / 2.0D - fontHeight / 2.0D, subTitleColor, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 100, getWidth(), TextAlign.CENTER);
  }
  
  public void drawEdit(DrawingContext context) {
    Minecraft mc = context.getMinecraft();
    Color titleColor = new Color(255, 209, 116, 255);
    GuiUtils.drawSplittedString(mc, "Achievement débloqué".toUpperCase(), getX() + getWidth() / 2.0D, getY() + height(2.0D), titleColor.darker(0.3F), Fonts.MONTSERRAT_BOLD.getFont(), 80, getWidth(), TextAlign.CENTER);
    GuiUtils.drawSplittedString(mc, "Achievement débloqué".toUpperCase(), getX() + getWidth() / 2.0D, getY(), titleColor, Fonts.MONTSERRAT_BOLD.getFont(), 80, getWidth(), TextAlign.CENTER);
    Color subTitleColor = new Color(255, 255, 255, 255);
    GuiUtils.drawSplittedString(mc, "Nom de l'achievement".toUpperCase(), getX() + getWidth() / 2.0D, getY() + getHeight() / 2.0D + height(4.0D), subTitleColor.darker(0.3F), Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 100, getWidth(), TextAlign.CENTER);
    GuiUtils.drawSplittedString(mc, "Nom de l'achievement".toUpperCase(), getX() + getWidth() / 2.0D, getY() + getHeight() / 2.0D, subTitleColor, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 100, getWidth(), TextAlign.CENTER);
  }
  
  public boolean canDraw(DrawingContext context) {
    return (ModuleAchievement.getInstance().getText() != null && !ModuleAchievement.getInstance().getText().isEmpty());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\helios\WidgetAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */