package fr.paladium.palaforgeutils.lib.secret.object;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import fr.paladium.palaforgeutils.lib.secret.Secret;

public class SecretBoolean extends Secret<Boolean> {
  public SecretBoolean() {}
  
  public SecretBoolean(boolean value) {
    super(Boolean.valueOf(value));
  }
  
  public JsonElement serialize() {
    return (JsonElement)new JsonPrimitive((Boolean)getSecret());
  }
  
  public void unSerialize(JsonElement value) {
    setSecret(Boolean.valueOf(value.getAsBoolean()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\secret\object\SecretBoolean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */