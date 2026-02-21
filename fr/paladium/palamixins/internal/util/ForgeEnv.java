package fr.paladium.palamixins.internal.util;

public class ForgeEnv {
  private static boolean IDE = false;
  
  static {
    String command = System.getProperty("sun.java.command");
    if (command != null && !command.isEmpty()) {
      String[] commandArgs = command.split(" ");
      if (commandArgs.length >= 1 && commandArgs[0].contains("Gradle"))
        IDE = true; 
    } 
  }
  
  public static boolean isIDE() {
    return IDE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\interna\\util\ForgeEnv.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */