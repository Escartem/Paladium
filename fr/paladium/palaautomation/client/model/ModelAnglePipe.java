package fr.paladium.palaautomation.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAnglePipe extends ModelBase {
  private final ModelRenderer bone3;
  
  private final ModelRenderer bone;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer bone2;
  
  private final ModelRenderer cube_r2;
  
  public ModelAnglePipe() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone3 = new ModelRenderer(this);
    this.bone3.func_78793_a(8.0F, 12.0F, -4.0F);
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(3.0F, -10.0F, -2.0F);
    this.bone3.func_78792_a(this.bone);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -11.0F, 2.0F, -2.0F, 3, 8, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 22, 0, -6.0F, 3.0F, -1.0F, 6, 6, 6, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bone.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 0.0F, -1.5708F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 16, -1.0F, -7.0F, -2.0F, 3, 8, 8, 0.0F));
    this.bone2 = new ModelRenderer(this);
    this.bone2.func_78793_a(3.0F, -10.0F, -2.0F);
    this.bone3.func_78792_a(this.bone2);
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 22, 24, -8.0F, 3.0F, -1.0F, 2, 6, 6, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(-6.0F, -2.0F, 0.0F);
    this.bone2.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, 0.0F, 1.5708F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 22, 12, 3.0F, -6.0F, -1.0F, 2, 6, 6, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone3.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll() {
    this.bone3.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\client\model\ModelAnglePipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */