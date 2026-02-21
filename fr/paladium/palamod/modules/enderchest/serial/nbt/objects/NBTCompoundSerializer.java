package fr.paladium.palamod.modules.enderchest.serial.nbt.objects;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.paladium.palamod.modules.enderchest.serial.nbt.ANBTSerializer;
import fr.paladium.palamod.modules.enderchest.serial.nbt.utils.NBTSerializer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class NBTCompoundSerializer extends ANBTSerializer<NBTTagCompound> {
  public String identifier() {
    return null;
  }
  
  public NBTTagCompound unSerialize(JsonElement json) {
    return null;
  }
  
  public JsonElement serialize(NBTTagCompound nbt) {
    JsonObject jsonObject = new JsonObject();
    for (Object key : nbt.func_150296_c()) {
      String str = (String)key;
      if (nbt.func_74781_a(str) != null)
        jsonObject.add(str, NBTSerializer.toJson(nbt.func_74781_a(str))); 
    } 
    return (JsonElement)jsonObject;
  }
  
  public Class<NBTTagCompound> instance() {
    return NBTTagCompound.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\serial\nbt\objects\NBTCompoundSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */