package fr.paladium.palamod.modules.back2future.dispenser;

import fr.paladium.palamod.modules.back2future.entities.EntityLingeringPotion;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DispenserBehaviourLingeringPotion implements IBehaviorDispenseItem {
  public ItemStack func_82482_a(IBlockSource block, final ItemStack stack) {
    return (new BehaviorProjectileDispense() {
        protected IProjectile func_82499_a(World world, IPosition pos) {
          return (IProjectile)new EntityLingeringPotion(world, pos.func_82615_a(), pos.func_82617_b(), pos.func_82616_c(), stack.func_77946_l());
        }
        
        protected float func_82498_a() {
          return super.func_82498_a() * 0.5F;
        }
        
        protected float func_82500_b() {
          return super.func_82500_b() * 1.25F;
        }
      }).func_82482_a(block, stack);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\dispenser\DispenserBehaviourLingeringPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */