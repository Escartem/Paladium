package fr.paladium.palamod.modules.paladium.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelPrintPressBooks extends ModelBase {
  ModelRenderer Bbook1;
  
  ModelRenderer Bbook2;
  
  ModelRenderer Bbook3;
  
  ModelRenderer Bbook4;
  
  ModelRenderer Fbook;
  
  public ModelPrintPressBooks() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.Bbook1 = new ModelRenderer(this, 0, 0);
    this.Bbook1.func_78789_a(-9.0F, -2.0F, -14.0F, 6, 2, 9);
    this.Bbook1.func_78793_a(0.0F, 3.0F, 0.0F);
    this.Bbook1.func_78787_b(32, 32);
    this.Bbook1.field_78809_i = true;
    setRotation(this.Bbook1, 0.0F, 0.0872665F, 0.0F);
    this.Bbook2 = new ModelRenderer(this, 0, 0);
    this.Bbook2.func_78789_a(-10.0F, -4.0F, -13.0F, 6, 2, 9);
    this.Bbook2.func_78793_a(0.0F, 3.0F, 0.0F);
    this.Bbook2.func_78787_b(32, 32);
    this.Bbook2.field_78809_i = true;
    setRotation(this.Bbook2, 0.0F, -0.0174533F, 0.0F);
    this.Bbook3 = new ModelRenderer(this, 0, 0);
    this.Bbook3.func_78789_a(-11.0F, -6.0F, -13.0F, 6, 2, 9);
    this.Bbook3.func_78793_a(0.0F, 3.0F, 0.0F);
    this.Bbook3.func_78787_b(32, 32);
    this.Bbook3.field_78809_i = true;
    setRotation(this.Bbook3, 0.0F, -0.122173F, 0.0F);
    this.Bbook4 = new ModelRenderer(this, 0, 0);
    this.Bbook4.func_78789_a(-9.0F, -8.0F, -14.0F, 6, 2, 9);
    this.Bbook4.func_78793_a(0.0F, 3.0F, 0.0F);
    this.Bbook4.func_78787_b(32, 32);
    this.Bbook4.field_78809_i = true;
    setRotation(this.Bbook4, 0.0F, 0.0872665F, 0.0F);
    this.Fbook = new ModelRenderer(this, 0, 12);
    this.Fbook.func_78789_a(3.0F, -2.0F, -14.0F, 6, 2, 9);
    this.Fbook.func_78793_a(0.0F, 3.0F, 0.0F);
    this.Fbook.func_78787_b(32, 32);
    this.Fbook.field_78809_i = true;
    setRotation(this.Fbook, 0.0F, -0.0349066F, 0.0F);
  }
  
  public static float scale = 0.05F;
  
  public void renderEmptyBooks(int books) {
    if (books >= 1)
      this.Bbook1.func_78785_a(scale); 
    if (books >= 2)
      this.Bbook2.func_78785_a(scale); 
    if (books >= 3)
      this.Bbook3.func_78785_a(scale); 
    if (books >= 4)
      this.Bbook4.func_78785_a(scale); 
  }
  
  public void renderBook() {
    this.Fbook.func_78785_a(scale);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\model\ModelPrintPressBooks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */