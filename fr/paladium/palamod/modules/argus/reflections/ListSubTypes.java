package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import fr.paladium.palamod.util.MyHashSet;
import java.util.Arrays;
import org.reflections.Reflections;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class ListSubTypes {
  public static MyHashSet<String> nh(Reflections rf, MyHashSet<String> a) {
    try {
      Arrays.<Class<?>>asList(GetCheaterClazz.clazz)
        .forEach(c -> rf.getSubTypesOf(c).forEach(()));
    } catch (Exception exception) {}
    return a;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\ListSubTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */