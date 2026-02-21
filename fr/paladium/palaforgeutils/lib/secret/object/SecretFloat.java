package fr.paladium.palaforgeutils.lib.secret.object;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import fr.paladium.palaforgeutils.lib.secret.Secret;

public class SecretFloat extends Secret<Float> {
  public SecretFloat() {}
  
  public SecretFloat(float value) {
    super(Float.valueOf(value));
  }
  
  public JsonElement serialize() {
    return (JsonElement)new JsonPrimitive((Number)getSecret());
  }
  
  public void unSerialize(JsonElement value) {
    setSecret(Float.valueOf(value.getAsFloat()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\secret\object\SecretFloat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */