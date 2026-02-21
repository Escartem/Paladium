package fr.paladium.palamod.modules.hunter.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelTurtle extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer head;
  
  private final ModelRenderer legLeft;
  
  private final ModelRenderer legRight;
  
  private final ModelRenderer armLeft;
  
  private final ModelRenderer armRight;
  
  private final ModelRenderer shell;
  
  private final ModelRenderer BONE;
  
  private final ModelRenderer HEAD;
  
  private final ModelRenderer LEG_RIGHT;
  
  private final ModelRenderer LEG_LEFT;
  
  private final ModelRenderer ARM_RIGHT;
  
  private final ModelRenderer ARM_LEFT;
  
  private final ModelRenderer TAIL;
  
  public ModelTurtle() {
    this.field_78090_t = 128;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.head = new ModelRenderer(this);
    this.head.func_78793_a(0.0F, -2.5F, -7.0F);
    this.bone.func_78792_a(this.head);
    this.head.field_78804_l.add(new ModelBox(this.head, 3, 0, -3.0F, -2.5F, -7.0F, 6, 5, 6, 0.0F));
    this.legLeft = new ModelRenderer(this);
    this.legLeft.func_78793_a(2.5F, -0.5F, 10.0F);
    this.bone.func_78792_a(this.legLeft);
    this.legLeft.field_78804_l.add(new ModelBox(this.legLeft, 1, 12, -2.0F, -0.5F, 0.0F, 4, 1, 10, 0.0F));
    this.legRight = new ModelRenderer(this);
    this.legRight.func_78793_a(-2.5F, -0.5F, 10.0F);
    this.bone.func_78792_a(this.legRight);
    this.legRight.field_78804_l.add(new ModelBox(this.legRight, 1, 23, -2.0F, -0.5F, 0.0F, 4, 1, 10, 0.0F));
    this.armLeft = new ModelRenderer(this);
    this.armLeft.func_78793_a(6.0F, -0.5F, -3.5F);
    this.bone.func_78792_a(this.armLeft);
    this.armLeft.field_78804_l.add(new ModelBox(this.armLeft, 27, 24, -0.5F, -0.5F, -2.5F, 13, 1, 5, 0.0F));
    this.armRight = new ModelRenderer(this);
    this.armRight.func_78793_a(-6.0F, -0.5F, -3.5F);
    this.bone.func_78792_a(this.armRight);
    this.armRight.field_78804_l.add(new ModelBox(this.armRight, 27, 30, -12.5F, -0.5F, -2.5F, 13, 1, 5, 0.0F));
    this.shell = new ModelRenderer(this);
    this.shell.func_78793_a(0.0F, -0.6667F, 1.3333F);
    this.bone.func_78792_a(this.shell);
    setRotationAngle(this.shell, 1.5708F, 0.0F, 0.0F);
    this.shell.field_78804_l.add(new ModelBox(this.shell, 7, 37, -9.5F, -9.3333F, 0.6667F, 19, 20, 6, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 31, 1, -5.5F, -9.3333F, -2.3333F, 11, 18, 3, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 70, 33, -4.5F, -9.3333F, -3.3333F, 9, 18, 1, 0.0F));
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.BONE = new ModelRenderer(this);
    this.BONE.func_78793_a(-0.0357F, 15.5F, 4.3571F);
    setRotationAngle(this.BONE, 0.0F, 0.0436F, 0.0F);
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 28, 14, -5.7026F, -9.5F, -6.3514F, 12, 12, 6, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 8, 19, -3.7026F, -9.4F, -7.8514F, 8, 11, 2, 0.0F));
    this.HEAD = new ModelRenderer(this);
    this.HEAD.func_78793_a(0.2974F, -10.5F, -4.8514F);
    this.BONE.func_78792_a(this.HEAD);
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 0, 0, -4.0F, -7.0F, -4.0F, 8, 8, 8, 0.0F));
    this.LEG_RIGHT = new ModelRenderer(this);
    this.LEG_RIGHT.func_78793_a(-2.2026F, 2.0F, -4.6014F);
    this.BONE.func_78792_a(this.LEG_RIGHT);
    this.LEG_RIGHT.field_78804_l.add(new ModelBox(this.LEG_RIGHT, 32, 0, -2.0F, -1.5F, -2.0F, 4, 8, 4, 0.0F));
    this.LEG_LEFT = new ModelRenderer(this);
    this.LEG_LEFT.func_78793_a(2.7974F, 1.5F, -4.6014F);
    this.BONE.func_78792_a(this.LEG_LEFT);
    this.LEG_LEFT.field_78804_l.add(new ModelBox(this.LEG_LEFT, 32, 0, -2.0F, -1.0F, -2.0F, 4, 8, 4, 0.0F));
    this.ARM_RIGHT = new ModelRenderer(this);
    this.ARM_RIGHT.func_78793_a(-5.7026F, -8.75F, -5.3514F);
    this.BONE.func_78792_a(this.ARM_RIGHT);
    this.ARM_RIGHT.field_78804_l.add(new ModelBox(this.ARM_RIGHT, 48, 0, -2.0F, -0.5F, -2.0F, 4, 9, 4, 0.0F));
    this.ARM_LEFT = new ModelRenderer(this);
    this.ARM_LEFT.func_78793_a(6.2974F, -8.75F, -5.3514F);
    this.BONE.func_78792_a(this.ARM_LEFT);
    this.ARM_LEFT.field_78804_l.add(new ModelBox(this.ARM_LEFT, 48, 0, -2.0F, -0.5F, -2.0F, 4, 9, 4, 0.0F));
    this.TAIL = new ModelRenderer(this);
    this.TAIL.func_78793_a(0.0872F, 1.0F, -1.9981F);
    this.BONE.func_78792_a(this.TAIL);
    setRotationAngle(this.TAIL, -0.5236F, 0.0F, 0.0F);
    this.TAIL.field_78804_l.add(new ModelBox(this.TAIL, 44, 32, -0.9643F, -1.0F, 0.1429F, 2, 2, 8, 0.0F));
    this.TAIL.field_78804_l.add(new ModelBox(this.TAIL, 0, 32, -0.9643F, -4.0F, 4.1429F, 2, 3, 4, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    if (entity.func_70005_c_().equalsIgnoreCase("carapuce")) {
      this.BONE.func_78785_a(f5);
    } else {
      this.bone.func_78785_a(f5);
    } 
    GL11.glDisable(3042);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scaleFactor, Entity e) {
    super.func_78087_a(limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scaleFactor, e);
    if (e.func_70005_c_().equalsIgnoreCase("carapuce")) {
      this.HEAD.field_78796_g = headYaw / 57.295776F;
      this.HEAD.field_78795_f = headPitch / 57.295776F;
      this.ARM_LEFT.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * limbSwingAmount;
      this.LEG_RIGHT.field_78795_f = MathHelper.func_76134_b(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
      this.LEG_LEFT.field_78795_f = MathHelper.func_76134_b(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
      this.TAIL.field_78796_g = MathHelper.func_76134_b(limbSwing * 0.6662F) * limbSwingAmount * 0.5F;
      this.ARM_RIGHT
        .field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * limbSwingAmount;
    } else {
      boolean isSwiming = true;
      this.head.field_78796_g = headYaw / 57.295776F;
      this.head.field_78795_f = headPitch / 57.295776F;
      if (!isSwiming) {
        this.legRight.field_78796_g = MathHelper.func_76134_b(limbSwing * 2.0F) * 1.0F * limbSwingAmount;
        this.armLeft.field_78796_g = MathHelper.func_76134_b(limbSwing * 2.0F) * -1.0F * limbSwingAmount * 3.0F;
        this.legLeft.field_78796_g = MathHelper.func_76134_b(limbSwing * 2.0F) * -1.0F * limbSwingAmount;
        this.armRight.field_78796_g = MathHelper.func_76134_b(limbSwing * 2.0F) * 1.0F * limbSwingAmount * 3.0F;
      } else {
        this.legRight.field_78795_f = MathHelper.func_76134_b(limbSwing * 2.0F) * 1.0F * limbSwingAmount * 3.0F;
        this.armLeft.field_78808_h = MathHelper.func_76134_b(limbSwing * 2.0F) * -1.0F * limbSwingAmount * 3.0F;
        this.legLeft.field_78795_f = MathHelper.func_76134_b(limbSwing * 2.0F) * -1.0F * limbSwingAmount * 3.0F;
        this.armRight.field_78808_h = MathHelper.func_76134_b(limbSwing * 2.0F) * 1.0F * limbSwingAmount * 3.0F;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\models\ModelTurtle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */