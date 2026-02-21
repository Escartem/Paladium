package fr.paladium.palamod.modules.argus.loader;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class AddToSendQueue {
  public static void a(Object a, Object b) {
    ((NetHandlerPlayClient)a).func_147297_a((Packet)b);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\loader\AddToSendQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */