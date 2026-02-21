package fr.paladium.palamod.modules.enderchest.serial.nbt.objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import fr.paladium.palamod.modules.enderchest.serial.nbt.ANBTArrayParser;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByteArray;

public class NBTByteArraySerializer extends ANBTArrayParser<NBTTagByteArray> {
  public String identifier() {
    return null;
  }
  
  public NBTTagByteArray unSerialize(JsonElement json) {
    JsonArray jsonArray = (JsonArray)json;
    byte[] array = new byte[jsonArray.size()];
    int i = 0;
    for (JsonElement element : jsonArray) {
      array[i] = Byte.valueOf(element.getAsString().replaceFirst(arrayIdenfitifer() + ":", "")).byteValue();
      i++;
    } 
    return new NBTTagByteArray(array);
  }
  
  public Class<NBTTagByteArray> instance() {
    return NBTTagByteArray.class;
  }
  
  public String arrayIdenfitifer() {
    return "BA";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\serial\nbt\objects\NBTByteArraySerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */