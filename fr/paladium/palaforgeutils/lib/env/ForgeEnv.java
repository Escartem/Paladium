package fr.paladium.palaforgeutils.lib.env;

import java.util.Map;
import net.minecraft.launchwrapper.Launch;

public class ForgeEnv {
  private static final String DEV_ARG = "fr.paladium.dev";
  
  private static boolean IDE = false;
  
  static {
    String command = System.getProperty("sun.java.command");
    if (command != null && !command.isEmpty()) {
      String[] commandArgs = command.split(" ");
      if (commandArgs.length >= 1 && commandArgs[0].contains("Gradle"))
        IDE = true; 
    } 
  }
  
  public static Map<String, Object> getArguments() {
    return Launch.blackboard;
  }
  
  public static Object getArgument(String key) {
    return getArguments().get(key);
  }
  
  public static boolean hasArgument(String key) {
    return getArguments().containsKey(key);
  }
  
  public static boolean isDev() {
    return (System.getProperty("fr.paladium.dev") != null);
  }
  
  public static boolean isIDE() {
    return IDE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\env\ForgeEnv.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */