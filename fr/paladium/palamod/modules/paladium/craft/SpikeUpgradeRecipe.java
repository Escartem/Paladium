package fr.paladium.palamod.modules.paladium.craft;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class SpikeUpgradeRecipe implements IRecipe {
  private final ItemStack resultItem;
  
  private final ItemStack upgradeItem;
  
  public SpikeUpgradeRecipe(ItemStack resultItem, ItemStack upgradeItem) {
    this.resultItem = resultItem;
    this.upgradeItem = upgradeItem;
  }
  
  public boolean func_77569_a(InventoryCrafting crafting, World world) {
    for (int i = 0; i < 9; i++) {
      ItemStack item = crafting.func_70301_a(i);
      if (item == null)
        return false; 
      if (i == 4) {
        if (!(Block.func_149634_a(item.func_77973_b()) instanceof fr.paladium.palamod.modules.paladium.common.blocks.BlockSpike))
          return false; 
      } else if (!item.func_77969_a(this.upgradeItem)) {
        return false;
      } 
    } 
    return true;
  }
  
  public int func_77570_a() {
    return 9;
  }
  
  public ItemStack func_77572_b(InventoryCrafting crafting) {
    return func_77571_b().func_77946_l();
  }
  
  public ItemStack func_77571_b() {
    return this.resultItem;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\craft\SpikeUpgradeRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */