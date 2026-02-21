package fr.paladium.palamod.modules.argus.packets;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetPacketInitiator {
  public static String a() {
    return DecodeStackTrace.a(CurrentThreadStack.a(CurrentThread.a()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\packets\GetPacketInitiator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */