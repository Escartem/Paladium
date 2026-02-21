package fr.paladium.palamod.modules.paladium.dummy;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelDummy extends ModelBiped {
  public ModelRenderer standPlate;
  
  public ModelDummy(float size, float p_i1149_2_) {
    this(size, p_i1149_2_, 64, 64);
  }
  
  public ModelDummy(float size, float p_i1149_2_, int xw, int yw) {
    super(size, p_i1149_2_, xw, yw);
    this.field_78112_f = new ModelRenderer((ModelBase)this, 40, 16);
    this.field_78112_f.func_78790_a(-3.01F, 1.0F, -2.01F, 4, 8, 4, size);
    this.field_78112_f.func_78793_a(-2.5F, 2.0F + p_i1149_2_, 0.0F);
    this.field_78113_g = new ModelRenderer((ModelBase)this, 40, 16);
    this.field_78113_g.field_78809_i = true;
    this.field_78113_g.func_78790_a(-1.01F, 1.0F, -2.01F, 4, 8, 4, size);
    this.field_78113_g.func_78793_a(2.5F, 2.0F + p_i1149_2_, 0.0F);
    this.field_78124_i = new ModelRenderer((ModelBase)this, 0, 16);
    this.field_78124_i.func_78790_a(-2.0F, -12.0F, -2.0F, 4, 12, 4, size);
    this.field_78124_i.func_78793_a(0.0F, 24.0F + p_i1149_2_, 0.0F);
    this.field_78123_h = new ModelRenderer((ModelBase)this, 0, 0);
    this.standPlate = new ModelRenderer((ModelBase)this, 0, 32);
    this.standPlate.func_78790_a(-8.0F, 11.5F, -8.0F, 16, 1, 16, size);
    this.standPlate.func_78793_a(0.0F, 12.0F + p_i1149_2_, 0.0F);
    this.field_78115_e = new ModelRenderer((ModelBase)this, 16, 16);
    this.field_78115_e.func_78790_a(-4.0F, -24.0F, -2.0F, 8, 12, 4, size);
    this.field_78115_e.func_78793_a(0.0F, 24.0F + p_i1149_2_, 0.0F);
  }
  
  public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
    func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
    if (this.field_78091_s) {
      float f6 = 2.0F;
      GL11.glPushMatrix();
      GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
      GL11.glTranslatef(0.0F, 16.0F * p_78088_7_, 0.0F);
      this.field_78116_c.func_78785_a(p_78088_7_);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
      GL11.glTranslatef(0.0F, 24.0F * p_78088_7_, 0.0F);
      this.field_78115_e.func_78785_a(p_78088_7_);
      this.field_78112_f.func_78785_a(p_78088_7_);
      this.field_78113_g.func_78785_a(p_78088_7_);
      this.standPlate.func_78785_a(p_78088_7_);
      this.field_78124_i.func_78785_a(p_78088_7_);
      GL11.glPopMatrix();
    } else {
      this.field_78116_c.func_78785_a(p_78088_7_);
      this.field_78115_e.func_78785_a(p_78088_7_);
      this.field_78112_f.func_78785_a(p_78088_7_);
      this.field_78113_g.func_78785_a(p_78088_7_);
      this.standPlate.func_78785_a(p_78088_7_);
      this.field_78124_i.func_78785_a(p_78088_7_);
    } 
  }
  
  public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
    this.field_78116_c.field_78796_g = p_78087_4_ / 57.295776F;
    this.field_78116_c.field_78795_f = p_78087_5_ / 57.295776F;
    this.field_78114_d.field_78796_g = this.field_78116_c.field_78796_g;
    this.field_78114_d.field_78795_f = this.field_78116_c.field_78795_f;
    this.field_78112_f.field_78808_h = 0.0F;
    this.field_78113_g.field_78808_h = 0.0F;
    this.field_78113_g.field_78795_f = 0.0F;
    this.field_78112_f.field_78795_f = 0.0F;
    if (this.field_78119_l != 0);
    if (this.field_78120_m != 0);
    this.field_78112_f.field_78796_g = 0.0F;
    this.field_78113_g.field_78796_g = 0.0F;
    this.field_78115_e.field_78795_f = 0.0F;
    this.field_78116_c.field_78797_d = 0.0F;
    this.field_78114_d.field_78797_d = 0.0F;
    this.field_78112_f.field_78808_h = 1.5707964F;
    this.field_78113_g.field_78808_h = -1.5707964F;
    float phase = ((EntityDummy)p_78087_7_).shakeAnimation;
    float shake = ((EntityDummy)p_78087_7_).shake;
    float r = 0.0F, r2 = 0.0F;
    if (shake > 0.0F) {
      r = (float)-(MathHelper.func_76126_a(phase) * Math.PI / 100.0D * shake);
      r2 = (float)(MathHelper.func_76134_b(phase) * Math.PI / 20.0D);
    } 
    this.field_78124_i.field_78795_f = r / 2.0F;
    this.field_78115_e.field_78795_f = r / 2.0F;
    this.field_78113_g.field_78795_f = r * 1.1F;
    this.field_78112_f.field_78795_f = r * 1.1F;
    this.field_78116_c.field_78795_f = -r;
    this.field_78116_c.field_78808_h = r2;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\dummy\ModelDummy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */