package fr.paladium.palamod.modules.argus.loader;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class LoaderFollowingResult {
  private final int a;
  
  private final int b;
  
  private final boolean l;
  
  private final String k;
  
  private boolean s;
  
  public LoaderFollowingResult(int a, int b, boolean l, String k) {
    this.a = a;
    this.b = b;
    this.l = l;
    this.k = k;
    this.s = false;
  }
  
  public byte[] p() {
    if (this.s)
      return null; 
    this.s = true;
    ByteArrayDataOutput o = ByteStreams.newDataOutput();
    o.writeLong(System.currentTimeMillis());
    o.writeInt(this.a);
    o.writeInt(this.b);
    o.writeBoolean(this.l);
    o.writeBoolean((this.k != null));
    if (this.k != null)
      o.writeUTF(this.k); 
    return o.toByteArray();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\loader\LoaderFollowingResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */