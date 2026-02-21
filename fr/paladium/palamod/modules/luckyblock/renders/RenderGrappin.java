package fr.paladium.palamod.modules.luckyblock.renders;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderGrappin extends Render {
  private static final ResourceLocation arrowTextures = new ResourceLocation("palamod:textures/entity/grappin.png");
  
  private static final String __OBFID = "CL_00000978";
  
  public void doRender(EntityArrow p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    func_110777_b((Entity)p_76986_1_);
    GL11.glPushMatrix();
    GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
    GL11.glRotatef(p_76986_1_.field_70126_B + (p_76986_1_.field_70177_z - p_76986_1_.field_70126_B) * p_76986_9_ - 90.0F, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(p_76986_1_.field_70127_C + (p_76986_1_.field_70125_A - p_76986_1_.field_70127_C) * p_76986_9_, 0.0F, 0.0F, 1.0F);
    Tessellator tessellator = Tessellator.field_78398_a;
    byte b0 = 0;
    float f2 = 0.0F;
    float f3 = 0.5F;
    float f4 = (0 + b0 * 10) / 32.0F;
    float f5 = (5 + b0 * 10) / 32.0F;
    float f6 = 0.0F;
    float f7 = 0.15625F;
    float f8 = (5 + b0 * 10) / 32.0F;
    float f9 = (10 + b0 * 10) / 32.0F;
    float f10 = 0.05625F;
    GL11.glEnable(32826);
    float f11 = p_76986_1_.field_70249_b - p_76986_9_;
    if (f11 > 0.0F) {
      float f12 = -MathHelper.func_76126_a(f11 * 3.0F) * f11;
      GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
    } 
    GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
    GL11.glScalef(f10, f10, f10);
    GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
    GL11.glNormal3f(f10, 0.0F, 0.0F);
    tessellator.func_78382_b();
    tessellator.func_78374_a(-7.0D, -2.0D, -2.0D, f6, f8);
    tessellator.func_78374_a(-7.0D, -2.0D, 2.0D, f7, f8);
    tessellator.func_78374_a(-7.0D, 2.0D, 2.0D, f7, f9);
    tessellator.func_78374_a(-7.0D, 2.0D, -2.0D, f6, f9);
    tessellator.func_78381_a();
    GL11.glNormal3f(-f10, 0.0F, 0.0F);
    tessellator.func_78382_b();
    tessellator.func_78374_a(-7.0D, 2.0D, -2.0D, f6, f8);
    tessellator.func_78374_a(-7.0D, 2.0D, 2.0D, f7, f8);
    tessellator.func_78374_a(-7.0D, -2.0D, 2.0D, f7, f9);
    tessellator.func_78374_a(-7.0D, -2.0D, -2.0D, f6, f9);
    tessellator.func_78381_a();
    for (int i = 0; i < 4; i++) {
      GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
      GL11.glNormal3f(0.0F, 0.0F, f10);
      tessellator.func_78382_b();
      tessellator.func_78374_a(-8.0D, -2.0D, 0.0D, f2, f4);
      tessellator.func_78374_a(8.0D, -2.0D, 0.0D, f3, f4);
      tessellator.func_78374_a(8.0D, 2.0D, 0.0D, f3, f5);
      tessellator.func_78374_a(-8.0D, 2.0D, 0.0D, f2, f5);
      tessellator.func_78381_a();
    } 
    GL11.glDisable(32826);
    GL11.glPopMatrix();
  }
  
  protected ResourceLocation getEntityTexture(EntityArrow p_110775_1_) {
    return arrowTextures;
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return getEntityTexture((EntityArrow)p_110775_1_);
  }
  
  public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    doRender((EntityArrow)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderGrappin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */