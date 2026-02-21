package fr.paladium.palamod.modules.luckyblock.renders.models.june;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelShark extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer jaw;
  
  private final ModelRenderer tail;
  
  private final ModelRenderer tail_r1;
  
  private final ModelRenderer tail2;
  
  private final ModelRenderer tail2_r1;
  
  private final ModelRenderer tail2_r2;
  
  private final ModelRenderer right_fin;
  
  private final ModelRenderer left_fin;
  
  private final ModelRenderer fin;
  
  private final ModelRenderer fin_r1;
  
  public ModelShark() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 22.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 28, 25, -3.0F, -4.0F, -17.0F, 6, 4, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -4.0F, -4.0F, -9.0F, 8, 6, 19, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 35, 12, -3.0F, 0.0F, -15.0F, 6, 1, 6, -0.001F));
    this.jaw = new ModelRenderer(this);
    this.jaw.func_78793_a(0.0F, 0.0F, -9.0F);
    this.bone.func_78792_a(this.jaw);
    this.jaw.field_78804_l.add(new ModelBox(this.jaw, 35, 4, -3.0F, 0.0F, -6.0F, 6, 2, 6, 0.0F));
    this.tail = new ModelRenderer(this);
    this.tail.func_78793_a(0.0F, -1.5F, 10.0F);
    this.bone.func_78792_a(this.tail);
    this.tail.field_78804_l.add(new ModelBox(this.tail, 0, 25, -3.0F, -2.5F, 0.0F, 6, 5, 8, 0.0F));
    this.tail_r1 = new ModelRenderer(this);
    this.tail_r1.func_78793_a(0.0F, -2.5F, 2.0F);
    this.tail.func_78792_a(this.tail_r1);
    setRotationAngle(this.tail_r1, -0.5236F, 0.0F, 0.0F);
    this.tail_r1.field_78804_l.add(new ModelBox(this.tail_r1, 6, 0, -0.5F, -2.0F, 0.0F, 1, 2, 2, 0.0F));
    this.tail2 = new ModelRenderer(this);
    this.tail2.func_78793_a(0.0F, -0.5F, 8.0F);
    this.tail.func_78792_a(this.tail2);
    this.tail2.field_78804_l.add(new ModelBox(this.tail2, 24, 37, -2.0F, -2.0F, 0.0F, 4, 4, 8, 0.0F));
    this.tail2_r1 = new ModelRenderer(this);
    this.tail2_r1.func_78793_a(0.0F, 2.0F, 4.0F);
    this.tail2.func_78792_a(this.tail2_r1);
    setRotationAngle(this.tail2_r1, 0.5236F, 0.0F, 0.0F);
    this.tail2_r1.field_78804_l.add(new ModelBox(this.tail2_r1, 6, 8, -0.5F, 0.0F, 0.0F, 1, 6, 4, 0.0F));
    this.tail2_r2 = new ModelRenderer(this);
    this.tail2_r2.func_78793_a(0.0F, -2.0F, 4.0F);
    this.tail2.func_78792_a(this.tail2_r2);
    setRotationAngle(this.tail2_r2, -0.5236F, 0.0F, 0.0F);
    this.tail2_r2.field_78804_l.add(new ModelBox(this.tail2_r2, 0, 0, -0.5F, -8.0F, 0.0F, 1, 8, 4, 0.0F));
    this.right_fin = new ModelRenderer(this);
    this.right_fin.func_78793_a(-4.0F, 1.0F, -4.0F);
    this.bone.func_78792_a(this.right_fin);
    setRotationAngle(this.right_fin, 0.0F, 0.0F, -0.7854F);
    this.right_fin.field_78804_l.add(new ModelBox(this.right_fin, 0, 38, -8.0F, 0.0F, -2.0F, 8, 1, 4, 0.0F));
    this.left_fin = new ModelRenderer(this);
    this.left_fin.func_78793_a(4.0F, 1.0F, -4.0F);
    this.bone.func_78792_a(this.left_fin);
    setRotationAngle(this.left_fin, 0.0F, 0.0F, 0.7854F);
    this.left_fin.field_78804_l.add(new ModelBox(this.left_fin, 0, 38, 0.0F, 0.0F, -2.0F, 8, 1, 4, 0.0F));
    this.fin = new ModelRenderer(this);
    this.fin.func_78793_a(0.0F, -4.0F, -2.0F);
    this.bone.func_78792_a(this.fin);
    this.fin_r1 = new ModelRenderer(this);
    this.fin_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.fin.func_78792_a(this.fin_r1);
    setRotationAngle(this.fin_r1, -0.5236F, 0.0F, 0.0F);
    this.fin_r1.field_78804_l.add(new ModelBox(this.fin_r1, 0, 44, -0.5F, -6.0F, 0.0F, 1, 6, 4, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    if (f4 == 0.0F) {
      this.fin_r1.field_82908_p = 0.0F;
      this.bone.field_78807_k = false;
    } else {
      this.bone.field_78807_k = true;
      this.fin_r1.field_82908_p = 1.2F;
      this.fin_r1.func_78785_a(f5);
    } 
    this.bone.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\june\ModelShark.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */