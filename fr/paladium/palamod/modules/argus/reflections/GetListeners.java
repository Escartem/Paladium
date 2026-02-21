package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import com.allatori.annotations.StringEncryption;
import com.allatori.annotations.StringEncryptionType;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import fr.paladium.palamod.util.MyHashSet;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
@StringEncryption("maximum")
@StringEncryptionType("strong")
public class GetListeners {
  public static MyHashSet<String> a(MyHashSet<String> _q) {
    try {
      Class<?> asm = Class.forName("cpw.mods.fml.common.eventhandler.ASMEventHandler");
      Class<?> p = Class.forName("cpw.mods.fml.common.eventhandler.ListenerList");
      Class<?> pInst = Class.forName("cpw.mods.fml.common.eventhandler.ListenerList$ListenerListInst");
      Field f = p.getDeclaredField("allLists");
      f.setAccessible(true);
      Field fInst = p.getDeclaredField("lists");
      fInst.setAccessible(true);
      Field fListeners = pInst.getDeclaredField("listeners");
      fListeners.setAccessible(true);
      Field pr = pInst.getDeclaredField("priorities");
      pr.setAccessible(true);
      Field par = pInst.getDeclaredField("parent");
      par.setAccessible(true);
      ImmutableList lists = (ImmutableList)f.get((Object)null);
      for (UnmodifiableIterator unmodifiableIterator = lists.iterator(); unmodifiableIterator.hasNext(); ) {
        Object o = unmodifiableIterator.next();
        Object[] listInst = (Object[])fInst.get(o);
        for (Object inst : listInst) {
          ArrayList<Object> priorities = (ArrayList<Object>)pr.get(inst);
          if (priorities != null)
            for (Object elList : priorities)
              z(elList, _q);  
          Object parent = par.get(inst);
          while (parent != null) {
            ArrayList<Object> subP = (ArrayList<Object>)pr.get(parent);
            if (subP != null)
              for (Object elList : priorities)
                z(elList, _q);  
            Object[] arrayOfObject = (Object[])fListeners.get(inst);
            if (arrayOfObject != null)
              for (Object l : arrayOfObject)
                z(l, _q);  
            parent = par.get(parent);
          } 
          Object[] ls = (Object[])fListeners.get(inst);
          if (ls != null)
            for (Object l : ls)
              z(l, _q);  
        } 
      } 
      Field fC = asm.getDeclaredField("cache");
      fC.setAccessible(true);
      Map c = (Map)fC.get((Object)null);
      for (Object o : c.keySet()) {
        if (o instanceof Method) {
          Method m = (Method)o;
          String target = m.getDeclaringClass().getName();
          _q.c(target);
        } 
      } 
    } catch (Throwable error) {
      error.printStackTrace();
      return _q;
    } 
    return _q;
  }
  
  private static void z(Object o, MyHashSet<String> l) throws Exception {
    Class<?> asm = Class.forName("cpw.mods.fml.common.eventhandler.ASMEventHandler");
    if (asm.isInstance(o)) {
      Object o1 = asm.cast(o);
      Field f1 = asm.getDeclaredField("readable");
      f1.setAccessible(true);
      String readable = (String)f1.get(o1);
      String target = readable.split(" ")[1];
      String targetC = target.split("@")[0];
      l.c(targetC);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\GetListeners.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */