package fr.paladium.palajobs.core.entity.boss;

import fr.paladium.palajobs.core.entity.gecko.AnimatedEntityLiving;
import fr.paladium.palajobs.core.entity.gecko.animation.AnimationType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityJobMinerDigBoss extends AnimatedEntityLiving {
  public EntityJobMinerDigBoss(World world) {
    super(world);
    setDefaultAnimation(AnimationType.IDLE, new String[] { "idle" });
    func_85030_a("palajobs:palajobs.boss.miner.drillplayer", 0.4F, 1.0F);
    this.field_70145_X = true;
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(Double.MAX_VALUE);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
  }
  
  public void func_70071_h_() {
    this.field_70159_w = 0.0D;
    this.field_70181_x = 0.0D;
    this.field_70179_y = 0.0D;
    super.func_70071_h_();
    if (this.field_70173_aa >= 70)
      func_70106_y(); 
  }
  
  public boolean func_70097_a(DamageSource source, float damage) {
    return false;
  }
  
  public boolean func_85032_ar() {
    return true;
  }
  
  public boolean func_70650_aV() {
    return true;
  }
  
  public void func_70623_bb() {}
  
  public boolean func_70692_ba() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\boss\EntityJobMinerDigBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */