package fr.paladium.palamod.modules.palaboss.client.renderer;

import fr.paladium.palamod.modules.palaboss.client.models.ModelTobalt;
import fr.paladium.palamod.modules.palaboss.common.boss.BossRegistry;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityTobalt;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderTobalt extends RenderBiped {
  private static final ResourceLocation tobaltTexture = new ResourceLocation("palamod", "textures/entity/boss/tobalt.png");
  
  private static final ResourceLocation tobaltTextureStunt = new ResourceLocation("palamod", "textures/entity/boss/tobalt.png");
  
  private static final ResourceLocation tobaltChargeTexture = new ResourceLocation("palamod", "textures/entity/boss/tobalt.png");
  
  public RenderTobalt() {
    super((ModelBiped)new ModelTobalt(), 0.05F);
    func_77042_a(this.field_77045_g);
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    EntityTobalt tobalt = (EntityTobalt)p_110775_1_;
    return tobalt.isInCharge() ? tobaltChargeTexture : (
      tobalt.isClientStunt() ? tobaltTextureStunt : tobaltTexture);
  }
  
  protected void func_77041_b(EntityLivingBase p_77041_1_, float p_77041_2_) {
    EntityTobalt tobalt = (EntityTobalt)p_77041_1_;
    float sizeMultiplier = BossRegistry.INSTANCE.getBossAttributes(tobalt.getClass()).getAttributeInteger("sizeMultiplier", 1);
    GL11.glScalef(sizeMultiplier, sizeMultiplier, sizeMultiplier);
  }
  
  protected void func_77036_a(EntityLivingBase p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_) {
    super.func_77036_a(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
  }
  
  protected int func_77032_a(EntityLiving p_77032_1_, int p_77032_2_, float p_77032_3_) {
    return -1;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\renderer\RenderTobalt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */