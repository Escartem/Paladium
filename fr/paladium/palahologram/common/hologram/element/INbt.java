package fr.paladium.palahologram.common.hologram.element;

import net.minecraft.nbt.NBTTagCompound;

public interface INbt<T> {
  T read(NBTTagCompound paramNBTTagCompound);
  
  void write(NBTTagCompound paramNBTTagCompound);
}


/* Location:              E:\Paladium\!\fr\paladium\palahologram\common\hologram\element\INbt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */