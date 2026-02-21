package fr.paladium.palamod.modules.palaboss.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelIceProjectile extends ModelBase {
  private final ModelRenderer bone;
  
  public ModelIceProjectile() {
    this.field_78090_t = 16;
    this.field_78089_u = 16;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.25F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 8, 6, -1.0F, -13.25F, -1.0F, 2, 8, 2, 0.2F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -1.0F, 4.75F, -1.0F, 2, 6, 2, -0.4F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -1.0F, -0.25F, -1.0F, 2, 6, 2, -0.2F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -1.0F, -5.25F, -1.0F, 2, 6, 2, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\models\ModelIceProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */