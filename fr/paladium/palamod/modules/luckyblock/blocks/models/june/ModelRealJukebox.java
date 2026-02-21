package fr.paladium.palamod.modules.luckyblock.blocks.models.june;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRealJukebox extends ModelBase {
  private final ModelRenderer bb_main;
  
  public ModelRealJukebox() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 7, -4.0F, -11.0F, -4.0F, 9, 11, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -3.0F, -12.0F, -4.0F, 7, 1, 6, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 14, 13, -2.0F, -13.0F, -4.0F, 5, 1, 6, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 20, 20, 4.5F, -2.0F, 1.5F, 1, 2, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 20, -4.0F, -7.0F, 1.0F, 9, 7, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 28, -4.0F, -11.0F, -3.0F, 1, 11, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 20, 20, 4.0F, -11.0F, -3.0F, 1, 11, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 20, 0, 4.5F, -2.0F, -4.5F, 1, 2, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 3, -4.5F, -2.0F, 1.5F, 1, 2, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -4.5F, -2.0F, -4.5F, 1, 2, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 20, 7, -4.0F, -11.0F, 1.0F, 9, 4, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 20, 0, -3.0F, -8.0F, -3.0F, 7, 1, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 6, 28, -3.0F, -9.0F, -1.0F, 4, 1, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 30, 15, -2.0F, -10.0F, -1.0F, 2, 1, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 26, 20, 0.0F, -9.0F, -3.0F, 4, 1, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 30, 12, 2.0F, -10.0F, -3.0F, 2, 1, 2, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\june\ModelRealJukebox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */