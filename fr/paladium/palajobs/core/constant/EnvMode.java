package fr.paladium.palajobs.core.constant;

public enum EnvMode {
  DEV, PROD;
  
  public static final EnvMode CURRENT_MODE;
  
  static {
    CURRENT_MODE = PROD;
  }
  
  public static boolean isOnProduction() {
    return (CURRENT_MODE == PROD);
  }
  
  public static boolean printMessage(String content) {
    if (isOnProduction())
      return false; 
    System.out.println("[DEV] " + content);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\constant\EnvMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */