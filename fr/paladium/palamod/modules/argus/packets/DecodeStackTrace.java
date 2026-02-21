package fr.paladium.palamod.modules.argus.packets;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class DecodeStackTrace {
  public static String a(StackTraceElement[] trace) {
    boolean x = false;
    for (StackTraceElement element : trace) {
      String a = RebuildTrace.a(element);
      if (x)
        return a; 
      for (String b : GetStackTriggers.a()) {
        if (a.equals(b)) {
          x = true;
          break;
        } 
      } 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\packets\DecodeStackTrace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */