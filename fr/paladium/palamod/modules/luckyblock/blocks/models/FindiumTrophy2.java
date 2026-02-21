package fr.paladium.palamod.modules.luckyblock.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class FindiumTrophy2 extends ModelBase {
  private final ModelRenderer BONE;
  
  private final ModelRenderer TROPHY;
  
  private final ModelRenderer CLUSTER;
  
  private final ModelRenderer CLUSTER_PART;
  
  private final ModelRenderer CLUSTER_PART2;
  
  private final ModelRenderer CLUSTER_PART3;
  
  private final ModelRenderer CLUSTER_PART4;
  
  private final ModelRenderer CLUSTER_PART5;
  
  private final ModelRenderer CLUSTER2;
  
  private final ModelRenderer CLUSTER_PART7;
  
  private final ModelRenderer CLUSTER_PART8;
  
  private final ModelRenderer CLUSTER_PART9;
  
  private final ModelRenderer CLUSTER_PART10;
  
  private final ModelRenderer CLUSTER3;
  
  private final ModelRenderer CLUSTER_PART6;
  
  private final ModelRenderer CLUSTER_PART11;
  
  private final ModelRenderer CLUSTER_PART12;
  
  private final ModelRenderer CLUSTER_PART13;
  
  private final ModelRenderer CLUSTER4;
  
  private final ModelRenderer CLUSTER_PART14;
  
  private final ModelRenderer CLUSTER_PART15;
  
  private final ModelRenderer CLUSTER_PART16;
  
  private final ModelRenderer CLUSTER_PART17;
  
  private final ModelRenderer CLUSTER5;
  
  private final ModelRenderer CLUSTER_PART18;
  
  private final ModelRenderer CLUSTER_PART19;
  
  private final ModelRenderer CLUSTER_PART20;
  
  private final ModelRenderer CLUSTER_PART21;
  
  private final ModelRenderer CLUSTER_PART22;
  
  private final ModelRenderer CLUSTER6;
  
  private final ModelRenderer CLUSTER_PART23;
  
  private final ModelRenderer CLUSTER_PART24;
  
  private final ModelRenderer CLUSTER_PART25;
  
  private final ModelRenderer CLUSTER_PART26;
  
  private final ModelRenderer CLUSTER7;
  
  private final ModelRenderer CLUSTER_PART27;
  
  private final ModelRenderer CLUSTER_PART28;
  
  private final ModelRenderer CLUSTER_PART29;
  
  private final ModelRenderer CLUSTER_PART30;
  
  private final ModelRenderer CLUSTER8;
  
  private final ModelRenderer CLUSTER_PART31;
  
  private final ModelRenderer CLUSTER_PART32;
  
  private final ModelRenderer CLUSTER_PART33;
  
  private final ModelRenderer CLUSTER_PART34;
  
  private final ModelRenderer CLUSTER_PART35;
  
  public FindiumTrophy2() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.BONE = new ModelRenderer(this);
    this.BONE.func_78793_a(0.0F, 24.0F, 0.0F);
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 0, -5.0F, -1.0F, -5.0F, 10, 1, 10, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 11, -4.0F, -2.0F, -4.0F, 8, 1, 8, 0.0F));
    this.TROPHY = new ModelRenderer(this);
    this.TROPHY.func_78793_a(-0.5F, -4.0F, -0.5F);
    this.BONE.func_78792_a(this.TROPHY);
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 20, 21, -1.5F, 1.0F, -1.5F, 4, 1, 4, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 8, 20, -1.5F, -5.0F, -1.5F, 4, 1, 4, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 8, 25, -2.5F, -8.0F, -1.5F, 1, 3, 4, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 24, 11, 2.5F, -8.0F, -1.5F, 1, 3, 4, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 22, 34, -1.5F, -8.0F, 2.5F, 4, 3, 1, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 30, 11, -1.5F, -8.0F, -2.5F, 4, 3, 1, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 24, 11, -3.5F, -7.0F, -0.25F, 1, 2, 1, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 20, 20, 3.5F, -7.0F, -0.25F, 1, 2, 1, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 34, 27, -0.5F, 0.0F, -0.5F, 2, 1, 2, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 34, 24, -0.5F, -4.0F, -0.5F, 2, 1, 2, 0.0F));
    this.TROPHY.field_78804_l.add(new ModelBox(this.TROPHY, 8, 20, 0.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F));
    this.CLUSTER = new ModelRenderer(this);
    this.CLUSTER.func_78793_a(2.0F, -3.0F, 2.0F);
    this.BONE.func_78792_a(this.CLUSTER);
    setRotationAngle(this.CLUSTER, -0.2618F, 0.0F, 0.2618F);
    this.CLUSTER.field_78804_l.add(new ModelBox(this.CLUSTER, 0, 20, -2.0F, -11.0F, 0.0F, 2, 11, 2, 0.0F));
    this.CLUSTER_PART = new ModelRenderer(this);
    this.CLUSTER_PART.func_78793_a(-1.0F, -0.5F, 1.0F);
    this.CLUSTER.func_78792_a(this.CLUSTER_PART);
    setRotationAngle(this.CLUSTER_PART, -0.2618F, 0.0F, -0.3054F);
    this.CLUSTER_PART.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART, 32, 16, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART2 = new ModelRenderer(this);
    this.CLUSTER_PART2.func_78793_a(-1.0F, -0.5F, 1.0F);
    this.CLUSTER.func_78792_a(this.CLUSTER_PART2);
    setRotationAngle(this.CLUSTER_PART2, -0.2618F, 0.0F, 0.6981F);
    this.CLUSTER_PART2.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART2, 32, 32, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART3 = new ModelRenderer(this);
    this.CLUSTER_PART3.func_78793_a(-1.0F, -0.5F, 1.0F);
    this.CLUSTER.func_78792_a(this.CLUSTER_PART3);
    setRotationAngle(this.CLUSTER_PART3, -0.4363F, 0.0F, 0.2618F);
    this.CLUSTER_PART3.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART3, 6, 32, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART4 = new ModelRenderer(this);
    this.CLUSTER_PART4.func_78793_a(-1.0F, -0.5F, 1.0F);
    this.CLUSTER.func_78792_a(this.CLUSTER_PART4);
    setRotationAngle(this.CLUSTER_PART4, 0.2182F, 0.0F, 0.5236F);
    this.CLUSTER_PART4.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART4, 0, 0, -1.0F, -7.5F, -1.0F, 2, 8, 2, 0.0F));
    this.CLUSTER_PART5 = new ModelRenderer(this);
    this.CLUSTER_PART5.func_78793_a(-1.0F, -0.5F, 1.0F);
    this.CLUSTER.func_78792_a(this.CLUSTER_PART5);
    setRotationAngle(this.CLUSTER_PART5, 0.2182F, 0.0F, -0.1745F);
    this.CLUSTER_PART5.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART5, 30, 0, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER2 = new ModelRenderer(this);
    this.CLUSTER2.func_78793_a(-2.25F, -1.0F, -1.0F);
    this.BONE.func_78792_a(this.CLUSTER2);
    setRotationAngle(this.CLUSTER2, 0.0F, -1.6144F, 0.0F);
    this.CLUSTER_PART7 = new ModelRenderer(this);
    this.CLUSTER_PART7.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER2.func_78792_a(this.CLUSTER_PART7);
    setRotationAngle(this.CLUSTER_PART7, -0.2618F, 0.0F, 0.6981F);
    this.CLUSTER_PART7.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART7, 26, 26, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART8 = new ModelRenderer(this);
    this.CLUSTER_PART8.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER2.func_78792_a(this.CLUSTER_PART8);
    setRotationAngle(this.CLUSTER_PART8, -0.4363F, 0.0F, 0.2618F);
    this.CLUSTER_PART8.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART8, 18, 26, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART9 = new ModelRenderer(this);
    this.CLUSTER_PART9.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER2.func_78792_a(this.CLUSTER_PART9);
    setRotationAngle(this.CLUSTER_PART9, 0.2182F, 0.0F, 0.5236F);
    this.CLUSTER_PART9.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART9, 14, 34, -1.0F, -3.5F, -1.0F, 2, 4, 2, 0.0F));
    this.CLUSTER_PART10 = new ModelRenderer(this);
    this.CLUSTER_PART10.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER2.func_78792_a(this.CLUSTER_PART10);
    setRotationAngle(this.CLUSTER_PART10, 0.2182F, 0.0F, -0.1745F);
    this.CLUSTER_PART10.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART10, 0, 11, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER3 = new ModelRenderer(this);
    this.CLUSTER3.func_78793_a(1.75F, -0.75F, 3.0F);
    this.BONE.func_78792_a(this.CLUSTER3);
    this.CLUSTER_PART6 = new ModelRenderer(this);
    this.CLUSTER_PART6.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER3.func_78792_a(this.CLUSTER_PART6);
    setRotationAngle(this.CLUSTER_PART6, -0.2618F, 0.0F, 0.6981F);
    this.CLUSTER_PART6.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART6, 26, 26, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART11 = new ModelRenderer(this);
    this.CLUSTER_PART11.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER3.func_78792_a(this.CLUSTER_PART11);
    setRotationAngle(this.CLUSTER_PART11, -0.4363F, 0.0F, 0.2618F);
    this.CLUSTER_PART11.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART11, 18, 26, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART12 = new ModelRenderer(this);
    this.CLUSTER_PART12.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER3.func_78792_a(this.CLUSTER_PART12);
    setRotationAngle(this.CLUSTER_PART12, 0.2182F, 0.0F, 0.5236F);
    this.CLUSTER_PART12.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART12, 14, 34, -1.0F, -3.5F, -1.0F, 2, 4, 2, 0.0F));
    this.CLUSTER_PART13 = new ModelRenderer(this);
    this.CLUSTER_PART13.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER3.func_78792_a(this.CLUSTER_PART13);
    setRotationAngle(this.CLUSTER_PART13, 0.2182F, 0.0F, -0.1745F);
    this.CLUSTER_PART13.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART13, 0, 11, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER4 = new ModelRenderer(this);
    this.CLUSTER4.func_78793_a(-2.25F, -1.0F, 2.0F);
    this.BONE.func_78792_a(this.CLUSTER4);
    setRotationAngle(this.CLUSTER4, 0.0F, -1.6144F, 0.0F);
    this.CLUSTER_PART14 = new ModelRenderer(this);
    this.CLUSTER_PART14.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER4.func_78792_a(this.CLUSTER_PART14);
    setRotationAngle(this.CLUSTER_PART14, -0.2618F, 0.0F, 0.6981F);
    this.CLUSTER_PART14.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART14, 26, 26, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART15 = new ModelRenderer(this);
    this.CLUSTER_PART15.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER4.func_78792_a(this.CLUSTER_PART15);
    setRotationAngle(this.CLUSTER_PART15, -0.4363F, 0.0F, 0.2618F);
    this.CLUSTER_PART15.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART15, 18, 26, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART16 = new ModelRenderer(this);
    this.CLUSTER_PART16.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER4.func_78792_a(this.CLUSTER_PART16);
    setRotationAngle(this.CLUSTER_PART16, 0.2182F, 0.0F, 0.5236F);
    this.CLUSTER_PART16.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART16, 14, 34, -1.0F, -3.5F, -1.0F, 2, 4, 2, 0.0F));
    this.CLUSTER_PART17 = new ModelRenderer(this);
    this.CLUSTER_PART17.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER4.func_78792_a(this.CLUSTER_PART17);
    setRotationAngle(this.CLUSTER_PART17, 0.2182F, 0.0F, -0.1745F);
    this.CLUSTER_PART17.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART17, 0, 11, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER5 = new ModelRenderer(this);
    this.CLUSTER5.func_78793_a(0.0F, -3.0F, 2.0F);
    this.BONE.func_78792_a(this.CLUSTER5);
    setRotationAngle(this.CLUSTER5, -0.2618F, 0.0F, -0.3054F);
    this.CLUSTER5.field_78804_l.add(new ModelBox(this.CLUSTER5, 0, 20, -2.0F, -11.0F, 0.0F, 2, 11, 2, 0.0F));
    this.CLUSTER_PART18 = new ModelRenderer(this);
    this.CLUSTER_PART18.func_78793_a(-1.0F, -0.5F, 1.0F);
    this.CLUSTER5.func_78792_a(this.CLUSTER_PART18);
    setRotationAngle(this.CLUSTER_PART18, -0.2618F, 0.0F, -0.3054F);
    this.CLUSTER_PART18.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART18, 32, 16, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART19 = new ModelRenderer(this);
    this.CLUSTER_PART19.func_78793_a(-1.0F, -0.5F, 1.0F);
    this.CLUSTER5.func_78792_a(this.CLUSTER_PART19);
    setRotationAngle(this.CLUSTER_PART19, -0.2618F, 0.0F, 0.6981F);
    this.CLUSTER_PART19.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART19, 32, 32, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART20 = new ModelRenderer(this);
    this.CLUSTER_PART20.func_78793_a(-1.0F, -0.5F, 1.0F);
    this.CLUSTER5.func_78792_a(this.CLUSTER_PART20);
    setRotationAngle(this.CLUSTER_PART20, -0.4363F, 0.0F, 0.2618F);
    this.CLUSTER_PART20.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART20, 6, 32, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART21 = new ModelRenderer(this);
    this.CLUSTER_PART21.func_78793_a(-1.0F, -0.5F, 1.0F);
    this.CLUSTER5.func_78792_a(this.CLUSTER_PART21);
    setRotationAngle(this.CLUSTER_PART21, 0.2182F, 0.0F, 0.5236F);
    this.CLUSTER_PART21.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART21, 0, 0, -1.0F, -7.5F, -1.0F, 2, 8, 2, 0.0F));
    this.CLUSTER_PART22 = new ModelRenderer(this);
    this.CLUSTER_PART22.func_78793_a(-1.0F, -0.5F, 1.0F);
    this.CLUSTER5.func_78792_a(this.CLUSTER_PART22);
    setRotationAngle(this.CLUSTER_PART22, 0.2182F, 0.0F, -0.1745F);
    this.CLUSTER_PART22.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART22, 30, 0, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER6 = new ModelRenderer(this);
    this.CLUSTER6.func_78793_a(-1.25F, -1.0F, 3.25F);
    this.BONE.func_78792_a(this.CLUSTER6);
    setRotationAngle(this.CLUSTER6, -0.48F, -0.6545F, 0.5236F);
    this.CLUSTER_PART23 = new ModelRenderer(this);
    this.CLUSTER_PART23.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER6.func_78792_a(this.CLUSTER_PART23);
    setRotationAngle(this.CLUSTER_PART23, -0.2618F, 0.0F, 0.6981F);
    this.CLUSTER_PART23.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART23, 26, 26, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART24 = new ModelRenderer(this);
    this.CLUSTER_PART24.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER6.func_78792_a(this.CLUSTER_PART24);
    setRotationAngle(this.CLUSTER_PART24, -0.4363F, 0.0F, 0.2618F);
    this.CLUSTER_PART24.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART24, 18, 26, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART25 = new ModelRenderer(this);
    this.CLUSTER_PART25.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER6.func_78792_a(this.CLUSTER_PART25);
    setRotationAngle(this.CLUSTER_PART25, 0.2182F, 0.0F, 0.5236F);
    this.CLUSTER_PART25.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART25, 14, 34, -1.0F, -3.5F, -1.0F, 2, 4, 2, 0.0F));
    this.CLUSTER_PART26 = new ModelRenderer(this);
    this.CLUSTER_PART26.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER6.func_78792_a(this.CLUSTER_PART26);
    setRotationAngle(this.CLUSTER_PART26, 0.2182F, 0.0F, -0.1745F);
    this.CLUSTER_PART26.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART26, 0, 11, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER7 = new ModelRenderer(this);
    this.CLUSTER7.func_78793_a(-1.25F, -1.0F, -1.75F);
    this.BONE.func_78792_a(this.CLUSTER7);
    setRotationAngle(this.CLUSTER7, 0.9599F, -0.6545F, -0.48F);
    this.CLUSTER_PART27 = new ModelRenderer(this);
    this.CLUSTER_PART27.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER7.func_78792_a(this.CLUSTER_PART27);
    setRotationAngle(this.CLUSTER_PART27, -0.2618F, 0.0F, 0.6981F);
    this.CLUSTER_PART27.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART27, 26, 26, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART28 = new ModelRenderer(this);
    this.CLUSTER_PART28.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER7.func_78792_a(this.CLUSTER_PART28);
    setRotationAngle(this.CLUSTER_PART28, -0.4363F, 0.0F, 0.2618F);
    this.CLUSTER_PART28.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART28, 18, 26, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART29 = new ModelRenderer(this);
    this.CLUSTER_PART29.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER7.func_78792_a(this.CLUSTER_PART29);
    setRotationAngle(this.CLUSTER_PART29, 0.2182F, 0.0F, 0.5236F);
    this.CLUSTER_PART29.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART29, 14, 34, -1.0F, -3.5F, -1.0F, 2, 4, 2, 0.0F));
    this.CLUSTER_PART30 = new ModelRenderer(this);
    this.CLUSTER_PART30.func_78793_a(0.25F, -0.5F, 0.0F);
    this.CLUSTER7.func_78792_a(this.CLUSTER_PART30);
    setRotationAngle(this.CLUSTER_PART30, 0.2182F, 0.0F, -0.1745F);
    this.CLUSTER_PART30.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART30, 0, 11, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER8 = new ModelRenderer(this);
    this.CLUSTER8.func_78793_a(0.0F, -7.0F, 2.0F);
    this.BONE.func_78792_a(this.CLUSTER8);
    setRotationAngle(this.CLUSTER8, -0.2618F, 0.0F, 0.0436F);
    this.CLUSTER8.field_78804_l.add(new ModelBox(this.CLUSTER8, 0, 20, -2.0F, -11.0F, 0.0F, 2, 11, 2, 0.0F));
    this.CLUSTER_PART31 = new ModelRenderer(this);
    this.CLUSTER_PART31.func_78793_a(-1.0F, -0.5F, 1.0F);
    this.CLUSTER8.func_78792_a(this.CLUSTER_PART31);
    setRotationAngle(this.CLUSTER_PART31, -0.2618F, 0.0F, -0.3054F);
    this.CLUSTER_PART31.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART31, 32, 16, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART32 = new ModelRenderer(this);
    this.CLUSTER_PART32.func_78793_a(-1.0F, -0.5F, 1.0F);
    this.CLUSTER8.func_78792_a(this.CLUSTER_PART32);
    setRotationAngle(this.CLUSTER_PART32, -0.2618F, 0.0F, 0.6981F);
    this.CLUSTER_PART32.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART32, 32, 32, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART33 = new ModelRenderer(this);
    this.CLUSTER_PART33.func_78793_a(-1.0F, -0.5F, 1.0F);
    this.CLUSTER8.func_78792_a(this.CLUSTER_PART33);
    setRotationAngle(this.CLUSTER_PART33, -0.4363F, 0.0F, 0.2618F);
    this.CLUSTER_PART33.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART33, 6, 32, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
    this.CLUSTER_PART34 = new ModelRenderer(this);
    this.CLUSTER_PART34.func_78793_a(-1.0F, -0.5F, 1.0F);
    this.CLUSTER8.func_78792_a(this.CLUSTER_PART34);
    setRotationAngle(this.CLUSTER_PART34, 0.2182F, 0.0F, 0.5236F);
    this.CLUSTER_PART34.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART34, 0, 0, -1.0F, -7.5F, -1.0F, 2, 8, 2, 0.0F));
    this.CLUSTER_PART35 = new ModelRenderer(this);
    this.CLUSTER_PART35.func_78793_a(-1.0F, -0.5F, 1.0F);
    this.CLUSTER8.func_78792_a(this.CLUSTER_PART35);
    setRotationAngle(this.CLUSTER_PART35, 0.2182F, 0.0F, -0.1745F);
    this.CLUSTER_PART35.field_78804_l
      .add(new ModelBox(this.CLUSTER_PART35, 30, 0, -1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\FindiumTrophy2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */