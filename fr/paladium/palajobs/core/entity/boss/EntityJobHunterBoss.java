package fr.paladium.palajobs.core.entity.boss;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.entity.boss.attack.AttackProjectile;
import fr.paladium.palajobs.core.entity.boss.attack.AttackZone;
import fr.paladium.palajobs.core.entity.boss.task.EntityAIMoveTarget;
import fr.paladium.palajobs.core.entity.gecko.animation.AnimationType;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityJobHunterBoss extends AEntityJobBoss {
  private final String snakeEntity = "entitySnake";
  
  public EntityJobHunterBoss(World world) {
    super(world, JobType.HUNTER, 2.0D, 50000.0D, 0.2F, 0.5F, 0.5F, 30);
    setDefaultAnimation(AnimationType.IDLE, new String[] { "spawn_idle" });
    setDefaultAnimation(AnimationType.WALK, new String[] { "walk" });
    setDefaultAnimation(AnimationType.DEATH, new String[] { "death" });
    setDefaultAnimation(AnimationType.HURT, new String[] { "damaged_1", "damaged_2" });
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIMoveTarget((EntityCreature)this));
    func_70105_a(2.0F, 3.0F);
  }
  
  public void activate() {
    setDefaultAnimation(AnimationType.IDLE, new String[] { "idle" });
    this.attacks.add((new AttackZone(0.0D, 4.0D, 50, 18)).setCallback((e, t) -> {
            func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
            playAnimation("palajobs:palajobs.boss.hunter.punch", "punch_1", 870L, true).setCallback(());
            return Boolean.valueOf(true);
          }));
    this.attacks.add((new AttackProjectile(0.0D, 50.0D, 35, 39)).setCallback((e, t) -> {
            func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
            int arrow = this.field_70170_p.field_73012_v.nextInt(4);
            String animation = (arrow == 0) ? "shot_red" : ((arrow == 1) ? "shot_orange" : ((arrow == 2) ? "shot_green" : "shot_black"));
            String sound = (arrow == 0) ? "shot.red" : ((arrow == 1) ? "shot.orange" : ((arrow == 2) ? "shot.green" : "shot.black"));
            playAnimation("palajobs:palajobs.boss.hunter." + sound, animation, 1950L, true).setCallback(());
            return Boolean.valueOf(true);
          }));
    this.attacks.add((new AttackZone(0.0D, 32.0D, 15, 35)).setCallback((e, t) -> {
            func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
            playAnimation("palajobs:palajobs.boss.hunter.invocation", "invocation", 1750L, true).setCallback(());
            return Boolean.valueOf(true);
          }));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\boss\EntityJobHunterBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */