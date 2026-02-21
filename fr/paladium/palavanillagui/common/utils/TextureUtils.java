package fr.paladium.palavanillagui.common.utils;

import net.minecraft.client.renderer.Tessellator;

public class TextureUtils {
  public static void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
    float f = 0.00390625F;
    float f1 = 0.00390625F;
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78382_b();
    tessellator.func_78374_a((x + 0), (y + height), 1.0D, ((textureX + 0) * f), ((textureY + height) * f1));
    tessellator.func_78374_a((x + width), (y + height), 1.0D, ((textureX + width) * f), ((textureY + height) * f1));
    tessellator.func_78374_a((x + width), (y + 0), 1.0D, ((textureX + width) * f), ((textureY + 0) * f1));
    tessellator.func_78374_a((x + 0), (y + 0), 1.0D, ((textureX + 0) * f), ((textureY + 0) * f1));
    tessellator.func_78381_a();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\commo\\utils\TextureUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */