package fr.paladium.palamod.modules.palaboss.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSandstorm extends ModelBase {
  private final ModelRenderer bone4;
  
  private final ModelRenderer bone;
  
  private final ModelRenderer bone2;
  
  private final ModelRenderer bone3;
  
  public ModelSandstorm() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone4 = new ModelRenderer(this);
    this.bone4.func_78793_a(-0.25F, 12.0F, 0.25F);
    this.bone4.field_78804_l.add(new ModelBox(this.bone4, 4, 0, -7.75F, -15.0F, -7.25F, 15, 6, 15, 0.0F));
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 2.0F, 0.0F);
    this.bone4.func_78792_a(this.bone);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 20, 0, -5.75F, -16.0F, -5.25F, 11, 12, 11, 0.0F));
    this.bone2 = new ModelRenderer(this);
    this.bone2.func_78793_a(0.0F, 5.5F, 0.0F);
    this.bone.func_78792_a(this.bone2);
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 52, 0, -1.75F, -13.5F, -1.25F, 3, 18, 3, 0.0F));
    this.bone3 = new ModelRenderer(this);
    this.bone3.func_78793_a(-0.25F, -10.5F, 0.25F);
    this.bone2.func_78792_a(this.bone3);
    this.bone3.field_78804_l.add(new ModelBox(this.bone3, 36, 0, -3.5F, -9.0F, -3.5F, 7, 18, 7, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    this.bone4.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, e);
    this.bone4.field_78796_g = f2 / 20.0F;
    this.bone.field_78796_g = f2 / 15.0F;
    this.bone2.field_78796_g = f2 / 10.0F;
    this.bone3.field_78796_g = f2 / 5.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\models\ModelSandstorm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */