package fr.paladium.palamod.modules.trixium.api;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TrixiumAPI {
  private static final ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(4);
  
  public static ThreadPoolExecutor getExecutor() {
    return executor;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\api\TrixiumAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */