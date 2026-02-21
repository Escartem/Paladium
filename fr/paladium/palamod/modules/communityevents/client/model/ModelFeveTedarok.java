package fr.paladium.palamod.modules.communityevents.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFeveTedarok extends ModelBase {
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer cube_r3;
  
  private final ModelRenderer cube_r4;
  
  private final ModelRenderer cube_r5;
  
  private final ModelRenderer cube_r6;
  
  private final ModelRenderer cube_r7;
  
  private final ModelRenderer cube_r8;
  
  public ModelFeveTedarok() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -3.0F, -2.0F, -2.0F, 6, 2, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 10, 17, -1.0F, -6.0F, -1.0F, 2, 4, 2, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, -6.5669F, 0.8977F);
    this.bb_main.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.2618F, 0.0F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 16, 0, -0.5F, -0.339F, 3.7565F, 1, 1, 3, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(0.0F, -6.5669F, 0.8977F);
    this.bb_main.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.3927F, 0.0F, 0.0F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 12, 6, -1.0F, -0.213F, 1.5319F, 2, 2, 3, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(0.0F, -6.5669F, 0.8977F);
    this.bb_main.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, 0.3054F, 0.0F, 0.0F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 0, 15, -1.0F, -3.6461F, -5.4814F, 2, 2, 3, 0.0F));
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 8, 11, -1.5F, -5.3961F, -5.9814F, 3, 2, 4, 0.0F));
    this.cube_r4 = new ModelRenderer(this);
    this.cube_r4.func_78793_a(0.0F, -6.5669F, 0.8977F);
    this.bb_main.func_78792_a(this.cube_r4);
    setRotationAngle(this.cube_r4, 1.2217F, 0.0F, 0.0F);
    this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 0, 6, -1.5F, -3.9574F, -1.5087F, 3, 6, 3, 0.0F));
    this.cube_r5 = new ModelRenderer(this);
    this.cube_r5.func_78793_a(0.0F, -6.5669F, 0.8977F);
    this.bb_main.func_78792_a(this.cube_r5);
    setRotationAngle(this.cube_r5, -1.3526F, 0.0F, 0.0F);
    this.cube_r5.field_78804_l.add(new ModelBox(this.cube_r5, 9, 6, -2.0F, 3.244F, -0.1879F, 1, 2, 1, 0.0F));
    this.cube_r5.field_78804_l.add(new ModelBox(this.cube_r5, 21, 0, 1.0F, 3.244F, -0.1879F, 1, 2, 1, 0.0F));
    this.cube_r6 = new ModelRenderer(this);
    this.cube_r6.func_78793_a(0.0F, -6.5669F, 0.8977F);
    this.bb_main.func_78792_a(this.cube_r6);
    setRotationAngle(this.cube_r6, -0.3491F, 0.0F, 0.0F);
    this.cube_r6.field_78804_l.add(new ModelBox(this.cube_r6, 0, 0, -2.0F, -0.5703F, -3.4134F, 1, 3, 1, 0.0F));
    this.cube_r6.field_78804_l.add(new ModelBox(this.cube_r6, 18, 11, 1.0F, -0.5703F, -3.4134F, 1, 3, 1, 0.0F));
    this.cube_r7 = new ModelRenderer(this);
    this.cube_r7.func_78793_a(0.0F, -6.5669F, 0.8977F);
    this.bb_main.func_78792_a(this.cube_r7);
    setRotationAngle(this.cube_r7, 0.6981F, 0.0F, 0.0F);
    this.cube_r7.field_78804_l.add(new ModelBox(this.cube_r7, 18, 17, -2.0F, 0.1632F, -0.8161F, 1, 3, 2, 0.0F));
    this.cube_r7.field_78804_l.add(new ModelBox(this.cube_r7, 19, 4, 1.0F, 0.1632F, -0.8161F, 1, 3, 2, 0.0F));
    this.cube_r8 = new ModelRenderer(this);
    this.cube_r8.func_78793_a(0.0F, -6.5669F, 0.8977F);
    this.bb_main.func_78792_a(this.cube_r8);
    setRotationAngle(this.cube_r8, 1.0036F, 0.0F, 0.0F);
    this.cube_r8.field_78804_l.add(new ModelBox(this.cube_r8, 0, 20, -2.0F, 2.8199F, -1.6181F, 1, 2, 1, 0.0F));
    this.cube_r8.field_78804_l.add(new ModelBox(this.cube_r8, 4, 20, 1.0F, 2.8199F, -1.6181F, 1, 2, 1, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\client\model\ModelFeveTedarok.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */