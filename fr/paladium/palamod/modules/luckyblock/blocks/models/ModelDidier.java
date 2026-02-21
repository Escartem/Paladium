package fr.paladium.palamod.modules.luckyblock.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDidier extends ModelBase {
  private final ModelRenderer body;
  
  private final ModelRenderer rightLeg;
  
  private final ModelRenderer leftLeg;
  
  private final ModelRenderer rightArm;
  
  private final ModelRenderer leftArm;
  
  private final ModelRenderer rightEye;
  
  private final ModelRenderer leftEye;
  
  public ModelDidier() {
    this.field_78090_t = 128;
    this.field_78089_u = 64;
    this.body = new ModelRenderer(this);
    this.body.func_78793_a(0.0F, 13.0F, 0.0F);
    this.body.field_78804_l.add(new ModelBox(this.body, 16, 0, -6.0F, -3.0F, -6.0F, 12, 12, 12, 0.0F));
    this.rightLeg = new ModelRenderer(this);
    this.rightLeg.func_78793_a(-4.0F, 4.0F, 0.5F);
    this.body.func_78792_a(this.rightLeg);
    this.rightLeg.field_78804_l.add(new ModelBox(this.rightLeg, 16, 24, -1.5F, 1.0F, -3.5F, 5, 6, 6, 0.0F));
    this.leftLeg = new ModelRenderer(this);
    this.leftLeg.func_78793_a(4.0F, 4.0F, 0.5F);
    this.body.func_78792_a(this.leftLeg);
    this.leftLeg.field_78804_l.add(new ModelBox(this.leftLeg, 42, 24, -3.5F, 1.0F, -3.5F, 5, 6, 6, 0.0F));
    this.rightArm = new ModelRenderer(this);
    this.rightArm.func_78793_a(-8.0F, 0.0F, 0.0F);
    this.body.func_78792_a(this.rightArm);
    this.rightArm.field_78804_l.add(new ModelBox(this.rightArm, 0, 12, 0.0F, 1.0F, -3.0F, 2, 6, 6, 0.0F));
    this.leftArm = new ModelRenderer(this);
    this.leftArm.func_78793_a(8.0F, 0.0F, 0.0F);
    this.body.func_78792_a(this.leftArm);
    this.leftArm.field_78804_l.add(new ModelBox(this.leftArm, 64, 12, -2.0F, 1.0F, -3.0F, 2, 6, 6, 0.0F));
    this.rightEye = new ModelRenderer(this);
    this.rightEye.func_78793_a(-2.0F, 3.0F, -8.0F);
    this.body.func_78792_a(this.rightEye);
    this.rightEye.field_78804_l.add(new ModelBox(this.rightEye, 22, 8, -1.0F, -0.2F, 1.5F, 2, 3, 1, 0.0F));
    this.leftEye = new ModelRenderer(this);
    this.leftEye.func_78793_a(2.0F, 3.0F, -8.0F);
    this.body.func_78792_a(this.leftEye);
    this.leftEye.field_78804_l.add(new ModelBox(this.leftEye, 52, 8, -1.0F, -0.2F, 1.5F, 2, 3, 1, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.body.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll() {
    this.body.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\ModelDidier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */