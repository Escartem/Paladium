package fr.paladium.palamod.modules.luckyblock.renders.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWaterPistol extends ModelBase {
  private final ModelRenderer group;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer bone;
  
  private final ModelRenderer cube_r3;
  
  public ModelWaterPistol() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.group = new ModelRenderer(this);
    this.group.func_78793_a(0.0F, 20.0F, 4.0F);
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, -0.5115F, -1.4968F);
    this.group.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.3927F, 0.0F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 19, -1.0F, -1.5F, -1.5F, 2, 3, 3, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(-8.0F, -7.3499F, 10.2315F);
    this.group.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.3927F, 0.0F, 0.0F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 0, 0, 7.0F, 1.3296F, -11.9525F, 2, 6, 3, 0.0F));
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(8.0F, 4.0F, -8.0F);
    this.group.func_78792_a(this.bone);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 19, -10.0F, -10.0F, 1.0F, 4, 5, 13, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 31, 34, -10.0F, -13.0F, 11.0F, 4, 3, 3, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 25, 0, -10.0F, -10.0F, -6.0F, 4, 5, 5, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -11.0F, -16.0F, -2.0F, 6, 6, 13, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(-8.0F, -7.5F, -4.4F);
    this.bone.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, 0.0F, 0.0F, 0.7854F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 21, 19, -1.4F, -1.4F, -5.4F, 2, 2, 10, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\ModelWaterPistol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */