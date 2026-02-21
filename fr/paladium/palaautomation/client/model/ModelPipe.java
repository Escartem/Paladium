package fr.paladium.palaautomation.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPipe extends ModelBase {
  private final ModelRenderer bone;
  
  public ModelPipe() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 20.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 22, 0, -3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 22, 12, 3.0F, -3.0F, -3.0F, 2, 6, 6, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 22, 24, -5.0F, -3.0F, -3.0F, 2, 6, 6, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, 5.0F, -4.0F, -4.0F, 3, 8, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 16, -8.0F, -4.0F, -4.0F, 3, 8, 8, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll() {
    this.bone.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\client\model\ModelPipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */