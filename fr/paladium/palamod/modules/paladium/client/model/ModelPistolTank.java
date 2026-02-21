package fr.paladium.palamod.modules.paladium.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPistolTank extends ModelBase {
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  public ModelPistolTank() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -3.0F, -12.0F, -2.0F, 10, 3, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 16, 7, -10.0F, -11.0F, -1.0F, 7, 2, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 7, -11.0F, -12.0F, -2.0F, 6, 4, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 10, 15, -2.0F, -9.0F, -1.0F, 7, 2, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 20, 11, 7.0F, -11.0F, -1.0F, 1, 1, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 14, 22, 8.0F, -12.0F, -2.0F, 1, 3, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 14, 19, -4.0F, -13.0F, -1.0F, 7, 1, 2, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, -5.0F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 0.0F, -0.5672F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 24, 22, 0.0F, -3.0F, -1.0F, 1, 3, 2, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(-1.0F, -7.0F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, 0.0F, 0.5672F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 0, 15, -3.0F, -3.0F, -2.0F, 3, 9, 4, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bb_main.func_78785_a(f5);
  }
  
  public void renderAll() {
    this.bb_main.func_78785_a(0.0625F);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\model\ModelPistolTank.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */