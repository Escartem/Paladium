package fr.paladium.palaconfiguration.server.dto;

public enum Environment {
  DEV, CREATION, PROD;
  
  public static final Environment[] values;
  
  static {
    values = values();
  }
  
  public static Environment getByName(String name) {
    for (Environment environment : values) {
      if (environment.name().equalsIgnoreCase(name))
        return environment; 
    } 
    return null;
  }
  
  public boolean isDevelopment() {
    return (this != PROD);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\dto\Environment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */