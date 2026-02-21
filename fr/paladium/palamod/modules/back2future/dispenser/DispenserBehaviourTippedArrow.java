package fr.paladium.palamod.modules.back2future.dispenser;

import fr.paladium.palamod.modules.back2future.entities.EntityTippedArrow;
import fr.paladium.palamod.modules.back2future.items.TippedArrow;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DispenserBehaviourTippedArrow extends BehaviorDefaultDispenseItem {
  public ItemStack func_82487_b(IBlockSource block, final ItemStack stack) {
    return (new BehaviorProjectileDispense() {
        protected IProjectile func_82499_a(World world, IPosition pos) {
          EntityTippedArrow entity = new EntityTippedArrow(world, pos.func_82615_a(), pos.func_82617_b(), pos.func_82616_c());
          entity.field_70251_a = 1;
          entity.setEffect(TippedArrow.getEffect(stack));
          return (IProjectile)entity;
        }
      }).func_82482_a(block, stack);
  }
  
  protected void func_82485_a(IBlockSource block) {
    block.func_82618_k().func_72926_e(1002, block.func_82623_d(), block.func_82622_e(), block.func_82621_f(), 0);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\dispenser\DispenserBehaviourTippedArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */