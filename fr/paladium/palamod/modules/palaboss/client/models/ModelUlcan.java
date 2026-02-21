package fr.paladium.palamod.modules.palaboss.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelUlcan extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer body;
  
  private final ModelRenderer neck;
  
  private final ModelRenderer neck2;
  
  private final ModelRenderer neck3;
  
  private final ModelRenderer head;
  
  private final ModelRenderer fin;
  
  private final ModelRenderer fin2;
  
  private final ModelRenderer lantern;
  
  private final ModelRenderer lantern2;
  
  private final ModelRenderer lantern3;
  
  private final ModelRenderer lantern4;
  
  private final ModelRenderer teeth;
  
  private final ModelRenderer jaw;
  
  private final ModelRenderer tail;
  
  private final ModelRenderer tail2;
  
  private final ModelRenderer tail3;
  
  public ModelUlcan() {
    this.field_78090_t = 256;
    this.field_78089_u = 256;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.body = new ModelRenderer(this);
    this.body.func_78793_a(0.0F, -3.3333F, -0.3333F);
    this.bone.func_78792_a(this.body);
    this.body.field_78804_l.add(new ModelBox(this.body, 132, 142, -9.0F, -16.6667F, -22.6667F, 18, 20, 44, 0.0F));
    this.neck = new ModelRenderer(this);
    this.neck.func_78793_a(0.0F, -6.4167F, -25.4167F);
    this.body.func_78792_a(this.neck);
    this.neck.field_78804_l.add(new ModelBox(this.neck, 158, 89, -8.0F, -8.75F, -30.25F, 16, 18, 33, 0.0F));
    this.neck2 = new ModelRenderer(this);
    this.neck2.func_78793_a(-1.0F, 0.0F, -30.5F);
    this.neck.func_78792_a(this.neck2);
    this.neck2.field_78804_l.add(new ModelBox(this.neck2, 162, 40, -6.0F, -6.75F, -32.75F, 14, 14, 33, 0.0F));
    this.neck3 = new ModelRenderer(this);
    this.neck3.func_78793_a(1.0F, 1.25F, -34.25F);
    this.neck2.func_78792_a(this.neck3);
    this.neck3.field_78804_l.add(new ModelBox(this.neck3, 176, 0, -6.0F, -6.0F, -26.5F, 12, 11, 28, 0.0F));
    this.head = new ModelRenderer(this);
    this.head.func_78793_a(0.0F, -1.25F, -27.5F);
    this.neck3.func_78792_a(this.head);
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 0, -8.0F, -5.0F, -15.0F, 16, 11, 16, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 28, -6.0F, -5.0F, -31.0F, 12, 7, 16, 0.0F));
    this.fin = new ModelRenderer(this);
    this.fin.func_78793_a(-8.0F, 2.5F, -5.0F);
    this.head.func_78792_a(this.fin);
    setRotationAngle(this.fin, 0.0F, -0.2618F, 0.0F);
    this.fin.field_78804_l.add(new ModelBox(this.fin, 65, 29, 0.0F, -3.5F, 0.0F, 0, 7, 6, 0.0F));
    this.fin2 = new ModelRenderer(this);
    this.fin2.func_78793_a(8.0F, 2.5F, -5.0F);
    this.head.func_78792_a(this.fin2);
    setRotationAngle(this.fin2, 0.0F, 0.2618F, 0.0F);
    this.fin2.field_78804_l.add(new ModelBox(this.fin2, 65, 29, 0.0F, -3.5F, 0.0F, 0, 7, 6, 0.0F));
    this.lantern = new ModelRenderer(this);
    this.lantern.func_78793_a(0.0F, -5.25F, -13.5F);
    this.head.func_78792_a(this.lantern);
    setRotationAngle(this.lantern, 0.5236F, 0.0F, 0.0F);
    this.lantern.field_78804_l.add(new ModelBox(this.lantern, 41, 29, -1.0F, -10.75F, -1.5F, 2, 11, 2, 0.0F));
    this.lantern2 = new ModelRenderer(this);
    this.lantern2.func_78793_a(0.0F, -11.0792F, -0.4056F);
    this.lantern.func_78792_a(this.lantern2);
    setRotationAngle(this.lantern2, 0.5236F, 0.0F, 0.0F);
    this.lantern2.field_78804_l.add(new ModelBox(this.lantern2, 41, 29, -1.0F, -10.25F, -1.5F, 2, 11, 2, 0.0F));
    this.lantern3 = new ModelRenderer(this);
    this.lantern3.func_78793_a(0.0F, -10.1478F, 0.0308F);
    this.lantern2.func_78792_a(this.lantern3);
    setRotationAngle(this.lantern3, 0.7854F, 0.0F, 0.0F);
    this.lantern3.field_78804_l
      .add(new ModelBox(this.lantern3, 41, 29, -1.0F, -10.7462F, -1.5872F, 2, 11, 2, 0.0F));
    this.lantern4 = new ModelRenderer(this);
    this.lantern4.func_78793_a(0.0F, -10.7348F, -0.8486F);
    this.lantern3.func_78792_a(this.lantern4);
    setRotationAngle(this.lantern4, 1.309F, 0.0F, 0.0F);
    this.lantern4.field_78804_l.add(new ModelBox(this.lantern4, 50, 30, -1.5F, -8.0F, -3.0F, 3, 8, 4, 0.0F));
    this.teeth = new ModelRenderer(this);
    this.teeth.func_78793_a(0.0F, 3.5F, -23.0F);
    this.head.func_78792_a(this.teeth);
    setRotationAngle(this.teeth, 0.0F, 0.0F, -3.1416F);
    this.teeth.field_78804_l.add(new ModelBox(this.teeth, 72, 0, -6.0F, -1.5F, -8.0F, 12, 3, 16, 0.2F));
    this.jaw = new ModelRenderer(this);
    this.jaw.func_78793_a(0.0F, 3.5F, -15.0F);
    this.head.func_78792_a(this.jaw);
    setRotationAngle(this.jaw, 0.6109F, 0.0F, 0.0F);
    this.jaw.field_78804_l.add(new ModelBox(this.jaw, 72, 0, -6.0F, -3.5F, -16.0F, 12, 3, 16, 0.1F));
    this.jaw.field_78804_l.add(new ModelBox(this.jaw, 0, 52, -6.0F, -1.5F, -16.0F, 12, 4, 16, 0.0F));
    this.tail = new ModelRenderer(this);
    this.tail.func_78793_a(-1.1667F, -6.25F, 18.0F);
    this.body.func_78792_a(this.tail);
    setRotationAngle(this.tail, 0.0F, 3.1416F, 0.0F);
    this.tail.field_78804_l.add(new ModelBox(this.tail, 0, 76, -8.3333F, -9.9167F, -29.6667F, 16, 19, 33, 0.0F));
    this.tail.field_78804_l.add(new ModelBox(this.tail, 0, 157, -0.8333F, -15.1667F, -29.6667F, 0, 30, 33, 0.0F));
    this.tail2 = new ModelRenderer(this);
    this.tail2.func_78793_a(-0.1667F, 0.05F, -29.0F);
    this.tail.func_78792_a(this.tail2);
    this.tail2.field_78804_l
      .add(new ModelBox(this.tail2, 69, 48, -5.6667F, -9.7667F, -40.6667F, 10, 19, 40, 0.0F));
    this.tail2.field_78804_l
      .add(new ModelBox(this.tail2, 172, 186, -0.6667F, -15.2667F, -40.6667F, 0, 30, 40, 0.0F));
    this.tail3 = new ModelRenderer(this);
    this.tail3.func_78793_a(0.3333F, -0.4667F, -41.6667F);
    this.tail2.func_78792_a(this.tail3);
    this.tail3.field_78804_l.add(new ModelBox(this.tail3, 37, 108, -5.0F, -8.0F, -60.0F, 8, 17, 61, 0.0F));
    this.tail3.field_78804_l.add(new ModelBox(this.tail3, 0, 140, -1.25F, -14.75F, -85.0F, 0, 30, 86, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    this.bone.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, e);
    this.head.field_78796_g = f3 / 57.295776F / 4.0F;
    this.head.field_78795_f = f4 / 57.295776F / 4.0F;
    this.neck.field_78796_g = f3 / 57.295776F / 4.0F;
    this.neck.field_78795_f = f4 / 57.295776F / 4.0F;
    this.neck2.field_78796_g = f3 / 57.295776F / 4.0F;
    this.neck2.field_78795_f = f4 / 57.295776F / 4.0F;
    this.neck3.field_78796_g = f3 / 57.295776F / 4.0F;
    this.neck3.field_78795_f = f4 / 57.295776F / 4.0F;
    this.tail.field_78796_g = f3 / 57.295776F / 3.0F * -1.0F + 135.15F;
    this.tail.field_78795_f = f4 / 57.295776F / 3.0F * -1.0F;
    this.tail2
      .field_78796_g = f3 / 57.295776F / 2.0F * -1.0F + MathHelper.func_76134_b(f) / 4.0F * f1;
    this.tail2.field_78795_f = f4 / 57.295776F / 3.0F * -1.0F;
    this.tail3
      .field_78796_g = f3 / 57.295776F / 3.0F * -1.0F + MathHelper.func_76134_b(f) / 4.0F * f1;
    this.tail3.field_78795_f = f4 / 57.295776F / 3.0F * -1.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\models\ModelUlcan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */