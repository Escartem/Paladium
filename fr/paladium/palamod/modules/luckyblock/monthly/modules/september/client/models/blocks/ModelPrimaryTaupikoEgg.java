package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPrimaryTaupikoEgg extends ModelBase {
  private final ModelRenderer egg;
  
  public ModelPrimaryTaupikoEgg() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.egg = new ModelRenderer(this);
    this.egg.func_78793_a(8.0F, 24.0F, -8.0F);
    this.egg.field_78804_l.add(new ModelBox(this.egg, 0, 0, -13.0F, -13.0F, 3.0F, 10, 13, 10, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\models\blocks\ModelPrimaryTaupikoEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */