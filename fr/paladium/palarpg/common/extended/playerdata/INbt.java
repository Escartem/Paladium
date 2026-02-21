package fr.paladium.palarpg.common.extended.playerdata;

import net.minecraft.nbt.NBTTagCompound;

public interface INbt {
  void read(NBTTagCompound paramNBTTagCompound, boolean paramBoolean);
  
  void write(NBTTagCompound paramNBTTagCompound, boolean paramBoolean);
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\common\extended\playerdata\INbt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */