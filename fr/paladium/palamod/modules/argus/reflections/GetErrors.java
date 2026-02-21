package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import com.google.common.collect.Multimap;
import fr.paladium.palamod.modules.argus.strings.StringErrors;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockSafeChest;
import fr.paladium.palamod.util.MyHashSet;
import java.lang.reflect.Field;
import java.util.Arrays;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetErrors {
  public static MyHashSet<String> a(MyHashSet<String> _q) {
    try {
      Field errorField = GetLoadControllerClass.a().getDeclaredField(StringErrors._a);
      BlockSafeChest.f(errorField);
      Multimap<String, Throwable> tw = (Multimap<String, Throwable>)errorField.get(GetLoadController.a());
      tw.values()
        .forEach(tp -> Arrays.<StackTraceElement>asList(tp.getStackTrace()).forEach(()));
    } catch (Exception error) {
      return _q;
    } 
    return _q;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\GetErrors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */