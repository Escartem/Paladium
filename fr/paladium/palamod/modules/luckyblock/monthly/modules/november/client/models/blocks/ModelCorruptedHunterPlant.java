package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCorruptedHunterPlant extends ModelBase {
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer cube_r3;
  
  private final ModelRenderer cube_r4;
  
  private final ModelRenderer cube_r5;
  
  private final ModelRenderer cube_r6;
  
  public ModelCorruptedHunterPlant() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 32, 0, -2.0F, -17.0F, -1.5F, 4, 7, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -4.0F, -24.0F, -3.5F, 8, 7, 8, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, -17.0F, -3.5F);
    this.bb_main.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.5236F, 3.1416F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 28, 11, -4.0F, 0.0F, 0.0F, 8, 0, 4, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(4.0F, -17.0F, 0.5F);
    this.bb_main.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, 1.5708F, -0.5236F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 28, 11, -4.0F, 0.0F, 0.0F, 8, 0, 4, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(-4.0F, -17.0F, 0.5F);
    this.bb_main.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, 0.0F, -1.5708F, 0.5236F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 28, 11, -4.0F, 0.0F, 0.0F, 8, 0, 4, 0.0F));
    this.cube_r4 = new ModelRenderer(this);
    this.cube_r4.func_78793_a(0.0F, -17.0F, 4.5F);
    this.bb_main.func_78792_a(this.cube_r4);
    setRotationAngle(this.cube_r4, 0.5236F, 0.0F, 0.0F);
    this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 28, 11, -4.0F, 0.0F, 0.0F, 8, 0, 4, 0.0F));
    this.cube_r5 = new ModelRenderer(this);
    this.cube_r5.func_78793_a(0.0F, -0.5F, 0.5F);
    this.bb_main.func_78792_a(this.cube_r5);
    setRotationAngle(this.cube_r5, 0.0F, 0.7854F, 0.0F);
    this.cube_r5.field_78804_l.add(new ModelBox(this.cube_r5, 0, 15, -8.0F, -15.5F, 0.0F, 16, 16, 0, 0.0F));
    this.cube_r6 = new ModelRenderer(this);
    this.cube_r6.func_78793_a(0.0F, -0.5F, 0.5F);
    this.bb_main.func_78792_a(this.cube_r6);
    setRotationAngle(this.cube_r6, 0.0F, -0.7854F, 0.0F);
    this.cube_r6.field_78804_l.add(new ModelBox(this.cube_r6, 0, 15, -8.0F, -15.5F, 0.0F, 16, 16, 0, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bb_main.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll() {
    this.bb_main.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\client\models\blocks\ModelCorruptedHunterPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */