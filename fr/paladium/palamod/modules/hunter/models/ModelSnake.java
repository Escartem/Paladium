package fr.paladium.palamod.modules.hunter.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelSnake extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer tail5;
  
  private final ModelRenderer tail6;
  
  private final ModelRenderer tail7;
  
  private final ModelRenderer tail8;
  
  private final ModelRenderer tail4;
  
  private final ModelRenderer tail3;
  
  private final ModelRenderer tail2;
  
  private final ModelRenderer tail1;
  
  private final ModelRenderer head;
  
  private final ModelRenderer jaw;
  
  public ModelSnake() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, -4.0F);
    this.tail5 = new ModelRenderer(this);
    this.tail5.func_78793_a(0.0F, -1.5F, 34.75F);
    this.bone.func_78792_a(this.tail5);
    this.tail5.field_78804_l.add(new ModelBox(this.tail5, 14, 11, -2.0F, -1.5F, 0.25F, 4, 3, 12, 0.1F));
    this.tail6 = new ModelRenderer(this);
    this.tail6.func_78793_a(0.0F, 0.0F, 12.5F);
    this.tail5.func_78792_a(this.tail6);
    this.tail6.field_78804_l.add(new ModelBox(this.tail6, 0, 26, -2.0F, -1.5F, -0.25F, 4, 3, 12, 0.0F));
    this.tail7 = new ModelRenderer(this);
    this.tail7.func_78793_a(0.0F, 0.0F, 11.75F);
    this.tail6.func_78792_a(this.tail7);
    this.tail7.field_78804_l.add(new ModelBox(this.tail7, 34, 26, -1.5F, -1.5F, 0.0F, 3, 3, 12, 0.0F));
    this.tail8 = new ModelRenderer(this);
    this.tail8.func_78793_a(0.5F, 0.0F, 12.0F);
    this.tail7.func_78792_a(this.tail8);
    this.tail8.field_78804_l.add(new ModelBox(this.tail8, 1, 47, -1.5F, -1.5F, 0.0F, 2, 3, 12, 0.0F));
    this.tail4 = new ModelRenderer(this);
    this.tail4.func_78793_a(0.0F, -1.5F, 35.5F);
    this.bone.func_78792_a(this.tail4);
    this.tail4.field_78804_l.add(new ModelBox(this.tail4, 38, 9, -2.0F, -1.5F, -9.5F, 4, 3, 9, 0.2F));
    this.tail3 = new ModelRenderer(this);
    this.tail3.func_78793_a(0.0F, 0.0F, -9.5F);
    this.tail4.func_78792_a(this.tail3);
    this.tail3.field_78804_l.add(new ModelBox(this.tail3, 38, 9, -2.0F, -1.5F, -9.0F, 4, 3, 9, 0.2F));
    this.tail2 = new ModelRenderer(this);
    this.tail2.func_78793_a(0.0F, 0.0F, -9.0F);
    this.tail3.func_78792_a(this.tail2);
    this.tail2.field_78804_l.add(new ModelBox(this.tail2, 23, 0, -2.0F, -1.5F, -7.0F, 4, 3, 7, 0.1F));
    this.tail1 = new ModelRenderer(this);
    this.tail1.func_78793_a(0.0F, 0.0F, -6.5F);
    this.tail2.func_78792_a(this.tail1);
    this.tail1.field_78804_l.add(new ModelBox(this.tail1, 46, 0, -2.0F, -1.5F, -5.5F, 4, 3, 5, 0.0F));
    this.head = new ModelRenderer(this);
    this.head.func_78793_a(0.0F, 1.5F, -5.5F);
    this.tail1.func_78792_a(this.head);
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 0, -2.5F, -3.0F, -6.0F, 5, 3, 6, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 9, -2.0F, -3.0F, -11.0F, 4, 2, 5, 0.0F));
    this.jaw = new ModelRenderer(this);
    this.jaw.func_78793_a(0.0F, -0.5F, -6.0F);
    this.head.func_78792_a(this.jaw);
    this.jaw.field_78804_l.add(new ModelBox(this.jaw, 0, 16, -2.0F, -0.5F, -5.0F, 4, 1, 5, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    GL11.glTranslated(0.0D, 0.0D, -2.0D);
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
    this.head
      .field_78796_g = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * f1 + f4 / 57.295776F;
    this.head.field_78795_f = f4 / 57.295776F + 0.1F;
    this.tail1.field_78796_g = MathHelper.func_76134_b(f * 0.6662F * 0.5F) * f1;
    this.tail1.field_78796_g = 0.1F;
    this.tail2.field_78796_g = MathHelper.func_76134_b(f * 0.6662F * 0.5F) * f1;
    this.tail3.field_78796_g = MathHelper.func_76134_b(f * 0.6662F * 0.5F + 3.1415927F) * f1;
    this.tail3.field_78795_f = -0.1F;
    this.tail4.field_78796_g = MathHelper.func_76134_b(f * 0.6662F * 0.5F + 3.1415927F) * f1;
    this.tail4.field_78795_f = -0.1F;
    this.tail5.field_78796_g = MathHelper.func_76134_b(f * 0.6662F) * f1;
    this.tail6.field_78796_g = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * f1;
    this.tail7.field_78796_g = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * f1;
    this.tail8.field_78796_g = MathHelper.func_76134_b(f * 0.6662F) * f1;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\models\ModelSnake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */