package fr.paladium.palamod.modules.luckyblock.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBasket extends ModelBase {
  private final ModelRenderer Main;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  public ModelBasket() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.Main = new ModelRenderer(this);
    this.Main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.Main.field_78804_l.add(new ModelBox(this.Main, 0, 10, -1.0F, -48.0F, 6.0F, 2, 47, 2, 0.0F));
    this.Main.field_78804_l.add(new ModelBox(this.Main, 8, 10, -10.0F, -48.0F, -1.25F, 20, 14, 0, 0.0F));
    this.Main.field_78804_l.add(new ModelBox(this.Main, 0, 0, -1.0F, -39.0F, -3.2F, 2, 1, 2, 0.0F));
    this.Main.field_78804_l.add(new ModelBox(this.Main, 8, 24, -4.0F, -39.0F, -11.2F, 8, 1, 8, 0.0F));
    this.Main.field_78804_l.add(new ModelBox(this.Main, 8, 33, -4.0F, -38.0F, -11.2F, 0, 8, 8, 0.0F));
    this.Main.field_78804_l.add(new ModelBox(this.Main, 32, 16, 4.0F, -38.0F, -11.2F, 0, 8, 8, 0.0F));
    this.Main.field_78804_l.add(new ModelBox(this.Main, 0, 0, -8.0F, -1.0F, -1.0F, 16, 1, 9, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, -31.0F, 5.0F);
    this.Main.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.6981F, 0.0F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 40, 32, -2.0F, -13.0F, 3.0F, 4, 17, 1, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(0.0F, -35.0F, -7.0F);
    this.Main.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, -3.1416F, 1.5708F, -3.1416F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 8, 25, -3.8F, -3.0F, -4.0F, 0, 8, 8, 0.0F));
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 24, 25, 4.2F, -3.0F, -4.0F, 0, 8, 8, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.Main.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll() {
    this.Main.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\ModelBasket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */