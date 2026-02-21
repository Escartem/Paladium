package fr.paladium.palamod.modules.ajobs.client.renders.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelJobThrone extends ModelBase {
  private final ModelRenderer group;
  
  private final ModelRenderer bone;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer bone2;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer cube_r3;
  
  private final ModelRenderer cube_r4;
  
  private final ModelRenderer cube_r5;
  
  private final ModelRenderer bone3;
  
  private final ModelRenderer cube_r6;
  
  private final ModelRenderer cube_r7;
  
  private final ModelRenderer cube_r8;
  
  private final ModelRenderer cube_r9;
  
  public ModelJobThrone() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.group = new ModelRenderer(this);
    this.group.func_78793_a(6.0F, 12.0F, -1.0F);
    this.group.field_78804_l.add(new ModelBox(this.group, 0, 41, -12.0F, 3.0F, -8.0F, 12, 6, 14, 0.0F));
    this.group.field_78804_l.add(new ModelBox(this.group, 0, 0, -15.0F, -20.0F, 6.0F, 18, 29, 5, 0.0F));
    this.group.field_78804_l.add(new ModelBox(this.group, 46, 0, -13.0F, 9.0F, -5.0F, 14, 3, 13, 0.0F));
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(-6.0F, -23.5F, 8.5F);
    this.group.func_78792_a(this.bone);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 52, 72, -2.0F, -8.5F, -6.5F, 4, 5, 4, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 22, 63, -7.0F, -10.5F, -1.5F, 4, 7, 4, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 22, 63, 3.0F, -10.5F, -1.5F, 4, 7, 4, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bone.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 0.7854F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 32, 20, -7.0F, -3.5F, -7.0F, 14, 7, 14, 0.0F));
    this.bone2 = new ModelRenderer(this);
    this.bone2.func_78793_a(5.2591F, -4.8572F, -1.0F);
    this.group.func_78792_a(this.bone2);
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 0, 61, -5.2591F, 4.8572F, -7.0F, 4, 9, 14, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bone2.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, 0.0F, 0.3927F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 64, 41, -3.0F, -0.5F, -7.0F, 6, 1, 14, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(-4.573F, 1.8942F, 0.0F);
    this.bone2.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, 0.0F, 0.0F, 0.3927F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 66, 56, -0.5F, -3.0F, -7.0F, 1, 6, 14, 0.0F));
    this.cube_r4 = new ModelRenderer(this);
    this.cube_r4.func_78793_a(-3.2336F, -1.3394F, 0.0F);
    this.bone2.func_78792_a(this.cube_r4);
    setRotationAngle(this.cube_r4, 0.0F, 0.0F, 0.3927F);
    this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 36, 67, -0.5F, -0.5F, -7.0F, 1, 1, 14, 0.0F));
    this.cube_r5 = new ModelRenderer(this);
    this.cube_r5.func_78793_a(-1.3394F, 3.2336F, -1.0F);
    this.bone2.func_78792_a(this.cube_r5);
    setRotationAngle(this.cube_r5, 0.0F, 0.0F, 0.3927F);
    this.cube_r5.field_78804_l.add(new ModelBox(this.cube_r5, 36, 45, -3.0F, -3.0F, -8.0F, 6, 6, 16, 0.0F));
    this.bone3 = new ModelRenderer(this);
    this.bone3.func_78793_a(-17.2591F, -4.8572F, -1.0F);
    this.group.func_78792_a(this.bone3);
    this.bone3.field_78804_l.add(new ModelBox(this.bone3, 0, 61, 1.2591F, 4.8572F, -7.0F, 4, 9, 14, 0.0F));
    this.cube_r6 = new ModelRenderer(this);
    this.cube_r6.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bone3.func_78792_a(this.cube_r6);
    setRotationAngle(this.cube_r6, 0.0F, 0.0F, -0.3927F);
    this.cube_r6.field_78804_l.add(new ModelBox(this.cube_r6, 64, 41, -3.0F, -0.5F, -7.0F, 6, 1, 14, 0.0F));
    this.cube_r7 = new ModelRenderer(this);
    this.cube_r7.func_78793_a(4.573F, 1.8942F, 0.0F);
    this.bone3.func_78792_a(this.cube_r7);
    setRotationAngle(this.cube_r7, 0.0F, 0.0F, -0.3927F);
    this.cube_r7.field_78804_l.add(new ModelBox(this.cube_r7, 66, 56, -0.5F, -3.0F, -7.0F, 1, 6, 14, 0.0F));
    this.cube_r8 = new ModelRenderer(this);
    this.cube_r8.func_78793_a(3.2336F, -1.3394F, 0.0F);
    this.bone3.func_78792_a(this.cube_r8);
    setRotationAngle(this.cube_r8, 0.0F, 0.0F, -0.3927F);
    this.cube_r8.field_78804_l.add(new ModelBox(this.cube_r8, 36, 67, -0.5F, -0.5F, -7.0F, 1, 1, 14, 0.0F));
    this.cube_r9 = new ModelRenderer(this);
    this.cube_r9.func_78793_a(1.3394F, 3.2336F, -1.0F);
    this.bone3.func_78792_a(this.cube_r9);
    setRotationAngle(this.cube_r9, 0.0F, 0.0F, -0.3927F);
    this.cube_r9.field_78804_l.add(new ModelBox(this.cube_r9, 36, 45, -3.0F, -3.0F, -8.0F, 6, 6, 16, 0.0F));
  }
  
  public void renderAll() {
    this.group.func_78785_a(0.0625F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.group.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\client\renders\models\ModelJobThrone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */