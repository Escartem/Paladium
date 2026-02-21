package fr.paladium.palamod.modules.hunter.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelMegaCreeper extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer armRight;
  
  private final ModelRenderer rArmpart1;
  
  private final ModelRenderer rArmpart2;
  
  private final ModelRenderer rArmpart3;
  
  private final ModelRenderer armLeft;
  
  private final ModelRenderer lArmpart1;
  
  private final ModelRenderer lArmpart2;
  
  private final ModelRenderer lArmpart3;
  
  private final ModelRenderer legLeft;
  
  private final ModelRenderer legRight;
  
  public ModelMegaCreeper() {
    this.field_78090_t = 128;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 12.0F, 6.0F);
    setRotationAngle(this.bone, 0.3491F, 0.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 13, 22, -8.0F, -20.0F, -8.0F, 16, 16, 16, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 96, 12, -4.0F, -4.0F, -4.0F, 8, 4, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 96, 0, -4.0F, -4.0F, -4.0F, 8, 4, 8, 0.3F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 96, 25, -4.0F, -23.0F, -4.0F, 8, 4, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 63, 18, -4.0F, -23.0F, -4.0F, 8, 4, 8, 0.3F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -4.0F, -16.0F, -9.0F, 8, 8, 6, 0.0F));
    this.armRight = new ModelRenderer(this);
    this.armRight.func_78793_a(-8.0F, -11.5F, 0.0F);
    this.bone.func_78792_a(this.armRight);
    setRotationAngle(this.armRight, -0.7854F, 0.0F, 0.0F);
    this.armRight.field_78804_l.add(new ModelBox(this.armRight, 59, 0, -4.0F, -3.5F, -3.0F, 4, 8, 6, 0.5F));
    this.rArmpart1 = new ModelRenderer(this);
    this.rArmpart1.func_78793_a(-2.0F, 4.5F, 0.0F);
    this.armRight.func_78792_a(this.rArmpart1);
    this.rArmpart1.field_78804_l.add(new ModelBox(this.rArmpart1, 42, 0, -2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F));
    this.rArmpart2 = new ModelRenderer(this);
    this.rArmpart2.func_78793_a(0.0F, 6.0F, 0.0F);
    this.rArmpart1.func_78792_a(this.rArmpart2);
    setRotationAngle(this.rArmpart2, 0.4363F, 0.0F, 0.0F);
    this.rArmpart2.field_78804_l.add(new ModelBox(this.rArmpart2, 41, 11, -2.0F, 0.0F, -2.0F, 4, 4, 4, 0.5F));
    this.rArmpart3 = new ModelRenderer(this);
    this.rArmpart3.func_78793_a(0.0F, 4.0F, 0.0F);
    this.rArmpart2.func_78792_a(this.rArmpart3);
    this.rArmpart3.field_78804_l.add(new ModelBox(this.rArmpart3, 0, 15, -2.0F, 0.0F, -2.0F, 4, 9, 4, 0.0F));
    this.rArmpart3.field_78804_l.add(new ModelBox(this.rArmpart3, 17, 15, -2.0F, 5.0F, -2.0F, 4, 2, 4, 1.0F));
    this.armLeft = new ModelRenderer(this);
    this.armLeft.func_78793_a(8.0F, -11.5F, 0.0F);
    this.bone.func_78792_a(this.armLeft);
    setRotationAngle(this.armLeft, -0.7854F, 0.0F, 0.0F);
    this.armLeft.field_78804_l.add(new ModelBox(this.armLeft, 59, 0, 0.0F, -3.5F, -3.0F, 4, 8, 6, 0.5F));
    this.lArmpart1 = new ModelRenderer(this);
    this.lArmpart1.func_78793_a(2.0F, 4.5F, 0.0F);
    this.armLeft.func_78792_a(this.lArmpart1);
    this.lArmpart1.field_78804_l.add(new ModelBox(this.lArmpart1, 42, 0, -2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F));
    this.lArmpart2 = new ModelRenderer(this);
    this.lArmpart2.func_78793_a(0.0F, 6.0F, 0.0F);
    this.lArmpart1.func_78792_a(this.lArmpart2);
    setRotationAngle(this.lArmpart2, 0.4363F, 0.0F, 0.0F);
    this.lArmpart2.field_78804_l.add(new ModelBox(this.lArmpart2, 41, 11, -2.0F, 0.0F, -2.0F, 4, 4, 4, 0.5F));
    this.lArmpart3 = new ModelRenderer(this);
    this.lArmpart3.func_78793_a(0.0F, 4.0F, 0.0F);
    this.lArmpart2.func_78792_a(this.lArmpart3);
    this.lArmpart3.field_78804_l.add(new ModelBox(this.lArmpart3, 0, 15, -2.0F, 0.0F, -2.0F, 4, 9, 4, 0.0F));
    this.lArmpart3.field_78804_l.add(new ModelBox(this.lArmpart3, 17, 15, -2.0F, 5.0F, -2.0F, 4, 2, 4, 1.0F));
    this.legLeft = new ModelRenderer(this);
    this.legLeft.func_78793_a(-4.0F, -0.5F, 0.0F);
    this.bone.func_78792_a(this.legLeft);
    setRotationAngle(this.legLeft, -0.3491F, 0.0F, 0.0F);
    this.legLeft.field_78804_l.add(new ModelBox(this.legLeft, 80, 0, -2.0F, -0.5F, -2.0F, 4, 13, 4, 0.0F));
    this.legRight = new ModelRenderer(this);
    this.legRight.func_78793_a(4.0F, -0.5F, 0.0F);
    this.bone.func_78792_a(this.legRight);
    setRotationAngle(this.legRight, -0.3491F, 0.0F, 0.0F);
    this.legRight.field_78804_l.add(new ModelBox(this.legRight, 80, 0, -2.0F, -0.5F, -2.0F, 4, 13, 4, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
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
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, e);
    this.legRight.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1 + -0.3491F;
    this.armLeft.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1 + -0.7854F;
    this.legLeft.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1 + -0.3491F;
    this.armRight.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1 + -0.7854F;
    this.lArmpart2.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * -0.5F * f1 + 0.4363F;
    this.rArmpart2.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * 0.5F * f1 + 0.4363F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\models\ModelMegaCreeper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */