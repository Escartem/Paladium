package fr.paladium.palamod.modules.palaboss.client.models;

import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityTobalt;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelTobalt extends ModelBiped {
  private final ModelRenderer bone;
  
  private final ModelRenderer head;
  
  private final ModelRenderer stars;
  
  private final ModelRenderer stars2;
  
  private final ModelRenderer shell;
  
  private final ModelRenderer armRight;
  
  private final ModelRenderer armLeft;
  
  private final ModelRenderer legLeft;
  
  private final ModelRenderer legRight;
  
  public ModelTobalt() {
    this.field_78090_t = 256;
    this.field_78089_u = 256;
    this.bone = new ModelRenderer((ModelBase)this);
    this.bone.func_78793_a(6.0F, 24.0F, -8.0F);
    this.head = new ModelRenderer((ModelBase)this);
    this.head.func_78793_a(-6.0F, -18.0F, -30.0F);
    this.bone.func_78792_a(this.head);
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 0, -6.0F, -7.0F, -13.0F, 12, 10, 12, 2.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 29, -8.5F, 2.75F, -14.0F, 2, 6, 2, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 29, 6.5F, 2.75F, -14.0F, 2, 6, 2, 0.0F));
    this.stars = new ModelRenderer((ModelBase)this);
    this.stars.func_78793_a(0.0F, -14.8333F, -8.8333F);
    this.head.func_78792_a(this.stars);
    setRotationAngle(this.stars, 0.0F, -1.5708F, 0.0F);
    this.stars.field_78804_l.add(new ModelBox(this.stars, 51, 0, -6.0F, -4.9167F, 10.8333F, 12, 10, 0, 0.0F));
    this.stars.field_78804_l.add(new ModelBox(this.stars, 51, 0, -6.0F, -4.9167F, -11.1667F, 12, 10, 0, 0.0F));
    this.stars2 = new ModelRenderer((ModelBase)this);
    this.stars2.func_78793_a(0.0F, 0.0833F, -0.1667F);
    this.stars.func_78792_a(this.stars2);
    setRotationAngle(this.stars2, 0.0F, -1.5708F, 0.0F);
    this.stars2.field_78804_l.add(new ModelBox(this.stars2, 51, 0, -6.0F, -5.0F, 11.0F, 12, 10, 0, 0.0F));
    this.stars2.field_78804_l.add(new ModelBox(this.stars2, 51, 0, -6.0F, -5.0F, -11.0F, 12, 10, 0, 0.0F));
    this.shell = new ModelRenderer((ModelBase)this);
    this.shell.func_78793_a(-6.0F, -25.0F, 8.0F);
    this.bone.func_78792_a(this.shell);
    setRotationAngle(this.shell, -1.5708F, 0.0F, 0.0F);
    this.shell.field_78804_l.add(new ModelBox(this.shell, 46, 46, -35.0F, -36.0F, -25.0F, 70, 72, 35, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 65, 65, -35.0F, -36.0F, -15.0F, 70, 72, 16, 3.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 115, 48, -38.0F, -36.0F, -25.0F, 3, 72, 33, -2.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 48, 48, -35.0F, -38.0F, -25.0F, 70, 2, 33, -2.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 48, 48, -35.0F, 36.0F, -25.0F, 70, 1, 33, -2.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 134, 66, 34.0F, -36.0F, -23.0F, 4, 72, 15, -1.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 71, 167, -40.0F, -40.0F, -1.0F, 80, 80, 9, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 218, 184, 40.0F, -27.0F, -1.0F, 2, 56, 9, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 224, 186, -42.0F, -27.0F, -1.0F, 2, 56, 9, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 112, 238, -28.0F, 40.0F, -1.0F, 56, 2, 9, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 112, 237, -28.0F, -42.0F, -1.0F, 56, 2, 9, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, 17.0F, -5.0F, -37.0F, 8, 11, 12, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, 7.0F, 14.0F, -37.0F, 8, 11, 12, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, 15.0F, 0.0F, -47.0F, 8, 22, 22, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, 12.0F, -7.0F, -55.0F, 3, 22, 26, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, -12.0F, 16.0F, -50.0F, 18, 7, 13, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, -12.0F, 13.0F, -55.0F, 18, 3, 13, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, -12.0F, 10.0F, -64.0F, 18, 3, 14, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, -19.0F, 10.0F, -55.0F, 11, 7, 18, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, 23.0F, -3.0F, -36.0F, 3, 22, 11, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, 30.0F, 13.0F, -26.0F, 9, 6, 12, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, 23.0F, 0.0F, -43.0F, 3, 14, 7, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, -27.0F, -4.0F, -48.0F, 9, 11, 23, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, -26.0F, -3.0F, -50.0F, 6, 8, 2, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, -25.0F, -2.0F, -52.0F, 4, 6, 2, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, -25.0F, 3.0F, -48.0F, 9, 11, 23, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, -22.0F, 14.0F, -37.0F, 8, 11, 12, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, -30.0F, 21.0F, -26.0F, 5, 4, 1, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, -22.0F, -19.0F, -43.0F, 8, 15, 18, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, -16.0F, -23.0F, -50.0F, 8, 8, 25, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, 4.0F, -23.0F, -39.0F, 17, 12, 15, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 98, 115, 18.0F, -11.0F, -28.0F, 15, 6, 4, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 98, 115, 15.0F, -11.0F, -33.0F, 2, 6, 5, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 98, 115, 33.0F, -11.0F, -28.0F, 6, 6, 16, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, 10.0F, -13.0F, -49.0F, 11, 12, 15, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, 3.0F, -18.0F, -55.0F, 11, 12, 15, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, 7.0F, -1.0F, -71.0F, 5, 14, 21, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, 8.0F, 0.0F, -73.0F, 3, 10, 2, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, -17.0F, -1.0F, -58.0F, 5, 14, 15, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, 4.0F, -15.0F, -58.0F, 7, 4, 3, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, -8.0F, 18.0F, -30.0F, 6, 12, 5, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, -7.0F, 13.0F, -58.0F, 4, 7, 18, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 98, 106, -13.0F, 23.0F, -27.0F, 4, 17, 15, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, -2.0F, 17.0F, -40.0F, 11, 8, 15, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, 5.0F, 14.0F, -51.0F, 11, 8, 15, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, -20.0F, 14.0F, -41.0F, 7, 8, 15, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, -8.0F, -23.0F, -55.0F, 17, 7, 15, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, -19.0F, -14.0F, -55.0F, 9, 15, 15, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, -16.0F, -14.0F, -70.0F, 6, 8, 15, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, -13.0F, -7.0F, -67.0F, 6, 8, 15, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 96, 114, -15.0F, 13.0F, -40.0F, 13, 8, 14, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, 6.0F, -14.0F, -64.0F, 5, 15, 15, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, -11.0F, -19.0F, -70.0F, 9, 7, 21, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 214, -3.0F, -19.0F, -63.0F, 9, 7, 14, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, -23.0F, -23.0F, -39.0F, 13, 8, 14, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 98, 101, -12.0F, -40.0F, -31.0F, 7, 18, 12, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 98, 101, -12.0F, -21.0F, -41.0F, 7, 18, 12, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 98, 101, -12.0F, -13.0F, -61.0F, 19, 23, 3, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 98, 101, -17.0F, 0.0F, -61.0F, 5, 10, 3, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 98, 101, -18.0F, 0.0F, -61.0F, 1, 10, 13, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 98, 101, -20.0F, 0.0F, -49.0F, 2, 10, 1, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 98, 101, -26.0F, 6.0F, -49.0F, 6, 3, 11, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, -28.0F, -5.0F, -39.0F, 18, 8, 14, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, -40.0F, -5.0F, -28.0F, 18, 8, 14, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 197, -34.0F, -4.0F, -32.0F, 7, 6, 4, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 190, -5.0F, -27.0F, -45.0F, 15, 8, 20, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 190, -12.0F, -34.0F, -29.0F, 22, 8, 5, 0.0F));
    this.shell.field_78804_l.add(new ModelBox(this.shell, 0, 190, 21.0F, -34.0F, -29.0F, 6, 8, 5, 0.0F));
    this.armRight = new ModelRenderer((ModelBase)this);
    this.armRight.func_78793_a(23.5F, -17.5F, -14.5F);
    this.bone.func_78792_a(this.armRight);
    this.armRight.field_78804_l.add(new ModelBox(this.armRight, 204, 0, -6.5F, 0.5F, -6.5F, 13, 17, 13, 0.0F));
    this.armLeft = new ModelRenderer((ModelBase)this);
    this.armLeft.func_78793_a(-35.5F, -17.5F, -14.5F);
    this.bone.func_78792_a(this.armLeft);
    this.armLeft.field_78804_l.add(new ModelBox(this.armLeft, 204, 0, -6.5F, 0.5F, -6.5F, 13, 17, 13, 0.0F));
    this.legLeft = new ModelRenderer((ModelBase)this);
    this.legLeft.func_78793_a(-35.5F, -17.5F, 27.5F);
    this.bone.func_78792_a(this.legLeft);
    this.legLeft.field_78804_l.add(new ModelBox(this.legLeft, 204, 0, -6.5F, 0.5F, -6.5F, 13, 17, 13, 0.0F));
    this.legRight = new ModelRenderer((ModelBase)this);
    this.legRight.func_78793_a(23.5F, -17.5F, 27.5F);
    this.bone.func_78792_a(this.legRight);
    this.legRight.field_78804_l.add(new ModelBox(this.legRight, 204, 0, -6.5F, 0.5F, -6.5F, 13, 17, 13, 0.0F));
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
    EntityTobalt tobalt = (EntityTobalt)e;
    int f6 = tobalt.isInCharge() ? 1 : 0;
    this.head.field_78796_g = f3 / 57.295776F;
    this.head.field_78795_f = f4 / 57.295776F;
    this.legRight.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1;
    this.armLeft.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1;
    this.legLeft.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1;
    this.armRight.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1;
    this.stars.field_78796_g = f2 / 20.0F;
    this.bone.field_78796_g = f2 * f6;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\models\ModelTobalt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */