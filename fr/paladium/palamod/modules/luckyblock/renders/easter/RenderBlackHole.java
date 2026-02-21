package fr.paladium.palamod.modules.luckyblock.renders.easter;

import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderBlackHole extends Render {
  protected ResourceLocation func_110775_a(Entity entity) {
    return new ResourceLocation("palamod:textures/entity/blackhole.png");
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
    float size = 0.025F * entity.field_70173_aa;
    if (size > 2.0F)
      size = 2.0F; 
    float sizeY = 0.025F * entity.field_70173_aa;
    if (sizeY > 1.0F)
      sizeY = 1.0F; 
    GL11.glPushMatrix();
    GL11.glTranslated(x, y, z);
    GL11.glScalef(size, sizeY, size);
    GL11.glEnable(3042);
    GL11.glDepthMask(false);
    GL11.glBlendFunc(770, 771);
    GL11.glColor4f(0.0F, 0.0F, 0.0F, 1.0F);
    GL11.glEnable(3008);
    GL11.glCallList(ClientProxy.blackHolePreRenderSphere);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\easter\RenderBlackHole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */