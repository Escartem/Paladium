package fr.paladium.palamod.modules.back2future.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.OpenGLHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelPlayer extends ModelBiped {
  public ModelRenderer bipedLeftArmwear;
  
  public ModelRenderer bipedRightArmwear;
  
  public ModelRenderer bipedLeftLegwear;
  
  public ModelRenderer bipedRightLegwear;
  
  public ModelRenderer bipedBodyWear;
  
  public ModelPlayer(float size, boolean isAlex) {
    super(size, 0.0F, 64, 64);
    this.field_78121_j = new ModelRenderer((ModelBase)this, 24, 0);
    this.field_78121_j.func_78790_a(-3.0F, -6.0F, -1.0F, 6, 6, 1, size);
    this.field_78122_k = new ModelRenderer((ModelBase)this, 0, 0);
    this.field_78122_k.func_78787_b(64, 32);
    this.field_78122_k.func_78790_a(-5.0F, 0.0F, -1.0F, 10, 16, 1, size);
    if (isAlex) {
      this.field_78113_g = new ModelRenderer((ModelBase)this, 32, 48);
      this.field_78113_g.func_78790_a(-1.0F, -2.0F, -2.0F, 3, 12, 4, size);
      this.field_78113_g.func_78793_a(5.0F, 2.5F, 0.0F);
      this.field_78112_f = new ModelRenderer((ModelBase)this, 40, 16);
      this.field_78112_f.func_78790_a(-2.0F, -2.0F, -2.0F, 3, 12, 4, size);
      this.field_78112_f.func_78793_a(-5.0F, 2.5F, 0.0F);
      this.bipedLeftArmwear = new ModelRenderer((ModelBase)this, 48, 48);
      this.bipedLeftArmwear.func_78790_a(-1.0F, -2.0F, -2.0F, 3, 12, 4, size + 0.25F);
      this.bipedLeftArmwear.func_78793_a(5.0F, 2.5F, 0.0F);
      this.bipedRightArmwear = new ModelRenderer((ModelBase)this, 40, 32);
      this.bipedRightArmwear.func_78790_a(-2.0F, -2.0F, -2.0F, 3, 12, 4, size + 0.25F);
      this.bipedRightArmwear.func_78793_a(-5.0F, 2.5F, 10.0F);
    } else {
      this.field_78113_g = new ModelRenderer((ModelBase)this, 32, 48);
      this.field_78113_g.func_78790_a(-1.0F, -2.0F, -2.0F, 4, 12, 4, size);
      this.field_78113_g.func_78793_a(5.0F, 2.0F, 0.0F);
      this.bipedLeftArmwear = new ModelRenderer((ModelBase)this, 48, 48);
      this.bipedLeftArmwear.func_78790_a(-1.0F, -2.0F, -2.0F, 4, 12, 4, size + 0.25F);
      this.bipedLeftArmwear.func_78793_a(5.0F, 2.0F, 0.0F);
      this.bipedRightArmwear = new ModelRenderer((ModelBase)this, 40, 32);
      this.bipedRightArmwear.func_78790_a(-3.0F, -2.0F, -2.0F, 4, 12, 4, size + 0.25F);
      this.bipedRightArmwear.func_78793_a(-5.0F, 2.0F, 10.0F);
    } 
    this.field_78124_i = new ModelRenderer((ModelBase)this, 16, 48);
    this.field_78124_i.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 12, 4, size);
    this.field_78124_i.func_78793_a(1.9F, 12.0F, 0.0F);
    this.bipedLeftLegwear = new ModelRenderer((ModelBase)this, 0, 48);
    this.bipedLeftLegwear.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 12, 4, size + 0.25F);
    this.bipedLeftLegwear.func_78793_a(1.9F, 12.0F, 0.0F);
    this.bipedRightLegwear = new ModelRenderer((ModelBase)this, 0, 32);
    this.bipedRightLegwear.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 12, 4, size + 0.25F);
    this.bipedRightLegwear.func_78793_a(-1.9F, 12.0F, 0.0F);
    this.bipedBodyWear = new ModelRenderer((ModelBase)this, 16, 32);
    this.bipedBodyWear.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 12, 4, size + 0.25F);
    this.bipedBodyWear.func_78793_a(0.0F, 0.0F, 0.0F);
  }
  
  public void func_78088_a(Entity entity, float f0, float f1, float f2, float f3, float f4, float scale) {
    func_78087_a(f0, f1, f2, f3, f4, scale, entity);
    if (this.field_78091_s) {
      float f6 = 2.0F;
      OpenGLHelper.pushMatrix();
      OpenGLHelper.scale(1.5F / f6, 1.5F / f6, 1.5F / f6);
      OpenGLHelper.translate(0.0F, 16.0F * scale, 0.0F);
      this.field_78116_c.func_78785_a(scale);
      OpenGLHelper.popMatrix();
      OpenGLHelper.pushMatrix();
      OpenGLHelper.scale(1.0F / f6, 1.0F / f6, 1.0F / f6);
      OpenGLHelper.translate(0.0F, 24.0F * scale, 0.0F);
      this.field_78115_e.func_78785_a(scale);
      this.field_78112_f.func_78785_a(scale);
      this.field_78113_g.func_78785_a(scale);
      this.field_78123_h.func_78785_a(scale);
      this.field_78124_i.func_78785_a(scale);
      OpenGLHelper.popMatrix();
    } else {
      this.field_78116_c.func_78785_a(scale);
      this.field_78115_e.func_78785_a(scale);
      this.field_78112_f.func_78785_a(scale);
      this.field_78113_g.func_78785_a(scale);
      this.field_78123_h.func_78785_a(scale);
      this.field_78124_i.func_78785_a(scale);
      OpenGLHelper.enableBlend();
      OpenGLHelper.blendFunc(770, 771);
      this.field_78114_d.func_78785_a(scale);
      OpenGLHelper.disableBlend();
    } 
    OpenGLHelper.pushMatrix();
    OpenGLHelper.enableBlend();
    OpenGLHelper.blendFunc(770, 771);
    if (this.field_78091_s) {
      float f6 = 2.0F;
      OpenGLHelper.scale(1.0F / f6, 1.0F / f6, 1.0F / f6);
      OpenGLHelper.translate(0.0F, 24.0F * scale, 0.0F);
      this.bipedLeftLegwear.func_78785_a(scale);
      this.bipedRightLegwear.func_78785_a(scale);
      this.bipedLeftArmwear.func_78785_a(scale);
      this.bipedRightArmwear.func_78785_a(scale);
      this.bipedBodyWear.func_78785_a(scale);
    } else {
      this.bipedLeftLegwear.func_78785_a(scale);
      this.bipedRightLegwear.func_78785_a(scale);
      this.bipedLeftArmwear.func_78785_a(scale);
      this.bipedRightArmwear.func_78785_a(scale);
      this.bipedBodyWear.func_78785_a(scale);
    } 
    OpenGLHelper.disableBlend();
    OpenGLHelper.popMatrix();
  }
  
  public void func_78087_a(float f0, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.func_78087_a(f0, f1, f2, f3, f4, f5, entity);
    copyModelAngles(this.field_78124_i, this.bipedLeftLegwear);
    copyModelAngles(this.field_78123_h, this.bipedRightLegwear);
    copyModelAngles(this.field_78113_g, this.bipedLeftArmwear);
    copyModelAngles(this.field_78112_f, this.bipedRightArmwear);
    copyModelAngles(this.field_78115_e, this.bipedBodyWear);
  }
  
  private void copyModelAngles(ModelRenderer source, ModelRenderer dest) {
    dest.field_78795_f = source.field_78795_f;
    dest.field_78796_g = source.field_78796_g;
    dest.field_78808_h = source.field_78808_h;
    dest.field_78800_c = source.field_78800_c;
    dest.field_78797_d = source.field_78797_d;
    dest.field_78798_e = source.field_78798_e;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\model\ModelPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */