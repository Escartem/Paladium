package fr.paladium.palaforgeutils.lib.secret.object;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import fr.paladium.palaforgeutils.lib.secret.Secret;

public class SecretInteger extends Secret<Integer> {
  public SecretInteger() {}
  
  public SecretInteger(int value) {
    super(Integer.valueOf(value));
  }
  
  public JsonElement serialize() {
    return (JsonElement)new JsonPrimitive((Number)getSecret());
  }
  
  public void unSerialize(JsonElement value) {
    setSecret(Integer.valueOf(value.getAsInt()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\secret\object\SecretInteger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */