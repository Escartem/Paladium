package fr.paladium.palamod.modules.hunter.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelSnail extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer eyes;
  
  private final ModelRenderer rEye;
  
  private final ModelRenderer lEye;
  
  private final ModelRenderer shell;
  
  public ModelSnail() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 1.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 10, 21, -1.5F, -3.0F, -7.0F, 3, 3, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 7, 0, -1.5F, -1.0F, -8.0F, 1, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 7, 0, 0.5F, -1.0F, -8.0F, 1, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 21, -1.0F, -2.0F, 1.0F, 2, 2, 5, 0.0F));
    this.eyes = new ModelRenderer(this);
    this.eyes.func_78793_a(0.0F, -2.25F, -6.25F);
    this.bone.func_78792_a(this.eyes);
    setRotationAngle(this.eyes, 0.5236F, 0.0F, 0.0F);
    this.rEye = new ModelRenderer(this);
    this.rEye.func_78793_a(-1.0F, -1.0F, 0.0F);
    this.eyes.func_78792_a(this.rEye);
    setRotationAngle(this.rEye, 0.0F, 0.0F, -0.1745F);
    this.rEye.field_78804_l.add(new ModelBox(this.rEye, 0, 0, -0.5F, -2.5F, -0.5F, 1, 3, 1, 0.0F));
    this.lEye = new ModelRenderer(this);
    this.lEye.func_78793_a(1.0F, -1.0F, 0.0F);
    this.eyes.func_78792_a(this.lEye);
    setRotationAngle(this.lEye, 0.0F, 0.0F, 0.1745F);
    this.lEye.field_78804_l.add(new ModelBox(this.lEye, 0, 0, -0.5F, -2.5F, -0.5F, 1, 3, 1, 0.0F));
    this.shell = new ModelRenderer(this);
    this.shell.func_78793_a(0.0F, -4.0F, -1.0F);
    this.bone.func_78792_a(this.shell);
    setRotationAngle(this.shell, 1.309F, 0.0F, 0.0F);
    this.shell.field_78804_l.add(new ModelBox(this.shell, 12, 0, -1.5F, -2.5858F, -2.5858F, 3, 7, 7, 0.25F));
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
    this.eyes.field_78795_f = MathHelper.func_76134_b(f2 * 0.6662F / 8.0F) / 8.0F + 0.5236F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\models\ModelSnail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */