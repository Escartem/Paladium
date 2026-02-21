package fr.paladium.palamod.modules.miner.render;

import fr.paladium.palamod.modules.miner.entity.EntityGodVillager;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderGodVillager extends RenderLiving {
  private static final ResourceLocation christmasTexture = new ResourceLocation("palamod:textures/entity/christmas_villager.png");
  
  private static final ResourceLocation voyantTexture = new ResourceLocation("palamod:textures/entity/villanull_villager.png");
  
  private static final ResourceLocation villanullTexture = new ResourceLocation("palamod:textures/entity/villanull_villager.png");
  
  protected ModelVillager villagerModel;
  
  public RenderGodVillager() {
    super((ModelBase)new ModelVillager(0.0F), 0.5F);
    this.villagerModel = (ModelVillager)this.field_77045_g;
  }
  
  protected int shouldRenderPass(EntityGodVillager p_77032_1_, int p_77032_2_, float p_77032_3_) {
    return -1;
  }
  
  public void doRender(EntityGodVillager p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    super.func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
  
  protected ResourceLocation getEntityTexture(EntityGodVillager entity) {
    return (entity.getType() == 0) ? christmasTexture : ((entity.getType() == 1) ? christmasTexture : ((entity.getType() == 2) ? voyantTexture : villanullTexture));
  }
  
  protected void renderEquippedItems(EntityGodVillager p_77029_1_, float p_77029_2_) {
    super.func_77029_c((EntityLivingBase)p_77029_1_, p_77029_2_);
  }
  
  protected void preRenderCallback(EntityGodVillager p_77041_1_, float p_77041_2_) {
    float f1 = 0.9375F;
    if (p_77041_1_.func_70874_b() < 0) {
      f1 = (float)(f1 * 0.5D);
      this.field_76989_e = 0.25F;
    } else {
      this.field_76989_e = 0.5F;
    } 
    GL11.glScalef(f1, f1, f1);
  }
  
  public void func_76986_a(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    doRender((EntityGodVillager)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
  
  protected void func_77041_b(EntityLivingBase p_77041_1_, float p_77041_2_) {
    preRenderCallback((EntityGodVillager)p_77041_1_, p_77041_2_);
  }
  
  protected int func_77032_a(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
    return shouldRenderPass((EntityGodVillager)p_77032_1_, p_77032_2_, p_77032_3_);
  }
  
  protected void func_77029_c(EntityLivingBase p_77029_1_, float p_77029_2_) {
    renderEquippedItems((EntityGodVillager)p_77029_1_, p_77029_2_);
  }
  
  public void func_76986_a(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    doRender((EntityGodVillager)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return getEntityTexture((EntityGodVillager)p_110775_1_);
  }
  
  public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    doRender((EntityGodVillager)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\render\RenderGodVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */