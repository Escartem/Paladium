package fr.paladium.palamod.modules.luckyblock.blocks.models.june;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelJuneTrophy extends ModelBase {
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r1;
  
  public ModelJuneTrophy() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 12, 28, -3.0F, -1.0F, -1.0F, 6, 1, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 28, 26, -1.0F, -5.0F, -1.0F, 2, 4, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 18, -5.0F, -10.0F, -1.0F, 1, 3, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, 4.0F, -10.0F, -1.0F, 1, 3, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -3.0F, -6.0F, -3.0F, 6, 1, 6, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 18, 7, -4.0F, -11.0F, -4.0F, 8, 5, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 18, 0, -4.0F, -11.0F, 3.0F, 8, 5, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 18, 3.0F, -11.0F, -3.0F, 1, 5, 6, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 10, 12, -4.0F, -11.0F, -3.0F, 1, 5, 6, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 14, 23, -4.0F, -16.0F, 4.0F, 8, 5, 0, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 18, 13, -4.0F, -16.0F, -4.0F, 8, 5, 0, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 5, -4.0F, -16.0F, -4.0F, 0, 5, 8, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, 4.0F, -16.0F, -4.0F, 0, 5, 8, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, -1.5708F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 24, 18, -3.0F, -1.0F, -1.0F, 6, 1, 2, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\june\ModelJuneTrophy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */