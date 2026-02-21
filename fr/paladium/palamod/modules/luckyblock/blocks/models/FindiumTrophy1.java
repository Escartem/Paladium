package fr.paladium.palamod.modules.luckyblock.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class FindiumTrophy1 extends ModelBase {
  private final ModelRenderer BONE;
  
  private final ModelRenderer TROPHY;
  
  private final ModelRenderer CLUSTER;
  
  private final ModelRenderer CLUSTER_PART;
  
  private final ModelRenderer CLUSTER_PART2;
  
  private final ModelRenderer CLUSTER_PART3;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer CLUSTER_PART4;
  
  private final ModelRenderer CLUSTER_PART5;
  
  private final ModelRenderer CLUSTER2;
  
  private final ModelRenderer CLUSTER_PART6;
  
  private final ModelRenderer CLUSTER_PART7;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer CLUSTER_PART8;
  
  private final ModelRenderer CLUSTER_PART9;
  
  private final ModelRenderer CLUSTER_PART10;
  
  private final ModelRenderer cube_r3;
  
  private final ModelRenderer CLUSTER3;
  
  private final ModelRenderer CLUSTER_PART11;
  
  private final ModelRenderer CLUSTER_PART12;
  
  private final ModelRenderer CLUSTER_PART13;
  
  private final ModelRenderer CLUSTER_PART14;
  
  private final ModelRenderer CLUSTER_PART15;
  
  public FindiumTrophy1() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.BONE = new ModelRenderer(this);
    this.BONE.func_78793_a(0.0F, 24.0F, 0.0F);
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 0, -4.0F, -1.0F, -4.0F, 8, 1, 8, 0.0F));
    this.TROPHY = new ModelRenderer(this);
    this.TROPHY.func_78793_a(4.0F, -5.0F, -4.0F);
    this.BONE.func_78792_a(this.TROPHY);
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 0, 9, -4.5F, 0.0F, 3.5F, 1, 3, 1, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 12, 12, -6.0F, 3.0F, 2.0F, 4, 1, 4, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 0, 9, -6.0F, -2.0F, 2.0F, 4, 1, 4, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 24, 0, -6.0F, -6.0F, 2.0F, 4, 4, 1, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 24, 0, -6.0F, -6.0F, 5.0F, 4, 4, 1, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 0, 25, -6.0F, -6.0F, 3.0F, 1, 4, 2, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 0, 25, -3.0F, -6.0F, 3.0F, 1, 4, 2, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 12, 9, -5.0F, -1.0F, 3.0F, 2, 1, 2, 0.0F));
    this.CLUSTER = new ModelRenderer(this);
    this.CLUSTER.func_78793_a(-2.25F, 22.5833F, 1.9167F);
    setRotationAngle(this.CLUSTER, -0.5236F, 0.5672F, 0.0436F);
    this.CLUSTER.field_78804_l.add(new ModelBox(this.CLUSTER, 0, 14, -0.7868F, -9.4368F, -1.4365F, 2, 9, 2, 0.0F));
    this.CLUSTER_PART = new ModelRenderer(this);
    this.CLUSTER_PART.func_78793_a(0.25F, -0.0833F, 0.0833F);
    this.CLUSTER.func_78792_a(this.CLUSTER_PART);
    setRotationAngle(this.CLUSTER_PART, 0.0F, 0.0F, -0.3927F);
    this.CLUSTER_PART.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART, 22, 24, -1.0F, -4.5F, -1.0F, 2, 5, 2, 0.0F));
    this.CLUSTER_PART2 = new ModelRenderer(this);
    this.CLUSTER_PART2.func_78793_a(0.25F, -0.0833F, 0.0833F);
    this.CLUSTER.func_78792_a(this.CLUSTER_PART2);
    setRotationAngle(this.CLUSTER_PART2, 0.5672F, 0.0F, 0.0F);
    this.CLUSTER_PART2.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART2, 0, 0, -1.0F, -4.5F, -1.0F, 2, 5, 2, 0.0F));
    this.CLUSTER_PART3 = new ModelRenderer(this);
    this.CLUSTER_PART3.func_78793_a(0.25F, -0.0833F, 0.0833F);
    this.CLUSTER.func_78792_a(this.CLUSTER_PART3);
    setRotationAngle(this.CLUSTER_PART3, -0.2182F, 0.0F, 0.6545F);
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(1.0F, 0.5F, -1.0F);
    this.CLUSTER_PART3.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 0.0F, -0.0873F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 8, 17, -2.0F, -8.0F, 0.0F, 2, 8, 2, 0.0F));
    this.CLUSTER_PART4 = new ModelRenderer(this);
    this.CLUSTER_PART4.func_78793_a(0.25F, -0.0833F, 0.0833F);
    this.CLUSTER.func_78792_a(this.CLUSTER_PART4);
    setRotationAngle(this.CLUSTER_PART4, -0.4363F, 0.0F, -0.2182F);
    this.CLUSTER_PART4.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART4, 16, 17, -1.0F, -6.5F, -1.0F, 2, 7, 2, 0.0F));
    this.CLUSTER_PART5 = new ModelRenderer(this);
    this.CLUSTER_PART5.func_78793_a(0.25F, -0.0833F, 0.0833F);
    this.CLUSTER.func_78792_a(this.CLUSTER_PART5);
    setRotationAngle(this.CLUSTER_PART5, -1.0472F, 0.0F, 0.0436F);
    this.CLUSTER_PART5.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART5, 24, 17, -1.0F, -3.5F, -1.0F, 2, 4, 2, 0.0F));
    this.CLUSTER2 = new ModelRenderer(this);
    this.CLUSTER2.func_78793_a(-2.25F, 23.5833F, -2.0833F);
    setRotationAngle(this.CLUSTER2, -0.7854F, 0.2618F, -0.4363F);
    this.CLUSTER2.field_78804_l
      .add(new ModelBox(this.CLUSTER2, 0, 14, -0.3418F, -9.3015F, -1.4802F, 2, 9, 2, 0.0F));
    this.CLUSTER_PART6 = new ModelRenderer(this);
    this.CLUSTER_PART6.func_78793_a(0.25F, -0.0833F, 0.0833F);
    this.CLUSTER2.func_78792_a(this.CLUSTER_PART6);
    setRotationAngle(this.CLUSTER_PART6, 0.0F, 0.0F, -0.3927F);
    this.CLUSTER_PART6.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART6, 22, 24, -0.348F, -5.0073F, -1.5635F, 2, 5, 2, 0.0F));
    this.CLUSTER_PART7 = new ModelRenderer(this);
    this.CLUSTER_PART7.func_78793_a(0.25F, -0.0833F, 0.0833F);
    this.CLUSTER2.func_78792_a(this.CLUSTER_PART7);
    setRotationAngle(this.CLUSTER_PART7, 0.5672F, 0.0F, 0.0F);
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(1.0F, 0.5F, -1.0F);
    this.CLUSTER_PART7.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, 0.0F, -0.1309F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 0, 0, -1.5918F, -5.9085F, -0.0894F, 2, 5, 2, 0.0F));
    this.CLUSTER_PART8 = new ModelRenderer(this);
    this.CLUSTER_PART8.func_78793_a(0.25F, -0.0833F, 0.0833F);
    this.CLUSTER2.func_78792_a(this.CLUSTER_PART8);
    setRotationAngle(this.CLUSTER_PART8, 0.0873F, 0.0F, 0.2618F);
    this.CLUSTER_PART8.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART8, 8, 17, -1.1134F, -8.1769F, -1.7273F, 2, 8, 2, 0.0F));
    this.CLUSTER_PART9 = new ModelRenderer(this);
    this.CLUSTER_PART9.func_78793_a(0.25F, -0.0833F, 0.0833F);
    this.CLUSTER2.func_78792_a(this.CLUSTER_PART9);
    setRotationAngle(this.CLUSTER_PART9, -0.4363F, 0.0F, -0.2182F);
    this.CLUSTER_PART9.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART9, 16, 17, -0.446F, -6.8173F, -1.7697F, 2, 7, 2, 0.0F));
    this.CLUSTER_PART10 = new ModelRenderer(this);
    this.CLUSTER_PART10.func_78793_a(0.25F, -0.0833F, 0.0833F);
    this.CLUSTER2.func_78792_a(this.CLUSTER_PART10);
    setRotationAngle(this.CLUSTER_PART10, -1.0472F, 0.0F, 0.0436F);
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(1.0F, 0.5F, -1.0F);
    this.CLUSTER_PART10.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, 0.6981F, 0.0F, 0.3491F);
    this.cube_r3.field_78804_l
      .add(new ModelBox(this.cube_r3, 24, 17, -1.9013F, -4.1506F, -0.1734F, 2, 4, 2, 0.0F));
    this.CLUSTER3 = new ModelRenderer(this);
    this.CLUSTER3.func_78793_a(-0.25F, 23.0833F, -2.0833F);
    setRotationAngle(this.CLUSTER3, 0.48F, 1.0472F, -0.5672F);
    this.CLUSTER_PART11 = new ModelRenderer(this);
    this.CLUSTER_PART11.func_78793_a(0.25F, -0.0833F, 0.0833F);
    this.CLUSTER3.func_78792_a(this.CLUSTER_PART11);
    setRotationAngle(this.CLUSTER_PART11, 0.0F, 0.0F, -0.3927F);
    this.CLUSTER_PART11.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART11, 22, 24, -0.7739F, -4.6949F, -0.5989F, 2, 5, 2, 0.0F));
    this.CLUSTER_PART12 = new ModelRenderer(this);
    this.CLUSTER_PART12.func_78793_a(0.25F, -0.0833F, 0.0833F);
    this.CLUSTER3.func_78792_a(this.CLUSTER_PART12);
    setRotationAngle(this.CLUSTER_PART12, 0.5672F, 0.0F, 0.0F);
    this.CLUSTER_PART12.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART12, 0, 0, -1.3772F, -3.1705F, -0.6094F, 2, 5, 2, 0.0F));
    this.CLUSTER_PART13 = new ModelRenderer(this);
    this.CLUSTER_PART13.func_78793_a(0.25F, -0.0833F, 0.0833F);
    this.CLUSTER3.func_78792_a(this.CLUSTER_PART13);
    setRotationAngle(this.CLUSTER_PART13, -0.2182F, 0.0F, 0.6545F);
    this.CLUSTER_PART14 = new ModelRenderer(this);
    this.CLUSTER_PART14.func_78793_a(0.25F, -0.0833F, 0.0833F);
    this.CLUSTER3.func_78792_a(this.CLUSTER_PART14);
    setRotationAngle(this.CLUSTER_PART14, -0.4363F, 0.0F, -0.2182F);
    this.CLUSTER_PART14.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART14, 16, 17, -0.9056F, -6.6895F, -0.8671F, 2, 7, 2, 0.0F));
    this.CLUSTER_PART15 = new ModelRenderer(this);
    this.CLUSTER_PART15.func_78793_a(0.25F, -0.0833F, 0.0833F);
    this.CLUSTER3.func_78792_a(this.CLUSTER_PART15);
    setRotationAngle(this.CLUSTER_PART15, -1.0472F, 0.0F, 0.0436F);
    this.CLUSTER_PART15.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART15, 24, 17, -0.7862F, -3.4375F, -1.1136F, 2, 4, 2, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float limbSwing, float limbSwingAmount, float liveTick, float headYaw, float headPitch, float scaleFactor) {
    func_78087_a(limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor, entity);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    super.func_78088_a(entity, limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor);
    this.BONE.func_78785_a(scaleFactor);
    this.CLUSTER.func_78785_a(scaleFactor);
    this.CLUSTER2.func_78785_a(scaleFactor);
    this.CLUSTER3.func_78785_a(scaleFactor);
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\FindiumTrophy1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */