package fr.paladium.palamod.modules.argus.instances;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import cpw.mods.fml.client.FMLClientHandler;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class InstanceFMLClientHandler {
  public static FMLClientHandler i() {
    return FMLClientHandler.instance();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\instances\InstanceFMLClientHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */