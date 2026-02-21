package fr.paladium.palamod.modules.argus.loader;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C17PacketCustomPayload;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class CustomPayloadBuilder {
  private final String a;
  
  private final byte[] b;
  
  public CustomPayloadBuilder(String a, byte[] b) {
    this.a = a;
    this.b = b;
  }
  
  public Packet toPacket() {
    return (Packet)new C17PacketCustomPayload(this.a, this.b);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\loader\CustomPayloadBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */