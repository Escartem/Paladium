package fr.paladium.palamod.modules.luckyblock.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class FindiumTrophy3 extends ModelBase {
  private final ModelRenderer BONE;
  
  private final ModelRenderer CLUSTER4;
  
  private final ModelRenderer CLUSTER_PART13;
  
  private final ModelRenderer CLUSTER_PART14;
  
  private final ModelRenderer CLUSTER_PART15;
  
  private final ModelRenderer CLUSTER_PART16;
  
  private final ModelRenderer CLUSTER3;
  
  private final ModelRenderer CLUSTER_PART9;
  
  private final ModelRenderer CLUSTER_PART10;
  
  private final ModelRenderer CLUSTER_PART11;
  
  private final ModelRenderer CLUSTER_PART12;
  
  private final ModelRenderer CLUSTER2;
  
  private final ModelRenderer CLUSTER_PART5;
  
  private final ModelRenderer CLUSTER_PART6;
  
  private final ModelRenderer CLUSTER_PART7;
  
  private final ModelRenderer CLUSTER_PART8;
  
  private final ModelRenderer CLUSTER;
  
  private final ModelRenderer CLUSTER_PART;
  
  private final ModelRenderer CLUSTER_PART4;
  
  private final ModelRenderer CLUSTER_PART2;
  
  private final ModelRenderer CLUSTER_PART3;
  
  private final ModelRenderer TROPHY;
  
  private final ModelRenderer TROPHY_PART;
  
  public FindiumTrophy3() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.BONE = new ModelRenderer(this);
    this.BONE.func_78793_a(0.0F, 24.0F, 0.0F);
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 0, -4.0F, -1.0F, -4.0F, 8, 1, 8, 0.0F));
    this.CLUSTER4 = new ModelRenderer(this);
    this.CLUSTER4.func_78793_a(1.0F, -1.0F, 1.0F);
    this.BONE.func_78792_a(this.CLUSTER4);
    setRotationAngle(this.CLUSTER4, 0.1309F, 0.0F, -0.4363F);
    this.CLUSTER4.field_78804_l.add(new ModelBox(this.CLUSTER4, 0, 9, -2.0F, -12.0F, -1.0F, 2, 12, 2, 0.0F));
    this.CLUSTER_PART13 = new ModelRenderer(this);
    this.CLUSTER_PART13.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.CLUSTER4.func_78792_a(this.CLUSTER_PART13);
    setRotationAngle(this.CLUSTER_PART13, 0.0F, 0.0F, -0.3927F);
    this.CLUSTER_PART13.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART13, 16, 19, -1.0F, -6.0F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART14 = new ModelRenderer(this);
    this.CLUSTER_PART14.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.CLUSTER4.func_78792_a(this.CLUSTER_PART14);
    setRotationAngle(this.CLUSTER_PART14, -0.48F, 0.0F, 0.2182F);
    this.CLUSTER_PART14.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART14, 0, 0, -1.0F, -6.0F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART15 = new ModelRenderer(this);
    this.CLUSTER_PART15.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.CLUSTER4.func_78792_a(this.CLUSTER_PART15);
    setRotationAngle(this.CLUSTER_PART15, 0.1745F, 0.0F, 0.2618F);
    this.CLUSTER_PART15.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART15, 8, 19, -1.0F, -8.0F, -1.0F, 2, 8, 2, 0.0F));
    this.CLUSTER_PART16 = new ModelRenderer(this);
    this.CLUSTER_PART16.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.CLUSTER4.func_78792_a(this.CLUSTER_PART16);
    setRotationAngle(this.CLUSTER_PART16, 0.2618F, 0.0F, -0.1745F);
    this.CLUSTER_PART16.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART16, 22, 12, -1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F));
    this.CLUSTER3 = new ModelRenderer(this);
    this.CLUSTER3.func_78793_a(1.0F, -1.0F, 1.0F);
    this.BONE.func_78792_a(this.CLUSTER3);
    setRotationAngle(this.CLUSTER3, -0.2182F, 0.0F, -0.1309F);
    this.CLUSTER3.field_78804_l.add(new ModelBox(this.CLUSTER3, 0, 9, -2.0F, -12.0F, -1.0F, 2, 12, 2, 0.0F));
    this.CLUSTER_PART9 = new ModelRenderer(this);
    this.CLUSTER_PART9.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.CLUSTER3.func_78792_a(this.CLUSTER_PART9);
    setRotationAngle(this.CLUSTER_PART9, 0.0F, 0.0F, -0.3927F);
    this.CLUSTER_PART9.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART9, 16, 19, -1.0F, -6.0F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART10 = new ModelRenderer(this);
    this.CLUSTER_PART10.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.CLUSTER3.func_78792_a(this.CLUSTER_PART10);
    setRotationAngle(this.CLUSTER_PART10, -0.48F, 0.0F, 0.2182F);
    this.CLUSTER_PART10.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART10, 0, 0, -1.0F, -6.0F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART11 = new ModelRenderer(this);
    this.CLUSTER_PART11.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.CLUSTER3.func_78792_a(this.CLUSTER_PART11);
    setRotationAngle(this.CLUSTER_PART11, 0.1745F, 0.0F, 0.2618F);
    this.CLUSTER_PART11.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART11, 8, 19, -1.0F, -8.0F, -1.0F, 2, 8, 2, 0.0F));
    this.CLUSTER_PART12 = new ModelRenderer(this);
    this.CLUSTER_PART12.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.CLUSTER3.func_78792_a(this.CLUSTER_PART12);
    setRotationAngle(this.CLUSTER_PART12, 0.2618F, 0.0F, -0.1745F);
    this.CLUSTER_PART12.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART12, 22, 12, -1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F));
    this.CLUSTER2 = new ModelRenderer(this);
    this.CLUSTER2.func_78793_a(-1.0F, -1.0F, -1.0F);
    this.BONE.func_78792_a(this.CLUSTER2);
    setRotationAngle(this.CLUSTER2, 0.1745F, 0.0F, -0.1309F);
    this.CLUSTER2.field_78804_l.add(new ModelBox(this.CLUSTER2, 0, 9, -2.0F, -12.0F, -1.0F, 2, 12, 2, 0.0F));
    this.CLUSTER_PART5 = new ModelRenderer(this);
    this.CLUSTER_PART5.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.CLUSTER2.func_78792_a(this.CLUSTER_PART5);
    setRotationAngle(this.CLUSTER_PART5, 0.0F, 0.0F, -0.3927F);
    this.CLUSTER_PART5.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART5, 16, 19, -1.0F, -6.0F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART6 = new ModelRenderer(this);
    this.CLUSTER_PART6.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.CLUSTER2.func_78792_a(this.CLUSTER_PART6);
    setRotationAngle(this.CLUSTER_PART6, -0.48F, 0.0F, 0.2182F);
    this.CLUSTER_PART6.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART6, 0, 0, -1.0F, -6.0F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART7 = new ModelRenderer(this);
    this.CLUSTER_PART7.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.CLUSTER2.func_78792_a(this.CLUSTER_PART7);
    setRotationAngle(this.CLUSTER_PART7, 0.1745F, 0.0F, 0.2618F);
    this.CLUSTER_PART7.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART7, 8, 19, -1.0F, -8.0F, -1.0F, 2, 8, 2, 0.0F));
    this.CLUSTER_PART8 = new ModelRenderer(this);
    this.CLUSTER_PART8.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.CLUSTER2.func_78792_a(this.CLUSTER_PART8);
    setRotationAngle(this.CLUSTER_PART8, 0.2618F, 0.0F, -0.1745F);
    this.CLUSTER_PART8.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART8, 22, 12, -1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F));
    this.CLUSTER = new ModelRenderer(this);
    this.CLUSTER.func_78793_a(2.0F, -1.0F, -1.0F);
    this.BONE.func_78792_a(this.CLUSTER);
    setRotationAngle(this.CLUSTER, 0.0F, 0.0F, 0.1309F);
    this.CLUSTER.field_78804_l.add(new ModelBox(this.CLUSTER, 0, 9, -2.0F, -12.0F, -1.0F, 2, 12, 2, 0.0F));
    this.CLUSTER_PART = new ModelRenderer(this);
    this.CLUSTER_PART.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.CLUSTER.func_78792_a(this.CLUSTER_PART);
    setRotationAngle(this.CLUSTER_PART, 0.0F, 0.0F, -0.3927F);
    this.CLUSTER_PART.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART, 16, 19, -1.0F, -6.0F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART4 = new ModelRenderer(this);
    this.CLUSTER_PART4.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.CLUSTER.func_78792_a(this.CLUSTER_PART4);
    setRotationAngle(this.CLUSTER_PART4, -0.48F, 0.0F, 0.2182F);
    this.CLUSTER_PART4.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART4, 0, 0, -1.0F, -6.0F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART2 = new ModelRenderer(this);
    this.CLUSTER_PART2.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.CLUSTER.func_78792_a(this.CLUSTER_PART2);
    setRotationAngle(this.CLUSTER_PART2, 0.1745F, 0.0F, 0.2618F);
    this.CLUSTER_PART2.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART2, 8, 19, -1.0F, -8.0F, -1.0F, 2, 8, 2, 0.0F));
    this.CLUSTER_PART3 = new ModelRenderer(this);
    this.CLUSTER_PART3.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.CLUSTER.func_78792_a(this.CLUSTER_PART3);
    setRotationAngle(this.CLUSTER_PART3, 0.2618F, 0.0F, -0.1745F);
    this.CLUSTER_PART3.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART3, 22, 12, -1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F));
    this.TROPHY = new ModelRenderer(this);
    this.TROPHY.func_78793_a(0.0F, -11.25F, -0.5F);
    this.BONE.func_78792_a(this.TROPHY);
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 14, 27, -1.5F, 3.0F, -0.5F, 2, 1, 2, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 24, 4, -1.5F, 0.0F, -0.5F, 2, 1, 2, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 20, 9, -1.5F, -3.0F, -0.5F, 2, 1, 2, 0.0F));
    this.TROPHY_PART = new ModelRenderer(this);
    this.TROPHY_PART.func_78793_a(-0.5F, 5.0F, 0.5F);
    this.TROPHY.func_78792_a(this.TROPHY_PART);
    setRotationAngle(this.TROPHY_PART, 0.0F, -0.7854F, 0.0F);
    this.TROPHY_PART.field_78804_l.add(new ModelBox(this.TROPHY_PART, 8, 14, -0.5F, -4.0F, -0.5F, 1, 2, 1, 0.0F));
    this.TROPHY_PART.field_78804_l.add(new ModelBox(this.TROPHY_PART, 8, 9, -0.5F, -7.0F, -0.5F, 1, 2, 1, 0.0F));
    this.TROPHY_PART.field_78804_l.add(new ModelBox(this.TROPHY_PART, 8, 9, -2.0F, -9.0F, -2.0F, 4, 1, 4, 0.0F));
    this.TROPHY_PART.field_78804_l.add(new ModelBox(this.TROPHY_PART, 24, 0, -2.0F, -12.0F, -2.0F, 4, 3, 1, 0.0F));
    this.TROPHY_PART.field_78804_l.add(new ModelBox(this.TROPHY_PART, 24, 24, -2.0F, -12.0F, 1.0F, 4, 3, 1, 0.0F));
    this.TROPHY_PART.field_78804_l.add(new ModelBox(this.TROPHY_PART, 0, 23, -2.0F, -12.0F, -1.0F, 1, 3, 2, 0.0F));
    this.TROPHY_PART.field_78804_l.add(new ModelBox(this.TROPHY_PART, 24, 18, 1.0F, -12.0F, -1.0F, 1, 3, 2, 0.0F));
    this.TROPHY_PART.field_78804_l.add(new ModelBox(this.TROPHY_PART, 8, 14, -2.0F, -1.0F, -2.0F, 4, 1, 4, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\FindiumTrophy3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */