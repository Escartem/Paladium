package fr.paladium.palamod.modules.palaboss.client.models;

import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelBeepBoop extends ModelBiped {
  private float renderTickTime;
  
  private final ModelRenderer bone;
  
  private final ModelRenderer body;
  
  private final ModelRenderer machinery;
  
  private final ModelRenderer bobyBottom;
  
  private final ModelRenderer gear1;
  
  private final ModelRenderer part1;
  
  private final ModelRenderer part2;
  
  private final ModelRenderer part3;
  
  private final ModelRenderer gear2;
  
  private final ModelRenderer part4;
  
  private final ModelRenderer part5;
  
  private final ModelRenderer part6;
  
  private final ModelRenderer gear3;
  
  private final ModelRenderer part7;
  
  private final ModelRenderer part8;
  
  private final ModelRenderer part9;
  
  private final ModelRenderer head;
  
  private final ModelRenderer lent;
  
  private final ModelRenderer gear4;
  
  private final ModelRenderer part10;
  
  private final ModelRenderer part11;
  
  private final ModelRenderer armRight;
  
  private final ModelRenderer forearmRight;
  
  private final ModelRenderer drill;
  
  private final ModelRenderer drillPart1;
  
  private final ModelRenderer drillPart2;
  
  private final ModelRenderer drillPart3;
  
  private final ModelRenderer drillPart4;
  
  private final ModelRenderer drillPart5;
  
  private final ModelRenderer drillPart6;
  
  private final ModelRenderer armLeft;
  
  private final ModelRenderer forearmLeft;
  
  private final ModelRenderer legRight;
  
  private final ModelRenderer legLeft;
  
  public ModelBeepBoop() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.bone = new ModelRenderer((ModelBase)this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.body = new ModelRenderer((ModelBase)this);
    this.body.func_78793_a(0.1667F, -17.4167F, 0.1667F);
    this.bone.func_78792_a(this.body);
    setRotationAngle(this.body, 0.1745F, 0.0F, 0.0F);
    this.body.field_78804_l.add(new ModelBox(this.body, 0, 20, -11.1667F, -27.5833F, -7.1667F, 22, 15, 13, 1.0F));
    this.machinery = new ModelRenderer((ModelBase)this);
    this.machinery.func_78793_a(0.0F, 0.0F, 0.0F);
    this.body.func_78792_a(this.machinery);
    this.machinery.field_78804_l
      .add(new ModelBox(this.machinery, 0, 67, -11.0F, -28.2056F, 6.0801F, 9, 16, 7, 0.0F));
    this.machinery.field_78804_l
      .add(new ModelBox(this.machinery, 122, 111, -1.0F, -28.2056F, 9.0801F, 2, 16, 1, 0.0F));
    this.machinery.field_78804_l
      .add(new ModelBox(this.machinery, 122, 111, 2.0F, -28.2056F, 9.0801F, 2, 16, 1, 0.0F));
    this.machinery.field_78804_l
      .add(new ModelBox(this.machinery, 122, 111, 5.0F, -28.2056F, 9.0801F, 2, 16, 1, 0.0F));
    this.machinery.field_78804_l
      .add(new ModelBox(this.machinery, 116, 83, 8.0F, -28.2056F, 6.0801F, 2, 16, 4, 0.0F));
    this.machinery.field_78804_l
      .add(new ModelBox(this.machinery, 90, 122, -2.0F, -28.3041F, 6.0975F, 10, 2, 4, 0.0F));
    this.machinery.field_78804_l
      .add(new ModelBox(this.machinery, 90, 122, -2.0F, -14.0724F, 6.2597F, 10, 2, 4, 0.0F));
    this.machinery.field_78804_l
      .add(new ModelBox(this.machinery, 34, 78, -9.0F, -37.4162F, 7.6733F, 2, 10, 2, 0.0F));
    this.machinery.field_78804_l
      .add(new ModelBox(this.machinery, 43, 81, -6.0F, -33.4162F, 7.6733F, 3, 6, 3, 0.0F));
    this.machinery.field_78804_l
      .add(new ModelBox(this.machinery, 0, 94, -3.5F, -34.4162F, 7.6733F, 1, 1, 3, 0.0F));
    this.machinery.field_78804_l
      .add(new ModelBox(this.machinery, 0, 91, -6.0F, -34.3293F, 10.1657F, 3, 1, 1, 0.0F));
    this.machinery.field_78804_l.add(new ModelBox(this.machinery, 0, 91, -6.0F, -34.503F, 7.1809F, 3, 1, 1, 0.0F));
    this.machinery.field_78804_l
      .add(new ModelBox(this.machinery, 0, 94, -6.5F, -34.4162F, 7.6733F, 1, 1, 3, 0.0F));
    this.bobyBottom = new ModelRenderer((ModelBase)this);
    this.bobyBottom.func_78793_a(-0.4667F, 0.8167F, 0.0333F);
    this.body.func_78792_a(this.bobyBottom);
    this.bobyBottom.field_78804_l.add(new ModelBox(this.bobyBottom, 68, 0, -7.7F, -3.4F, -2.2F, 15, 5, 5, 0.0F));
    this.bobyBottom.field_78804_l.add(new ModelBox(this.bobyBottom, 0, 49, -2.7F, -12.4F, -2.2F, 5, 9, 5, 0.0F));
    this.gear1 = new ModelRenderer((ModelBase)this);
    this.gear1.func_78793_a(-0.1286F, -12.2571F, -0.2F);
    this.bobyBottom.func_78792_a(this.gear1);
    this.gear1.field_78804_l.add(new ModelBox(this.gear1, 38, 54, 5.4286F, -0.1429F, -4.0F, 5, 2, 8, 0.0F));
    this.gear1.field_78804_l.add(new ModelBox(this.gear1, 52, 48, 10.4286F, -0.1429F, -2.0F, 2, 2, 4, 0.0F));
    this.gear1.field_78804_l.add(new ModelBox(this.gear1, 52, 48, -11.5714F, -0.1429F, -2.0F, 2, 2, 4, 0.0F));
    this.gear1.field_78804_l.add(new ModelBox(this.gear1, 38, 54, -9.5714F, -0.1429F, -4.0F, 5, 2, 8, 0.0F));
    this.part1 = new ModelRenderer((ModelBase)this);
    this.part1.func_78793_a(0.4286F, 0.8571F, 0.0F);
    this.gear1.func_78792_a(this.part1);
    setRotationAngle(this.part1, 0.0F, -0.7854F, 0.0F);
    this.part1.field_78804_l.add(new ModelBox(this.part1, 38, 54, 5.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.part1.field_78804_l.add(new ModelBox(this.part1, 52, 48, 10.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part1.field_78804_l.add(new ModelBox(this.part1, 52, 48, -12.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part1.field_78804_l.add(new ModelBox(this.part1, 38, 54, -10.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.part2 = new ModelRenderer((ModelBase)this);
    this.part2.func_78793_a(0.4286F, 0.8571F, 0.0F);
    this.gear1.func_78792_a(this.part2);
    setRotationAngle(this.part2, 0.0F, -1.5708F, 0.0F);
    this.part2.field_78804_l.add(new ModelBox(this.part2, 38, 54, 5.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.part2.field_78804_l.add(new ModelBox(this.part2, 52, 48, 10.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part2.field_78804_l.add(new ModelBox(this.part2, 52, 48, -12.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part2.field_78804_l.add(new ModelBox(this.part2, 38, 54, -10.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.part3 = new ModelRenderer((ModelBase)this);
    this.part3.func_78793_a(0.4286F, 0.8571F, 0.0F);
    this.gear1.func_78792_a(this.part3);
    setRotationAngle(this.part3, 0.0F, -2.3562F, 0.0F);
    this.part3.field_78804_l.add(new ModelBox(this.part3, 38, 54, 5.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.part3.field_78804_l.add(new ModelBox(this.part3, 52, 48, 10.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part3.field_78804_l.add(new ModelBox(this.part3, 52, 48, -12.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part3.field_78804_l.add(new ModelBox(this.part3, 38, 54, -10.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.gear2 = new ModelRenderer((ModelBase)this);
    this.gear2.func_78793_a(-0.1286F, -9.5429F, -0.2F);
    this.bobyBottom.func_78792_a(this.gear2);
    this.gear2.field_78804_l.add(new ModelBox(this.gear2, 38, 54, 5.4286F, 0.1429F, -4.0F, 5, 2, 8, 0.0F));
    this.gear2.field_78804_l.add(new ModelBox(this.gear2, 52, 48, 10.4286F, 0.1429F, -2.0F, 2, 2, 4, 0.0F));
    this.gear2.field_78804_l.add(new ModelBox(this.gear2, 52, 48, -11.5714F, 0.1429F, -2.0F, 2, 2, 4, 0.0F));
    this.gear2.field_78804_l.add(new ModelBox(this.gear2, 38, 54, -9.5714F, 0.1429F, -4.0F, 5, 2, 8, 0.0F));
    this.part4 = new ModelRenderer((ModelBase)this);
    this.part4.func_78793_a(0.4286F, 1.1429F, 0.0F);
    this.gear2.func_78792_a(this.part4);
    setRotationAngle(this.part4, 0.0F, -0.7854F, 0.0F);
    this.part4.field_78804_l.add(new ModelBox(this.part4, 38, 54, 5.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.part4.field_78804_l.add(new ModelBox(this.part4, 52, 48, 10.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part4.field_78804_l.add(new ModelBox(this.part4, 52, 48, -12.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part4.field_78804_l.add(new ModelBox(this.part4, 38, 54, -10.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.part5 = new ModelRenderer((ModelBase)this);
    this.part5.func_78793_a(0.4286F, 1.1429F, 0.0F);
    this.gear2.func_78792_a(this.part5);
    setRotationAngle(this.part5, 0.0F, -1.5708F, 0.0F);
    this.part5.field_78804_l.add(new ModelBox(this.part5, 38, 54, 5.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.part5.field_78804_l.add(new ModelBox(this.part5, 52, 48, 10.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part5.field_78804_l.add(new ModelBox(this.part5, 52, 48, -12.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part5.field_78804_l.add(new ModelBox(this.part5, 38, 54, -10.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.part6 = new ModelRenderer((ModelBase)this);
    this.part6.func_78793_a(0.4286F, 1.1429F, 0.0F);
    this.gear2.func_78792_a(this.part6);
    setRotationAngle(this.part6, 0.0F, -2.3562F, 0.0F);
    this.part6.field_78804_l.add(new ModelBox(this.part6, 38, 54, 5.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.part6.field_78804_l.add(new ModelBox(this.part6, 52, 48, 10.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part6.field_78804_l.add(new ModelBox(this.part6, 52, 48, -12.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part6.field_78804_l.add(new ModelBox(this.part6, 38, 54, -10.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.gear3 = new ModelRenderer((ModelBase)this);
    this.gear3.func_78793_a(-0.1286F, -5.8286F, -0.2F);
    this.bobyBottom.func_78792_a(this.gear3);
    this.gear3.field_78804_l.add(new ModelBox(this.gear3, 38, 54, 5.4286F, -0.5714F, -4.0F, 5, 2, 8, 0.0F));
    this.gear3.field_78804_l.add(new ModelBox(this.gear3, 52, 48, 10.4286F, -0.5714F, -2.0F, 2, 2, 4, 0.0F));
    this.gear3.field_78804_l.add(new ModelBox(this.gear3, 52, 48, -11.5714F, -0.5714F, -2.0F, 2, 2, 4, 0.0F));
    this.gear3.field_78804_l.add(new ModelBox(this.gear3, 38, 54, -9.5714F, -0.5714F, -4.0F, 5, 2, 8, 0.0F));
    this.part7 = new ModelRenderer((ModelBase)this);
    this.part7.func_78793_a(0.4286F, 0.4286F, 0.0F);
    this.gear3.func_78792_a(this.part7);
    setRotationAngle(this.part7, 0.0F, -0.7854F, 0.0F);
    this.part7.field_78804_l.add(new ModelBox(this.part7, 38, 54, 5.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.part7.field_78804_l.add(new ModelBox(this.part7, 52, 48, 10.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part7.field_78804_l.add(new ModelBox(this.part7, 52, 48, -12.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part7.field_78804_l.add(new ModelBox(this.part7, 38, 54, -10.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.part8 = new ModelRenderer((ModelBase)this);
    this.part8.func_78793_a(0.4286F, 0.4286F, 0.0F);
    this.gear3.func_78792_a(this.part8);
    setRotationAngle(this.part8, 0.0F, -1.5708F, 0.0F);
    this.part8.field_78804_l.add(new ModelBox(this.part8, 38, 54, 5.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.part8.field_78804_l.add(new ModelBox(this.part8, 52, 48, 10.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part8.field_78804_l.add(new ModelBox(this.part8, 52, 48, -12.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part8.field_78804_l.add(new ModelBox(this.part8, 38, 54, -10.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.part9 = new ModelRenderer((ModelBase)this);
    this.part9.func_78793_a(0.4286F, 0.4286F, 0.0F);
    this.gear3.func_78792_a(this.part9);
    setRotationAngle(this.part9, 0.0F, -2.3562F, 0.0F);
    this.part9.field_78804_l.add(new ModelBox(this.part9, 38, 54, 5.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.part9.field_78804_l.add(new ModelBox(this.part9, 52, 48, 10.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part9.field_78804_l.add(new ModelBox(this.part9, 52, 48, -12.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F));
    this.part9.field_78804_l.add(new ModelBox(this.part9, 38, 54, -10.0F, -1.0F, -4.0F, 5, 2, 8, 0.0F));
    this.head = new ModelRenderer((ModelBase)this);
    this.head.func_78793_a(-0.1667F, -29.5833F, -0.1667F);
    this.body.func_78792_a(this.head);
    this.head.field_78804_l.add(new ModelBox(this.head, 0, 0, -6.0F, -10.5F, -5.0F, 12, 10, 10, 0.0F));
    this.head.field_78804_l.add(new ModelBox(this.head, 52, 0, -1.5F, -4.0F, -1.5F, 3, 6, 3, 0.0F));
    this.lent = new ModelRenderer((ModelBase)this);
    this.lent.func_78793_a(-2.0F, -0.5F, -0.5F);
    this.head.func_78792_a(this.lent);
    this.lent.field_78804_l.add(new ModelBox(this.lent, 68, 10, 5.0F, -7.0F, -6.0F, 1, 4, 2, 0.0F));
    this.lent.field_78804_l.add(new ModelBox(this.lent, 75, 10, -2.0F, -7.0F, -6.0F, 1, 4, 2, 0.0F));
    this.lent.field_78804_l.add(new ModelBox(this.lent, 88, 11, -2.0F, -3.0F, -6.0F, 8, 1, 2, 0.0F));
    this.lent.field_78804_l.add(new ModelBox(this.lent, 88, 11, -2.0F, -8.0F, -6.0F, 8, 1, 2, 0.0F));
    this.gear4 = new ModelRenderer((ModelBase)this);
    this.gear4.func_78793_a(-1.0F, 11.75F, 1.5F);
    this.head.func_78792_a(this.gear4);
    this.gear4.field_78804_l.add(new ModelBox(this.gear4, 26, 61, -1.0F, -12.0F, 0.0F, 4, 1, 2, 0.0F));
    this.gear4.field_78804_l.add(new ModelBox(this.gear4, 32, 59, 0.0F, -12.0F, 2.0F, 2, 1, 1, 0.0F));
    this.gear4.field_78804_l.add(new ModelBox(this.gear4, 32, 59, 0.0F, -12.0F, -6.0F, 2, 1, 1, 0.0F));
    this.gear4.field_78804_l.add(new ModelBox(this.gear4, 26, 61, -1.0F, -12.0F, -5.0F, 4, 1, 2, 0.0F));
    this.part10 = new ModelRenderer((ModelBase)this);
    this.part10.func_78793_a(1.0F, -11.5F, -1.5F);
    this.gear4.func_78792_a(this.part10);
    setRotationAngle(this.part10, 0.0F, -1.0472F, 0.0F);
    this.part10.field_78804_l.add(new ModelBox(this.part10, 26, 61, -2.0F, -0.5F, 1.5F, 4, 1, 2, 0.0F));
    this.part10.field_78804_l.add(new ModelBox(this.part10, 32, 59, -1.0F, -0.5F, 3.5F, 2, 1, 1, 0.0F));
    this.part10.field_78804_l.add(new ModelBox(this.part10, 32, 59, -1.0F, -0.5F, -4.5F, 2, 1, 1, 0.0F));
    this.part10.field_78804_l.add(new ModelBox(this.part10, 26, 61, -2.0F, -0.5F, -3.5F, 4, 1, 2, 0.0F));
    this.part11 = new ModelRenderer((ModelBase)this);
    this.part11.func_78793_a(1.0F, -11.5F, -1.5F);
    this.gear4.func_78792_a(this.part11);
    setRotationAngle(this.part11, 0.0F, -2.0944F, 0.0F);
    this.part11.field_78804_l.add(new ModelBox(this.part11, 26, 61, -2.0F, -0.5F, 1.5F, 4, 1, 2, 0.0F));
    this.part11.field_78804_l.add(new ModelBox(this.part11, 32, 59, -1.0F, -0.5F, 3.5F, 2, 1, 1, 0.0F));
    this.part11.field_78804_l.add(new ModelBox(this.part11, 32, 59, -1.0F, -0.5F, -4.5F, 2, 1, 1, 0.0F));
    this.part11.field_78804_l.add(new ModelBox(this.part11, 26, 61, -2.0F, -0.5F, -3.5F, 4, 1, 2, 0.0F));
    this.armRight = new ModelRenderer((ModelBase)this);
    this.armRight.func_78793_a(-14.6667F, -22.0833F, -1.1667F);
    this.body.func_78792_a(this.armRight);
    setRotationAngle(this.armRight, -0.1745F, 0.0F, 0.0F);
    this.armRight.field_78804_l.add(new ModelBox(this.armRight, 98, 43, -2.5F, -4.5F, -5.0F, 5, 9, 10, 0.0F));
    this.armRight.field_78804_l.add(new ModelBox(this.armRight, 118, 63, -1.5F, 4.5F, 2.0F, 3, 9, 2, 0.0F));
    this.armRight.field_78804_l.add(new ModelBox(this.armRight, 118, 63, -1.5F, 4.5F, -3.0F, 3, 9, 2, 0.0F));
    this.forearmRight = new ModelRenderer((ModelBase)this);
    this.forearmRight.func_78793_a(0.0F, 16.0F, 0.0F);
    this.armRight.func_78792_a(this.forearmRight);
    setRotationAngle(this.forearmRight, -0.1745F, 0.0F, 0.0F);
    this.forearmRight.field_78804_l
      .add(new ModelBox(this.forearmRight, 91, 64, -2.5F, -4.5F, -4.0F, 5, 7, 8, 0.0F));
    this.forearmRight.field_78804_l
      .add(new ModelBox(this.forearmRight, 118, 63, -1.5F, 2.5304F, 1.6527F, 3, 9, 2, 0.0F));
    this.forearmRight.field_78804_l
      .add(new ModelBox(this.forearmRight, 118, 63, -1.5F, 2.5304F, -3.3473F, 3, 9, 2, 0.0F));
    this.drill = new ModelRenderer((ModelBase)this);
    this.drill.func_78793_a(0.0F, 10.0F, 0.0F);
    this.forearmRight.func_78792_a(this.drill);
    this.drill.field_78804_l.add(new ModelBox(this.drill, 0, 116, -4.0F, -2.0F, -4.0F, 8, 4, 8, 0.0F));
    this.drillPart1 = new ModelRenderer((ModelBase)this);
    this.drillPart1.func_78793_a(0.0F, 2.5868F, 0.0076F);
    this.drill.func_78792_a(this.drillPart1);
    this.drillPart1.field_78804_l.add(new ModelBox(this.drillPart1, 45, 120, -3.5F, -0.5F, -3.5F, 7, 1, 7, 0.0F));
    this.drillPart2 = new ModelRenderer((ModelBase)this);
    this.drillPart2.func_78793_a(0.0F, 4.1736F, 0.0152F);
    this.drill.func_78792_a(this.drillPart2);
    this.drillPart2.field_78804_l.add(new ModelBox(this.drillPart2, 45, 120, -3.0F, -1.0F, -3.0F, 6, 2, 6, 0.0F));
    this.drillPart3 = new ModelRenderer((ModelBase)this);
    this.drillPart3.func_78793_a(0.0F, 6.0295F, -0.1943F);
    this.drill.func_78792_a(this.drillPart3);
    this.drillPart3.field_78804_l.add(new ModelBox(this.drillPart3, 48, 121, -2.5F, -1.0F, -2.5F, 5, 2, 5, 0.0F));
    this.drillPart4 = new ModelRenderer((ModelBase)this);
    this.drillPart4.func_78793_a(0.0F, 7.8701F, -0.2301F);
    this.drill.func_78792_a(this.drillPart4);
    this.drillPart4.field_78804_l.add(new ModelBox(this.drillPart4, 48, 121, -2.0F, -1.0F, -2.0F, 4, 2, 4, 0.0F));
    this.drillPart5 = new ModelRenderer((ModelBase)this);
    this.drillPart5.func_78793_a(0.0F, 9.7107F, -0.2659F);
    this.drill.func_78792_a(this.drillPart5);
    this.drillPart5.field_78804_l.add(new ModelBox(this.drillPart5, 48, 121, -1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F));
    this.drillPart6 = new ModelRenderer((ModelBase)this);
    this.drillPart6.func_78793_a(0.0F, 10.5589F, -0.3885F);
    this.drill.func_78792_a(this.drillPart6);
    this.drillPart6.field_78804_l.add(new ModelBox(this.drillPart6, 48, 121, -1.0F, -2.5F, -1.0F, 2, 5, 2, 0.0F));
    this.armLeft = new ModelRenderer((ModelBase)this);
    this.armLeft.func_78793_a(10.7083F, -21.7083F, -0.6667F);
    this.body.func_78792_a(this.armLeft);
    setRotationAngle(this.armLeft, -0.1745F, 0.0F, 0.0F);
    this.armLeft.field_78804_l.add(new ModelBox(this.armLeft, 98, 43, 1.125F, -4.875F, -5.5F, 5, 9, 10, 0.0F));
    this.armLeft.field_78804_l.add(new ModelBox(this.armLeft, 118, 63, 2.125F, 4.125F, 1.5F, 3, 9, 2, 0.0F));
    this.armLeft.field_78804_l.add(new ModelBox(this.armLeft, 118, 63, 2.125F, 4.125F, -3.5F, 3, 9, 2, 0.0F));
    this.forearmLeft = new ModelRenderer((ModelBase)this);
    this.forearmLeft.func_78793_a(3.625F, 15.625F, -0.5F);
    this.armLeft.func_78792_a(this.forearmLeft);
    setRotationAngle(this.forearmLeft, -0.1745F, 0.0F, 0.0F);
    this.forearmLeft.field_78804_l.add(new ModelBox(this.forearmLeft, 91, 64, -2.5F, -4.5F, -4.0F, 5, 7, 8, 0.0F));
    this.forearmLeft.field_78804_l
      .add(new ModelBox(this.forearmLeft, 118, 63, -1.5F, 2.5304F, 1.6527F, 3, 9, 2, 0.0F));
    this.forearmLeft.field_78804_l
      .add(new ModelBox(this.forearmLeft, 118, 63, -1.5F, 2.5304F, -3.3473F, 3, 9, 2, 0.0F));
    this.legRight = new ModelRenderer((ModelBase)this);
    this.legRight.func_78793_a(-6.5F, -17.4167F, 1.0417F);
    this.bone.func_78792_a(this.legRight);
    this.legRight.field_78804_l
      .add(new ModelBox(this.legRight, 104, 17, -3.5F, -2.8333F, -4.0417F, 5, 7, 7, 0.0F));
    this.legRight.field_78804_l
      .add(new ModelBox(this.legRight, 104, 31, -3.5F, 10.1667F, -0.0417F, 5, 7, 3, 0.0F));
    this.legRight.field_78804_l.add(new ModelBox(this.legRight, 88, 31, -3.5F, 13.1667F, -3.0417F, 5, 4, 3, 0.0F));
    this.legRight.field_78804_l.add(new ModelBox(this.legRight, 120, 31, -3.25F, 4.1667F, 0.2083F, 2, 7, 2, 0.0F));
    this.legRight.field_78804_l.add(new ModelBox(this.legRight, 96, 19, -2.0F, 4.1667F, -2.7917F, 2, 10, 2, 0.0F));
    this.legRight.field_78804_l.add(new ModelBox(this.legRight, 120, 31, -0.75F, 4.1667F, 0.2083F, 2, 7, 2, 0.0F));
    this.legLeft = new ModelRenderer((ModelBase)this);
    this.legLeft.func_78793_a(6.5F, -17.4167F, 1.0417F);
    this.bone.func_78792_a(this.legLeft);
    this.legLeft.field_78804_l.add(new ModelBox(this.legLeft, 104, 17, -1.5F, -2.8333F, -4.0417F, 5, 7, 7, 0.0F));
    this.legLeft.field_78804_l.add(new ModelBox(this.legLeft, 104, 31, -1.5F, 10.1667F, -0.0417F, 5, 7, 3, 0.0F));
    this.legLeft.field_78804_l.add(new ModelBox(this.legLeft, 88, 31, -1.5F, 13.1667F, -3.0417F, 5, 4, 3, 0.0F));
    this.legLeft.field_78804_l.add(new ModelBox(this.legLeft, 120, 31, -1.25F, 4.1667F, 0.2083F, 2, 7, 2, 0.0F));
    this.legLeft.field_78804_l.add(new ModelBox(this.legLeft, 96, 19, 0.0F, 4.1667F, -2.7917F, 2, 10, 2, 0.0F));
    this.legLeft.field_78804_l.add(new ModelBox(this.legLeft, 120, 31, 1.25F, 4.1667F, 0.2083F, 2, 7, 2, 0.0F));
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
    this.drillPart4.field_78796_g = f2 / 5.0F;
    this.drillPart5.field_78796_g = f2 / 20.0F;
    this.drillPart2.field_78796_g = f2 / 5.0F;
    this.drillPart3.field_78796_g = f2 / 20.0F;
    this.drillPart1.field_78796_g = f2 / 20.0F;
    this.drillPart6.field_78796_g = f2 / 5.0F;
    this.head.field_78796_g = f3 / 57.295776F;
    this.gear1.field_78796_g = f2 / 20.0F;
    this.gear2.field_78796_g = f2 * -1.0F / 20.0F;
    this.gear3.field_78796_g = f2 / 20.0F;
    this.legRight.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * 1.0F * f1;
    this.armLeft.field_78795_f = MathHelper.func_76134_b(f * 0.6662F) * f1;
    this.forearmRight.field_78795_f = MathHelper.func_76134_b(f * 0.6662F) * f1 + 5.0F;
    this.forearmLeft.field_78795_f = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * f1 + 5.0F;
    this.legLeft.field_78795_f = MathHelper.func_76134_b(f * 1.0F) * -1.0F * f1;
    EntityBossBase entityBossBase = (EntityBossBase)e;
    int i = entityBossBase.getPunchTimer();
    if (i > 0) {
      this.armRight
        .field_78795_f = -2.0F + 1.5F * func_78172_a(i - this.renderTickTime, 10.0F);
    } else {
      this.armRight.field_78795_f = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * f1;
    } 
  }
  
  public void func_78086_a(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
    this.renderTickTime = p_78086_4_;
  }
  
  private float func_78172_a(float p_78172_1_, float p_78172_2_) {
    return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / p_78172_2_ * 0.25F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\models\ModelBeepBoop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */