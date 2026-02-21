package fr.paladium.palamod.modules.palaboss.client.renderer;

import fr.paladium.palamod.modules.palaboss.client.models.ModelUlcan;
import fr.paladium.palamod.modules.palaboss.common.boss.BossRegistry;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityUlcan;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderUlcan extends RenderLiving {
  private static final ResourceLocation ulcanTexture = new ResourceLocation("palamod", "textures/entity/boss/ulcan.png");
  
  public RenderUlcan() {
    super((ModelBase)new ModelUlcan(), 0.05F);
    func_77042_a(this.field_77045_g);
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return ulcanTexture;
  }
  
  protected void func_77041_b(EntityLivingBase p_77041_1_, float p_77041_2_) {
    EntityUlcan ulcan = (EntityUlcan)p_77041_1_;
    float sizeMultiplier = BossRegistry.INSTANCE.getBossAttributes(ulcan.getClass()).getAttributeInteger("sizeMultiplier", 1);
    GL11.glScalef(sizeMultiplier, sizeMultiplier, sizeMultiplier);
  }
  
  protected void func_77036_a(EntityLivingBase p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_) {
    super.func_77036_a(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\renderer\RenderUlcan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */