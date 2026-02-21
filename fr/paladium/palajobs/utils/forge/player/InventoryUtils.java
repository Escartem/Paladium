package fr.paladium.palajobs.utils.forge.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class InventoryUtils {
  public static boolean isInventoryFull(EntityPlayer player) {
    return (player.field_71071_by.func_70447_i() == -1);
  }
  
  public static boolean isInventoryEmpty(EntityPlayer player) {
    for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
      if (player.field_71071_by.func_70301_a(i) != null)
        return false; 
    } 
    return true;
  }
  
  public static boolean hasItem(EntityPlayer player, ItemStack itemStack) {
    for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
      ItemStack item = player.field_71071_by.func_70301_a(i);
      if (item != null && item.func_77969_a(itemStack))
        return true; 
    } 
    return false;
  }
  
  public ItemStack getItemInHand(EntityPlayer player) {
    return player.field_71071_by.func_70448_g();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajob\\utils\forge\player\InventoryUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */