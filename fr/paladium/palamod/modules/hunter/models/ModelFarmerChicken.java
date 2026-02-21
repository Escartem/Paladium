package fr.paladium.palamod.modules.hunter.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelFarmerChicken extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer wing1;
  
  private final ModelRenderer wing0;
  
  private final ModelRenderer leg1;
  
  private final ModelRenderer leg0;
  
  private final ModelRenderer head;
  
  private final ModelRenderer comb;
  
  private final ModelRenderer beak;
  
  private final ModelRenderer body;
  
  public ModelFarmerChicken() {
    this.field_78090_t = 64;
    this.field_78089_u = 32;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.wing1 = new ModelRenderer(this);
    this.wing1.func_78793_a(3.0F, -11.0F, 0.0F);
    this.bone.func_78792_a(this.wing1);
    this.wing1.field_78804_l.add(new ModelBox(this.wing1, 24, 13, 0.0F, 0.0F, -3.0F, 1, 4, 6, 0.0F));
    this.wing0 = new ModelRenderer(this);
    this.wing0.func_78793_a(-3.0F, -11.0F, 0.0F);
    this.bone.func_78792_a(this.wing0);
    this.wing0.field_78804_l.add(new ModelBox(this.wing0, 24, 13, -1.0F, 0.0F, -3.0F, 1, 4, 6, 0.0F));
    this.leg1 = new ModelRenderer(this);
    this.leg1.func_78793_a(1.0F, -5.0F, 1.0F);
    this.bone.func_78792_a(this.leg1);
    this.leg1.field_78804_l.add(new ModelBox(this.leg1, 26, 0, -1.0F, 0.0F, -3.0F, 3, 5, 3, 0.0F));
    this.leg0 = new ModelRenderer(this);
    this.leg0.func_78793_a(-2.0F, -5.0F, 1.0F);
    this.bone.func_78792_a(this.leg0);
    this.leg0.field_78804_l.add(new ModelBox(this.leg0, 26, 0, -1.0F, 0.0F, -3.0F, 3, 5, 3, 0.0F));
    this.head = new ModelRenderer(this);
    this.head.func_78793_a(0.0F, -9.0F, -4.0F);
    this.bone.func_78792_a(this.head);
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 0, -2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 25, -4.0F, -5.0F, -4.0F, 8, 0, 7, 0.0F));
    this.comb = new ModelRenderer(this);
    this.comb.func_78793_a(0.0F, 0.0F, 0.0F);
    this.head.func_78792_a(this.comb);
    this.comb.field_78804_l.add(new ModelBox(this.comb, 14, 4, -1.0F, -2.0F, -3.0F, 2, 2, 2, 0.0F));
    this.beak = new ModelRenderer(this);
    this.beak.func_78793_a(0.0F, 0.0F, 0.0F);
    this.head.func_78792_a(this.beak);
    this.beak.field_78804_l.add(new ModelBox(this.beak, 14, 0, -2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F));
    this.body = new ModelRenderer(this);
    this.body.func_78793_a(0.0F, -8.0F, 0.0F);
    this.bone.func_78792_a(this.body);
    setRotationAngle(this.body, 1.5708F, 0.0F, 0.0F);
    this.body.field_78804_l.add(new ModelBox(this.body, 0, 9, -3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    setRotationAngles(entity, f, f1, f2, f3, f4, f5);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    this.bone.func_78785_a(f5);
    GL11.glDisable(3042);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
    this.head.field_78795_f = headPitch * 0.017453292F;
    this.head.field_78796_g = netHeadYaw * 0.017453292F;
    this.leg0.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
    this.leg1
      .field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 1.0F * limbSwingAmount;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\models\ModelFarmerChicken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */