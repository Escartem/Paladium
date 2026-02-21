package fr.paladium.palamod.modules.luckyblock.blocks.models.christmas;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDeerMockup extends ModelBase {
  private final ModelRenderer DEER;
  
  private final ModelRenderer DEER_LEG_FRONT_RIGHT;
  
  private final ModelRenderer DEER_LEG_FRONT_LEFT;
  
  private final ModelRenderer DEER_BACK;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer DEER_LEG_BACK_RIGHT;
  
  private final ModelRenderer DEER_LEG_BACK_LEFT;
  
  private final ModelRenderer DEER_HEAD;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer cube_r3;
  
  private final ModelRenderer cube_r4;
  
  private final ModelRenderer cube_r5;
  
  public ModelDeerMockup() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.DEER = new ModelRenderer(this);
    this.DEER.func_78793_a(1.0F, 12.0F, 1.0F);
    setRotationAngle(this.DEER, 0.0F, -1.5708F, 0.0F);
    this.DEER.field_78804_l.add(new ModelBox(this.DEER, 0, 18, -11.0F, -10.0F, -6.0F, 7, 10, 10, 0.0F));
    this.DEER_LEG_FRONT_RIGHT = new ModelRenderer(this);
    this.DEER_LEG_FRONT_RIGHT.func_78793_a(-9.0F, 0.0F, 1.0F);
    this.DEER.func_78792_a(this.DEER_LEG_FRONT_RIGHT);
    this.DEER_LEG_FRONT_RIGHT.field_78804_l.add(new ModelBox(this.DEER_LEG_FRONT_RIGHT, 48, 33, -2.0F, 5.0F, -2.0F, 4, 7, 4, 0.0F));
    this.DEER_LEG_FRONT_RIGHT.field_78804_l.add(new ModelBox(this.DEER_LEG_FRONT_RIGHT, 31, 12, -2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F));
    this.DEER_LEG_FRONT_LEFT = new ModelRenderer(this);
    this.DEER_LEG_FRONT_LEFT.func_78793_a(-9.0F, 0.0F, -3.0F);
    this.DEER.func_78792_a(this.DEER_LEG_FRONT_LEFT);
    this.DEER_LEG_FRONT_LEFT.field_78804_l.add(new ModelBox(this.DEER_LEG_FRONT_LEFT, 31, 12, -2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F));
    this.DEER_LEG_FRONT_LEFT.field_78804_l.add(new ModelBox(this.DEER_LEG_FRONT_LEFT, 48, 33, -2.0F, 5.0F, -2.0F, 4, 7, 4, 0.0F));
    this.DEER_BACK = new ModelRenderer(this);
    this.DEER_BACK.func_78793_a(0.0F, 0.0F, 0.0F);
    this.DEER.func_78792_a(this.DEER_BACK);
    this.DEER_BACK.field_78804_l.add(new ModelBox(this.DEER_BACK, 0, 38, -8.0F, -9.0F, -5.0F, 17, 8, 8, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(10.0F, -7.0F, -2.0F);
    this.DEER_BACK.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 0.0F, -0.3927F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 52, 14, -2.75F, -0.5F, 0.0F, 2, 4, 2, 0.0F));
    this.DEER_LEG_BACK_RIGHT = new ModelRenderer(this);
    this.DEER_LEG_BACK_RIGHT.func_78793_a(7.0F, -0.75F, 1.0F);
    this.DEER_BACK.func_78792_a(this.DEER_LEG_BACK_RIGHT);
    this.DEER_LEG_BACK_RIGHT.field_78804_l.add(new ModelBox(this.DEER_LEG_BACK_RIGHT, 48, 33, -2.0F, 5.75F, -2.0F, 4, 7, 4, 0.0F));
    this.DEER_LEG_BACK_RIGHT.field_78804_l.add(new ModelBox(this.DEER_LEG_BACK_RIGHT, 48, 22, -2.0F, -0.25F, -2.0F, 4, 6, 4, 0.0F));
    this.DEER_LEG_BACK_LEFT = new ModelRenderer(this);
    this.DEER_LEG_BACK_LEFT.func_78793_a(7.0F, -0.75F, -3.0F);
    this.DEER_BACK.func_78792_a(this.DEER_LEG_BACK_LEFT);
    this.DEER_LEG_BACK_LEFT.field_78804_l.add(new ModelBox(this.DEER_LEG_BACK_LEFT, 48, 22, -2.0F, -0.25F, -2.0F, 4, 6, 4, 0.0F));
    this.DEER_LEG_BACK_LEFT.field_78804_l.add(new ModelBox(this.DEER_LEG_BACK_LEFT, 48, 33, -2.0F, 5.75F, -2.0F, 4, 7, 4, 0.0F));
    this.DEER_HEAD = new ModelRenderer(this);
    this.DEER_HEAD.func_78793_a(-9.0339F, -9.8759F, -0.9821F);
    this.DEER.func_78792_a(this.DEER_HEAD);
    setRotationAngle(this.DEER_HEAD, 0.0F, 0.0F, -0.3054F);
    this.DEER_HEAD.field_78804_l.add(new ModelBox(this.DEER_HEAD, 42, 0, -3.9661F, -5.1241F, -2.0179F, 7, 7, 4, 0.0F));
    this.DEER_HEAD.field_78804_l.add(new ModelBox(this.DEER_HEAD, 29, 0, -6.9661F, -5.1241F, -0.0179F, 6, 10, 0, 0.0F));
    this.DEER_HEAD.field_78804_l.add(new ModelBox(this.DEER_HEAD, 0, 0, -4.9661F, -9.1241F, -3.0179F, 8, 4, 6, 0.0F));
    this.DEER_HEAD.field_78804_l.add(new ModelBox(this.DEER_HEAD, 0, 10, -9.9661F, -9.1241F, -2.0179F, 5, 4, 4, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(1.0339F, -9.1241F, -2.0179F);
    this.DEER_HEAD.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, 0.2182F, 0.0F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 0, 56, -4.0F, -8.0F, 0.0F, 16, 8, 0, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(1.0339F, -9.1241F, 1.9821F);
    this.DEER_HEAD.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, 0.0F, -0.2182F, 0.0F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 0, 56, -4.0F, -8.0F, 0.0F, 16, 8, 0, 0.0F));
    this.cube_r4 = new ModelRenderer(this);
    this.cube_r4.func_78793_a(2.0339F, -9.3741F, -2.5179F);
    this.DEER_HEAD.func_78792_a(this.cube_r4);
    setRotationAngle(this.cube_r4, 0.5236F, 0.0F, 0.0F);
    this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 19, 14, -1.0F, -2.75F, -0.5F, 2, 3, 1, 0.0F));
    this.cube_r5 = new ModelRenderer(this);
    this.cube_r5.func_78793_a(2.0339F, -9.1241F, 2.4821F);
    this.DEER_HEAD.func_78792_a(this.cube_r5);
    setRotationAngle(this.cube_r5, -0.5236F, 0.0F, 0.0F);
    this.cube_r5.field_78804_l.add(new ModelBox(this.cube_r5, 19, 14, -1.0F, -3.0F, -0.5F, 2, 3, 1, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.DEER.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\christmas\ModelDeerMockup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */