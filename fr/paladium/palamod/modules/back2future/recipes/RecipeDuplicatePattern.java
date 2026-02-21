package fr.paladium.palamod.modules.back2future.recipes;

import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityBanner;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipeDuplicatePattern implements IRecipe {
  public boolean func_77569_a(InventoryCrafting grid, World world) {
    ItemStack itemstack = null;
    ItemStack itemstack1 = null;
    for (int i = 0; i < grid.func_70302_i_(); i++) {
      ItemStack slot = grid.func_70301_a(i);
      if (slot != null) {
        if (slot.func_77973_b() != Item.func_150898_a(ModBlocks.banner))
          return false; 
        if (itemstack != null && itemstack1 != null)
          return false; 
        int colour = TileEntityBanner.getBaseColor(slot);
        boolean flag = (TileEntityBanner.getPatterns(slot) > 0);
        if (itemstack != null) {
          if (flag)
            return false; 
          if (colour != TileEntityBanner.getBaseColor(itemstack))
            return false; 
          itemstack1 = slot;
        } else if (itemstack1 != null) {
          if (!flag)
            return false; 
          if (colour != TileEntityBanner.getBaseColor(itemstack1))
            return false; 
          itemstack = slot;
        } else if (flag) {
          itemstack = slot;
        } else {
          itemstack1 = slot;
        } 
      } 
    } 
    return (itemstack != null && itemstack1 != null);
  }
  
  public ItemStack func_77572_b(InventoryCrafting grid) {
    for (int i = 0; i < grid.func_70302_i_(); i++) {
      ItemStack slot = grid.func_70301_a(i);
      if (slot != null && TileEntityBanner.getPatterns(slot) > 0) {
        ItemStack copy = slot.func_77946_l();
        copy.field_77994_a = 2;
        return copy;
      } 
    } 
    return null;
  }
  
  public int func_77570_a() {
    return 2;
  }
  
  public ItemStack func_77571_b() {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\recipes\RecipeDuplicatePattern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */