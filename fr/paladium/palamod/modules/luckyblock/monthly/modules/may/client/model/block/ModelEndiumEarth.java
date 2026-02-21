package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEndiumEarth extends ModelBase {
  private final ModelRenderer bb_main;
  
  public ModelEndiumEarth() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -4.0F, -2.0F, -4.0F, 8, 2, 8, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 10, -3.0F, -8.0F, -3.0F, 6, 6, 6, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -1.0F, -10.0F, -1.0F, 2, 2, 2, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\client\model\block\ModelEndiumEarth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */