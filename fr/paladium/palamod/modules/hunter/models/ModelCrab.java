package fr.paladium.palamod.modules.hunter.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelCrab extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer claws;
  
  private final ModelRenderer rClaw;
  
  private final ModelRenderer RightClaw;
  
  private final ModelRenderer rClaw2;
  
  private final ModelRenderer rClaw3;
  
  private final ModelRenderer lClaw;
  
  private final ModelRenderer LeftClaw;
  
  private final ModelRenderer lClaw2;
  
  private final ModelRenderer lClaw3;
  
  private final ModelRenderer leftLegs;
  
  private final ModelRenderer lLeg1;
  
  private final ModelRenderer lLeg2;
  
  private final ModelRenderer lLeg3;
  
  private final ModelRenderer lLeg4;
  
  private final ModelRenderer rightLegs;
  
  private final ModelRenderer rLeg1;
  
  private final ModelRenderer rLeg2;
  
  private final ModelRenderer rLeg3;
  
  private final ModelRenderer rLeg4;
  
  public ModelCrab() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(-1.0F, 21.0F, -2.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 8, -1.0F, -1.0F, 0.0F, 4, 1, 4, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -1.5F, 0.0F, -0.5F, 5, 2, 5, 0.0F));
    this.claws = new ModelRenderer(this);
    this.claws.func_78793_a(-0.5F, 1.25F, -4.5F);
    this.bone.func_78792_a(this.claws);
    this.rClaw = new ModelRenderer(this);
    this.rClaw.func_78793_a(-0.6667F, 1.5F, 4.1667F);
    this.claws.func_78792_a(this.rClaw);
    setRotationAngle(this.rClaw, 0.0F, 0.48F, 0.0F);
    this.rClaw.field_78804_l.add(new ModelBox(this.rClaw, 28, 3, -0.3333F, -1.5F, -1.1667F, 1, 1, 1, 0.0F));
    this.RightClaw = new ModelRenderer(this);
    this.RightClaw.func_78793_a(0.1667F, -1.0F, -1.1667F);
    this.rClaw.func_78792_a(this.RightClaw);
    setRotationAngle(this.RightClaw, 0.0F, -0.7854F, 0.0F);
    this.rClaw2 = new ModelRenderer(this);
    this.rClaw2.func_78793_a(0.0F, 0.0F, 0.0F);
    this.RightClaw.func_78792_a(this.rClaw2);
    setRotationAngle(this.rClaw2, 0.0F, 0.0436F, 0.0F);
    this.rClaw2.field_78804_l.add(new ModelBox(this.rClaw2, 26, 5, -0.5F, -0.5F, -2.0F, 1, 1, 2, 0.1F));
    this.rClaw3 = new ModelRenderer(this);
    this.rClaw3.func_78793_a(0.0F, 0.0F, 0.0F);
    this.RightClaw.func_78792_a(this.rClaw3);
    setRotationAngle(this.rClaw3, 0.0F, -0.4363F, 0.0F);
    this.rClaw3.field_78804_l.add(new ModelBox(this.rClaw3, 26, 8, -0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F));
    this.lClaw = new ModelRenderer(this);
    this.lClaw.func_78793_a(3.6667F, 1.5F, 4.1667F);
    this.claws.func_78792_a(this.lClaw);
    setRotationAngle(this.lClaw, 0.0F, -0.48F, 0.0F);
    this.lClaw.field_78804_l.add(new ModelBox(this.lClaw, 28, 3, -0.6667F, -1.5F, -1.1667F, 1, 1, 1, 0.0F));
    this.LeftClaw = new ModelRenderer(this);
    this.LeftClaw.func_78793_a(-0.1667F, -1.0F, -1.1667F);
    this.lClaw.func_78792_a(this.LeftClaw);
    setRotationAngle(this.LeftClaw, 0.0F, 0.7854F, 0.0F);
    this.lClaw2 = new ModelRenderer(this);
    this.lClaw2.func_78793_a(0.0F, 0.0F, 0.0F);
    this.LeftClaw.func_78792_a(this.lClaw2);
    setRotationAngle(this.lClaw2, 0.0F, -0.0436F, 0.0F);
    this.lClaw2.field_78804_l.add(new ModelBox(this.lClaw2, 26, 5, -0.5F, -0.5F, -2.0F, 1, 1, 2, 0.1F));
    this.lClaw3 = new ModelRenderer(this);
    this.lClaw3.func_78793_a(0.0F, 0.0F, 0.0F);
    this.LeftClaw.func_78792_a(this.lClaw3);
    setRotationAngle(this.lClaw3, 0.0F, 0.4363F, 0.0F);
    this.lClaw3.field_78804_l.add(new ModelBox(this.lClaw3, 26, 8, -0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F));
    this.leftLegs = new ModelRenderer(this);
    this.leftLegs.func_78793_a(2.5F, 1.25F, 2.5F);
    this.bone.func_78792_a(this.leftLegs);
    setRotationAngle(this.leftLegs, 0.0F, 0.0F, 0.3491F);
    this.lLeg1 = new ModelRenderer(this);
    this.lLeg1.func_78793_a(1.0F, 0.0F, -1.5F);
    this.leftLegs.func_78792_a(this.lLeg1);
    setRotationAngle(this.lLeg1, 0.0F, 0.3054F, 0.0F);
    this.lLeg1.field_78804_l.add(new ModelBox(this.lLeg1, 24, 0, -0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F));
    this.lLeg2 = new ModelRenderer(this);
    this.lLeg2.func_78793_a(1.0F, 0.0F, -0.5F);
    this.leftLegs.func_78792_a(this.lLeg2);
    this.lLeg2.field_78804_l.add(new ModelBox(this.lLeg2, 24, 0, -0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F));
    this.lLeg3 = new ModelRenderer(this);
    this.lLeg3.func_78793_a(1.0F, 0.0F, 0.5F);
    this.leftLegs.func_78792_a(this.lLeg3);
    setRotationAngle(this.lLeg3, 0.0F, -0.1745F, 0.0F);
    this.lLeg3.field_78804_l.add(new ModelBox(this.lLeg3, 24, 0, -0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F));
    this.lLeg4 = new ModelRenderer(this);
    this.lLeg4.func_78793_a(1.0F, 0.0F, 1.5F);
    this.leftLegs.func_78792_a(this.lLeg4);
    setRotationAngle(this.lLeg4, 0.0F, -0.4363F, 0.0F);
    this.lLeg4.field_78804_l.add(new ModelBox(this.lLeg4, 24, 0, -0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F));
    this.rightLegs = new ModelRenderer(this);
    this.rightLegs.func_78793_a(-0.5F, 1.25F, 2.5F);
    this.bone.func_78792_a(this.rightLegs);
    setRotationAngle(this.rightLegs, 0.0F, 0.0F, -0.3491F);
    this.rLeg1 = new ModelRenderer(this);
    this.rLeg1.func_78793_a(-1.0F, 0.0F, -1.5F);
    this.rightLegs.func_78792_a(this.rLeg1);
    setRotationAngle(this.rLeg1, 0.0F, -0.3054F, 0.0F);
    this.rLeg1.field_78804_l.add(new ModelBox(this.rLeg1, 24, 0, -2.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F));
    this.rLeg2 = new ModelRenderer(this);
    this.rLeg2.func_78793_a(-1.0F, 0.0F, -0.5F);
    this.rightLegs.func_78792_a(this.rLeg2);
    this.rLeg2.field_78804_l.add(new ModelBox(this.rLeg2, 24, 0, -2.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F));
    this.rLeg3 = new ModelRenderer(this);
    this.rLeg3.func_78793_a(-1.0F, 0.0F, 0.5F);
    this.rightLegs.func_78792_a(this.rLeg3);
    setRotationAngle(this.rLeg3, 0.0F, 0.1745F, 0.0F);
    this.rLeg3.field_78804_l.add(new ModelBox(this.rLeg3, 24, 0, -2.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F));
    this.rLeg4 = new ModelRenderer(this);
    this.rLeg4.func_78793_a(-1.0F, 0.0F, 1.5F);
    this.rightLegs.func_78792_a(this.rLeg4);
    setRotationAngle(this.rLeg4, 0.0F, 0.4363F, 0.0F);
    this.rLeg4.field_78804_l.add(new ModelBox(this.rLeg4, 24, 0, -2.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F));
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    this.bone.func_78785_a(f5);
    GL11.glDisable(3042);
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, e);
    this.lLeg3.field_78796_g = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1 + 25.0F;
    this.lLeg2.field_78796_g = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1;
    this.lLeg4.field_78796_g = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1 + 24.0F;
    this.rLeg4.field_78796_g = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1 - 24.0F;
    this.lLeg1.field_78796_g = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1 + 26.0F;
    this.rLeg2.field_78796_g = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1;
    this.rLeg3.field_78796_g = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1 - 25.0F;
    this.rLeg1.field_78796_g = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1 - 26.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\models\ModelCrab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */