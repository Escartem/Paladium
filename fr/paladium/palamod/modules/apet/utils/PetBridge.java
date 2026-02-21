package fr.paladium.palamod.modules.apet.utils;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.pet.common.event.PlayerEnchantItemEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class PetBridge {
  public static void callEnchantItemEvent(EntityPlayer player, ItemStack stack, int cost) {
    PlayerEnchantItemEvent event = new PlayerEnchantItemEvent(player, stack, cost);
    MinecraftForge.EVENT_BUS.post((Event)event);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ape\\utils\PetBridge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */