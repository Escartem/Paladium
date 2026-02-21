package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderAstronautZombie extends RenderBiped {
  private static final ResourceLocation zombieTextures = new ResourceLocation("palamod", "textures/entities/astronaut_zombie.png");
  
  private ModelBiped model;
  
  private ModelZombieVillager zombieVillagerModel;
  
  protected ModelBiped model1;
  
  protected ModelBiped model2;
  
  protected ModelBiped model3;
  
  protected ModelBiped model4;
  
  private int field_82431_q = 1;
  
  public RenderAstronautZombie() {
    super((ModelBiped)new ModelZombie(), 0.5F, 1.0F);
    this.model = this.field_77071_a;
    this.zombieVillagerModel = new ModelZombieVillager();
  }
  
  protected void func_82421_b() {
    this.field_82423_g = (ModelBiped)new ModelZombie(1.0F, true);
    this.field_82425_h = (ModelBiped)new ModelZombie(0.5F, true);
    this.model1 = this.field_82423_g;
    this.model2 = this.field_82425_h;
    this.model3 = (ModelBiped)new ModelZombieVillager(1.0F, 0.0F, true);
    this.model4 = (ModelBiped)new ModelZombieVillager(0.5F, 0.0F, true);
  }
  
  protected int shouldRenderPass(EntityZombie entity, int p_77032_2_, float p_77032_3_) {
    func_82427_a(entity);
    return super.func_77032_a((EntityLiving)entity, p_77032_2_, p_77032_3_);
  }
  
  public void doRender(EntityZombie entity, double x, double y, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    func_82427_a(entity);
    super.func_76986_a((EntityLiving)entity, x, y, p_76986_6_, p_76986_8_, p_76986_9_);
  }
  
  protected ResourceLocation getEntityTexture(EntityZombie zombie) {
    return zombieTextures;
  }
  
  protected void renderEquippedItems(EntityZombie p_77029_1_, float p_77029_2_) {
    func_82427_a(p_77029_1_);
    super.func_77029_c((EntityLiving)p_77029_1_, p_77029_2_);
  }
  
  private void func_82427_a(EntityZombie p_82427_1_) {
    if (p_82427_1_.func_82231_m()) {
      if (this.field_82431_q != this.zombieVillagerModel.func_82897_a()) {
        this.zombieVillagerModel = new ModelZombieVillager();
        this.field_82431_q = this.zombieVillagerModel.func_82897_a();
        this.model3 = (ModelBiped)new ModelZombieVillager(1.0F, 0.0F, true);
        this.model4 = (ModelBiped)new ModelZombieVillager(0.5F, 0.0F, true);
      } 
      this.field_77045_g = (ModelBase)this.zombieVillagerModel;
      this.field_82423_g = this.model3;
      this.field_82425_h = this.model4;
    } else {
      this.field_77045_g = (ModelBase)this.model;
      this.field_82423_g = this.model1;
      this.field_82425_h = this.model2;
    } 
    this.field_77071_a = (ModelBiped)this.field_77045_g;
  }
  
  protected void rotateCorpse(EntityZombie p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
    if (p_77043_1_.func_82230_o())
      p_77043_3_ += (float)(Math.cos(p_77043_1_.field_70173_aa * 3.25D) * Math.PI * 0.25D); 
    super.func_77043_a((EntityLivingBase)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
  }
  
  protected void func_77029_c(EntityLiving p_77029_1_, float p_77029_2_) {
    renderEquippedItems((EntityZombie)p_77029_1_, p_77029_2_);
  }
  
  protected ResourceLocation func_110775_a(EntityLiving p_110775_1_) {
    return getEntityTexture((EntityZombie)p_110775_1_);
  }
  
  public void func_76986_a(EntityLiving entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    doRender((EntityZombie)entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
  
  protected int func_77032_a(EntityLiving entity, int p_77032_2_, float p_77032_3_) {
    return shouldRenderPass((EntityZombie)entity, p_77032_2_, p_77032_3_);
  }
  
  protected int func_77032_a(EntityLivingBase entity, int p_77032_2_, float p_77032_3_) {
    return shouldRenderPass((EntityZombie)entity, p_77032_2_, p_77032_3_);
  }
  
  protected void func_77029_c(EntityLivingBase entity, float p_77029_2_) {
    renderEquippedItems((EntityZombie)entity, p_77029_2_);
  }
  
  protected void func_77043_a(EntityLivingBase entity, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
    rotateCorpse((EntityZombie)entity, p_77043_2_, p_77043_3_, p_77043_4_);
  }
  
  public void func_76986_a(EntityLivingBase entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    doRender((EntityZombie)entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return getEntityTexture((EntityZombie)entity);
  }
  
  public void func_76986_a(Entity entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    doRender((EntityZombie)entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\render\entity\RenderAstronautZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */