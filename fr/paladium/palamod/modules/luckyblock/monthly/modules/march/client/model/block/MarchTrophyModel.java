package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MarchTrophyModel extends ModelBase {
  private final ModelRenderer bb_main;
  
  public MarchTrophyModel() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -4.0F, -2.0F, -4.0F, 8, 2, 8, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 18, -3.0F, -3.0F, -5.0F, 6, 3, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 10, -3.0F, -4.0F, -3.0F, 6, 2, 6, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -1.0F, -11.0F, -1.0F, 2, 6, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 14, 18, -0.5F, -14.0F, -0.5F, 1, 3, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 18, 10, -2.0F, -9.0F, -0.5F, 1, 5, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 10, 1.0F, -9.0F, -0.5F, 1, 5, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 18, 18, -0.5F, -5.0F, -0.5F, 1, 1, 1, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\model\block\MarchTrophyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */