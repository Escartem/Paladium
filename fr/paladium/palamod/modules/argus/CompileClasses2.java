package fr.paladium.palamod.modules.argus;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import fr.paladium.palamod.util.MyHashSet;
import java.util.Collection;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class CompileClasses2 {
  public static MyHashSet<String> get(MyHashSet<String> a) {
    try {
      a.p((Collection)CurrentGuiScreen._a);
    } catch (Exception exception) {}
    return a;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\CompileClasses2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */