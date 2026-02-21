package fr.paladium.palamod.modules.argus.loader;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import net.minecraft.client.Minecraft;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetThePlayer {
  public static Object a() {
    return (Minecraft.func_71410_x()).field_71439_g;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\loader\GetThePlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */