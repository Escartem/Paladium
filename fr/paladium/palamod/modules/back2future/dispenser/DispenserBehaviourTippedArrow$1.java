package fr.paladium.palamod.modules.back2future.dispenser;

import fr.paladium.palamod.modules.back2future.entities.EntityTippedArrow;
import fr.paladium.palamod.modules.back2future.items.TippedArrow;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

class null extends BehaviorProjectileDispense {
  protected IProjectile func_82499_a(World world, IPosition pos) {
    EntityTippedArrow entity = new EntityTippedArrow(world, pos.func_82615_a(), pos.func_82617_b(), pos.func_82616_c());
    entity.field_70251_a = 1;
    entity.setEffect(TippedArrow.getEffect(stack));
    return (IProjectile)entity;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\dispenser\DispenserBehaviourTippedArrow$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */