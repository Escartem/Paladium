package fr.paladium.palamod.modules.back2future.dispenser;

import fr.paladium.palamod.modules.back2future.items.ItemEntityEgg;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class DispenserBehaviourSpawnEgg extends BehaviorDefaultDispenseItem {
  public ItemStack func_82487_b(IBlockSource block, ItemStack stack) {
    EnumFacing enumfacing = BlockDispenser.func_149937_b(block.func_82620_h());
    double d0 = block.func_82615_a() + enumfacing.func_82601_c();
    double d1 = (block.func_82622_e() + 0.2F);
    double d2 = block.func_82616_c() + enumfacing.func_82599_e();
    Entity entity = ItemEntityEgg.spawnEntity(block.func_82618_k(), stack.func_77960_j(), d0, d1, d2);
    if (entity instanceof net.minecraft.entity.EntityLivingBase && stack.func_82837_s())
      ((EntityLiving)entity).func_94058_c(stack.func_82833_r()); 
    stack.func_77979_a(1);
    return stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\dispenser\DispenserBehaviourSpawnEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */