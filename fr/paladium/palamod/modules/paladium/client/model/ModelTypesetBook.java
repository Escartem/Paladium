package fr.paladium.palamod.modules.paladium.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelTypesetBook extends ModelBase {
  ModelRenderer book;
  
  private float scale = 0.020833334F;
  
  public ModelTypesetBook() {
    this.field_78090_t = 128;
    this.field_78089_u = 32;
    this.book = new ModelRenderer(this, 0, 0);
    this.book.func_78789_a(-22.5F, -3.0F, -20.5F, 21, 5, 15);
    this.book.func_78793_a(0.0F, 0.0F, 0.0F);
    this.book.func_78787_b(64, 32);
    this.book.field_78809_i = true;
    setRotation(this.book, 0.0F, 0.0F, 0.0F);
  }
  
  public void renderBook() {
    this.book.func_78785_a(this.scale);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\model\ModelTypesetBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */