package fr.paladium.palamod.modules.argus.loader;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import com.allatori.annotations.StringEncryption;
import com.allatori.annotations.StringEncryptionType;
import fr.paladium.palamod.modules.argus.packetregistration.PacketRegistration;
import io.netty.channel.ChannelHandler;
import java.lang.reflect.Method;

@StringEncryption("maximum")
@StringEncryptionType("strong")
@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class BytecodeListener {
  public static void rg() {
    try {
      Object nr = PacketRegistration.nr;
      Method m = nr.getClass().getDeclaredMethod("newChannel", new Class[] { String.class, ChannelHandler[].class });
      m.invoke(nr, new Object[] { " ", { new BytecodeChannel() } });
    } catch (Throwable e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\loader\BytecodeListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */