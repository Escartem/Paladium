package fr.paladium.palamod.modules.luckyblock.renders.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMissileMeteo extends ModelBase {
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer cube_r3;
  
  private final ModelRenderer cube_r4;
  
  private final ModelRenderer cube_r5;
  
  private final ModelRenderer cube_r6;
  
  private final ModelRenderer cube_r7;
  
  private final ModelRenderer cube_r8;
  
  private final ModelRenderer cube_r9;
  
  private final ModelRenderer cube_r10;
  
  private final ModelRenderer cube_r11;
  
  private final ModelRenderer cube_r12;
  
  private final ModelRenderer cube_r13;
  
  private final ModelRenderer cube_r14;
  
  private final ModelRenderer cube_r15;
  
  public ModelMissileMeteo() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -6.0F, -25.0F, -6.0F, 12, 18, 12, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 30, -4.0F, -58.0F, -4.0F, 8, 33, 8, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 32, 43, -2.0F, -11.0F, -8.0F, 4, 11, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 32, 30, -5.0F, -4.0F, -5.0F, 9, 3, 10, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 24, 30, -2.0F, -7.0F, -2.0F, 4, 3, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 52, 0, -2.0F, -64.0F, -2.0F, 4, 6, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 36, 0, -2.0F, -76.0F, -2.0F, 4, 2, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 48, 10, -2.0F, -53.0F, -6.0F, 4, 17, 2, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(-6.6F, -8.4318F, -0.0111F);
    this.bb_main.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 1.5708F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 32, 43, -2.0F, -2.5682F, -1.3889F, 4, 11, 2, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(6.6F, -8.4318F, -0.0111F);
    this.bb_main.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, -1.5708F, 0.0F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 32, 43, -2.0F, -2.5682F, -1.3889F, 4, 11, 2, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(0.0F, -8.6318F, 6.3889F);
    this.bb_main.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, -3.1416F, 0.0F, 3.1416F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 32, 43, -2.0F, -2.5682F, -1.3889F, 4, 11, 2, 0.0F));
    this.cube_r4 = new ModelRenderer(this);
    this.cube_r4.func_78793_a(-4.6F, -50.4318F, -0.0111F);
    this.bb_main.func_78792_a(this.cube_r4);
    setRotationAngle(this.cube_r4, 0.0F, 1.5708F, 0.0F);
    this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 48, 10, -2.0F, -2.5682F, -1.3889F, 4, 17, 2, 0.0F));
    this.cube_r5 = new ModelRenderer(this);
    this.cube_r5.func_78793_a(-4.6F, -50.4318F, -0.0111F);
    this.bb_main.func_78792_a(this.cube_r5);
    setRotationAngle(this.cube_r5, 0.0F, 1.5708F, 0.7854F);
    this.cube_r5.field_78804_l.add(new ModelBox(this.cube_r5, 32, 56, -2.0F, -3.8481F, -2.7981F, 4, 3, 2, 0.0F));
    this.cube_r6 = new ModelRenderer(this);
    this.cube_r6.func_78793_a(0.0F, -50.6318F, 4.5889F);
    this.bb_main.func_78792_a(this.cube_r6);
    setRotationAngle(this.cube_r6, -3.1416F, 0.0F, 3.1416F);
    this.cube_r6.field_78804_l.add(new ModelBox(this.cube_r6, 48, 10, -2.0F, -2.5682F, -1.3889F, 4, 17, 2, 0.0F));
    this.cube_r7 = new ModelRenderer(this);
    this.cube_r7.func_78793_a(0.0F, -50.6318F, 4.5889F);
    this.bb_main.func_78792_a(this.cube_r7);
    setRotationAngle(this.cube_r7, 2.3562F, 0.0F, 3.1416F);
    this.cube_r7.field_78804_l.add(new ModelBox(this.cube_r7, 32, 56, -2.0F, -3.8481F, -2.7981F, 4, 3, 2, 0.0F));
    this.cube_r8 = new ModelRenderer(this);
    this.cube_r8.func_78793_a(4.6F, -50.4318F, -0.0111F);
    this.bb_main.func_78792_a(this.cube_r8);
    setRotationAngle(this.cube_r8, 0.0F, -1.5708F, -0.7854F);
    this.cube_r8.field_78804_l.add(new ModelBox(this.cube_r8, 32, 56, -2.0F, -3.8481F, -2.7981F, 4, 3, 2, 0.0F));
    this.cube_r9 = new ModelRenderer(this);
    this.cube_r9.func_78793_a(4.6F, -50.4318F, -0.0111F);
    this.bb_main.func_78792_a(this.cube_r9);
    setRotationAngle(this.cube_r9, 0.0F, -1.5708F, 0.0F);
    this.cube_r9.field_78804_l.add(new ModelBox(this.cube_r9, 48, 10, -2.0F, -2.5682F, -1.3889F, 4, 17, 2, 0.0F));
    this.cube_r10 = new ModelRenderer(this);
    this.cube_r10.func_78793_a(-1.0F, -54.0F, -5.0F);
    this.bb_main.func_78792_a(this.cube_r10);
    setRotationAngle(this.cube_r10, -0.7854F, 0.0F, 0.0F);
    this.cube_r10.field_78804_l.add(new ModelBox(this.cube_r10, 32, 56, -1.0F, -1.6F, 0.0F, 4, 3, 2, 0.0F));
    this.cube_r11 = new ModelRenderer(this);
    this.cube_r11.func_78793_a(0.0F, -65.0F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r11);
    setRotationAngle(this.cube_r11, 0.0F, 0.7854F, 0.0F);
    this.cube_r11.field_78804_l.add(new ModelBox(this.cube_r11, 0, 0, -1.0F, -9.0F, -1.0F, 2, 10, 2, 0.0F));
    this.cube_r12 = new ModelRenderer(this);
    this.cube_r12.func_78793_a(6.6F, -8.4318F, -0.0111F);
    this.bb_main.func_78792_a(this.cube_r12);
    setRotationAngle(this.cube_r12, 0.0F, -1.5708F, -0.7854F);
    this.cube_r12.field_78804_l.add(new ModelBox(this.cube_r12, 32, 56, -2.0F, -3.8481F, -2.7981F, 4, 3, 2, 0.0F));
    this.cube_r13 = new ModelRenderer(this);
    this.cube_r13.func_78793_a(-6.6F, -8.4318F, -0.0111F);
    this.bb_main.func_78792_a(this.cube_r13);
    setRotationAngle(this.cube_r13, 0.0F, 1.5708F, 0.7854F);
    this.cube_r13.field_78804_l.add(new ModelBox(this.cube_r13, 32, 56, -2.0F, -3.8481F, -2.7981F, 4, 3, 2, 0.0F));
    this.cube_r14 = new ModelRenderer(this);
    this.cube_r14.func_78793_a(0.0F, -8.6318F, 6.3889F);
    this.bb_main.func_78792_a(this.cube_r14);
    setRotationAngle(this.cube_r14, 2.3562F, 0.0F, 3.1416F);
    this.cube_r14.field_78804_l.add(new ModelBox(this.cube_r14, 32, 56, -2.0F, -3.8481F, -2.7981F, 4, 3, 2, 0.0F));
    this.cube_r15 = new ModelRenderer(this);
    this.cube_r15.func_78793_a(-1.0F, -12.0F, -7.0F);
    this.bb_main.func_78792_a(this.cube_r15);
    setRotationAngle(this.cube_r15, -0.7854F, 0.0F, 0.0F);
    this.cube_r15.field_78804_l.add(new ModelBox(this.cube_r15, 32, 56, -1.0F, -1.6F, 0.0F, 4, 3, 2, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bb_main.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\ModelMissileMeteo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */