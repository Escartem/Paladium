package fr.paladium.palamod.modules.paladium.client.render;

import fr.paladium.palamod.modules.paladium.common.entities.projectiles.DynamiteNinjaEntity;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderDynamiteNinja extends Render {
  private static final ResourceLocation textureDynamite = new ResourceLocation("palamod:textures/entity/dynamite.png");
  
  private static final ResourceLocation textureNinja = new ResourceLocation("palamod:textures/entity/dynamite_ninja.png");
  
  public static final int DEFAULT = 0;
  
  public static final int NINJA = 1;
  
  public int type;
  
  public float pitch;
  
  public RenderDynamiteNinja(int type) {
    this.pitch = 40.0F;
    this.type = type;
  }
  
  public void renderDynamite(DynamiteNinjaEntity e, double d, double d1, double d2, float f, float f1) {
    try {
      GL11.glPushMatrix();
      func_110777_b((Entity)e);
      GL11.glTranslatef((float)d, (float)d1, (float)d2);
      GL11.glRotatef(e.field_70177_z + 90.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(e.field_70127_C + (e.field_70125_A - e.field_70127_C) * f1, 0.0F, 0.0F, 1.0F);
      Tessellator tessellator = Tessellator.field_78398_a;
      int i = 0;
      float f2 = 0.0F;
      float f3 = 0.5F;
      float f4 = (0 + i * 10) / 32.0F;
      float f5 = (5 + i * 10) / 32.0F;
      float f6 = 0.0F;
      float f7 = 0.15625F;
      float f8 = (5 + i * 10) / 32.0F;
      float f9 = (10 + i * 10) / 32.0F;
      float f10 = 0.05625F;
      GL11.glEnable(32826);
      float f11 = -f1;
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
      for (int j = 0; j < 4; j++) {
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
    } catch (IllegalStateException exception) {
      System.out.println("Oups une erreur est survenue lors du load du render de la ninja dynamite, Message: " + exception.getMessage());
    } 
  }
  
  public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {
    renderDynamite((DynamiteNinjaEntity)entity, d, d1, d2, f, f1);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    if (this.type == 1)
      return textureNinja; 
    return textureDynamite;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\RenderDynamiteNinja.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */