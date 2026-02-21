package fr.paladium.palamod.modules.hunter.models;

import fr.paladium.palamod.modules.hunter.entites.EntityElephant;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelElephant extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer armRight;
  
  private final ModelRenderer armLeft;
  
  private final ModelRenderer legLeft;
  
  private final ModelRenderer legRight;
  
  private final ModelRenderer tail;
  
  private final ModelRenderer head;
  
  private final ModelRenderer tuskRight;
  
  private final ModelRenderer tuskLeft;
  
  private final ModelRenderer trunk;
  
  private final ModelRenderer trunk2;
  
  private final ModelRenderer earRight;
  
  private final ModelRenderer earLeft;
  
  private final ModelRenderer boneA;
  
  private final ModelRenderer armRightA;
  
  private final ModelRenderer armLeftA;
  
  private final ModelRenderer legLeftA;
  
  private final ModelRenderer legRightA;
  
  private final ModelRenderer tailA;
  
  private final ModelRenderer tail2A;
  
  private final ModelRenderer headA;
  
  private final ModelRenderer trunkA;
  
  private final ModelRenderer trunk2A;
  
  private final ModelRenderer trunk3A;
  
  private final ModelRenderer tuskRightA;
  
  private final ModelRenderer tuskLeftA;
  
  private final ModelRenderer earRightA;
  
  private final ModelRenderer earRightPartA;
  
  private final ModelRenderer earRightPart2A;
  
  private final ModelRenderer earLeftA;
  
  private final ModelRenderer earLeftPartA;
  
  private final ModelRenderer earLeftPart2A;
  
  private final ModelRenderer boneB;
  
  private final ModelRenderer armRightB;
  
  private final ModelRenderer armLeftB;
  
  private final ModelRenderer legLeftB;
  
  private final ModelRenderer legRightB;
  
  private final ModelRenderer tailB;
  
  private final ModelRenderer tail2B;
  
  private final ModelRenderer headB;
  
  private final ModelRenderer tuskRightB;
  
  private final ModelRenderer tuskRight2B;
  
  private final ModelRenderer tuskLeftB;
  
  private final ModelRenderer tuskLeft2B;
  
  private final ModelRenderer trunkB;
  
  private final ModelRenderer trunk2B;
  
  private final ModelRenderer trunk3B;
  
  private final ModelRenderer trunk4B;
  
  private final ModelRenderer earRightB;
  
  private final ModelRenderer earRight2B;
  
  private final ModelRenderer earLeftB;
  
  private final ModelRenderer earLeft2B;
  
  public ModelElephant() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(1.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 6, 37, -7.0F, -18.0F, -9.0F, 12, 10, 17, 0.0F));
    this.armRight = new ModelRenderer(this);
    this.armRight.func_78793_a(-6.0F, -10.5F, -6.0F);
    this.bone.func_78792_a(this.armRight);
    this.armRight.field_78804_l.add(new ModelBox(this.armRight, 44, 0, -2.0F, -2.5F, -2.0F, 5, 13, 5, 0.0F));
    this.armLeft = new ModelRenderer(this);
    this.armLeft.func_78793_a(4.0F, -10.5F, -6.0F);
    this.bone.func_78792_a(this.armLeft);
    this.armLeft.field_78804_l.add(new ModelBox(this.armLeft, 44, 0, -3.0F, -2.5F, -2.0F, 5, 13, 5, 0.0F));
    this.legLeft = new ModelRenderer(this);
    this.legLeft.func_78793_a(4.0F, -10.5F, 7.0F);
    this.bone.func_78792_a(this.legLeft);
    this.legLeft.field_78804_l.add(new ModelBox(this.legLeft, 48, 38, -2.0F, -0.5F, -3.25F, 4, 11, 4, 0.0F));
    this.legRight = new ModelRenderer(this);
    this.legRight.func_78793_a(-5.0F, -10.5F, 6.75F);
    this.bone.func_78792_a(this.legRight);
    this.legRight.field_78804_l.add(new ModelBox(this.legRight, 48, 38, -3.0F, -0.5F, -3.0F, 4, 11, 4, 0.0F));
    this.tail = new ModelRenderer(this);
    this.tail.func_78793_a(-1.0F, -15.0F, 8.25F);
    this.bone.func_78792_a(this.tail);
    setRotationAngle(this.tail, 0.1309F, 0.0F, 0.0F);
    this.tail.field_78804_l.add(new ModelBox(this.tail, 1, 56, -0.5F, -1.0F, -0.5F, 1, 6, 1, 0.0F));
    this.head = new ModelRenderer(this);
    this.head.func_78793_a(-1.0F, -15.25F, -8.6667F);
    this.bone.func_78792_a(this.head);
    setRotationAngle(this.head, 0.0F, 0.0F, 0.0F);
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 0, -4.0F, -8.75F, -5.3333F, 8, 11, 8, 0.0F));
    this.tuskRight = new ModelRenderer(this);
    this.tuskRight.func_78793_a(-2.5F, 1.25F, -4.8333F);
    this.head.func_78792_a(this.tuskRight);
    setRotationAngle(this.tuskRight, 0.5672F, 0.1745F, 0.0F);
    this.tuskRight.field_78804_l.add(new ModelBox(this.tuskRight, 0, 48, -1.0F, -1.0F, -2.5F, 2, 2, 3, 0.0F));
    this.tuskLeft = new ModelRenderer(this);
    this.tuskLeft.func_78793_a(2.5F, 1.25F, -4.8333F);
    this.head.func_78792_a(this.tuskLeft);
    setRotationAngle(this.tuskLeft, 0.5672F, -0.1745F, 0.0F);
    this.tuskLeft.field_78804_l.add(new ModelBox(this.tuskLeft, 0, 48, -1.0F, -1.0F, -2.5F, 2, 2, 3, 0.0F));
    this.trunk = new ModelRenderer(this);
    this.trunk.func_78793_a(0.0F, -0.25F, -4.8333F);
    this.head.func_78792_a(this.trunk);
    setRotationAngle(this.trunk, -0.1745F, 0.0F, 0.0F);
    this.trunk.field_78804_l.add(new ModelBox(this.trunk, 16, 24, -1.5F, -1.5F, -1.5F, 3, 9, 3, 0.0F));
    this.trunk2 = new ModelRenderer(this);
    this.trunk2.func_78793_a(0.0F, 7.0F, 0.0F);
    this.trunk.func_78792_a(this.trunk2);
    setRotationAngle(this.trunk2, 0.5236F, 0.0F, 0.0F);
    this.trunk2.field_78804_l.add(new ModelBox(this.trunk2, 1, 20, -1.5F, -0.5F, -1.5F, 3, 6, 3, -0.5F));
    this.earRight = new ModelRenderer(this);
    this.earRight.func_78793_a(-4.0F, -5.5F, -1.3333F);
    this.head.func_78792_a(this.earRight);
    setRotationAngle(this.earRight, 0.0F, 0.3054F, 0.2618F);
    this.earRight.field_78804_l.add(new ModelBox(this.earRight, 8, 46, -5.0F, -1.25F, -1.0F, 5, 2, 2, 0.0F));
    this.earRight.field_78804_l.add(new ModelBox(this.earRight, 1, 38, -5.0F, 0.75F, 0.0F, 7, 7, 0, 0.0F));
    this.earLeft = new ModelRenderer(this);
    this.earLeft.func_78793_a(4.0F, -5.5F, -1.3333F);
    this.head.func_78792_a(this.earLeft);
    setRotationAngle(this.earLeft, 0.0F, -0.3054F, -0.2618F);
    this.earLeft.field_78804_l.add(new ModelBox(this.earLeft, 8, 46, 0.0F, -1.25F, -1.0F, 5, 2, 2, 0.0F));
    this.earLeft.field_78804_l.add(new ModelBox(this.earLeft, 1, 30, -2.0F, 0.75F, 0.0F, 7, 7, 0, 0.0F));
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.boneA = new ModelRenderer(this);
    this.boneA.func_78793_a(-0.8F, 12.2F, 2.2F);
    this.boneA.field_78804_l.add(new ModelBox(this.boneA, 0, 0, -6.2F, -15.2F, -11.2F, 14, 16, 24, 0.0F));
    this.armRightA = new ModelRenderer(this);
    this.armRightA.func_78793_a(-4.2F, 0.8F, -7.7F);
    this.boneA.func_78792_a(this.armRightA);
    this.armRightA.field_78804_l.add(new ModelBox(this.armRightA, 104, 0, -3.0F, -5.0F, -3.0F, 6, 16, 6, 0.0F));
    this.armLeftA = new ModelRenderer(this);
    this.armLeftA.func_78793_a(5.8F, 0.8F, -7.7F);
    this.boneA.func_78792_a(this.armLeftA);
    this.armLeftA.field_78804_l.add(new ModelBox(this.armLeftA, 104, 0, -3.0F, -5.0F, -3.0F, 6, 16, 6, 0.0F));
    this.legLeftA = new ModelRenderer(this);
    this.legLeftA.func_78793_a(5.8F, 0.8F, 9.3F);
    this.boneA.func_78792_a(this.legLeftA);
    this.legLeftA.field_78804_l.add(new ModelBox(this.legLeftA, 78, 0, -3.0F, -4.0F, -3.0F, 6, 15, 6, 0.0F));
    this.legRightA = new ModelRenderer(this);
    this.legRightA.func_78793_a(-4.2F, 0.8F, 9.3F);
    this.boneA.func_78792_a(this.legRightA);
    this.legRightA.field_78804_l.add(new ModelBox(this.legRightA, 78, 0, -3.0F, -4.0F, -3.0F, 6, 15, 6, 0.0F));
    this.tailA = new ModelRenderer(this);
    this.tailA.func_78793_a(0.8F, -10.5F, 12.75F);
    this.boneA.func_78792_a(this.tailA);
    setRotationAngle(this.tailA, 0.2618F, 0.0F, 0.0F);
    this.tailA.field_78804_l.add(new ModelBox(this.tailA, 78, 23, -0.5F, -1.5F, -0.5F, 1, 9, 1, 0.0F));
    this.tail2A = new ModelRenderer(this);
    this.tail2A.func_78793_a(0.0F, 7.1464F, 0.1124F);
    this.tailA.func_78792_a(this.tail2A);
    setRotationAngle(this.tail2A, -0.1745F, 0.0F, 0.0F);
    this.tail2A.field_78804_l.add(new ModelBox(this.tail2A, 78, 34, -1.0F, 0.0F, 0.0F, 2, 4, 0, 0.0F));
    this.headA = new ModelRenderer(this);
    this.headA.func_78793_a(0.7857F, -11.1429F, -10.5714F);
    this.boneA.func_78792_a(this.headA);
    this.headA.field_78804_l.add(new ModelBox(this.headA, 87, 23, -5.0357F, -7.8571F, -8.4286F, 10, 13, 10, 0.0F));
    this.headA.field_78804_l.add(new ModelBox(this.headA, 95, 47, -4.0357F, -8.8571F, -7.4286F, 8, 1, 8, 0.0F));
    this.trunkA = new ModelRenderer(this);
    this.trunkA.func_78793_a(-0.0357F, 4.1429F, -7.4286F);
    this.headA.func_78792_a(this.trunkA);
    setRotationAngle(this.trunkA, -0.3054F, 0.0F, 0.0F);
    this.trunkA.field_78804_l.add(new ModelBox(this.trunkA, 56, 43, -2.0F, -3.0F, -2.0F, 4, 8, 4, 0.0F));
    this.trunk2A = new ModelRenderer(this);
    this.trunk2A.func_78793_a(0.0F, 5.0F, 0.0F);
    this.trunkA.func_78792_a(this.trunk2A);
    setRotationAngle(this.trunk2A, 0.5672F, 0.0F, 0.0F);
    this.trunk2A.field_78804_l.add(new ModelBox(this.trunk2A, 38, 41, -2.0F, -1.0F, -2.0F, 4, 9, 4, -0.25F));
    this.trunk3A = new ModelRenderer(this);
    this.trunk3A.func_78793_a(0.0F, 7.0639F, -0.0554F);
    this.trunk2A.func_78792_a(this.trunk3A);
    setRotationAngle(this.trunk3A, 0.8727F, 0.0F, 0.0F);
    this.trunk3A.field_78804_l.add(new ModelBox(this.trunk3A, 24, 43, -1.5F, -0.178F, -1.2158F, 3, 6, 3, 0.0F));
    this.tuskRightA = new ModelRenderer(this);
    this.tuskRightA.func_78793_a(-3.7857F, 5.1429F, -8.4286F);
    this.headA.func_78792_a(this.tuskRightA);
    setRotationAngle(this.tuskRightA, -0.6981F, 0.0F, 0.2618F);
    this.tuskRightA.field_78804_l.add(new ModelBox(this.tuskRightA, 75, 59, -1.0F, -1.0F, -1.0F, 2, 8, 2, 0.0F));
    this.tuskLeftA = new ModelRenderer(this);
    this.tuskLeftA.func_78793_a(3.7143F, 5.1429F, -8.4286F);
    this.headA.func_78792_a(this.tuskLeftA);
    setRotationAngle(this.tuskLeftA, -0.6981F, 0.0F, -0.2618F);
    this.tuskLeftA.field_78804_l.add(new ModelBox(this.tuskLeftA, 75, 59, -1.0F, -1.0F, -1.0F, 2, 8, 2, 0.0F));
    this.earRightA = new ModelRenderer(this);
    this.earRightA.func_78793_a(-5.6357F, -5.7071F, -3.5286F);
    this.headA.func_78792_a(this.earRightA);
    setRotationAngle(this.earRightA, 0.0F, 0.2618F, 0.0F);
    this.earRightA.field_78804_l.add(new ModelBox(this.earRightA, 107, 57, -9.4F, -2.15F, 0.1F, 10, 13, 0, 0.0F));
    this.earRightPartA = new ModelRenderer(this);
    this.earRightPartA.func_78793_a(1.1F, -0.15F, 0.1F);
    this.earRightA.func_78792_a(this.earRightPartA);
    setRotationAngle(this.earRightPartA, 0.0F, 0.0F, 0.1309F);
    this.earRightPartA.field_78804_l
      .add(new ModelBox(this.earRightPartA, 77, 47, -5.5F, -1.0F, -1.0F, 5, 2, 2, 0.0F));
    this.earRightPart2A = new ModelRenderer(this);
    this.earRightPart2A.func_78793_a(-5.0F, 0.0F, 0.0F);
    this.earRightPartA.func_78792_a(this.earRightPart2A);
    setRotationAngle(this.earRightPart2A, 0.0F, 0.0F, -0.3491F);
    this.earRightPart2A.field_78804_l
      .add(new ModelBox(this.earRightPart2A, 76, 52, -6.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F));
    this.earLeftA = new ModelRenderer(this);
    this.earLeftA.func_78793_a(4.9643F, -5.7071F, -3.5286F);
    this.headA.func_78792_a(this.earLeftA);
    setRotationAngle(this.earLeftA, 0.0F, -0.2618F, 0.0F);
    this.earLeftA.field_78804_l.add(new ModelBox(this.earLeftA, 86, 57, 0.0F, -2.15F, 0.1F, 10, 13, 0, 0.0F));
    this.earLeftPartA = new ModelRenderer(this);
    this.earLeftPartA.func_78793_a(0.0F, -0.15F, 0.1F);
    this.earLeftA.func_78792_a(this.earLeftPartA);
    setRotationAngle(this.earLeftPartA, 0.0F, 0.0F, -0.1309F);
    this.earLeftPartA.field_78804_l
      .add(new ModelBox(this.earLeftPartA, 77, 47, 0.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F));
    this.earLeftPart2A = new ModelRenderer(this);
    this.earLeftPart2A.func_78793_a(5.0F, 0.0F, 0.0F);
    this.earLeftPartA.func_78792_a(this.earLeftPart2A);
    setRotationAngle(this.earLeftPart2A, 0.0F, 0.0F, 0.3491F);
    this.earLeftPart2A.field_78804_l
      .add(new ModelBox(this.earLeftPart2A, 76, 52, -0.5F, -1.0F, -1.0F, 6, 2, 2, 0.0F));
    this.field_78090_t = 160;
    this.field_78089_u = 160;
    this.boneB = new ModelRenderer(this);
    this.boneB.func_78793_a(-1.0F, 4.0F, -2.0F);
    this.boneB.field_78804_l.add(new ModelBox(this.boneB, 20, 80, -10.0F, -32.0F, -17.0F, 22, 32, 48, 0.0F));
    this.armRightB = new ModelRenderer(this);
    this.armRightB.func_78793_a(-7.0F, -12.0F, -9.0F);
    this.boneB.func_78792_a(this.armRightB);
    this.armRightB.field_78804_l.add(new ModelBox(this.armRightB, 120, 80, -5.0F, -4.0F, -5.0F, 10, 36, 10, 0.0F));
    this.armLeftB = new ModelRenderer(this);
    this.armLeftB.func_78793_a(9.0F, -12.0F, -9.0F);
    this.boneB.func_78792_a(this.armLeftB);
    this.armLeftB.field_78804_l.add(new ModelBox(this.armLeftB, 120, 80, -5.0F, -4.0F, -5.0F, 10, 36, 10, 0.0F));
    this.legLeftB = new ModelRenderer(this);
    this.legLeftB.func_78793_a(9.0F, -12.0F, 23.0F);
    this.boneB.func_78792_a(this.legLeftB);
    this.legLeftB.field_78804_l.add(new ModelBox(this.legLeftB, 120, 80, -5.0F, -4.0F, -5.0F, 10, 36, 10, 0.0F));
    this.legRightB = new ModelRenderer(this);
    this.legRightB.func_78793_a(-7.0F, -12.0F, 23.0F);
    this.boneB.func_78792_a(this.legRightB);
    this.legRightB.field_78804_l.add(new ModelBox(this.legRightB, 120, 80, -5.0F, -4.0F, -5.0F, 10, 36, 10, 0.0F));
    this.tailB = new ModelRenderer(this);
    this.tailB.func_78793_a(1.0F, -26.0F, 31.0F);
    this.boneB.func_78792_a(this.tailB);
    setRotationAngle(this.tailB, 0.1309F, 0.0F, 0.0F);
    this.tailB.field_78804_l.add(new ModelBox(this.tailB, 0, 0, -1.0F, -1.0F, -1.0F, 2, 16, 2, 0.0F));
    this.tail2B = new ModelRenderer(this);
    this.tail2B.func_78793_a(0.0F, 14.0F, 0.0F);
    this.tailB.func_78792_a(this.tail2B);
    setRotationAngle(this.tail2B, -0.0873F, 0.0F, 0.0F);
    this.tail2B.field_78804_l.add(new ModelBox(this.tail2B, 10, 0, -2.0F, -1.0F, 0.0F, 4, 8, 0, 0.0F));
    this.headB = new ModelRenderer(this);
    this.headB.func_78793_a(0.0F, -32.0F, -18.0F);
    this.boneB.func_78792_a(this.headB);
    setRotationAngle(this.headB, 0.0F, 0.0F, 0.0F);
    this.headB.field_78804_l.add(new ModelBox(this.headB, 0, 0, -8.0F, -12.0F, -13.0F, 18, 22, 19, 0.0F));
    this.headB.field_78804_l.add(new ModelBox(this.headB, 55, 0, -7.5F, -13.0F, -12.75F, 8, 1, 18, 0.0F));
    this.headB.field_78804_l.add(new ModelBox(this.headB, 55, 0, 1.5F, -13.0F, -12.75F, 8, 1, 18, 0.0F));
    this.tuskRightB = new ModelRenderer(this);
    this.tuskRightB.func_78793_a(-6.0F, 8.0F, -11.5F);
    this.headB.func_78792_a(this.tuskRightB);
    setRotationAngle(this.tuskRightB, 0.4883F, 0.3006F, 0.0547F);
    this.tuskRightB.field_78804_l.add(new ModelBox(this.tuskRightB, 76, 22, -2.0F, -2.0F, -13.5F, 4, 4, 15, 0.0F));
    this.tuskRight2B = new ModelRenderer(this);
    this.tuskRight2B.func_78793_a(0.0F, 0.0F, -13.0F);
    this.tuskRightB.func_78792_a(this.tuskRight2B);
    setRotationAngle(this.tuskRight2B, 0.0F, -0.5236F, 0.0F);
    this.tuskRight2B.field_78804_l.add(new ModelBox(this.tuskRight2B, 93, 65, -2.0F, -2.0F, -7.5F, 4, 4, 8, 0.0F));
    this.tuskLeftB = new ModelRenderer(this);
    this.tuskLeftB.func_78793_a(8.0F, 8.0F, -11.5F);
    this.headB.func_78792_a(this.tuskLeftB);
    setRotationAngle(this.tuskLeftB, 0.5338F, -0.298F, -0.0681F);
    this.tuskLeftB.field_78804_l.add(new ModelBox(this.tuskLeftB, 76, 22, -2.0F, -2.0F, -13.5F, 4, 4, 15, 0.0F));
    this.tuskLeft2B = new ModelRenderer(this);
    this.tuskLeft2B.func_78793_a(0.0F, 0.0F, -13.0F);
    this.tuskLeftB.func_78792_a(this.tuskLeft2B);
    setRotationAngle(this.tuskLeft2B, 0.0F, 0.4363F, 0.0F);
    this.tuskLeft2B.field_78804_l.add(new ModelBox(this.tuskLeft2B, 93, 65, -2.0F, -2.0F, -7.5F, 4, 4, 8, 0.0F));
    this.trunkB = new ModelRenderer(this);
    this.trunkB.func_78793_a(1.0F, 7.5F, -12.5F);
    this.headB.func_78792_a(this.trunkB);
    setRotationAngle(this.trunkB, -0.1309F, 0.0F, 0.0F);
    this.trunkB.field_78804_l.add(new ModelBox(this.trunkB, 38, 107, -3.5F, -3.5F, -3.5F, 7, 12, 7, 0.0F));
    this.trunk2B = new ModelRenderer(this);
    this.trunk2B.func_78793_a(0.0F, 9.0F, -0.5F);
    this.trunkB.func_78792_a(this.trunk2B);
    setRotationAngle(this.trunk2B, 0.1745F, 0.0F, 0.0F);
    this.trunk2B.field_78804_l.add(new ModelBox(this.trunk2B, 41, 83, -3.0F, -1.5F, -2.5F, 6, 15, 6, 0.0F));
    this.trunk3B = new ModelRenderer(this);
    this.trunk3B.func_78793_a(0.0F, 13.0F, 0.5F);
    this.trunk2B.func_78792_a(this.trunk3B);
    setRotationAngle(this.trunk3B, 0.3491F, 0.0F, 0.0F);
    this.trunk3B.field_78804_l.add(new ModelBox(this.trunk3B, 15, 110, -2.5F, -0.5F, -2.5F, 5, 10, 5, 0.0F));
    this.trunk4B = new ModelRenderer(this);
    this.trunk4B.func_78793_a(0.0F, 7.6934F, -0.5412F);
    this.trunk3B.func_78792_a(this.trunk4B);
    setRotationAngle(this.trunk4B, 0.5672F, 0.0F, 0.0F);
    this.trunk4B.field_78804_l.add(new ModelBox(this.trunk4B, 17, 92, -2.5F, 0.5F, -2.5F, 5, 9, 5, 0.0F));
    this.earRightB = new ModelRenderer(this);
    this.earRightB.func_78793_a(-8.0F, -8.0F, -2.0F);
    this.headB.func_78792_a(this.earRightB);
    setRotationAngle(this.earRightB, 0.0F, 0.5672F, 0.0436F);
    this.earRightB.field_78804_l.add(new ModelBox(this.earRightB, 132, 0, -10.0F, -2.0F, -2.0F, 10, 4, 4, 0.0F));
    this.earRightB.field_78804_l.add(new ModelBox(this.earRightB, 124, 10, -17.0F, -2.0F, 0.0F, 18, 31, 0, 0.0F));
    this.earRight2B = new ModelRenderer(this);
    this.earRight2B.func_78793_a(-9.0F, 0.0F, 0.0F);
    this.earRightB.func_78792_a(this.earRight2B);
    setRotationAngle(this.earRight2B, 0.0F, 0.0F, -0.6545F);
    this.earRight2B.field_78804_l.add(new ModelBox(this.earRight2B, 132, 0, -10.0F, -2.0F, -2.0F, 10, 4, 4, 0.0F));
    this.earLeftB = new ModelRenderer(this);
    this.earLeftB.func_78793_a(10.0F, -8.0F, -2.0F);
    this.headB.func_78792_a(this.earLeftB);
    setRotationAngle(this.earLeftB, 0.0F, -0.5672F, -0.0436F);
    this.earLeftB.field_78804_l.add(new ModelBox(this.earLeftB, 132, 0, 0.0F, -2.0F, -2.0F, 10, 4, 4, 0.0F));
    this.earLeftB.field_78804_l.add(new ModelBox(this.earLeftB, 124, 42, -1.0F, -2.0F, 0.0F, 18, 31, 0, 0.0F));
    this.earLeft2B = new ModelRenderer(this);
    this.earLeft2B.func_78793_a(10.0F, 0.0F, 0.0F);
    this.earLeftB.func_78792_a(this.earLeft2B);
    setRotationAngle(this.earLeft2B, 0.0F, 0.0F, 0.6545F);
    this.earLeft2B.field_78804_l.add(new ModelBox(this.earLeft2B, 132, 0, -1.0F, -2.0F, -2.0F, 10, 4, 4, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    EntityElephant elephant = (EntityElephant)entity;
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    if (elephant.func_70654_ax() == 0) {
      this.bone.func_78785_a(f5);
    } else if (elephant.func_70654_ax() == 1) {
      this.boneA.func_78785_a(f5);
    } else if (elephant.func_70654_ax() == 2) {
      this.boneB.func_78785_a(f5);
    } 
    GL11.glDisable(3042);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
    EntityElephant elephant = (EntityElephant)e;
    super.func_78087_a(f, f1, f2, f3, f4, f5, e);
    if (elephant.func_70654_ax() == 0) {
      this.legRight.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1;
      this.armLeft.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1;
      this.armRight.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1;
      this.head.field_78796_g = f3 / 57.295776F;
      this.head.field_78795_f = f4 / 57.295776F;
      this.legLeft.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1;
    } else if (elephant.func_70654_ax() == 1) {
      this.legRightA.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1;
      this.armLeftA.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1;
      this.armRightA.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1;
      this.headA.field_78796_g = f3 / 57.295776F;
      this.headA.field_78795_f = f4 / 57.295776F;
      this.legLeftA.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1;
    } else if (elephant.func_70654_ax() == 2) {
      this.legRightB.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1;
      this.armLeftB.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1;
      this.armRightB.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1;
      this.headB.field_78796_g = f3 / 57.295776F;
      this.headB.field_78795_f = f4 / 57.295776F;
      this.legLeftB.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\models\ModelElephant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */