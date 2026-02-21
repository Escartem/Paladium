package fr.paladium.palamod.modules.enderchest.serial.nbt;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagIntArray;

public class ANBTArrayParser<T extends NBTBase> implements INBTSerializer<T> {
  public JsonElement serialize(T nbt) {
    JsonArray jsonArray = new JsonArray();
    if (nbt instanceof NBTTagIntArray) {
      for (int i : ((NBTTagIntArray)nbt).func_150302_c())
        jsonArray.add((JsonElement)new JsonPrimitive(arrayIdenfitifer() + ":" + i)); 
    } else if (nbt instanceof NBTTagByteArray) {
      for (byte i : ((NBTTagByteArray)nbt).func_150292_c())
        jsonArray.add((JsonElement)new JsonPrimitive(arrayIdenfitifer() + ":" + i)); 
    } 
    return (JsonElement)jsonArray;
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
  
  public String arrayIdenfitifer() {
    return "A:";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\serial\nbt\ANBTArrayParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */