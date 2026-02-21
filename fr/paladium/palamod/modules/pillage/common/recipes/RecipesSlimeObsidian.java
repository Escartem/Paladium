package fr.paladium.palamod.modules.pillage.common.recipes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipesSlimeObsidian implements IRecipe {
  private final Block block;
  
  public RecipesSlimeObsidian(Block block) {
    this.block = block;
  }
  
  public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
    int validator = 0;
    for (int i = 0; i < p_77569_1_.func_70302_i_(); i++) {
      ItemStack itemstack1 = p_77569_1_.func_70301_a(i);
      if (itemstack1 != null)
        if (i <= 2) {
          if (itemstack1.func_77973_b().equals(Item.func_150898_a(Blocks.field_150343_Z)))
            validator++; 
        } else if (i <= 5) {
          if (itemstack1.func_77977_a().contains("slimepad"))
            validator++; 
        } else if (i <= 8 && 
          itemstack1.func_77973_b().equals(Item.func_150898_a(Blocks.field_150343_Z))) {
          validator++;
        }  
    } 
    return (validator == 9);
  }
  
  public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
    return new ItemStack(this.block, 2);
  }
  
  public int func_77570_a() {
    return 9;
  }
  
  public ItemStack func_77571_b() {
    return new ItemStack(this.block, 2);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\recipes\RecipesSlimeObsidian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */