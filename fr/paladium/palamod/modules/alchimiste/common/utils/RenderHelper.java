package fr.paladium.palamod.modules.alchimiste.common.utils;

import net.minecraft.client.renderer.Tessellator;

public class RenderHelper {
  public static void drawTexturedModalRectWithZ(int p_73729_1_, int p_73729_2_, int p_73729_3_, int p_73729_4_, int p_73729_5_, int p_73729_6_, double zLevel) {
    float f = 0.00390625F;
    float f1 = 0.00390625F;
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78382_b();
    tessellator.func_78374_a((p_73729_1_ + 0), (p_73729_2_ + p_73729_6_), zLevel, ((p_73729_3_ + 0) * f), ((p_73729_4_ + p_73729_6_) * f1));
    tessellator.func_78374_a((p_73729_1_ + p_73729_5_), (p_73729_2_ + p_73729_6_), zLevel, ((p_73729_3_ + p_73729_5_) * f), ((p_73729_4_ + p_73729_6_) * f1));
    tessellator.func_78374_a((p_73729_1_ + p_73729_5_), (p_73729_2_ + 0), zLevel, ((p_73729_3_ + p_73729_5_) * f), ((p_73729_4_ + 0) * f1));
    tessellator.func_78374_a((p_73729_1_ + 0), (p_73729_2_ + 0), zLevel, ((p_73729_3_ + 0) * f), ((p_73729_4_ + 0) * f1));
    tessellator.func_78381_a();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\commo\\utils\RenderHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */