package fr.paladium.palacommunityevent;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PalaEventCommunityLogger {
  public static Logger logger = Logger.getLogger("palaeventcommunity");
  
  private static final String prefix = "[PalaEventCommunity] ";
  
  static {
    logger.setLevel(Level.ALL);
  }
  
  public static void warn(String message) {
    logger.log(Level.WARNING, "[PalaEventCommunity] " + message);
  }
  
  public static void info(String message) {
    logger.log(Level.INFO, "[PalaEventCommunity] " + message);
  }
  
  public static void error(String message) {
    logger.log(Level.SEVERE, "[PalaEventCommunity] " + message);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\PalaEventCommunityLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */