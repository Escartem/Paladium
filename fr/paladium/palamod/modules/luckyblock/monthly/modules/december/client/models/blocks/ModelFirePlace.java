package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.client.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFirePlace extends ModelBase {
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r1;
  
  public ModelFirePlace() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 28, 73, -10.0F, -13.0F, -7.0F, 4, 11, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 46, 72, -11.0F, -2.0F, -9.0F, 6, 2, 6, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 35, -10.0F, -4.0F, -9.0F, 4, 2, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 20, 6.0F, -4.0F, -9.0F, 4, 2, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 70, 72, 6.0F, -13.0F, -7.0F, 4, 11, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 28, 65, 5.0F, -2.0F, -9.0F, 6, 2, 6, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 44, 28, -10.0F, -19.0F, -7.0F, 20, 3, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 55, 6, -10.0F, -16.0F, -7.0F, 6, 1, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 16, 36, 4.0F, -16.0F, -7.0F, 6, 1, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 80, 0, 5.0F, -15.0F, -7.0F, 5, 1, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 70, 7, -10.0F, -15.0F, -7.0F, 5, 1, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 80, 48, -10.0F, -14.0F, -7.0F, 4, 1, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 42, 80, 6.0F, -14.0F, -7.0F, 4, 1, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 56, 48, -5.0F, -21.0F, -8.0F, 10, 2, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -11.0F, -2.0F, -3.0F, 22, 2, 11, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 28, 28, -10.0F, -19.0F, -4.0F, 2, 17, 12, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 28, 8.0F, -19.0F, -4.0F, 2, 17, 12, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 13, -8.0F, -19.0F, -5.0F, 16, 2, 13, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 54, 55, -8.0F, -17.0F, 6.0F, 16, 15, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 55, 0, -5.0F, -1.0F, -8.0F, 10, 1, 5, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 71, -7.0F, -9.0F, -3.0F, 14, 7, 0, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 58, 13, -7.0F, -9.0F, -2.0F, 14, 6, 0, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 57, -7.0F, -11.0F, -1.0F, 14, 9, 0, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 56, 34, -7.0F, -15.0F, 0.0F, 14, 13, 0, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 28, 87, -6.0F, -6.0F, -5.0F, 12, 4, 0, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, 0.0F, -6.0F);
    this.bb_main.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 0.0F, -3.1416F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 0, 7.0F, 15.0F, -4.0F, 2, 3, 2, 0.0F));
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 13, 6.0F, 14.0F, -3.0F, 4, 5, 2, 0.0F));
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 16, 28, 5.0F, 19.0F, -3.0F, 6, 2, 6, 0.0F));
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 5, -9.0F, 15.0F, -4.0F, 2, 3, 2, 0.0F));
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 28, 57, -11.0F, 19.0F, -3.0F, 6, 2, 6, 0.0F));
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 28, -10.0F, 14.0F, -3.0F, 4, 5, 2, 0.0F));
  }
  
  public void renderAll() {
    this.bb_main.func_78785_a(0.0625F);
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\client\models\blocks\ModelFirePlace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */