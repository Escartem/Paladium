package fr.paladium.palamod.modules.luckyblock.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelChristmasTree extends ModelBase {
  private final ModelRenderer BALLS;
  
  private final ModelRenderer BONE;
  
  private final ModelRenderer BRANCH;
  
  private final ModelRenderer BRANCH_LEVEL_8;
  
  private final ModelRenderer BRANCH_8_0;
  
  private final ModelRenderer BRANCH_8_1;
  
  private final ModelRenderer BRANCH_8_2;
  
  private final ModelRenderer BRANCH_8_3;
  
  private final ModelRenderer BRANCH_LEVEL_7;
  
  private final ModelRenderer BRANCH_7_0;
  
  private final ModelRenderer BRANCH_7_1;
  
  private final ModelRenderer BRANCH_7_2;
  
  private final ModelRenderer BRANCH_7_3;
  
  private final ModelRenderer BRANCH_LEVEL_6;
  
  private final ModelRenderer BRANCH_6_0;
  
  private final ModelRenderer BRANCH_6_1;
  
  private final ModelRenderer BRANCH_6_2;
  
  private final ModelRenderer BRANCH_6_3;
  
  private final ModelRenderer BRANCH_LEVEL_5;
  
  private final ModelRenderer BRANCH_5_0;
  
  private final ModelRenderer BRANCH_5_1;
  
  private final ModelRenderer BRANCH_5_2;
  
  private final ModelRenderer BRANCH_5_3;
  
  private final ModelRenderer BRANCH_LEVEL_4;
  
  private final ModelRenderer BRANCH_4_0;
  
  private final ModelRenderer BRANCH_4_1;
  
  private final ModelRenderer BRANCH_4_2;
  
  private final ModelRenderer BRANCH_4_3;
  
  private final ModelRenderer BRANCH_LEVEL_3;
  
  private final ModelRenderer BRANCH_3_0;
  
  private final ModelRenderer BRANCH_3_1;
  
  private final ModelRenderer BRANCH_3_2;
  
  private final ModelRenderer BRANCH_3_3;
  
  private final ModelRenderer BRANCH_LEVEL_2;
  
  private final ModelRenderer BRANCH_2_0;
  
  private final ModelRenderer BRANCH_2_1;
  
  private final ModelRenderer BRANCH_2_2;
  
  private final ModelRenderer BRANCH_2_3;
  
  private final ModelRenderer BRANCH_LEVEL_1;
  
  private final ModelRenderer BRANCH_1_0;
  
  private final ModelRenderer BRANCH_1_1;
  
  private final ModelRenderer BRANCH_1_2;
  
  private final ModelRenderer BRANCH_1_3;
  
  private final ModelRenderer BRANCH_LEVEL_0;
  
  private final ModelRenderer BRANCH_0_0;
  
  private final ModelRenderer BRANCH_0_1;
  
  private final ModelRenderer BRANCH_0_2;
  
  private final ModelRenderer BRANCH_0_3;
  
  private final ModelRenderer BRANCH_TOP;
  
  private final ModelRenderer STAR;
  
  private final ModelRenderer GARLAND;
  
  private final ModelRenderer LIGHTSTRING;
  
  public ModelChristmasTree() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.BALLS = new ModelRenderer(this);
    this.BALLS.func_78793_a(0.0F, -8.0F, 0.0F);
    this.BALLS.field_78804_l.add(new ModelBox(this.BALLS, 0, 33, 2.0F, 9.0F, 0.0F, 3, 3, 3, 0.0F));
    this.BALLS.field_78804_l.add(new ModelBox(this.BALLS, 0, 33, -6.0F, 18.0F, -4.0F, 3, 3, 3, 0.0F));
    this.BALLS.field_78804_l.add(new ModelBox(this.BALLS, 0, 33, -1.0F, 13.0F, -7.0F, 3, 3, 3, 0.0F));
    this.BALLS.field_78804_l.add(new ModelBox(this.BALLS, 13, 33, -2.0F, 26.0F, -6.0F, 3, 3, 3, 0.0F));
    this.BALLS.field_78804_l.add(new ModelBox(this.BALLS, 13, 33, -7.0F, 10.0F, 1.0F, 3, 3, 3, 0.0F));
    this.BALLS.field_78804_l.add(new ModelBox(this.BALLS, 13, 33, 3.0F, 21.0F, -3.0F, 3, 3, 3, 0.0F));
    this.BALLS.field_78804_l.add(new ModelBox(this.BALLS, 26, 33, 2.0F, 16.0F, 3.0F, 3, 3, 3, 0.0F));
    this.BALLS.field_78804_l.add(new ModelBox(this.BALLS, 26, 33, 3.0F, 24.0F, 4.0F, 3, 3, 3, 0.0F));
    this.BALLS.field_78804_l.add(new ModelBox(this.BALLS, 26, 33, -8.0F, 21.0F, 3.0F, 3, 3, 3, 0.0F));
    this.BONE = new ModelRenderer(this);
    this.BONE.func_78793_a(0.0F, 24.0F, 0.0F);
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 4, 9, -1.0F, -12.0F, -1.0F, 2, 10, 2, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 12, 12, -1.5F, -2.0F, -1.5F, 3, 2, 3, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 0, -0.5F, -30.0F, -0.5F, 1, 18, 1, 0.0F));
    this.BRANCH = new ModelRenderer(this);
    this.BRANCH.func_78793_a(0.0F, 0.0F, 0.0F);
    this.BONE.func_78792_a(this.BRANCH);
    this.BRANCH_LEVEL_8 = new ModelRenderer(this);
    this.BRANCH_LEVEL_8.func_78793_a(-0.5F, -21.0F, 0.5F);
    this.BRANCH.func_78792_a(this.BRANCH_LEVEL_8);
    this.BRANCH_8_0 = new ModelRenderer(this);
    this.BRANCH_8_0.func_78793_a(0.5F, -5.0F, 3.5F);
    this.BRANCH_LEVEL_8.func_78792_a(this.BRANCH_8_0);
    setRotationAngle(this.BRANCH_8_0, -0.7854F, 0.0F, 0.0F);
    this.BRANCH_8_0.field_78804_l.add(new ModelBox(this.BRANCH_8_0, 33, 0, -2.5F, 0.0F, -5.5F, 5, 0, 9, 0.0F));
    this.BRANCH_8_1 = new ModelRenderer(this);
    this.BRANCH_8_1.func_78793_a(-3.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_8.func_78792_a(this.BRANCH_8_1);
    setRotationAngle(this.BRANCH_8_1, -0.7854F, -1.5708F, 0.0F);
    this.BRANCH_8_1.field_78804_l.add(new ModelBox(this.BRANCH_8_1, 33, 0, -2.5F, 0.0F, -5.5F, 5, 0, 9, 0.0F));
    this.BRANCH_8_2 = new ModelRenderer(this);
    this.BRANCH_8_2.func_78793_a(0.5F, -5.0F, -4.5F);
    this.BRANCH_LEVEL_8.func_78792_a(this.BRANCH_8_2);
    setRotationAngle(this.BRANCH_8_2, -0.7854F, 3.1416F, 0.0F);
    this.BRANCH_8_2.field_78804_l.add(new ModelBox(this.BRANCH_8_2, 33, 0, -2.5F, 0.0F, -5.5F, 5, 0, 9, 0.0F));
    this.BRANCH_8_3 = new ModelRenderer(this);
    this.BRANCH_8_3.func_78793_a(4.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_8.func_78792_a(this.BRANCH_8_3);
    setRotationAngle(this.BRANCH_8_3, -0.7854F, 1.5708F, 0.0F);
    this.BRANCH_8_3.field_78804_l.add(new ModelBox(this.BRANCH_8_3, 33, 0, -2.5F, 0.0F, -5.5F, 5, 0, 9, 0.0F));
    this.BRANCH_LEVEL_7 = new ModelRenderer(this);
    this.BRANCH_LEVEL_7.func_78793_a(-0.5F, -18.0F, 0.5F);
    this.BRANCH.func_78792_a(this.BRANCH_LEVEL_7);
    setRotationAngle(this.BRANCH_LEVEL_7, 0.0F, -0.7854F, 0.0F);
    this.BRANCH_7_0 = new ModelRenderer(this);
    this.BRANCH_7_0.func_78793_a(0.5F, -5.0F, 4.5F);
    this.BRANCH_LEVEL_7.func_78792_a(this.BRANCH_7_0);
    setRotationAngle(this.BRANCH_7_0, -0.5236F, 0.0F, 0.0F);
    this.BRANCH_7_0.field_78804_l.add(new ModelBox(this.BRANCH_7_0, 33, 0, -2.5F, 0.0F, -5.5F, 5, 0, 9, 0.0F));
    this.BRANCH_7_1 = new ModelRenderer(this);
    this.BRANCH_7_1.func_78793_a(-4.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_7.func_78792_a(this.BRANCH_7_1);
    setRotationAngle(this.BRANCH_7_1, -0.5236F, -1.5708F, 0.0F);
    this.BRANCH_7_1.field_78804_l.add(new ModelBox(this.BRANCH_7_1, 33, 0, -2.5F, 0.0F, -5.5F, 5, 0, 9, 0.0F));
    this.BRANCH_7_2 = new ModelRenderer(this);
    this.BRANCH_7_2.func_78793_a(0.5F, -5.0F, -5.5F);
    this.BRANCH_LEVEL_7.func_78792_a(this.BRANCH_7_2);
    setRotationAngle(this.BRANCH_7_2, -0.5236F, 3.1416F, 0.0F);
    this.BRANCH_7_2.field_78804_l.add(new ModelBox(this.BRANCH_7_2, 33, 0, -2.5F, 0.0F, -5.5F, 5, 0, 9, 0.0F));
    this.BRANCH_7_3 = new ModelRenderer(this);
    this.BRANCH_7_3.func_78793_a(5.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_7.func_78792_a(this.BRANCH_7_3);
    setRotationAngle(this.BRANCH_7_3, -0.5236F, 1.5708F, 0.0F);
    this.BRANCH_7_3.field_78804_l.add(new ModelBox(this.BRANCH_7_3, 33, 0, -2.5F, 0.0F, -5.5F, 5, 0, 9, 0.0F));
    this.BRANCH_LEVEL_6 = new ModelRenderer(this);
    this.BRANCH_LEVEL_6.func_78793_a(-0.5F, -16.0F, 0.5F);
    this.BRANCH.func_78792_a(this.BRANCH_LEVEL_6);
    this.BRANCH_6_0 = new ModelRenderer(this);
    this.BRANCH_6_0.func_78793_a(0.5F, -5.0F, 4.5F);
    this.BRANCH_LEVEL_6.func_78792_a(this.BRANCH_6_0);
    setRotationAngle(this.BRANCH_6_0, -0.4363F, 0.0F, 0.0F);
    this.BRANCH_6_0.field_78804_l.add(new ModelBox(this.BRANCH_6_0, 33, 0, -2.5F, 0.0F, -5.5F, 5, 0, 9, 0.0F));
    this.BRANCH_6_1 = new ModelRenderer(this);
    this.BRANCH_6_1.func_78793_a(-4.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_6.func_78792_a(this.BRANCH_6_1);
    setRotationAngle(this.BRANCH_6_1, -0.4363F, -1.5708F, 0.0F);
    this.BRANCH_6_1.field_78804_l.add(new ModelBox(this.BRANCH_6_1, 33, 0, -2.5F, 0.0F, -5.5F, 5, 0, 9, 0.0F));
    this.BRANCH_6_2 = new ModelRenderer(this);
    this.BRANCH_6_2.func_78793_a(0.5F, -5.0F, -5.5F);
    this.BRANCH_LEVEL_6.func_78792_a(this.BRANCH_6_2);
    setRotationAngle(this.BRANCH_6_2, -0.4363F, 3.1416F, 0.0F);
    this.BRANCH_6_2.field_78804_l.add(new ModelBox(this.BRANCH_6_2, 33, 0, -2.5F, 0.0F, -5.5F, 5, 0, 9, 0.0F));
    this.BRANCH_6_3 = new ModelRenderer(this);
    this.BRANCH_6_3.func_78793_a(5.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_6.func_78792_a(this.BRANCH_6_3);
    setRotationAngle(this.BRANCH_6_3, -0.4363F, 1.5708F, 0.0F);
    this.BRANCH_6_3.field_78804_l.add(new ModelBox(this.BRANCH_6_3, 33, 0, -2.5F, 0.0F, -5.5F, 5, 0, 9, 0.0F));
    this.BRANCH_LEVEL_5 = new ModelRenderer(this);
    this.BRANCH_LEVEL_5.func_78793_a(-0.5F, -13.0F, 0.5F);
    this.BRANCH.func_78792_a(this.BRANCH_LEVEL_5);
    setRotationAngle(this.BRANCH_LEVEL_5, 0.0F, -0.7854F, 0.0F);
    this.BRANCH_5_0 = new ModelRenderer(this);
    this.BRANCH_5_0.func_78793_a(0.5F, -5.0F, 4.5F);
    this.BRANCH_LEVEL_5.func_78792_a(this.BRANCH_5_0);
    setRotationAngle(this.BRANCH_5_0, -0.4363F, 0.0F, 0.0F);
    this.BRANCH_5_0.field_78804_l.add(new ModelBox(this.BRANCH_5_0, 0, 21, -3.5F, 0.0F, -5.5F, 7, 0, 11, 0.0F));
    this.BRANCH_5_1 = new ModelRenderer(this);
    this.BRANCH_5_1.func_78793_a(-4.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_5.func_78792_a(this.BRANCH_5_1);
    setRotationAngle(this.BRANCH_5_1, -0.4363F, -1.5708F, 0.0F);
    this.BRANCH_5_1.field_78804_l.add(new ModelBox(this.BRANCH_5_1, 0, 21, -3.5F, 0.0F, -5.5F, 7, 0, 11, 0.0F));
    this.BRANCH_5_2 = new ModelRenderer(this);
    this.BRANCH_5_2.func_78793_a(0.5F, -5.0F, -5.5F);
    this.BRANCH_LEVEL_5.func_78792_a(this.BRANCH_5_2);
    setRotationAngle(this.BRANCH_5_2, -0.4363F, 3.1416F, 0.0F);
    this.BRANCH_5_2.field_78804_l.add(new ModelBox(this.BRANCH_5_2, 0, 21, -3.5F, 0.0F, -5.5F, 7, 0, 11, 0.0F));
    this.BRANCH_5_3 = new ModelRenderer(this);
    this.BRANCH_5_3.func_78793_a(5.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_5.func_78792_a(this.BRANCH_5_3);
    setRotationAngle(this.BRANCH_5_3, -0.4363F, 1.5708F, 0.0F);
    this.BRANCH_5_3.field_78804_l.add(new ModelBox(this.BRANCH_5_3, 0, 21, -3.5F, 0.0F, -5.5F, 7, 0, 11, 0.0F));
    this.BRANCH_LEVEL_4 = new ModelRenderer(this);
    this.BRANCH_LEVEL_4.func_78793_a(-0.5F, -10.0F, 0.5F);
    this.BRANCH.func_78792_a(this.BRANCH_LEVEL_4);
    this.BRANCH_4_0 = new ModelRenderer(this);
    this.BRANCH_4_0.func_78793_a(0.5F, -5.0F, 4.5F);
    this.BRANCH_LEVEL_4.func_78792_a(this.BRANCH_4_0);
    setRotationAngle(this.BRANCH_4_0, -0.3491F, 0.0F, 0.0F);
    this.BRANCH_4_0.field_78804_l.add(new ModelBox(this.BRANCH_4_0, 0, 21, -3.5F, 0.0F, -5.5F, 7, 0, 11, 0.0F));
    this.BRANCH_4_1 = new ModelRenderer(this);
    this.BRANCH_4_1.func_78793_a(-4.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_4.func_78792_a(this.BRANCH_4_1);
    setRotationAngle(this.BRANCH_4_1, -0.3491F, -1.5708F, 0.0F);
    this.BRANCH_4_1.field_78804_l.add(new ModelBox(this.BRANCH_4_1, 0, 21, -3.5F, 0.0F, -5.5F, 7, 0, 11, 0.0F));
    this.BRANCH_4_2 = new ModelRenderer(this);
    this.BRANCH_4_2.func_78793_a(0.5F, -5.0F, -5.5F);
    this.BRANCH_LEVEL_4.func_78792_a(this.BRANCH_4_2);
    setRotationAngle(this.BRANCH_4_2, -0.3491F, 3.1416F, 0.0F);
    this.BRANCH_4_2.field_78804_l.add(new ModelBox(this.BRANCH_4_2, 0, 21, -3.5F, 0.0F, -5.5F, 7, 0, 11, 0.0F));
    this.BRANCH_4_3 = new ModelRenderer(this);
    this.BRANCH_4_3.func_78793_a(5.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_4.func_78792_a(this.BRANCH_4_3);
    setRotationAngle(this.BRANCH_4_3, -0.3491F, 1.5708F, 0.0F);
    this.BRANCH_4_3.field_78804_l.add(new ModelBox(this.BRANCH_4_3, 0, 21, -3.5F, 0.0F, -5.5F, 7, 0, 11, 0.0F));
    this.BRANCH_LEVEL_3 = new ModelRenderer(this);
    this.BRANCH_LEVEL_3.func_78793_a(-0.5F, -7.0F, 0.5F);
    this.BRANCH.func_78792_a(this.BRANCH_LEVEL_3);
    setRotationAngle(this.BRANCH_LEVEL_3, 0.0F, -0.7854F, 0.0F);
    this.BRANCH_3_0 = new ModelRenderer(this);
    this.BRANCH_3_0.func_78793_a(0.5F, -5.0F, 4.5F);
    this.BRANCH_LEVEL_3.func_78792_a(this.BRANCH_3_0);
    setRotationAngle(this.BRANCH_3_0, -0.3491F, 0.0F, 0.0F);
    this.BRANCH_3_0.field_78804_l.add(new ModelBox(this.BRANCH_3_0, 0, 21, -3.5F, 0.0F, -5.5F, 7, 0, 11, 0.0F));
    this.BRANCH_3_1 = new ModelRenderer(this);
    this.BRANCH_3_1.func_78793_a(-4.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_3.func_78792_a(this.BRANCH_3_1);
    setRotationAngle(this.BRANCH_3_1, -0.3491F, -1.5708F, 0.0F);
    this.BRANCH_3_1.field_78804_l.add(new ModelBox(this.BRANCH_3_1, 0, 21, -3.5F, 0.0F, -5.5F, 7, 0, 11, 0.0F));
    this.BRANCH_3_2 = new ModelRenderer(this);
    this.BRANCH_3_2.func_78793_a(0.5F, -5.0F, -5.5F);
    this.BRANCH_LEVEL_3.func_78792_a(this.BRANCH_3_2);
    setRotationAngle(this.BRANCH_3_2, -0.3491F, 3.1416F, 0.0F);
    this.BRANCH_3_2.field_78804_l.add(new ModelBox(this.BRANCH_3_2, 0, 21, -3.5F, 0.0F, -5.5F, 7, 0, 11, 0.0F));
    this.BRANCH_3_3 = new ModelRenderer(this);
    this.BRANCH_3_3.func_78793_a(5.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_3.func_78792_a(this.BRANCH_3_3);
    setRotationAngle(this.BRANCH_3_3, -0.3491F, 1.5708F, 0.0F);
    this.BRANCH_3_3.field_78804_l.add(new ModelBox(this.BRANCH_3_3, 0, 21, -3.5F, 0.0F, -5.5F, 7, 0, 11, 0.0F));
    this.BRANCH_LEVEL_2 = new ModelRenderer(this);
    this.BRANCH_LEVEL_2.func_78793_a(-0.5F, -4.0F, 0.5F);
    this.BRANCH.func_78792_a(this.BRANCH_LEVEL_2);
    this.BRANCH_2_0 = new ModelRenderer(this);
    this.BRANCH_2_0.func_78793_a(0.5F, -5.0F, 5.5F);
    this.BRANCH_LEVEL_2.func_78792_a(this.BRANCH_2_0);
    setRotationAngle(this.BRANCH_2_0, -0.3491F, 0.0F, 0.0F);
    this.BRANCH_2_0.field_78804_l.add(new ModelBox(this.BRANCH_2_0, 0, 0, -5.5F, 0.0F, -5.5F, 11, 0, 11, 0.0F));
    this.BRANCH_2_1 = new ModelRenderer(this);
    this.BRANCH_2_1.func_78793_a(-5.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_2.func_78792_a(this.BRANCH_2_1);
    setRotationAngle(this.BRANCH_2_1, -0.3491F, -1.5708F, 0.0F);
    this.BRANCH_2_1.field_78804_l.add(new ModelBox(this.BRANCH_2_1, 0, 0, -5.5F, 0.0F, -5.5F, 11, 0, 11, 0.0F));
    this.BRANCH_2_2 = new ModelRenderer(this);
    this.BRANCH_2_2.func_78793_a(0.5F, -5.0F, -6.5F);
    this.BRANCH_LEVEL_2.func_78792_a(this.BRANCH_2_2);
    setRotationAngle(this.BRANCH_2_2, -0.3491F, 3.1416F, 0.0F);
    this.BRANCH_2_2.field_78804_l.add(new ModelBox(this.BRANCH_2_2, 0, 0, -5.5F, 0.0F, -5.5F, 11, 0, 11, 0.0F));
    this.BRANCH_2_3 = new ModelRenderer(this);
    this.BRANCH_2_3.func_78793_a(6.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_2.func_78792_a(this.BRANCH_2_3);
    setRotationAngle(this.BRANCH_2_3, -0.3491F, 1.5708F, 0.0F);
    this.BRANCH_2_3.field_78804_l.add(new ModelBox(this.BRANCH_2_3, 0, 0, -5.5F, 0.0F, -5.5F, 11, 0, 11, 0.0F));
    this.BRANCH_LEVEL_1 = new ModelRenderer(this);
    this.BRANCH_LEVEL_1.func_78793_a(-0.5F, -1.0F, 0.5F);
    this.BRANCH.func_78792_a(this.BRANCH_LEVEL_1);
    setRotationAngle(this.BRANCH_LEVEL_1, 0.0F, -0.7854F, 0.0F);
    this.BRANCH_1_0 = new ModelRenderer(this);
    this.BRANCH_1_0.func_78793_a(0.5F, -5.0F, 5.5F);
    this.BRANCH_LEVEL_1.func_78792_a(this.BRANCH_1_0);
    setRotationAngle(this.BRANCH_1_0, -0.2618F, 0.0F, 0.0F);
    this.BRANCH_1_0.field_78804_l.add(new ModelBox(this.BRANCH_1_0, 0, 0, -5.5F, 0.0F, -5.5F, 11, 0, 11, 0.0F));
    this.BRANCH_1_1 = new ModelRenderer(this);
    this.BRANCH_1_1.func_78793_a(-5.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_1.func_78792_a(this.BRANCH_1_1);
    setRotationAngle(this.BRANCH_1_1, -0.2618F, -1.5708F, 0.0F);
    this.BRANCH_1_1.field_78804_l.add(new ModelBox(this.BRANCH_1_1, 0, 0, -5.5F, 0.0F, -5.5F, 11, 0, 11, 0.0F));
    this.BRANCH_1_2 = new ModelRenderer(this);
    this.BRANCH_1_2.func_78793_a(0.5F, -5.0F, -6.5F);
    this.BRANCH_LEVEL_1.func_78792_a(this.BRANCH_1_2);
    setRotationAngle(this.BRANCH_1_2, -0.2618F, 3.1416F, 0.0F);
    this.BRANCH_1_2.field_78804_l.add(new ModelBox(this.BRANCH_1_2, 0, 0, -5.5F, 0.0F, -5.5F, 11, 0, 11, 0.0F));
    this.BRANCH_1_3 = new ModelRenderer(this);
    this.BRANCH_1_3.func_78793_a(6.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_1.func_78792_a(this.BRANCH_1_3);
    setRotationAngle(this.BRANCH_1_3, -0.2618F, 1.5708F, 0.0F);
    this.BRANCH_1_3.field_78804_l.add(new ModelBox(this.BRANCH_1_3, 0, 0, -5.5F, 0.0F, -5.5F, 11, 0, 11, 0.0F));
    this.BRANCH_LEVEL_0 = new ModelRenderer(this);
    this.BRANCH_LEVEL_0.func_78793_a(-0.5F, 2.0F, 0.5F);
    this.BRANCH.func_78792_a(this.BRANCH_LEVEL_0);
    this.BRANCH_0_0 = new ModelRenderer(this);
    this.BRANCH_0_0.func_78793_a(0.5F, -5.0F, 5.5F);
    this.BRANCH_LEVEL_0.func_78792_a(this.BRANCH_0_0);
    setRotationAngle(this.BRANCH_0_0, -0.2618F, 0.0F, 0.0F);
    this.BRANCH_0_0.field_78804_l.add(new ModelBox(this.BRANCH_0_0, 0, 0, -5.5F, 0.0F, -5.5F, 11, 0, 11, 0.0F));
    this.BRANCH_0_1 = new ModelRenderer(this);
    this.BRANCH_0_1.func_78793_a(-5.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_0.func_78792_a(this.BRANCH_0_1);
    setRotationAngle(this.BRANCH_0_1, -0.2618F, -1.5708F, 0.0F);
    this.BRANCH_0_1.field_78804_l.add(new ModelBox(this.BRANCH_0_1, 0, 0, -5.5F, 0.0F, -5.5F, 11, 0, 11, 0.0F));
    this.BRANCH_0_2 = new ModelRenderer(this);
    this.BRANCH_0_2.func_78793_a(0.5F, -5.0F, -6.5F);
    this.BRANCH_LEVEL_0.func_78792_a(this.BRANCH_0_2);
    setRotationAngle(this.BRANCH_0_2, -0.2618F, 3.1416F, 0.0F);
    this.BRANCH_0_2.field_78804_l.add(new ModelBox(this.BRANCH_0_2, 0, 0, -5.5F, 0.0F, -5.5F, 11, 0, 11, 0.0F));
    this.BRANCH_0_3 = new ModelRenderer(this);
    this.BRANCH_0_3.func_78793_a(6.5F, -5.0F, -0.5F);
    this.BRANCH_LEVEL_0.func_78792_a(this.BRANCH_0_3);
    setRotationAngle(this.BRANCH_0_3, -0.2618F, 1.5708F, 0.0F);
    this.BRANCH_0_3.field_78804_l.add(new ModelBox(this.BRANCH_0_3, 0, 0, -5.5F, 0.0F, -5.5F, 11, 0, 11, 0.0F));
    this.BRANCH_TOP = new ModelRenderer(this);
    this.BRANCH_TOP.func_78793_a(0.0F, -29.0F, 0.0F);
    this.BRANCH.func_78792_a(this.BRANCH_TOP);
    setRotationAngle(this.BRANCH_TOP, 0.0F, -0.7854F, 0.0F);
    this.BRANCH_TOP.field_78804_l.add(new ModelBox(this.BRANCH_TOP, 0, 22, -2.0F, -6.0F, 0.0F, 4, 6, 0, 0.0F));
    this.BRANCH_TOP.field_78804_l.add(new ModelBox(this.BRANCH_TOP, 0, 18, 0.0F, -6.0F, -2.0F, 0, 6, 4, 0.0F));
    this.STAR = new ModelRenderer(this);
    this.STAR.func_78793_a(0.0F, -32.0F, 0.0F);
    this.BONE.func_78792_a(this.STAR);
    this.STAR.field_78804_l.add(new ModelBox(this.STAR, 27, 13, -3.0F, -7.0F, 0.0F, 6, 7, 0, 0.0F));
    this.STAR.field_78804_l.add(new ModelBox(this.STAR, 27, 7, 0.0F, -7.0F, -3.0F, 0, 7, 6, 0.0F));
    this.GARLAND = new ModelRenderer(this);
    this.GARLAND.func_78793_a(0.0F, 24.0F, 0.0F);
    this.GARLAND.field_78804_l.add(new ModelBox(this.GARLAND, 0, 39, -4.0F, -22.0F, -4.0F, 8, 17, 8, 0.1F));
    this.LIGHTSTRING = new ModelRenderer(this);
    this.LIGHTSTRING.func_78793_a(0.0F, 24.0F, 0.0F);
    this.LIGHTSTRING.field_78804_l
      .add(new ModelBox(this.LIGHTSTRING, 32, 34, -4.0F, -27.0F, -4.0F, 8, 22, 8, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float limbSwing, float limbSwingAmount, float liveTick, float headYaw, float headPitch, float scaleFactor) {
    func_78087_a(limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor, entity);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    super.func_78088_a(entity, limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor);
    this.BONE.func_78785_a(scaleFactor);
    GL11.glDisable(3042);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll(int state) {
    this.BONE.func_78785_a(0.0625F);
    if (state >= 1)
      this.BALLS.func_78785_a(0.0625F); 
    if (state >= 2)
      this.GARLAND.func_78785_a(0.0625F); 
    if (state >= 3)
      this.LIGHTSTRING.func_78785_a(0.0625F); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\ModelChristmasTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */