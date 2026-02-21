package fr.paladium.palamod.modules.argus.packets;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class RebuildTrace {
  public static String a(StackTraceElement line) {
    return TraceGetClassName.a(line) + (new Object() {
        int t;
        
        public String toString() {
          byte[] buf = new byte[1];
          this.t = -1905072839;
          buf[0] = (byte)(this.t >>> 12);
          return new String(buf);
        }
      }).toString() + TraceGetMethodName.a(line);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\packets\RebuildTrace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */