package fr.paladium.palaspawner.common.utils;

import net.minecraft.inventory.IInventory;

public class ContainerUtils {
  public static boolean isInventoryFull(IInventory inventory) {
    for (int i = 0; i < inventory.func_70302_i_(); i++) {
      if (inventory.func_70301_a(i) == null)
        return false; 
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\commo\\utils\ContainerUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */