package fr.paladium.palamod.modules.hunter.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelJellyFish extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer tentacleRight;
  
  private final ModelRenderer tentacleLeft;
  
  private final ModelRenderer tentacleFront;
  
  private final ModelRenderer tentacleBack;
  
  public ModelJellyFish() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 19.0F, 1.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 11, -3.0F, -4.0F, -4.0F, 6, 1, 6, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -4.0F, -3.0F, -5.0F, 8, 3, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 11, 18, -1.0F, -3.0F, -2.0F, 2, 12, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 20, 21, -1.5F, -3.0F, -2.5F, 3, 8, 3, 0.0F));
    this.tentacleRight = new ModelRenderer(this);
    this.tentacleRight.func_78793_a(-2.0F, -2.5F, -1.0F);
    this.bone.func_78792_a(this.tentacleRight);
    setRotationAngle(this.tentacleRight, 0.0F, 0.0F, 0.1309F);
    this.tentacleRight.field_78804_l
      .add(new ModelBox(this.tentacleRight, 0, 16, 0.0F, -1.5F, -2.5F, 0, 11, 5, 0.0F));
    this.tentacleLeft = new ModelRenderer(this);
    this.tentacleLeft.func_78793_a(2.0F, -2.5F, -1.0F);
    this.bone.func_78792_a(this.tentacleLeft);
    setRotationAngle(this.tentacleLeft, 0.0F, 3.1416F, -0.1309F);
    this.tentacleLeft.field_78804_l
      .add(new ModelBox(this.tentacleLeft, 0, 16, 0.0F, -1.5F, -2.5F, 0, 11, 5, 0.0F));
    this.tentacleFront = new ModelRenderer(this);
    this.tentacleFront.func_78793_a(0.0F, -2.5F, -3.0F);
    this.bone.func_78792_a(this.tentacleFront);
    setRotationAngle(this.tentacleFront, 0.1309F, 3.1416F, 0.0F);
    this.tentacleFront.field_78804_l
      .add(new ModelBox(this.tentacleFront, 0, 21, -2.5F, -1.5F, 0.0F, 5, 11, 0, 0.0F));
    this.tentacleBack = new ModelRenderer(this);
    this.tentacleBack.func_78793_a(0.0F, -2.5F, 1.0F);
    this.bone.func_78792_a(this.tentacleBack);
    setRotationAngle(this.tentacleBack, -0.1309F, -3.1416F, 0.0F);
    this.tentacleBack.field_78804_l
      .add(new ModelBox(this.tentacleBack, 0, 21, -2.5F, -1.5F, 0.0F, 5, 11, 0, 0.0F));
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
    this.tentacleBack.field_78795_f = MathHelper.func_76134_b(f2 / 8.0F * 0.6662F + 3.1415927F) * f1 * 2.0F;
    this.tentacleRight.field_78808_h = MathHelper.func_76134_b(f2 / 8.0F * 0.6662F) * f1 * 2.0F;
    this.tentacleFront.field_78795_f = MathHelper.func_76134_b(f2 / 8.0F * 0.6662F) * f1 * 2.0F;
    this.tentacleLeft.field_78808_h = MathHelper.func_76134_b(f2 / 8.0F * 0.6662F + 3.1415927F) * f1 * 2.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\models\ModelJellyFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */