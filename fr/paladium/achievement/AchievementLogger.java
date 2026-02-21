package fr.paladium.achievement;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AchievementLogger {
  public static Logger logger = Logger.getLogger("achievement");
  
  private static final String prefix = "[Achievement] ";
  
  static {
    logger.setLevel(Level.ALL);
  }
  
  public static void warn(String message) {
    logger.log(Level.WARNING, "[Achievement] " + message);
  }
  
  public static void info(String message) {
    logger.log(Level.INFO, "[Achievement] " + message);
  }
  
  public static void error(String message) {
    logger.log(Level.SEVERE, "[Achievement] " + message);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\AchievementLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */