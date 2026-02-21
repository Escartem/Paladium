package fr.paladium.palamod.modules.luckyblock.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderPalachat extends RenderLiving {
  private static final ResourceLocation ocelotTextures = new ResourceLocation("palamod:textures/entity/palachat.png");
  
  public RenderPalachat(ModelBase model, float shadow) {
    super(model, shadow);
  }
  
  public void doRender(EntityOcelot p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    super.func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
  
  protected ResourceLocation getEntityTexture(EntityOcelot p_110775_1_) {
    this;
    return ocelotTextures;
  }
  
  protected void preRenderCallback(EntityOcelot p_77041_1_, float p_77041_2_) {
    super.func_77041_b((EntityLivingBase)p_77041_1_, p_77041_2_);
    if (p_77041_1_.func_70909_n())
      GL11.glScalef(0.8F, 0.8F, 0.8F); 
  }
  
  public void func_76986_a(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    doRender((EntityOcelot)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
  
  protected void func_77041_b(EntityLivingBase p_77041_1_, float p_77041_2_) {
    preRenderCallback((EntityOcelot)p_77041_1_, p_77041_2_);
  }
  
  public void func_76986_a(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    doRender((EntityOcelot)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return getEntityTexture((EntityOcelot)p_110775_1_);
  }
  
  public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    doRender((EntityOcelot)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderPalachat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */