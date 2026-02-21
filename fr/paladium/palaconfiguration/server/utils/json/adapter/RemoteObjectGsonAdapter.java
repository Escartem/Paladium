package fr.paladium.palaconfiguration.server.utils.json.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import fr.paladium.palaconfiguration.server.dto.file.RemoteDirectory;
import fr.paladium.palaconfiguration.server.dto.file.RemoteFile;
import fr.paladium.palaconfiguration.server.dto.file.RemoteObject;
import java.lang.reflect.Type;

public class RemoteObjectGsonAdapter implements JsonSerializer<RemoteObject>, JsonDeserializer<RemoteObject> {
  public RemoteObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    if (!json.isJsonObject())
      throw new JsonParseException("Invalid remote object json: " + json); 
    JsonObject object = json.getAsJsonObject();
    if (!object.has("directory"))
      throw new JsonParseException("Invalid remote object json: " + json); 
    boolean isDirectory = object.get("directory").getAsBoolean();
    return (RemoteObject)context.deserialize(json, isDirectory ? RemoteDirectory.class : RemoteFile.class);
  }
  
  public JsonElement serialize(RemoteObject src, Type typeOfSrc, JsonSerializationContext context) {
    return context.serialize(src, src.getClass());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\serve\\utils\json\adapter\RemoteObjectGsonAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */