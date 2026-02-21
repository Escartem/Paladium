package fr.paladium.palamod.modules.argus.methods;

import fr.paladium.palamod.modules.argus.classes.ClassClass;
import fr.paladium.palamod.modules.argus.classes.ClassInt;
import fr.paladium.palamod.modules.argus.strings.StringFunc150751a;

public class MethodGetFunc150751a {
  public static final Class<?> fpp;
  
  public static Object a(Class a) throws Exception {
    return a.getDeclaredMethod(StringFunc150751a.a(), new Class[] { ClassInt.a(), ClassClass.a() });
  }
  
  static {
    try {
      fpp = Class.forName("cpw.mods.fml.common.network.internal.FMLProxyPacket");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\methods\MethodGetFunc150751a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */