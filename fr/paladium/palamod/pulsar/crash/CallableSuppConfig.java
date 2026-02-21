package fr.paladium.palamod.pulsar.crash;

import cpw.mods.fml.common.ICrashCallable;
import org.reflections.scanners.ResourcesScanner;

public class CallableSuppConfig implements ICrashCallable {
  public static ResourcesScanner scanner = new ResourcesScanner();
  
  private String modId;
  
  public CallableSuppConfig(String modId) {
    this.modId = modId;
  }
  
  public String call() throws Exception {
    return "Environment healthy.";
  }
  
  public String getLabel() {
    return this.modId + " Environment";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\pulsar\crash\CallableSuppConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */