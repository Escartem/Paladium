package fr.paladium.palaforgeutils.lib.secret.object;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import fr.paladium.palaforgeutils.lib.secret.Secret;

public class SecretDouble extends Secret<Double> {
  public SecretDouble() {}
  
  public SecretDouble(double value) {
    super(Double.valueOf(value));
  }
  
  public JsonElement serialize() {
    return (JsonElement)new JsonPrimitive((Number)getSecret());
  }
  
  public void unSerialize(JsonElement value) {
    setSecret(Double.valueOf(value.getAsDouble()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\secret\object\SecretDouble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */