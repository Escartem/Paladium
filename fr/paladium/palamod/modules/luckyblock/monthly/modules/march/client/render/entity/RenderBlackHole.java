package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

public class RenderBlackHole extends Render {
  private static final ResourceLocation texture = new ResourceLocation("palamod:textures/entities/blackhole.png");
  
  public static int blackHolePreRenderSphere;
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return texture;
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
    GL11.glCallList(blackHolePreRenderSphere);
    GL11.glPopMatrix();
  }
  
  public static void blackHolePreRender() {
    Sphere sphere = new Sphere();
    sphere.setDrawStyle(100012);
    sphere.setNormals(100000);
    blackHolePreRenderSphere = GL11.glGenLists(1);
    GL11.glNewList(blackHolePreRenderSphere, 4864);
    Minecraft.func_71410_x().func_110434_K().func_110577_a(texture);
    sphere.draw(0.5F, 32, 32);
    GL11.glEndList();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\render\entity\RenderBlackHole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */