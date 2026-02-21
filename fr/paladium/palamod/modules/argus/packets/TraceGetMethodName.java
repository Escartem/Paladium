package fr.paladium.palamod.modules.argus.packets;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class TraceGetMethodName {
  public static String a(StackTraceElement line) {
    return line.getMethodName();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\packets\TraceGetMethodName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */