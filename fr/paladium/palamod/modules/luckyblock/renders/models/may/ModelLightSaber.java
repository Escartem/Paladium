package fr.paladium.palamod.modules.luckyblock.renders.models.may;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLightSaber extends ModelBase {
  private final ModelRenderer bb_main;
  
  public ModelLightSaber() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 12, 6, -1.0F, -1.0F, -1.0F, 2, 1, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 8, 0, -0.5F, -20.0F, -0.5F, 1, 14, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 12, 9, -0.5F, -3.0F, -0.5F, 1, 2, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 12, 0, -1.0F, -6.0F, -1.0F, 2, 4, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -1.0F, -21.0F, -1.0F, 2, 15, 2, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bb_main.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\may\ModelLightSaber.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */