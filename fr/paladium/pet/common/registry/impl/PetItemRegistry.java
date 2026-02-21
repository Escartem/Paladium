package fr.paladium.pet.common.registry.impl;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.pet.common.item.ItemBait;
import fr.paladium.pet.common.registry.IRegistry;
import net.minecraft.item.Item;

public class PetItemRegistry implements IRegistry {
  public static ItemBait BAIT;
  
  public void onPreInit(FMLPreInitializationEvent event) {
    RegistryUtils.item(new Item[] { (Item)(BAIT = new ItemBait()) });
  }
  
  public void onInit(FMLInitializationEvent event) {}
  
  public void onPostInit(FMLPostInitializationEvent event) {}
  
  public void onServerStarting(FMLServerStartingEvent event) {}
  
  public void onServerStarted(FMLServerStartedEvent event) {}
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\registry\impl\PetItemRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */