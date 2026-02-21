package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCaptainFlint extends ModelBase {
  private final ModelRenderer body;
  
  private final ModelRenderer wing0;
  
  private final ModelRenderer wing1;
  
  private final ModelRenderer head;
  
  private final ModelRenderer tail;
  
  private final ModelRenderer leg0;
  
  private final ModelRenderer leg1;
  
  public ModelCaptainFlint() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.body = new ModelRenderer(this);
    this.body.func_78793_a(0.0F, 16.5F, -3.0F);
    setRotationAngle(this.body, 0.4363F, 0.0F, 0.0F);
    this.body.field_78804_l.add(new ModelBox(this.body, 2, 8, -1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F));
    this.wing0 = new ModelRenderer(this);
    this.wing0.func_78793_a(1.5F, 0.4F, 0.2F);
    this.body.func_78792_a(this.wing0);
    setRotationAngle(this.wing0, 0.1745F, 0.0F, 0.0F);
    this.wing0.field_78804_l.add(new ModelBox(this.wing0, 19, 8, -0.5F, 0.0F, -1.5F, 1, 5, 3, 0.0F));
    this.wing1 = new ModelRenderer(this);
    this.wing1.func_78793_a(-1.5F, 0.4F, 0.2F);
    this.body.func_78792_a(this.wing1);
    setRotationAngle(this.wing1, 0.1745F, 0.0F, 0.0F);
    this.wing1.field_78804_l.add(new ModelBox(this.wing1, 19, 8, -0.5F, 0.0F, -1.5F, 1, 5, 3, 0.0F));
    this.head = new ModelRenderer(this);
    this.head.func_78793_a(0.0F, 15.7F, -2.8F);
    this.head.field_78804_l.add(new ModelBox(this.head, 2, 2, -1.0F, -1.5F, -1.0F, 2, 3, 2, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 10, 0, -1.0F, -2.5F, -3.0F, 2, 1, 4, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 11, 7, -0.5F, -1.5F, -1.9F, 1, 2, 1, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 16, 7, -0.5F, -1.5F, -2.9F, 1, 1, 1, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 28, -2.0F, -4.5F, -2.0F, 4, 2, 2, 0.0F));
    this.tail = new ModelRenderer(this);
    this.tail.func_78793_a(0.0F, 21.1F, 1.2F);
    setRotationAngle(this.tail, 0.8727F, 0.0F, 0.0F);
    this.tail.field_78804_l.add(new ModelBox(this.tail, 22, 1, -1.5F, -1.0F, -1.0F, 3, 4, 1, 0.0F));
    this.leg0 = new ModelRenderer(this);
    this.leg0.func_78793_a(1.5F, 23.0F, -0.5F);
    this.leg0.field_78804_l.add(new ModelBox(this.leg0, 14, 18, -1.0F, -0.5F, -1.0F, 1, 2, 1, 0.0F));
    this.leg1 = new ModelRenderer(this);
    this.leg1.func_78793_a(-0.5F, 23.0F, -0.5F);
    this.leg1.field_78804_l.add(new ModelBox(this.leg1, 14, 18, -1.0F, -0.5F, -1.0F, 1, 2, 1, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.body.func_78785_a(f5);
    this.head.func_78785_a(f5);
    this.tail.func_78785_a(f5);
    this.leg0.func_78785_a(f5);
    this.leg1.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\renders\models\entities\ModelCaptainFlint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */