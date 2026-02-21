package fr.paladium.palamod.modules.enderchest.serial.nbt;

import com.google.gson.JsonElement;
import net.minecraft.nbt.NBTBase;

public interface INBTSerializer<T extends NBTBase> {
  String identifier();
  
  T unSerialize(JsonElement paramJsonElement);
  
  JsonElement serialize(T paramT);
  
  JsonElement writeNBT(NBTBase paramNBTBase);
  
  Class<? extends NBTBase> instance();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\serial\nbt\INBTSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */