package fr.paladium.palamod.modules.back2future.client.model;

import fr.paladium.palamod.modules.back2future.core.utils.ModelUtils;
import fr.paladium.palamod.modules.back2future.entities.EntityGolem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelGolem extends ModelBase {
  public ModelRenderer WAIST;
  
  public ModelRenderer HIP;
  
  public ModelRenderer Body;
  
  public ModelRenderer RARM;
  
  public ModelRenderer LARM;
  
  public ModelRenderer LRShoulder2;
  
  public ModelRenderer LFShoulder2;
  
  public ModelRenderer RFShoulder2;
  
  public ModelRenderer RRShoulder2;
  
  public ModelRenderer RFShoulder1;
  
  public ModelRenderer RRShoulder1;
  
  public ModelRenderer LRShoulder1;
  
  public ModelRenderer LFShoulder1;
  
  public ModelRenderer RChest;
  
  public ModelRenderer LChest;
  
  public ModelRenderer HEAD;
  
  public ModelRenderer RArm1;
  
  public ModelRenderer RArm2;
  
  public ModelRenderer LArm1;
  
  public ModelRenderer LArm2;
  
  public ModelRenderer LFHip;
  
  public ModelRenderer LLEG;
  
  public ModelRenderer RLEG;
  
  public ModelRenderer RRHip;
  
  public ModelRenderer LRHip;
  
  public ModelRenderer RFHip;
  
  public ModelRenderer LLeg1;
  
  public ModelRenderer LLeg2;
  
  public ModelRenderer RLeg1;
  
  public ModelRenderer RLeg2;
  
  public ModelGolem() {
    this.field_78090_t = 16;
    this.field_78089_u = 16;
    this.RRShoulder1 = new ModelRenderer(this, 0, 0);
    this.RRShoulder1.func_78793_a(-5.52F, -47.0F, -5.5F);
    this.RRShoulder1.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.RRShoulder1, 0.2617994F, 0.0F, -0.2617994F);
    this.RArm1 = new ModelRenderer(this, 0, 0);
    this.RArm1.func_78793_a(0.02F, 12.0F, 0.01F);
    this.RArm1.func_78790_a(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.RArm1, 0.43633232F, 0.0F, 0.0F);
    this.RRShoulder2 = new ModelRenderer(this, 0, 0);
    this.RRShoulder2.func_78793_a(-4.2F, -42.0F, -18.0F);
    this.RRShoulder2.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.RRShoulder2, 0.5235988F, 0.0F, -0.2617994F);
    this.RArm2 = new ModelRenderer(this, 0, 0);
    this.RArm2.func_78793_a(0.02F, 12.0F, 0.01F);
    this.RArm2.func_78790_a(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.RArm2, 0.17453292F, 0.0F, 0.0F);
    this.RLEG = new ModelRenderer(this, 0, 0);
    this.RLEG.func_78793_a(0.0F, -11.0F, -7.0F);
    this.RLEG.func_78790_a(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.RLEG, -0.34906584F, 0.0F, 0.0F);
    this.WAIST = new ModelRenderer(this, 0, 0);
    this.WAIST.func_78793_a(0.0F, -25.0F, 0.0F);
    this.WAIST.func_78790_a(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F);
    this.LArm2 = new ModelRenderer(this, 0, 0);
    this.LArm2.func_78793_a(0.02F, 12.0F, 0.01F);
    this.LArm2.func_78790_a(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.LArm2, -0.17453292F, 0.0F, 0.0F);
    this.Body = new ModelRenderer(this, 0, 0);
    this.Body.func_78793_a(0.0F, 14.0F, 0.0F);
    this.Body.func_78790_a(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
    setRotateAngle(this.Body, 0.0F, 1.5707964F, 0.0F);
    this.RFShoulder1 = new ModelRenderer(this, 0, 0);
    this.RFShoulder1.func_78793_a(5.5F, -47.0F, -5.5F);
    this.RFShoulder1.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.RFShoulder1, 0.2617994F, 0.0F, 0.2617994F);
    this.LLEG = new ModelRenderer(this, 0, 0);
    this.LLEG.func_78793_a(0.0F, -11.0F, 7.0F);
    this.LLEG.func_78790_a(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.LLEG, 0.34906584F, 0.0F, 0.0F);
    this.LLeg1 = new ModelRenderer(this, 0, 0);
    this.LLeg1.func_78793_a(0.02F, 13.3F, 0.5F);
    this.LLeg1.func_78790_a(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.LLeg1, -0.34906584F, 0.0F, 0.0F);
    this.RLeg1 = new ModelRenderer(this, 0, 0);
    this.RLeg1.func_78793_a(0.02F, 13.3F, -0.5F);
    this.RLeg1.func_78790_a(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.RLeg1, 0.34906584F, 0.0F, 0.0F);
    this.HEAD = new ModelRenderer(this, 0, 0);
    this.HEAD.func_78793_a(0.0F, -54.0F, 0.0F);
    this.HEAD.func_78790_a(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F);
    this.LArm1 = new ModelRenderer(this, 0, 0);
    this.LArm1.func_78793_a(0.02F, 12.0F, 0.01F);
    this.LArm1.func_78790_a(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.LArm1, -0.43633232F, 0.0F, 0.0F);
    this.LRHip = new ModelRenderer(this, 0, 0);
    this.LRHip.func_78793_a(-5.52F, -9.0F, -5.5F);
    this.LRHip.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.LRHip, 0.2617994F, 0.0F, -0.2617994F);
    this.RFHip = new ModelRenderer(this, 0, 0);
    this.RFHip.func_78793_a(5.52F, -9.0F, 5.5F);
    this.RFHip.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.RFHip, -0.2617994F, 0.0F, 0.2617994F);
    this.RFShoulder2 = new ModelRenderer(this, 0, 0);
    this.RFShoulder2.func_78793_a(4.2F, -42.0F, -18.0F);
    this.RFShoulder2.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.RFShoulder2, 0.5235988F, 0.0F, 0.2617994F);
    this.LLeg2 = new ModelRenderer(this, 0, 0);
    this.LLeg2.func_78793_a(0.01F, 16.0F, 0.01F);
    this.LLeg2.func_78790_a(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
    this.LFHip = new ModelRenderer(this, 0, 0);
    this.LFHip.func_78793_a(5.5F, -9.0F, -5.5F);
    this.LFHip.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.LFHip, 0.2617994F, 0.0F, 0.2617994F);
    this.LRShoulder2 = new ModelRenderer(this, 0, 0);
    this.LRShoulder2.func_78793_a(-4.22F, -42.0F, 18.0F);
    this.LRShoulder2.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.LRShoulder2, -0.5235988F, 0.0F, -0.2617994F);
    this.RChest = new ModelRenderer(this, 0, 0);
    this.RChest.func_78793_a(0.0F, -38.0F, -8.0F);
    this.RChest.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    this.LFShoulder2 = new ModelRenderer(this, 0, 0);
    this.LFShoulder2.func_78793_a(4.21F, -42.0F, 18.0F);
    this.LFShoulder2.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.LFShoulder2, -0.5235988F, 0.0F, 0.2617994F);
    this.LChest = new ModelRenderer(this, 0, 0);
    this.LChest.func_78793_a(0.0F, -38.0F, 8.0F);
    this.LChest.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    this.RARM = new ModelRenderer(this, 0, 0);
    this.RARM.func_78793_a(0.0F, -40.0F, -21.0F);
    this.RARM.func_78790_a(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.RARM, -0.6981317F, 0.0F, 0.0F);
    this.RRHip = new ModelRenderer(this, 0, 0);
    this.RRHip.func_78793_a(-5.5F, -9.0F, 5.5F);
    this.RRHip.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.RRHip, -0.2617994F, 0.0F, -0.2617994F);
    this.RLeg2 = new ModelRenderer(this, 0, 0);
    this.RLeg2.func_78793_a(0.01F, 16.0F, 0.01F);
    this.RLeg2.func_78790_a(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
    this.HIP = new ModelRenderer(this, 0, 0);
    this.HIP.func_78793_a(0.0F, -9.0F, 0.0F);
    this.HIP.func_78790_a(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
    setRotateAngle(this.HIP, 0.0F, 1.5707964F, 0.0F);
    this.LRShoulder1 = new ModelRenderer(this, 0, 0);
    this.LRShoulder1.func_78793_a(-5.5F, -47.0F, 5.5F);
    this.LRShoulder1.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.LRShoulder1, -0.2617994F, 0.0F, -0.2617994F);
    this.LARM = new ModelRenderer(this, 0, 0);
    this.LARM.func_78793_a(0.0F, -40.0F, 21.0F);
    this.LARM.func_78790_a(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.LARM, 0.6981317F, 0.034906585F, 0.0F);
    this.LFShoulder1 = new ModelRenderer(this, 0, 0);
    this.LFShoulder1.func_78793_a(5.52F, -47.0F, 5.5F);
    this.LFShoulder1.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
    setRotateAngle(this.LFShoulder1, -0.2617994F, 0.0F, 0.2617994F);
    this.Body.func_78792_a(this.RRShoulder1);
    this.RARM.func_78792_a(this.RArm1);
    this.Body.func_78792_a(this.RRShoulder2);
    this.RArm1.func_78792_a(this.RArm2);
    this.HIP.func_78792_a(this.RLEG);
    this.LArm1.func_78792_a(this.LArm2);
    this.WAIST.func_78792_a(this.Body);
    this.Body.func_78792_a(this.RFShoulder1);
    this.HIP.func_78792_a(this.LLEG);
    this.LLEG.func_78792_a(this.LLeg1);
    this.RLEG.func_78792_a(this.RLeg1);
    this.Body.func_78792_a(this.HEAD);
    this.LARM.func_78792_a(this.LArm1);
    this.HIP.func_78792_a(this.LRHip);
    this.HIP.func_78792_a(this.RFHip);
    this.Body.func_78792_a(this.RFShoulder2);
    this.LLeg1.func_78792_a(this.LLeg2);
    this.HIP.func_78792_a(this.LFHip);
    this.Body.func_78792_a(this.LRShoulder2);
    this.Body.func_78792_a(this.RChest);
    this.Body.func_78792_a(this.LFShoulder2);
    this.Body.func_78792_a(this.LChest);
    this.Body.func_78792_a(this.RARM);
    this.HIP.func_78792_a(this.RRHip);
    this.RLeg1.func_78792_a(this.RLeg2);
    this.Body.func_78792_a(this.LRShoulder1);
    this.Body.func_78792_a(this.LARM);
    this.Body.func_78792_a(this.LFShoulder1);
    build_Build();
    build_Stand();
    build_Throw();
    build_Roll();
    Build_Stomp();
    Build_Die();
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.WAIST.func_78785_a(f5);
    this.HIP.func_78785_a(f5);
  }
  
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void func_78086_a(EntityLivingBase entity, float par2, float par3, float partialTick) {
    setLivingAnimations((EntityGolem)entity, par2, par3, partialTick);
  }
  
  private void setLivingAnimations(EntityGolem golem, float moveCounter, float speed, float PartialTick) {
    if (golem.aniID == 1) {
      Stand(golem.aniFrame, PartialTick);
      speed /= 2.0F;
      float RAngle = MathHelper.func_76134_b(moveCounter / 2.0F) * speed / 2.0F;
      float idle = MathHelper.func_76134_b((golem.field_70173_aa + PartialTick) / 20.0F) - 2.7F;
      this.LLEG.field_78808_h = RAngle * 3.0F;
      this.RLEG.field_78808_h = -RAngle * 3.0F;
      this.LLeg2.field_78808_h = (RAngle <= 0.0F) ? 0.0F : (RAngle * 3.0F);
      this.RLeg2.field_78808_h = (-RAngle <= 0.0F) ? 0.0F : (-RAngle * 3.0F);
      this.WAIST.field_78797_d = -24.0F - MathHelper.func_76134_b(moveCounter) * speed * 5.0F;
      this.HIP.field_78797_d = -9.0F - MathHelper.func_76134_b(moveCounter) * speed * 5.0F;
      this.LARM.field_78808_h = -RAngle * 3.0F;
      this.RARM.field_78808_h = RAngle * 3.0F;
      this.LArm2.field_78808_h = (-RAngle >= 0.0F) ? 0.0F : (-RAngle * 3.0F);
      this.RArm2.field_78808_h = (RAngle >= 0.0F) ? 0.0F : (RAngle * 3.0F);
      this.LARM.field_78795_f = -idle / 6.0F;
      this.RARM.field_78795_f = idle / 6.0F;
      this.LArm1.field_78795_f = (idle + 1.0F) / 10.0F;
      this.RArm1.field_78795_f = (-idle - 1.0F) / 10.0F;
    } else if (golem.aniID == 0) {
      Build(golem.aniFrame, PartialTick);
    } else if (golem.aniID == 2) {
      Throw(golem.aniFrame, PartialTick);
    } else if (golem.aniID == 3) {
      Roll(golem.aniFrame, PartialTick);
    } else if (golem.aniID == 4) {
      Stomp(golem.aniFrame, PartialTick);
    } else if (golem.aniID == 5) {
      Die(golem.aniFrame, PartialTick);
    } 
  }
  
  static final KeyFrame[] KF_Die_HEAD = new KeyFrame[1];
  
  static final KeyFrame[] KF_Die_LRShoulder2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Die_LFShoulder1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Die_RRShoulder2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Die_RFShoulder1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Die_LRShoulder1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Die_LFShoulder2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Die_RFShoulder2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Die_RRShoulder1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Die_LARM = new KeyFrame[6];
  
  static final KeyFrame[] KF_Die_LArm1 = new KeyFrame[5];
  
  static final KeyFrame[] KF_Die_LArm2 = new KeyFrame[6];
  
  static final KeyFrame[] KF_Die_RARM = new KeyFrame[6];
  
  static final KeyFrame[] KF_Die_RArm1 = new KeyFrame[5];
  
  static final KeyFrame[] KF_Die_RArm2 = new KeyFrame[5];
  
  static final KeyFrame[] KF_Die_RChest = new KeyFrame[1];
  
  static final KeyFrame[] KF_Die_LChest = new KeyFrame[1];
  
  static final KeyFrame[] KF_Die_WAIST = new KeyFrame[4];
  
  static final KeyFrame[] KF_Die_HIP = new KeyFrame[3];
  
  static final KeyFrame[] KF_Die_LFHip = new KeyFrame[4];
  
  static final KeyFrame[] KF_Die_RRHip = new KeyFrame[4];
  
  static final KeyFrame[] KF_Die_RFHip = new KeyFrame[4];
  
  static final KeyFrame[] KF_Die_LRHip = new KeyFrame[4];
  
  static final KeyFrame[] KF_Die_RLEG = new KeyFrame[5];
  
  static final KeyFrame[] KF_Die_RLeg1 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Die_RLeg2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Die_LLEG = new KeyFrame[5];
  
  static final KeyFrame[] KF_Die_LLeg1 = new KeyFrame[5];
  
  static final KeyFrame[] KF_Die_LLeg2 = new KeyFrame[5];
  
  public void Build_Die() {
    KF_Die_HEAD[0] = new KeyFrame(0, 0.0F, -54.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Die_LRShoulder2[0] = new KeyFrame(0, -4.22F, -42.0F, 18.0F, -30.0F, 0.0F, -15.0F);
    KF_Die_LRShoulder2[1] = new KeyFrame(37, -4.22F, -42.0F, 18.0F, -30.0F, 0.0F, -15.0F);
    KF_Die_LRShoulder2[2] = new KeyFrame(47, -29.22F, 50.0F, 18.0F, -30.0F, 0.0F, 100.0F);
    KF_Die_LRShoulder2[3] = new KeyFrame(54, -29.22F, 50.0F, 18.0F, -30.0F, 0.0F, 100.0F);
    KF_Die_LFShoulder1[0] = new KeyFrame(0, 5.52F, -47.0F, 5.5F, -15.0F, 0.0F, 15.0F);
    KF_Die_RRShoulder2[0] = new KeyFrame(0, -4.2F, -42.0F, -18.0F, 30.0F, 0.0F, -15.0F);
    KF_Die_RRShoulder2[1] = new KeyFrame(35, -4.2F, -42.0F, -18.0F, 30.0F, 0.0F, -15.0F);
    KF_Die_RRShoulder2[2] = new KeyFrame(45, -21.2F, 47.0F, -18.0F, 30.0F, 0.0F, 100.0F);
    KF_Die_RRShoulder2[3] = new KeyFrame(54, -21.2F, 47.0F, -18.0F, 30.0F, 0.0F, 100.0F);
    KF_Die_RFShoulder1[0] = new KeyFrame(0, 5.5F, -47.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Die_LRShoulder1[0] = new KeyFrame(0, -5.5F, -47.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Die_RRShoulder1[0] = new KeyFrame(0, -5.52F, -47.0F, -5.5F, 15.0F, 0.0F, -15.0F);
    KF_Die_LFShoulder2[0] = new KeyFrame(0, 4.21F, -42.0F, 18.0F, -30.0F, 0.0F, 15.0F);
    KF_Die_LFShoulder2[1] = new KeyFrame(34, 4.21F, -42.0F, 18.0F, -30.0F, 0.0F, 15.0F);
    KF_Die_LFShoulder2[2] = new KeyFrame(44, 4.21F, 55.0F, 18.0F, -30.0F, 0.0F, 200.0F);
    KF_Die_LFShoulder2[3] = new KeyFrame(54, 4.21F, 55.0F, 18.0F, -30.0F, 0.0F, 200.0F);
    KF_Die_RFShoulder2[0] = new KeyFrame(0, 4.2F, -42.0F, -18.0F, 30.0F, 0.0F, 15.0F);
    KF_Die_RFShoulder2[1] = new KeyFrame(38, 4.2F, -42.0F, -18.0F, 30.0F, 0.0F, 15.0F);
    KF_Die_RFShoulder2[2] = new KeyFrame(48, -8.8F, 50.0F, -18.0F, 30.0F, 0.0F, 100.0F);
    KF_Die_RFShoulder2[3] = new KeyFrame(54, -8.8F, 50.0F, -18.0F, 30.0F, 0.0F, 100.0F);
    KF_Die_LARM[0] = new KeyFrame(0, 0.0F, -40.0F, 21.0F, 40.0F, 0.0F, 0.0F);
    KF_Die_LARM[1] = new KeyFrame(5, 0.0F, -40.0F, 21.0F, 40.0F, 0.0F, 30.0F);
    KF_Die_LARM[2] = new KeyFrame(32, 0.0F, -40.0F, 21.0F, 40.0F, 0.0F, 30.0F);
    KF_Die_LARM[3] = new KeyFrame(42, -10.0F, 43.0F, 19.0F, 40.0F, 0.0F, 0.0F);
    KF_Die_LARM[4] = new KeyFrame(49, -10.0F, 43.0F, 19.0F, 40.0F, 0.0F, 0.0F);
    KF_Die_LARM[5] = new KeyFrame(54, -45.0F, -25.0F, 19.0F, 40.0F, 0.0F, 0.0F);
    KF_Die_LArm1[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, -25.0F, 0.0F, 0.0F);
    KF_Die_LArm1[1] = new KeyFrame(26, 0.02F, 12.0F, 0.01F, -25.0F, 0.0F, 0.0F);
    KF_Die_LArm1[2] = new KeyFrame(36, 27.02F, 54.0F, -56.99F, 0.0F, 5.0F, -22.0F);
    KF_Die_LArm1[3] = new KeyFrame(49, 27.02F, 54.0F, -56.99F, 0.0F, 5.0F, -22.0F);
    KF_Die_LArm1[4] = new KeyFrame(54, 27.02F, 0.0F, -56.99F, 0.0F, 5.0F, -22.0F);
    KF_Die_LArm2[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, -10.0F, 0.0F, 0.0F);
    KF_Die_LArm2[1] = new KeyFrame(20, 0.02F, 12.0F, 0.01F, -10.0F, 0.0F, 0.0F);
    KF_Die_LArm2[2] = new KeyFrame(30, 48.02F, 79.0F, 0.01F, -10.0F, 0.0F, 100.0F);
    KF_Die_LArm2[3] = new KeyFrame(40, 48.02F, 79.0F, 0.01F, -10.0F, 0.0F, 100.0F);
    KF_Die_LArm2[4] = new KeyFrame(45, 10.02F, 0.0F, 0.01F, -10.0F, 0.0F, 100.0F);
    KF_Die_LArm2[5] = new KeyFrame(54, 10.02F, 0.0F, 0.01F, -10.0F, 0.0F, 100.0F);
    KF_Die_RARM[0] = new KeyFrame(0, 0.0F, -40.0F, -21.0F, -40.0F, 0.0F, 0.0F);
    KF_Die_RARM[1] = new KeyFrame(5, 0.0F, -40.0F, -21.0F, -40.0F, 0.0F, -109.0F);
    KF_Die_RARM[2] = new KeyFrame(30, 0.0F, -40.0F, -21.0F, -40.0F, 0.0F, -109.0F);
    KF_Die_RARM[3] = new KeyFrame(40, -15.0F, 54.0F, -21.0F, -40.0F, 0.0F, -109.0F);
    KF_Die_RARM[4] = new KeyFrame(49, -15.0F, 54.0F, -21.0F, -40.0F, 0.0F, -109.0F);
    KF_Die_RARM[5] = new KeyFrame(54, -32.0F, 4.0F, 5.0F, -40.0F, 0.0F, -109.0F);
    KF_Die_RArm1[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, 25.0F, 0.0F, 0.0F);
    KF_Die_RArm1[1] = new KeyFrame(27, 0.02F, 12.0F, 0.01F, 25.0F, 0.0F, 0.0F);
    KF_Die_RArm1[2] = new KeyFrame(37, -86.98F, -32.0F, -22.0F, 0.0F, 0.0F, 0.0F);
    KF_Die_RArm1[3] = new KeyFrame(49, -86.98F, -32.0F, -22.0F, 0.0F, 0.0F, 0.0F);
    KF_Die_RArm1[4] = new KeyFrame(54, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Die_RArm2[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, 10.0F, 0.0F, 0.0F);
    KF_Die_RArm2[1] = new KeyFrame(19, 0.02F, 12.0F, 0.01F, 10.0F, 0.0F, 0.0F);
    KF_Die_RArm2[2] = new KeyFrame(29, -93.98F, -36.0F, 0.01F, 10.0F, 0.0F, 100.0F);
    KF_Die_RArm2[3] = new KeyFrame(49, -93.98F, -36.0F, 0.01F, 10.0F, 0.0F, 100.0F);
    KF_Die_RArm2[4] = new KeyFrame(54, 0.0F, 0.0F, 0.0F, 10.0F, 0.0F, 100.0F);
    KF_Die_RChest[0] = new KeyFrame(0, 0.0F, -38.0F, -8.0F, 0.0F, 0.0F, 0.0F);
    KF_Die_LChest[0] = new KeyFrame(0, 0.0F, -38.0F, 8.0F, 0.0F, 0.0F, 0.0F);
    KF_Die_WAIST[0] = new KeyFrame(0, 0.0F, -25.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Die_WAIST[1] = new KeyFrame(5, 0.0F, -22.0F, 7.0F, -10.0F, 0.0F, 0.0F);
    KF_Die_WAIST[2] = new KeyFrame(49, 0.0F, -22.0F, 7.0F, -10.0F, 0.0F, 0.0F);
    KF_Die_WAIST[3] = new KeyFrame(54, 0.0F, 25.0F, 7.0F, -67.0F, 0.0F, 0.0F);
    KF_Die_HIP[0] = new KeyFrame(0, 0.0F, -9.0F, 0.0F, 0.0F, 90.0F, 0.0F);
    KF_Die_HIP[1] = new KeyFrame(5, 0.0F, -6.0F, 7.0F, 0.0F, 90.0F, 0.0F);
    KF_Die_HIP[2] = new KeyFrame(54, 0.0F, -6.0F, 7.0F, 0.0F, 90.0F, 0.0F);
    KF_Die_LFHip[0] = new KeyFrame(0, 5.5F, -9.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Die_LFHip[1] = new KeyFrame(33, 5.5F, -9.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Die_LFHip[2] = new KeyFrame(43, 5.5F, 51.0F, -5.5F, 15.0F, 0.0F, 100.0F);
    KF_Die_LFHip[3] = new KeyFrame(54, 5.5F, 51.0F, -5.5F, 15.0F, 0.0F, 100.0F);
    KF_Die_RRHip[0] = new KeyFrame(0, -5.5F, -9.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Die_RRHip[1] = new KeyFrame(36, -5.5F, -9.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Die_RRHip[2] = new KeyFrame(46, -5.5F, 46.0F, 5.5F, -15.0F, 0.0F, 200.0F);
    KF_Die_RRHip[3] = new KeyFrame(54, -5.5F, 46.0F, 5.5F, -15.0F, 0.0F, 200.0F);
    KF_Die_RFHip[0] = new KeyFrame(0, 5.52F, -9.0F, 5.5F, -15.0F, 0.0F, 15.0F);
    KF_Die_RFHip[1] = new KeyFrame(28, 5.52F, -9.0F, 5.5F, -15.0F, 0.0F, 15.0F);
    KF_Die_RFHip[2] = new KeyFrame(38, 5.52F, 47.0F, 5.5F, -15.0F, 0.0F, 100.0F);
    KF_Die_RFHip[3] = new KeyFrame(54, 5.52F, 47.0F, 5.5F, -15.0F, 0.0F, 100.0F);
    KF_Die_LRHip[0] = new KeyFrame(0, -5.52F, -9.0F, 5.5F, 15.0F, 0.0F, -15.0F);
    KF_Die_LRHip[1] = new KeyFrame(39, -5.52F, -9.0F, 5.5F, 15.0F, 0.0F, -15.0F);
    KF_Die_LRHip[2] = new KeyFrame(49, -5.52F, 50.0F, -5.5F, 15.0F, 0.0F, 100.0F);
    KF_Die_LRHip[3] = new KeyFrame(54, -5.52F, 50.0F, -5.5F, 15.0F, 0.0F, 100.0F);
    KF_Die_RLEG[0] = new KeyFrame(0, 0.0F, -11.0F, -7.0F, -20.0F, 0.0F, 0.0F);
    KF_Die_RLEG[1] = new KeyFrame(5, 0.0F, -10.0F, -7.0F, -20.0F, 0.0F, 35.0F);
    KF_Die_RLEG[2] = new KeyFrame(26, 0.0F, -10.0F, -7.0F, -20.0F, 0.0F, 35.0F);
    KF_Die_RLEG[3] = new KeyFrame(36, 0.0F, 38.0F, -7.0F, 0.0F, 0.0F, 0.0F);
    KF_Die_RLEG[4] = new KeyFrame(54, 0.0F, 38.0F, -7.0F, 0.0F, 0.0F, 0.0F);
    KF_Die_RLeg1[0] = new KeyFrame(0, 0.02F, 13.3F, -0.5F, 20.0F, 0.0F, 0.0F);
    KF_Die_RLeg1[1] = new KeyFrame(22, 0.02F, 13.3F, -0.5F, 20.0F, 0.0F, 0.0F);
    KF_Die_RLeg1[2] = new KeyFrame(32, 22.02F, 54.3F, 11.5F, 20.0F, 0.0F, 0.0F);
    KF_Die_RLeg1[3] = new KeyFrame(54, 22.02F, 54.3F, 11.5F, 20.0F, 0.0F, 0.0F);
    KF_Die_RLeg2[0] = new KeyFrame(0, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Die_RLeg2[1] = new KeyFrame(17, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Die_RLeg2[2] = new KeyFrame(27, 27.01F, 48.0F, 0.01F, 0.0F, 0.0F, 100.0F);
    KF_Die_RLeg2[3] = new KeyFrame(54, 27.01F, 48.0F, 0.01F, 0.0F, 0.0F, 100.0F);
    KF_Die_LLEG[0] = new KeyFrame(0, 0.0F, -11.0F, 7.0F, 20.0F, 0.0F, 0.0F);
    KF_Die_LLEG[1] = new KeyFrame(5, 0.0F, -11.0F, 7.0F, 20.0F, 0.0F, -20.0F);
    KF_Die_LLEG[2] = new KeyFrame(24, 0.0F, -11.0F, 7.0F, 20.0F, 0.0F, -20.0F);
    KF_Die_LLEG[3] = new KeyFrame(34, 0.0F, 40.0F, 7.0F, 0.0F, 0.0F, 0.0F);
    KF_Die_LLEG[4] = new KeyFrame(54, 0.0F, 40.0F, 7.0F, 0.0F, 0.0F, 0.0F);
    KF_Die_LLeg1[0] = new KeyFrame(0, 0.02F, 13.3F, 0.5F, -20.0F, 0.0F, 0.0F);
    KF_Die_LLeg1[1] = new KeyFrame(5, 0.02F, 13.3F, 0.5F, -20.0F, 0.0F, 10.0F);
    KF_Die_LLeg1[2] = new KeyFrame(21, 0.02F, 13.3F, 0.5F, -20.0F, 0.0F, 10.0F);
    KF_Die_LLeg1[3] = new KeyFrame(31, -10.98F, 48.3F, -11.5F, -20.0F, 0.0F, 10.0F);
    KF_Die_LLeg1[4] = new KeyFrame(54, -10.98F, 48.3F, -11.5F, -20.0F, 0.0F, 10.0F);
    KF_Die_LLeg2[0] = new KeyFrame(0, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Die_LLeg2[1] = new KeyFrame(5, 0.01F, 14.0F, 0.01F, 0.0F, 0.0F, 10.0F);
    KF_Die_LLeg2[2] = new KeyFrame(15, 0.01F, 14.0F, 0.01F, 0.0F, 0.0F, 10.0F);
    KF_Die_LLeg2[3] = new KeyFrame(25, 0.01F, 50.0F, 0.01F, 0.0F, 0.0F, 100.0F);
    KF_Die_LLeg2[4] = new KeyFrame(54, 0.01F, 50.0F, 0.01F, 0.0F, 0.0F, 100.0F);
  }
  
  public void Die(int frame, float partialTick) {
    ModelUtils.moveParts(frame, this.HIP, KF_Die_HIP, partialTick);
    ModelUtils.moveParts(frame, this.WAIST, KF_Die_WAIST, partialTick);
    ModelUtils.moveParts(frame, this.LFHip, KF_Die_LFHip, partialTick);
    ModelUtils.moveParts(frame, this.RChest, KF_Die_RChest, partialTick);
    ModelUtils.moveParts(frame, this.LRShoulder2, KF_Die_LRShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.RArm1, KF_Die_RArm1, partialTick);
    ModelUtils.moveParts(frame, this.LFShoulder1, KF_Die_LFShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.LArm2, KF_Die_LArm2, partialTick);
    ModelUtils.moveParts(frame, this.LARM, KF_Die_LARM, partialTick);
    ModelUtils.moveParts(frame, this.RLeg2, KF_Die_RLeg2, partialTick);
    ModelUtils.moveParts(frame, this.RRShoulder2, KF_Die_RRShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.RFShoulder1, KF_Die_RFShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RRHip, KF_Die_RRHip, partialTick);
    ModelUtils.moveParts(frame, this.LLEG, KF_Die_LLEG, partialTick);
    ModelUtils.moveParts(frame, this.LRShoulder1, KF_Die_LRShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RLeg1, KF_Die_RLeg1, partialTick);
    ModelUtils.moveParts(frame, this.LArm1, KF_Die_LArm1, partialTick);
    ModelUtils.moveParts(frame, this.LFShoulder2, KF_Die_LFShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.LLeg2, KF_Die_LLeg2, partialTick);
    ModelUtils.moveParts(frame, this.RArm2, KF_Die_RArm2, partialTick);
    ModelUtils.moveParts(frame, this.LChest, KF_Die_LChest, partialTick);
    ModelUtils.moveParts(frame, this.RFShoulder2, KF_Die_RFShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.LLeg1, KF_Die_LLeg1, partialTick);
    ModelUtils.moveParts(frame, this.RFHip, KF_Die_RFHip, partialTick);
    ModelUtils.moveParts(frame, this.HEAD, KF_Die_HEAD, partialTick);
    ModelUtils.moveParts(frame, this.RRShoulder1, KF_Die_RRShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RARM, KF_Die_RARM, partialTick);
    ModelUtils.moveParts(frame, this.RLEG, KF_Die_RLEG, partialTick);
    ModelUtils.moveParts(frame, this.LRHip, KF_Die_LRHip, partialTick);
  }
  
  static final KeyFrame[] KF_Stomp_HEAD = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_LRShoulder2 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_LFShoulder1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_RRShoulder2 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_RFShoulder1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_LRShoulder1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_LFShoulder2 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_RFShoulder2 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_RRShoulder1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_LARM = new KeyFrame[4];
  
  static final KeyFrame[] KF_Stomp_LArm1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_LArm2 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_RARM = new KeyFrame[4];
  
  static final KeyFrame[] KF_Stomp_RArm1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_RArm2 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_RChest = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_LChest = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_WAIST = new KeyFrame[6];
  
  static final KeyFrame[] KF_Stomp_HIP = new KeyFrame[5];
  
  static final KeyFrame[] KF_Stomp_LFHip = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_RRHip = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_RFHip = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_LRHip = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stomp_RLEG = new KeyFrame[6];
  
  static final KeyFrame[] KF_Stomp_RLeg1 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Stomp_RLeg2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Stomp_LLEG = new KeyFrame[5];
  
  static final KeyFrame[] KF_Stomp_LLeg1 = new KeyFrame[5];
  
  static final KeyFrame[] KF_Stomp_LLeg2 = new KeyFrame[5];
  
  public void Build_Stomp() {
    KF_Stomp_HEAD[0] = new KeyFrame(0, 0.0F, -54.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Stomp_LRShoulder2[0] = new KeyFrame(0, -4.22F, -42.0F, 18.0F, -30.0F, 0.0F, -15.0F);
    KF_Stomp_LFShoulder1[0] = new KeyFrame(0, 5.52F, -47.0F, 5.5F, -15.0F, 0.0F, 15.0F);
    KF_Stomp_RRShoulder2[0] = new KeyFrame(0, -4.2F, -42.0F, -18.0F, 30.0F, 0.0F, -15.0F);
    KF_Stomp_RFShoulder1[0] = new KeyFrame(0, 5.5F, -47.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Stomp_LRShoulder1[0] = new KeyFrame(0, -5.5F, -47.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Stomp_LFShoulder2[0] = new KeyFrame(0, 4.21F, -42.0F, 18.0F, -30.0F, 0.0F, 15.0F);
    KF_Stomp_RFShoulder2[0] = new KeyFrame(0, 4.2F, -42.0F, -18.0F, 30.0F, 0.0F, 15.0F);
    KF_Stomp_RRShoulder1[0] = new KeyFrame(0, -5.52F, -47.0F, -5.5F, 15.0F, 0.0F, -15.0F);
    KF_Stomp_LARM[0] = new KeyFrame(0, 0.0F, -40.0F, 21.0F, 40.0F, 0.0F, 0.0F);
    KF_Stomp_LARM[1] = new KeyFrame(5, 0.0F, -40.0F, 21.0F, 55.0F, 0.0F, 0.0F);
    KF_Stomp_LARM[2] = new KeyFrame(12, 0.0F, -40.0F, 21.0F, 55.0F, 0.0F, 0.0F);
    KF_Stomp_LARM[3] = new KeyFrame(17, 0.0F, -40.0F, 21.0F, 40.0F, 0.0F, 0.0F);
    KF_Stomp_LArm1[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, -25.0F, 0.0F, 0.0F);
    KF_Stomp_LArm2[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, -10.0F, 0.0F, 0.0F);
    KF_Stomp_RARM[0] = new KeyFrame(0, 0.0F, -40.0F, -21.0F, -40.0F, 0.0F, 0.0F);
    KF_Stomp_RARM[1] = new KeyFrame(5, 0.0F, -40.0F, -21.0F, -55.0F, 0.0F, 0.0F);
    KF_Stomp_RARM[2] = new KeyFrame(12, 0.0F, -40.0F, -21.0F, -55.0F, 0.0F, 0.0F);
    KF_Stomp_RARM[3] = new KeyFrame(17, 0.0F, -40.0F, -21.0F, -40.0F, 0.0F, 0.0F);
    KF_Stomp_RArm1[0] = new KeyFrame(0, 0.2F, 12.0F, 0.01F, 25.0F, 0.0F, 0.0F);
    KF_Stomp_RArm2[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, 10.0F, 0.0F, 0.0F);
    KF_Stomp_RChest[0] = new KeyFrame(0, 0.0F, -38.0F, -8.0F, 0.0F, 0.0F, 0.0F);
    KF_Stomp_LChest[0] = new KeyFrame(0, 0.0F, -38.0F, 8.0F, 0.0F, 0.0F, 0.0F);
    KF_Stomp_LFHip[0] = new KeyFrame(0, 5.5F, -9.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Stomp_RRHip[0] = new KeyFrame(0, -5.5F, -9.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Stomp_RFHip[0] = new KeyFrame(0, 5.52F, -9.0F, 5.5F, -15.0F, 0.0F, 15.0F);
    KF_Stomp_LRHip[0] = new KeyFrame(0, -5.52F, -9.0F, -5.5F, 15.0F, 0.0F, -15.0F);
    KF_Stomp_RLEG[0] = new KeyFrame(0, 0.0F, -11.0F, -7.0F, -20.0F, 0.0F, 0.0F);
    KF_Stomp_RLEG[1] = new KeyFrame(5, 3.0F, -8.0F, -7.0F, -20.0F, 0.0F, -65.0F);
    KF_Stomp_RLEG[2] = new KeyFrame(7, 3.0F, -8.0F, -7.0F, -20.0F, 0.0F, -65.0F);
    KF_Stomp_RLEG[3] = new KeyFrame(10, 3.0F, -5.0F, -7.0F, -20.0F, 0.0F, -53.0F);
    KF_Stomp_RLEG[4] = new KeyFrame(12, 3.0F, -5.0F, -7.0F, -20.0F, 0.0F, -53.0F);
    KF_Stomp_RLEG[5] = new KeyFrame(17, 0.0F, -11.0F, -7.0F, -20.0F, 0.0F, 0.0F);
    KF_Stomp_RLeg1[0] = new KeyFrame(0, 0.02F, 13.3F, -0.5F, 20.0F, 0.0F, 0.0F);
    KF_Stomp_RLeg1[1] = new KeyFrame(5, 0.02F, 11.3F, -0.05F, 20.0F, 0.0F, 15.0F);
    KF_Stomp_RLeg1[2] = new KeyFrame(12, 0.02F, 11.3F, -0.05F, 20.0F, 0.0F, 15.0F);
    KF_Stomp_RLeg1[3] = new KeyFrame(17, 0.02F, 13.3F, -0.5F, 20.0F, 0.0F, 0.0F);
    KF_Stomp_RLeg2[0] = new KeyFrame(0, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Stomp_RLeg2[1] = new KeyFrame(5, 2.01F, 11.0F, 0.01F, 0.0F, 0.0F, 40.0F);
    KF_Stomp_RLeg2[2] = new KeyFrame(12, 2.01F, 11.0F, 0.01F, 0.0F, 0.0F, 40.0F);
    KF_Stomp_RLeg2[3] = new KeyFrame(17, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Stomp_LLEG[0] = new KeyFrame(0, 0.0F, -10.0F, 7.0F, 20.0F, 0.0F, 0.0F);
    KF_Stomp_LLEG[1] = new KeyFrame(7, 0.0F, -10.0F, 7.0F, 20.0F, 0.0F, 0.0F);
    KF_Stomp_LLEG[2] = new KeyFrame(10, 0.0F, -11.0F, 7.0F, 20.0F, 0.0F, 18.0F);
    KF_Stomp_LLEG[3] = new KeyFrame(12, 0.0F, -11.0F, 7.0F, 20.0F, 0.0F, 18.0F);
    KF_Stomp_LLEG[4] = new KeyFrame(17, 0.0F, -10.0F, 7.0F, 20.0F, 0.0F, 0.0F);
    KF_Stomp_LLeg1[0] = new KeyFrame(0, 0.02F, 13.3F, 0.5F, -20.0F, 0.0F, 0.0F);
    KF_Stomp_LLeg1[1] = new KeyFrame(7, 0.02F, 13.3F, 0.5F, -20.0F, 0.0F, 0.0F);
    KF_Stomp_LLeg1[2] = new KeyFrame(10, 1.02F, 12.3F, 0.5F, -20.0F, 0.0F, 18.0F);
    KF_Stomp_LLeg1[3] = new KeyFrame(12, 1.02F, 12.3F, 0.5F, -20.0F, 0.0F, 18.0F);
    KF_Stomp_LLeg1[4] = new KeyFrame(17, 0.02F, 13.3F, 0.5F, -20.0F, 0.0F, 0.0F);
    KF_Stomp_LLeg2[0] = new KeyFrame(0, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Stomp_LLeg2[1] = new KeyFrame(7, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Stomp_LLeg2[2] = new KeyFrame(10, 1.01F, 13.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Stomp_LLeg2[3] = new KeyFrame(12, 1.01F, 13.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Stomp_LLeg2[4] = new KeyFrame(17, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Stomp_WAIST[0] = new KeyFrame(0, 0.0F, -25.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Stomp_WAIST[1] = new KeyFrame(5, 0.0F, -25.0F, 0.0F, 10.0F, 0.0F, 0.0F);
    KF_Stomp_WAIST[2] = new KeyFrame(7, 0.0F, -25.0F, 0.0F, 10.0F, 0.0F, 0.0F);
    KF_Stomp_WAIST[3] = new KeyFrame(10, 0.0F, -16.0F, -10.0F, 10.0F, 0.0F, 0.0F);
    KF_Stomp_WAIST[4] = new KeyFrame(12, 0.0F, -16.0F, -10.0F, 10.0F, 0.0F, 0.0F);
    KF_Stomp_WAIST[5] = new KeyFrame(17, 0.0F, -25.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Stomp_HIP[0] = new KeyFrame(0, 0.0F, -9.0F, 0.0F, 0.0F, 90.0F, 0.0F);
    KF_Stomp_HIP[1] = new KeyFrame(7, 0.0F, -9.0F, 0.0F, 0.0F, 90.0F, 0.0F);
    KF_Stomp_HIP[2] = new KeyFrame(10, 0.0F, 0.0F, -10.0F, 0.0F, 90.0F, 0.0F);
    KF_Stomp_HIP[3] = new KeyFrame(12, 0.0F, 0.0F, -10.0F, 0.0F, 90.0F, 0.0F);
    KF_Stomp_HIP[4] = new KeyFrame(17, 0.0F, -9.0F, 0.0F, 0.0F, 90.0F, 0.0F);
  }
  
  public void Stomp(int frame, float partialTick) {
    ModelUtils.moveParts(frame, this.HIP, KF_Stomp_HIP, partialTick);
    ModelUtils.moveParts(frame, this.WAIST, KF_Stomp_WAIST, partialTick);
    ModelUtils.moveParts(frame, this.LFHip, KF_Stomp_LFHip, partialTick);
    ModelUtils.moveParts(frame, this.RChest, KF_Stomp_RChest, partialTick);
    ModelUtils.moveParts(frame, this.LRShoulder2, KF_Stomp_LRShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.RArm1, KF_Stomp_RArm1, partialTick);
    ModelUtils.moveParts(frame, this.LFShoulder1, KF_Stomp_LFShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.LArm2, KF_Stomp_LArm2, partialTick);
    ModelUtils.moveParts(frame, this.LARM, KF_Stomp_LARM, partialTick);
    ModelUtils.moveParts(frame, this.RLeg2, KF_Stomp_RLeg2, partialTick);
    ModelUtils.moveParts(frame, this.RRShoulder2, KF_Stomp_RRShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.RFShoulder1, KF_Stomp_RFShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RRHip, KF_Stomp_RRHip, partialTick);
    ModelUtils.moveParts(frame, this.LLEG, KF_Stomp_LLEG, partialTick);
    ModelUtils.moveParts(frame, this.LRShoulder1, KF_Stomp_LRShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RLeg1, KF_Stomp_RLeg1, partialTick);
    ModelUtils.moveParts(frame, this.LArm1, KF_Stomp_LArm1, partialTick);
    ModelUtils.moveParts(frame, this.LFShoulder2, KF_Stomp_LFShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.LLeg2, KF_Stomp_LLeg2, partialTick);
    ModelUtils.moveParts(frame, this.RArm2, KF_Stomp_RArm2, partialTick);
    ModelUtils.moveParts(frame, this.LChest, KF_Stomp_LChest, partialTick);
    ModelUtils.moveParts(frame, this.RFShoulder2, KF_Stomp_RFShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.LLeg1, KF_Stomp_LLeg1, partialTick);
    ModelUtils.moveParts(frame, this.RFHip, KF_Stomp_RFHip, partialTick);
    ModelUtils.moveParts(frame, this.HEAD, KF_Stomp_HEAD, partialTick);
    ModelUtils.moveParts(frame, this.RRShoulder1, KF_Stomp_RRShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RARM, KF_Stomp_RARM, partialTick);
    ModelUtils.moveParts(frame, this.RLEG, KF_Stomp_RLEG, partialTick);
    ModelUtils.moveParts(frame, this.LRHip, KF_Stomp_LRHip, partialTick);
  }
  
  static final KeyFrame[] KF_Roll_HEAD = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_LRShoulder2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_LFShoulder1 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_RRShoulder2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_RFShoulder1 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_LRShoulder1 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_LFShoulder2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_RFShoulder2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_RRShoulder1 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_LARM = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_LArm1 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_LArm2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_RARM = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_RArm1 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_RArm2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_RChest = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_LChest = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_WAIST = new KeyFrame[6];
  
  static final KeyFrame[] KF_Roll_HIP = new KeyFrame[7];
  
  static final KeyFrame[] KF_Roll_LFHip = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_RRHip = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_RFHip = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_LRHip = new KeyFrame[1];
  
  static final KeyFrame[] KF_Roll_RLEG = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_RLeg1 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_RLeg2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_LLEG = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_LLeg1 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Roll_LLeg2 = new KeyFrame[4];
  
  public void build_Roll() {
    KF_Roll_HEAD[0] = new KeyFrame(0, 0.0F, -54.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Roll_HEAD[1] = new KeyFrame(3, 6.0F, -26.0F, 9.0F, -5.0F, -30.0F, 40.0F);
    KF_Roll_HEAD[2] = new KeyFrame(20, 6.0F, -26.0F, 9.0F, -5.0F, -30.0F, 40.0F);
    KF_Roll_HEAD[3] = new KeyFrame(23, 0.0F, -54.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Roll_LRShoulder2[0] = new KeyFrame(0, -4.22F, -42.0F, 18.0F, -30.0F, 0.0F, -15.0F);
    KF_Roll_LRShoulder2[1] = new KeyFrame(3, -13.22F, -10.0F, 17.0F, -7.0F, 29.0F, 0.0F);
    KF_Roll_LRShoulder2[2] = new KeyFrame(20, -13.22F, -10.0F, 17.0F, -7.0F, 29.0F, 0.0F);
    KF_Roll_LRShoulder2[3] = new KeyFrame(23, -4.22F, -42.0F, 18.0F, -30.0F, 0.0F, -15.0F);
    KF_Roll_LFShoulder1[0] = new KeyFrame(0, 5.52F, -47.0F, 5.5F, -15.0F, 0.0F, 15.0F);
    KF_Roll_LFShoulder1[1] = new KeyFrame(3, 19.52F, -15.0F, 0.5F, -7.0F, -6.0F, -4.0F);
    KF_Roll_LFShoulder1[2] = new KeyFrame(20, 19.52F, -15.0F, 0.5F, -7.0F, -6.0F, -4.0F);
    KF_Roll_LFShoulder1[3] = new KeyFrame(23, 5.52F, -47.0F, 5.5F, -15.0F, 0.0F, 15.0F);
    KF_Roll_RRShoulder2[0] = new KeyFrame(0, -4.2F, -42.0F, -18.0F, 30.0F, 0.0F, -15.0F);
    KF_Roll_RRShoulder2[1] = new KeyFrame(3, -9.2F, -21.0F, -18.0F, 55.0F, 0.0F, -39.0F);
    KF_Roll_RRShoulder2[2] = new KeyFrame(20, -9.2F, -21.0F, -18.0F, 55.0F, 0.0F, -39.0F);
    KF_Roll_RRShoulder2[3] = new KeyFrame(23, -4.2F, -42.0F, -18.0F, 30.0F, 0.0F, -15.0F);
    KF_Roll_RFShoulder1[0] = new KeyFrame(0, 5.5F, -47.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Roll_RFShoulder1[1] = new KeyFrame(3, 5.5F, -35.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Roll_RFShoulder1[2] = new KeyFrame(20, 5.5F, -35.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Roll_RFShoulder1[3] = new KeyFrame(23, 5.5F, -47.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Roll_LRShoulder1[0] = new KeyFrame(0, -5.5F, -47.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Roll_LRShoulder1[1] = new KeyFrame(3, -5.5F, -34.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Roll_LRShoulder1[2] = new KeyFrame(20, -5.5F, -34.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Roll_LRShoulder1[3] = new KeyFrame(23, -5.5F, -47.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Roll_LFShoulder2[0] = new KeyFrame(0, 4.21F, -42.0F, 18.0F, -30.0F, 0.0F, 15.0F);
    KF_Roll_LFShoulder2[1] = new KeyFrame(3, 12.21F, -9.0F, 17.0F, -1.0F, -32.0F, 30.0F);
    KF_Roll_LFShoulder2[2] = new KeyFrame(20, 12.21F, -9.0F, 17.0F, -1.0F, -32.0F, 30.0F);
    KF_Roll_LFShoulder2[3] = new KeyFrame(23, 4.21F, -42.0F, 18.0F, -30.0F, 0.0F, 15.0F);
    KF_Roll_RFShoulder2[0] = new KeyFrame(0, 4.2F, -42.0F, -18.0F, 30.0F, 0.0F, 15.0F);
    KF_Roll_RFShoulder2[1] = new KeyFrame(3, 10.2F, -25.0F, -17.0F, 64.0F, -20.0F, 6.0F);
    KF_Roll_RFShoulder2[2] = new KeyFrame(20, 10.2F, -25.0F, -17.0F, 64.0F, -20.0F, 6.0F);
    KF_Roll_RFShoulder2[3] = new KeyFrame(23, 4.2F, -42.0F, -18.0F, 30.0F, 0.0F, 15.0F);
    KF_Roll_RRShoulder1[0] = new KeyFrame(0, -5.52F, -47.0F, -5.5F, 15.0F, 0.0F, -15.0F);
    KF_Roll_RRShoulder1[1] = new KeyFrame(3, -15.52F, -28.0F, -2.5F, 13.0F, -7.0F, -56.0F);
    KF_Roll_RRShoulder1[2] = new KeyFrame(20, -15.52F, -28.0F, -2.5F, 13.0F, -7.0F, -56.0F);
    KF_Roll_RRShoulder1[3] = new KeyFrame(23, -5.52F, -47.0F, -5.5F, 15.0F, 0.0F, -15.0F);
    KF_Roll_LARM[0] = new KeyFrame(0, 0.0F, -40.0F, 21.0F, 40.0F, 0.0F, 0.0F);
    KF_Roll_LARM[1] = new KeyFrame(3, -1.0F, -33.0F, 18.0F, 7.0F, 0.0F, 0.0F);
    KF_Roll_LARM[2] = new KeyFrame(20, -1.0F, -33.0F, 18.0F, 7.0F, 0.0F, 0.0F);
    KF_Roll_LARM[3] = new KeyFrame(23, 0.0F, -40.0F, 21.0F, 40.0F, 0.0F, 0.0F);
    KF_Roll_LArm1[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, -25.0F, 0.0F, 0.0F);
    KF_Roll_LArm1[1] = new KeyFrame(3, 17.02F, 1.0F, 0.01F, -12.0F, 54.0F, 0.0F);
    KF_Roll_LArm1[2] = new KeyFrame(20, 17.02F, 1.0F, 0.01F, -12.0F, 54.0F, 0.0F);
    KF_Roll_LArm1[3] = new KeyFrame(23, 0.02F, 12.0F, 0.01F, -25.0F, 0.0F, 0.0F);
    KF_Roll_LArm2[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, -10.0F, 0.0F, 0.0F);
    KF_Roll_LArm2[1] = new KeyFrame(3, -11.98F, 18.0F, -5.99F, -12.0F, -38.0F, 0.0F);
    KF_Roll_LArm2[2] = new KeyFrame(20, -11.98F, 18.0F, -5.99F, -12.0F, -38.0F, 0.0F);
    KF_Roll_LArm2[3] = new KeyFrame(23, 0.02F, 12.0F, 0.01F, -10.0F, 0.0F, 0.0F);
    KF_Roll_RARM[0] = new KeyFrame(0, 0.0F, -40.0F, -21.0F, -40.0F, 0.0F, 0.0F);
    KF_Roll_RARM[1] = new KeyFrame(3, -5.0F, -8.0F, -10.0F, -68.0F, 54.0F, 0.0F);
    KF_Roll_RARM[2] = new KeyFrame(20, -5.0F, -8.0F, -10.0F, -68.0F, 54.0F, 0.0F);
    KF_Roll_RARM[3] = new KeyFrame(23, 0.0F, -40.0F, -21.0F, -40.0F, 0.0F, 0.0F);
    KF_Roll_RArm1[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, 25.0F, 0.0F, 0.0F);
    KF_Roll_RArm1[1] = new KeyFrame(3, 11.02F, -7.0F, -2.99F, 2.0F, 0.0F, -32.0F);
    KF_Roll_RArm1[2] = new KeyFrame(20, 11.02F, -7.0F, -2.99F, 2.0F, 0.0F, -32.0F);
    KF_Roll_RArm1[3] = new KeyFrame(23, 0.02F, 12.0F, 0.01F, 25.0F, 0.0F, 0.0F);
    KF_Roll_RArm2[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, 10.0F, 0.0F, 0.0F);
    KF_Roll_RArm2[1] = new KeyFrame(3, -10.98F, -5.0F, 15.01F, 14.0F, -6.0F, -15.0F);
    KF_Roll_RArm2[2] = new KeyFrame(20, -10.98F, -5.0F, 15.01F, 14.0F, -6.0F, -15.0F);
    KF_Roll_RArm2[3] = new KeyFrame(23, 0.02F, 12.0F, 0.01F, 10.0F, 0.0F, 0.0F);
    KF_Roll_RChest[0] = new KeyFrame(0, 0.0F, -38.0F, -8.0F, 0.0F, 0.0F, 0.0F);
    KF_Roll_RChest[1] = new KeyFrame(3, 19.0F, -11.0F, -12.0F, 35.0F, -53.0F, -26.0F);
    KF_Roll_RChest[2] = new KeyFrame(20, 19.0F, -11.0F, -12.0F, 35.0F, -53.0F, -26.0F);
    KF_Roll_RChest[3] = new KeyFrame(23, 0.0F, -38.0F, -8.0F, 0.0F, 0.0F, 0.0F);
    KF_Roll_LChest[0] = new KeyFrame(0, 0.0F, -38.0F, 8.0F, 0.0F, 0.0F, 0.0F);
    KF_Roll_LChest[1] = new KeyFrame(3, -11.0F, -25.0F, 11.0F, 0.0F, 26.0F, 16.0F);
    KF_Roll_LChest[2] = new KeyFrame(20, -11.0F, -25.0F, 11.0F, 0.0F, 26.0F, 16.0F);
    KF_Roll_LChest[3] = new KeyFrame(23, 0.0F, -38.0F, 8.0F, 0.0F, 0.0F, 0.0F);
    KF_Roll_LFHip[0] = new KeyFrame(0, 5.5F, -9.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Roll_LFHip[1] = new KeyFrame(3, 13.5F, 8.0F, -2.5F, 3.0F, 6.0F, 33.0F);
    KF_Roll_LFHip[2] = new KeyFrame(20, 13.5F, 8.0F, -2.5F, 3.0F, 6.0F, 33.0F);
    KF_Roll_LFHip[3] = new KeyFrame(23, 5.5F, -9.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Roll_RRHip[0] = new KeyFrame(0, -5.5F, -9.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Roll_RRHip[1] = new KeyFrame(3, -0.5F, 3.0F, 18.5F, 7.0F, 7.0F, 2.0F);
    KF_Roll_RRHip[2] = new KeyFrame(20, -0.5F, 3.0F, 18.5F, 7.0F, 7.0F, 2.0F);
    KF_Roll_RRHip[3] = new KeyFrame(23, -5.5F, -9.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Roll_RFHip[0] = new KeyFrame(0, 5.52F, -9.0F, 5.5F, -15.0F, 0.0F, 15.0F);
    KF_Roll_RFHip[1] = new KeyFrame(3, 10.52F, 18.0F, 12.5F, -2.0F, -36.0F, 35.0F);
    KF_Roll_RFHip[2] = new KeyFrame(20, 10.52F, 18.0F, 12.5F, -2.0F, -36.0F, 35.0F);
    KF_Roll_RFHip[3] = new KeyFrame(23, 5.52F, -9.0F, 5.5F, -15.0F, 0.0F, 15.0F);
    KF_Roll_LRHip[0] = new KeyFrame(0, -5.52F, -9.0F, -5.5F, 15.0F, 0.0F, -15.0F);
    KF_Roll_RLEG[0] = new KeyFrame(0, 0.0F, -11.0F, -7.0F, -20.0F, 0.0F, 0.0F);
    KF_Roll_RLEG[1] = new KeyFrame(3, 0.0F, 2.0F, -7.0F, -41.0F, 0.0F, 0.0F);
    KF_Roll_RLEG[2] = new KeyFrame(20, 0.0F, 2.0F, -7.0F, -41.0F, 0.0F, 0.0F);
    KF_Roll_RLEG[3] = new KeyFrame(23, 0.0F, -11.0F, -7.0F, -20.0F, 0.0F, 0.0F);
    KF_Roll_RLeg1[0] = new KeyFrame(0, 0.02F, 13.3F, -0.5F, 20.0F, 0.0F, 0.0F);
    KF_Roll_RLeg1[1] = new KeyFrame(3, 0.02F, 13.3F, -0.5F, 51.0F, -13.0F, 0.0F);
    KF_Roll_RLeg1[2] = new KeyFrame(20, 0.02F, 13.3F, -0.5F, 51.0F, -13.0F, 0.0F);
    KF_Roll_RLeg1[3] = new KeyFrame(23, 0.02F, 13.3F, -0.5F, 20.0F, 0.0F, 0.0F);
    KF_Roll_RLeg2[0] = new KeyFrame(0, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Roll_RLeg2[1] = new KeyFrame(3, 12.01F, 9.0F, 0.01F, 81.0F, 1.0F, -12.0F);
    KF_Roll_RLeg2[2] = new KeyFrame(20, 12.01F, 9.0F, 0.01F, 81.0F, 1.0F, -12.0F);
    KF_Roll_RLeg2[3] = new KeyFrame(23, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Roll_LLEG[0] = new KeyFrame(0, 0.0F, -11.0F, 7.0F, 20.0F, 0.0F, 0.0F);
    KF_Roll_LLEG[1] = new KeyFrame(3, 0.0F, 2.0F, 7.0F, 20.0F, 0.0F, 0.0F);
    KF_Roll_LLEG[2] = new KeyFrame(20, 0.0F, 2.0F, 7.0F, 20.0F, 0.0F, 0.0F);
    KF_Roll_LLEG[3] = new KeyFrame(23, 0.0F, -11.0F, 7.0F, 20.0F, 0.0F, 0.0F);
    KF_Roll_LLeg1[0] = new KeyFrame(0, 0.02F, 13.3F, 0.5F, -20.0F, 0.0F, 0.0F);
    KF_Roll_LLeg1[1] = new KeyFrame(3, 1.02F, 7.3F, 10.5F, -37.0F, 5.0F, 0.0F);
    KF_Roll_LLeg1[2] = new KeyFrame(20, 1.02F, 7.3F, 10.5F, -37.0F, 5.0F, 0.0F);
    KF_Roll_LLeg1[3] = new KeyFrame(23, 0.02F, 13.3F, 0.5F, -20.0F, 0.0F, 0.0F);
    KF_Roll_LLeg2[0] = new KeyFrame(0, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Roll_LLeg2[1] = new KeyFrame(3, 0.01F, 15.0F, 0.01F, -51.0F, 0.0F, 0.0F);
    KF_Roll_LLeg2[2] = new KeyFrame(20, 0.01F, 15.0F, 0.01F, -51.0F, 0.0F, 0.0F);
    KF_Roll_LLeg2[3] = new KeyFrame(23, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Roll_WAIST[0] = new KeyFrame(0, 0.0F, -25.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Roll_WAIST[1] = new KeyFrame(4, 0.0F, -25.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Roll_WAIST[2] = new KeyFrame(7, 0.0F, -7.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Roll_WAIST[3] = new KeyFrame(10, 0.0F, -7.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Roll_WAIST[4] = new KeyFrame(20, 0.0F, -7.0F, 0.0F, 360.0F, 0.0F, 0.0F);
    KF_Roll_WAIST[5] = new KeyFrame(23, 0.0F, -25.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Roll_HIP[0] = new KeyFrame(0, 0.0F, -9.0F, 0.0F, 0.0F, 90.0F, 0.0F);
    KF_Roll_HIP[1] = new KeyFrame(3, 0.0F, -25.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Roll_HIP[2] = new KeyFrame(4, 0.0F, -25.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Roll_HIP[3] = new KeyFrame(7, 0.0F, -7.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Roll_HIP[4] = new KeyFrame(10, 0.0F, -7.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Roll_HIP[5] = new KeyFrame(20, 0.0F, -7.0F, 0.0F, 360.0F, 0.0F, 0.0F);
    KF_Roll_HIP[6] = new KeyFrame(23, 0.0F, -9.0F, 0.0F, 0.0F, 90.0F, 0.0F);
  }
  
  public void Roll(int frame, float partialTick) {
    ModelUtils.moveParts(frame, this.HIP, KF_Roll_HIP, partialTick);
    ModelUtils.moveParts(frame, this.WAIST, KF_Roll_WAIST, partialTick);
    ModelUtils.moveParts(frame, this.LFHip, KF_Roll_LFHip, partialTick);
    ModelUtils.moveParts(frame, this.RChest, KF_Roll_RChest, partialTick);
    ModelUtils.moveParts(frame, this.LRShoulder2, KF_Roll_LRShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.RArm1, KF_Roll_RArm1, partialTick);
    ModelUtils.moveParts(frame, this.LFShoulder1, KF_Roll_LFShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.LArm2, KF_Roll_LArm2, partialTick);
    ModelUtils.moveParts(frame, this.LARM, KF_Roll_LARM, partialTick);
    ModelUtils.moveParts(frame, this.RLeg2, KF_Roll_RLeg2, partialTick);
    ModelUtils.moveParts(frame, this.RRShoulder2, KF_Roll_RRShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.RFShoulder1, KF_Roll_RFShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RRHip, KF_Roll_RRHip, partialTick);
    ModelUtils.moveParts(frame, this.LLEG, KF_Roll_LLEG, partialTick);
    ModelUtils.moveParts(frame, this.LRShoulder1, KF_Roll_LRShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RLeg1, KF_Roll_RLeg1, partialTick);
    ModelUtils.moveParts(frame, this.LArm1, KF_Roll_LArm1, partialTick);
    ModelUtils.moveParts(frame, this.LFShoulder2, KF_Roll_LFShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.LLeg2, KF_Roll_LLeg2, partialTick);
    ModelUtils.moveParts(frame, this.RArm2, KF_Roll_RArm2, partialTick);
    ModelUtils.moveParts(frame, this.LChest, KF_Roll_LChest, partialTick);
    ModelUtils.moveParts(frame, this.RFShoulder2, KF_Roll_RFShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.LLeg1, KF_Roll_LLeg1, partialTick);
    ModelUtils.moveParts(frame, this.RFHip, KF_Roll_RFHip, partialTick);
    ModelUtils.moveParts(frame, this.HEAD, KF_Roll_HEAD, partialTick);
    ModelUtils.moveParts(frame, this.RRShoulder1, KF_Roll_RRShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RARM, KF_Roll_RARM, partialTick);
    ModelUtils.moveParts(frame, this.RLEG, KF_Roll_RLEG, partialTick);
    ModelUtils.moveParts(frame, this.LRHip, KF_Roll_LRHip, partialTick);
  }
  
  static final KeyFrame[] KF_Stand_HEAD = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_LRShoulder2 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_LFShoulder1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_RRShoulder2 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_RFShoulder1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_LRShoulder1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_LFShoulder2 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_RFShoulder2 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_RRShoulder1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_LARM = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_LArm1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_LArm2 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_RARM = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_RArm1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_RArm2 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_RChest = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_LChest = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_WAIST = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_HIP = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_LFHip = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_RRHip = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_RFHip = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_LRHip = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_RLEG = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_RLeg1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_RLeg2 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_LLEG = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_LLeg1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Stand_LLeg2 = new KeyFrame[1];
  
  public void build_Stand() {
    KF_Stand_HEAD[0] = new KeyFrame(0, 0.0F, -54.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Stand_LRShoulder2[0] = new KeyFrame(0, -4.22F, -42.0F, 18.0F, -30.0F, 0.0F, -15.0F);
    KF_Stand_LFShoulder1[0] = new KeyFrame(0, 5.52F, -47.0F, 5.5F, -15.0F, 0.0F, 15.0F);
    KF_Stand_RRShoulder2[0] = new KeyFrame(0, -4.2F, -42.0F, -18.0F, 30.0F, 0.0F, -15.0F);
    KF_Stand_RFShoulder1[0] = new KeyFrame(0, 5.5F, -47.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Stand_LRShoulder1[0] = new KeyFrame(0, -5.5F, -47.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Stand_LFShoulder2[0] = new KeyFrame(0, 4.21F, -42.0F, 18.0F, -30.0F, 0.0F, 15.0F);
    KF_Stand_RFShoulder2[0] = new KeyFrame(0, 4.2F, -42.0F, -18.0F, 30.0F, 0.0F, 15.0F);
    KF_Stand_RRShoulder1[0] = new KeyFrame(0, -5.52F, -47.0F, -5.5F, 15.0F, 0.0F, -15.0F);
    KF_Stand_LARM[0] = new KeyFrame(0, 0.0F, -40.0F, 21.0F, 40.0F, 0.0F, 0.0F);
    KF_Stand_LArm1[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, -25.0F, 0.0F, 0.0F);
    KF_Stand_LArm2[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, -10.0F, 0.0F, 0.0F);
    KF_Stand_RARM[0] = new KeyFrame(0, 0.0F, -40.0F, -21.0F, -40.0F, 0.0F, 0.0F);
    KF_Stand_RArm1[0] = new KeyFrame(0, 0.2F, 12.0F, 0.01F, 25.0F, 0.0F, 0.0F);
    KF_Stand_RArm2[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, 10.0F, 0.0F, 0.0F);
    KF_Stand_RChest[0] = new KeyFrame(0, 0.0F, -38.0F, -8.0F, 0.0F, 0.0F, 0.0F);
    KF_Stand_LChest[0] = new KeyFrame(0, 0.0F, -38.0F, 8.0F, 0.0F, 0.0F, 0.0F);
    KF_Stand_WAIST[0] = new KeyFrame(0, 0.0F, -25.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Stand_HIP[0] = new KeyFrame(0, 0.0F, -9.0F, 0.0F, 0.0F, 90.0F, 0.0F);
    KF_Stand_LFHip[0] = new KeyFrame(0, 5.5F, -9.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Stand_RRHip[0] = new KeyFrame(0, -5.5F, -9.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Stand_RFHip[0] = new KeyFrame(0, 5.52F, -9.0F, 5.5F, -15.0F, 0.0F, 15.0F);
    KF_Stand_LRHip[0] = new KeyFrame(0, -5.52F, -9.0F, -5.5F, 15.0F, 0.0F, -15.0F);
    KF_Stand_RLEG[0] = new KeyFrame(0, 0.0F, -11.0F, -7.0F, -20.0F, 0.0F, 0.0F);
    KF_Stand_RLeg1[0] = new KeyFrame(0, 0.02F, 13.3F, -0.5F, 20.0F, 0.0F, 0.0F);
    KF_Stand_RLeg2[0] = new KeyFrame(0, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Stand_LLEG[0] = new KeyFrame(0, 0.0F, -11.0F, 7.0F, 20.0F, 0.0F, 0.0F);
    KF_Stand_LLeg1[0] = new KeyFrame(0, 0.02F, 13.3F, 0.5F, -20.0F, 0.0F, 0.0F);
    KF_Stand_LLeg2[0] = new KeyFrame(0, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
  }
  
  public void Stand(int frame, float partialTick) {
    ModelUtils.moveParts(frame, this.HIP, KF_Stand_HIP, partialTick);
    ModelUtils.moveParts(frame, this.WAIST, KF_Stand_WAIST, partialTick);
    ModelUtils.moveParts(frame, this.LFHip, KF_Stand_LFHip, partialTick);
    ModelUtils.moveParts(frame, this.RChest, KF_Stand_RChest, partialTick);
    ModelUtils.moveParts(frame, this.LRShoulder2, KF_Stand_LRShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.RArm1, KF_Stand_RArm1, partialTick);
    ModelUtils.moveParts(frame, this.LFShoulder1, KF_Stand_LFShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.LArm2, KF_Stand_LArm2, partialTick);
    ModelUtils.moveParts(frame, this.LARM, KF_Stand_LARM, partialTick);
    ModelUtils.moveParts(frame, this.RLeg2, KF_Stand_RLeg2, partialTick);
    ModelUtils.moveParts(frame, this.RRShoulder2, KF_Stand_RRShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.RFShoulder1, KF_Stand_RFShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RRHip, KF_Stand_RRHip, partialTick);
    ModelUtils.moveParts(frame, this.LLEG, KF_Stand_LLEG, partialTick);
    ModelUtils.moveParts(frame, this.LRShoulder1, KF_Stand_LRShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RLeg1, KF_Stand_RLeg1, partialTick);
    ModelUtils.moveParts(frame, this.LArm1, KF_Stand_LArm1, partialTick);
    ModelUtils.moveParts(frame, this.LFShoulder2, KF_Stand_LFShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.LLeg2, KF_Stand_LLeg2, partialTick);
    ModelUtils.moveParts(frame, this.RArm2, KF_Stand_RArm2, partialTick);
    ModelUtils.moveParts(frame, this.LChest, KF_Stand_LChest, partialTick);
    ModelUtils.moveParts(frame, this.RFShoulder2, KF_Stand_RFShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.LLeg1, KF_Stand_LLeg1, partialTick);
    ModelUtils.moveParts(frame, this.RFHip, KF_Stand_RFHip, partialTick);
    ModelUtils.moveParts(frame, this.HEAD, KF_Stand_HEAD, partialTick);
    ModelUtils.moveParts(frame, this.RRShoulder1, KF_Stand_RRShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RARM, KF_Stand_RARM, partialTick);
    ModelUtils.moveParts(frame, this.RLEG, KF_Stand_RLEG, partialTick);
    ModelUtils.moveParts(frame, this.LRHip, KF_Stand_LRHip, partialTick);
  }
  
  static final KeyFrame[] KF_Throw_HEAD = new KeyFrame[6];
  
  static final KeyFrame[] KF_Throw_LRShoulder2 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Throw_LFShoulder1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Throw_RRShoulder2 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Throw_RFShoulder1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Throw_LRShoulder1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Throw_LFShoulder2 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Throw_RFShoulder2 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Throw_RRShoulder1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Throw_LARM = new KeyFrame[6];
  
  static final KeyFrame[] KF_Throw_LArm1 = new KeyFrame[1];
  
  static final KeyFrame[] KF_Throw_LArm2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Throw_RARM = new KeyFrame[6];
  
  static final KeyFrame[] KF_Throw_RArm1 = new KeyFrame[7];
  
  static final KeyFrame[] KF_Throw_RArm2 = new KeyFrame[6];
  
  static final KeyFrame[] KF_Throw_RChest = new KeyFrame[1];
  
  static final KeyFrame[] KF_Throw_LChest = new KeyFrame[1];
  
  static final KeyFrame[] KF_Throw_WAIST = new KeyFrame[7];
  
  static final KeyFrame[] KF_Throw_HIP = new KeyFrame[7];
  
  static final KeyFrame[] KF_Throw_LFHip = new KeyFrame[1];
  
  static final KeyFrame[] KF_Throw_RRHip = new KeyFrame[1];
  
  static final KeyFrame[] KF_Throw_RFHip = new KeyFrame[1];
  
  static final KeyFrame[] KF_Throw_LRHip = new KeyFrame[1];
  
  static final KeyFrame[] KF_Throw_RLEG = new KeyFrame[6];
  
  static final KeyFrame[] KF_Throw_RLeg1 = new KeyFrame[6];
  
  static final KeyFrame[] KF_Throw_RLeg2 = new KeyFrame[7];
  
  static final KeyFrame[] KF_Throw_LLEG = new KeyFrame[7];
  
  static final KeyFrame[] KF_Throw_LLeg1 = new KeyFrame[7];
  
  static final KeyFrame[] KF_Throw_LLeg2 = new KeyFrame[6];
  
  public void build_Throw() {
    KF_Throw_HEAD[0] = new KeyFrame(0, 0.0F, -54.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Throw_HEAD[1] = new KeyFrame(10, 0.0F, -54.0F, 0.0F, 0.0F, -50.0F, 0.0F);
    KF_Throw_HEAD[2] = new KeyFrame(12, 0.0F, -54.0F, 0.0F, 0.0F, -50.0F, 0.0F);
    KF_Throw_HEAD[3] = new KeyFrame(17, 0.0F, -54.0F, 0.0F, 0.0F, 40.0F, 0.0F);
    KF_Throw_HEAD[4] = new KeyFrame(19, 0.0F, -54.0F, 0.0F, 0.0F, 40.0F, 0.0F);
    KF_Throw_HEAD[5] = new KeyFrame(29, 0.0F, -54.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Throw_LRShoulder2[0] = new KeyFrame(0, -4.22F, -42.0F, 18.0F, -30.0F, 0.0F, -15.0F);
    KF_Throw_LFShoulder1[0] = new KeyFrame(0, 5.52F, -47.0F, 5.5F, -15.0F, 0.0F, 15.0F);
    KF_Throw_RRShoulder2[0] = new KeyFrame(0, -4.2F, -42.0F, -18.0F, 30.0F, 0.0F, -15.0F);
    KF_Throw_RFShoulder1[0] = new KeyFrame(0, 5.5F, -47.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Throw_LRShoulder1[0] = new KeyFrame(0, -5.5F, -47.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Throw_LFShoulder2[0] = new KeyFrame(0, 4.21F, -42.0F, 18.0F, -30.0F, 0.0F, 15.0F);
    KF_Throw_RFShoulder2[0] = new KeyFrame(0, 4.2F, -42.0F, -18.0F, 30.0F, 0.0F, 15.0F);
    KF_Throw_RRShoulder1[0] = new KeyFrame(0, -5.52F, -47.0F, -5.5F, 15.0F, 0.0F, -15.0F);
    KF_Throw_LARM[0] = new KeyFrame(0, 0.0F, -40.0F, 21.0F, 40.0F, 0.0F, 0.0F);
    KF_Throw_LARM[1] = new KeyFrame(10, 0.0F, -40.0F, 21.0F, 40.0F, 0.0F, -20.0F);
    KF_Throw_LARM[2] = new KeyFrame(12, 0.0F, -40.0F, 21.0F, 40.0F, 0.0F, -20.0F);
    KF_Throw_LARM[3] = new KeyFrame(15, 0.0F, -40.0F, 21.0F, 40.0F, 0.0F, 30.0F);
    KF_Throw_LARM[4] = new KeyFrame(19, 0.0F, -40.0F, 21.0F, 40.0F, 0.0F, 30.0F);
    KF_Throw_LARM[5] = new KeyFrame(29, 0.0F, -40.0F, 21.0F, 40.0F, 0.0F, 0.0F);
    KF_Throw_LArm1[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, -25.0F, 0.0F, 0.0F);
    KF_Throw_LArm2[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, -10.0F, 0.0F, 0.0F);
    KF_Throw_LArm2[1] = new KeyFrame(10, 0.02F, 12.0F, 0.01F, -10.0F, 0.0F, -25.0F);
    KF_Throw_LArm2[2] = new KeyFrame(19, 0.02F, 12.0F, 0.01F, -10.0F, 0.0F, -25.0F);
    KF_Throw_LArm2[3] = new KeyFrame(29, 0.02F, 12.0F, 0.01F, -10.0F, 0.0F, 0.0F);
    KF_Throw_RARM[0] = new KeyFrame(0, 0.0F, -40.0F, -21.0F, -40.0F, 0.0F, 0.0F);
    KF_Throw_RARM[1] = new KeyFrame(12, 0.0F, -40.0F, -21.0F, -42.0F, 2.61F, -180.0F);
    KF_Throw_RARM[2] = new KeyFrame(15, 0.0F, -40.0F, -21.0F, -100.0F, 0.0F, -180.0F);
    KF_Throw_RARM[3] = new KeyFrame(17, 0.0F, -40.0F, -21.0F, -80.0F, 35.0F, -155.0F);
    KF_Throw_RARM[4] = new KeyFrame(19, 0.0F, -40.0F, -21.0F, -80.0F, 35.0F, -155.0F);
    KF_Throw_RARM[5] = new KeyFrame(29, 0.0F, -40.0F, -21.0F, -40.0F, 0.0F, 0.0F);
    KF_Throw_RArm1[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, 25.0F, 0.0F, 0.0F);
    KF_Throw_RArm1[1] = new KeyFrame(10, 0.02F, 12.0F, 0.01F, -5.22F, 0.0F, 10.0F);
    KF_Throw_RArm1[2] = new KeyFrame(12, 0.02F, 12.0F, 0.01F, -5.22F, 0.0F, 10.0F);
    KF_Throw_RArm1[3] = new KeyFrame(15, 0.02F, 12.0F, 0.01F, 50.0F, 0.0F, 10.0F);
    KF_Throw_RArm1[4] = new KeyFrame(17, 0.02F, 12.0F, 0.01F, 10.0F, 0.0F, 30.0F);
    KF_Throw_RArm1[5] = new KeyFrame(19, 0.02F, 12.0F, 0.01F, 10.0F, 0.0F, 30.0F);
    KF_Throw_RArm1[6] = new KeyFrame(29, 0.02F, 12.0F, 0.01F, 25.0F, 0.0F, 0.0F);
    KF_Throw_RArm2[0] = new KeyFrame(0, 0.02F, 12.0F, 0.01F, 10.0F, 0.0F, 0.0F);
    KF_Throw_RArm2[1] = new KeyFrame(12, 0.02F, 12.0F, 0.01F, 10.0F, 0.0F, 0.0F);
    KF_Throw_RArm2[2] = new KeyFrame(15, 0.02F, 12.0F, 0.01F, 35.0F, 0.0F, 0.0F);
    KF_Throw_RArm2[3] = new KeyFrame(17, 0.02F, 12.0F, 0.01F, 15.0F, 0.0F, 0.0F);
    KF_Throw_RArm2[4] = new KeyFrame(19, 0.02F, 12.0F, 0.01F, 15.0F, 0.0F, 0.0F);
    KF_Throw_RArm2[5] = new KeyFrame(29, 0.02F, 12.0F, 0.01F, 10.0F, 0.0F, 0.0F);
    KF_Throw_RChest[0] = new KeyFrame(0, 0.0F, -38.0F, -8.0F, 0.0F, 0.0F, 0.0F);
    KF_Throw_LChest[0] = new KeyFrame(0, 0.0F, -38.0F, 8.0F, 0.0F, 0.0F, 0.0F);
    KF_Throw_WAIST[0] = new KeyFrame(0, 0.0F, -25.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Throw_WAIST[1] = new KeyFrame(10, 0.0F, -22.0F, 16.0F, 0.0F, 60.0F, 0.0F);
    KF_Throw_WAIST[2] = new KeyFrame(12, 0.0F, -22.0F, 16.0F, 0.0F, 60.0F, 0.0F);
    KF_Throw_WAIST[3] = new KeyFrame(15, 0.0F, -24.0F, -3.2F, 0.0F, 0.0F, 0.0F);
    KF_Throw_WAIST[4] = new KeyFrame(17, 0.0F, -22.0F, -16.0F, 0.0F, -40.0F, 0.0F);
    KF_Throw_WAIST[5] = new KeyFrame(19, 0.0F, -22.0F, -16.0F, 0.0F, -40.0F, 0.0F);
    KF_Throw_WAIST[6] = new KeyFrame(29, 0.0F, -25.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Throw_HIP[0] = new KeyFrame(0, 0.0F, -9.0F, 0.0F, 0.0F, 90.0F, 0.0F);
    KF_Throw_HIP[1] = new KeyFrame(10, 0.0F, -6.0F, 16.0F, 0.0F, 150.0F, 0.0F);
    KF_Throw_HIP[2] = new KeyFrame(12, 0.0F, -6.0F, 16.0F, 0.0F, 150.0F, 0.0F);
    KF_Throw_HIP[3] = new KeyFrame(15, 0.0F, -8.0F, -3.2F, 0.0F, 90.0F, 0.0F);
    KF_Throw_HIP[4] = new KeyFrame(17, 0.0F, -6.0F, -16.0F, 0.0F, 50.0F, 0.0F);
    KF_Throw_HIP[5] = new KeyFrame(19, 0.0F, -6.0F, -16.0F, 0.0F, 50.0F, 0.0F);
    KF_Throw_HIP[6] = new KeyFrame(29, 0.0F, -9.0F, 0.0F, 0.0F, 90.0F, 0.0F);
    KF_Throw_LFHip[0] = new KeyFrame(0, 5.5F, -9.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Throw_RRHip[0] = new KeyFrame(0, -5.5F, -9.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Throw_RFHip[0] = new KeyFrame(0, 5.52F, -9.0F, 5.5F, -15.0F, 0.0F, 15.0F);
    KF_Throw_LRHip[0] = new KeyFrame(0, -5.52F, -9.0F, -5.5F, 15.0F, 0.0F, -15.0F);
    KF_Throw_RLEG[0] = new KeyFrame(0, 0.0F, -11.0F, -7.0F, -20.0F, 0.0F, 0.0F);
    KF_Throw_RLEG[1] = new KeyFrame(10, 0.0F, -11.0F, -7.0F, -25.0F, 0.0F, 0.0F);
    KF_Throw_RLEG[2] = new KeyFrame(15, 0.0F, -11.0F, -7.0F, -25.0F, 0.0F, 0.0F);
    KF_Throw_RLEG[3] = new KeyFrame(17, 0.0F, -11.0F, -7.0F, -25.0F, 25.0F, -25.0F);
    KF_Throw_RLEG[4] = new KeyFrame(19, 0.0F, -11.0F, -7.0F, -25.0F, 25.0F, -25.0F);
    KF_Throw_RLEG[5] = new KeyFrame(29, 0.0F, -11.0F, -7.0F, -20.0F, 0.0F, 0.0F);
    KF_Throw_RLeg1[0] = new KeyFrame(0, 0.02F, 13.3F, -0.5F, 20.0F, 0.0F, 0.0F);
    KF_Throw_RLeg1[1] = new KeyFrame(10, 0.02F, 13.3F, -0.5F, 5.0F, 5.5F, 0.0F);
    KF_Throw_RLeg1[2] = new KeyFrame(12, 0.02F, 13.3F, -0.5F, 5.0F, 5.5F, 0.0F);
    KF_Throw_RLeg1[3] = new KeyFrame(15, 0.02F, 15.3F, -0.5F, 5.0F, 5.5F, 0.0F);
    KF_Throw_RLeg1[4] = new KeyFrame(19, 0.02F, 15.3F, -0.5F, 5.0F, 5.5F, 0.0F);
    KF_Throw_RLeg1[5] = new KeyFrame(29, 0.02F, 13.3F, -0.5F, 20.0F, 0.0F, 0.0F);
    KF_Throw_RLeg2[0] = new KeyFrame(0, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Throw_RLeg2[1] = new KeyFrame(10, 0.01F, 14.0F, 0.01F, 15.0F, 0.0F, 0.0F);
    KF_Throw_RLeg2[2] = new KeyFrame(12, 0.01F, 14.0F, 0.01F, 15.0F, 0.0F, 0.0F);
    KF_Throw_RLeg2[3] = new KeyFrame(15, 0.01F, 11.0F, 0.01F, 15.0F, 0.0F, 41.74F);
    KF_Throw_RLeg2[4] = new KeyFrame(17, 0.01F, 12.0F, 0.01F, 5.0F, 0.0F, 25.0F);
    KF_Throw_RLeg2[5] = new KeyFrame(19, 0.01F, 12.0F, 0.01F, 5.0F, 0.0F, 25.0F);
    KF_Throw_RLeg2[6] = new KeyFrame(29, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Throw_LLEG[0] = new KeyFrame(0, 0.0F, -11.0F, 7.0F, 20.0F, 0.0F, 0.0F);
    KF_Throw_LLEG[1] = new KeyFrame(10, 0.0F, -11.0F, 7.0F, 20.0F, -60.0F, -20.0F);
    KF_Throw_LLEG[2] = new KeyFrame(12, 0.0F, -11.0F, 7.0F, 20.0F, -60.0F, -20.0F);
    KF_Throw_LLEG[3] = new KeyFrame(15, 0.0F, -11.0F, 7.0F, 25.0F, -75.0F, -30.0F);
    KF_Throw_LLEG[4] = new KeyFrame(17, 0.0F, -11.0F, 7.0F, 30.0F, -15.0F, -10.0F);
    KF_Throw_LLEG[5] = new KeyFrame(19, 0.0F, -11.0F, 7.0F, 30.0F, -15.0F, -10.0F);
    KF_Throw_LLEG[6] = new KeyFrame(29, 0.0F, -11.0F, 7.0F, 20.0F, 0.0F, 0.0F);
    KF_Throw_LLeg1[0] = new KeyFrame(0, 0.02F, 13.3F, 0.5F, -20.0F, 0.0F, 0.0F);
    KF_Throw_LLeg1[1] = new KeyFrame(10, 0.02F, 13.3F, 0.5F, -8.0F, 10.0F, -15.0F);
    KF_Throw_LLeg1[2] = new KeyFrame(12, 0.02F, 13.3F, 0.5F, -8.0F, 10.0F, -15.0F);
    KF_Throw_LLeg1[3] = new KeyFrame(15, 0.02F, 13.3F, 0.5F, 5.0F, 0.0F, 0.0F);
    KF_Throw_LLeg1[4] = new KeyFrame(17, 0.02F, 13.3F, 0.5F, 5.0F, 0.0F, 0.0F);
    KF_Throw_LLeg1[5] = new KeyFrame(19, 0.02F, 13.3F, 0.5F, 5.0F, 0.0F, 20.0F);
    KF_Throw_LLeg1[6] = new KeyFrame(29, 0.02F, 13.3F, 0.5F, -20.0F, 0.0F, 0.0F);
    KF_Throw_LLeg2[0] = new KeyFrame(0, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Throw_LLeg2[1] = new KeyFrame(10, 0.01F, 13.0F, 0.01F, 0.0F, -15.0F, 20.0F);
    KF_Throw_LLeg2[2] = new KeyFrame(12, 0.01F, 13.0F, 0.01F, 0.0F, -15.0F, 20.0F);
    KF_Throw_LLeg2[3] = new KeyFrame(15, 0.01F, 14.0F, 0.01F, 0.0F, 0.0F, 10.0F);
    KF_Throw_LLeg2[4] = new KeyFrame(19, 0.01F, 14.0F, 0.01F, 0.0F, 0.0F, 10.0F);
    KF_Throw_LLeg2[5] = new KeyFrame(29, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
  }
  
  public void Throw(int frame, float partialTick) {
    ModelUtils.moveParts(frame, this.HIP, KF_Throw_HIP, partialTick);
    ModelUtils.moveParts(frame, this.WAIST, KF_Throw_WAIST, partialTick);
    ModelUtils.moveParts(frame, this.LFHip, KF_Throw_LFHip, partialTick);
    ModelUtils.moveParts(frame, this.RChest, KF_Throw_RChest, partialTick);
    ModelUtils.moveParts(frame, this.LRShoulder2, KF_Throw_LRShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.RArm1, KF_Throw_RArm1, partialTick);
    ModelUtils.moveParts(frame, this.LFShoulder1, KF_Throw_LFShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.LArm2, KF_Throw_LArm2, partialTick);
    ModelUtils.moveParts(frame, this.LARM, KF_Throw_LARM, partialTick);
    ModelUtils.moveParts(frame, this.RLeg2, KF_Throw_RLeg2, partialTick);
    ModelUtils.moveParts(frame, this.RRShoulder2, KF_Throw_RRShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.RFShoulder1, KF_Throw_RFShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RRHip, KF_Throw_RRHip, partialTick);
    ModelUtils.moveParts(frame, this.LLEG, KF_Throw_LLEG, partialTick);
    ModelUtils.moveParts(frame, this.LRShoulder1, KF_Throw_LRShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RLeg1, KF_Throw_RLeg1, partialTick);
    ModelUtils.moveParts(frame, this.LArm1, KF_Throw_LArm1, partialTick);
    ModelUtils.moveParts(frame, this.LFShoulder2, KF_Throw_LFShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.LLeg2, KF_Throw_LLeg2, partialTick);
    ModelUtils.moveParts(frame, this.RArm2, KF_Throw_RArm2, partialTick);
    ModelUtils.moveParts(frame, this.LChest, KF_Throw_LChest, partialTick);
    ModelUtils.moveParts(frame, this.RFShoulder2, KF_Throw_RFShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.LLeg1, KF_Throw_LLeg1, partialTick);
    ModelUtils.moveParts(frame, this.RFHip, KF_Throw_RFHip, partialTick);
    ModelUtils.moveParts(frame, this.HEAD, KF_Throw_HEAD, partialTick);
    ModelUtils.moveParts(frame, this.RRShoulder1, KF_Throw_RRShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RARM, KF_Throw_RARM, partialTick);
    ModelUtils.moveParts(frame, this.RLEG, KF_Throw_RLEG, partialTick);
    ModelUtils.moveParts(frame, this.LRHip, KF_Throw_LRHip, partialTick);
  }
  
  static final KeyFrame[] KF_Build_HEAD = new KeyFrame[3];
  
  static final KeyFrame[] KF_Build_LRShoulder2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_LFShoulder1 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_RRShoulder2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_RFShoulder1 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_LRShoulder1 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_LFShoulder2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_RFShoulder2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_RRShoulder1 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_LARM = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_LArm1 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_LArm2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_RARM = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_RArm1 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_RArm2 = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_RChest = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_LChest = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_WAIST = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_LFHip = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_RRHip = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_RFHip = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_LRHip = new KeyFrame[4];
  
  static final KeyFrame[] KF_Build_RLEG = new KeyFrame[3];
  
  static final KeyFrame[] KF_Build_RLeg1 = new KeyFrame[3];
  
  static final KeyFrame[] KF_Build_RLeg2 = new KeyFrame[3];
  
  static final KeyFrame[] KF_Build_LLEG = new KeyFrame[3];
  
  static final KeyFrame[] KF_Build_LLeg1 = new KeyFrame[3];
  
  static final KeyFrame[] KF_Build_LLeg2 = new KeyFrame[3];
  
  static final KeyFrame[] KF_Build_HIP = new KeyFrame[1];
  
  public void build_Build() {
    KF_Build_HIP[0] = new KeyFrame(0, 0.0F, -9.0F, 0.0F, 0.0F, 90.0F, 0.0F);
    KF_Build_LFHip[0] = new KeyFrame(0, 26.5F, 49.0F, -51.5F, 140.0F, -170.0F, 112.0F);
    KF_Build_LFHip[1] = new KeyFrame(10, 26.5F, 49.0F, -51.5F, 140.0F, -170.0F, 112.0F);
    KF_Build_LFHip[2] = new KeyFrame(30, 5.5F, -9.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Build_LFHip[3] = new KeyFrame(90, 5.5F, -9.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Build_RChest[0] = new KeyFrame(0, 0.0F, 60.0F, -49.0F, -222.0F, 222.0F, 111.0F);
    KF_Build_RChest[1] = new KeyFrame(20, 0.0F, 60.0F, -49.0F, -222.0F, 222.0F, 111.0F);
    KF_Build_RChest[2] = new KeyFrame(40, 0.0F, -38.0F, -8.0F, 0.0F, 0.0F, 0.0F);
    KF_Build_RChest[3] = new KeyFrame(90, 0.0F, -38.0F, -8.0F, 0.0F, 0.0F, 0.0F);
    KF_Build_LRShoulder2[0] = new KeyFrame(0, -55.22F, 60.0F, 23.0F, -200.0F, 90.0F, 50.0F);
    KF_Build_LRShoulder2[1] = new KeyFrame(30, -55.22F, 60.0F, 23.0F, -200.0F, 90.0F, 50.0F);
    KF_Build_LRShoulder2[2] = new KeyFrame(50, -4.22F, -42.0F, 18.0F, -30.0F, 0.0F, -15.0F);
    KF_Build_LRShoulder2[3] = new KeyFrame(90, -4.22F, -42.0F, 18.0F, -30.0F, 0.0F, -15.0F);
    KF_Build_RArm1[0] = new KeyFrame(0, -63.98F, 93.0F, 46.01F, 81.0F, -91.0F, -55.0F);
    KF_Build_RArm1[1] = new KeyFrame(45, -63.98F, 93.0F, 46.01F, 81.0F, -91.0F, -55.0F);
    KF_Build_RArm1[2] = new KeyFrame(65, 0.02F, 12.0F, 0.01F, 25.0F, 0.0F, 0.0F);
    KF_Build_RArm1[3] = new KeyFrame(90, 0.02F, 12.0F, 0.01F, 25.0F, 0.0F, 0.0F);
    KF_Build_LFShoulder1[0] = new KeyFrame(0, 32.52F, 50.0F, 29.5F, 230.0F, 170.0F, 100.0F);
    KF_Build_LFShoulder1[1] = new KeyFrame(25, 32.52F, 50.0F, 29.5F, 230.0F, 170.0F, 100.0F);
    KF_Build_LFShoulder1[2] = new KeyFrame(45, 5.52F, -47.0F, 5.5F, -15.0F, 0.0F, 15.0F);
    KF_Build_LFShoulder1[3] = new KeyFrame(90, 5.52F, -47.0F, 5.5F, -15.0F, 0.0F, 15.0F);
    KF_Build_LArm2[0] = new KeyFrame(0, 49.02F, 87.0F, 40.01F, 30.0F, 88.0F, 120.0F);
    KF_Build_LArm2[1] = new KeyFrame(50, 49.02F, 87.0F, 40.01F, 30.0F, 88.0F, 120.0F);
    KF_Build_LArm2[2] = new KeyFrame(70, 0.02F, 12.0F, 0.01F, -10.0F, 0.0F, 0.0F);
    KF_Build_LArm2[3] = new KeyFrame(90, 0.02F, 12.0F, 0.01F, -10.0F, 0.0F, 0.0F);
    KF_Build_LARM[0] = new KeyFrame(0, -52.0F, 64.0F, 55.0F, 88.0F, 220.0F, 19.0F);
    KF_Build_LARM[1] = new KeyFrame(35, -52.0F, 64.0F, 55.0F, 88.0F, 220.0F, 19.0F);
    KF_Build_LARM[2] = new KeyFrame(55, 0.0F, -40.0F, 21.0F, 40.0F, 0.0F, 0.0F);
    KF_Build_LARM[3] = new KeyFrame(90, 0.0F, -40.0F, 21.0F, 40.0F, 0.0F, 0.0F);
    KF_Build_RLeg2[0] = new KeyFrame(0, -34.99F, 56.0F, -10.99F, 56.0F, -50.0F, 200.0F);
    KF_Build_RLeg2[1] = new KeyFrame(20, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Build_RLeg2[2] = new KeyFrame(90, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Build_RRShoulder2[0] = new KeyFrame(0, -47.2F, 50.0F, -33.0F, -50.0F, 200.0F, 90.0F);
    KF_Build_RRShoulder2[1] = new KeyFrame(30, -47.2F, 50.0F, -33.0F, -50.0F, 200.0F, 90.0F);
    KF_Build_RRShoulder2[2] = new KeyFrame(50, -4.2F, -42.0F, -18.0F, 30.0F, 0.0F, -15.0F);
    KF_Build_RRShoulder2[3] = new KeyFrame(90, -4.2F, -42.0F, -18.0F, 30.0F, 0.0F, -15.0F);
    KF_Build_RFShoulder1[0] = new KeyFrame(0, 59.5F, 50.0F, -58.5F, 100.0F, 200.0F, -200.0F);
    KF_Build_RFShoulder1[1] = new KeyFrame(25, 59.5F, 50.0F, -58.5F, 100.0F, 200.0F, -200.0F);
    KF_Build_RFShoulder1[2] = new KeyFrame(45, 5.5F, -47.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Build_RFShoulder1[3] = new KeyFrame(90, 5.5F, -47.0F, -5.5F, 15.0F, 0.0F, 15.0F);
    KF_Build_RRHip[0] = new KeyFrame(0, -41.5F, 53.0F, 37.5F, 222.0F, 111.0F, 200.0F);
    KF_Build_RRHip[1] = new KeyFrame(10, -41.5F, 53.0F, 37.5F, 222.0F, 111.0F, 200.0F);
    KF_Build_RRHip[2] = new KeyFrame(30, -5.5F, -9.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Build_RRHip[3] = new KeyFrame(90, -5.5F, -9.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Build_LLEG[0] = new KeyFrame(0, -20.0F, 42.0F, 20.0F, 200.0F, 200.0F, 200.0F);
    KF_Build_LLEG[1] = new KeyFrame(20, 0.0F, -11.0F, 7.0F, 20.0F, 0.0F, 0.0F);
    KF_Build_LLEG[2] = new KeyFrame(90, 0.0F, -11.0F, 7.0F, 20.0F, 0.0F, 0.0F);
    KF_Build_LRShoulder1[0] = new KeyFrame(0, -15.5F, 55.0F, 35.5F, 120.0F, -200.0F, 160.0F);
    KF_Build_LRShoulder1[1] = new KeyFrame(25, -15.5F, 55.0F, 35.5F, 120.0F, -200.0F, 160.0F);
    KF_Build_LRShoulder1[2] = new KeyFrame(45, -5.5F, -47.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Build_LRShoulder1[3] = new KeyFrame(90, -5.5F, -47.0F, 5.5F, -15.0F, 0.0F, -15.0F);
    KF_Build_RLeg1[0] = new KeyFrame(0, 0.02F, 41.3F, -13.5F, 20.0F, -60.0F, 0.0F);
    KF_Build_RLeg1[1] = new KeyFrame(20, 0.02F, 13.3F, -0.5F, 20.0F, 0.0F, 0.0F);
    KF_Build_RLeg1[2] = new KeyFrame(90, 0.02F, 13.3F, -0.5F, 20.0F, 0.0F, 0.0F);
    KF_Build_LArm1[0] = new KeyFrame(0, -48.98F, 74.0F, -59.99F, 80.0F, 120.0F, 200.0F);
    KF_Build_LArm1[1] = new KeyFrame(46, -48.98F, 74.0F, -59.99F, 80.0F, 120.0F, 200.0F);
    KF_Build_LArm1[2] = new KeyFrame(66, 0.02F, 12.0F, 0.01F, -25.0F, 0.0F, 0.0F);
    KF_Build_LArm1[3] = new KeyFrame(90, 0.02F, 12.0F, 0.01F, -25.0F, 0.0F, 0.0F);
    KF_Build_LFShoulder2[0] = new KeyFrame(0, 45.21F, 56.0F, 44.0F, 160.0F, 200.0F, -200.0F);
    KF_Build_LFShoulder2[1] = new KeyFrame(30, 45.21F, 56.0F, 44.0F, 160.0F, 200.0F, -200.0F);
    KF_Build_LFShoulder2[2] = new KeyFrame(50, 4.21F, -42.0F, 18.0F, -30.0F, 0.0F, 15.0F);
    KF_Build_LFShoulder2[3] = new KeyFrame(90, 4.21F, -42.0F, 18.0F, -30.0F, 0.0F, 15.0F);
    KF_Build_LLeg2[0] = new KeyFrame(0, 22.01F, 48.0F, -20.99F, 100.0F, -100.0F, 50.0F);
    KF_Build_LLeg2[1] = new KeyFrame(20, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Build_LLeg2[2] = new KeyFrame(90, 0.01F, 16.0F, 0.01F, 0.0F, 0.0F, 0.0F);
    KF_Build_RArm2[0] = new KeyFrame(0, 13.02F, 90.0F, 0.01F, 200.0F, 190.0F, 55.0F);
    KF_Build_RArm2[1] = new KeyFrame(48, 13.02F, 90.0F, 0.01F, 200.0F, 190.0F, 55.0F);
    KF_Build_RArm2[2] = new KeyFrame(68, 0.02F, 12.0F, 0.01F, 10.0F, 0.0F, 0.0F);
    KF_Build_RArm2[3] = new KeyFrame(90, 0.02F, 12.0F, 0.01F, 10.0F, 0.0F, 0.0F);
    KF_Build_LChest[0] = new KeyFrame(0, 0.0F, 60.0F, 63.0F, 220.0F, -170.0F, 160.0F);
    KF_Build_LChest[1] = new KeyFrame(20, 0.0F, 60.0F, 63.0F, 220.0F, -170.0F, 160.0F);
    KF_Build_LChest[2] = new KeyFrame(40, 0.0F, -38.0F, 8.0F, 0.0F, 0.0F, 0.0F);
    KF_Build_LChest[3] = new KeyFrame(90, 0.0F, -38.0F, 8.0F, 0.0F, 0.0F, 0.0F);
    KF_Build_RFShoulder2[0] = new KeyFrame(0, 38.2F, 60.0F, -35.0F, -90.0F, 120.0F, -60.0F);
    KF_Build_RFShoulder2[1] = new KeyFrame(30, 38.2F, 60.0F, -35.0F, -90.0F, 120.0F, -60.0F);
    KF_Build_RFShoulder2[2] = new KeyFrame(50, 4.2F, -42.0F, -18.0F, 30.0F, 0.0F, 15.0F);
    KF_Build_RFShoulder2[3] = new KeyFrame(90, 4.2F, -42.0F, -18.0F, 30.0F, 0.0F, 15.0F);
    KF_Build_LLeg1[0] = new KeyFrame(0, 12.02F, 38.3F, 13.5F, 100.0F, 201.0F, 0.0F);
    KF_Build_LLeg1[1] = new KeyFrame(20, 0.02F, 13.3F, 0.5F, -20.0F, 0.0F, 0.0F);
    KF_Build_LLeg1[2] = new KeyFrame(90, 0.02F, 13.3F, 0.5F, -20.0F, 0.0F, 0.0F);
    KF_Build_RFHip[0] = new KeyFrame(0, 27.52F, 50.0F, 42.5F, -200.0F, 70.0F, -80.0F);
    KF_Build_RFHip[1] = new KeyFrame(10, 27.52F, 50.0F, 42.5F, -200.0F, 70.0F, -80.0F);
    KF_Build_RFHip[2] = new KeyFrame(30, 5.52F, -9.0F, 5.5F, -15.0F, 0.0F, 15.0F);
    KF_Build_RFHip[3] = new KeyFrame(90, 5.52F, -9.0F, 5.5F, -15.0F, 0.0F, 15.0F);
    KF_Build_HEAD[0] = new KeyFrame(0, 70.0F, 54.0F, 0.0F, 33.0F, 48.0F, 160.0F);
    KF_Build_HEAD[1] = new KeyFrame(70, 70.0F, 54.0F, 0.0F, 33.0F, 48.0F, 160.0F);
    KF_Build_HEAD[2] = new KeyFrame(90, 0.0F, -54.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Build_RRShoulder1[0] = new KeyFrame(0, -40.0F, 70.0F, -43.5F, 200.0F, 200.0F, 90.0F);
    KF_Build_RRShoulder1[1] = new KeyFrame(25, -40.0F, 70.0F, -43.5F, 200.0F, 200.0F, 90.0F);
    KF_Build_RRShoulder1[2] = new KeyFrame(45, -5.52F, -47.0F, -5.5F, 15.0F, 0.0F, -15.0F);
    KF_Build_RRShoulder1[3] = new KeyFrame(90, -5.52F, -47.0F, -5.5F, 15.0F, 0.0F, -15.0F);
    KF_Build_RARM[0] = new KeyFrame(0, -3.0F, 51.0F, -63.0F, -101.0F, 42.0F, -28.0F);
    KF_Build_RARM[1] = new KeyFrame(40, -3.0F, 51.0F, -63.0F, -101.0F, 42.0F, -28.0F);
    KF_Build_RARM[2] = new KeyFrame(60, 0.0F, -40.0F, -21.0F, -40.0F, 0.0F, 0.0F);
    KF_Build_RARM[3] = new KeyFrame(90, 0.0F, -40.0F, -21.0F, -40.0F, 0.0F, 0.0F);
    KF_Build_RLEG[0] = new KeyFrame(0, 35.0F, 63.0F, -26.0F, 100.0F, 150.0F, 10.0F);
    KF_Build_RLEG[1] = new KeyFrame(20, 0.0F, -11.0F, -7.0F, -20.0F, 0.0F, 0.0F);
    KF_Build_RLEG[2] = new KeyFrame(90, 0.0F, -11.0F, -7.0F, -20.0F, 0.0F, 0.0F);
    KF_Build_LRHip[0] = new KeyFrame(0, -40.52F, 53.0F, -50.5F, 222.0F, 111.0F, 111.0F);
    KF_Build_LRHip[1] = new KeyFrame(10, -40.52F, 53.0F, -50.5F, 222.0F, 111.0F, 111.0F);
    KF_Build_LRHip[2] = new KeyFrame(30, -5.52F, -9.0F, -5.5F, 15.0F, 0.0F, -15.0F);
    KF_Build_LRHip[3] = new KeyFrame(90, -5.52F, -9.0F, -5.5F, 15.0F, 0.0F, -15.0F);
    KF_Build_WAIST[0] = new KeyFrame(0, 0.0F, 46.0F, 39.0F, -25.0F, 24.0F, -22.0F);
    KF_Build_WAIST[1] = new KeyFrame(15, 0.0F, 46.0F, 39.0F, -25.0F, 24.0F, -22.0F);
    KF_Build_WAIST[2] = new KeyFrame(35, 0.0F, -25.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    KF_Build_WAIST[3] = new KeyFrame(90, 0.0F, -25.0F, 0.0F, 0.0F, 0.0F, 0.0F);
  }
  
  public void Build(int frame, float partialTick) {
    ModelUtils.moveParts(frame, this.HIP, KF_Build_HIP, partialTick);
    ModelUtils.moveParts(frame, this.WAIST, KF_Build_WAIST, partialTick);
    ModelUtils.moveParts(frame, this.LFHip, KF_Build_LFHip, partialTick);
    ModelUtils.moveParts(frame, this.RChest, KF_Build_RChest, partialTick);
    ModelUtils.moveParts(frame, this.LRShoulder2, KF_Build_LRShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.RArm1, KF_Build_RArm1, partialTick);
    ModelUtils.moveParts(frame, this.LFShoulder1, KF_Build_LFShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.LArm2, KF_Build_LArm2, partialTick);
    ModelUtils.moveParts(frame, this.LARM, KF_Build_LARM, partialTick);
    ModelUtils.moveParts(frame, this.RLeg2, KF_Build_RLeg2, partialTick);
    ModelUtils.moveParts(frame, this.RRShoulder2, KF_Build_RRShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.RFShoulder1, KF_Build_RFShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RRHip, KF_Build_RRHip, partialTick);
    ModelUtils.moveParts(frame, this.LLEG, KF_Build_LLEG, partialTick);
    ModelUtils.moveParts(frame, this.LRShoulder1, KF_Build_LRShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RLeg1, KF_Build_RLeg1, partialTick);
    ModelUtils.moveParts(frame, this.LArm1, KF_Build_LArm1, partialTick);
    ModelUtils.moveParts(frame, this.LFShoulder2, KF_Build_LFShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.LLeg2, KF_Build_LLeg2, partialTick);
    ModelUtils.moveParts(frame, this.RArm2, KF_Build_RArm2, partialTick);
    ModelUtils.moveParts(frame, this.LChest, KF_Build_LChest, partialTick);
    ModelUtils.moveParts(frame, this.RFShoulder2, KF_Build_RFShoulder2, partialTick);
    ModelUtils.moveParts(frame, this.LLeg1, KF_Build_LLeg1, partialTick);
    ModelUtils.moveParts(frame, this.RFHip, KF_Build_RFHip, partialTick);
    ModelUtils.moveParts(frame, this.HEAD, KF_Build_HEAD, partialTick);
    ModelUtils.moveParts(frame, this.RRShoulder1, KF_Build_RRShoulder1, partialTick);
    ModelUtils.moveParts(frame, this.RARM, KF_Build_RARM, partialTick);
    ModelUtils.moveParts(frame, this.RLEG, KF_Build_RLEG, partialTick);
    ModelUtils.moveParts(frame, this.LRHip, KF_Build_LRHip, partialTick);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\model\ModelGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */