package fr.paladium.palamod.modules.palaboss.common.entity.ia;

import fr.paladium.palamod.modules.palaboss.common.attacks.Attack;
import fr.paladium.palamod.modules.palaboss.common.attacks.EarthQuakeAttack;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityTobalt;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;

public class EntityAIGroundAttack extends EntityAIBase {
  private final EntityBossBase entityHost;
  
  private final IRangedAttackMob rangedAttackEntityHost;
  
  private Attack lastAttack;
  
  private EntityLivingBase attackTarget;
  
  private double entityMoveSpeed;
  
  private double field_82642_h;
  
  private float field_96562_i;
  
  private int field_96561_g;
  
  private int field_75318_f;
  
  private int maxRangedAttackTime;
  
  private int rangedAttackTime;
  
  private long maxTickTime;
  
  private int pathFindDelay;
  
  private int attackDelay;
  
  private float lastEarthQuakeHealth = 6500.0F;
  
  public EntityAIGroundAttack(IRangedAttackMob p_i1650_1_, double p_i1650_2_, int p_i1650_4_, int p_i1650_5_, float p_i1650_6_) {
    this.rangedAttackTime = -1;
    if (!(p_i1650_1_ instanceof EntityLivingBase))
      throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob"); 
    this.rangedAttackEntityHost = p_i1650_1_;
    this.entityHost = (EntityBossBase)p_i1650_1_;
    this.entityMoveSpeed = p_i1650_2_;
    this.field_96561_g = p_i1650_4_;
    this.maxRangedAttackTime = p_i1650_5_;
    this.field_96562_i = p_i1650_6_;
    this.field_82642_h = (p_i1650_6_ * p_i1650_6_);
    func_75248_a(3);
    this.lastEarthQuakeHealth = this.entityHost.func_110138_aP();
  }
  
  public boolean func_75250_a() {
    EntityLivingBase entitylivingbase = this.entityHost.func_70638_az();
    if (this.entityHost.getPunchTimer() > 0)
      return false; 
    if (entitylivingbase == null)
      return false; 
    if (entitylivingbase.func_70089_S()) {
      if (this.attackTarget != entitylivingbase) {
        this.attackTarget = entitylivingbase;
        this.pathFindDelay = 0;
      } 
      return true;
    } 
    return false;
  }
  
  public boolean func_75253_b() {
    return (func_75250_a() || !this.entityHost.func_70661_as().func_75500_f());
  }
  
  public void func_75251_c() {
    this.attackTarget = null;
    this.rangedAttackTime = -1;
    this.field_75318_f = 0;
  }
  
  public void func_75246_d() {
    double d0 = this.entityHost.func_70092_e(this.attackTarget.field_70165_t, this.attackTarget.field_70121_D.field_72338_b, this.attackTarget.field_70161_v);
    boolean flag = this.entityHost.func_70635_at().func_75522_a((Entity)this.attackTarget);
    EarthQuakeAttack earthQuakeAttack = null;
    for (Attack attack : this.entityHost.getAttacks()) {
      if (attack instanceof EarthQuakeAttack)
        earthQuakeAttack = (EarthQuakeAttack)attack; 
    } 
    if (earthQuakeAttack != null && 
      this.lastEarthQuakeHealth - this.entityHost.func_110138_aP() / 100.0F * 15.0F > this.entityHost
      .func_110143_aJ()) {
      this.lastEarthQuakeHealth = this.entityHost.func_110143_aJ();
      if (!earthQuakeAttack.isRunning()) {
        earthQuakeAttack.execute(this.entityHost.field_70170_p, this.entityHost, null);
        return;
      } 
    } 
    if (this.entityHost instanceof EntityTobalt) {
      EntityTobalt tobalt = (EntityTobalt)this.entityHost;
      if (tobalt.isStunt())
        return; 
    } 
    if (flag) {
      this.field_75318_f++;
    } else {
      this.field_75318_f = 0;
    } 
    if (d0 <= this.field_82642_h && this.field_75318_f >= 20) {
      this.entityHost.func_70661_as().func_75499_g();
      this.pathFindDelay = 50;
    } else {
      this.pathFindDelay--;
      if (this.pathFindDelay <= 0) {
        this.entityHost.func_70661_as().func_75497_a((Entity)this.attackTarget, this.entityMoveSpeed);
        this.pathFindDelay = 50 + this.entityHost.field_70170_p.field_73012_v.nextInt(30);
      } 
    } 
    if (this.attackDelay > 0)
      this.attackDelay--; 
    this.entityHost.func_70671_ap().func_75651_a((Entity)this.attackTarget, 30.0F, 30.0F);
    if (--this.rangedAttackTime == 0) {
      if (!flag && d0 > 10.0D)
        return; 
      float f = MathHelper.func_76133_a(d0) / this.field_96562_i;
      float f1 = f;
      if (f < 0.1F)
        f1 = 0.1F; 
      if (f1 > 1.0F)
        f1 = 1.0F; 
      List<Attack> availableAttacks = new ArrayList<>();
      for (Attack a : this.entityHost.getAttacks()) {
        int min = a.getMinRange() * this.entityHost.getAttributes().getAttributeInteger("sizeMultiplier", 1) * this.entityHost.getAttributes().getAttributeInteger("sizeMultiplier", 1);
        int max = a.getMaxRange() * this.entityHost.getAttributes().getAttributeInteger("sizeMultiplier", 1) * this.entityHost.getAttributes().getAttributeInteger("sizeMultiplier", 1);
        if (d0 < max && d0 > min && !(a instanceof EarthQuakeAttack))
          availableAttacks.add(a); 
      } 
      int attackCount = availableAttacks.size();
      if (attackCount > 0 && this.attackDelay <= 0) {
        Attack toAttack = null;
        toAttack = availableAttacks.get(this.entityHost.field_70170_p.field_73012_v.nextInt(attackCount));
        if (toAttack != null) {
          if (this.lastAttack != null && 
            this.lastAttack.isRunning()) {
            this
              .rangedAttackTime = MathHelper.func_76141_d(f * (this.maxRangedAttackTime - this.field_96561_g) + this.field_96561_g);
            return;
          } 
          this.lastAttack = toAttack;
          toAttack.execute(this.entityHost.field_70170_p, this.entityHost, this.attackTarget);
          if (this.entityHost instanceof fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityUlcan)
            this.attackDelay = 80; 
        } 
        this
          .rangedAttackTime = MathHelper.func_76141_d(f * (this.maxRangedAttackTime - this.field_96561_g) + this.field_96561_g);
      } 
    } else if (this.rangedAttackTime < 0) {
      float f = MathHelper.func_76133_a(d0) / this.field_96562_i;
      this.rangedAttackTime = MathHelper.func_76141_d(f * (this.maxRangedAttackTime - this.field_96561_g) + this.field_96561_g);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\entity\ia\EntityAIGroundAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */