package fr.paladium.palamod.modules.enderchest.serial.nbt.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fr.paladium.palamod.modules.enderchest.serial.nbt.ANBTArrayParser;
import fr.paladium.palamod.modules.enderchest.serial.nbt.INBTSerializer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;

public class NBTSerializer {
  private static List<INBTSerializer<?>> parsers = new ArrayList<>();
  
  public static void registerParser(INBTSerializer<?> parser) {
    parsers.add(parser);
  }
  
  public static JsonElement toJson(NBTBase nbtElement) {
    if (nbtElement == null)
      return null; 
    for (INBTSerializer<?> parser : parsers) {
      if (parser.instance().isAssignableFrom(nbtElement.getClass())) {
        JsonElement writed = parser.writeNBT(nbtElement);
        if (writed instanceof JsonPrimitive && parser.identifier() != null)
          return (JsonElement)new JsonPrimitive(parser.identifier() + ":" + writed.getAsJsonPrimitive().getAsString()); 
        return writed;
      } 
    } 
    return null;
  }
  
  public static NBTBase toNbt(JsonElement jsonElement) {
    if (jsonElement == null || jsonElement instanceof com.google.gson.JsonNull)
      return null; 
    if (jsonElement instanceof JsonPrimitive) {
      JsonPrimitive primitive = (JsonPrimitive)jsonElement;
      if (primitive.isString()) {
        String str = primitive.getAsString();
        for (INBTSerializer<?> parser : parsers) {
          if (parser.identifier() != null) {
            if (str.startsWith(parser.identifier() + ":"))
              return parser.unSerialize((JsonElement)new JsonPrimitive(str.replaceFirst(parser.identifier() + ":", ""))); 
            continue;
          } 
          return parser.unSerialize(jsonElement);
        } 
      } else {
        if (primitive.isBoolean()) {
          boolean value = primitive.getAsBoolean();
          if (value)
            return (NBTBase)new NBTTagByte((byte)1); 
          return (NBTBase)new NBTTagByte((byte)0);
        } 
        if (primitive.isNumber()) {
          if (primitive.getAsNumber() instanceof Integer)
            return (NBTBase)new NBTTagInt(primitive.getAsInt()); 
          if (primitive.getAsNumber() instanceof Byte)
            return (NBTBase)new NBTTagByte(primitive.getAsByte()); 
          if (primitive.getAsNumber() instanceof Long)
            return (NBTBase)new NBTTagLong(primitive.getAsLong()); 
          if (primitive.getAsNumber() instanceof Float)
            return (NBTBase)new NBTTagFloat(primitive.getAsFloat()); 
          if (primitive.getAsNumber() instanceof Double)
            return (NBTBase)new NBTTagDouble(primitive.getAsDouble()); 
          if (primitive.getAsNumber() instanceof Short)
            return (NBTBase)new NBTTagShort(primitive.getAsShort()); 
        } 
      } 
    } else {
      if (jsonElement instanceof JsonObject) {
        JsonObject jsonObject = (JsonObject)jsonElement;
        NBTTagCompound nbtCompound = new NBTTagCompound();
        for (Map.Entry<String, JsonElement> jsonEntry : (Iterable<Map.Entry<String, JsonElement>>)jsonObject.entrySet())
          nbtCompound.func_74782_a(jsonEntry.getKey(), toNbt(jsonEntry.getValue())); 
        return (NBTBase)nbtCompound;
      } 
      if (jsonElement instanceof JsonArray) {
        JsonArray jsonArray = (JsonArray)jsonElement;
        if (jsonArray.size() > 0) {
          if (!jsonArray.get(0).isJsonObject() && jsonArray.get(0).isJsonPrimitive())
            for (INBTSerializer<?> parser : parsers) {
              ANBTArrayParser<?> arrayParser = (ANBTArrayParser)parser;
              if (parser instanceof ANBTArrayParser && jsonArray.get(0).getAsString().startsWith(arrayParser.arrayIdenfitifer() + ":"))
                return arrayParser.unSerialize(jsonElement); 
            }  
          NBTTagList nbtList = new NBTTagList();
          for (JsonElement element : jsonArray) {
            if (element == null)
              continue; 
            NBTBase base = toNbt(element);
            if (base == null)
              continue; 
            nbtList.func_74742_a(base);
          } 
          return (NBTBase)nbtList;
        } 
        return (NBTBase)new NBTTagList();
      } 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\serial\nb\\utils\NBTSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */