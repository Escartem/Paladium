package fr.paladium.palaconfiguration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigurationLogger {
  private static final String prefix = "[Configuration] ";
  
  public static Logger logger = Logger.getLogger("configuration");
  
  static {
    logger.setLevel(Level.ALL);
  }
  
  public static void warn(String message) {
    logger.log(Level.WARNING, "[Configuration] " + message);
  }
  
  public static void info(String message) {
    logger.log(Level.INFO, "[Configuration] " + message);
  }
  
  public static void error(String message) {
    logger.log(Level.SEVERE, "[Configuration] " + message);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\ConfigurationLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */