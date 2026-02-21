package fr.paladium.palamod.modules.back2future.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBoulder extends ModelBase {
  public ModelRenderer part;
  
  public ModelBoulder() {
    this.field_78090_t = 16;
    this.field_78089_u = 16;
    this.part = new ModelRenderer(this, 0, 0);
    this.part.func_78793_a(0.0F, 0.0F, 0.0F);
    this.part.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    this.part.func_78785_a(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }
  
  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\model\ModelBoulder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */