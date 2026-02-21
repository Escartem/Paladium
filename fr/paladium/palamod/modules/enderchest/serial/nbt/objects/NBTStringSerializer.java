package fr.paladium.palamod.modules.enderchest.serial.nbt.objects;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import fr.paladium.palamod.modules.enderchest.serial.nbt.ANBTSerializer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;

public class NBTStringSerializer extends ANBTSerializer<NBTTagString> {
  public String identifier() {
    return "S";
  }
  
  public NBTTagString unSerialize(JsonElement json) {
    return new NBTTagString(json.getAsString());
  }
  
  public JsonElement serialize(NBTTagString nbt) {
    return (JsonElement)new JsonPrimitive(nbt.func_150285_a_());
  }
  
  public Class<NBTTagString> instance() {
    return NBTTagString.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\serial\nbt\objects\NBTStringSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */