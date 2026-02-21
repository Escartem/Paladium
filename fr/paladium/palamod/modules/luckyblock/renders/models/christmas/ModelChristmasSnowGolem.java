package fr.paladium.palamod.modules.luckyblock.renders.models.christmas;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelChristmasSnowGolem extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer cube_r3;
  
  private final ModelRenderer cube_r4;
  
  private final ModelRenderer cube_r5;
  
  private final ModelRenderer cube_r6;
  
  private final ModelRenderer cube_r7;
  
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r8;
  
  private final ModelRenderer cube_r9;
  
  private final ModelRenderer cube_r10;
  
  private final ModelRenderer cube_r11;
  
  private final ModelRenderer cube_r12;
  
  private final ModelRenderer cube_r13;
  
  private final ModelRenderer cube_r14;
  
  public ModelChristmasSnowGolem() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(15.2F, 2.4F, 3.8F);
    setRotationAngle(this.bone, 0.0F, 0.0F, -3.1416F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 4, 3.8F, 1.6F, -4.8F, 6, 2, 2, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(1.0F, 5.0F, 0.4F);
    this.bone.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, -2.1341F, 0.4334F, 2.8591F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 45, 43, -0.5F, -1.0F, -1.2F, 1, 2, 1, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(-1.8F, 2.6F, -1.2F);
    this.bone.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.5387F, 0.1178F, -1.4495F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 0, 41, -0.5F, -1.0F, -1.2F, 1, 3, 1, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(1.9F, 3.0F, 2.7F);
    this.bone.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, 3.1247F, 0.1468F, 1.8581F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 46, 32, -0.5F, -1.0F, -1.2F, 1, 2, 1, 0.0F));
    this.cube_r4 = new ModelRenderer(this);
    this.cube_r4.func_78793_a(1.9F, 3.0F, 2.7F);
    this.bone.func_78792_a(this.cube_r4);
    setRotationAngle(this.cube_r4, -1.0641F, 0.1468F, 1.8581F);
    this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 4, 41, -0.5F, -1.0F, -1.2F, 1, 3, 1, 0.0F));
    this.cube_r5 = new ModelRenderer(this);
    this.cube_r5.func_78793_a(-1.8F, 2.6F, -1.2F);
    this.bone.func_78792_a(this.cube_r5);
    setRotationAngle(this.cube_r5, -1.5557F, 0.1178F, -1.4495F);
    this.cube_r5.field_78804_l.add(new ModelBox(this.cube_r5, 46, 35, -0.5F, -1.0F, -1.2F, 1, 2, 1, 0.0F));
    this.cube_r6 = new ModelRenderer(this);
    this.cube_r6.func_78793_a(1.0F, 5.0F, 0.4F);
    this.bone.func_78792_a(this.cube_r6);
    setRotationAngle(this.cube_r6, -0.0397F, 0.4334F, 2.8591F);
    this.cube_r6.field_78804_l.add(new ModelBox(this.cube_r6, 8, 41, -0.5F, -1.0F, -1.2F, 1, 3, 1, 0.0F));
    this.cube_r7 = new ModelRenderer(this);
    this.cube_r7.func_78793_a(0.8F, 2.6F, -3.8F);
    this.bone.func_78792_a(this.cube_r7);
    setRotationAngle(this.cube_r7, 0.0F, 0.8727F, 0.0F);
    this.cube_r7.field_78804_l.add(new ModelBox(this.cube_r7, 0, 0, -3.5F, -1.0F, 1.5F, 6, 2, 2, 0.0F));
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 44, 36, -6.0F, -28.0F, -6.0F, 12, 12, 12, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 38, 60, -5.0F, -38.0F, -5.0F, 10, 10, 10, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 6, 38, -1.0F, -9.0F, 8.0F, 2, 2, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 38, -1.0F, -13.0F, 8.0F, 2, 2, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 6, 35, -1.0F, -19.0F, 6.0F, 2, 2, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 35, -1.0F, -23.0F, 6.0F, 2, 2, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 6, 32, -1.0F, -27.0F, 6.0F, 2, 2, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 32, -1.0F, -5.0F, 8.0F, 2, 2, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 12, -12.0F, -23.0F, -1.0F, 6, 2, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 32, -7.0F, -37.0F, -7.0F, 14, 2, 14, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 48, -6.0F, -42.0F, -6.0F, 12, 5, 12, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 48, 0, -5.0F, -45.0F, -5.0F, 10, 3, 10, 0.0F));
    this.cube_r8 = new ModelRenderer(this);
    this.cube_r8.func_78793_a(-13.9F, -21.6F, 6.5F);
    this.bb_main.func_78792_a(this.cube_r8);
    setRotationAngle(this.cube_r8, -1.0641F, 0.1468F, 1.8581F);
    this.cube_r8.field_78804_l.add(new ModelBox(this.cube_r8, 42, 32, -0.5F, -1.0F, -1.2F, 1, 3, 1, 0.0F));
    this.cube_r9 = new ModelRenderer(this);
    this.cube_r9.func_78793_a(-13.9F, -21.6F, 6.5F);
    this.bb_main.func_78792_a(this.cube_r9);
    setRotationAngle(this.cube_r9, 3.1247F, 0.1468F, 1.8581F);
    this.cube_r9.field_78804_l.add(new ModelBox(this.cube_r9, 46, 38, -0.5F, -1.0F, -1.2F, 1, 2, 1, 0.0F));
    this.cube_r10 = new ModelRenderer(this);
    this.cube_r10.func_78793_a(-17.6F, -22.0F, 2.6F);
    this.bb_main.func_78792_a(this.cube_r10);
    setRotationAngle(this.cube_r10, -1.5557F, 0.1178F, -1.4495F);
    this.cube_r10.field_78804_l.add(new ModelBox(this.cube_r10, 0, 48, -0.5F, -1.0F, -1.2F, 1, 2, 1, 0.0F));
    this.cube_r11 = new ModelRenderer(this);
    this.cube_r11.func_78793_a(-17.6F, -22.0F, 2.6F);
    this.bb_main.func_78792_a(this.cube_r11);
    setRotationAngle(this.cube_r11, 0.5387F, 0.1178F, -1.4495F);
    this.cube_r11.field_78804_l.add(new ModelBox(this.cube_r11, 42, 36, -0.5F, -1.0F, -1.2F, 1, 3, 1, 0.0F));
    this.cube_r12 = new ModelRenderer(this);
    this.cube_r12.func_78793_a(-15.8F, -24.6F, 3.8F);
    this.bb_main.func_78792_a(this.cube_r12);
    setRotationAngle(this.cube_r12, -1.7906F, -0.7004F, -0.4244F);
    this.cube_r12.field_78804_l.add(new ModelBox(this.cube_r12, 48, 0, -0.5F, -1.0F, -1.2F, 1, 2, 1, 0.0F));
    this.cube_r13 = new ModelRenderer(this);
    this.cube_r13.func_78793_a(-15.8F, -24.6F, 3.8F);
    this.bb_main.func_78792_a(this.cube_r13);
    setRotationAngle(this.cube_r13, 0.3038F, -0.7004F, -0.4244F);
    this.cube_r13.field_78804_l.add(new ModelBox(this.cube_r13, 42, 40, -0.5F, -1.0F, -1.2F, 1, 3, 1, 0.0F));
    this.cube_r14 = new ModelRenderer(this);
    this.cube_r14.func_78793_a(-15.0F, -22.0F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r14);
    setRotationAngle(this.cube_r14, 0.0F, 0.8727F, 0.0F);
    this.cube_r14.field_78804_l.add(new ModelBox(this.cube_r14, 0, 8, -3.5F, -1.0F, 1.5F, 6, 2, 2, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
    this.bb_main.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\christmas\ModelChristmasSnowGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */