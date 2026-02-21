package fr.paladium.palamod.modules.luckyblock.renders.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGadgeto extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer Hand;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r1_r1;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer cube_r2_r1;
  
  private final ModelRenderer cube_r3;
  
  private final ModelRenderer cube_r3_r1;
  
  private final ModelRenderer cube_r4;
  
  private final ModelRenderer cube_r4_r1;
  
  private final ModelRenderer Arm;
  
  private final ModelRenderer cube_r5;
  
  private final ModelRenderer gear1;
  
  private final ModelRenderer cube_r6;
  
  private final ModelRenderer gear2;
  
  private final ModelRenderer cube_r7;
  
  private final ModelRenderer gear3;
  
  private final ModelRenderer cube_r8;
  
  private final ModelRenderer gear4;
  
  private final ModelRenderer cube_r9;
  
  private final ModelRenderer arm1;
  
  private final ModelRenderer arm2;
  
  private final ModelRenderer cube_r10;
  
  public ModelGadgeto() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.6F, 20.5F, -4.5F);
    this.Hand = new ModelRenderer(this);
    this.Hand.func_78793_a(-1.6F, 0.5F, 1.5F);
    this.bone.func_78792_a(this.Hand);
    setRotationAngle(this.Hand, 0.0F, 0.0F, 1.5708F);
    this.Hand.field_78804_l.add(new ModelBox(this.Hand, 5, 0, -1.0F, -2.0F, 0.0F, 3, 2, 1, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(-1.0F, -2.0F, 0.0F);
    this.Hand.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 1.789F, 0.0F, 0.0F);
    this.cube_r1_r1 = new ModelRenderer(this);
    this.cube_r1_r1.func_78793_a(2.5F, -0.4F, -2.4F);
    this.cube_r1.func_78792_a(this.cube_r1_r1);
    setRotationAngle(this.cube_r1_r1, -0.4363F, 0.0F, 0.0F);
    this.cube_r1_r1.field_78804_l
      .add(new ModelBox(this.cube_r1_r1, 11, 12, -0.5F, -2.0761F, -0.1173F, 1, 2, 1, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(-1.0F, -2.0F, 0.0F);
    this.Hand.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 1.4835F, 0.0F, 0.0F);
    this.cube_r2_r1 = new ModelRenderer(this);
    this.cube_r2_r1.func_78793_a(1.5F, 0.2F, -2.5F);
    this.cube_r2.func_78792_a(this.cube_r2_r1);
    setRotationAngle(this.cube_r2_r1, -0.2618F, 0.0F, 0.0F);
    this.cube_r2_r1.field_78804_l.add(new ModelBox(this.cube_r2_r1, 13, 0, -0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(-1.0F, -2.0F, 0.0F);
    this.Hand.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, 1.2217F, 0.0F, 0.0F);
    this.cube_r3_r1 = new ModelRenderer(this);
    this.cube_r3_r1.func_78793_a(0.5F, 0.7F, -2.3F);
    this.cube_r3.func_78792_a(this.cube_r3_r1);
    setRotationAngle(this.cube_r3_r1, 0.2182F, 0.0F, 0.0F);
    this.cube_r3_r1.field_78804_l.add(new ModelBox(this.cube_r3_r1, 6, 13, -0.5F, -1.9F, -0.5F, 1, 2, 1, 0.0F));
    this.cube_r4 = new ModelRenderer(this);
    this.cube_r4.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.Hand.func_78792_a(this.cube_r4);
    setRotationAngle(this.cube_r4, 0.0F, -2.1817F, 0.0F);
    this.cube_r4_r1 = new ModelRenderer(this);
    this.cube_r4_r1.func_78793_a(0.0F, -1.0F, -0.5F);
    this.cube_r4.func_78792_a(this.cube_r4_r1);
    setRotationAngle(this.cube_r4_r1, 0.0097F, 0.218F, 0.0447F);
    this.cube_r4_r1.field_78804_l.add(new ModelBox(this.cube_r4_r1, 10, 9, -2.0F, -0.5F, 0.5F, 2, 1, 1, 0.0F));
    this.Arm = new ModelRenderer(this);
    this.Arm.func_78793_a(-1.1F, -0.5F, 11.5F);
    this.bone.func_78792_a(this.Arm);
    this.cube_r5 = new ModelRenderer(this);
    this.cube_r5.func_78793_a(0.0F, 0.0F, 0.0F);
    this.Arm.func_78792_a(this.cube_r5);
    setRotationAngle(this.cube_r5, 0.7854F, 0.0F, 0.0F);
    this.cube_r5.field_78804_l.add(new ModelBox(this.cube_r5, 0, 8, -0.5F, -2.0F, -1.0F, 1, 2, 2, 0.0F));
    this.gear1 = new ModelRenderer(this);
    this.gear1.func_78793_a(0.0F, 1.4F, -5.0F);
    this.Arm.func_78792_a(this.gear1);
    this.cube_r6 = new ModelRenderer(this);
    this.cube_r6.func_78793_a(0.0F, 0.0F, 2.0F);
    this.gear1.func_78792_a(this.cube_r6);
    setRotationAngle(this.cube_r6, 0.0F, 0.0F, -0.7854F);
    this.cube_r6.field_78804_l.add(new ModelBox(this.cube_r6, 0, 4, -1.0F, -1.0F, -3.0F, 2, 2, 2, 0.0F));
    this.gear2 = new ModelRenderer(this);
    this.gear2.func_78793_a(0.0F, 1.4F, -3.0F);
    this.Arm.func_78792_a(this.gear2);
    this.cube_r7 = new ModelRenderer(this);
    this.cube_r7.func_78793_a(0.0F, 0.0F, 0.0F);
    this.gear2.func_78792_a(this.cube_r7);
    setRotationAngle(this.cube_r7, 0.0F, 0.0F, -0.7854F);
    this.cube_r7.field_78804_l.add(new ModelBox(this.cube_r7, 0, 12, -1.0F, -1.0F, -5.0F, 2, 2, 1, 0.0F));
    this.gear3 = new ModelRenderer(this);
    this.gear3.func_78793_a(0.9F, 0.4F, -1.0F);
    this.Arm.func_78792_a(this.gear3);
    this.cube_r8 = new ModelRenderer(this);
    this.cube_r8.func_78793_a(0.0F, 0.0F, 0.0F);
    this.gear3.func_78792_a(this.cube_r8);
    setRotationAngle(this.cube_r8, 0.7854F, 0.0F, 0.0F);
    this.cube_r8.field_78804_l.add(new ModelBox(this.cube_r8, 6, 9, -0.5F, -1.0F, -1.0F, 1, 2, 2, 0.0F));
    this.gear4 = new ModelRenderer(this);
    this.gear4.func_78793_a(-0.9F, 0.4F, -1.0F);
    this.Arm.func_78792_a(this.gear4);
    this.cube_r9 = new ModelRenderer(this);
    this.cube_r9.func_78793_a(0.0F, 0.0F, 0.0F);
    this.gear4.func_78792_a(this.cube_r9);
    setRotationAngle(this.cube_r9, -0.7854F, 0.0F, 0.0F);
    this.cube_r9.field_78804_l.add(new ModelBox(this.cube_r9, 10, 3, -0.5F, -1.0F, -1.0F, 1, 2, 2, 0.0F));
    this.arm1 = new ModelRenderer(this);
    this.arm1.func_78793_a(0.0F, 1.5F, -7.5F);
    this.Arm.func_78792_a(this.arm1);
    this.arm1.field_78804_l.add(new ModelBox(this.arm1, 5, 5, -0.5F, -0.5F, -1.5F, 1, 1, 3, 0.0F));
    this.arm2 = new ModelRenderer(this);
    this.arm2.func_78793_a(0.0F, 0.9362F, -2.7052F);
    this.Arm.func_78792_a(this.arm2);
    this.cube_r10 = new ModelRenderer(this);
    this.cube_r10.func_78793_a(0.0F, 0.5638F, 0.2052F);
    this.arm2.func_78792_a(this.cube_r10);
    setRotationAngle(this.cube_r10, 0.3491F, 0.0F, 0.0F);
    this.cube_r10.field_78804_l.add(new ModelBox(this.cube_r10, 0, 0, -0.5F, -1.1F, -1.5F, 1, 1, 3, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\ModelGadgeto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */