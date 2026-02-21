package fr.paladium.palamod.modules.luckyblock.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelTrophy extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer trophySilverFeatures;
  
  private final ModelRenderer trophyGoldFeatures;
  
  private final ModelRenderer trophyLegendaryFeatures;
  
  public ModelTrophy() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -3.0F, -1.0F, -3.0F, 6, 1, 6, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 24, 12, -1.0F, -8.0F, -1.0F, 2, 7, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 12, -2.0F, -9.0F, -2.0F, 4, 1, 4, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 22, 24, -2.0F, -15.0F, 2.0F, 4, 7, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 22, 24, -2.0F, -15.0F, -3.0F, 4, 7, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 19, 2.0F, -15.0F, -3.0F, 1, 7, 6, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 19, -3.0F, -15.0F, -3.0F, 1, 7, 6, 0.0F));
    this.trophySilverFeatures = new ModelRenderer(this);
    this.trophySilverFeatures.func_78793_a(0.0F, 0.0F, -0.5F);
    this.bone.func_78792_a(this.trophySilverFeatures);
    this.trophySilverFeatures.field_78804_l
      .add(new ModelBox(this.trophySilverFeatures, 14, 27, -5.0F, -14.0F, 0.0F, 2, 4, 1, 0.0F));
    this.trophySilverFeatures.field_78804_l
      .add(new ModelBox(this.trophySilverFeatures, 14, 27, 3.0F, -14.0F, 0.0F, 2, 4, 1, 0.0F));
    this.trophySilverFeatures.field_78804_l
      .add(new ModelBox(this.trophySilverFeatures, 0, 7, -2.0F, -2.0F, -1.5F, 4, 1, 4, 0.0F));
    this.trophyGoldFeatures = new ModelRenderer(this);
    this.trophyGoldFeatures.func_78793_a(0.0F, -7.0F, 0.0F);
    this.trophySilverFeatures.func_78792_a(this.trophyGoldFeatures);
    this.trophyGoldFeatures.field_78804_l
      .add(new ModelBox(this.trophyGoldFeatures, 20, 0, -1.5F, 1.0F, -1.0F, 3, 1, 3, 0.0F));
    this.trophyGoldFeatures.field_78804_l
      .add(new ModelBox(this.trophyGoldFeatures, 16, 7, -2.0F, -1.0F, -1.5F, 4, 1, 4, 0.0F));
    this.trophyGoldFeatures.field_78804_l
      .add(new ModelBox(this.trophyGoldFeatures, 20, 12, 5.0F, -7.0F, 0.0F, 1, 3, 1, 0.0F));
    this.trophyGoldFeatures.field_78804_l
      .add(new ModelBox(this.trophyGoldFeatures, 20, 12, -6.0F, -7.0F, 0.0F, 1, 3, 1, 0.0F));
    this.trophyLegendaryFeatures = new ModelRenderer(this);
    this.trophyLegendaryFeatures.func_78793_a(0.0F, 0.0F, 0.0F);
    this.trophyGoldFeatures.func_78792_a(this.trophyLegendaryFeatures);
    this.trophyLegendaryFeatures.field_78804_l
      .add(new ModelBox(this.trophyLegendaryFeatures, 0, 0, -3.5F, -9.0F, -3.0F, 1, 2, 1, 0.0F));
    this.trophyLegendaryFeatures.field_78804_l
      .add(new ModelBox(this.trophyLegendaryFeatures, 0, 0, -3.5F, -9.0F, 3.0F, 1, 2, 1, 0.0F));
    this.trophyLegendaryFeatures.field_78804_l
      .add(new ModelBox(this.trophyLegendaryFeatures, 0, 0, 2.5F, -9.0F, 3.0F, 1, 2, 1, 0.0F));
    this.trophyLegendaryFeatures.field_78804_l
      .add(new ModelBox(this.trophyLegendaryFeatures, 0, 0, 2.5F, -9.0F, -3.0F, 1, 2, 1, 0.0F));
    this.trophyLegendaryFeatures.field_78804_l
      .add(new ModelBox(this.trophyLegendaryFeatures, 14, 21, -0.5F, -7.0F, -4.5F, 1, 4, 2, 0.0F));
    this.trophyLegendaryFeatures.field_78804_l
      .add(new ModelBox(this.trophyLegendaryFeatures, 14, 21, -0.5F, -7.0F, 3.5F, 1, 4, 2, 0.0F));
    this.trophyLegendaryFeatures.field_78804_l
      .add(new ModelBox(this.trophyLegendaryFeatures, 16, 12, -0.5F, -7.0F, -5.5F, 1, 3, 1, 0.0F));
    this.trophyLegendaryFeatures.field_78804_l
      .add(new ModelBox(this.trophyLegendaryFeatures, 16, 12, -0.5F, -7.0F, 5.5F, 1, 3, 1, 0.0F));
    this.trophyLegendaryFeatures.field_78804_l
      .add(new ModelBox(this.trophyLegendaryFeatures, 0, 0, 2.5F, 5.0F, -3.0F, 1, 2, 1, 0.0F));
    this.trophyLegendaryFeatures.field_78804_l
      .add(new ModelBox(this.trophyLegendaryFeatures, 0, 0, -3.5F, 5.0F, -3.0F, 1, 2, 1, 0.0F));
    this.trophyLegendaryFeatures.field_78804_l
      .add(new ModelBox(this.trophyLegendaryFeatures, 0, 0, -3.5F, 5.0F, 3.0F, 1, 2, 1, 0.0F));
    this.trophyLegendaryFeatures.field_78804_l
      .add(new ModelBox(this.trophyLegendaryFeatures, 0, 0, 2.5F, 5.0F, 3.0F, 1, 2, 1, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    this.bone.func_78785_a(f5);
    GL11.glDisable(3042);
  }
  
  public void renderAll() {
    this.bone.func_78785_a(0.0625F);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\ModelTrophy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */