package fr.paladium.palajobs.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.core.entity.EntityCustomFishHook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderCustomFish extends Render {
  private static final ResourceLocation PARTICLES_TEXTURE = new ResourceLocation("textures/particle/particles.png");
  
  public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float pitch) {
    doRender((EntityCustomFishHook)entity, x, y, z, yaw, pitch);
  }
  
  public void doRender(EntityCustomFishHook entity, double x, double y, double z, float yaw, float pitch) {
    GL11.glPushMatrix();
    GL11.glTranslated(x, y, z);
    GL11.glEnable(32826);
    GL11.glScalef(0.5F, 0.5F, 0.5F);
    func_110777_b((Entity)entity);
    Tessellator tessellator = Tessellator.field_78398_a;
    byte b0 = 1;
    byte b1 = 2;
    float f2 = (b0 * 8 + 0) / 128.0F;
    float f3 = (b0 * 8 + 8) / 128.0F;
    float f4 = (b1 * 8 + 0) / 128.0F;
    float f5 = (b1 * 8 + 8) / 128.0F;
    float f6 = 1.0F;
    float f7 = 0.5F;
    float f8 = 0.5F;
    GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
    tessellator.func_78382_b();
    tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
    tessellator.func_78374_a((0.0F - f7), (0.0F - f8), 0.0D, f2, f5);
    tessellator.func_78374_a((f6 - f7), (0.0F - f8), 0.0D, f3, f5);
    tessellator.func_78374_a((f6 - f7), (1.0F - f8), 0.0D, f3, f4);
    tessellator.func_78374_a((0.0F - f7), (1.0F - f8), 0.0D, f2, f4);
    tessellator.func_78381_a();
    GL11.glDisable(32826);
    GL11.glPopMatrix();
    EntityPlayer thrower = entity.getThrower();
    if (thrower != null) {
      float f9 = thrower.func_70678_g(pitch);
      float f10 = MathHelper.func_76126_a(MathHelper.func_76129_c(f9) * 3.1415927F);
      Vec3 vec3 = Vec3.func_72443_a(-0.5D, 0.03D, 0.8D);
      vec3.func_72440_a(-(thrower.field_70127_C + (thrower.field_70125_A - thrower.field_70127_C) * pitch) * 3.1415927F / 180.0F);
      vec3.func_72442_b(-(thrower.field_70126_B + (thrower.field_70177_z - thrower.field_70126_B) * pitch) * 3.1415927F / 180.0F);
      vec3.func_72442_b(f10 * 0.5F);
      vec3.func_72440_a(-f10 * 0.7F);
      double d3 = thrower.field_70169_q + (thrower.field_70165_t - thrower.field_70169_q) * pitch + vec3.field_72450_a;
      double d4 = thrower.field_70167_r + (thrower.field_70163_u - thrower.field_70167_r) * pitch + vec3.field_72448_b;
      double d5 = thrower.field_70166_s + (thrower.field_70161_v - thrower.field_70166_s) * pitch + vec3.field_72449_c;
      double d6 = (thrower == (Minecraft.func_71410_x()).field_71439_g) ? 0.0D : thrower.func_70047_e();
      if (this.field_76990_c.field_78733_k.field_74320_O > 0 || thrower != (Minecraft.func_71410_x()).field_71439_g) {
        float f11 = (thrower.field_70760_ar + (thrower.field_70761_aq - thrower.field_70760_ar) * pitch) * 3.1415927F / 180.0F;
        double d7 = MathHelper.func_76126_a(f11);
        double d9 = MathHelper.func_76134_b(f11);
        d3 = thrower.field_70169_q + (thrower.field_70165_t - thrower.field_70169_q) * pitch - d9 * 0.35D - d7 * 0.85D;
        d4 = thrower.field_70167_r + d6 + (thrower.field_70163_u - thrower.field_70167_r) * pitch - 0.45D;
        d5 = thrower.field_70166_s + (thrower.field_70161_v - thrower.field_70166_s) * pitch - d7 * 0.35D + d9 * 0.85D;
      } 
      double d14 = entity.field_70169_q + entity.field_70165_t - entity.field_70169_q;
      double d8 = entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * 1.0D + 0.25D;
      double d10 = entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * 1.0D;
      double d11 = (float)(d3 - d14);
      double d12 = (float)(d4 - d8);
      double d13 = (float)(d5 - d10);
      GL11.glDisable(3553);
      GL11.glDisable(2896);
      tessellator.func_78371_b(3);
      tessellator.func_78378_d(0);
      byte b2 = 16;
      for (int i = 0; i <= b2; i++) {
        float f12 = i / b2;
        tessellator.func_78377_a(x + d11 * f12, y + d12 * (f12 * f12 + f12) * 0.5D + 0.25D, z + d13 * f12);
      } 
      tessellator.func_78381_a();
      GL11.glEnable(2896);
      GL11.glEnable(3553);
    } 
  }
  
  public ResourceLocation getEntityTexture(EntityCustomFishHook entity) {
    return PARTICLES_TEXTURE;
  }
  
  public ResourceLocation func_110775_a(Entity entity) {
    return getEntityTexture((EntityCustomFishHook)entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\render\RenderCustomFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */