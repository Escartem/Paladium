package fr.paladium.palamod.modules.hunter.entites;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.pojo.objectives.types.EntityKillSpecialObjective;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityCavernousZombie extends EntityZombie {
  public EntityCavernousZombie(World world) {
    super(world);
  }
  
  public void func_70645_a(DamageSource damageSource) {
    super.func_70645_a(damageSource);
    if (damageSource.func_76346_g() instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)damageSource.func_76346_g();
      UseItemAchievement.performCheck(player, player.func_71045_bC(), "MINI_BOSS_KILL_CAVERNOUS_ZOMBIE");
      JobsPlayer jobPlayer = JobsPlayer.get((Entity)player);
      if (jobPlayer != null) {
        int xp = JobExpUtils.getNeededXpForLvl(jobPlayer.getLevel(JobType.MINER) + 1) / 10;
        EntityKillSpecialObjective.performCheck(player, getClass(), xp);
      } 
    } 
  }
  
  protected void func_82164_bB() {
    func_70062_b(0, new ItemStack(Items.field_151005_D));
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\entites\EntityCavernousZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */