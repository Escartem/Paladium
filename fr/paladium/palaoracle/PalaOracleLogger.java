package fr.paladium.palaoracle;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PalaOracleLogger {
  public static Logger logger = Logger.getLogger("oracle");
  
  private static final String prefix = "[Oracle] ";
  
  static {
    logger.setLevel(Level.ALL);
  }
  
  public static void warn(String message) {
    logger.log(Level.WARNING, "[Oracle] " + message);
  }
  
  public static void info(String message) {
    logger.log(Level.INFO, "[Oracle] " + message);
  }
  
  public static void error(String message) {
    logger.log(Level.SEVERE, "[Oracle] " + message);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaoracle\PalaOracleLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */