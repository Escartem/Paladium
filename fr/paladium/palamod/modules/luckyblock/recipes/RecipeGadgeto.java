package fr.paladium.palamod.modules.luckyblock.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class RecipeGadgeto implements IRecipe {
  public boolean func_77569_a(InventoryCrafting crafting, World world) {
    boolean gadgeto = false;
    boolean tool = false;
    for (int i = 0; i <= crafting.func_70302_i_(); i++) {
      ItemStack stack = crafting.func_70301_a(i);
      if (stack != null && stack.func_77973_b() != null)
        if (stack.func_77973_b() instanceof fr.paladium.palamod.modules.luckyblock.items.black.ItemGadgeto) {
          if (gadgeto)
            return false; 
          gadgeto = true;
        } else if (stack.func_77973_b() instanceof net.minecraft.item.ItemPickaxe || stack.func_77973_b() instanceof net.minecraft.item.ItemSpade || stack
          .func_77973_b() instanceof net.minecraft.item.ItemHoe || stack.func_77973_b() instanceof net.minecraft.item.ItemAxe) {
          if (tool)
            return false; 
          tool = true;
        } else {
          return false;
        }  
    } 
    return (gadgeto && tool);
  }
  
  public ItemStack func_77572_b(InventoryCrafting crafting) {
    ItemStack tool = null;
    for (int i = 0; i <= crafting.func_70302_i_(); i++) {
      ItemStack stack = crafting.func_70301_a(i);
      if (stack != null && stack.func_77973_b() != null && (
        stack.func_77973_b() instanceof net.minecraft.item.ItemPickaxe || stack.func_77973_b() instanceof net.minecraft.item.ItemSpade || stack
        .func_77973_b() instanceof net.minecraft.item.ItemHoe || stack.func_77973_b() instanceof net.minecraft.item.ItemAxe))
        tool = stack.func_77946_l(); 
    } 
    if (tool != null) {
      if (!tool.func_77942_o())
        tool.field_77990_d = new NBTTagCompound(); 
      tool.field_77990_d.func_74757_a("gadgeto", true);
      return tool;
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\recipes\RecipeGadgeto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */