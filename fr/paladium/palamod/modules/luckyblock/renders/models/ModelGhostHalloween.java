package fr.paladium.palamod.modules.luckyblock.renders.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.entity.halloween.EntityGhostHalloween;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelGhostHalloween extends ModelBiped {
  private int attackCounter;
  
  public ModelGhostHalloween() {
    super(12.0F, 0.0F, 64, 32);
    this.field_78119_l = 0;
    this.field_78120_m = 0;
    this.field_78117_n = false;
    (this.field_78116_c = new ModelRenderer((ModelBase)this, 0, 40)).func_78790_a(-4.0F, -8.0F, -4.0F, 1, 1, 1, 0.0F);
    this.field_78116_c.func_78793_a(0.0F, 0.0F, 0.0F);
    (this.field_78114_d = new ModelRenderer((ModelBase)this, 0, 0)).func_78790_a(-5.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
    this.field_78114_d.func_78793_a(0.0F, 0.0F, 0.0F);
    (this.field_78115_e = new ModelRenderer((ModelBase)this, 36, 0)).func_78790_a(-6.0F, 0.0F, -2.0F, 10, 20, 4, 0.0F);
    this.field_78115_e.func_78793_a(0.0F, 0.0F, 0.0F);
    (this.field_78112_f = new ModelRenderer((ModelBase)this, 16, 16)).func_78790_a(-5.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
    this.field_78112_f.func_78793_a(-5.0F, 2.0F, 0.0F);
    this.field_78113_g = new ModelRenderer((ModelBase)this, 16, 16);
    this.field_78113_g.field_78809_i = true;
    this.field_78113_g.func_78790_a(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
    this.field_78113_g.func_78793_a(5.0F, 2.0F, 0.0F);
    (this.field_78123_h = new ModelRenderer((ModelBase)this, 0, 16)).func_78790_a(-2.0F, 0.0F, -2.0F, 2, 2, 2, 0.0F);
    this.field_78123_h.func_78793_a(-2.0F, 12.0F, 0.0F);
    this.field_78124_i = new ModelRenderer((ModelBase)this, 0, 16);
    this.field_78124_i.field_78809_i = true;
    this.field_78124_i.func_78790_a(-2.0F, 0.0F, -2.0F, 2, 2, 2, 0.0F);
    this.field_78124_i.func_78793_a(2.0F, 12.0F, 0.0F);
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
    if (par7Entity instanceof EntityGhostHalloween)
      this.attackCounter = ((EntityGhostHalloween)par7Entity).attackCounter; 
    float f6 = MathHelper.func_76126_a(this.field_78095_p * 3.141593F);
    float f7 = MathHelper.func_76126_a((1.0F - (1.0F - this.field_78095_p) * (1.0F - this.field_78095_p)) * 3.141593F);
    this.field_78112_f.field_78808_h = 0.0F;
    this.field_78113_g.field_78808_h = 0.0F;
    this.field_78112_f.field_78796_g = -(0.1F - f6 * 0.6F);
    this.field_78113_g.field_78796_g = 0.1F - f6 * 0.6F;
    if (this.attackCounter != 0) {
      float armMov = MathHelper.func_76134_b(this.attackCounter * 0.12F) * 4.0F;
      this.field_78112_f.field_78795_f = -armMov;
      this.field_78113_g.field_78795_f = -armMov;
    } else {
      this.field_78112_f.field_78795_f = -1.570796F;
      this.field_78113_g.field_78795_f = -1.570796F;
      ModelRenderer field_178723_h = this.field_78112_f;
      field_178723_h.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
      ModelRenderer field_178724_i = this.field_78113_g;
      field_178724_i.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
      ModelRenderer field_178723_h2 = this.field_78112_f;
      field_178723_h2.field_78795_f += MathHelper.func_76126_a(f2 * 0.067F) * 0.05F;
      ModelRenderer field_178724_i2 = this.field_78113_g;
      field_178724_i2.field_78795_f -= MathHelper.func_76126_a(f2 * 0.067F) * 0.05F;
    } 
    ModelRenderer field_178723_h3 = this.field_78112_f;
    field_178723_h3.field_78808_h += MathHelper.func_76134_b(f2 * 0.09F) * 0.05F + 0.05F;
    ModelRenderer field_178724_i3 = this.field_78113_g;
    field_178724_i3.field_78808_h -= MathHelper.func_76134_b(f2 * 0.09F) * 0.05F + 0.05F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\ModelGhostHalloween.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */