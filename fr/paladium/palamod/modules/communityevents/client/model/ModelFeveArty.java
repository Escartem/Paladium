package fr.paladium.palamod.modules.communityevents.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFeveArty extends ModelBase {
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer cube_r3;
  
  private final ModelRenderer cube_r4;
  
  private final ModelRenderer cube_r5;
  
  private final ModelRenderer cube_r6;
  
  private final ModelRenderer cube_r7;
  
  private final ModelRenderer cube_r8;
  
  public ModelFeveArty() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 8, -3.0F, -2.0F, -2.5F, 6, 2, 4, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, 0.0F, 1.0F);
    this.bb_main.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.9631F, -0.2602F, -0.3543F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 19, 18, 1.25F, -1.0F, 3.75F, 1, 1, 3, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(0.0F, 0.0F, 1.0F);
    this.bb_main.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.6545F, 0.0F, 0.0F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 0, 19, -0.5F, -2.0F, 0.0F, 1, 1, 4, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(-1.25F, 0.0F, 1.0F);
    this.bb_main.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, 0.0F, 0.0F, -0.2182F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 12, 14, -3.0F, -8.75F, -2.0F, 4, 2, 2, 0.0F));
    this.cube_r4 = new ModelRenderer(this);
    this.cube_r4.func_78793_a(-1.25F, 0.0F, 1.0F);
    this.bb_main.func_78792_a(this.cube_r4);
    setRotationAngle(this.cube_r4, 0.0F, 0.0F, 0.7418F);
    this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 16, 0, -1.5F, -9.0F, -2.0F, 4, 2, 2, 0.0F));
    this.cube_r5 = new ModelRenderer(this);
    this.cube_r5.func_78793_a(-1.25F, 0.0F, 1.0F);
    this.bb_main.func_78792_a(this.cube_r5);
    setRotationAngle(this.cube_r5, 0.0F, 0.0F, 0.1745F);
    this.cube_r5.field_78804_l.add(new ModelBox(this.cube_r5, 0, 0, -2.5F, -8.75F, -3.0F, 6, 4, 4, 0.0F));
    this.cube_r6 = new ModelRenderer(this);
    this.cube_r6.func_78793_a(0.0F, 0.0F, 1.0F);
    this.bb_main.func_78792_a(this.cube_r6);
    setRotationAngle(this.cube_r6, 0.0F, 0.0873F, 0.6109F);
    this.cube_r6.field_78804_l.add(new ModelBox(this.cube_r6, 16, 8, -7.25F, -3.5F, -2.25F, 4, 2, 1, 0.0F));
    this.cube_r7 = new ModelRenderer(this);
    this.cube_r7.func_78793_a(0.0F, 0.0F, 1.0F);
    this.bb_main.func_78792_a(this.cube_r7);
    setRotationAngle(this.cube_r7, 0.0F, 0.1309F, 0.4363F);
    this.cube_r7.field_78804_l.add(new ModelBox(this.cube_r7, 12, 18, 0.0F, -4.75F, -1.75F, 4, 2, 1, 0.0F));
    this.cube_r8 = new ModelRenderer(this);
    this.cube_r8.func_78793_a(0.0F, 0.0F, 1.0F);
    this.bb_main.func_78792_a(this.cube_r8);
    setRotationAngle(this.cube_r8, -0.0436F, 0.0F, 0.0F);
    this.cube_r8.field_78804_l.add(new ModelBox(this.cube_r8, 0, 14, -2.0F, -5.0F, -2.5F, 4, 4, 2, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\client\model\ModelFeveArty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */