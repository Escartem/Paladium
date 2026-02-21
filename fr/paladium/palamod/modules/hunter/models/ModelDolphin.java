package fr.paladium.palamod.modules.hunter.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelDolphin extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer rightFin;
  
  private final ModelRenderer leftFin;
  
  private final ModelRenderer fin;
  
  private final ModelRenderer head;
  
  private final ModelRenderer tail;
  
  private final ModelRenderer tail2;
  
  public ModelDolphin() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 22, 0, -4.0F, -7.0F, -2.0F, 8, 7, 13, 0.0F));
    this.rightFin = new ModelRenderer(this);
    this.rightFin.func_78793_a(-3.5F, -2.0F, 0.5F);
    this.bone.func_78792_a(this.rightFin);
    setRotationAngle(this.rightFin, -1.0472F, 0.0F, 0.6981F);
    this.rightFin.field_78804_l.add(new ModelBox(this.rightFin, 48, 20, -0.5F, -2.0F, -0.5F, 1, 4, 7, 0.0F));
    this.leftFin = new ModelRenderer(this);
    this.leftFin.func_78793_a(3.5F, -2.0F, 0.5F);
    this.bone.func_78792_a(this.leftFin);
    setRotationAngle(this.leftFin, -1.0472F, 0.0F, -0.6981F);
    this.leftFin.field_78804_l.add(new ModelBox(this.leftFin, 48, 20, -0.5F, -2.0F, -0.5F, 1, 4, 7, 0.0F));
    this.fin = new ModelRenderer(this);
    this.fin.func_78793_a(0.0F, -7.0F, 1.0F);
    this.bone.func_78792_a(this.fin);
    setRotationAngle(this.fin, 1.0036F, 0.0F, 0.0F);
    this.fin.field_78804_l.add(new ModelBox(this.fin, 51, 0, -0.5F, 0.0F, 0.0F, 1, 4, 5, 0.0F));
    this.head = new ModelRenderer(this);
    this.head.func_78793_a(0.0F, -2.25F, -2.5F);
    this.bone.func_78792_a(this.head);
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 0, -4.0F, -4.75F, -5.5F, 8, 7, 6, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 13, -1.0F, 0.25F, -9.5F, 2, 2, 4, 0.0F));
    this.tail = new ModelRenderer(this);
    this.tail.func_78793_a(0.0F, -3.75F, 11.25F);
    this.bone.func_78792_a(this.tail);
    this.tail.field_78804_l.add(new ModelBox(this.tail, 0, 19, -2.0F, -2.25F, -0.25F, 4, 5, 11, 0.0F));
    this.tail2 = new ModelRenderer(this);
    this.tail2.func_78793_a(0.0F, 0.25F, 8.75F);
    this.tail.func_78792_a(this.tail2);
    this.tail2.field_78804_l.add(new ModelBox(this.tail2, 19, 20, -5.0F, -0.5F, 0.0F, 10, 1, 6, 0.0F));
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
    this.bone.field_78796_g = f3 / 57.295776F;
    this.bone.field_78795_f = MathHelper.func_76134_b(f * 0.6662F) * f1 + f4 / 57.295776F;
    this.tail2.field_78795_f = MathHelper.func_76134_b(f * 0.6662F * 2.0F) * f1 * 3.0F;
    this.tail.field_78795_f = MathHelper.func_76134_b(f * 0.6662F * 2.0F) * f1;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\models\ModelDolphin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */