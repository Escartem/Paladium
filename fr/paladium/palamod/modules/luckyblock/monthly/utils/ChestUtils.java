package fr.paladium.palamod.modules.luckyblock.monthly.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class ChestUtils {
  public static boolean fillChest(World world, int x, int y, int z, ItemStack... items) {
    return fillChest(world, x, y, z, true, items);
  }
  
  public static boolean fillChest(TileEntityChest chest, ItemStack... items) {
    return fillChest(chest, true, items);
  }
  
  public static boolean fillChest(World world, int x, int y, int z, boolean clear, ItemStack... items) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntityChest))
      return false; 
    return fillChest((TileEntityChest)tileEntity, clear, items);
  }
  
  public static boolean fillChest(TileEntityChest chest, boolean clear, ItemStack... items) {
    if (clear)
      for (int j = 0; j < chest.func_70302_i_(); j++)
        chest.func_70299_a(j, null);  
    for (int i = 0; i < items.length; i++) {
      ItemStack item = items[i];
      if (item != null)
        chest.func_70299_a(i, item.func_77946_l()); 
    } 
    chest.func_70296_d();
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthl\\utils\ChestUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */