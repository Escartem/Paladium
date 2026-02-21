package fr.paladium.palamod.modules.argus;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import fr.paladium.palamod.modules.argus.reflections.GetActiveModListObjects;
import fr.paladium.palamod.modules.argus.reflections.GetDiscoverer;
import fr.paladium.palamod.modules.argus.reflections.GetErrors;
import fr.paladium.palamod.modules.argus.reflections.GetListeners;
import fr.paladium.palamod.modules.argus.reflections.GetModContainers;
import fr.paladium.palamod.modules.argus.reflections.GetModList;
import fr.paladium.palamod.modules.argus.reflections.GetModObjectList;
import fr.paladium.palamod.modules.argus.reflections.GetNamedMods;
import fr.paladium.palamod.modules.argus.reflections.GetPackageOwners;
import fr.paladium.palamod.util.MyHashSet;
import java.util.Collection;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class CompileClasses4 {
  public static MyHashSet<String> get(MyHashSet<String> a) {
    try {
      a.p((Collection)pop());
    } catch (Exception error) {
      error.printStackTrace();
    } 
    return a;
  }
  
  public static MyHashSet<String> pop() {
    MyHashSet<String> plep = new MyHashSet();
    try {
      plep.p((Collection)GetModObjectList.a(new MyHashSet()));
      plep.p((Collection)GetPackageOwners.a(new MyHashSet()));
      plep.p((Collection)GetActiveModListObjects.a(new MyHashSet()));
      plep.p((Collection)GetModList.a(new MyHashSet()));
      plep.p((Collection)GetNamedMods.a(new MyHashSet()));
      plep.p((Collection)GetDiscoverer.a(new MyHashSet()));
      plep.p((Collection)GetErrors.a(new MyHashSet()));
      plep.p((Collection)GetModContainers.a(new MyHashSet()));
      plep.p((Collection)GetListeners.a(new MyHashSet()));
    } catch (Exception err) {
      err.printStackTrace();
    } 
    return plep;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\CompileClasses4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */