package fr.paladium.palamod.modules.argus.packets;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import java.util.Iterator;
import java.util.Set;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class PacketSet<T> {
  private final Set<T> data = (Set)PacketSetConcurrent.a();
  
  public void a(T t) {
    this.data.add(t);
  }
  
  public Iterator<T> i() {
    return this.data.iterator();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\packets\PacketSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */