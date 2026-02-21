package fr.paladium.palamod.modules.luckyblock.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTombe extends ModelBase {
  private final ModelRenderer grass;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer grass2;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r3;
  
  private final ModelRenderer cube_r4;
  
  private final ModelRenderer cube_r5;
  
  public ModelTombe() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.grass = new ModelRenderer(this);
    this.grass.func_78793_a(-3.0F, 22.0F, -4.0F);
    this.grass.field_78804_l.add(new ModelBox(this.grass, 8, 24, -6.0F, -6.0F, 0.0F, 12, 8, 0, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.grass.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 1.5708F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 8, 24, -6.0F, -6.0F, 0.0F, 12, 8, 0, 0.0F));
    this.grass2 = new ModelRenderer(this);
    this.grass2.func_78793_a(-2.0F, 0.0F, -1.0F);
    this.grass.func_78792_a(this.grass2);
    this.grass2.field_78804_l.add(new ModelBox(this.grass2, 9, 24, 6.0F, -4.0F, 8.0F, 11, 6, 0, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(12.0F, 0.0F, 8.0F);
    this.grass2.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, 1.5708F, 0.0F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 9, 24, -6.0F, -4.0F, 0.0F, 11, 6, 0, 0.0F));
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(-4.0F, -5.5F, 5.0F);
    this.bb_main.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, -0.0993F, 0.1704F, -0.0381F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 0, 16, -4.0F, -5.1F, 0.9F, 8, 11, 0, 0.0F));
    this.cube_r4 = new ModelRenderer(this);
    this.cube_r4.func_78793_a(7.0F, -5.5F, 4.5F);
    this.bb_main.func_78792_a(this.cube_r4);
    setRotationAngle(this.cube_r4, 0.0F, 0.0F, 0.1658F);
    this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 16, 16, -4.0F, -4.5F, 0.5F, 8, 11, 0, 0.0F));
    this.cube_r5 = new ModelRenderer(this);
    this.cube_r5.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r5);
    setRotationAngle(this.cube_r5, -0.1743F, 0.0094F, 0.0426F);
    this.cube_r5.field_78804_l.add(new ModelBox(this.cube_r5, 0, 0, -5.0F, -14.7F, 5.6F, 11, 14, 2, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.grass.func_78785_a(f5);
    this.bb_main.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll() {
    this.grass.func_78785_a(0.0625F);
    this.bb_main.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\ModelTombe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */