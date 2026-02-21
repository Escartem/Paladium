package fr.paladium.palamod.pulsar.crash;

import cpw.mods.fml.common.ICrashCallable;
import java.util.List;

public class CallableUnsuppConfig implements ICrashCallable {
  private String modId;
  
  private List<String> modIds;
  
  public CallableUnsuppConfig(String modId, List<String> modIds) {
    this.modId = modId;
    this.modIds = modIds;
  }
  
  public String getLabel() {
    return this.modId + " Environment";
  }
  
  public String call() throws Exception {
    String str = "Unsupported mods in environment: ";
    Boolean firstEntry = Boolean.valueOf(true);
    for (String id : this.modIds) {
      str = str + (firstEntry.booleanValue() ? id : (", " + id));
      firstEntry = Boolean.valueOf(false);
    } 
    return str;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\pulsar\crash\CallableUnsuppConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */