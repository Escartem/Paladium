package fr.paladium.palamod.modules.luckyblock.gui.luckystats;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiTalismanCalm {
  public GuiTalismanCalm() {
    Minecraft mc = Minecraft.func_71410_x();
    ScaledResolution res = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
    int width = res.func_78326_a();
    int height = res.func_78328_b();
    GL11.glColor3f(1.0F, 1.0F, 1.0F);
    mc.func_110434_K().func_110577_a(new ResourceLocation("palamod", "textures/gui/LuckyStats/calm.png"));
    Gui.func_146110_a(width / 2 - 91, height - 29, 0.0F, 0.0F, 182, 5, 182.0F, 10.0F);
    Gui.func_146110_a(width / 2 - 91, height - 29, 0.0F, 5.0F, 182, 5, 182.0F, 10.0F);
    GL11.glColor3f(1.0F, 1.0F, 1.0F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\luckystats\GuiTalismanCalm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */