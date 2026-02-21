package fr.paladium.palamod.modules.back2future.recipes;

import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.items.block.ItemBanner;
import fr.paladium.palamod.modules.back2future.lib.EnumColour;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityBanner;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class RecipeAddPattern implements IRecipe {
  public boolean func_77569_a(InventoryCrafting grid, World world) {
    boolean flag = false;
    for (int i = 0; i < grid.func_70302_i_(); i++) {
      ItemStack slot = grid.func_70301_a(i);
      if (slot != null && slot.func_77973_b() == Item.func_150898_a(ModBlocks.banner)) {
        if (flag)
          return false; 
        if (TileEntityBanner.getPatterns(slot) >= 6)
          return false; 
        flag = true;
      } 
    } 
    if (!flag)
      return false; 
    return (getPattern(grid) != null);
  }
  
  public ItemStack func_77572_b(InventoryCrafting grid) {
    ItemStack banner = null;
    for (int i = 0; i < grid.func_70302_i_(); i++) {
      ItemStack slot = grid.func_70301_a(i);
      if (slot != null && slot.func_77973_b() == Item.func_150898_a(ModBlocks.banner)) {
        banner = slot.func_77946_l();
        banner.field_77994_a = 1;
        break;
      } 
    } 
    TileEntityBanner.EnumBannerPattern pattern = getPattern(grid);
    if (pattern != null) {
      NBTTagList nbttaglist;
      int dyeMeta = 15;
      for (int j = 0; j < grid.func_70302_i_(); j++) {
        ItemStack slot = grid.func_70301_a(j);
        if (slot != null && isDye(slot)) {
          dyeMeta = getDyeIndex(slot);
          break;
        } 
      } 
      NBTTagCompound nbt = ItemBanner.getSubTag(banner, "BlockEntityTag", true);
      if (nbt.func_150297_b("Patterns", 9)) {
        nbttaglist = nbt.func_150295_c("Patterns", 10);
      } else {
        nbttaglist = new NBTTagList();
        nbt.func_74782_a("Patterns", (NBTBase)nbttaglist);
      } 
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      nbttagcompound.func_74778_a("Pattern", pattern.getPatternID());
      nbttagcompound.func_74768_a("Color", dyeMeta);
      nbttaglist.func_74742_a((NBTBase)nbttagcompound);
    } 
    return banner;
  }
  
  public int func_77570_a() {
    return 10;
  }
  
  public ItemStack func_77571_b() {
    return null;
  }
  
  private boolean isDye(ItemStack stack) {
    for (String ore : Utils.getOreNames(stack)) {
      for (EnumColour colour : EnumColour.values()) {
        if (ore.equals(colour.getOreName()))
          return true; 
      } 
    } 
    return false;
  }
  
  private int getDyeIndex(ItemStack stack) {
    for (String ore : Utils.getOreNames(stack)) {
      for (EnumColour colour : EnumColour.values()) {
        if (ore.equals(colour.getOreName()))
          return colour.getDamage(); 
      } 
    } 
    return -1;
  }
  
  private TileEntityBanner.EnumBannerPattern getPattern(InventoryCrafting grid) {
    for (TileEntityBanner.EnumBannerPattern pattern : TileEntityBanner.EnumBannerPattern.values()) {
      if (pattern.hasValidCrafting()) {
        boolean flag = true;
        if (pattern.hasCraftingStack()) {
          boolean flag1 = false;
          boolean flag2 = false;
          for (int i = 0; i < grid.func_70302_i_() && flag; i++) {
            ItemStack slot = grid.func_70301_a(i);
            if (slot != null && slot.func_77973_b() != Item.func_150898_a(ModBlocks.banner))
              if (isDye(slot)) {
                if (flag2) {
                  flag = false;
                  break;
                } 
                flag2 = true;
              } else {
                if (flag1 || !slot.func_77969_a(pattern.getCraftingStack())) {
                  flag = false;
                  break;
                } 
                flag1 = true;
              }  
          } 
          if (!flag1)
            flag = false; 
        } else if (grid.func_70302_i_() != (pattern.getCraftingLayers()).length * pattern
          .getCraftingLayers()[0].length()) {
          flag = false;
        } else {
          int k = -1;
          for (int l = 0; l < grid.func_70302_i_() && flag; l++) {
            int i = l / 3;
            int j1 = l % 3;
            ItemStack slot = grid.func_70301_a(l);
            if (slot != null && slot.func_77973_b() != Item.func_150898_a(ModBlocks.banner)) {
              if (!isDye(slot)) {
                flag = false;
                break;
              } 
              if (k != -1 && k != getDyeIndex(slot)) {
                flag = false;
                break;
              } 
              if (pattern.getCraftingLayers()[i].charAt(j1) == ' ') {
                flag = false;
                break;
              } 
              k = getDyeIndex(slot);
            } else if (pattern.getCraftingLayers()[i].charAt(j1) != ' ') {
              flag = false;
              break;
            } 
          } 
        } 
        if (flag)
          return pattern; 
      } 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\recipes\RecipeAddPattern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */