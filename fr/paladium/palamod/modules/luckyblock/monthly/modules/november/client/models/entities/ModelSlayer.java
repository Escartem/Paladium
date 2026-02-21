package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSlayer extends ModelBase {
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer cube_r3;
  
  public ModelSlayer() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -3.0F, -10.0F, -3.0F, 6, 6, 6, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 18, 0, -2.0F, -4.0F, -1.0F, 1, 4, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, 1.0F, -4.0F, -1.0F, 1, 4, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 12, -6.0F, -11.0F, -3.1F, 12, 8, 0, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(4.0F, -1.5F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 1.2217F, 0.0F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 16, 18, 0.5F, -1.1F, 0.9F, 0, 5, 2, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(3.0F, -8.0F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, 0.0F, -0.5236F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 0, 20, -2.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(-3.0F, -8.0F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, 0.0F, 0.0F, 0.5236F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 8, 20, 0.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\client\models\entities\ModelSlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */