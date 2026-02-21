package fr.paladium.palamod.modules.paladium.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelInkPlate extends ModelBase {
  ModelRenderer inkplate;
  
  public static float scale = 0.05F;
  
  public ModelInkPlate() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.inkplate = new ModelRenderer(this, 0, 0);
    this.inkplate.func_78789_a(-3.5F, -0.0F, 0.5F, 7, 1, 7);
    this.inkplate.func_78793_a(0.0F, 0.0F, 0.0F);
    this.inkplate.func_78787_b(32, 32);
    this.inkplate.field_78809_i = true;
    setRotation(this.inkplate, 0.2792527F, 0.0F, 0.0F);
  }
  
  public void render() {
    this.inkplate.func_78785_a(scale);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\model\ModelInkPlate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */