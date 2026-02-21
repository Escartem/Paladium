package fr.paladium.palamod.modules.palaboss.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelArachna extends ModelBiped {
  private final ModelRenderer bone;
  
  private final ModelRenderer body;
  
  private final ModelRenderer back;
  
  private final ModelRenderer abdomen;
  
  private final ModelRenderer mushroom;
  
  private final ModelRenderer mushroom2;
  
  private final ModelRenderer head;
  
  private final ModelRenderer fangRight;
  
  private final ModelRenderer fangRight2;
  
  private final ModelRenderer fangLeft;
  
  private final ModelRenderer fangLeft2;
  
  private final ModelRenderer legsRight;
  
  private final ModelRenderer RLeg1;
  
  private final ModelRenderer RLeg1part;
  
  private final ModelRenderer RLeg1part2;
  
  private final ModelRenderer RLeg1part3;
  
  private final ModelRenderer RLeg2;
  
  private final ModelRenderer RLeg2part;
  
  private final ModelRenderer RLeg2part2;
  
  private final ModelRenderer RLeg2part3;
  
  private final ModelRenderer RLeg3;
  
  private final ModelRenderer RLeg3part;
  
  private final ModelRenderer RLeg3part2;
  
  private final ModelRenderer RLeg3part3;
  
  private final ModelRenderer RLeg4;
  
  private final ModelRenderer RLeg4part;
  
  private final ModelRenderer RLeg4part2;
  
  private final ModelRenderer RLeg4part3;
  
  private final ModelRenderer legsLeft;
  
  private final ModelRenderer LLeg;
  
  private final ModelRenderer LLeg1part;
  
  private final ModelRenderer LLeg1part2;
  
  private final ModelRenderer LLeg1part3;
  
  private final ModelRenderer LLeg2;
  
  private final ModelRenderer LLeg2part;
  
  private final ModelRenderer LLeg2part2;
  
  private final ModelRenderer LLeg2part3;
  
  private final ModelRenderer LLeg3;
  
  private final ModelRenderer LLeg3part;
  
  private final ModelRenderer LLeg3part2;
  
  private final ModelRenderer LLeg3part3;
  
  private final ModelRenderer LLeg4;
  
  private final ModelRenderer LLeg4part;
  
  private final ModelRenderer LLeg4part2;
  
  private final ModelRenderer LLeg4part3;
  
  public ModelArachna() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.bone = new ModelRenderer((ModelBase)this);
    this.bone.func_78793_a(-64.0F, 18.0F, 0.0F);
    this.body = new ModelRenderer((ModelBase)this);
    this.body.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bone.func_78792_a(this.body);
    this.body.field_78804_l.add(new ModelBox(this.body, 35, 0, 56.0F, -28.0F, -8.0F, 16, 16, 16, 0.0F));
    this.back = new ModelRenderer((ModelBase)this);
    this.back.func_78793_a(64.0F, -19.25F, 6.25F);
    this.body.func_78792_a(this.back);
    setRotationAngle(this.back, 0.0873F, 0.0F, 0.0F);
    this.back.field_78804_l.add(new ModelBox(this.back, 0, 33, -5.0F, -6.75F, 0.75F, 10, 11, 9, 0.0F));
    this.abdomen = new ModelRenderer((ModelBase)this);
    this.abdomen.func_78793_a(0.0F, -1.5038F, 7.4205F);
    this.back.func_78792_a(this.abdomen);
    setRotationAngle(this.abdomen, 0.1745F, 0.0F, 0.0F);
    this.abdomen.field_78804_l.add(new ModelBox(this.abdomen, 39, 34, -6.0F, -6.3333F, 29.3333F, 12, 12, 6, 0.0F));
    this.abdomen.field_78804_l.add(new ModelBox(this.abdomen, 39, 34, -6.0F, -5.3333F, -0.6667F, 12, 12, 6, 0.0F));
    this.abdomen.field_78804_l.add(new ModelBox(this.abdomen, 0, 54, -8.0F, -8.3333F, 1.3333F, 16, 17, 28, 0.0F));
    this.mushroom = new ModelRenderer((ModelBase)this);
    this.mushroom.func_78793_a(-4.3F, -9.3063F, 9.6837F);
    this.abdomen.func_78792_a(this.mushroom);
    setRotationAngle(this.mushroom, -0.4363F, 0.0F, -0.3054F);
    this.mushroom.field_78804_l.add(new ModelBox(this.mushroom, 62, 75, -1.7F, -0.8F, -1.3F, 3, 2, 3, 0.0F));
    this.mushroom.field_78804_l.add(new ModelBox(this.mushroom, 61, 59, -2.7F, -10.8F, -2.3F, 5, 10, 5, 0.0F));
    this.mushroom.field_78804_l.add(new ModelBox(this.mushroom, 62, 75, -1.7F, -12.8F, -1.3F, 3, 2, 3, 0.0F));
    this.mushroom.field_78804_l
      .add(new ModelBox(this.mushroom, 76, 33, -6.361F, -16.5564F, -5.7963F, 12, 4, 12, 0.0F));
    this.mushroom.field_78804_l
      .add(new ModelBox(this.mushroom, 76, 50, -4.361F, -18.5564F, -3.7963F, 8, 2, 8, 0.0F));
    this.mushroom2 = new ModelRenderer((ModelBase)this);
    this.mushroom2.func_78793_a(0.0F, 0.9659F, -0.2588F);
    this.abdomen.func_78792_a(this.mushroom2);
    this.mushroom2.field_78804_l
      .add(new ModelBox(this.mushroom2, 83, 62, -2.0F, -10.6933F, 7.3294F, 2, 2, 2, 0.0F));
    this.mushroom2.field_78804_l
      .add(new ModelBox(this.mushroom2, 83, 62, -2.0F, -15.6933F, 7.3294F, 2, 2, 2, 0.0F));
    this.mushroom2.field_78804_l
      .add(new ModelBox(this.mushroom2, 83, 67, -2.5F, -14.5639F, 6.8123F, 3, 5, 3, 0.0F));
    this.mushroom2.field_78804_l
      .add(new ModelBox(this.mushroom2, 90, 78, -5.5F, -18.5639F, 3.8123F, 9, 3, 9, 0.0F));
    this.mushroom2.field_78804_l
      .add(new ModelBox(this.mushroom2, 97, 68, -4.5F, -19.5639F, 4.8123F, 7, 1, 7, 0.0F));
    this.head = new ModelRenderer((ModelBase)this);
    this.head.func_78793_a(64.0F, 0.0F, 0.0F);
    this.body.func_78792_a(this.head);
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 0, -7.0F, -27.0F, -11.0F, 14, 14, 3, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 18, -5.0F, -26.5F, -12.0F, 4, 4, 3, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 18, 1.0F, -26.5F, -12.0F, 4, 4, 3, 0.0F));
    this.fangRight = new ModelRenderer((ModelBase)this);
    this.fangRight.func_78793_a(1.5F, -19.5F, -11.5F);
    this.head.func_78792_a(this.fangRight);
    setRotationAngle(this.fangRight, 0.0F, 0.0F, -0.4363F);
    this.fangRight.field_78804_l.add(new ModelBox(this.fangRight, 15, 18, -2.5F, -0.5F, -2.5F, 5, 10, 3, 0.0F));
    this.fangRight2 = new ModelRenderer((ModelBase)this);
    this.fangRight2.func_78793_a(-0.2394F, 7.612F, 0.0F);
    this.fangRight.func_78792_a(this.fangRight2);
    setRotationAngle(this.fangRight2, 0.0F, 0.0F, 1.0036F);
    this.fangRight2.field_78804_l.add(new ModelBox(this.fangRight2, 0, 25, 0.5F, -0.5F, -1.5F, 2, 5, 1, 0.0F));
    this.fangLeft = new ModelRenderer((ModelBase)this);
    this.fangLeft.func_78793_a(-1.5F, -19.5F, -11.5F);
    this.head.func_78792_a(this.fangLeft);
    setRotationAngle(this.fangLeft, 0.0F, 0.0F, 0.4363F);
    this.fangLeft.field_78804_l.add(new ModelBox(this.fangLeft, 15, 18, -2.5F, -0.5F, -2.5F, 5, 10, 3, 0.0F));
    this.fangLeft2 = new ModelRenderer((ModelBase)this);
    this.fangLeft2.func_78793_a(0.2394F, 7.612F, 0.0F);
    this.fangLeft.func_78792_a(this.fangLeft2);
    setRotationAngle(this.fangLeft2, 0.0F, 0.0F, -1.0036F);
    this.fangLeft2.field_78804_l.add(new ModelBox(this.fangLeft2, 0, 25, -2.5F, -0.5F, -1.5F, 2, 5, 1, 0.0F));
    this.legsRight = new ModelRenderer((ModelBase)this);
    this.legsRight.func_78793_a(-16.0F, 0.0F, 0.0F);
    this.body.func_78792_a(this.legsRight);
    this.RLeg1 = new ModelRenderer((ModelBase)this);
    this.RLeg1.func_78793_a(73.0F, -14.0F, -2.0F);
    this.legsRight.func_78792_a(this.RLeg1);
    setRotationAngle(this.RLeg1, 0.0F, -0.3927F, 0.9163F);
    this.RLeg1.field_78804_l.add(new ModelBox(this.RLeg1, 88, 0, -16.0F, -2.0F, -2.0F, 16, 4, 4, 0.0F));
    this.RLeg1part = new ModelRenderer((ModelBase)this);
    this.RLeg1part.func_78793_a(-15.6812F, 0.6756F, 0.2821F);
    this.RLeg1.func_78792_a(this.RLeg1part);
    setRotationAngle(this.RLeg1part, 0.2729F, 0.286F, 2.3521F);
    this.RLeg1part.field_78804_l.add(new ModelBox(this.RLeg1part, 88, 8, -1.0F, -2.0F, -2.0F, 16, 4, 4, 0.25F));
    this.RLeg1part2 = new ModelRenderer((ModelBase)this);
    this.RLeg1part2.func_78793_a(15.0F, 0.0F, 0.0F);
    this.RLeg1part.func_78792_a(this.RLeg1part2);
    setRotationAngle(this.RLeg1part2, 0.0F, 0.0F, 1.9199F);
    this.RLeg1part2.field_78804_l.add(new ModelBox(this.RLeg1part2, 0, 100, -32.0F, -2.0F, -2.0F, 32, 4, 4, 0.5F));
    this.RLeg1part3 = new ModelRenderer((ModelBase)this);
    this.RLeg1part3.func_78793_a(-31.0F, 0.0F, 0.0F);
    this.RLeg1part2.func_78792_a(this.RLeg1part3);
    setRotationAngle(this.RLeg1part3, 0.0F, 0.0F, 2.3562F);
    this.RLeg1part3.field_78804_l.add(new ModelBox(this.RLeg1part3, 0, 55, -1.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F));
    this.RLeg2 = new ModelRenderer((ModelBase)this);
    this.RLeg2.func_78793_a(73.0F, -14.0F, -2.0F);
    this.legsRight.func_78792_a(this.RLeg2);
    setRotationAngle(this.RLeg2, 0.5045F, 0.0388F, 0.8235F);
    this.RLeg2.field_78804_l.add(new ModelBox(this.RLeg2, 88, 0, -16.0F, -2.0F, -2.0F, 16, 4, 4, 0.0F));
    this.RLeg2part = new ModelRenderer((ModelBase)this);
    this.RLeg2part.func_78793_a(-15.6812F, 0.6756F, 0.2821F);
    this.RLeg2.func_78792_a(this.RLeg2part);
    setRotationAngle(this.RLeg2part, 0.2729F, 0.286F, 2.3521F);
    this.RLeg2part.field_78804_l.add(new ModelBox(this.RLeg2part, 88, 8, -1.0F, -2.0F, -2.0F, 16, 4, 4, 0.25F));
    this.RLeg2part2 = new ModelRenderer((ModelBase)this);
    this.RLeg2part2.func_78793_a(15.0F, 0.0F, 0.0F);
    this.RLeg2part.func_78792_a(this.RLeg2part2);
    setRotationAngle(this.RLeg2part2, 0.0F, 0.0F, 1.9199F);
    this.RLeg2part2.field_78804_l.add(new ModelBox(this.RLeg2part2, 0, 100, -32.0F, -2.0F, -2.0F, 32, 4, 4, 0.5F));
    this.RLeg2part3 = new ModelRenderer((ModelBase)this);
    this.RLeg2part3.func_78793_a(-31.0F, 0.0F, 0.0F);
    this.RLeg2part2.func_78792_a(this.RLeg2part3);
    setRotationAngle(this.RLeg2part3, 0.0F, 0.0F, 2.3562F);
    this.RLeg2part3.field_78804_l.add(new ModelBox(this.RLeg2part3, 0, 55, -1.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F));
    this.RLeg3 = new ModelRenderer((ModelBase)this);
    this.RLeg3.func_78793_a(73.0F, -14.0F, 0.0F);
    this.legsRight.func_78792_a(this.RLeg3);
    setRotationAngle(this.RLeg3, 0.7F, 0.2149F, 0.8484F);
    this.RLeg3.field_78804_l.add(new ModelBox(this.RLeg3, 88, 0, -16.0F, -2.0F, -2.0F, 16, 4, 4, 0.0F));
    this.RLeg3part = new ModelRenderer((ModelBase)this);
    this.RLeg3part.func_78793_a(-15.6812F, 0.6756F, 0.2821F);
    this.RLeg3.func_78792_a(this.RLeg3part);
    setRotationAngle(this.RLeg3part, 0.2729F, 0.286F, 2.3521F);
    this.RLeg3part.field_78804_l.add(new ModelBox(this.RLeg3part, 88, 8, -1.0F, -2.0F, -2.0F, 16, 4, 4, 0.25F));
    this.RLeg3part2 = new ModelRenderer((ModelBase)this);
    this.RLeg3part2.func_78793_a(15.0F, 0.0F, 0.0F);
    this.RLeg3part.func_78792_a(this.RLeg3part2);
    setRotationAngle(this.RLeg3part2, 0.0F, 0.0F, 1.9199F);
    this.RLeg3part2.field_78804_l.add(new ModelBox(this.RLeg3part2, 0, 100, -32.0F, -2.0F, -2.0F, 32, 4, 4, 0.5F));
    this.RLeg3part3 = new ModelRenderer((ModelBase)this);
    this.RLeg3part3.func_78793_a(-31.0F, 0.0F, 0.0F);
    this.RLeg3part2.func_78792_a(this.RLeg3part3);
    setRotationAngle(this.RLeg3part3, 0.0F, 0.0F, 2.3562F);
    this.RLeg3part3.field_78804_l.add(new ModelBox(this.RLeg3part3, 0, 55, -1.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F));
    this.RLeg4 = new ModelRenderer((ModelBase)this);
    this.RLeg4.func_78793_a(73.0F, -14.0F, 1.0F);
    this.legsRight.func_78792_a(this.RLeg4);
    setRotationAngle(this.RLeg4, 1.3813F, 0.6356F, 1.1453F);
    this.RLeg4.field_78804_l.add(new ModelBox(this.RLeg4, 88, 0, -16.0F, -2.0F, -2.0F, 16, 4, 4, 0.0F));
    this.RLeg4part = new ModelRenderer((ModelBase)this);
    this.RLeg4part.func_78793_a(-15.6812F, 0.6756F, 0.2821F);
    this.RLeg4.func_78792_a(this.RLeg4part);
    setRotationAngle(this.RLeg4part, 0.2729F, 0.286F, 2.3521F);
    this.RLeg4part.field_78804_l.add(new ModelBox(this.RLeg4part, 88, 8, -1.0F, -2.0F, -2.0F, 16, 4, 4, 0.25F));
    this.RLeg4part2 = new ModelRenderer((ModelBase)this);
    this.RLeg4part2.func_78793_a(15.0F, 0.0F, 0.0F);
    this.RLeg4part.func_78792_a(this.RLeg4part2);
    setRotationAngle(this.RLeg4part2, 0.0F, 0.0F, 1.9199F);
    this.RLeg4part2.field_78804_l.add(new ModelBox(this.RLeg4part2, 0, 100, -32.0F, -2.0F, -2.0F, 32, 4, 4, 0.5F));
    this.RLeg4part3 = new ModelRenderer((ModelBase)this);
    this.RLeg4part3.func_78793_a(-31.0F, 0.0F, 0.0F);
    this.RLeg4part2.func_78792_a(this.RLeg4part3);
    setRotationAngle(this.RLeg4part3, 0.0F, 0.0F, 2.3562F);
    this.RLeg4part3.field_78804_l.add(new ModelBox(this.RLeg4part3, 0, 55, -1.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F));
    this.legsLeft = new ModelRenderer((ModelBase)this);
    this.legsLeft.func_78793_a(144.0F, 0.0F, 0.0F);
    this.body.func_78792_a(this.legsLeft);
    this.LLeg = new ModelRenderer((ModelBase)this);
    this.LLeg.func_78793_a(-73.0F, -14.0F, -2.0F);
    this.legsLeft.func_78792_a(this.LLeg);
    setRotationAngle(this.LLeg, 0.0F, 0.3927F, -0.9163F);
    this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 88, 0, 0.0F, -2.0F, -2.0F, 16, 4, 4, 0.0F));
    this.LLeg1part = new ModelRenderer((ModelBase)this);
    this.LLeg1part.func_78793_a(15.6812F, 0.6756F, 0.2821F);
    this.LLeg.func_78792_a(this.LLeg1part);
    setRotationAngle(this.LLeg1part, 0.2729F, -0.286F, -2.3521F);
    this.LLeg1part.field_78804_l.add(new ModelBox(this.LLeg1part, 88, 8, -15.0F, -2.0F, -2.0F, 16, 4, 4, 0.25F));
    this.LLeg1part2 = new ModelRenderer((ModelBase)this);
    this.LLeg1part2.func_78793_a(-15.0F, 0.0F, 0.0F);
    this.LLeg1part.func_78792_a(this.LLeg1part2);
    setRotationAngle(this.LLeg1part2, 0.0F, 0.0F, -1.9199F);
    this.LLeg1part2.field_78804_l.add(new ModelBox(this.LLeg1part2, 0, 100, 0.0F, -2.0F, -2.0F, 32, 4, 4, 0.5F));
    this.LLeg1part3 = new ModelRenderer((ModelBase)this);
    this.LLeg1part3.func_78793_a(31.0F, 0.0F, 0.0F);
    this.LLeg1part2.func_78792_a(this.LLeg1part3);
    setRotationAngle(this.LLeg1part3, 0.0F, 0.0F, -2.3562F);
    this.LLeg1part3.field_78804_l.add(new ModelBox(this.LLeg1part3, 0, 55, -5.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F));
    this.LLeg2 = new ModelRenderer((ModelBase)this);
    this.LLeg2.func_78793_a(-73.0F, -14.0F, -2.0F);
    this.legsLeft.func_78792_a(this.LLeg2);
    setRotationAngle(this.LLeg2, 0.5045F, -0.0388F, -0.8235F);
    this.LLeg2.field_78804_l.add(new ModelBox(this.LLeg2, 88, 0, 0.0F, -2.0F, -2.0F, 16, 4, 4, 0.0F));
    this.LLeg2part = new ModelRenderer((ModelBase)this);
    this.LLeg2part.func_78793_a(15.6812F, 0.6756F, 0.2821F);
    this.LLeg2.func_78792_a(this.LLeg2part);
    setRotationAngle(this.LLeg2part, 0.2729F, -0.286F, -2.3521F);
    this.LLeg2part.field_78804_l.add(new ModelBox(this.LLeg2part, 88, 8, -15.0F, -2.0F, -2.0F, 16, 4, 4, 0.25F));
    this.LLeg2part2 = new ModelRenderer((ModelBase)this);
    this.LLeg2part2.func_78793_a(-15.0F, 0.0F, 0.0F);
    this.LLeg2part.func_78792_a(this.LLeg2part2);
    setRotationAngle(this.LLeg2part2, 0.0F, 0.0F, -1.9199F);
    this.LLeg2part2.field_78804_l.add(new ModelBox(this.LLeg2part2, 0, 100, 0.0F, -2.0F, -2.0F, 32, 4, 4, 0.5F));
    this.LLeg2part3 = new ModelRenderer((ModelBase)this);
    this.LLeg2part3.func_78793_a(31.0F, 0.0F, 0.0F);
    this.LLeg2part2.func_78792_a(this.LLeg2part3);
    setRotationAngle(this.LLeg2part3, 0.0F, 0.0F, -2.3562F);
    this.LLeg2part3.field_78804_l.add(new ModelBox(this.LLeg2part3, 0, 55, -5.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F));
    this.LLeg3 = new ModelRenderer((ModelBase)this);
    this.LLeg3.func_78793_a(-73.0F, -14.0F, 0.0F);
    this.legsLeft.func_78792_a(this.LLeg3);
    setRotationAngle(this.LLeg3, 0.7F, -0.2149F, -0.8484F);
    this.LLeg3.field_78804_l.add(new ModelBox(this.LLeg3, 88, 0, 0.0F, -2.0F, -2.0F, 16, 4, 4, 0.0F));
    this.LLeg3part = new ModelRenderer((ModelBase)this);
    this.LLeg3part.func_78793_a(15.6812F, 0.6756F, 0.2821F);
    this.LLeg3.func_78792_a(this.LLeg3part);
    setRotationAngle(this.LLeg3part, 0.2729F, -0.286F, -2.3521F);
    this.LLeg3part.field_78804_l.add(new ModelBox(this.LLeg3part, 88, 8, -15.0F, -2.0F, -2.0F, 16, 4, 4, 0.25F));
    this.LLeg3part2 = new ModelRenderer((ModelBase)this);
    this.LLeg3part2.func_78793_a(-15.0F, 0.0F, 0.0F);
    this.LLeg3part.func_78792_a(this.LLeg3part2);
    setRotationAngle(this.LLeg3part2, 0.0F, 0.0F, -1.9199F);
    this.LLeg3part2.field_78804_l.add(new ModelBox(this.LLeg3part2, 0, 100, 0.0F, -2.0F, -2.0F, 32, 4, 4, 0.5F));
    this.LLeg3part3 = new ModelRenderer((ModelBase)this);
    this.LLeg3part3.func_78793_a(31.0F, 0.0F, 0.0F);
    this.LLeg3part2.func_78792_a(this.LLeg3part3);
    setRotationAngle(this.LLeg3part3, 0.0F, 0.0F, -2.3562F);
    this.LLeg3part3.field_78804_l.add(new ModelBox(this.LLeg3part3, 0, 55, -5.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F));
    this.LLeg4 = new ModelRenderer((ModelBase)this);
    this.LLeg4.func_78793_a(-73.0F, -14.0F, 1.0F);
    this.legsLeft.func_78792_a(this.LLeg4);
    setRotationAngle(this.LLeg4, 1.3813F, -0.6356F, -1.1453F);
    this.LLeg4.field_78804_l.add(new ModelBox(this.LLeg4, 88, 0, 0.0F, -2.0F, -2.0F, 16, 4, 4, 0.0F));
    this.LLeg4part = new ModelRenderer((ModelBase)this);
    this.LLeg4part.func_78793_a(15.6812F, 0.6756F, 0.2821F);
    this.LLeg4.func_78792_a(this.LLeg4part);
    setRotationAngle(this.LLeg4part, 0.2729F, -0.286F, -2.3521F);
    this.LLeg4part.field_78804_l.add(new ModelBox(this.LLeg4part, 88, 8, -15.0F, -2.0F, -2.0F, 16, 4, 4, 0.25F));
    this.LLeg4part2 = new ModelRenderer((ModelBase)this);
    this.LLeg4part2.func_78793_a(-15.0F, 0.0F, 0.0F);
    this.LLeg4part.func_78792_a(this.LLeg4part2);
    setRotationAngle(this.LLeg4part2, 0.0F, 0.0F, -1.9199F);
    this.LLeg4part2.field_78804_l.add(new ModelBox(this.LLeg4part2, 0, 100, 0.0F, -2.0F, -2.0F, 32, 4, 4, 0.5F));
    this.LLeg4part3 = new ModelRenderer((ModelBase)this);
    this.LLeg4part3.func_78793_a(31.0F, 0.0F, 0.0F);
    this.LLeg4part2.func_78792_a(this.LLeg4part3);
    setRotationAngle(this.LLeg4part3, 0.0F, 0.0F, -2.3562F);
    this.LLeg4part3.field_78804_l.add(new ModelBox(this.LLeg4part3, 0, 55, -5.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    this.bone.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, e);
    boolean isAttacking = false;
    this.LLeg4.field_78808_h = MathHelper.func_76134_b(f * 1.0F) * 0.25F * f1 + 30.6F;
    this.LLeg3.field_78808_h = MathHelper.func_76134_b(f * 1.0F) * 0.25F * f1 + 30.6F;
    this.RLeg3.field_78808_h = MathHelper.func_76134_b(f * 1.0F) * -0.25F * f1 - 30.6F;
    this.RLeg4.field_78808_h = MathHelper.func_76134_b(f * 1.0F) * -0.25F * f1 - 30.6F;
    this.RLeg2.field_78808_h = MathHelper.func_76134_b(f * 1.0F) * 0.25F * f1 - 30.6F;
    this.LLeg2.field_78808_h = MathHelper.func_76134_b(f * 1.0F) * -0.25F * f1 + 30.6F;
    this.back.field_78808_h = MathHelper.func_76134_b(f * 1.0F) / 5.0F * f1;
    this.back.field_78796_g = MathHelper.func_76134_b(f * 1.0F) / 6.0F * f1;
    this.abdomen.field_78808_h = MathHelper.func_76134_b(f * 1.0F) / 8.0F * f1;
    this.back.field_78795_f = MathHelper.func_76134_b(f2 / 8.0F) / 10.0F;
    this.fangLeft.field_78808_h = MathHelper.func_76134_b(f2 / 9.0F) / 8.0F - 100.0F;
    this.fangRight.field_78808_h = (MathHelper.func_76134_b(f2 / 9.0F) / 8.0F - 100.0F) * -1.0F;
    if (!isAttacking) {
      this.RLeg1.field_78808_h = MathHelper.func_76134_b(f * 1.0F) * 0.25F * f1 - 30.6F;
      this.LLeg.field_78808_h = MathHelper.func_76134_b(f * 1.0F) * -0.25F * f1 + 30.6F;
      this.fangLeft.field_78808_h = MathHelper.func_76134_b(f2 / 9.0F) / 8.0F - 100.0F;
      this.fangRight.field_78808_h = (MathHelper.func_76134_b(f2 / 9.0F) / 8.0F - 100.0F) * -1.0F;
    } else {
      this.RLeg1.field_78795_f = MathHelper.func_76134_b(f2) * 0.7F - 1.5F;
      this.LLeg.field_78795_f = MathHelper.func_76134_b(f2) * 0.7F - 1.5F;
      this.fangLeft.field_78808_h = MathHelper.func_76134_b(f2 * 2.0F) / 4.0F - 100.0F;
      this.fangRight.field_78808_h = (MathHelper.func_76134_b(f2 * 2.0F) / 4.0F - 100.0F) * -1.0F;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\models\ModelArachna.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */