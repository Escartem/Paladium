package fr.paladium.palamod.modules.hunter.gui;

import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.modules.hunter.proxies.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiRituelProgress extends Gui {
  public GuiRituelProgress(Minecraft mc) {
    if (ClientProxy.rituel < 0)
      return; 
    ScaledResolution res = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
    int width = res.func_78326_a();
    int height = res.func_78328_b();
    float scale = res.func_78325_e();
    float x = width / 2.0F - width / 6.0F;
    float w = width / 3.0F;
    float y = height / 100.0F;
    float h = height / 15.0F;
    GuiUtils.drawImageTransparent(x, y, new ResourceLocation("palamod", "textures/gui/rituel/empty.png"), w, h);
    GL11.glPushMatrix();
    GL11.glScissor((int)(x * scale), (int)((y + h) * scale), (int)(w * scale / 25.0F * ClientProxy.rituel), (int)(height * scale));
    GL11.glEnable(3089);
    GuiUtils.drawImageTransparent(x, y, new ResourceLocation("palamod", "textures/gui/rituel/fill.png"), w, h, (ClientProxy.rituel == 25));
    GL11.glDisable(3089);
    GL11.glPopMatrix();
    GuiUtils.drawImageTransparent(x, y, new ResourceLocation("palamod", "textures/gui/rituel/title.png"), w, h);
    GuiUtils.drawImageTransparent(x, y, new ResourceLocation("palamod", "textures/gui/rituel/points.png"), w, h);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\gui\GuiRituelProgress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */