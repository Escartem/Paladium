package fr.paladium.palamod.modules.communityevents.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFeveRavirok extends ModelBase {
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
  
  public ModelFeveRavirok() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -3.0F, -2.0F, -2.0F, 6, 2, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 14, 18, -1.0F, -6.0F, -1.0F, 2, 4, 2, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(-0.0093F, -3.6591F, -3.3828F);
    this.bb_main.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 1.0144F, -0.1166F, -0.1848F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 10, 6, 0.6823F, 0.0F, -1.776F, 1, 0, 3, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(-0.0093F, -3.6591F, -3.3828F);
    this.bb_main.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 1.0036F, 0.0F, 0.0F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 13, 0, -0.4907F, 0.0F, -1.567F, 1, 0, 3, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(-0.0093F, -3.6591F, -3.3828F);
    this.bb_main.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, 1.0144F, 0.1166F, 0.1848F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 15, 0, -1.6997F, 0.0F, -1.6974F, 1, 0, 3, 0.0F));
    this.cube_r4 = new ModelRenderer(this);
    this.cube_r4.func_78793_a(-1.5F, -5.0F, 4.75F);
    this.bb_main.func_78792_a(this.cube_r4);
    setRotationAngle(this.cube_r4, -0.2182F, 0.0F, 0.0F);
    this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 7, 24, 2.5F, 1.0F, -4.25F, 1, 1, 3, 0.0F));
    this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 24, 11, -0.5F, 1.0F, -4.25F, 1, 1, 3, 0.0F));
    this.cube_r5 = new ModelRenderer(this);
    this.cube_r5.func_78793_a(7.343F, -9.1264F, -2.5812F);
    this.bb_main.func_78792_a(this.cube_r5);
    setRotationAngle(this.cube_r5, -0.619F, 0.0962F, -0.5455F);
    this.cube_r5.field_78804_l.add(new ModelBox(this.cube_r5, 16, 6, -3.0F, -3.25F, -0.25F, 6, 4, 1, 0.0F));
    this.cube_r6 = new ModelRenderer(this);
    this.cube_r6.func_78793_a(5.0F, -7.5F, 1.25F);
    this.bb_main.func_78792_a(this.cube_r6);
    setRotationAngle(this.cube_r6, -0.7423F, 0.4407F, -0.1966F);
    this.cube_r6.field_78804_l.add(new ModelBox(this.cube_r6, 20, 0, -2.0F, -1.25F, -3.0F, 4, 4, 1, 0.0F));
    this.cube_r7 = new ModelRenderer(this);
    this.cube_r7.func_78793_a(-7.343F, -9.1264F, -2.5812F);
    this.bb_main.func_78792_a(this.cube_r7);
    setRotationAngle(this.cube_r7, -0.619F, -0.0962F, 0.5455F);
    this.cube_r7.field_78804_l.add(new ModelBox(this.cube_r7, 0, 17, -3.0F, -3.25F, -0.25F, 6, 4, 1, 0.0F));
    this.cube_r8 = new ModelRenderer(this);
    this.cube_r8.func_78793_a(-5.0F, -7.5F, 1.25F);
    this.bb_main.func_78792_a(this.cube_r8);
    setRotationAngle(this.cube_r8, -0.7423F, -0.4407F, 0.1966F);
    this.cube_r8.field_78804_l.add(new ModelBox(this.cube_r8, 0, 22, -2.0F, -1.25F, -3.0F, 4, 4, 1, 0.0F));
    this.cube_r9 = new ModelRenderer(this);
    this.cube_r9.func_78793_a(0.75F, 0.5F, 3.5F);
    this.bb_main.func_78792_a(this.cube_r9);
    setRotationAngle(this.cube_r9, -0.841F, -0.1468F, 0.162F);
    this.cube_r9.field_78804_l.add(new ModelBox(this.cube_r9, 21, 11, -2.75F, -8.5F, -13.75F, 1, 0, 3, 0.0F));
    this.cube_r10 = new ModelRenderer(this);
    this.cube_r10.func_78793_a(0.75F, 0.5F, 3.5F);
    this.bb_main.func_78792_a(this.cube_r10);
    setRotationAngle(this.cube_r10, -0.841F, 0.1468F, -0.162F);
    this.cube_r10.field_78804_l.add(new ModelBox(this.cube_r10, 9, 22, 0.25F, -8.5F, -14.0F, 1, 0, 3, 0.0F));
    this.cube_r11 = new ModelRenderer(this);
    this.cube_r11.func_78793_a(0.75F, 0.5F, 3.5F);
    this.bb_main.func_78792_a(this.cube_r11);
    setRotationAngle(this.cube_r11, -0.829F, 0.0F, 0.0F);
    this.cube_r11.field_78804_l.add(new ModelBox(this.cube_r11, 24, 15, -1.25F, -8.5F, -14.0F, 1, 0, 3, 0.0F));
    this.cube_r12 = new ModelRenderer(this);
    this.cube_r12.func_78793_a(0.0F, -10.4557F, 1.0955F);
    this.bb_main.func_78792_a(this.cube_r12);
    setRotationAngle(this.cube_r12, -0.7854F, 0.0F, 0.0F);
    this.cube_r12.field_78804_l.add(new ModelBox(this.cube_r12, 22, 18, -1.5F, -1.0F, -0.5F, 3, 2, 2, 0.0F));
    this.cube_r13 = new ModelRenderer(this);
    this.cube_r13.func_78793_a(0.75F, 0.5F, 3.5F);
    this.bb_main.func_78792_a(this.cube_r13);
    setRotationAngle(this.cube_r13, -0.3927F, 0.0F, 0.0F);
    this.cube_r13.field_78804_l.add(new ModelBox(this.cube_r13, 22, 22, -2.25F, -11.75F, -4.0F, 3, 2, 2, 0.0F));
    this.cube_r13.field_78804_l.add(new ModelBox(this.cube_r13, 13, 12, -2.75F, -12.5F, -7.0F, 4, 3, 3, 0.0F));
    this.cube_r14 = new ModelRenderer(this);
    this.cube_r14.func_78793_a(0.0F, -6.5505F, 1.9612F);
    this.bb_main.func_78792_a(this.cube_r14);
    setRotationAngle(this.cube_r14, -0.4363F, 0.0F, 0.0F);
    this.cube_r14.field_78804_l.add(new ModelBox(this.cube_r14, 0, 6, -2.5F, -2.5F, -3.5F, 5, 6, 3, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bb_main.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll() {
    this.bb_main.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\client\model\ModelFeveRavirok.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */