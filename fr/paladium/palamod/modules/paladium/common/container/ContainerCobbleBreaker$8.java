package fr.paladium.palamod.modules.paladium.common.container;

import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

class null extends Slot {
  null(IInventory x0, int x1, int x2, int x3) {
    super(x0, x1, x2, x3);
  }
  
  public boolean func_75214_a(@Nullable ItemStack stack) {
    if (JobsBridge.canUseCraft(player, stack) && 
      stack.func_77973_b() instanceof fr.paladium.palamod.modules.miner.item.ItemCobbleBreakerUpgrade)
      return true; 
    return false;
  }
  
  public int func_75219_a() {
    return 1;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\ContainerCobbleBreaker$8.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */