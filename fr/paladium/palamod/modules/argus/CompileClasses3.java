package fr.paladium.palamod.modules.argus;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import fr.paladium.palamod.util.MyHashSet;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class CompileClasses3 {
  public static MyHashSet<String> get() {
    return CompileClasses4.get(CompileClasses2.get(CompileClasses1.get(new MyHashSet())));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\CompileClasses3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */