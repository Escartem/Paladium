package fr.paladium.palamod.modules.paladium.common.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class PalaRecipe implements IRecipe {
  public final int recipeWidth;
  
  public final int recipeHeight;
  
  public final ItemStack[] recipeItems;
  
  private ItemStack recipeOutput;
  
  private boolean field_92101_f;
  
  private static final String __OBFID = "CL_00000093";
  
  public PalaRecipe(int p_i1917_1_, int p_i1917_2_, ItemStack[] p_i1917_3_, ItemStack p_i1917_4_) {
    this.recipeWidth = p_i1917_1_;
    this.recipeHeight = p_i1917_2_;
    this.recipeItems = p_i1917_3_;
    this.recipeOutput = p_i1917_4_;
  }
  
  public ItemStack func_77571_b() {
    return this.recipeOutput;
  }
  
  public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
    for (int i = 0; i <= 3 - this.recipeWidth; i++) {
      for (int j = 0; j <= 3 - this.recipeHeight; j++) {
        if (checkMatch(p_77569_1_, i, j, true))
          return true; 
        if (checkMatch(p_77569_1_, i, j, false))
          return true; 
      } 
    } 
    return false;
  }
  
  private boolean checkMatch(InventoryCrafting p_77573_1_, int p_77573_2_, int p_77573_3_, boolean p_77573_4_) {
    for (int k = 0; k < 3; k++) {
      for (int l = 0; l < 3; l++) {
        int i1 = k - p_77573_2_;
        int j1 = l - p_77573_3_;
        ItemStack itemstack = null;
        if (i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth && j1 < this.recipeHeight)
          if (p_77573_4_) {
            itemstack = this.recipeItems[this.recipeWidth - i1 - 1 + j1 * this.recipeWidth];
          } else {
            itemstack = this.recipeItems[i1 + j1 * this.recipeWidth];
          }  
        ItemStack itemstack1 = p_77573_1_.func_70463_b(k, l);
        if (itemstack1 != null || itemstack != null) {
          if ((itemstack1 == null && itemstack != null) || (itemstack1 != null && itemstack == null))
            return false; 
          if (itemstack.func_77973_b() != itemstack1.func_77973_b())
            return false; 
          if (itemstack.func_77960_j() != 32767 && itemstack
            .func_77960_j() != itemstack1.func_77960_j())
            return false; 
          if (itemstack.field_77994_a > itemstack1.field_77994_a)
            return false; 
        } 
      } 
    } 
    return true;
  }
  
  public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
    ItemStack itemstack = func_77571_b().func_77946_l();
    if (this.field_92101_f)
      for (int i = 0; i < p_77572_1_.func_70302_i_(); i++) {
        ItemStack itemstack1 = p_77572_1_.func_70301_a(i);
        if (itemstack1 != null && itemstack1.func_77942_o())
          itemstack.func_77982_d((NBTTagCompound)itemstack1.field_77990_d.func_74737_b()); 
      }  
    return itemstack;
  }
  
  public int func_77570_a() {
    return this.recipeWidth * this.recipeHeight;
  }
  
  public PalaRecipe func_92100_c() {
    this.field_92101_f = true;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\crafting\PalaRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */