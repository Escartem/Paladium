package fr.paladium.palamod.modules.luckyblock.renders.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelDove extends ModelBase {
  private final ModelRenderer BONE;
  
  private final ModelRenderer HEAD;
  
  private final ModelRenderer GRASS;
  
  private final ModelRenderer WING_RIGHT;
  
  private final ModelRenderer WING_LEFT;
  
  public ModelDove() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.BONE = new ModelRenderer(this);
    this.BONE.func_78793_a(0.0F, 24.0F, 0.0F);
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 10, 10, -1.0F, -3.0F, -2.0F, 2, 2, 5, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 13, -1.0F, -3.0F, 3.0F, 2, 1, 3, 0.0F));
    this.HEAD = new ModelRenderer(this);
    this.HEAD.func_78793_a(0.0F, -1.9167F, -2.0F);
    this.BONE.func_78792_a(this.HEAD);
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 0, 17, -1.0F, -1.0833F, -2.0F, 2, 2, 2, 0.0F));
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 0, 0, -0.5F, -0.0833F, -3.0F, 1, 1, 1, 0.0F));
    this.GRASS = new ModelRenderer(this);
    this.GRASS.func_78793_a(0.0F, 0.9167F, -2.5F);
    this.HEAD.func_78792_a(this.GRASS);
    setRotationAngle(this.GRASS, 0.0F, 0.0F, -0.2618F);
    this.GRASS.field_78804_l.add(new ModelBox(this.GRASS, 0, 10, -4.5F, 0.0F, -2.5F, 6, 0, 3, 0.0F));
    this.WING_RIGHT = new ModelRenderer(this);
    this.WING_RIGHT.func_78793_a(-1.0F, -2.5F, 0.0F);
    this.BONE.func_78792_a(this.WING_RIGHT);
    this.WING_RIGHT.field_78804_l.add(new ModelBox(this.WING_RIGHT, 0, 5, -7.0F, -0.5F, -2.0F, 7, 1, 4, 0.0F));
    this.WING_LEFT = new ModelRenderer(this);
    this.WING_LEFT.func_78793_a(1.0F, -2.5F, 0.0F);
    this.BONE.func_78792_a(this.WING_LEFT);
    this.WING_LEFT.field_78804_l.add(new ModelBox(this.WING_LEFT, 0, 0, 0.0F, -0.5F, -2.0F, 7, 1, 4, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float limbSwing, float limbSwingAmount, float liveTick, float headYaw, float headPitch, float scaleFactor) {
    setRotationAngles(entity, limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    super.func_78088_a(entity, limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor);
    this.BONE.func_78785_a(scaleFactor);
    GL11.glDisable(3042);
  }
  
  public void func_78087_a(float limbSwing, float limbSwingAmount, float liveTick, float headYaw, float headPitch, float scaleFactor, Entity e) {
    super.func_78087_a(limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor, e);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, e);
    this.WING_RIGHT.field_78808_h = MathHelper.func_76134_b(f2 * 0.6662F);
    this.WING_LEFT.field_78808_h = -MathHelper.func_76134_b(f2 * 0.6662F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\ModelDove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */