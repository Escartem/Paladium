package fr.paladium.palarpg.module.entity.client.loader.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import fr.paladium.palarpg.module.entity.client.loader.RPGEntityModel;
import fr.paladium.palarpg.module.entity.client.loader.cache.RPGEntityModelCache;
import java.lang.reflect.Type;

public class RPGEntityModelGsonTypeAdapter implements JsonDeserializer<RPGEntityModel> {
  public RPGEntityModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    if (json.isJsonPrimitive()) {
      String modelId = json.getAsString();
      return RPGEntityModelCache.get(modelId);
    } 
    JsonObject jsonObject = json.getAsJsonObject();
    RPGEntityModel model = new RPGEntityModel();
    model.setModel(jsonObject.get("model").getAsString());
    model.setAnimation(jsonObject.get("animation").getAsString());
    model.setTexture(jsonObject.get("texture").getAsString());
    return model;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\client\loader\adapter\RPGEntityModelGsonTypeAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */