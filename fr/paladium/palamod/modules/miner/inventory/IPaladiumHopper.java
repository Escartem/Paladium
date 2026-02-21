package fr.paladium.palamod.modules.miner.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;

public interface IPaladiumHopper extends IInventory {
  World getWorld();
  
  double getXPos();
  
  double getYPos();
  
  double getZPos();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\inventory\IPaladiumHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */