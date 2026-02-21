package fr.paladium.palamod.modules.paladium.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.EntitySplashPotion;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderEntityPotion extends Render {
  private static ResourceLocation nausea = new ResourceLocation("palamod:textures/entity/SicknessPotion.png");
  
  private static ResourceLocation web = new ResourceLocation("palamod:textures/entity/WebPotion.png");
  
  private float scale = 1.0F;
  
  public void renderProjectile(EntityThrowable projectile, double x, double y, double z) {
    GL11.glPushMatrix();
    func_110777_b((Entity)projectile);
    GL11.glTranslatef((float)x, (float)y, (float)z);
    GL11.glEnable(32826);
    GL11.glScalef(this.scale * 0.5F, this.scale * 0.5F, this.scale * 0.5F);
    Tessellator tessellator = Tessellator.field_78398_a;
    float minU = 0.0F;
    float maxU = 1.0F;
    float minV = 0.0F;
    float maxV = 1.0F;
    float f7 = 1.0F;
    float f8 = 0.5F;
    float f9 = 0.25F;
    GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
    tessellator.func_78382_b();
    tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
    tessellator.func_78374_a((0.0F - f8), (0.0F - f9), 0.0D, minU, maxV);
    tessellator.func_78374_a((f7 - f8), (0.0F - f9), 0.0D, maxU, maxV);
    tessellator.func_78374_a((f7 - f8), (1.0F - f9), 0.0D, maxU, minV);
    tessellator.func_78374_a((0.0F - f8), (1.0F - f9), 0.0D, minU, minV);
    tessellator.func_78381_a();
    GL11.glDisable(32826);
    GL11.glPopMatrix();
  }
  
  public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
    renderProjectile((EntityThrowable)par1Entity, par2, par4, par6);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    if (entity instanceof fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.entities.EntityCorruptedSplashPotion)
      return nausea; 
    EntitySplashPotion e = (EntitySplashPotion)entity;
    switch (e.getDamageValue()) {
      case 0:
        return nausea;
      case 1:
        return web;
    } 
    return nausea;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\RenderEntityPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */