package fr.paladium.palamod.modules.luckyblock.renders;

import fr.paladium.palamod.modules.luckyblock.entity.EntityNervousBat;
import fr.paladium.palamod.modules.luckyblock.renders.models.ModelNervousBat;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderNervousBat extends RenderLiving {
  private static final ResourceLocation batTextures = new ResourceLocation("textures/entity/bat.png");
  
  private int renderedBatSize;
  
  private static final String __OBFID = "CL_00000979";
  
  public RenderNervousBat() {
    super((ModelBase)new ModelNervousBat(), 0.25F);
    this.renderedBatSize = ((ModelNervousBat)this.field_77045_g).getBatSize();
  }
  
  public void doRender(EntityNervousBat p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    int i = ((ModelNervousBat)this.field_77045_g).getBatSize();
    if (i != this.renderedBatSize) {
      this.renderedBatSize = i;
      this.field_77045_g = (ModelBase)new ModelNervousBat();
    } 
    super.func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
  
  protected ResourceLocation getEntityTexture(EntityNervousBat p_110775_1_) {
    return batTextures;
  }
  
  protected void preRenderCallback(EntityNervousBat p_77041_1_, float p_77041_2_) {
    GL11.glScalef(0.35F, 0.35F, 0.35F);
  }
  
  protected void renderLivingAt(EntityNervousBat p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_) {
    super.func_77039_a((EntityLivingBase)p_77039_1_, p_77039_2_, p_77039_4_, p_77039_6_);
  }
  
  protected void rotateCorpse(EntityNervousBat p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
    if (!p_77043_1_.getIsBatHanging()) {
      GL11.glTranslatef(0.0F, MathHelper.func_76134_b(p_77043_2_ * 0.3F) * 0.1F, 0.0F);
    } else {
      GL11.glTranslatef(0.0F, -0.1F, 0.0F);
    } 
    super.func_77043_a((EntityLivingBase)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
  }
  
  public void func_76986_a(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    doRender((EntityNervousBat)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
  
  protected void func_77041_b(EntityLivingBase p_77041_1_, float p_77041_2_) {
    preRenderCallback((EntityNervousBat)p_77041_1_, p_77041_2_);
  }
  
  protected void func_77043_a(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
    rotateCorpse((EntityNervousBat)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
  }
  
  protected void func_77039_a(EntityLivingBase p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_) {
    renderLivingAt((EntityNervousBat)p_77039_1_, p_77039_2_, p_77039_4_, p_77039_6_);
  }
  
  public void func_76986_a(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    doRender((EntityNervousBat)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return getEntityTexture((EntityNervousBat)p_110775_1_);
  }
  
  public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    doRender((EntityNervousBat)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderNervousBat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */