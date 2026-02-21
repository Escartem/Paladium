package fr.paladium.palaboss.common.entity.impl;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palaboss.common.entity.ai.attack.ABaseAttack;
import fr.paladium.palaboss.common.entity.impl.attack.DemoAttackProjectile;
import fr.paladium.palaboss.common.entity.properties.EntityProperties;
import fr.paladium.palaboss.common.reward.IReward;
import fr.paladium.palaboss.common.reward.impl.ItemReward;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import software.bernie.geckolib3.entity.animation.AnimationType;

public class DemoEntity extends EntityBossMob {
  public DemoEntity(World world) {
    super(world, EntityProperties.builder().health(100.0D).build());
    setDefaultAnimation(AnimationType.IDLE, new String[] { "idle" });
    setDefaultAnimation(AnimationType.WALK, new String[] { "walk" });
    setDefaultAnimation(AnimationType.DEATH, new String[] { "death" });
    setDefaultAnimation(AnimationType.HURT, new String[] { "damaged_1", "damaged_2" });
    func_70105_a(2.0F, 3.0F);
    addAttack((ABaseAttack)new DemoAttackProjectile(this));
  }
  
  public void func_70077_a(EntityLightningBolt bolt) {}
  
  public void registerRewards() {
    registerReward((IReward)new ItemReward(new ItemStack(Items.field_151034_e), 10));
    registerReward((IReward)new ItemReward(new ItemStack(Items.field_151104_aV), 1));
    registerReward((IReward)new ItemReward(new ItemStack(Items.field_151032_g), 100));
    registerReward((IReward)new ItemReward(new ItemStack(Items.field_151137_ax), 5));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\entity\impl\DemoEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */