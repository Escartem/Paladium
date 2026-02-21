package fr.paladium.palamod.modules.luckyblock.gui;

import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiFishBowl extends Gui {
  public GuiFishBowl(Minecraft mc) {
    ScaledResolution res = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
    int width = res.func_78326_a();
    int height = res.func_78328_b();
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.2F);
    GuiUtils.drawImageTransparent(0.0D, 0.0D, new ResourceLocation("palamod", "textures/items/water.png"), width, height);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GuiUtils.drawImageTransparent(0.0D, 0.0D, new ResourceLocation("palamod", "textures/items/glass.png"), width, height);
    GuiUtils.drawImageTransparent((mc.field_71439_g.field_70173_aa % width), Math.abs((height / 100.0F) * Math.sin((mc.field_71439_g.field_70173_aa / 50.0F)) * 100.0D), new ResourceLocation("palamod", "textures/items/fish.png"), (width / 4), (width / 4));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\GuiFishBowl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */