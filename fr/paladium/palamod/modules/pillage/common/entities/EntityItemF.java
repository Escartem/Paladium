package fr.paladium.palamod.modules.pillage.common.entities;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityItemF extends EntityItem {
  public EntityItemF(World world) {
    super(world);
  }
  
  public EntityItemF(World world, double x, double y, double z, ItemStack stack) {
    super(world);
    func_70107_b(x, y, z);
    this.field_70177_z = 0.0F;
    func_92058_a(stack);
    this.lifespan = (stack.func_77973_b() == null) ? 6000 : stack.func_77973_b().getEntityLifespan(stack, world);
    this.field_70290_d = 0.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\entities\EntityItemF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */