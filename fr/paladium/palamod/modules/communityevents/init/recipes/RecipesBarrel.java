package fr.paladium.palamod.modules.communityevents.init.recipes;

import fr.paladium.palamod.modules.world.PWorld;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipesBarrel implements IRecipe {
  private Block item;
  
  public RecipesBarrel(Block item) {
    this.item = item;
  }
  
  public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
    int validator = 0;
    for (int i = 0; i < p_77569_1_.func_70302_i_(); i++) {
      ItemStack itemstack1 = p_77569_1_.func_70301_a(i);
      if (i != 3 && i != 5) {
        if (itemstack1 != null && (itemstack1.func_77969_a(new ItemStack(PWorld.PLANKS_ERABLE)) || itemstack1
          .func_77969_a(new ItemStack(PWorld.PLANKS_JACARANDA)) || itemstack1
          .func_77969_a(new ItemStack(PWorld.PLANKS_JUDEECERCIS)) || itemstack1
          .func_77969_a(new ItemStack(PWorld.PLANKS_OSTRYA))))
          validator++; 
      } else if (itemstack1 != null) {
        validator--;
      } 
    } 
    return (validator == 7);
  }
  
  public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
    return new ItemStack(this.item, 4);
  }
  
  public int func_77570_a() {
    return 7;
  }
  
  public ItemStack func_77571_b() {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\init\recipes\RecipesBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */