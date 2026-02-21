package fr.paladium.palamod.modules.luckyblock.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAIDolphinAttack extends EntityAIBase {
  World worldObj;
  
  EntityCreature attacker;
  
  int attackTick;
  
  double speedTowardsTarget;
  
  boolean longMemory;
  
  PathEntity entityPathEntity;
  
  Class classTarget;
  
  private int field_75445_i;
  
  private double field_151497_i;
  
  private double field_151495_j;
  
  private double field_151496_k;
  
  private static final String __OBFID = "CL_00001595";
  
  private int failedPathFindingPenalty;
  
  private static final DamageSource dolphinDS = new DamageSource("dolphin.attack");
  
  public EntityAIDolphinAttack(EntityCreature p_i1635_1_, Class p_i1635_2_, double p_i1635_3_, boolean p_i1635_5_) {
    this(p_i1635_1_, p_i1635_3_, p_i1635_5_);
    this.classTarget = p_i1635_2_;
  }
  
  public EntityAIDolphinAttack(EntityCreature p_i1636_1_, double p_i1636_2_, boolean p_i1636_4_) {
    this.attacker = p_i1636_1_;
    this.worldObj = p_i1636_1_.field_70170_p;
    this.speedTowardsTarget = p_i1636_2_;
    this.longMemory = p_i1636_4_;
    func_75248_a(3);
  }
  
  public boolean func_75250_a() {
    EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
    if (entitylivingbase == null)
      return false; 
    if (!entitylivingbase.func_70089_S())
      return false; 
    if (this.classTarget != null && !this.classTarget.isAssignableFrom(entitylivingbase.getClass()))
      return false; 
    if (--this.field_75445_i <= 0) {
      this.entityPathEntity = this.attacker.func_70661_as().func_75494_a((Entity)entitylivingbase);
      this.field_75445_i = 4 + this.attacker.func_70681_au().nextInt(7);
      return (this.entityPathEntity != null);
    } 
    return true;
  }
  
  public boolean func_75253_b() {
    EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
    return (entitylivingbase == null) ? false : (!entitylivingbase.func_70089_S() ? false : (!this.longMemory ? (!this.attacker.func_70661_as().func_75500_f()) : this.attacker.func_110176_b(MathHelper.func_76128_c(entitylivingbase.field_70165_t), MathHelper.func_76128_c(entitylivingbase.field_70163_u), MathHelper.func_76128_c(entitylivingbase.field_70161_v))));
  }
  
  public void func_75249_e() {
    this.attacker.func_70661_as().func_75484_a(this.entityPathEntity, this.speedTowardsTarget);
    this.field_75445_i = 0;
  }
  
  public void func_75251_c() {
    this.attacker.func_70661_as().func_75499_g();
  }
  
  public void func_75246_d() {
    EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
    this.attacker.func_70671_ap().func_75651_a((Entity)entitylivingbase, 30.0F, 30.0F);
    double d0 = this.attacker.func_70092_e(entitylivingbase.field_70165_t, entitylivingbase.field_70121_D.field_72338_b, entitylivingbase.field_70161_v);
    double d1 = (this.attacker.field_70130_N * 2.0F * this.attacker.field_70130_N * 2.0F + entitylivingbase.field_70130_N);
    this.field_75445_i--;
    if ((this.longMemory || this.attacker.func_70635_at().func_75522_a((Entity)entitylivingbase)) && this.field_75445_i <= 0 && ((this.field_151497_i == 0.0D && this.field_151495_j == 0.0D && this.field_151496_k == 0.0D) || entitylivingbase.func_70092_e(this.field_151497_i, this.field_151495_j, this.field_151496_k) >= 1.0D || this.attacker.func_70681_au().nextFloat() < 0.05F)) {
      this.field_151497_i = entitylivingbase.field_70165_t;
      this.field_151495_j = entitylivingbase.field_70121_D.field_72338_b;
      this.field_151496_k = entitylivingbase.field_70161_v;
      this.field_75445_i = this.failedPathFindingPenalty + 4 + this.attacker.func_70681_au().nextInt(7);
      if (this.attacker.func_70661_as().func_75505_d() != null) {
        PathPoint finalPathPoint = this.attacker.func_70661_as().func_75505_d().func_75870_c();
        if (finalPathPoint != null && entitylivingbase.func_70092_e(finalPathPoint.field_75839_a, finalPathPoint.field_75837_b, finalPathPoint.field_75838_c) < 1.0D) {
          this.failedPathFindingPenalty = 0;
        } else {
          this.failedPathFindingPenalty += 10;
        } 
      } else {
        this.failedPathFindingPenalty += 10;
      } 
      if (d0 > 1024.0D) {
        this.field_75445_i += 10;
      } else if (d0 > 256.0D) {
        this.field_75445_i += 5;
      } 
      if (!this.attacker.func_70661_as().func_75497_a((Entity)entitylivingbase, this.speedTowardsTarget))
        this.field_75445_i += 15; 
    } 
    this.attackTick = Math.max(this.attackTick - 1, 0);
    if (d0 <= d1 && this.attackTick <= 20) {
      this.attackTick = 20;
      entitylivingbase.func_70097_a(dolphinDS, 8.0F);
      this.attacker.func_70652_k((Entity)entitylivingbase);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\ai\EntityAIDolphinAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */