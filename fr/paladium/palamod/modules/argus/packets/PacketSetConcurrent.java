package fr.paladium.palamod.modules.argus.packets;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class PacketSetConcurrent {
  public static Set<?> a() {
    return ConcurrentHashMap.newKeySet();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\packets\PacketSetConcurrent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */