package fr.paladium.palamod.modules.enderchest.serial.nbt;

import com.google.gson.JsonElement;
import net.minecraft.nbt.NBTBase;

public class ANBTSerializer<T extends NBTBase> implements INBTSerializer<T> {
  public JsonElement serialize(T nbt) {
    return null;
  }
  
  public String identifier() {
    return null;
  }
  
  public T unSerialize(JsonElement json) {
    return null;
  }
  
  public Class<? extends NBTBase> instance() {
    return null;
  }
  
  public JsonElement writeNBT(NBTBase nbt) {
    return serialize((T)nbt);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\serial\nbt\ANBTSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */