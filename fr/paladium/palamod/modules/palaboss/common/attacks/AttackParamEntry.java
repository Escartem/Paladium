package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonPrimitive;

public class AttackParamEntry {
  private String paramName;
  
  private Object value;
  
  private Object defaultValue;
  
  public AttackParamEntry(String paramName, Object defaultValue) {
    this.paramName = paramName;
    this.defaultValue = defaultValue;
  }
  
  public String getParamName() {
    return this.paramName;
  }
  
  public void setValue(Object value) {
    this.value = value;
  }
  
  public Object getValue() {
    return (this.value == null) ? this.defaultValue : this.value;
  }
  
  public Object getDefaultValue() {
    return this.defaultValue;
  }
  
  public JsonPrimitive getValueAsPrimitive() {
    return (JsonPrimitive)getValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\AttackParamEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */