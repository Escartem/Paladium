package fr.paladium.palamod.modules.luckyblock.renders.models.june;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSitar extends ModelBase {
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer cube_r3;
  
  public ModelSitar() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 8, 0, -3.0F, -12.0F, -0.1F, 6, 11, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -1.0F, -24.0F, 0.0F, 2, 24, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 24, 12, -3.0F, -22.0F, 0.0F, 6, 2, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 23, 25, 3.0F, -11.0F, -0.1F, 2, 11, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 26, 16, 5.0F, -7.0F, -0.1F, 2, 5, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 16, 14, -5.0F, -11.0F, -0.1F, 2, 11, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 26, 0, -7.0F, -7.0F, -0.1F, 2, 5, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 33, 29, 1.0F, -19.0F, 0.0F, 1, 3, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 33, 24, -2.0F, -19.0F, 0.0F, 1, 3, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 16, 33, 1.0F, -15.0F, 0.0F, 1, 3, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 16, 28, -2.0F, -15.0F, 0.0F, 1, 3, 2, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, -24.0F, 1.0F);
    this.bb_main.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 0.0F, 0.7854F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 26, -1.0F, -1.0F, -1.1F, 2, 2, 2, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(3.0F, -21.0F, 1.0F);
    this.bb_main.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, 0.0F, 0.7854F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 26, 8, -1.0F, -1.0F, -1.1F, 2, 2, 2, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(-3.0F, -21.0F, 1.0F);
    this.bb_main.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, 0.0F, 0.0F, 0.7854F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 0, 30, -1.0F, -1.0F, -1.1F, 2, 2, 2, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bb_main.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\june\ModelSitar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */