package fr.paladium.palarpg.module.dungeon.common.world.parser;

import com.eliotlash.molang.MolangParser;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;
import lombok.NonNull;

public class GsonMolangDungeonNumberAdapter implements JsonDeserializer<Number> {
  private final MolangParser parser;
  
  public GsonMolangDungeonNumberAdapter(MolangParser parser) {
    this.parser = parser;
  }
  
  public Number deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    if (json.isJsonPrimitive()) {
      JsonPrimitive primitive = json.getAsJsonPrimitive();
      if (primitive.isNumber())
        return parseNumber(primitive.getAsNumber(), typeOfT); 
      if (primitive.isString()) {
        String value = primitive.getAsString();
        try {
          return parseNumber(Double.valueOf(this.parser.parse(value).get()), typeOfT);
        } catch (Exception e) {
          throw new JsonParseException("Invalid Molang number: " + json + " for " + typeOfT, e);
        } 
      } 
    } 
    throw new JsonParseException("Invalid expression: " + json + " for " + typeOfT);
  }
  
  private Number parseNumber(@NonNull Number number, Type typeOfT) {
    if (number == null)
      throw new NullPointerException("number is marked non-null but is null"); 
    if (typeOfT == int.class || typeOfT == Integer.class)
      return Integer.valueOf(number.intValue()); 
    if (typeOfT == long.class || typeOfT == Long.class)
      return Long.valueOf(number.longValue()); 
    if (typeOfT == float.class || typeOfT == Float.class)
      return Float.valueOf(number.floatValue()); 
    if (typeOfT == double.class || typeOfT == Double.class)
      return Double.valueOf(number.doubleValue()); 
    if (typeOfT == byte.class || typeOfT == Byte.class)
      return Byte.valueOf(number.byteValue()); 
    if (typeOfT == short.class || typeOfT == Short.class)
      return Short.valueOf(number.shortValue()); 
    throw new JsonParseException("Invalid number type: " + typeOfT);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\parser\GsonMolangDungeonNumberAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */