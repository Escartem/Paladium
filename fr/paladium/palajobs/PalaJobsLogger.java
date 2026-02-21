package fr.paladium.palajobs;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PalaJobsLogger {
  public static Logger logger = Logger.getLogger("palajobs");
  
  private static final String prefix = "[PalaJobs] ";
  
  static {
    logger.setLevel(Level.ALL);
  }
  
  public static void warn(String message) {
    logger.log(Level.WARNING, "[PalaJobs] " + message);
  }
  
  public static void info(String message) {
    logger.log(Level.INFO, "[PalaJobs] " + message);
  }
  
  public static void error(String message) {
    logger.log(Level.SEVERE, "[PalaJobs] " + message);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\PalaJobsLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */