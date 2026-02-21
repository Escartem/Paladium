package fr.paladium.palamod.modules.spellsv2.gui.utils;

import fr.paladium.lib.apollon.font.TTFRenderer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiSpellUtils {
  public static Map<String, TTFRenderer> font = new HashMap<>();
  
  public static void drawCircleImage(int x, int y, int radius, int color) {
    GL11.glPushMatrix();
    Color c = new Color(color, true);
    GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, c.getAlpha() / 255.0F);
    Minecraft.func_71410_x().func_110434_K().func_110577_a(new ResourceLocation("palamod", "textures/gui/circle.png"));
    Gui.func_146110_a(x, y, 0.0F, 0.0F, radius, radius, radius, radius);
    GL11.glPopMatrix();
  }
  
  public static void renderSplitTextWithCustomFontWithShadow(String text, int posX, int posY, int wrapWidth, int color, int scroll, int maxHeight, String fontName, boolean shadow) {
    TTFRenderer renderer = getFont(fontName, 22);
    List<String> lines = new ArrayList<>();
    int split = (int)(wrapWidth / 100.0F * 32.0F);
    while (text.length() > split) {
      lines.add(text.substring(0, split));
      text = text.substring(split);
    } 
    lines.add(text);
    if (posY + renderer.FONT_HEIGHT + (lines.size() - scroll % lines.size()) * renderer.FONT_HEIGHT > maxHeight) {
      for (int i = scroll % lines.size(); i < lines.size(); i++) {
        if (posY + renderer.FONT_HEIGHT + (i - scroll % lines.size()) * renderer.FONT_HEIGHT < maxHeight)
          renderer.drawString(lines.get(i), posX, posY + (i - scroll % lines.size()) * renderer.FONT_HEIGHT, color); 
      } 
    } else {
      for (int i = 0; i < lines.size(); i++) {
        if (posY + renderer.FONT_HEIGHT + i * renderer.FONT_HEIGHT < maxHeight)
          renderer.drawString(lines.get(i), posX, posY + i * renderer.FONT_HEIGHT, color); 
      } 
    } 
  }
  
  public static void drawStringCustomFontWithShadow(String text, int posX, int posY, Color color, boolean shadow, String fontName) {
    TTFRenderer fontrender = getFont(fontName, 22);
    GL11.glPushMatrix();
    GL11.glTranslated((posX - 1), (posY + 1), 1.0D);
    fontrender.drawString(text, 0, 0, (new Color(0, 0, 0, 150)).getRGB());
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslated(posX, posY, 1.0D);
    fontrender.drawString(text, 0, 0, color.getRGB());
    GL11.glPopMatrix();
  }
  
  public static void drawStringCustomFont(String text, int posX, int posY, Color color, boolean shadow, String fontName) {
    TTFRenderer fontrender = getFont(fontName, 22);
    GL11.glPushMatrix();
    GL11.glTranslated(posX, posY, 1.0D);
    fontrender.drawString(text, 0, 0, color.getRGB());
    GL11.glPopMatrix();
  }
  
  public static void drawCenteredCustomFont(String text, int posX, int posY, Color color, boolean shadow, String fontName) {
    TTFRenderer fontrender = getFont(fontName, 22);
    GL11.glPushMatrix();
    GL11.glTranslated((posX - fontrender.getStringWidth(text) / 2), posY, 1.0D);
    fontrender.drawString(text, 0, 0, color.getRGB());
    GL11.glPopMatrix();
  }
  
  public static int renderSplitTextSizeWithCustomFontWithShadow(String text, int posX, int posY, int wrapWidth, int color, int scroll, int maxHeight, String fontName, boolean shadow) {
    int size = 0;
    TTFRenderer renderer = getFont(fontName, 22);
    List<String> lines = new ArrayList<>();
    int split = (int)(wrapWidth / 100.0F * 32.0F);
    while (text.length() > split) {
      lines.add(text.substring(0, split));
      text = text.substring(split);
    } 
    lines.add(text);
    if (posY + renderer.FONT_HEIGHT + (lines.size() - scroll % lines.size()) * renderer.FONT_HEIGHT > maxHeight) {
      for (int i = scroll % lines.size(); i < lines.size(); i++) {
        if (posY + renderer.FONT_HEIGHT + (i - scroll % lines.size()) * renderer.FONT_HEIGHT < maxHeight) {
          renderer.drawString(lines.get(i), posX, posY + (i - scroll % lines.size()) * renderer.FONT_HEIGHT, color);
          size++;
        } 
      } 
    } else {
      for (int i = 0; i < lines.size(); i++) {
        if (posY + renderer.FONT_HEIGHT + i * renderer.FONT_HEIGHT < maxHeight) {
          renderer.drawString(lines.get(i), posX, posY + i * renderer.FONT_HEIGHT, color);
          size++;
        } 
      } 
    } 
    size = lines.size() - size;
    if (size < 0)
      size = 0; 
    return size;
  }
  
  public static void renderTooltip(String[] tooltip, int x, int y) {
    Minecraft mc = Minecraft.func_71410_x();
    float w = 0.0F;
    int h = 0;
    for (String tip : tooltip) {
      if (mc.field_71466_p.func_78256_a(tip) / 100.0F * 70.0F > w)
        w = mc.field_71466_p.func_78256_a(tip) / 100.0F * 70.0F; 
      h += mc.field_71466_p.field_78288_b;
    } 
    GL11.glPushMatrix();
    if (!GL11.glIsEnabled(3042))
      GL11.glEnable(3042); 
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glTranslatef(1.0F, 1.0F, 2.0F);
    Gui.func_73734_a(x, y - 4, (int)(x + w + 2.0F), y - 2 + h, (new Color(30, 30, 30, 130)).getRGB());
    for (int i = 0; i < tooltip.length; i++) {
      String tip = tooltip[i];
      drawStringCustomFontWithShadow(tip, x + 2, y - 2 + mc.field_71466_p.field_78288_b * i, Color.WHITE, true, "BurbankBigCondensed-Bold");
    } 
    if (!GL11.glIsEnabled(3042))
      GL11.glDisable(3042); 
    GL11.glPopMatrix();
  }
  
  public static TTFRenderer getFont(String name, int size) {
    if (font.containsKey(name + "-" + size))
      return font.get(name + "-" + size); 
    TTFRenderer render = new TTFRenderer(name, size);
    font.put(name + "-" + size, render);
    return render;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\gu\\utils\GuiSpellUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */