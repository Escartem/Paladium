package fr.paladium.palamod.modules.alchimiste.client.renderer;

import fr.paladium.palamod.modules.alchimiste.common.entities.EntityGlueball;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.ItemPotion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderGlueball extends Render {
  public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    EntityGlueball entityGlueball = (EntityGlueball)p_76986_1_;
    IIcon iicon = entityGlueball.getItem().func_77617_a(0);
    if (iicon != null) {
      GL11.glPushMatrix();
      GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
      GL11.glEnable(32826);
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      func_110777_b(p_76986_1_);
      Tessellator tessellator = Tessellator.field_78398_a;
      if (iicon == ItemPotion.func_94589_d("bottle_splash")) {
        int i = PotionHelper.func_77915_a(((EntityPotion)p_76986_1_).func_70196_i(), false);
        float f2 = (i >> 16 & 0xFF) / 255.0F;
        float f3 = (i >> 8 & 0xFF) / 255.0F;
        float f4 = (i & 0xFF) / 255.0F;
        GL11.glColor3f(f2, f3, f4);
        GL11.glPushMatrix();
        func_77026_a(tessellator, ItemPotion.func_94589_d("overlay"));
        GL11.glPopMatrix();
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
      } 
      func_77026_a(tessellator, iicon);
      GL11.glDisable(32826);
      GL11.glPopMatrix();
    } 
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return TextureMap.field_110576_c;
  }
  
  private void func_77026_a(Tessellator p_77026_1_, IIcon p_77026_2_) {
    float f = p_77026_2_.func_94209_e();
    float f1 = p_77026_2_.func_94212_f();
    float f2 = p_77026_2_.func_94206_g();
    float f3 = p_77026_2_.func_94210_h();
    float f4 = 1.0F;
    float f5 = 0.5F;
    float f6 = 0.25F;
    GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
    p_77026_1_.func_78382_b();
    p_77026_1_.func_78375_b(0.0F, 1.0F, 0.0F);
    p_77026_1_.func_78374_a(-0.5D, -0.25D, 0.0D, f, f3);
    p_77026_1_.func_78374_a(0.5D, -0.25D, 0.0D, f1, f3);
    p_77026_1_.func_78374_a(0.5D, 0.75D, 0.0D, f1, f2);
    p_77026_1_.func_78374_a(-0.5D, 0.75D, 0.0D, f, f2);
    p_77026_1_.func_78381_a();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\client\renderer\RenderGlueball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */