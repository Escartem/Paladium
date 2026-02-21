package fr.paladium.palaforgeutils.lib.secret.object;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import fr.paladium.palaforgeutils.lib.secret.Secret;

public class SecretLong extends Secret<Long> {
  public SecretLong() {}
  
  public SecretLong(long value) {
    super(Long.valueOf(value));
  }
  
  public JsonElement serialize() {
    return (JsonElement)new JsonPrimitive((Number)getSecret());
  }
  
  public void unSerialize(JsonElement value) {
    setSecret(Long.valueOf(value.getAsLong()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\secret\object\SecretLong.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */