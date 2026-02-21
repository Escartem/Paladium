package fr.paladium.palamod.modules.luckyblock.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBallBasket extends ModelBase {
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r1;
  
  public ModelBallBasket() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, -8.0F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, -1.5708F, 0.0F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 50, 18, -6.0F, -9.0F, -5.0F, 12, 18, 10, 0.0F));
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 0, -8.0F, -7.0F, -7.0F, 16, 14, 14, 0.0F));
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 28, -6.0F, -5.0F, -9.0F, 12, 10, 18, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\ModelBallBasket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */