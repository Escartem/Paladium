package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelJulyLuckyBlock extends ModelBase {
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer cube_r3;
  
  public ModelJulyLuckyBlock() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 8, 32, -14.0F, -27.0F, 5.0F, 28, 15, 0, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 8, 32, -14.0F, -27.0F, 0.0F, 28, 15, 0, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 26, 51, -5.0F, -19.1F, -5.0F, 10, 3, 10, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(9.5F, -12.5F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.1309F, 0.0F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 2, 39, -1.5F, 0.5F, 0.0F, 5, 11, 0, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(-7.0F, -16.0F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, 0.0F, 0.6981F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 44, 37, 0.0F, -4.0F, -5.0F, 0, 4, 10, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(7.0F, -16.0F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, 0.0F, 0.0F, -0.6981F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 44, 37, 0.0F, -4.0F, -5.0F, 0, 4, 10, 0.0F));
    ModelRenderer bb_second = new ModelRenderer(this);
    bb_second.field_78804_l.add(new ModelBox(this.bb_main, 8, 32, -14.0F, -27.0F, 5.0F, 28, 15, 0, 0.0F));
    bb_second.field_78804_l.add(new ModelBox(this.bb_main, 8, 32, -14.0F, -27.0F, 0.0F, 28, 15, 0, 0.0F));
    bb_second.func_78793_a(0.0F, 0.0F, 0.0F);
    setRotationAngle(bb_second, 0.0F, 3.2F, 0.0F);
    this.bb_main.func_78792_a(bb_second);
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\renders\models\blocks\ModelJulyLuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */