package fr.paladium.palamod.modules.luckyblock.blocks.models.easter;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEasterGift extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer bone2;
  
  public ModelEasterGift() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 12, 38, -7.0F, -11.0F, -1.0F, 1, 4, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 30, 15, -6.0F, -11.0F, -1.0F, 12, 1, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 9, 32, -1.0F, -11.0F, 6.0F, 2, 4, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 32, -1.0F, -11.0F, 1.0F, 2, 1, 5, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 36, 6, 6.0F, -11.0F, -1.0F, 1, 4, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 30, 20, -1.0F, -11.0F, -7.0F, 2, 4, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 14, 32, -1.0F, -11.0F, -6.0F, 2, 1, 5, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -6.0F, -10.0F, -6.0F, 12, 3, 12, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(-1.0F, -0.5F, 0.0F);
    this.bone.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 0.0F, 0.4363F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 36, 0, -4.0F, -13.0F, -1.0F, 2, 4, 2, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(-1.0F, -0.5F, 0.0F);
    this.bone.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, 0.0F, -0.48F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 34, 32, 4.0F, -13.0F, -1.0F, 2, 4, 2, 0.0F));
    this.bone2 = new ModelRenderer(this);
    this.bone2.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 0, 15, 5.0F, -7.0F, -1.0F, 1, 7, 2, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 0, 15, -5.0F, -7.0F, -5.0F, 10, 7, 10, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 0, 0, -1.0F, 0.1F, -6.0F, 2, 0, 5, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 28, 18, -6.0F, 0.1F, -1.0F, 12, 0, 2, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 0, 5, -1.0F, 0.1F, 1.0F, 2, 0, 5, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 0, 38, -1.0F, -7.0F, -6.0F, 2, 7, 1, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 6, 38, -1.0F, -7.0F, 5.0F, 2, 7, 1, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 28, 32, -6.0F, -7.0F, -1.0F, 1, 7, 2, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
    this.bone2.func_78785_a(f5);
  }
  
  public void renderAll() {
    this.bone.func_78785_a(0.0625F);
    this.bone2.func_78785_a(0.0625F);
  }
  
  public void animate(long animationTime) {
    float y = 24.0F - 0.1F * (float)animationTime;
    float x = 0.0F;
    if (y < 23.0F)
      y = 23.0F; 
    if (animationTime > 10L) {
      x = 0.2F * (float)(animationTime - 10L);
      if (x > 5.0F)
        x = 5.0F; 
    } 
    this.bone.func_78793_a(x, y, x);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\easter\ModelEasterGift.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */