package fr.paladium.palamod.modules.paladynamique.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftListener {
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onCraft(PlayerEvent.ItemCraftedEvent event) {
    if (event.player.field_70170_p.field_72995_K)
      return; 
    if (event.isCanceled() || event.crafting == null)
      return; 
    if (event.crafting.func_77973_b() == null)
      return; 
    if (event.crafting.func_77973_b() == ItemsRegister.PALADIUM_INGOT || event.crafting.func_77973_b() == Item.func_150898_a(BlocksRegister.BLOCK_PALADIUM) || event.crafting
      .func_77973_b() == ItemsRegister.PARTICLE_PALADIUM)
      return; 
    IInventory craftMatrix = event.craftMatrix;
    if (craftMatrix == null)
      return; 
    int removedPaladium = 0;
    for (int itr = 0; itr < craftMatrix.func_70302_i_(); itr++) {
      ItemStack stack = craftMatrix.func_70301_a(itr);
      if (stack != null)
        if (stack.func_77973_b() == ItemsRegister.PALADIUM_INGOT) {
          removedPaladium += stack.field_77994_a;
        } else if (stack.func_77973_b() == Item.func_150898_a(BlocksRegister.BLOCK_PALADIUM)) {
          removedPaladium += stack.field_77994_a * 9;
        } else if (stack.func_77973_b() == ItemsRegister.PARTICLE_PALADIUM) {
          removedPaladium = (int)(removedPaladium + stack.field_77994_a / 9.0D);
        }  
    } 
    if (removedPaladium > 0)
      PPalaDynamique.instance.addDestroyed("CRAFT", removedPaladium); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladynamique\listener\CraftListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */