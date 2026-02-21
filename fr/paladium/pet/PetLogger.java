package fr.paladium.pet;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PetLogger {
  public static final Logger LOGGER = Logger.getLogger("pet");
  
  private static final String PREFIX = "[Pet] ";
  
  static {
    LOGGER.setLevel(Level.ALL);
  }
  
  public static void warn(String message) {
    LOGGER.log(Level.WARNING, "[Pet] " + message);
  }
  
  public static void info(String message) {
    LOGGER.log(Level.INFO, "[Pet] " + message);
  }
  
  public static void error(String message) {
    LOGGER.log(Level.SEVERE, "[Pet] " + message);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\PetLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */