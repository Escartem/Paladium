package fr.paladium.palamod.modules.luckyblock.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelTrophyHalloween extends ModelBase {
  private final ModelRenderer BONE;
  
  private final ModelRenderer STEM;
  
  private final ModelRenderer cube_r1;
  
  public ModelTrophyHalloween() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.BONE = new ModelRenderer(this);
    this.BONE.func_78793_a(0.0F, 24.0F, 0.0F);
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 11, 31, -4.0F, -1.0F, 1.0F, 4, 1, 3, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 14, -6.0F, -1.0F, -5.0F, 6, 1, 6, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 18, 0, -3.0F, -2.0F, -3.0F, 5, 1, 5, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 8, 21, -1.0F, -3.0F, -2.0F, 2, 1, 3, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 32, -2.0F, -6.0F, -1.0F, 2, 3, 2, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 32, 6, -1.0F, -8.0F, -2.0F, 2, 2, 2, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 7, -3.0F, -9.0F, -4.0F, 6, 1, 6, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 28, 28, -3.0F, -14.0F, 2.0F, 6, 5, 1, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 14, 25, -3.0F, -14.0F, -5.0F, 6, 5, 1, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 24, 6, -4.0F, -14.0F, -4.0F, 1, 5, 6, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 21, 3.0F, -14.0F, -4.0F, 1, 5, 6, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 0, -3.0F, -15.0F, -4.0F, 6, 1, 6, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 0, 1.0F, -7.0F, -3.0F, 1, 4, 1, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 14, 3.0F, -5.0F, -6.5F, 1, 3, 1, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 7, -3.0F, -12.0F, 5.5F, 1, 3, 1, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 18, 7, 5.0F, -2.0F, 5.5F, 1, 2, 1, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 18, 10, 2.0F, -8.0F, -6.0F, 1, 2, 1, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 18, 0, -5.0F, -2.0F, -6.0F, 1, 2, 1, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 18, 18, 0.0F, -1.0F, -3.0F, 4, 1, 6, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 18, 15, 2.0F, -1.0F, -6.0F, 2, 1, 2, 0.0F));
    this.STEM = new ModelRenderer(this);
    this.STEM.func_78793_a(0.0F, 0.0F, 0.0F);
    this.BONE.func_78792_a(this.STEM);
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, -15.0F, 0.0F);
    this.STEM.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 0.0F, -0.7854F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 22, 6, -2.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float limbSwing, float limbSwingAmount, float liveTick, float headYaw, float headPitch, float scaleFactor) {
    func_78087_a(limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor, entity);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    super.func_78088_a(entity, limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor);
    this.BONE.func_78785_a(scaleFactor);
    GL11.glDisable(3042);
  }
  
  public void renderAll() {
    this.BONE.func_78785_a(0.0625F);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\ModelTrophyHalloween.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */