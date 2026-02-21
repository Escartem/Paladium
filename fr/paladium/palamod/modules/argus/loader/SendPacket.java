package fr.paladium.palamod.modules.argus.loader;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class SendPacket {
  public static boolean a(String ch, byte[] a) {
    try {
      Object _a = GetThePlayer.a();
      Object _b = GetAddQueue.a(_a);
      CustomPayloadBuilder _c = new CustomPayloadBuilder(ch, a);
      AddToSendQueue.a(_b, _c.toPacket());
      return true;
    } catch (Exception error) {
      error.printStackTrace();
      return false;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\loader\SendPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */