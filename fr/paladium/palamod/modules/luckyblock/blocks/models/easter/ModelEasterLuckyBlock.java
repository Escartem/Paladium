package fr.paladium.palamod.modules.luckyblock.blocks.models.easter;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEasterLuckyBlock extends ModelBase {
  private final ModelRenderer EGG3;
  
  private final ModelRenderer EGG2;
  
  private final ModelRenderer EGG1;
  
  private final ModelRenderer EGG4;
  
  private final ModelRenderer grass;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r3;
  
  private final ModelRenderer paille;
  
  public ModelEasterLuckyBlock() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.EGG3 = new ModelRenderer(this);
    this.EGG3.func_78793_a(-9.0F, 16.0F, 23.0F);
    setRotationAngle(this.EGG3, 0.0F, 0.0F, -0.5236F);
    this.EGG3.field_78804_l.add(new ModelBox(this.EGG3, 50, 42, 2.0F, -4.0F, -15.0F, 5, 2, 5, 0.0F));
    this.EGG3.field_78804_l.add(new ModelBox(this.EGG3, 4, 5, 4.0F, -1.0F, -13.0F, 1, 1, 1, 0.0F));
    this.EGG3.field_78804_l.add(new ModelBox(this.EGG3, 0, 60, 3.0F, -2.0F, -14.0F, 3, 1, 3, 0.0F));
    this.EGG3.field_78804_l.add(new ModelBox(this.EGG3, 0, 5, 4.0F, -7.0F, -13.0F, 1, 1, 1, 0.0F));
    this.EGG3.field_78804_l.add(new ModelBox(this.EGG3, 0, 0, 3.0F, -6.0F, -14.0F, 3, 2, 3, 0.0F));
    this.EGG2 = new ModelRenderer(this);
    this.EGG2.func_78793_a(-10.0F, 25.0F, 0.9F);
    setRotationAngle(this.EGG2, 0.0F, 0.0F, -0.4363F);
    this.EGG2.field_78804_l.add(new ModelBox(this.EGG2, 0, 44, 1.0F, -4.0F, -16.0F, 7, 2, 7, 0.0F));
    this.EGG2.field_78804_l.add(new ModelBox(this.EGG2, 0, 64, 3.0F, -1.0F, -14.0F, 3, 1, 3, 0.0F));
    this.EGG2.field_78804_l.add(new ModelBox(this.EGG2, 35, 59, 2.0F, -2.0F, -15.0F, 5, 1, 5, 0.0F));
    this.EGG2.field_78804_l.add(new ModelBox(this.EGG2, 63, 9, 3.0F, -7.0F, -14.0F, 3, 1, 3, 0.0F));
    this.EGG2.field_78804_l.add(new ModelBox(this.EGG2, 0, 53, 2.0F, -6.0F, -15.0F, 5, 2, 5, 0.0F));
    this.EGG1 = new ModelRenderer(this);
    this.EGG1.func_78793_a(2.0F, 22.0F, 0.0F);
    setRotationAngle(this.EGG1, 0.0F, 0.0F, 0.3491F);
    this.EGG1.field_78804_l.add(new ModelBox(this.EGG1, 0, 32, 0.0F, -6.0F, -17.0F, 9, 3, 9, 0.0F));
    this.EGG1.field_78804_l.add(new ModelBox(this.EGG1, 55, 59, 2.0F, -1.0F, -15.0F, 5, 1, 5, 0.0F));
    this.EGG1.field_78804_l.add(new ModelBox(this.EGG1, 48, 0, 1.0F, -3.0F, -16.0F, 7, 2, 7, 0.0F));
    this.EGG1.field_78804_l.add(new ModelBox(this.EGG1, 57, 32, 2.0F, -11.0F, -15.0F, 5, 2, 5, 0.0F));
    this.EGG1.field_78804_l.add(new ModelBox(this.EGG1, 36, 32, 1.0F, -9.0F, -16.0F, 7, 3, 7, 0.0F));
    this.EGG4 = new ModelRenderer(this);
    this.EGG4.func_78793_a(2.0F, 21.0F, 23.9F);
    setRotationAngle(this.EGG4, 0.0F, 0.0F, 0.48F);
    this.EGG4.field_78804_l.add(new ModelBox(this.EGG4, 29, 42, 1.0F, -4.0F, -16.0F, 7, 2, 7, 0.0F));
    this.EGG4.field_78804_l.add(new ModelBox(this.EGG4, 15, 53, 3.0F, -1.0F, -14.0F, 3, 1, 3, 0.0F));
    this.EGG4.field_78804_l.add(new ModelBox(this.EGG4, 15, 59, 2.0F, -2.0F, -15.0F, 5, 1, 5, 0.0F));
    this.EGG4.field_78804_l.add(new ModelBox(this.EGG4, 21, 44, 3.0F, -7.0F, -14.0F, 3, 1, 3, 0.0F));
    this.EGG4.field_78804_l.add(new ModelBox(this.EGG4, 48, 9, 2.0F, -6.0F, -15.0F, 5, 2, 5, 0.0F));
    this.grass = new ModelRenderer(this);
    this.grass.func_78793_a(0.0F, 24.0F, 0.0F);
    this.grass.field_78804_l.add(new ModelBox(this.grass, 27, 24, 10.0F, -7.0F, -1.0F, 0, 7, 8, 0.0F));
    this.grass.field_78804_l.add(new ModelBox(this.grass, 0, 0, -10.0F, -7.0F, -8.0F, 0, 7, 8, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.grass.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, -0.6981F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 28, 51, -3.0F, -8.0F, 12.0F, 10, 8, 0, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(0.0F, 0.0F, 0.0F);
    this.grass.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, 0.3054F, 0.0F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 48, 51, -14.0F, -8.0F, 8.0F, 10, 8, 0, 0.0F));
    this.paille = new ModelRenderer(this);
    this.paille.func_78793_a(0.0F, 24.0F, 0.0F);
    this.paille.field_78804_l.add(new ModelBox(this.paille, 3, 72, -17.0F, -0.1F, -10.0F, 17, 0, 20, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(0.0F, 0.0F, 0.0F);
    this.paille.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, 0.0F, 3.1416F, 0.0F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 3, 72, -17.0F, -0.1F, -10.0F, 17, 0, 20, 0.0F));
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.EGG3.func_78785_a(f5);
    this.EGG2.func_78785_a(f5);
    this.EGG1.func_78785_a(f5);
    this.EGG4.func_78785_a(f5);
    this.grass.func_78785_a(f5);
    this.paille.func_78785_a(f5);
    this.bb_main.func_78785_a(f5);
  }
  
  public void renderAll() {
    this.EGG3.func_78785_a(0.0625F);
    this.EGG2.func_78785_a(0.0625F);
    this.EGG1.func_78785_a(0.0625F);
    this.EGG4.func_78785_a(0.0625F);
    this.grass.func_78785_a(0.0625F);
    this.paille.func_78785_a(0.0625F);
    this.bb_main.func_78785_a(0.0625F);
  }
  
  public void renderCube() {
    this.bb_main.func_78785_a(0.0625F);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\easter\ModelEasterLuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */