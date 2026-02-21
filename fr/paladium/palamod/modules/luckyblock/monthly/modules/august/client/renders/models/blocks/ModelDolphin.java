package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDolphin extends ModelBase {
  private ModelRenderer bone;
  
  private ModelRenderer tail_fin;
  
  private ModelRenderer back_fin;
  
  private ModelRenderer left_fin;
  
  private ModelRenderer left_fin_r1;
  
  private ModelRenderer right_fin;
  
  private ModelRenderer right_fin_r1;
  
  private ModelRenderer tail;
  
  private ModelRenderer body;
  
  private ModelRenderer head;
  
  public ModelDolphin() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 1.0F);
    this.tail_fin = new ModelRenderer(this);
    this.tail_fin.func_78793_a(8.0F, -2.5F, 11.0F);
    this.bone.func_78792_a(this.tail_fin);
    this.tail_fin.field_78804_l.add(new ModelBox(this.tail_fin, 0, 18, -12.0F, -1.5F, -5.0F, 8, 1, 4, 0.0F));
    this.back_fin = new ModelRenderer(this);
    this.back_fin.func_78793_a(8.0F, -11.0F, -14.0F);
    this.bone.func_78792_a(this.back_fin);
    this.back_fin.field_78804_l.add(new ModelBox(this.back_fin, 0, 23, -8.5F, 2.0F, 8.0F, 1, 2, 5, 0.0F));
    this.left_fin = new ModelRenderer(this);
    this.left_fin.func_78793_a(12.5F, 0.0F, -11.0F);
    this.bone.func_78792_a(this.left_fin);
    this.left_fin_r1 = new ModelRenderer(this);
    this.left_fin_r1.func_78793_a(-8.0F, -1.5F, 11.0F);
    this.left_fin.func_78792_a(this.left_fin_r1);
    setRotationAngle(this.left_fin_r1, 0.0F, 0.0F, 0.7854F);
    this.left_fin_r1.field_78804_l.add(new ModelBox(this.left_fin_r1, 0, 0, -1.5F, -0.5F, -2.0F, 3, 1, 4, 0.0F));
    this.right_fin = new ModelRenderer(this);
    this.right_fin.func_78793_a(3.5F, 0.0F, -11.0F);
    this.bone.func_78792_a(this.right_fin);
    this.right_fin_r1 = new ModelRenderer(this);
    this.right_fin_r1.func_78793_a(-8.0F, -1.5F, 11.0F);
    this.right_fin.func_78792_a(this.right_fin_r1);
    setRotationAngle(this.right_fin_r1, 0.0F, 0.0F, 0.7854F);
    this.right_fin_r1.field_78804_l.add(new ModelBox(this.right_fin_r1, 0, 0, -0.5F, -1.5F, -2.0F, 1, 3, 4, 0.0F));
    this.tail = new ModelRenderer(this);
    this.tail.func_78793_a(8.0F, -2.5F, 2.0F);
    this.bone.func_78792_a(this.tail);
    this.tail.field_78804_l.add(new ModelBox(this.tail, 20, 19, -10.0F, -2.5F, 1.0F, 4, 5, 4, 0.0F));
    this.body = new ModelRenderer(this);
    this.body.func_78793_a(8.0F, 0.0F, -12.0F);
    this.bone.func_78792_a(this.body);
    this.body.field_78804_l.add(new ModelBox(this.body, 12, 24, -9.0F, -2.0F, 0.0F, 2, 2, 4, 0.0F));
    this.head = new ModelRenderer(this);
    this.head.func_78793_a(8.0F, -4.0F, -15.0F);
    this.bone.func_78792_a(this.head);
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 0, -12.0F, -3.0F, 7.0F, 8, 7, 11, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 36, 13, -12.5F, -3.0F, 6.5F, 9, 7, 5, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll() {
    this.bone.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\client\renders\models\blocks\ModelDolphin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */