package fr.paladium.palashop.server.shop.dto.page.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import fr.paladium.palashop.server.shop.dto.page.ShopPageData;
import java.lang.reflect.Type;

public class ShopPageDataGsonAdapter implements JsonSerializer<ShopPageData>, JsonDeserializer<ShopPageData> {
  public ShopPageData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject object = json.getAsJsonObject();
    String className = object.get("class").getAsString();
    JsonElement element = object.get("data");
    try {
      return (ShopPageData)context.deserialize(element, Class.forName(className, false, Thread.currentThread().getContextClassLoader()));
    } catch (ClassNotFoundException e) {
      throw new JsonParseException(e);
    } 
  }
  
  public JsonElement serialize(ShopPageData src, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject object = new JsonObject();
    object.addProperty("class", src.getClass().getName());
    object.add("data", context.serialize(src, src.getClass()));
    return (JsonElement)object;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\dto\page\gson\ShopPageDataGsonAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */