package fr.paladium.palajobs.core.entity.boss;

import fr.paladium.palajobs.core.entity.boss.task.EntityAINearestPlayerTarget;
import fr.paladium.palajobs.core.entity.gecko.AnimatedEntityMob;
import fr.paladium.palajobs.core.entity.gecko.animation.AnimationType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityJobFarmerPlantBoss extends AnimatedEntityMob {
  private boolean activation;
  
  private boolean activated;
  
  public EntityJobFarmerPlantBoss(World world) {
    super(world);
    this.field_70715_bh.func_75776_a(0, (EntityAIBase)new EntityAINearestPlayerTarget((EntityCreature)this));
    setAnimationTransition(0.0F);
    setDefaultAnimation(AnimationType.IDLE, new String[] { "idle" });
    setDefaultAnimation(AnimationType.DEATH, new String[] { "death" });
    this.field_70724_aR = 20;
    this.field_70145_X = true;
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
  }
  
  public void func_70071_h_() {
    this.field_70159_w = 0.0D;
    this.field_70181_x = 0.0D;
    this.field_70179_y = 0.0D;
    super.func_70071_h_();
    if (this.field_70173_aa >= 1800) {
      func_70097_a(DamageSource.field_76376_m, Float.MAX_VALUE);
      return;
    } 
    if (!this.field_70170_p.field_72995_K && func_70638_az() != null) {
      double d0 = (func_70638_az()).field_70165_t - this.field_70165_t;
      double d1 = (func_70638_az()).field_70163_u - this.field_70163_u + func_70047_e();
      double d2 = (func_70638_az()).field_70161_v - this.field_70161_v;
      double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
      float f = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
      float f1 = (float)-(Math.atan2(d1, d3) * 180.0D / Math.PI);
      this.field_70125_A = f1;
      this.field_70177_z = f;
    } 
    if (!this.activated && !this.activation) {
      this.activation = true;
      playAnimation("evolution", 870L, false).setCallback(e -> {
            setDefaultAnimation(AnimationType.IDLE, new String[] { "idle_evo" });
            this.activated = true;
          });
    } 
    if (!this.activated)
      return; 
    if (func_70638_az() == null || isDeathAnimation() || this.field_70724_aR > 0)
      return; 
    playAnimation("palajobs:palajobs.boss.farmer.plant.attack", "attack", 800L, true).setCallback(e -> {
          if (func_70638_az() != null && func_70638_az().func_70089_S()) {
            EntityArrow entityarrow = new EntityArrow(this.field_70170_p, (EntityLivingBase)this, func_70638_az(), 1.5F, 0.0F);
            entityarrow.func_70239_b(50.0D);
            this.field_70170_p.func_72838_d((Entity)entityarrow);
          } 
        });
    this.field_70724_aR = 40;
  }
  
  public float func_70047_e() {
    return this.field_70131_O * 0.65F;
  }
  
  public boolean func_70650_aV() {
    return true;
  }
  
  public boolean func_85032_ar() {
    return (!this.activated || super.func_85032_ar());
  }
  
  public void func_70623_bb() {}
  
  public boolean func_70104_M() {
    return false;
  }
  
  public boolean func_82171_bF() {
    return false;
  }
  
  public void func_82167_n(Entity entity) {}
  
  public void func_85033_bc() {}
  
  public boolean func_70692_ba() {
    return false;
  }
  
  public boolean func_90999_ad() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\boss\EntityJobFarmerPlantBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */