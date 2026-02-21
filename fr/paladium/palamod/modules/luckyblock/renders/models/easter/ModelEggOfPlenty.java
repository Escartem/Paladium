package fr.paladium.palamod.modules.luckyblock.renders.models.easter;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEggOfPlenty extends ModelBase {
  private final ModelRenderer nest_part_0;
  
  private final ModelRenderer straw_3_r1;
  
  private final ModelRenderer straw_2_r1;
  
  private final ModelRenderer straw_1_r1;
  
  private final ModelRenderer straw_0_r1;
  
  private final ModelRenderer nest_part_1;
  
  private final ModelRenderer straw_7_r1;
  
  private final ModelRenderer straw_6_r1;
  
  private final ModelRenderer straw_5_r1;
  
  private final ModelRenderer straw_4_r1;
  
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r1;
  
  public ModelEggOfPlenty() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.nest_part_0 = new ModelRenderer(this);
    this.nest_part_0.func_78793_a(0.0F, 24.0F, 0.0F);
    this.straw_3_r1 = new ModelRenderer(this);
    this.straw_3_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.nest_part_0.func_78792_a(this.straw_3_r1);
    setRotationAngle(this.straw_3_r1, 0.3491F, 0.0F, 0.0F);
    this.straw_3_r1.field_78804_l.add(new ModelBox(this.straw_3_r1, 9, 25, -4.0F, 1.3681F, 3.7588F, 8, 0, 7, 0.0F));
    this.straw_2_r1 = new ModelRenderer(this);
    this.straw_2_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.nest_part_0.func_78792_a(this.straw_2_r1);
    setRotationAngle(this.straw_2_r1, 0.0F, 0.0F, 0.3491F);
    this.straw_2_r1.field_78804_l.add(new ModelBox(this.straw_2_r1, -8, 24, -10.7588F, 1.3681F, -4.0F, 7, 0, 8, 0.0F));
    this.straw_1_r1 = new ModelRenderer(this);
    this.straw_1_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.nest_part_0.func_78792_a(this.straw_1_r1);
    setRotationAngle(this.straw_1_r1, -0.3491F, 0.0F, 0.0F);
    this.straw_1_r1.field_78804_l.add(new ModelBox(this.straw_1_r1, 9, 25, -4.0F, 1.3681F, -10.7588F, 8, 0, 7, 0.0F));
    this.straw_0_r1 = new ModelRenderer(this);
    this.straw_0_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.nest_part_0.func_78792_a(this.straw_0_r1);
    setRotationAngle(this.straw_0_r1, 0.0F, 0.0F, -0.3491F);
    this.straw_0_r1.field_78804_l.add(new ModelBox(this.straw_0_r1, -8, 24, 3.7588F, 1.3681F, -4.0F, 7, 0, 8, 0.0F));
    this.nest_part_1 = new ModelRenderer(this);
    this.nest_part_1.func_78793_a(0.0F, 24.0F, 0.0F);
    setRotationAngle(this.nest_part_1, 0.0F, -0.7854F, 0.0F);
    this.straw_7_r1 = new ModelRenderer(this);
    this.straw_7_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.nest_part_1.func_78792_a(this.straw_7_r1);
    setRotationAngle(this.straw_7_r1, 0.3491F, 0.0F, 0.0F);
    this.straw_7_r1.field_78804_l.add(new ModelBox(this.straw_7_r1, 9, 25, -4.0F, 1.3681F, 3.7588F, 8, 0, 7, 0.0F));
    this.straw_6_r1 = new ModelRenderer(this);
    this.straw_6_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.nest_part_1.func_78792_a(this.straw_6_r1);
    setRotationAngle(this.straw_6_r1, 0.0F, 0.0F, 0.3491F);
    this.straw_6_r1.field_78804_l.add(new ModelBox(this.straw_6_r1, -8, 24, -10.7588F, 1.3681F, -4.0F, 7, 0, 8, 0.0F));
    this.straw_5_r1 = new ModelRenderer(this);
    this.straw_5_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.nest_part_1.func_78792_a(this.straw_5_r1);
    setRotationAngle(this.straw_5_r1, -0.3491F, 0.0F, 0.0F);
    this.straw_5_r1.field_78804_l.add(new ModelBox(this.straw_5_r1, 9, 25, -4.0F, 1.3681F, -10.7588F, 8, 0, 7, 0.0F));
    this.straw_4_r1 = new ModelRenderer(this);
    this.straw_4_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.nest_part_1.func_78792_a(this.straw_4_r1);
    setRotationAngle(this.straw_4_r1, 0.0F, 0.0F, -0.3491F);
    this.straw_4_r1.field_78804_l.add(new ModelBox(this.straw_4_r1, -8, 24, 3.7588F, 1.3681F, -4.0F, 7, 0, 8, 0.0F));
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 3.1416F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 0, -4.0F, -12.0F, -4.0F, 8, 12, 8, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.nest_part_0.func_78785_a(f5);
    this.nest_part_1.func_78785_a(f5);
    this.bb_main.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll() {
    this.nest_part_0.func_78785_a(0.0625F);
    this.nest_part_1.func_78785_a(0.0625F);
    this.bb_main.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\easter\ModelEggOfPlenty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */