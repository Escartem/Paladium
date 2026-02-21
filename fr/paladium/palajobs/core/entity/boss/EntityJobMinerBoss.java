package fr.paladium.palajobs.core.entity.boss;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.entity.boss.attack.AttackMelee;
import fr.paladium.palajobs.core.entity.boss.attack.AttackProjectile;
import fr.paladium.palajobs.core.entity.boss.attack.AttackZone;
import fr.paladium.palajobs.core.entity.boss.task.EntityAIMoveTarget;
import fr.paladium.palajobs.core.entity.gecko.animation.AnimationType;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityJobMinerBoss extends AEntityJobBoss {
  public EntityJobMinerBoss(World world) {
    super(world, JobType.MINER, 2.0D, 50000.0D, 0.2F, 0.5F, 0.5F, 10);
    setDefaultAnimation(AnimationType.IDLE, new String[] { "spawn_idle" });
    setDefaultAnimation(AnimationType.WALK, new String[] { "walk" });
    setDefaultAnimation(AnimationType.DEATH, new String[] { "death" });
    setDefaultAnimation(AnimationType.HURT, new String[] { "damaged_1", "damaged_2" });
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIMoveTarget((EntityCreature)this));
    func_70105_a(2.0F, 3.0F);
  }
  
  public void activate() {
    setDefaultAnimation(AnimationType.IDLE, new String[] { "idle" });
    this.attacks.add((new AttackMelee(0.0D, 4.0D, 40, 18)).setCallback((e, t) -> {
            func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
            boolean flag = this.field_70170_p.field_73012_v.nextBoolean();
            String animation = flag ? "punch_1" : "punch_2";
            String sound = flag ? "palajobs.boss.miner.punch1" : "palajobs.boss.miner.punch2";
            playAnimation("palajobs:" + sound, animation, 870L, true).setCallback(());
            return Boolean.valueOf(true);
          }));
    this.attacks.add((new AttackProjectile(0.0D, 50.0D, 30, 47)).setCallback((e, t) -> {
            func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
            playAnimation("palajobs:palajobs.boss.miner.shot", "shot", 2330L, true).setCallback(());
            return Boolean.valueOf(true);
          }));
    this.attacks.add((new AttackZone(0.0D, 8.0D, 30, 30)).setCallback((e, t) -> {
            func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
            playAnimation("palajobs:palajobs.boss.miner.dig", "dig", 1500L, true).setCallback(());
            return Boolean.valueOf(true);
          }));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\boss\EntityJobMinerBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */