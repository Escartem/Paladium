package fr.paladium.pet.common.utils;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class ItemUtils {
  public static ItemStack decrementCurrentItem(EntityPlayer player, ItemStack stack) {
    stack.field_77994_a--;
    if (stack.field_77994_a <= 0) {
      player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c] = null;
      MinecraftForge.EVENT_BUS.post((Event)new PlayerDestroyItemEvent(player, stack));
      return null;
    } 
    return stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\commo\\utils\ItemUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */