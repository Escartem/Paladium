package fr.paladium.palamod.modules.paladium.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTotem extends ModelBase {
  private ModelRenderer shape1;
  
  private ModelRenderer shape2;
  
  private ModelRenderer shape3;
  
  private ModelRenderer shape4;
  
  private ModelRenderer shape5;
  
  private ModelRenderer shape6;
  
  private ModelRenderer shape7;
  
  private ModelRenderer shape8;
  
  private ModelRenderer shape9;
  
  private ModelRenderer shape10;
  
  private ModelRenderer shape11;
  
  private ModelRenderer shape12;
  
  private ModelRenderer shape13;
  
  private ModelRenderer shape14;
  
  public ModelTotem() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.shape1 = new ModelRenderer(this, 0, 34);
    this.shape1.func_78790_a(0.0F, 0.0F, 0.0F, 14, 1, 14, 0.0F);
    this.shape1.func_78793_a(-7.0F, 23.0F, -7.0F);
    this.shape1.func_78784_a(0, 34);
    this.shape2 = new ModelRenderer(this, 0, 17);
    this.shape2.func_78790_a(0.0F, 0.0F, 0.0F, 8, 9, 8, 0.0F);
    this.shape2.func_78793_a(-4.0F, 14.0F, -4.0F);
    this.shape2.func_78784_a(0, 17);
    this.shape2.field_78809_i = true;
    this.shape3 = new ModelRenderer(this, 32, 17);
    this.shape3.func_78790_a(0.0F, 0.0F, 0.0F, 6, 2, 6, 0.0F);
    this.shape3.func_78793_a(-3.0F, 12.0F, -3.0F);
    this.shape3.func_78784_a(32, 17);
    this.shape4 = new ModelRenderer(this, 0, 0);
    this.shape4.func_78790_a(0.0F, 0.0F, 0.0F, 8, 9, 8, 0.0F);
    this.shape4.func_78793_a(-4.0F, 3.0F, -4.0F);
    this.shape5 = new ModelRenderer(this, 32, 25);
    this.shape5.func_78790_a(0.0F, 0.0F, 0.0F, 6, 2, 6, 0.0F);
    this.shape5.func_78793_a(-3.0F, 1.0F, -3.0F);
    this.shape5.func_78784_a(32, 25);
    this.shape6 = new ModelRenderer(this, 32, 0);
    this.shape6.func_78790_a(0.0F, 0.0F, 0.0F, 8, 9, 8, 0.0F);
    this.shape6.func_78793_a(-4.0F, -8.0F, -4.0F);
    this.shape6.func_78784_a(32, 0);
    this.shape7 = new ModelRenderer(this, 0, 49);
    this.shape7.func_78790_a(0.0F, 0.0F, 0.0F, 2, 7, 1, 0.0F);
    this.shape7.func_78793_a(-6.0F, 4.0F, 0.0F);
    this.shape7.func_78784_a(0, 49);
    this.shape8 = new ModelRenderer(this, 56, 41);
    this.shape8.func_78790_a(0.0F, 3.0F, 0.0F, 2, 6, 1, 0.0F);
    this.shape8.func_78793_a(-8.0F, 0.0F, 0.0F);
    this.shape8.func_78784_a(56, 41);
    this.shape9 = new ModelRenderer(this, 56, 22);
    this.shape9.func_78790_a(0.0F, 0.0F, 0.0F, 2, 5, 1, 0.0F);
    this.shape9.func_78793_a(-10.0F, 2.0F, 0.0F);
    this.shape9.func_78784_a(56, 22);
    this.shape10 = new ModelRenderer(this, 56, 17);
    this.shape10.func_78790_a(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
    this.shape10.func_78793_a(-11.0F, 1.0F, 0.0F);
    this.shape10.func_78784_a(56, 17);
    this.shape11 = new ModelRenderer(this, 60, 17);
    this.shape11.func_78790_a(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
    this.shape11.func_78793_a(10.0F, 1.0F, 0.0F);
    this.shape11.func_78784_a(60, 17);
    this.shape12 = new ModelRenderer(this, 56, 28);
    this.shape12.func_78790_a(0.0F, 0.0F, 0.0F, 2, 5, 1, 0.0F);
    this.shape12.func_78793_a(8.0F, 2.0F, 0.0F);
    this.shape12.func_78784_a(56, 28);
    this.shape13 = new ModelRenderer(this, 56, 34);
    this.shape13.func_78790_a(0.0F, 0.0F, 0.0F, 2, 6, 1, 0.0F);
    this.shape13.func_78793_a(6.0F, 3.0F, 0.0F);
    this.shape13.func_78784_a(56, 34);
    this.shape14 = new ModelRenderer(this, 6, 49);
    this.shape14.func_78790_a(0.0F, 0.0F, 0.0F, 2, 7, 1, 0.0F);
    this.shape14.func_78793_a(4.0F, 4.0F, 0.0F);
    this.shape14.func_78784_a(6, 49);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    this.shape2.func_78785_a(f5);
    this.shape1.func_78785_a(f5);
    this.shape3.func_78785_a(f5);
    this.shape4.func_78785_a(f5);
    this.shape5.func_78785_a(f5);
    this.shape6.func_78785_a(f5);
    this.shape7.func_78785_a(f5);
    this.shape8.func_78785_a(f5);
    this.shape9.func_78785_a(f5);
    this.shape10.func_78785_a(f5);
    this.shape11.func_78785_a(f5);
    this.shape12.func_78785_a(f5);
    this.shape13.func_78785_a(f5);
    this.shape14.func_78785_a(f5);
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\model\ModelTotem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */