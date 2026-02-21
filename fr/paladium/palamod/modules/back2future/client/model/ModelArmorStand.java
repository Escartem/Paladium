package fr.paladium.palamod.modules.back2future.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.OpenGLHelper;
import fr.paladium.palamod.modules.back2future.entities.EntityArmourStand;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelArmorStand extends ModelArmorStandArmor {
  public ModelRenderer standRightSide;
  
  public ModelRenderer standLeftSide;
  
  public ModelRenderer standWaist;
  
  public ModelRenderer standBase;
  
  public ModelArmorStand() {
    this(0.0F);
  }
  
  public ModelArmorStand(float size) {
    super(size, 64, 64);
    this.field_78116_c = new ModelRenderer((ModelBase)this, 0, 0);
    this.field_78116_c.func_78790_a(-1.0F, -7.0F, -1.0F, 2, 7, 2, size);
    this.field_78116_c.func_78793_a(0.0F, 0.0F, 0.0F);
    this.field_78115_e = new ModelRenderer((ModelBase)this, 0, 26);
    this.field_78115_e.func_78790_a(-6.0F, 0.0F, -1.5F, 12, 3, 3, size);
    this.field_78115_e.func_78793_a(0.0F, 0.0F, 0.0F);
    this.field_78112_f = new ModelRenderer((ModelBase)this, 24, 0);
    this.field_78112_f.func_78790_a(-2.0F, -2.0F, -1.0F, 2, 12, 2, size);
    this.field_78112_f.func_78793_a(-5.0F, 2.0F, 0.0F);
    this.field_78113_g = new ModelRenderer((ModelBase)this, 32, 16);
    this.field_78113_g.field_78809_i = true;
    this.field_78113_g.func_78790_a(0.0F, -2.0F, -1.0F, 2, 12, 2, size);
    this.field_78113_g.func_78793_a(5.0F, 2.0F, 0.0F);
    this.field_78123_h = new ModelRenderer((ModelBase)this, 8, 0);
    this.field_78123_h.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 11, 2, size);
    this.field_78123_h.func_78793_a(-1.9F, 12.0F, 0.0F);
    this.field_78124_i = new ModelRenderer((ModelBase)this, 40, 16);
    this.field_78124_i.field_78809_i = true;
    this.field_78124_i.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 11, 2, size);
    this.field_78124_i.func_78793_a(1.9F, 12.0F, 0.0F);
    this.standRightSide = new ModelRenderer((ModelBase)this, 16, 0);
    this.standRightSide.func_78790_a(-3.0F, 3.0F, -1.0F, 2, 7, 2, size);
    this.standRightSide.func_78793_a(0.0F, 0.0F, 0.0F);
    this.standRightSide.field_78806_j = true;
    this.standLeftSide = new ModelRenderer((ModelBase)this, 48, 16);
    this.standLeftSide.func_78790_a(1.0F, 3.0F, -1.0F, 2, 7, 2, size);
    this.standLeftSide.func_78793_a(0.0F, 0.0F, 0.0F);
    this.standWaist = new ModelRenderer((ModelBase)this, 0, 48);
    this.standWaist.func_78790_a(-4.0F, 10.0F, -1.0F, 8, 2, 2, size);
    this.standWaist.func_78793_a(0.0F, 0.0F, 0.0F);
    this.standBase = new ModelRenderer((ModelBase)this, 0, 32);
    this.standBase.func_78790_a(-6.0F, 11.0F, -6.0F, 12, 1, 12, size);
    this.standBase.func_78793_a(0.0F, 12.0F, 0.0F);
  }
  
  public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entity) {
    super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, entity);
    if (entity instanceof EntityArmourStand) {
      EntityArmourStand stand = (EntityArmourStand)entity;
      this.field_78113_g.field_78806_j = stand.getShowArms();
      this.field_78112_f.field_78806_j = stand.getShowArms();
      this.standBase.field_78806_j = !stand.hasNoBasePlate();
      this.field_78124_i.func_78793_a(1.9F, 12.0F, 0.0F);
      this.field_78123_h.func_78793_a(-1.9F, 12.0F, 0.0F);
      this.standRightSide.field_78795_f = 0.017453292F * stand.getBodyRotation().getX();
      this.standRightSide.field_78796_g = 0.017453292F * stand.getBodyRotation().getY();
      this.standRightSide.field_78808_h = 0.017453292F * stand.getBodyRotation().getZ();
      this.standLeftSide.field_78795_f = 0.017453292F * stand.getBodyRotation().getX();
      this.standLeftSide.field_78796_g = 0.017453292F * stand.getBodyRotation().getY();
      this.standLeftSide.field_78808_h = 0.017453292F * stand.getBodyRotation().getZ();
      this.standWaist.field_78795_f = 0.017453292F * stand.getBodyRotation().getX();
      this.standWaist.field_78796_g = 0.017453292F * stand.getBodyRotation().getY();
      this.standWaist.field_78808_h = 0.017453292F * stand.getBodyRotation().getZ();
      this.standBase.field_78795_f = 0.0F;
      this.standBase.field_78796_g = 0.017453292F * -entity.field_70177_z;
      this.standBase.field_78808_h = 0.0F;
    } 
  }
  
  public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
    super.func_78088_a(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
    OpenGLHelper.pushMatrix();
    if (this.field_78091_s) {
      float f6 = 2.0F;
      OpenGLHelper.scale(1.0F / f6, 1.0F / f6, 1.0F / f6);
      OpenGLHelper.translate(0.0F, 24.0F * p_78088_7_, 0.0F);
      this.standRightSide.func_78785_a(p_78088_7_);
      this.standLeftSide.func_78785_a(p_78088_7_);
      this.standWaist.func_78785_a(p_78088_7_);
      this.standBase.func_78785_a(p_78088_7_);
    } else {
      if (p_78088_1_.func_70093_af())
        OpenGLHelper.translate(0.0F, 0.2F, 0.0F); 
      this.standRightSide.func_78785_a(p_78088_7_);
      this.standLeftSide.func_78785_a(p_78088_7_);
      this.standWaist.func_78785_a(p_78088_7_);
      this.standBase.func_78785_a(p_78088_7_);
    } 
    OpenGLHelper.popMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\model\ModelArmorStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */