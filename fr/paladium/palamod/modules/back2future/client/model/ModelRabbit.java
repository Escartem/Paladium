package fr.paladium.palamod.modules.back2future.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.OpenGLHelper;
import fr.paladium.palamod.modules.back2future.entities.EntityRabbit;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelRabbit extends ModelBase {
  protected ModelRenderer rabbitLeftFoot;
  
  protected ModelRenderer rabbitRightFoot;
  
  protected ModelRenderer rabbitLeftThigh;
  
  protected ModelRenderer rabbitRightThigh;
  
  protected ModelRenderer rabbitBody;
  
  protected ModelRenderer rabbitTail;
  
  protected ModelRenderer rabbitLeftArm;
  
  protected ModelRenderer rabbitRightArm;
  
  protected ModelRenderer rabbitHead;
  
  protected ModelRenderer rabbitRightEar;
  
  protected ModelRenderer rabbitLeftEar;
  
  protected ModelRenderer rabbitNose;
  
  private float field_178701_m = 0.0F;
  
  public ModelRabbit() {
    func_78085_a("head.main", 0, 0);
    func_78085_a("head.nose", 0, 24);
    func_78085_a("head.ear1", 0, 10);
    func_78085_a("head.ear2", 6, 10);
    this.rabbitLeftFoot = new ModelRenderer(this, 26, 24);
    this.rabbitLeftFoot.func_78789_a(-1.0F, 5.5F, -3.7F, 2, 1, 7);
    this.rabbitLeftFoot.func_78793_a(3.0F, 17.5F, 3.7F);
    this.rabbitLeftFoot.field_78809_i = true;
    setRotationOffset(this.rabbitLeftFoot, 0.0F, 0.0F, 0.0F);
    this.rabbitRightFoot = new ModelRenderer(this, 8, 24);
    this.rabbitRightFoot.func_78789_a(-1.0F, 5.5F, -3.7F, 2, 1, 7);
    this.rabbitRightFoot.func_78793_a(-3.0F, 17.5F, 3.7F);
    this.rabbitRightFoot.field_78809_i = true;
    setRotationOffset(this.rabbitRightFoot, 0.0F, 0.0F, 0.0F);
    this.rabbitLeftThigh = new ModelRenderer(this, 30, 15);
    this.rabbitLeftThigh.func_78789_a(-1.0F, 0.0F, 0.0F, 2, 4, 5);
    this.rabbitLeftThigh.func_78793_a(3.0F, 17.5F, 3.7F);
    this.rabbitLeftThigh.field_78809_i = true;
    setRotationOffset(this.rabbitLeftThigh, -0.34906584F, 0.0F, 0.0F);
    this.rabbitRightThigh = new ModelRenderer(this, 16, 15);
    this.rabbitRightThigh.func_78789_a(-1.0F, 0.0F, 0.0F, 2, 4, 5);
    this.rabbitRightThigh.func_78793_a(-3.0F, 17.5F, 3.7F);
    this.rabbitRightThigh.field_78809_i = true;
    setRotationOffset(this.rabbitRightThigh, -0.34906584F, 0.0F, 0.0F);
    this.rabbitBody = new ModelRenderer(this, 0, 0);
    this.rabbitBody.func_78789_a(-3.0F, -2.0F, -10.0F, 6, 5, 10);
    this.rabbitBody.func_78793_a(0.0F, 19.0F, 8.0F);
    this.rabbitBody.field_78809_i = true;
    setRotationOffset(this.rabbitBody, -0.34906584F, 0.0F, 0.0F);
    this.rabbitLeftArm = new ModelRenderer(this, 8, 15);
    this.rabbitLeftArm.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 7, 2);
    this.rabbitLeftArm.func_78793_a(3.0F, 17.0F, -1.0F);
    this.rabbitLeftArm.field_78809_i = true;
    setRotationOffset(this.rabbitLeftArm, -0.17453292F, 0.0F, 0.0F);
    this.rabbitRightArm = new ModelRenderer(this, 0, 15);
    this.rabbitRightArm.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 7, 2);
    this.rabbitRightArm.func_78793_a(-3.0F, 17.0F, -1.0F);
    this.rabbitRightArm.field_78809_i = true;
    setRotationOffset(this.rabbitRightArm, -0.17453292F, 0.0F, 0.0F);
    this.rabbitHead = new ModelRenderer(this, 32, 0);
    this.rabbitHead.func_78789_a(-2.5F, -4.0F, -5.0F, 5, 4, 5);
    this.rabbitHead.func_78793_a(0.0F, 16.0F, -1.0F);
    this.rabbitHead.field_78809_i = true;
    setRotationOffset(this.rabbitHead, 0.0F, 0.0F, 0.0F);
    this.rabbitRightEar = new ModelRenderer(this, 52, 0);
    this.rabbitRightEar.func_78789_a(-2.5F, -9.0F, -1.0F, 2, 5, 1);
    this.rabbitRightEar.func_78793_a(0.0F, 16.0F, -1.0F);
    this.rabbitRightEar.field_78809_i = true;
    setRotationOffset(this.rabbitRightEar, 0.0F, -0.2617994F, 0.0F);
    this.rabbitLeftEar = new ModelRenderer(this, 58, 0);
    this.rabbitLeftEar.func_78789_a(0.5F, -9.0F, -1.0F, 2, 5, 1);
    this.rabbitLeftEar.func_78793_a(0.0F, 16.0F, -1.0F);
    this.rabbitLeftEar.field_78809_i = true;
    setRotationOffset(this.rabbitLeftEar, 0.0F, 0.2617994F, 0.0F);
    this.rabbitTail = new ModelRenderer(this, 52, 6);
    this.rabbitTail.func_78789_a(-1.5F, -1.5F, 0.0F, 3, 3, 2);
    this.rabbitTail.func_78793_a(0.0F, 20.0F, 7.0F);
    this.rabbitTail.field_78809_i = true;
    setRotationOffset(this.rabbitTail, -0.3490659F, 0.0F, 0.0F);
    this.rabbitNose = new ModelRenderer(this, 32, 9);
    this.rabbitNose.func_78789_a(-0.5F, -2.5F, -5.5F, 1, 1, 1);
    this.rabbitNose.func_78793_a(0.0F, 16.0F, -1.0F);
    this.rabbitNose.field_78809_i = true;
    setRotationOffset(this.rabbitNose, 0.0F, 0.0F, 0.0F);
  }
  
  private void setRotationOffset(ModelRenderer p_178691_1_, float p_178691_2_, float p_178691_3_, float p_178691_4_) {
    p_178691_1_.field_78795_f = p_178691_2_;
    p_178691_1_.field_78796_g = p_178691_3_;
    p_178691_1_.field_78808_h = p_178691_4_;
  }
  
  public void func_78088_a(Entity entityIn, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale) {
    func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale, entityIn);
    if (this.field_78091_s) {
      float f6 = 2.0F;
      OpenGLHelper.pushMatrix();
      OpenGLHelper.translate(0.0F, 5.0F * scale, 2.0F * scale);
      this.rabbitHead.func_78785_a(scale);
      this.rabbitLeftEar.func_78785_a(scale);
      this.rabbitRightEar.func_78785_a(scale);
      this.rabbitNose.func_78785_a(scale);
      OpenGLHelper.popMatrix();
      OpenGLHelper.pushMatrix();
      OpenGLHelper.scale(1.0F / f6, 1.0F / f6, 1.0F / f6);
      OpenGLHelper.translate(0.0F, 24.0F * scale, 0.0F);
      this.rabbitLeftFoot.func_78785_a(scale);
      this.rabbitRightFoot.func_78785_a(scale);
      this.rabbitLeftThigh.func_78785_a(scale);
      this.rabbitRightThigh.func_78785_a(scale);
      this.rabbitBody.func_78785_a(scale);
      this.rabbitLeftArm.func_78785_a(scale);
      this.rabbitRightArm.func_78785_a(scale);
      this.rabbitTail.func_78785_a(scale);
      OpenGLHelper.popMatrix();
    } else {
      this.rabbitLeftFoot.func_78785_a(scale);
      this.rabbitRightFoot.func_78785_a(scale);
      this.rabbitLeftThigh.func_78785_a(scale);
      this.rabbitRightThigh.func_78785_a(scale);
      this.rabbitBody.func_78785_a(scale);
      this.rabbitLeftArm.func_78785_a(scale);
      this.rabbitRightArm.func_78785_a(scale);
      this.rabbitHead.func_78785_a(scale);
      this.rabbitRightEar.func_78785_a(scale);
      this.rabbitLeftEar.func_78785_a(scale);
      this.rabbitTail.func_78785_a(scale);
      this.rabbitNose.func_78785_a(scale);
    } 
  }
  
  public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
    float f6 = p_78087_3_ - p_78087_7_.field_70173_aa;
    EntityRabbit entityrabbit = (EntityRabbit)p_78087_7_;
    this.rabbitLeftEar.field_78795_f = p_78087_5_ * 0.017453292F;
    this.rabbitHead.field_78796_g = p_78087_4_ * 0.017453292F;
    this.rabbitNose.field_78796_g -= 0.2617994F;
    this.rabbitNose.field_78796_g += 0.2617994F;
    this.field_178701_m = MathHelper.func_76126_a(entityrabbit.func_175521_o(f6) * 3.1415927F);
    this.rabbitRightThigh.field_78795_f = (this.field_178701_m * 50.0F - 21.0F) * 0.017453292F;
    this.rabbitRightFoot.field_78795_f = this.field_178701_m * 50.0F * 0.017453292F;
    this.rabbitRightArm.field_78795_f = (this.field_178701_m * -40.0F - 11.0F) * 0.017453292F;
  }
  
  public void func_78086_a(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\model\ModelRabbit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */