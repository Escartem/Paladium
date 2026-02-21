package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelParrotPlush extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer fin;
  
  private final ModelRenderer fins_right;
  
  private final ModelRenderer fins_left;
  
  private final ModelRenderer tail;
  
  public ModelParrotPlush() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -5.0F, -10.0F, -6.0F, 10, 10, 10, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 28, 26, -4.0F, -9.0F, -7.0F, 3, 4, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 20, 26, 1.0F, -9.0F, -7.0F, 3, 4, 1, 0.0F));
    this.fin = new ModelRenderer(this);
    this.fin.func_78793_a(0.0F, -3.0F, -8.5F);
    this.bone.func_78792_a(this.fin);
    this.fin.field_78804_l.add(new ModelBox(this.fin, 0, 0, -1.0F, -3.0F, -0.5F, 2, 5, 3, 0.0F));
    this.fin.field_78804_l.add(new ModelBox(this.fin, 0, 10, 0.0F, -12.0F, 2.5F, 0, 5, 10, 0.0F));
    this.fins_right = new ModelRenderer(this);
    this.fins_right.func_78793_a(-5.0F, 0.0F, -6.0F);
    this.bone.func_78792_a(this.fins_right);
    setRotationAngle(this.fins_right, 0.0F, 0.7854F, 0.0F);
    this.fins_right.field_78804_l.add(new ModelBox(this.fins_right, 10, 25, -1.0F, -4.0F, -2.0F, 2, 4, 3, 0.0F));
    this.fins_left = new ModelRenderer(this);
    this.fins_left.func_78793_a(5.0F, 0.0F, -6.0F);
    this.bone.func_78792_a(this.fins_left);
    setRotationAngle(this.fins_left, 0.0F, -0.7854F, 0.0F);
    this.fins_left.field_78804_l.add(new ModelBox(this.fins_left, 0, 25, -1.0F, -4.0F, -2.0F, 2, 4, 3, 0.0F));
    this.tail = new ModelRenderer(this);
    this.tail.func_78793_a(0.0F, -3.0F, 4.0F);
    this.bone.func_78792_a(this.tail);
    setRotationAngle(this.tail, -0.3054F, 0.0F, 0.0F);
    this.tail.field_78804_l.add(new ModelBox(this.tail, 20, 20, -3.0F, 0.0F, 0.0F, 6, 2, 4, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
  }
  
  public void renderAll() {
    this.bone.func_78785_a(0.0625F);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\renders\models\blocks\ModelParrotPlush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */