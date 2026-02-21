package fr.paladium.palamod.modules.enderchest.serial.nbt.objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import fr.paladium.palamod.modules.enderchest.serial.nbt.ANBTArrayParser;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagIntArray;

public class NBTIntArraySerializer extends ANBTArrayParser<NBTTagIntArray> {
  public String identifier() {
    return null;
  }
  
  public NBTTagIntArray unSerialize(JsonElement json) {
    JsonArray jsonArray = (JsonArray)json;
    int[] array = new int[jsonArray.size()];
    int i = 0;
    for (JsonElement element : jsonArray) {
      array[i] = Integer.valueOf(element.getAsString().replaceFirst(arrayIdenfitifer() + ":", "")).intValue();
      i++;
    } 
    return new NBTTagIntArray(array);
  }
  
  public Class<NBTTagIntArray> instance() {
    return NBTTagIntArray.class;
  }
  
  public String arrayIdenfitifer() {
    return "IA";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\serial\nbt\objects\NBTIntArraySerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */