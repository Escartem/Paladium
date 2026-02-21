package fr.paladium.palamod.modules.palaboss.client.gui;

import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.modules.palaboss.client.ClientProxy;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiPalaBossBar extends Gui {
  public GuiPalaBossBar(Minecraft mc) {
    ScaledResolution res = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
    int width = res.func_78326_a();
    int height = res.func_78328_b();
    float scale = res.func_78325_e();
    float x = width / 2.0F - width / 6.0F;
    float w = width / 3.0F;
    float y = height / 100.0F;
    float h = height / 15.0F;
    if (System.currentTimeMillis() % 200L < 10L) {
      EntityBossBase boss = null;
      for (Object entity : mc.field_71439_g.field_70170_p.field_72996_f) {
        if (entity instanceof EntityBossBase)
          boss = (EntityBossBase)entity; 
      } 
      ClientProxy.boss = boss;
    } 
    if (ClientProxy.boss == null)
      return; 
    float maxHealth = ClientProxy.boss.func_110138_aP();
    GL11.glPushMatrix();
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GuiUtils.drawImageTransparent(x, y, new ResourceLocation("palamod", "textures/gui/boss/bars/empty.png"), w, h);
    GL11.glPushMatrix();
    GL11.glScissor((int)(x * scale), (int)((y + h) * scale), (int)(w * scale / maxHealth * ClientProxy.boss.func_110143_aJ()), (int)(height * scale));
    GL11.glEnable(3089);
    GuiUtils.drawImageTransparent(x, y, new ResourceLocation("palamod", "textures/gui/boss/bars/" + ClientProxy.boss.name() + "/fill.png"), w, h);
    GL11.glDisable(3089);
    GL11.glPopMatrix();
    GuiUtils.drawImageTransparent(x, y, new ResourceLocation("palamod", "textures/gui/boss/bars/" + ClientProxy.boss.name() + "/text.png"), w, h);
    GuiUtils.drawImageTransparent(x, y, new ResourceLocation("palamod", "textures/gui/boss/bars/points.png"), w, h);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\gui\GuiPalaBossBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */