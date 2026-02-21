package fr.paladium.palamod.modules.argus.packets;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class CurrentThreadStack {
  public static StackTraceElement[] a(Thread t) {
    return t.getStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\packets\CurrentThreadStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */