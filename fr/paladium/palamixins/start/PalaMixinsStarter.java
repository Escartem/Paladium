package fr.paladium.palamixins.start;

import java.lang.reflect.Method;

public class PalaMixinsStarter {
  public static void start(String className, String[] args) {
    try {
      String anotherMixin = System.getProperty("palamixins");
      System.setProperty("fml.coreMods.load", "fr.paladium.palamixins.coremod.LoadingPlugin" + ((anotherMixin != null && !anotherMixin.trim().isEmpty()) ? ("," + anotherMixin) : ""));
      Class<?> clazz = Class.forName(className);
      Method mainMethod = clazz.getMethod("main", new Class[] { String[].class });
      mainMethod.invoke(null, new Object[] { args });
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\start\PalaMixinsStarter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */