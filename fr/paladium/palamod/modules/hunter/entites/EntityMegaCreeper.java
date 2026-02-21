package fr.paladium.palamod.modules.hunter.entites;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.pojo.objectives.types.EntityKillSpecialObjective;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class EntityMegaCreeper extends EntityCreeper {
  private int lastActiveTime;
  
  private int timeSinceIgnited;
  
  private final int fuseTime = 70;
  
  public EntityMegaCreeper(World world) {
    super(world);
  }
  
  public void func_70645_a(DamageSource damageSource) {
    super.func_70645_a(damageSource);
    if (damageSource.func_76346_g() instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)damageSource.func_76346_g();
      UseItemAchievement.performCheck(player, player.func_71045_bC(), "MINI_BOSS_KILL_MEGA_CREEPER");
      JobsPlayer jobPlayer = JobsPlayer.get((Entity)player);
      if (jobPlayer != null) {
        int xp = JobExpUtils.getNeededXpForLvl(jobPlayer.getLevel(JobType.HUNTER) + 1) / 10;
        EntityKillSpecialObjective.performCheck(player, getClass(), xp);
      } 
    } 
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
  }
  
  public void func_70071_h_() {
    if (ForgeHooks.onLivingUpdate((EntityLivingBase)this))
      return; 
    func_70030_z();
    if (!this.field_70170_p.field_72995_K) {
      int i = func_85035_bI();
      if (i > 0) {
        if (this.field_70720_be <= 0)
          this.field_70720_be = 20 * (30 - i); 
        this.field_70720_be--;
        if (this.field_70720_be <= 0)
          func_85034_r(i - 1); 
      } 
      if (this.field_70173_aa % 20 == 0)
        func_110142_aN().func_94549_h(); 
    } 
    func_70636_d();
    double d0 = this.field_70165_t - this.field_70169_q;
    double d1 = this.field_70161_v - this.field_70166_s;
    float f = (float)(d0 * d0 + d1 * d1);
    float f1 = this.field_70761_aq;
    float f2 = 0.0F;
    this.field_70768_au = this.field_110154_aX;
    float f3 = 0.0F;
    if (f > 0.0025000002F) {
      f3 = 1.0F;
      f2 = (float)Math.sqrt(f) * 3.0F;
      f1 = (float)Math.atan2(d1, d0) * 180.0F / 3.1415927F - 90.0F;
    } 
    if (this.field_70733_aJ > 0.0F)
      f1 = this.field_70177_z; 
    if (!this.field_70122_E)
      f3 = 0.0F; 
    this.field_110154_aX += (f3 - this.field_110154_aX) * 0.3F;
    this.field_70170_p.field_72984_F.func_76320_a("headTurn");
    f2 = func_110146_f(f1, f2);
    this.field_70170_p.field_72984_F.func_76319_b();
    this.field_70170_p.field_72984_F.func_76320_a("rangeChecks");
    while (this.field_70177_z - this.field_70126_B < -180.0F)
      this.field_70126_B -= 360.0F; 
    while (this.field_70177_z - this.field_70126_B >= 180.0F)
      this.field_70126_B += 360.0F; 
    while (this.field_70761_aq - this.field_70760_ar < -180.0F)
      this.field_70760_ar -= 360.0F; 
    while (this.field_70761_aq - this.field_70760_ar >= 180.0F)
      this.field_70760_ar += 360.0F; 
    while (this.field_70125_A - this.field_70127_C < -180.0F)
      this.field_70127_C -= 360.0F; 
    while (this.field_70125_A - this.field_70127_C >= 180.0F)
      this.field_70127_C += 360.0F; 
    while (this.field_70759_as - this.field_70758_at < -180.0F)
      this.field_70758_at -= 360.0F; 
    while (this.field_70759_as - this.field_70758_at >= 180.0F)
      this.field_70758_at += 360.0F; 
    this.field_70170_p.field_72984_F.func_76319_b();
    this.field_70764_aw += f2;
    if (func_70089_S()) {
      this.lastActiveTime = this.timeSinceIgnited;
      if (func_146078_ca())
        func_70829_a(1); 
      int i = func_70832_p();
      if (i > 0 && this.timeSinceIgnited == 0) {
        func_85030_a("creeper.primed", 1.0F, 0.5F);
        EventUtils.spawnParticle(this.field_70170_p, "lava", this.field_70165_t, this.field_70163_u, this.field_70161_v, 10, 0.03D);
      } 
      this.timeSinceIgnited += i;
      if (this.timeSinceIgnited < 0)
        this.timeSinceIgnited = 0; 
      getClass();
      if (this.timeSinceIgnited >= 70) {
        getClass();
        this.timeSinceIgnited = 70;
        func_146077_cc();
      } 
    } 
  }
  
  private void func_146077_cc() {
    if (!this.field_70170_p.field_72995_K) {
      boolean flag = this.field_70170_p.func_82736_K().func_82766_b("mobGriefing");
      if (func_70830_n()) {
        this.field_70170_p.func_72876_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 20.0F, flag);
      } else {
        this.field_70170_p.func_72885_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 10.0F, true, flag);
      } 
      func_70106_y();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\entites\EntityMegaCreeper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */