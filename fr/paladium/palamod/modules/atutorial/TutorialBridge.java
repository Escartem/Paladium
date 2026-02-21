package fr.paladium.palamod.modules.atutorial;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.tutorial.common.event.TutorialCommandEvent;
import fr.paladium.tutorial.common.event.TutorialCraftEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class TutorialBridge {
  public static void callCraftEvent(EntityPlayer player, ItemStack stack) {
    MinecraftForge.EVENT_BUS.post((Event)new TutorialCraftEvent(player, stack));
  }
  
  public static void callCommandEvent(EntityPlayer player, String command) {
    MinecraftForge.EVENT_BUS.post((Event)new TutorialCommandEvent(player, command));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\atutorial\TutorialBridge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */