package fr.paladium.palamod.client.utils;

import fr.paladium.lib.apollon.utils.GuiUtils;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import org.lwjgl.opengl.GL11;

public class HoverUtils {
  private static RenderItem itemRenderer = new RenderItem();
  
  public static void drawHoveringText(List<String> p_146283_1_, int p_146283_2_, int p_146283_3_, FontRenderer font) {
    if (!p_146283_1_.isEmpty()) {
      GL11.glPushMatrix();
      int k = 0;
      Iterator<String> iterator = p_146283_1_.iterator();
      while (iterator.hasNext()) {
        String s = iterator.next();
        int l = font.func_78256_a(s);
        if (l > k)
          k = l; 
      } 
      int j2 = p_146283_2_ + 12;
      int k2 = p_146283_3_ - 12;
      int i1 = 8;
      if (p_146283_1_.size() > 1)
        i1 += 2 + (p_146283_1_.size() - 1) * 10; 
      if (j2 + k > (Minecraft.func_71410_x()).field_71462_r.field_146294_l)
        j2 -= 28 + k; 
      if (k2 + i1 + 6 > (Minecraft.func_71410_x()).field_71462_r.field_146295_m)
        k2 = (Minecraft.func_71410_x()).field_71462_r.field_146295_m - i1 - 6; 
      itemRenderer.field_77023_b = 300.0F;
      int j1 = -267386864;
      GuiUtils.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, -267386864, -267386864, 300.0F);
      GuiUtils.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, -267386864, -267386864, 300.0F);
      GuiUtils.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, -267386864, -267386864, 300.0F);
      GuiUtils.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, -267386864, -267386864, 300.0F);
      GuiUtils.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, -267386864, -267386864, 300.0F);
      int k1 = 1347420415;
      int l1 = 1344798847;
      GuiUtils.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, 1347420415, 1344798847, 300.0F);
      GuiUtils.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, 1347420415, 1344798847, 300.0F);
      GuiUtils.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, 1347420415, 1347420415, 300.0F);
      GuiUtils.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, 1344798847, 1344798847, 300.0F);
      GL11.glTranslated(0.0D, 0.0D, (itemRenderer.field_77023_b + 1.0F));
      for (int i2 = 0; i2 < p_146283_1_.size(); i2++) {
        String s1 = p_146283_1_.get(i2);
        font.func_78261_a(s1, j2, k2, -1);
        if (i2 == 0)
          k2 += 2; 
        k2 += 10;
      } 
      itemRenderer.field_77023_b = 0.0F;
      GL11.glPopMatrix();
    } 
  }
  
  public static void drawHoveringText(List<String> list, int x, int p_146283_3_, float zindex, FontRenderer font) {
    if (!list.isEmpty()) {
      GL11.glPushMatrix();
      int k = 0;
      Iterator<String> iterator = list.iterator();
      while (iterator.hasNext()) {
        String s = iterator.next();
        int l = font.func_78256_a(s);
        if (l > k)
          k = l; 
      } 
      int j2 = x + 12;
      int k2 = p_146283_3_ - 12;
      int i1 = 8;
      if (list.size() > 1)
        i1 += 2 + (list.size() - 1) * 10; 
      if (j2 + k > (Minecraft.func_71410_x()).field_71462_r.field_146294_l)
        j2 -= 28 + k; 
      if (k2 + i1 + 6 > (Minecraft.func_71410_x()).field_71462_r.field_146295_m)
        k2 = (Minecraft.func_71410_x()).field_71462_r.field_146295_m - i1 - 6; 
      itemRenderer.field_77023_b = zindex;
      int j1 = -267386864;
      GuiUtils.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, -267386864, -267386864, itemRenderer.field_77023_b);
      GuiUtils.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, -267386864, -267386864, itemRenderer.field_77023_b);
      GuiUtils.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, -267386864, -267386864, itemRenderer.field_77023_b);
      GuiUtils.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, -267386864, -267386864, itemRenderer.field_77023_b);
      GuiUtils.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, -267386864, -267386864, itemRenderer.field_77023_b);
      int k1 = 1347420415;
      int l1 = 1344798847;
      GuiUtils.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, 1347420415, 1344798847, itemRenderer.field_77023_b);
      GuiUtils.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, 1347420415, 1344798847, itemRenderer.field_77023_b);
      GuiUtils.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, 1347420415, 1347420415, itemRenderer.field_77023_b);
      GuiUtils.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, 1344798847, 1344798847, itemRenderer.field_77023_b);
      itemRenderer.field_77023_b = 0.0F;
      GL11.glEnable(2896);
      RenderHelper.func_74519_b();
      GL11.glEnable(32826);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glDisable(32826);
      RenderHelper.func_74518_a();
      GL11.glDisable(2896);
      GL11.glTranslated(0.0D, 0.0D, (zindex + 1.0F));
      GL11.glScaled(1.0D, 1.0D, 1.0D);
      for (int i2 = 0; i2 < list.size(); i2++) {
        String s1 = list.get(i2);
        font.func_78261_a(s1, j2, k2, -1);
        if (i2 == 0)
          k2 += 2; 
        k2 += 10;
      } 
      GL11.glPopMatrix();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\clien\\utils\HoverUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */