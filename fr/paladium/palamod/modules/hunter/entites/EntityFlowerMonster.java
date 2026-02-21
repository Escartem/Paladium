package fr.paladium.palamod.modules.hunter.entites;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.pojo.objectives.types.EntityKillSpecialObjective;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityFlowerMonster extends EntityZombie {
  public EntityFlowerMonster(World world) {
    super(world);
  }
  
  public void func_70645_a(DamageSource damageSource) {
    super.func_70645_a(damageSource);
    if (damageSource.func_76346_g() instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)damageSource.func_76346_g();
      UseItemAchievement.performCheck(player, player.func_71045_bC(), "MINI_BOSS_KILL_FLOWER_MONSTER");
      JobsPlayer jobPlayer = JobsPlayer.get((Entity)player);
      if (jobPlayer != null) {
        int xp = JobExpUtils.getNeededXpForLvl(jobPlayer.getLevel(JobType.ALCHEMIST) + 1) / 10;
        EntityKillSpecialObjective.performCheck(player, getClass(), xp);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\entites\EntityFlowerMonster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */