package fr.paladium.palamod.modules.back2future.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.entities.EntityArmourStand;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelArmorStandArmor extends ModelBiped {
  public ModelArmorStandArmor() {
    this(0.0F);
  }
  
  public ModelArmorStandArmor(float p_i46307_1_) {
    this(p_i46307_1_, 64, 32);
  }
  
  protected ModelArmorStandArmor(float p_i46308_1_, int p_i46308_2_, int p_i46308_3_) {
    super(p_i46308_1_, 0.0F, p_i46308_2_, p_i46308_3_);
  }
  
  public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entity) {
    if (entity instanceof EntityArmourStand) {
      EntityArmourStand entityarmorstand = (EntityArmourStand)entity;
      this.field_78116_c.field_78795_f = 0.017453292F * entityarmorstand.getHeadRotation().getX();
      this.field_78116_c.field_78796_g = 0.017453292F * entityarmorstand.getHeadRotation().getY();
      this.field_78116_c.field_78808_h = 0.017453292F * entityarmorstand.getHeadRotation().getZ();
      this.field_78116_c.func_78793_a(0.0F, 1.0F, 0.0F);
      this.field_78115_e.field_78795_f = 0.017453292F * entityarmorstand.getBodyRotation().getX();
      this.field_78115_e.field_78796_g = 0.017453292F * entityarmorstand.getBodyRotation().getY();
      this.field_78115_e.field_78808_h = 0.017453292F * entityarmorstand.getBodyRotation().getZ();
      this.field_78113_g.field_78795_f = 0.017453292F * entityarmorstand.getLeftArmRotation().getX();
      this.field_78113_g.field_78796_g = 0.017453292F * entityarmorstand.getLeftArmRotation().getY();
      this.field_78113_g.field_78808_h = 0.017453292F * entityarmorstand.getLeftArmRotation().getZ();
      this.field_78112_f.field_78795_f = 0.017453292F * entityarmorstand.getRightArmRotation().getX();
      this.field_78112_f.field_78796_g = 0.017453292F * entityarmorstand.getRightArmRotation().getY();
      this.field_78112_f.field_78808_h = 0.017453292F * entityarmorstand.getRightArmRotation().getZ();
      this.field_78124_i.field_78795_f = 0.017453292F * entityarmorstand.getLeftLegRotation().getX();
      this.field_78124_i.field_78796_g = 0.017453292F * entityarmorstand.getLeftLegRotation().getY();
      this.field_78124_i.field_78808_h = 0.017453292F * entityarmorstand.getLeftLegRotation().getZ();
      this.field_78124_i.func_78793_a(1.9F, 11.0F, 0.0F);
      this.field_78123_h.field_78795_f = 0.017453292F * entityarmorstand.getRightLegRotation().getX();
      this.field_78123_h.field_78796_g = 0.017453292F * entityarmorstand.getRightLegRotation().getY();
      this.field_78123_h.field_78808_h = 0.017453292F * entityarmorstand.getRightLegRotation().getZ();
      this.field_78123_h.func_78793_a(-1.9F, 11.0F, 0.0F);
      copyModelAngles(this.field_78116_c, this.field_78114_d);
    } 
  }
  
  public static void copyModelAngles(ModelRenderer source, ModelRenderer dest) {
    dest.field_78795_f = source.field_78795_f;
    dest.field_78796_g = source.field_78796_g;
    dest.field_78808_h = source.field_78808_h;
    dest.field_78800_c = source.field_78800_c;
    dest.field_78797_d = source.field_78797_d;
    dest.field_78798_e = source.field_78798_e;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\model\ModelArmorStandArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */