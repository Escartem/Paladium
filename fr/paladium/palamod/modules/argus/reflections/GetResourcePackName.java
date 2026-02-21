package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import net.minecraft.client.resources.IResourcePack;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetResourcePackName {
  public static String _a(IResourcePack pk) {
    return pk.func_130077_b();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\GetResourcePackName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */