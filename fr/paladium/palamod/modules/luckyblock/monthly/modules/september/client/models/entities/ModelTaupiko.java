package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTaupiko extends ModelBase {
  private final ModelRenderer bone;
  
  public ModelTaupiko() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(2.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 8, 14, -9.0F, 0.0F, -5.0F, 4, 0, 5, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -5.0F, -5.0F, -5.0F, 6, 5, 9, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 19, -3.5F, -2.0F, -7.0F, 3, 2, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 6, 1.0F, 0.0F, 2.0F, 3, 0, 3, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 3, -8.0F, 0.0F, 2.0F, 3, 0, 3, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -3.6F, -1.0F, 4.0F, 3, 0, 3, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 14, 1.0F, 0.0F, -5.0F, 4, 0, 5, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\models\entities\ModelTaupiko.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */