package fr.paladium.palamod.client.utils;

import java.io.File;

public enum OperatingSystem {
  LINUX("linux", new String[] { "linux", "unix" }),
  WINDOWS("windows", new String[] { "win" }),
  OSX("osx", new String[] { "mac" }),
  UNKNOWN("unknown", new String[0]);
  
  private final String name;
  
  private final String[] aliases;
  
  OperatingSystem(String name, String... aliases) {
    this.name = name;
    this.aliases = (aliases == null) ? new String[0] : aliases;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String[] getAliases() {
    return this.aliases;
  }
  
  public boolean isSupported() {
    return (this != UNKNOWN);
  }
  
  public String getJavaDir() {
    String separator = System.getProperty("file.separator");
    String path = System.getProperty("java.home") + separator + "bin" + separator;
    if (getCurrentPlatform() == WINDOWS && (new File(path + "javaw.exe")).isFile())
      return path + "javaw.exe"; 
    return path + "java";
  }
  
  public static boolean getJavaProcess() {
    boolean bl = "32".equals(System.getProperty("sun.arch.data.model"));
    if (bl) {
      boolean bl2 = (System.getenv("PROCESSOR_ARCHITEW6432") != null && "AMD64".equals(System.getenv("PROCESSOR_ARCHITEW6432")));
      if (!bl2) {
        bl2 = (System.getenv("PROCESSOR_ARCHITECTURE") != null && "AMD64".equals(System.getenv("PROCESSOR_ARCHITECTURE")));
        return false;
      } 
      if (bl2)
        return true; 
    } 
    return bl;
  }
  
  public static OperatingSystem getCurrentPlatform() {
    String osName = System.getProperty("os.name").toLowerCase();
    for (OperatingSystem os : values()) {
      String[] arrayOfString;
      int i;
      byte b;
      for (arrayOfString = os.getAliases(), i = arrayOfString.length, b = 0; b < i; ) {
        String alias = arrayOfString[b];
        if (!osName.contains(alias)) {
          b++;
          continue;
        } 
        return os;
      } 
    } 
    return UNKNOWN;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\clien\\utils\OperatingSystem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */