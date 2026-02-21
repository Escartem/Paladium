package fr.paladium.palamod.modules.luckyblock.renders.models.easter;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGiantFish extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer fin_0;
  
  private final ModelRenderer fin_1;
  
  private final ModelRenderer fin_2;
  
  private final ModelRenderer fin_3;
  
  private final ModelRenderer back;
  
  private final ModelRenderer tail_0;
  
  private final ModelRenderer tail_1;
  
  private final ModelRenderer sail_0;
  
  private final ModelRenderer cube_r8;
  
  private final ModelRenderer sail_1;
  
  private final ModelRenderer cube_r10;
  
  private final ModelRenderer sail_2;
  
  private final ModelRenderer sail_3;
  
  private final ModelRenderer sail_4;
  
  private final ModelRenderer cube_r9;
  
  private final ModelRenderer sail_5;
  
  private final ModelRenderer sail_6;
  
  private final ModelRenderer sail_7;
  
  private final ModelRenderer cube_r11;
  
  private final ModelRenderer jaw;
  
  public ModelGiantFish() {
    this.field_78090_t = 1024;
    this.field_78089_u = 1024;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, -61.0F, -35.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 357, -39.0F, -356.0F, -24.0F, 78, 243, 124, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -63.0F, -121.0F, -81.0F, 126, 206, 151, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 724, -63.0F, -99.0F, 70.0F, 126, 184, 27, -0.05F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 460, 674, 63.0F, -101.0F, 3.0F, 24, 48, 48, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, 63.0F, -32.0F, 27.0F, 9, 22, 22, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, 63.0F, -86.0F, -29.0F, 9, 22, 22, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, 63.0F, -46.0F, -31.0F, 9, 22, 22, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 40, 0, 63.0F, -105.0F, -34.0F, 4, 8, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 40, 0, 63.0F, -17.0F, 4.0F, 4, 8, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 40, 0, 63.0F, 63.0F, -53.0F, 4, 8, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 40, 0, 63.0F, -5.0F, -16.0F, 4, 8, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 40, 0, 63.0F, 5.0F, 32.0F, 4, 8, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 40, 0, 63.0F, -58.0F, -47.0F, 4, 8, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 40, 0, -67.0F, -105.0F, -34.0F, 4, 8, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 40, 0, -67.0F, -17.0F, 4.0F, 4, 8, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 40, 0, -67.0F, -5.0F, -16.0F, 4, 8, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 40, 0, -67.0F, 5.0F, 32.0F, 4, 8, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 40, 0, -67.0F, 63.0F, -53.0F, 4, 8, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 40, 0, -67.0F, -58.0F, -47.0F, 4, 8, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -72.0F, -86.0F, -29.0F, 9, 22, 22, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 460, 674, -87.0F, -101.0F, 3.0F, 24, 48, 48, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -72.0F, -46.0F, -31.0F, 9, 22, 22, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -72.0F, -32.0F, 27.0F, 9, 22, 22, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 642, 558, -21.0F, -454.0F, -38.0F, 42, 98, 116, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 783, 151, -15.0F, -570.0F, -14.0F, 30, 116, 77, 0.0F));
    this.fin_0 = new ModelRenderer(this);
    this.fin_0.func_78793_a(0.0F, -113.0F, -66.0F);
    this.bone.func_78792_a(this.fin_0);
    setRotationAngle(this.fin_0, -0.5236F, 0.0F, 0.0F);
    this.fin_0.field_78804_l.add(new ModelBox(this.fin_0, 554, 0, -2.0F, -69.0F, -138.0F, 4, 79, 149, 0.0F));
    this.fin_1 = new ModelRenderer(this);
    this.fin_1.func_78793_a(0.0F, -203.0F, -66.0F);
    this.bone.func_78792_a(this.fin_1);
    setRotationAngle(this.fin_1, -0.6981F, 0.0F, 0.0F);
    this.fin_1.field_78804_l.add(new ModelBox(this.fin_1, 306, 674, -1.0F, -50.0F, -136.0F, 2, 60, 150, 0.0F));
    this.fin_2 = new ModelRenderer(this);
    this.fin_2.func_78793_a(0.0F, -266.0F, -66.0F);
    this.bone.func_78792_a(this.fin_2);
    setRotationAngle(this.fin_2, -0.8727F, 0.0F, 0.0F);
    this.fin_2.field_78804_l.add(new ModelBox(this.fin_2, 554, 143, 0.0F, -49.0F, -85.0F, 0, 50, 85, 0.0F));
    this.fin_3 = new ModelRenderer(this);
    this.fin_3.func_78793_a(-2.0F, -16.0F, -61.0F);
    this.bone.func_78792_a(this.fin_3);
    setRotationAngle(this.fin_3, 0.0F, -1.2217F, 1.5708F);
    this.fin_3.field_78804_l.add(new ModelBox(this.fin_3, 711, 7, -70.0F, -1.0F, -33.0F, 80, 2, 73, 0.0F));
    this.back = new ModelRenderer(this);
    this.back.func_78793_a(0.0F, -234.5F, -45.5F);
    this.bone.func_78792_a(this.back);
    setRotationAngle(this.back, 0.0F, 0.0F, 1.5708F);
    this.back.field_78804_l.add(new ModelBox(this.back, 280, 357, -121.5F, -31.0F, -21.5F, 243, 62, 43, 0.0F));
    this.tail_0 = new ModelRenderer(this);
    this.tail_0.func_78793_a(0.0F, -534.0F, 63.0F);
    this.bone.func_78792_a(this.tail_0);
    setRotationAngle(this.tail_0, 0.0F, 0.48F, 1.5708F);
    this.tail_0.field_78804_l.add(new ModelBox(this.tail_0, 0, 936, -75.0F, -2.0005F, -76.9546F, 77, 4, 77, -0.1F));
    this.tail_1 = new ModelRenderer(this);
    this.tail_1.func_78793_a(0.0F, -515.0F, -14.0F);
    this.bone.func_78792_a(this.tail_1);
    setRotationAngle(this.tail_1, 0.7854F, 0.0F, 0.0F);
    this.tail_1.field_78804_l.add(new ModelBox(this.tail_1, 842, 385, -2.0F, -142.0F, 0.0F, 4, 142, 77, 0.0F));
    this.sail_0 = new ModelRenderer(this);
    this.sail_0.func_78793_a(-38.0F, -141.75F, 74.0F);
    this.bone.func_78792_a(this.sail_0);
    setRotationAngle(this.sail_0, -0.4363F, 0.0F, -0.3927F);
    this.sail_0.field_78804_l.add(new ModelBox(this.sail_0, 0, 356, -1.0F, -68.25F, -16.0F, 12, 68, 32, 0.0F));
    this.cube_r8 = new ModelRenderer(this);
    this.cube_r8.func_78793_a(6.5F, -40.25F, 0.0F);
    this.sail_0.func_78792_a(this.cube_r8);
    setRotationAngle(this.cube_r8, 0.0F, 0.0F, 1.5708F);
    this.cube_r8.field_78804_l.add(new ModelBox(this.cube_r8, 391, 884, -66.5F, 0.0F, -39.0F, 67, 4, 78, 0.0F));
    this.sail_1 = new ModelRenderer(this);
    this.sail_1.func_78793_a(-38.0F, -278.75F, 74.0F);
    this.bone.func_78792_a(this.sail_1);
    setRotationAngle(this.sail_1, -0.0873F, 0.0F, -0.3054F);
    this.sail_1.field_78804_l.add(new ModelBox(this.sail_1, 0, 357, -1.0F, -68.25F, -16.0F, 12, 68, 32, 0.0F));
    this.cube_r10 = new ModelRenderer(this);
    this.cube_r10.func_78793_a(6.5F, -40.25F, 0.0F);
    this.sail_1.func_78792_a(this.cube_r10);
    setRotationAngle(this.cube_r10, 0.0F, 0.0F, 1.5708F);
    this.cube_r10.field_78804_l.add(new ModelBox(this.cube_r10, 391, 884, -66.5F, -1.0F, -39.0F, 67, 4, 78, 0.0F));
    this.sail_2 = new ModelRenderer(this);
    this.sail_2.func_78793_a(-38.0F, -162.75F, -1.0F);
    this.bone.func_78792_a(this.sail_2);
    setRotationAngle(this.sail_2, -0.1309F, 0.0F, -0.3927F);
    this.sail_2.field_78804_l.add(new ModelBox(this.sail_2, 382, 702, 2.0F, -68.25F, -6.0F, 9, 68, 22, 0.0F));
    this.sail_2.field_78804_l.add(new ModelBox(this.sail_2, 0, 0, 5.0F, -91.25F, -29.0F, 2, 52, 66, 0.0F));
    this.sail_3 = new ModelRenderer(this);
    this.sail_3.func_78793_a(-38.0F, -338.75F, -1.0F);
    this.bone.func_78792_a(this.sail_3);
    setRotationAngle(this.sail_3, 0.0F, 0.0F, -0.3927F);
    this.sail_3.field_78804_l.add(new ModelBox(this.sail_3, 382, 702, 2.0F, -68.25F, -6.0F, 9, 68, 22, 0.0F));
    this.sail_3.field_78804_l.add(new ModelBox(this.sail_3, 0, 0, 5.0F, -91.25F, -29.0F, 2, 52, 66, 0.0F));
    this.sail_4 = new ModelRenderer(this);
    this.sail_4.func_78793_a(38.0F, -141.75F, 74.0F);
    this.bone.func_78792_a(this.sail_4);
    setRotationAngle(this.sail_4, -0.4363F, 0.0F, 0.3927F);
    this.sail_4.field_78804_l.add(new ModelBox(this.sail_4, 0, 357, -11.0F, -68.25F, -16.0F, 12, 68, 32, 0.0F));
    this.cube_r9 = new ModelRenderer(this);
    this.cube_r9.func_78793_a(-2.5F, -40.25F, 0.0F);
    this.sail_4.func_78792_a(this.cube_r9);
    setRotationAngle(this.cube_r9, 0.0F, 0.0F, 1.5708F);
    this.cube_r9.field_78804_l.add(new ModelBox(this.cube_r9, 391, 884, -67.5F, 0.0F, -39.0F, 67, 4, 78, 0.0F));
    this.sail_5 = new ModelRenderer(this);
    this.sail_5.func_78793_a(38.0F, -162.75F, -1.0F);
    this.bone.func_78792_a(this.sail_5);
    setRotationAngle(this.sail_5, -0.1309F, 0.0F, 0.3927F);
    this.sail_5.field_78804_l.add(new ModelBox(this.sail_5, 382, 702, -11.0F, -68.25F, -6.0F, 9, 68, 22, 0.0F));
    this.sail_5.field_78804_l.add(new ModelBox(this.sail_5, 0, 0, -7.0F, -91.25F, -29.0F, 2, 52, 66, 0.0F));
    this.sail_6 = new ModelRenderer(this);
    this.sail_6.func_78793_a(38.0F, -338.75F, -1.0F);
    this.bone.func_78792_a(this.sail_6);
    setRotationAngle(this.sail_6, 0.0F, 0.0F, 0.3927F);
    this.sail_6.field_78804_l.add(new ModelBox(this.sail_6, 382, 702, -11.0F, -68.25F, -6.0F, 9, 68, 22, 0.0F));
    this.sail_6.field_78804_l.add(new ModelBox(this.sail_6, 0, 0, -7.0F, -91.25F, -29.0F, 2, 52, 66, 0.0F));
    this.sail_7 = new ModelRenderer(this);
    this.sail_7.func_78793_a(38.0F, -278.75F, 74.0F);
    this.bone.func_78792_a(this.sail_7);
    setRotationAngle(this.sail_7, -0.0873F, 0.0F, 0.3054F);
    this.sail_7.field_78804_l.add(new ModelBox(this.sail_7, 0, 357, -11.0F, -68.25F, -16.0F, 12, 68, 32, 0.0F));
    this.cube_r11 = new ModelRenderer(this);
    this.cube_r11.func_78793_a(-3.5F, -40.25F, 0.0F);
    this.sail_7.func_78792_a(this.cube_r11);
    setRotationAngle(this.cube_r11, 0.0F, 0.0F, 1.5708F);
    this.cube_r11.field_78804_l.add(new ModelBox(this.cube_r11, 391, 884, -66.5F, 0.0F, -39.0F, 67, 4, 78, 0.0F));
    this.jaw = new ModelRenderer(this);
    this.jaw.func_78793_a(0.0F, -121.0F, 69.0F);
    this.bone.func_78792_a(this.jaw);
    setRotationAngle(this.jaw, 0.5672F, 0.0F, 0.0F);
    this.jaw.field_78804_l.add(new ModelBox(this.jaw, 404, 462, -63.0F, 0.0F, 0.0F, 126, 161, 51, 0.0F));
    this.jaw.field_78804_l.add(new ModelBox(this.jaw, 610, 772, -63.0F, 0.0F, -28.0F, 126, 161, 28, -0.1F));
    this.jaw.field_78804_l.add(new ModelBox(this.jaw, 403, 0, -49.0F, 161.0F, 0.0F, 98, 30, 51, 0.0F));
    this.jaw.field_78804_l.add(new ModelBox(this.jaw, 403, 81, -49.0F, 161.0F, -28.0F, 98, 30, 28, -0.1F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\easter\ModelGiantFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */