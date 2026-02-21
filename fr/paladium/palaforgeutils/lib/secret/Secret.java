package fr.paladium.palaforgeutils.lib.secret;

import com.google.gson.JsonElement;

public abstract class Secret<T> {
  private T secret;
  
  public void setSecret(T secret) {
    this.secret = secret;
  }
  
  public Secret() {}
  
  public T getSecret() {
    return this.secret;
  }
  
  public Secret(T value) {
    this.secret = value;
  }
  
  public String toString() {
    return "Secret{value=****,type=" + ((this.secret == null) ? "null" : this.secret.getClass().getSimpleName()) + "}";
  }
  
  public abstract JsonElement serialize();
  
  public abstract void unSerialize(JsonElement paramJsonElement);
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\secret\Secret.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */