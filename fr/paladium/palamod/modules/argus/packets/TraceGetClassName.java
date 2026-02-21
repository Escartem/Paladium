package fr.paladium.palamod.modules.argus.packets;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class TraceGetClassName {
  public static String a(StackTraceElement line) {
    return line.getClassName();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\packets\TraceGetClassName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */