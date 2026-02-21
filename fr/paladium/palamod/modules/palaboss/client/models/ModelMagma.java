package fr.paladium.palamod.modules.palaboss.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMagma extends ModelBase {
  private final ModelRenderer bone;
  
  public ModelMagma() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -5.0F, -13.0F, -5.0F, 10, 10, 10, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -3.0F, -11.0F, -4.0F, 10, 10, 10, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -6.0F, -12.0F, -5.0F, 1, 8, 9, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -7.0F, -9.0F, -3.0F, 2, 4, 6, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -4.0F, -14.0F, -6.0F, 10, 10, 10, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, e);
    this.bone.field_78795_f = f2 * 20.0F;
    this.bone.field_78796_g = f2 * 20.0F;
    this.bone.field_78808_h = f2 * 20.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\models\ModelMagma.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */