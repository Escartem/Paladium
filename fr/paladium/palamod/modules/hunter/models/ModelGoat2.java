package fr.paladium.palamod.modules.hunter.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelGoat2 extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer tail;
  
  private final ModelRenderer legLeft;
  
  private final ModelRenderer armLeft;
  
  private final ModelRenderer legRight;
  
  private final ModelRenderer armRight;
  
  public ModelGoat2() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, -3.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 34, 0, -4.0F, -15.0F, 5.0F, 8, 9, 7, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 20, 42, -6.0F, -17.0F, -5.0F, 12, 12, 10, 0.1F));
    this.tail = new ModelRenderer(this);
    this.tail.func_78793_a(0.0F, -11.0F, 12.0F);
    this.bone.func_78792_a(this.tail);
    setRotationAngle(this.tail, 0.3927F, 0.0F, 0.0F);
    this.tail.field_78804_l.add(new ModelBox(this.tail, 21, 23, -1.0F, -1.0F, -1.0F, 2, 4, 2, 0.1F));
    this.legLeft = new ModelRenderer(this);
    this.legLeft.func_78793_a(-3.0F, -6.5F, 11.0F);
    this.bone.func_78792_a(this.legLeft);
    this.legLeft.field_78804_l.add(new ModelBox(this.legLeft, 0, 42, -2.0F, -0.5F, -2.0F, 4, 7, 4, 0.3F));
    this.armLeft = new ModelRenderer(this);
    this.armLeft.func_78793_a(-3.0F, -6.5F, 0.0F);
    this.bone.func_78792_a(this.armLeft);
    this.armLeft.field_78804_l.add(new ModelBox(this.armLeft, 0, 42, -2.0F, -0.5F, -2.0F, 4, 7, 4, 0.3F));
    this.legRight = new ModelRenderer(this);
    this.legRight.func_78793_a(3.0F, -6.5F, 11.0F);
    this.bone.func_78792_a(this.legRight);
    this.legRight.field_78804_l.add(new ModelBox(this.legRight, 0, 42, -2.0F, -0.5F, -2.0F, 4, 7, 4, 0.3F));
    this.armRight = new ModelRenderer(this);
    this.armRight.func_78793_a(3.0F, -6.5F, 0.0F);
    this.bone.func_78792_a(this.armRight);
    this.armRight.field_78804_l.add(new ModelBox(this.armRight, 0, 42, -2.0F, -0.5F, -2.0F, 4, 7, 4, 0.3F));
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
    this.legRight.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1;
    this.armLeft.field_78795_f = MathHelper.func_76134_b(f * 0.6662F) * f1;
    this.legLeft.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1;
    this.armRight.field_78795_f = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * f1;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\models\ModelGoat2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */