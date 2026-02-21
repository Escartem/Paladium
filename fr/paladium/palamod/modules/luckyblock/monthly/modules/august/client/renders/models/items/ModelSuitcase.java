package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.models.items;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSuitcase extends ModelBase {
  private final ModelRenderer bone;
  
  public ModelSuitcase() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(8.0F, 24.0F, -8.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -15.0F, -5.0F, -1.0F, 9, 5, 18, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 18, 0, -6.0F, -2.5F, -1.0F, 6, 0, 18, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 23, -15.1F, -5.1F, -1.1F, 9, 5, 18, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\client\renders\models\items\ModelSuitcase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */