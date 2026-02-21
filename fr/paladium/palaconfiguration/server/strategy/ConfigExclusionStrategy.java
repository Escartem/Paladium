package fr.paladium.palaconfiguration.server.strategy;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class ConfigExclusionStrategy implements ExclusionStrategy {
  public boolean shouldSkipField(FieldAttributes field) {
    return (field.getAnnotation(PrintExclude.class) != null);
  }
  
  public boolean shouldSkipClass(Class<?> clazz) {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\strategy\ConfigExclusionStrategy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */