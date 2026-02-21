package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDutchBoat extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer base;
  
  private final ModelRenderer left;
  
  private final ModelRenderer right;
  
  private final ModelRenderer back;
  
  private final ModelRenderer front;
  
  private final ModelRenderer bottom;
  
  private final ModelRenderer bow;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer cube_r3;
  
  private final ModelRenderer stern;
  
  private final ModelRenderer boom;
  
  public ModelDutchBoat() {
    this.field_78090_t = 128;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 42.0F, 0.0F);
    this.base = new ModelRenderer(this);
    this.base.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bone.func_78792_a(this.base);
    setRotationAngle(this.base, 0.0F, -1.5708F, 0.0F);
    this.left = new ModelRenderer(this);
    this.left.func_78793_a(0.0F, -24.0F, -9.0F);
    this.base.func_78792_a(this.left);
    this.left.field_78804_l.add(new ModelBox(this.left, 0, 43, -14.0F, -3.0F, -1.0F, 28, 6, 2, 0.0F));
    this.right = new ModelRenderer(this);
    this.right.func_78793_a(0.0F, -24.0F, 9.0F);
    this.base.func_78792_a(this.right);
    setRotationAngle(this.right, 0.0F, -3.1416F, 0.0F);
    this.right.field_78804_l.add(new ModelBox(this.right, 0, 35, -14.0F, -3.0F, -1.0F, 28, 6, 2, 0.0F));
    this.back = new ModelRenderer(this);
    this.back.func_78793_a(15.0F, -24.0F, 0.0F);
    this.base.func_78792_a(this.back);
    setRotationAngle(this.back, 0.0F, -1.5708F, 0.0F);
    this.back.field_78804_l.add(new ModelBox(this.back, 0, 19, -9.0F, -3.0F, -1.0F, 18, 6, 2, 0.0F));
    this.front = new ModelRenderer(this);
    this.front.func_78793_a(-15.0F, -24.0F, 0.0F);
    this.base.func_78792_a(this.front);
    setRotationAngle(this.front, 0.0F, 1.5708F, 0.0F);
    this.front.field_78804_l.add(new ModelBox(this.front, 0, 27, -8.0F, -3.0F, -1.0F, 16, 6, 2, 0.0F));
    this.bottom = new ModelRenderer(this);
    this.bottom.func_78793_a(0.0F, -18.0F, 0.0F);
    this.base.func_78792_a(this.bottom);
    setRotationAngle(this.bottom, -1.5708F, 0.0F, 0.0F);
    this.bottom.field_78804_l.add(new ModelBox(this.bottom, 0, 0, -14.0F, -8.0F, -3.0F, 28, 16, 3, 0.0F));
    this.bow = new ModelRenderer(this);
    this.bow.func_78793_a(0.0F, -18.0F, -4.0F);
    this.bone.func_78792_a(this.bow);
    setRotationAngle(this.bow, -0.0436F, 0.0F, 0.0F);
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, 0.0F, -4.0F);
    this.bow.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.8054F, 0.2236F, -0.2099F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 96, 22, 6.0F, -16.0F, -3.0F, 2, 8, 4, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(0.0F, 0.0F, -4.0F);
    this.bow.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.8054F, -0.2236F, 0.2099F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 96, 22, -8.0F, -16.0F, -3.0F, 2, 8, 4, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(0.0F, 0.0F, -4.0F);
    this.bow.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, 0.829F, 0.0F, 0.0F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 120, 0, -1.0F, -29.0F, -1.0F, 2, 26, 2, 0.0F));
    this.stern = new ModelRenderer(this);
    this.stern.func_78793_a(0.0F, -18.0F, 0.0F);
    this.bone.func_78792_a(this.stern);
    this.stern.field_78804_l.add(new ModelBox(this.stern, 110, 0, -1.0F, -38.0F, 8.0F, 2, 35, 2, 0.0F));
    this.stern.field_78804_l.add(new ModelBox(this.stern, 78, -15, 0.0F, -38.0F, 10.0F, 0, 20, 15, 0.0F));
    this.stern.field_78804_l.add(new ModelBox(this.stern, 0, 53, -8.0F, -17.0F, 12.0F, 16, 2, 9, 0.0F));
    this.stern.field_78804_l.add(new ModelBox(this.stern, 72, 46, -8.0F, -15.0F, 9.0F, 16, 6, 12, 0.0F));
    this.boom = new ModelRenderer(this);
    this.boom.func_78793_a(0.0F, -18.5F, 10.0F);
    this.stern.func_78792_a(this.boom);
    setRotationAngle(this.boom, -1.3526F, 0.0F, 0.0F);
    this.boom.field_78804_l.add(new ModelBox(this.boom, 120, 30, -1.0F, -10.5F, -1.0F, 2, 11, 2, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\renders\models\entities\ModelDutchBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */