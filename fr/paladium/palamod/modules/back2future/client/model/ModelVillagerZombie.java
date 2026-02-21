package fr.paladium.palamod.modules.back2future.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelZombie;

@SideOnly(Side.CLIENT)
public class ModelVillagerZombie extends ModelZombie {
  public ModelVillagerZombie(float size) {
    this(size, 64, 64);
  }
  
  public ModelVillagerZombie(float size, int width, int height) {
    super(size, 0.0F, width, height);
    this.field_78114_d.field_78807_k = true;
    this.field_78116_c = (new ModelRenderer((ModelBase)this)).func_78787_b(width, height);
    this.field_78116_c.func_78793_a(0.0F, 0.0F, 0.0F);
    this.field_78116_c.func_78784_a(0, 0).func_78790_a(-4.0F, -10.0F, -4.0F, 8, 10, 8, size);
    ModelRenderer nose = (new ModelRenderer((ModelBase)this)).func_78787_b(width, height);
    nose.func_78793_a(0.0F, -2.0F, 0.0F);
    nose.func_78784_a(24, 0).func_78790_a(-1.0F, -1.0F, -6.0F, 2, 4, 2, size);
    this.field_78116_c.func_78792_a(nose);
    this.field_78115_e = (new ModelRenderer((ModelBase)this)).func_78787_b(width, height);
    this.field_78115_e.func_78793_a(0.0F, 0.0F, 0.0F);
    this.field_78115_e.func_78784_a(16, 20).func_78790_a(-4.0F, 0.0F, -3.0F, 8, 12, 6, size);
    this.field_78115_e.func_78784_a(0, 38).func_78790_a(-4.0F, 0.0F, -3.0F, 8, 18, 6, size + 0.5F);
    this.field_78112_f = (new ModelRenderer((ModelBase)this, 44, 38)).func_78787_b(width, height);
    this.field_78112_f.func_78790_a(-3.0F, -2.0F, -2.0F, 4, 12, 4, size);
    this.field_78112_f.func_78793_a(-5.0F, 2.0F, 0.0F);
    this.field_78113_g = (new ModelRenderer((ModelBase)this, 44, 38)).func_78787_b(width, height);
    this.field_78113_g.field_78809_i = true;
    this.field_78113_g.func_78790_a(-1.0F, -2.0F, -2.0F, 4, 12, 4, size);
    this.field_78113_g.func_78793_a(5.0F, 2.0F, 0.0F);
    this.field_78123_h = (new ModelRenderer((ModelBase)this, 0, 22)).func_78787_b(width, height);
    this.field_78123_h.func_78793_a(-1.9F, 12.0F, 0.0F);
    this.field_78123_h.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 12, 4, size);
    this.field_78124_i = (new ModelRenderer((ModelBase)this, 0, 22)).func_78787_b(width, height);
    this.field_78124_i.field_78809_i = true;
    this.field_78124_i.func_78793_a(1.9F, 12.0F, 0.0F);
    this.field_78124_i.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 12, 4, size);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\model\ModelVillagerZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */