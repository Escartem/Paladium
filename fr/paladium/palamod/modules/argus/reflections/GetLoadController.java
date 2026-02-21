package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import cpw.mods.fml.common.LoadController;
import fr.paladium.palamod.modules.argus.strings.StringModController;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockSafeChest;
import java.lang.reflect.Field;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetLoadController {
  public static LoadController a() {
    try {
      Field f = GetLoadClass.a().getDeclaredField(StringModController._a);
      BlockSafeChest.f(f);
      return (LoadController)f.get(GetLoader.a());
    } catch (Exception err) {
      return null;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\GetLoadController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */