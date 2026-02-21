package fr.paladium.palamod.modules.luckyblock.blocks.models.easter;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEasterEgg extends ModelBase {
  private final ModelRenderer EGG1;
  
  private final ModelRenderer cube_r1;
  
  public ModelEasterEgg() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.EGG1 = new ModelRenderer(this);
    this.EGG1.func_78793_a(-1.0F, 24.0F, 3.0F);
    setRotationAngle(this.EGG1, 0.0F, 0.0F, 0.3491F);
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(-1.3258F, -5.1044F, -2.5F);
    this.EGG1.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 0.0F, -0.3491F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 12, -3.5F, -3.75F, -3.5F, 7, 3, 7, 0.0F));
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 22, -2.5F, -5.75F, -2.5F, 5, 2, 5, 0.0F));
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 21, 15, -3.5F, 2.25F, -3.5F, 7, 2, 7, 0.0F));
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 15, 24, -2.5F, 4.25F, -2.5F, 5, 1, 5, 0.0F));
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 0, -4.5F, -0.75F, -4.5F, 9, 3, 9, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.EGG1.func_78785_a(f5);
  }
  
  public void renderAll() {
    this.EGG1.func_78785_a(0.0625F);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\easter\ModelEasterEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */