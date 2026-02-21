package fr.paladium.pet.common.network.data.additional;

import net.minecraft.nbt.NBTTagCompound;

public interface INbtData {
  void read(NBTTagCompound paramNBTTagCompound);
  
  void write(NBTTagCompound paramNBTTagCompound);
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\data\additional\INbtData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */