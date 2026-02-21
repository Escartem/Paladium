package fr.paladium.palamod.modules.luckyblock.renders.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBee extends ModelBase {
  private final ModelRenderer body;
  
  private final ModelRenderer stinger;
  
  private final ModelRenderer rightwing_bone;
  
  private final ModelRenderer leftwing_bone;
  
  private final ModelRenderer leg_front;
  
  private final ModelRenderer leg_mid;
  
  private final ModelRenderer leg_back;
  
  public ModelBee() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.body = new ModelRenderer(this);
    this.body.func_78793_a(0.5F, 19.0F, 0.0F);
    this.body.field_78804_l.add(new ModelBox(this.body, 0, 0, -3.5F, -4.0F, -5.0F, 7, 7, 10, 0.0F));
    this.body.field_78804_l.add(new ModelBox(this.body, 2, 3, -2.5F, -4.0F, -8.0F, 1, 2, 3, 0.0F));
    this.body.field_78804_l.add(new ModelBox(this.body, 2, 0, 1.5F, -4.0F, -8.0F, 1, 2, 3, 0.0F));
    this.stinger = new ModelRenderer(this);
    this.stinger.func_78793_a(0.0F, -1.0F, 1.0F);
    this.body.func_78792_a(this.stinger);
    this.stinger.field_78804_l.add(new ModelBox(this.stinger, 26, 7, 0.0F, 0.0F, 4.0F, 0, 1, 2, 0.0F));
    this.rightwing_bone = new ModelRenderer(this);
    this.rightwing_bone.func_78793_a(-1.5F, -4.0F, -3.0F);
    this.body.func_78792_a(this.rightwing_bone);
    setRotationAngle(this.rightwing_bone, 0.2618F, -0.2618F, 0.0F);
    this.rightwing_bone.field_78804_l
      .add(new ModelBox(this.rightwing_bone, 0, 18, -9.0F, 0.0F, 0.0F, 9, 0, 6, 0.0F));
    this.leftwing_bone = new ModelRenderer(this);
    this.leftwing_bone.func_78793_a(1.5F, -4.0F, -3.0F);
    this.body.func_78792_a(this.leftwing_bone);
    setRotationAngle(this.leftwing_bone, 0.2618F, 0.2618F, 0.0F);
    this.leftwing_bone.field_78804_l.add(new ModelBox(this.leftwing_bone, 9, 24, 0.0F, 0.0F, 0.0F, 9, 0, 6, 0.0F));
    this.leg_front = new ModelRenderer(this);
    this.leg_front.func_78793_a(1.5F, 3.0F, -2.0F);
    this.body.func_78792_a(this.leg_front);
    this.leg_front.field_78804_l.add(new ModelBox(this.leg_front, 26, 1, -5.0F, 0.0F, 0.0F, 7, 2, 0, 0.0F));
    this.leg_mid = new ModelRenderer(this);
    this.leg_mid.func_78793_a(1.5F, 3.0F, 0.0F);
    this.body.func_78792_a(this.leg_mid);
    this.leg_mid.field_78804_l.add(new ModelBox(this.leg_mid, 26, 3, -5.0F, 0.0F, 0.0F, 7, 2, 0, 0.0F));
    this.leg_back = new ModelRenderer(this);
    this.leg_back.func_78793_a(1.5F, 3.0F, 2.0F);
    this.body.func_78792_a(this.leg_back);
    this.leg_back.field_78804_l.add(new ModelBox(this.leg_back, 26, 5, -5.0F, 0.0F, 0.0F, 7, 2, 0, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.body.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\ModelBee.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */