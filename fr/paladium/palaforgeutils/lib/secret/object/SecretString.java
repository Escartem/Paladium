package fr.paladium.palaforgeutils.lib.secret.object;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import fr.paladium.palaforgeutils.lib.secret.Secret;

public class SecretString extends Secret<String> {
  public SecretString() {}
  
  public SecretString(String value) {
    super(value);
  }
  
  public JsonElement serialize() {
    return (JsonElement)new JsonPrimitive((String)getSecret());
  }
  
  public void unSerialize(JsonElement value) {
    setSecret(value.getAsString());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\secret\object\SecretString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */