package fr.paladium.palamod.modules.hunter.models;

import fr.paladium.palamod.modules.hunter.entites.EntityPanda;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelPanda extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer bone1;
  
  private final ModelRenderer head;
  
  private final ModelRenderer armRight;
  
  private final ModelRenderer armLeft;
  
  private final ModelRenderer legLeft;
  
  private final ModelRenderer legRight;
  
  public ModelPanda() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone1 = new ModelRenderer(this);
    this.bone1.func_78793_a(0.0F, 14.0F, 9.0F);
    setRotationAngle(this.bone1, 0.0F, 0.0F, 0.0F);
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(-1.0F, 23.0F, 8.0F);
    this.bone1.func_78792_a(this.bone);
    setRotationAngle(this.bone, 1.5708F, 0.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 25, -9.0F, -24.0F, 8.0F, 19, 26, 13, 0.0F));
    this.head = new ModelRenderer(this);
    this.head.func_78793_a(0.5F, -26.375F, 11.125F);
    this.bone.func_78792_a(this.head);
    setRotationAngle(this.head, -1.5708F, 0.0F, 0.0F);
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 6, -6.5F, -7.625F, -5.125F, 13, 10, 9, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 45, 16, -3.5F, -2.625F, -7.125F, 7, 5, 2, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 52, 25, -7.5F, -10.625F, -1.125F, 5, 4, 1, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 52, 25, 2.5F, -10.625F, -1.125F, 5, 4, 1, 0.0F));
    this.armRight = new ModelRenderer(this);
    this.armRight.func_78793_a(-6.0F, -21.0F, 8.0F);
    this.bone.func_78792_a(this.armRight);
    setRotationAngle(this.armRight, -1.5708F, 0.0F, 0.0F);
    this.armRight.field_78804_l.add(new ModelBox(this.armRight, 40, 0, -3.0F, 0.0F, -3.0F, 6, 9, 6, 0.0F));
    this.armLeft = new ModelRenderer(this);
    this.armLeft.func_78793_a(7.0F, -21.0F, 8.0F);
    this.bone.func_78792_a(this.armLeft);
    setRotationAngle(this.armLeft, -1.5708F, 0.0F, 0.0F);
    this.armLeft.field_78804_l.add(new ModelBox(this.armLeft, 40, 0, -3.0F, 0.0F, -3.0F, 6, 9, 6, 0.0F));
    this.legLeft = new ModelRenderer(this);
    this.legLeft.func_78793_a(7.0F, -2.0F, 8.0F);
    this.bone.func_78792_a(this.legLeft);
    setRotationAngle(this.legLeft, -1.5708F, 0.0F, 0.0F);
    this.legLeft.field_78804_l.add(new ModelBox(this.legLeft, 40, 0, -3.0F, 0.0F, -3.0F, 6, 9, 6, 0.0F));
    this.legRight = new ModelRenderer(this);
    this.legRight.func_78793_a(-6.0F, -2.0F, 8.0F);
    this.bone.func_78792_a(this.legRight);
    setRotationAngle(this.legRight, -1.5708F, 0.0F, 0.0F);
    this.legRight.field_78804_l.add(new ModelBox(this.legRight, 40, 0, -3.0F, 0.0F, -3.0F, 6, 9, 6, 0.0F));
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
    EntityPanda panda = (EntityPanda)e;
    boolean isEating = panda.func_70113_ah();
    if (!isEating) {
      this.bone.field_78795_f = 1.6F;
      this.head.field_78808_h = f3 / 57.295776F / 2.0F;
      this.head.field_78795_f = f4 / 57.295776F - 1.6F;
      this.legRight.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1 - 1.6F;
      this.armLeft.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1 - 1.6F;
      this.legLeft.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1 - 1.6F;
      this.armRight.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1 - 1.6F;
    } else {
      this.bone.field_78795_f = 0.0F;
      this.bone.field_78796_g = 3.0F;
      this.bone1.field_78796_g = 3.0F;
      this.head.field_78795_f = MathHelper.func_76134_b(f2 * 0.5F) * 0.3F + 0.6F;
      this.armRight.field_78796_g = -0.5F;
      this.armLeft.field_78796_g = 0.5F;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\models\ModelPanda.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */