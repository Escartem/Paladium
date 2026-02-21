package fr.paladium.palamod.modules.argus.packets;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class PacketAnalyzer {
  public static void a() {
    String initiator = GetPacketInitiator.a();
    if (initiator != null)
      PacketInitiatorManager.initiators.a(initiator); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\packets\PacketAnalyzer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */