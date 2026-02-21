package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPrimaryCrabEgg extends ModelBase {
  private final ModelRenderer egg;
  
  public ModelPrimaryCrabEgg() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.egg = new ModelRenderer(this);
    this.egg.func_78793_a(1.0F, 16.0F, 1.0F);
    this.egg.field_78804_l.add(new ModelBox(this.egg, 0, 0, -4.0F, -1.0F, -4.0F, 7, 9, 7, 0.0F));
    this.egg.field_78804_l.add(new ModelBox(this.egg, 12, 16, 3.0F, 6.0F, -2.0F, 1, 2, 3, 0.0F));
    this.egg.field_78804_l.add(new ModelBox(this.egg, 0, 0, 3.0F, 7.0F, -3.0F, 1, 1, 1, 0.0F));
    this.egg.field_78804_l.add(new ModelBox(this.egg, 0, 16, -5.0F, 7.0F, 1.0F, 3, 1, 3, 0.0F));
    this.egg.field_78804_l.add(new ModelBox(this.egg, 0, 20, -3.0F, 5.0F, -5.0F, 3, 3, 1, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.egg.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll() {
    this.egg.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\client\renders\models\blocks\ModelPrimaryCrabEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */