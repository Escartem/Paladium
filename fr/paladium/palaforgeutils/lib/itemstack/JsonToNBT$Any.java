package fr.paladium.palaforgeutils.lib.itemstack;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;

abstract class Any {
  protected String json;
  
  private Any() {}
  
  public abstract NBTBase parse() throws NBTException;
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\itemstack\JsonToNBT$Any.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */