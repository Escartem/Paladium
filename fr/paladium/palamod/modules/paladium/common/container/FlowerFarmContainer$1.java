package fr.paladium.palamod.modules.paladium.common.container;

import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockFlowerFarm;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

class null extends Slot {
  null(IInventory x0, int x1, int x2, int x3) {
    super(x0, x1, x2, x3);
  }
  
  public boolean func_75214_a(ItemStack is) {
    return (is != null && is.func_77969_a(BlockFlowerFarm.FLOWER_FARM_FUEL));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\FlowerFarmContainer$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */