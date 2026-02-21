package fr.paladium.palamod.modules.luckyblock.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelTrophyChristmas extends ModelBase {
  private final ModelRenderer BONE;
  
  private final ModelRenderer LID;
  
  private final ModelRenderer TIE;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer BARELY_SUGAR_0;
  
  private final ModelRenderer BARELY_SUGAR_1;
  
  private final ModelRenderer BARELY_SUGAR_2;
  
  private final ModelRenderer BARELY_SUGAR_3;
  
  public ModelTrophyChristmas() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.BONE = new ModelRenderer(this);
    this.BONE.func_78793_a(0.0F, 24.0F, 0.0F);
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 0, -5.0F, -8.0F, -5.0F, 10, 8, 10, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 30, 18, 5.0F, -3.0F, -4.0F, 3, 3, 3, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 30, 0, -8.0F, -3.0F, -8.0F, 3, 3, 3, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 12, 30, 5.5F, -5.0F, -3.5F, 2, 2, 2, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 32, 34, 6.0F, -6.0F, -3.0F, 1, 1, 1, 0.0F));
    this.LID = new ModelRenderer(this);
    this.LID.func_78793_a(-5.0F, -5.0F, 6.5F);
    this.BONE.func_78792_a(this.LID);
    setRotationAngle(this.LID, -1.3963F, -0.8727F, 0.0F);
    this.LID.field_78804_l.add(new ModelBox(this.LID, 0, 18, -5.0F, 0.0F, -5.0F, 10, 2, 10, 0.0F));
    this.LID.field_78804_l.add(new ModelBox(this.LID, 16, 30, -2.0F, -2.0F, -2.0F, 4, 2, 4, 0.0F));
    this.TIE = new ModelRenderer(this);
    this.TIE.func_78793_a(0.0F, -1.0F, 0.0F);
    this.LID.func_78792_a(this.TIE);
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, -1.0F, 0.0F);
    this.TIE.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, -0.7854F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 30, -2.0F, -1.0F, -2.0F, 4, 2, 4, 0.0F));
    this.BARELY_SUGAR_0 = new ModelRenderer(this);
    this.BARELY_SUGAR_0.func_78793_a(-0.5F, -7.5F, 0.5F);
    this.BONE.func_78792_a(this.BARELY_SUGAR_0);
    setRotationAngle(this.BARELY_SUGAR_0, 1.0036F, 1.4835F, 0.0F);
    this.BARELY_SUGAR_0.field_78804_l
      .add(new ModelBox(this.BARELY_SUGAR_0, 4, 18, -0.5F, -6.5F, -0.5F, 1, 8, 1, 0.0F));
    this.BARELY_SUGAR_0.field_78804_l
      .add(new ModelBox(this.BARELY_SUGAR_0, 33, 33, -0.5F, -7.5F, -3.5F, 1, 1, 3, 0.0F));
    this.BARELY_SUGAR_0.field_78804_l
      .add(new ModelBox(this.BARELY_SUGAR_0, 35, 24, -0.5F, -6.5F, -4.5F, 1, 2, 1, 0.0F));
    this.BARELY_SUGAR_1 = new ModelRenderer(this);
    this.BARELY_SUGAR_1.func_78793_a(0.5F, -5.5F, 0.5F);
    this.BONE.func_78792_a(this.BARELY_SUGAR_1);
    setRotationAngle(this.BARELY_SUGAR_1, 0.3927F, -2.0508F, 0.4363F);
    this.BARELY_SUGAR_1.field_78804_l
      .add(new ModelBox(this.BARELY_SUGAR_1, 0, 18, -0.5F, -6.5F, -0.5F, 1, 8, 1, 0.0F));
    this.BARELY_SUGAR_1.field_78804_l
      .add(new ModelBox(this.BARELY_SUGAR_1, 30, 24, -0.5F, -7.5F, -3.5F, 1, 1, 3, 0.0F));
    this.BARELY_SUGAR_1.field_78804_l
      .add(new ModelBox(this.BARELY_SUGAR_1, 35, 6, -0.5F, -6.5F, -4.5F, 1, 2, 1, 0.0F));
    this.BARELY_SUGAR_2 = new ModelRenderer(this);
    this.BARELY_SUGAR_2.func_78793_a(-2.5F, -5.5F, -1.5F);
    this.BONE.func_78792_a(this.BARELY_SUGAR_2);
    setRotationAngle(this.BARELY_SUGAR_2, 0.6981F, 0.3491F, 0.0F);
    this.BARELY_SUGAR_2.field_78804_l
      .add(new ModelBox(this.BARELY_SUGAR_2, 4, 0, -0.5F, -6.5F, -0.5F, 1, 8, 1, 0.0F));
    this.BARELY_SUGAR_2.field_78804_l
      .add(new ModelBox(this.BARELY_SUGAR_2, 30, 6, -0.5F, -7.5F, -3.5F, 1, 1, 3, 0.0F));
    this.BARELY_SUGAR_2.field_78804_l
      .add(new ModelBox(this.BARELY_SUGAR_2, 33, 30, -0.5F, -6.5F, -4.5F, 1, 2, 1, 0.0F));
    this.BARELY_SUGAR_3 = new ModelRenderer(this);
    this.BARELY_SUGAR_3.func_78793_a(-0.5F, -5.5F, 0.5F);
    this.BONE.func_78792_a(this.BARELY_SUGAR_3);
    setRotationAngle(this.BARELY_SUGAR_3, -0.7418F, 0.2618F, 0.7418F);
    this.BARELY_SUGAR_3.field_78804_l
      .add(new ModelBox(this.BARELY_SUGAR_3, 0, 0, -0.5F, -6.5F, -0.5F, 1, 8, 1, 0.0F));
    this.BARELY_SUGAR_3.field_78804_l
      .add(new ModelBox(this.BARELY_SUGAR_3, 28, 30, -0.5F, -7.5F, -3.5F, 1, 1, 3, 0.0F));
    this.BARELY_SUGAR_3.field_78804_l
      .add(new ModelBox(this.BARELY_SUGAR_3, 0, 30, -0.5F, -6.5F, -4.5F, 1, 2, 1, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float limbSwing, float limbSwingAmount, float liveTick, float headYaw, float headPitch, float scaleFactor) {
    func_78087_a(limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor, entity);
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
  
  public void renderAll() {
    this.BONE.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\ModelTrophyChristmas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */