package fr.paladium.palamod.modules.luckyblock.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class FindiumTrophy0 extends ModelBase {
  private final ModelRenderer BONE;
  
  private final ModelRenderer TROPHY;
  
  private final ModelRenderer CLUSTER;
  
  private final ModelRenderer CLUSTER_PART;
  
  private final ModelRenderer CLUSTER_PART2;
  
  private final ModelRenderer CLUSTER_PART3;
  
  private final ModelRenderer CLUSTER2;
  
  private final ModelRenderer CLUSTER_PART4;
  
  private final ModelRenderer CLUSTER_PART5;
  
  private final ModelRenderer CLUSTER_PART6;
  
  private final ModelRenderer CLUSTER3;
  
  private final ModelRenderer CLUSTER_PART7;
  
  private final ModelRenderer CLUSTER_PART8;
  
  private final ModelRenderer CLUSTER_PART9;
  
  public FindiumTrophy0() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.BONE = new ModelRenderer(this);
    this.BONE.func_78793_a(0.0F, 24.0F, 0.0F);
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 23, -4.0F, -1.0F, -4.0F, 8, 1, 8, 0.0F));
    this.TROPHY = new ModelRenderer(this);
    this.TROPHY.func_78793_a(0.0F, -4.0F, 0.0F);
    this.BONE.func_78792_a(this.TROPHY);
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 0, 0, -3.0F, 2.0F, -3.0F, 6, 1, 6, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 0, 0, -3.0F, -2.0F, -3.0F, 6, 1, 6, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 0, 8, -3.0F, -5.0F, -3.0F, 6, 3, 1, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 0, 13, -3.0F, -5.0F, -2.0F, 1, 3, 4, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 0, 13, 2.0F, -5.0F, -2.0F, 1, 3, 4, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 0, 8, -3.0F, -5.0F, 2.0F, 6, 3, 1, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 24, 0, -1.0F, -1.0F, -1.0F, 2, 3, 2, 0.0F));
    this.CLUSTER = new ModelRenderer(this);
    this.CLUSTER.func_78793_a(-3.0F, -1.0F, 1.0F);
    this.BONE.func_78792_a(this.CLUSTER);
    setRotationAngle(this.CLUSTER, 0.0F, 0.6545F, -0.3927F);
    this.CLUSTER.field_78804_l.add(new ModelBox(this.CLUSTER, 24, 13, -1.0F, -7.0F, -1.0F, 2, 7, 2, 0.0F));
    this.CLUSTER_PART = new ModelRenderer(this);
    this.CLUSTER_PART.func_78793_a(0.0F, 0.0F, 0.0F);
    this.CLUSTER.func_78792_a(this.CLUSTER_PART);
    setRotationAngle(this.CLUSTER_PART, -0.6545F, 0.0F, -0.9599F);
    this.CLUSTER_PART.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART, 15, 8, -1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F));
    this.CLUSTER_PART2 = new ModelRenderer(this);
    this.CLUSTER_PART2.func_78793_a(0.0F, 0.0F, 0.0F);
    this.CLUSTER.func_78792_a(this.CLUSTER_PART2);
    setRotationAngle(this.CLUSTER_PART2, -0.0436F, 0.0F, 0.6981F);
    this.CLUSTER_PART2.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART2, 24, 5, -1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F));
    this.CLUSTER_PART3 = new ModelRenderer(this);
    this.CLUSTER_PART3.func_78793_a(0.0F, 0.0F, 0.0F);
    this.CLUSTER.func_78792_a(this.CLUSTER_PART3);
    setRotationAngle(this.CLUSTER_PART3, 1.5708F, 1.1345F, 1.2217F);
    this.CLUSTER_PART3.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART3, 15, 15, -1.3353F, -5.1119F, -0.9927F, 2, 5, 2, 0.0F));
    this.CLUSTER2 = new ModelRenderer(this);
    this.CLUSTER2.func_78793_a(2.0F, -1.0F, -2.0F);
    this.BONE.func_78792_a(this.CLUSTER2);
    setRotationAngle(this.CLUSTER2, -0.48F, 1.6581F, 0.0F);
    this.CLUSTER2.field_78804_l.add(new ModelBox(this.CLUSTER2, 24, 13, -1.0F, -7.0F, -1.0F, 2, 7, 2, 0.0F));
    this.CLUSTER_PART4 = new ModelRenderer(this);
    this.CLUSTER_PART4.func_78793_a(0.0F, -0.887F, -0.4617F);
    this.CLUSTER2.func_78792_a(this.CLUSTER_PART4);
    setRotationAngle(this.CLUSTER_PART4, -0.6545F, 0.0F, -0.7418F);
    this.CLUSTER_PART4.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART4, 15, 8, -1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F));
    this.CLUSTER_PART5 = new ModelRenderer(this);
    this.CLUSTER_PART5.func_78793_a(0.0F, 0.0F, 0.0F);
    this.CLUSTER2.func_78792_a(this.CLUSTER_PART5);
    setRotationAngle(this.CLUSTER_PART5, -0.0436F, 0.0F, 0.6981F);
    this.CLUSTER_PART5.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART5, 24, 5, -1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F));
    this.CLUSTER_PART6 = new ModelRenderer(this);
    this.CLUSTER_PART6.func_78793_a(0.0F, 0.0F, 0.0F);
    this.CLUSTER2.func_78792_a(this.CLUSTER_PART6);
    setRotationAngle(this.CLUSTER_PART6, 1.5708F, 1.1345F, 1.2217F);
    this.CLUSTER_PART6.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART6, 15, 15, -1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F));
    this.CLUSTER3 = new ModelRenderer(this);
    this.CLUSTER3.func_78793_a(-1.0F, -1.0F, 1.0F);
    this.BONE.func_78792_a(this.CLUSTER3);
    setRotationAngle(this.CLUSTER3, -1.309F, 0.9599F, 0.0F);
    this.CLUSTER3.field_78804_l.add(new ModelBox(this.CLUSTER3, 24, 13, -1.0F, -7.0F, -1.0F, 2, 7, 2, 0.0F));
    this.CLUSTER_PART7 = new ModelRenderer(this);
    this.CLUSTER_PART7.func_78793_a(0.0F, 0.0F, 0.0F);
    this.CLUSTER3.func_78792_a(this.CLUSTER_PART7);
    setRotationAngle(this.CLUSTER_PART7, 0.0F, 0.0F, -0.9599F);
    this.CLUSTER_PART7.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART7, 15, 8, -0.788F, -4.1485F, -1.9659F, 2, 4, 2, 0.0F));
    this.CLUSTER_PART8 = new ModelRenderer(this);
    this.CLUSTER_PART8.func_78793_a(0.0F, 0.0F, 0.0F);
    this.CLUSTER3.func_78792_a(this.CLUSTER_PART8);
    setRotationAngle(this.CLUSTER_PART8, -0.0436F, 0.0F, 0.6981F);
    this.CLUSTER_PART8.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART8, 24, 5, -1.1664F, -5.1559F, -1.9737F, 2, 5, 2, 0.0F));
    this.CLUSTER_PART9 = new ModelRenderer(this);
    this.CLUSTER_PART9.func_78793_a(0.0F, 0.0F, 0.0F);
    this.CLUSTER3.func_78792_a(this.CLUSTER_PART9);
    setRotationAngle(this.CLUSTER_PART9, 1.5708F, 1.1345F, 1.2217F);
    this.CLUSTER_PART9.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART9, 15, 15, -1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F));
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
  
  public void renderAll(int state) {
    this.BONE.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\FindiumTrophy0.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */