package fr.paladium.palamod.modules.hunter.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelParrot extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer tail;
  
  private final ModelRenderer legs;
  
  private final ModelRenderer legRight;
  
  private final ModelRenderer legLeft;
  
  private final ModelRenderer head;
  
  private final ModelRenderer feather;
  
  private final ModelRenderer body;
  
  private final ModelRenderer wingLeft;
  
  private final ModelRenderer wingRight;
  
  public ModelParrot() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, -2.0F);
    this.tail = new ModelRenderer(this);
    this.tail.func_78793_a(0.0F, -2.75F, 4.0F);
    this.bone.func_78792_a(this.tail);
    setRotationAngle(this.tail, 1.0908F, 0.0F, 0.0F);
    this.tail.field_78804_l.add(new ModelBox(this.tail, 22, 1, -1.5F, 0.0F, -0.5F, 3, 4, 1, 0.0F));
    this.legs = new ModelRenderer(this);
    this.legs.func_78793_a(1.5F, -1.0F, 2.25F);
    this.bone.func_78792_a(this.legs);
    this.legRight = new ModelRenderer(this);
    this.legRight.func_78793_a(0.0F, 0.0F, 0.0F);
    this.legs.func_78792_a(this.legRight);
    this.legRight.field_78804_l.add(new ModelBox(this.legRight, 14, 18, -3.0F, -1.0F, 0.0F, 1, 2, 1, 0.0F));
    this.legLeft = new ModelRenderer(this);
    this.legLeft.func_78793_a(0.0F, 0.0F, 0.0F);
    this.legs.func_78792_a(this.legLeft);
    this.legLeft.field_78804_l.add(new ModelBox(this.legLeft, 14, 18, -1.0F, -1.0F, 0.0F, 1, 2, 1, 0.0F));
    this.head = new ModelRenderer(this);
    this.head.func_78793_a(0.0F, -6.0F, -1.0F);
    this.bone.func_78792_a(this.head);
    this.head.field_78804_l.add(new ModelBox(this.head, 2, 2, -1.0F, -3.0F, 0.0F, 2, 3, 2, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 11, 7, -0.5F, -3.0F, -0.75F, 1, 2, 1, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 16, 7, -0.5F, -3.25F, -1.75F, 1, 2, 1, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 10, 0, -1.0F, -4.0F, -2.0F, 2, 1, 4, 0.0F));
    this.feather = new ModelRenderer(this);
    this.feather.func_78793_a(0.0F, -2.75F, 1.5F);
    this.head.func_78792_a(this.feather);
    setRotationAngle(this.feather, -0.3054F, 0.0F, 0.0F);
    this.feather.field_78804_l.add(new ModelBox(this.feather, 2, 18, 0.0F, -4.5F, -2.0F, 0, 5, 4, 0.0F));
    this.body = new ModelRenderer(this);
    this.body.func_78793_a(0.0F, -2.25F, 1.5F);
    this.bone.func_78792_a(this.body);
    setRotationAngle(this.body, 0.6545F, 0.0F, 0.0F);
    this.body.field_78804_l.add(new ModelBox(this.body, 2, 8, -1.5F, -4.6013F, -0.2016F, 3, 6, 3, 0.0F));
    this.wingLeft = new ModelRenderer(this);
    this.wingLeft.func_78793_a(1.5F, -5.5F, 0.5F);
    this.bone.func_78792_a(this.wingLeft);
    setRotationAngle(this.wingLeft, 0.829F, 0.0F, 0.0F);
    this.wingLeft.field_78804_l.add(new ModelBox(this.wingLeft, 19, 8, -0.5F, -0.5F, -1.5F, 1, 5, 3, 0.0F));
    this.wingRight = new ModelRenderer(this);
    this.wingRight.func_78793_a(-1.5F, -5.5F, 0.5F);
    this.bone.func_78792_a(this.wingRight);
    setRotationAngle(this.wingRight, 0.829F, 0.0F, 0.0F);
    this.wingRight.field_78804_l.add(new ModelBox(this.wingRight, 19, 8, -0.5F, -0.5F, -1.5F, 1, 5, 3, 0.0F));
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
    boolean isOnGround = false;
    this.head.field_78796_g = f3 / 57.295776F;
    this.head.field_78795_f = f4 / 57.295776F;
    if (!isOnGround) {
      this.bone.field_78795_f = 0.75F;
      this.wingLeft
        .field_78808_h = MathHelper.func_76134_b(f2 * 2.0F * 0.6662F + 3.1415927F) * f1 * 3.0F + 8.0F;
      this.wingLeft.field_78795_f = 9.0F;
      this.wingLeft.field_78796_g = 0.1F;
      this.wingRight
        .field_78808_h = MathHelper.func_76134_b(f2 * 2.0F * 0.6662F + 3.1415927F) * f1 * -3.0F - 8.0F;
      this.wingRight.field_78795_f = 9.0F;
      this.wingRight.field_78796_g = 0.1F;
      this.tail.field_78795_f = 1.0F;
      this.legs.field_78795_f = 1.0F;
      this.head.field_78795_f = f4 / 57.295776F - 0.5F;
    } else {
      this.legRight.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1;
      this.legLeft.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\models\ModelParrot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */