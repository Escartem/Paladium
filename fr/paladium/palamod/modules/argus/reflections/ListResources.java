package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import fr.paladium.palamod.util.MyHashSet;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class ListResources {
  public static MyHashSet<String> msh = new MyHashSet();
  
  public static boolean cop(String a) {
    return msh.m(a);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\ListResources.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */