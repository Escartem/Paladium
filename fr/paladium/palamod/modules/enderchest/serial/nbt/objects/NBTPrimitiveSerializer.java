package fr.paladium.palamod.modules.enderchest.serial.nbt.objects;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import fr.paladium.palamod.modules.enderchest.serial.nbt.ANBTSerializer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;

public class NBTPrimitiveSerializer extends ANBTSerializer<NBTBase.NBTPrimitive> {
  public String identifier() {
    return null;
  }
  
  public NBTBase.NBTPrimitive unSerialize(JsonElement json) {
    String value = json.getAsString();
    if (value.startsWith("L:")) {
      String[] splitted = value.split("L:");
      long v = Long.parseLong(splitted[1]);
      return (NBTBase.NBTPrimitive)new NBTTagLong(v);
    } 
    if (value.startsWith("B:")) {
      String[] splitted = value.split("B:");
      byte v = Byte.parseByte(splitted[1]);
      return (NBTBase.NBTPrimitive)new NBTTagByte(v);
    } 
    if (value.startsWith("D:")) {
      String[] splitted = value.split("D:");
      double v = Double.parseDouble(splitted[1]);
      return (NBTBase.NBTPrimitive)new NBTTagDouble(v);
    } 
    if (value.startsWith("F:")) {
      String[] splitted = value.split("F:");
      float v = Float.parseFloat(splitted[1]);
      return (NBTBase.NBTPrimitive)new NBTTagFloat(v);
    } 
    if (value.startsWith("I:")) {
      String[] splitted = value.split("I:");
      int v = Integer.parseInt(splitted[1]);
      return (NBTBase.NBTPrimitive)new NBTTagInt(v);
    } 
    if (value.startsWith("s:")) {
      String[] splitted = value.split("s:");
      short v = Short.parseShort(splitted[1]);
      return (NBTBase.NBTPrimitive)new NBTTagShort(v);
    } 
    return null;
  }
  
  public JsonElement serialize(NBTBase.NBTPrimitive nbt) {
    if (nbt.func_74732_a() == 4)
      return (JsonElement)new JsonPrimitive("L:" + nbt.func_150291_c()); 
    if (nbt.func_74732_a() == 1)
      return (JsonElement)new JsonPrimitive("B:" + nbt.func_150290_f()); 
    if (nbt.func_74732_a() == 6)
      return (JsonElement)new JsonPrimitive("D:" + nbt.func_150286_g()); 
    if (nbt.func_74732_a() == 5)
      return (JsonElement)new JsonPrimitive("F:" + nbt.func_150288_h()); 
    if (nbt.func_74732_a() == 3)
      return (JsonElement)new JsonPrimitive("I:" + nbt.func_150287_d()); 
    if (nbt.func_74732_a() == 2)
      return (JsonElement)new JsonPrimitive("s:" + nbt.func_150289_e()); 
    return (JsonElement)new JsonPrimitive(nbt.toString());
  }
  
  public Class<NBTBase.NBTPrimitive> instance() {
    return NBTBase.NBTPrimitive.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\serial\nbt\objects\NBTPrimitiveSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */