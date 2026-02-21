package fr.paladium.palamod.modules.paladium.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelTypesetBase extends ModelBase {
  ModelRenderer base;
  
  private float scale = 0.020833334F;
  
  public ModelTypesetBase() {
    this.field_78090_t = 256;
    this.field_78089_u = 128;
    this.base = new ModelRenderer(this, 0, 0);
    this.base.func_78789_a(-24.0F, 2.0F, -23.0F, 48, 42, 48);
    this.base.func_78793_a(0.0F, 0.0F, 0.0F);
    this.base.func_78787_b(256, 128);
    this.base.field_78809_i = true;
    setRotation(this.base, 0.0F, 0.0F, 0.0F);
  }
  
  public void renderBase() {
    this.base.func_78785_a(this.scale);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\model\ModelTypesetBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */