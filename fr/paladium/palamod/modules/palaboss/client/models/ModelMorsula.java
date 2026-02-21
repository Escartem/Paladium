package fr.paladium.palamod.modules.palaboss.client.models;

import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityMorsula;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMorsula extends ModelBiped {
  private final ModelRenderer bone;
  
  private final ModelRenderer body;
  
  private final ModelRenderer neck;
  
  private final ModelRenderer neck2;
  
  private final ModelRenderer head;
  
  private final ModelRenderer teeth;
  
  private final ModelRenderer teeth1;
  
  private final ModelRenderer teeth11;
  
  private final ModelRenderer teeth2;
  
  private final ModelRenderer teeth22;
  
  private final ModelRenderer tail;
  
  private final ModelRenderer tail2;
  
  private final ModelRenderer tail3;
  
  private final ModelRenderer tail4;
  
  private final ModelRenderer tail5;
  
  private final ModelRenderer tail6;
  
  private final ModelRenderer spike2;
  
  private final ModelRenderer spike;
  
  private final ModelRenderer right;
  
  private final ModelRenderer left;
  
  private final ModelRenderer armRight;
  
  private final ModelRenderer armRight2;
  
  private final ModelRenderer armLeft;
  
  private final ModelRenderer armLeft2;
  
  public ModelMorsula() {
    this.field_78090_t = 192;
    this.field_78089_u = 192;
    this.bone = new ModelRenderer((ModelBase)this);
    this.bone.func_78793_a(0.0F, -8.5F, 5.0F);
    this.body = new ModelRenderer((ModelBase)this);
    this.body.func_78793_a(0.0F, 25.5F, -5.0F);
    this.bone.func_78792_a(this.body);
    this.body.field_78804_l.add(new ModelBox(this.body, 0, 0, -10.5F, -31.0F, -12.0F, 22, 16, 22, 8.0F));
    this.neck = new ModelRenderer((ModelBase)this);
    this.neck.func_78793_a(0.25F, -27.0F, -17.25F);
    this.body.func_78792_a(this.neck);
    this.neck.field_78804_l.add(new ModelBox(this.neck, 0, 38, -14.25F, -8.5F, -12.75F, 29, 23, 8, 2.0F));
    this.neck2 = new ModelRenderer((ModelBase)this);
    this.neck2.func_78793_a(0.0F, 3.5F, -11.5F);
    this.neck.func_78792_a(this.neck2);
    setRotationAngle(this.neck2, 0.0873F, 0.0F, 0.0F);
    this.neck2.field_78804_l.add(new ModelBox(this.neck2, 0, 76, -12.75F, -8.5F, -12.25F, 26, 18, 9, 1.0F));
    this.head = new ModelRenderer((ModelBase)this);
    this.head.func_78793_a(0.0F, -3.0F, -9.5F);
    this.neck2.func_78792_a(this.head);
    setRotationAngle(this.head, 0.1745F, 0.0F, 0.0F);
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 105, -11.75F, -5.5F, -13.75F, 24, 16, 11, 0.0F));
    this.teeth = new ModelRenderer((ModelBase)this);
    this.teeth.func_78793_a(-0.25F, 26.5F, -21.75F);
    this.head.func_78792_a(this.teeth);
    this.teeth1 = new ModelRenderer((ModelBase)this);
    this.teeth1.func_78793_a(6.0F, -18.5F, 7.0F);
    this.teeth.func_78792_a(this.teeth1);
    setRotationAngle(this.teeth1, 0.0F, 0.0F, -0.6109F);
    this.teeth1.field_78804_l.add(new ModelBox(this.teeth1, 168, 0, -4.0F, -6.5F, -2.0F, 8, 13, 4, 0.0F));
    this.teeth11 = new ModelRenderer((ModelBase)this);
    this.teeth11.func_78793_a(-1.2633F, 2.1782F, 2.0F);
    this.teeth1.func_78792_a(this.teeth11);
    setRotationAngle(this.teeth11, 0.0F, 0.0F, -0.9599F);
    this.teeth11.field_78804_l
      .add(new ModelBox(this.teeth11, 150, 21, -16.2092F, -1.6649F, -2.0F, 18, 3, 3, 0.0F));
    this.teeth2 = new ModelRenderer((ModelBase)this);
    this.teeth2.func_78793_a(-3.5F, -18.5F, 7.0F);
    this.teeth.func_78792_a(this.teeth2);
    setRotationAngle(this.teeth2, 0.0F, 0.0F, 0.6109F);
    this.teeth2.field_78804_l.add(new ModelBox(this.teeth2, 168, 0, -4.4096F, -6.2132F, -2.0F, 8, 13, 4, 0.0F));
    this.teeth22 = new ModelRenderer((ModelBase)this);
    this.teeth22.func_78793_a(0.9486F, 1.8502F, 2.0F);
    this.teeth2.func_78792_a(this.teeth22);
    setRotationAngle(this.teeth22, 0.0F, 0.0F, -2.1817F);
    this.teeth22.field_78804_l
      .add(new ModelBox(this.teeth22, 150, 21, -16.6791F, -1.8359F, -2.0F, 18, 3, 3, 0.0F));
    this.tail = new ModelRenderer((ModelBase)this);
    this.tail.func_78793_a(0.5F, -22.1011F, 17.8952F);
    this.body.func_78792_a(this.tail);
    setRotationAngle(this.tail, -0.3491F, 0.0F, 0.0F);
    this.tail.field_78804_l.add(new ModelBox(this.tail, 78, 0, -9.0F, -6.3746F, 2.789F, 18, 10, 7, 8.0F));
    this.tail2 = new ModelRenderer((ModelBase)this);
    this.tail2.func_78793_a(0.0F, 0.8499F, 15.2078F);
    this.tail.func_78792_a(this.tail2);
    setRotationAngle(this.tail2, -0.3491F, 0.0F, 0.0F);
    this.tail2.field_78804_l.add(new ModelBox(this.tail2, 90, 20, -6.0F, -5.4136F, 2.789F, 12, 4, 7, 8.0F));
    this.tail3 = new ModelRenderer((ModelBase)this);
    this.tail3.func_78793_a(0.0F, -1.0921F, 19.9702F);
    this.tail2.func_78792_a(this.tail3);
    setRotationAngle(this.tail3, 0.3491F, 0.0F, 0.0F);
    this.tail3.field_78804_l.add(new ModelBox(this.tail3, 92, 32, -5.0F, -4.4136F, 2.789F, 9, 2, 9, 7.0F));
    this.tail4 = new ModelRenderer((ModelBase)this);
    this.tail4.func_78793_a(0.0F, 0.0795F, 20.2483F);
    this.tail3.func_78792_a(this.tail4);
    setRotationAngle(this.tail4, 0.3491F, 0.0F, 0.0F);
    this.tail4.field_78804_l.add(new ModelBox(this.tail4, 92, 32, -5.0F, -4.4136F, 2.789F, 9, 2, 9, 6.0F));
    this.tail5 = new ModelRenderer((ModelBase)this);
    this.tail5.func_78793_a(-4.5F, -2.9136F, 14.289F);
    this.tail4.func_78792_a(this.tail5);
    setRotationAngle(this.tail5, 0.0F, -0.6109F, 0.0F);
    this.tail5.field_78804_l.add(new ModelBox(this.tail5, 48, 44, -5.5F, -2.5F, 0.5F, 11, 3, 29, 1.0F));
    this.tail6 = new ModelRenderer((ModelBase)this);
    this.tail6.func_78793_a(-1.5F, -2.9136F, 20.289F);
    this.tail4.func_78792_a(this.tail6);
    setRotationAngle(this.tail6, 0.0F, 0.6109F, 0.0F);
    this.tail6.field_78804_l.add(new ModelBox(this.tail6, 48, 44, 1.2181F, -2.5F, -2.1206F, 11, 3, 29, 1.0F));
    this.spike2 = new ModelRenderer((ModelBase)this);
    this.spike2.func_78793_a(-0.5F, -11.4644F, 2.8801F);
    this.tail.func_78792_a(this.spike2);
    setRotationAngle(this.spike2, -0.9599F, 0.0F, 0.0F);
    this.spike2.field_78804_l.add(new ModelBox(this.spike2, 151, 63, -1.5F, -21.75F, -2.0F, 3, 10, 4, 0.0F));
    this.spike2.field_78804_l.add(new ModelBox(this.spike2, 168, 58, -3.0F, -11.75F, -3.0F, 6, 13, 6, 0.0F));
    this.spike = new ModelRenderer((ModelBase)this);
    this.spike.func_78793_a(0.0F, -40.0F, -11.0F);
    this.body.func_78792_a(this.spike);
    setRotationAngle(this.spike, -0.8727F, 0.0F, 0.0F);
    this.right = new ModelRenderer((ModelBase)this);
    this.right.func_78793_a(9.5F, 1.6428F, 1.766F);
    this.spike.func_78792_a(this.right);
    setRotationAngle(this.right, 0.0F, 0.0F, 0.2618F);
    this.right.field_78804_l.add(new ModelBox(this.right, 176, 33, -2.0F, -16.0F, -2.0F, 4, 17, 4, 1.0F));
    this.right.field_78804_l.add(new ModelBox(this.right, 162, 39, -1.5F, -28.0F, -1.5F, 3, 11, 3, 0.0F));
    this.left = new ModelRenderer((ModelBase)this);
    this.left.func_78793_a(-9.5F, 1.6428F, 1.766F);
    this.spike.func_78792_a(this.left);
    setRotationAngle(this.left, 0.0F, 0.0F, -0.2618F);
    this.left.field_78804_l.add(new ModelBox(this.left, 176, 33, -2.0F, -16.0F, -2.0F, 4, 17, 4, 1.0F));
    this.left.field_78804_l.add(new ModelBox(this.left, 162, 39, -1.5F, -28.0F, -1.5F, 3, 11, 3, 0.0F));
    this.armRight = new ModelRenderer((ModelBase)this);
    this.armRight.func_78793_a(-19.5F, 1.75F, -11.75F);
    this.bone.func_78792_a(this.armRight);
    setRotationAngle(this.armRight, 0.0F, 0.0F, 0.3491F);
    this.armRight.field_78804_l.add(new ModelBox(this.armRight, 100, 86, -2.0F, -0.25F, -5.25F, 1, 29, 13, 2.0F));
    this.armRight2 = new ModelRenderer((ModelBase)this);
    this.armRight2.func_78793_a(-2.184F, 30.3706F, -2.75F);
    this.armRight.func_78792_a(this.armRight2);
    setRotationAngle(this.armRight2, 0.0F, 0.0F, 1.2217F);
    this.armRight2.field_78804_l.add(new ModelBox(this.armRight2, 70, 93, -0.5F, -0.5F, -2.5F, 1, 22, 13, 2.0F));
    this.armLeft = new ModelRenderer((ModelBase)this);
    this.armLeft.func_78793_a(19.5F, 1.75F, -11.75F);
    this.bone.func_78792_a(this.armLeft);
    setRotationAngle(this.armLeft, 0.0F, 0.0F, -0.3491F);
    this.armLeft.field_78804_l.add(new ModelBox(this.armLeft, 100, 86, 1.0F, -0.25F, -5.25F, 1, 29, 13, 2.0F));
    this.armLeft2 = new ModelRenderer((ModelBase)this);
    this.armLeft2.func_78793_a(2.184F, 30.3706F, -2.75F);
    this.armLeft.func_78792_a(this.armLeft2);
    setRotationAngle(this.armLeft2, 0.0F, 0.0F, -1.2217F);
    this.armLeft2.field_78804_l.add(new ModelBox(this.armLeft2, 70, 93, -0.5F, -0.5F, -2.5F, 1, 22, 13, 2.0F));
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
    EntityMorsula morsula = (EntityMorsula)e;
    boolean isAttacking = morsula.isShockwaveTeeth();
    this.tail4.field_78796_g = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * f1 / 4.0F;
    this.tail3.field_78796_g = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * f1 / 8.0F;
    this.armRight.field_78796_g = MathHelper.func_76134_b(f * 0.6662F) * f1 * -1.0F;
    this.armLeft.field_78796_g = MathHelper.func_76134_b(f * 0.6662F) * f1;
    if (!isAttacking) {
      this.neck2.field_78796_g = f3 / 57.295776F / 6.0F;
      this.neck2
        .field_78795_f = f4 / 57.295776F / 6.0F + MathHelper.func_76134_b(f * 0.6662F) * f1 / 6.0F;
      this.head.field_78796_g = f3 / 57.295776F / 6.0F;
      this.head
        .field_78795_f = f4 / 57.295776F / 6.0F + MathHelper.func_76134_b(f * 0.6662F) * f1 / 6.0F;
      this.neck.field_78796_g = f3 / 57.295776F / 6.0F;
      this.neck
        .field_78795_f = f4 / 57.295776F / 6.0F + MathHelper.func_76134_b(f * 0.6662F) * f1 / 6.0F;
    } else {
      this.neck.field_78796_g = MathHelper.func_76134_b(f2 * 0.75F * 0.6662F) * 0.25F;
      this.neck.field_78795_f = MathHelper.func_76134_b(f2 * 0.75F * 0.6662F) * 0.25F;
      this.neck2.field_78796_g = MathHelper.func_76134_b(f2 * 0.75F * 0.6662F) * 0.25F;
      this.neck2.field_78795_f = MathHelper.func_76134_b(f2 * 0.75F * 0.6662F) * 0.25F;
      this.head.field_78796_g = MathHelper.func_76134_b(f2 * 0.75F * 0.6662F) * 0.25F;
      this.head.field_78795_f = MathHelper.func_76134_b(f2 * 0.75F * 0.6662F) * 0.25F;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\models\ModelMorsula.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */