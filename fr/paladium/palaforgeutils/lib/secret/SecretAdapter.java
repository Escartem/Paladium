package fr.paladium.palaforgeutils.lib.secret;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class SecretAdapter implements JsonSerializer<Secret<?>>, JsonDeserializer<Secret<?>> {
  public Secret<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    Class<?> clazz = TypeToken.get(typeOfT).getRawType();
    try {
      Secret<?> secret = (Secret)clazz.newInstance();
      secret.unSerialize(json);
      return secret;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  public JsonElement serialize(Secret<?> secret, Type type, JsonSerializationContext context) {
    return secret.serialize();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\secret\SecretAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */