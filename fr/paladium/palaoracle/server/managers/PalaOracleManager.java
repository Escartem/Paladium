package fr.paladium.palaoracle.server.managers;

import fr.paladium.palaoracle.core.pojo.PalaOracleEvent;
import fr.paladium.palaoracle.server.proxy.ServerProxy;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PalaOracleManager {
  public static PalaOracleManager instance;
  
  private ExecutorService executorService = Executors.newFixedThreadPool(10);
  
  public void generalCaptureEvent(PalaOracleEvent palaOracleEventInput) {
    this.executorService.submit(() -> {
          try {
            ServerProxy.getInstance().getApi().generalCaptureEvent(palaOracleEventInput).execute();
          } catch (IOException e) {
            e.printStackTrace();
          } 
        });
  }
  
  public PalaOracleManager() {
    instance = this;
  }
  
  public static PalaOracleManager getInstance() {
    if (instance == null)
      new PalaOracleManager(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaoracle\server\managers\PalaOracleManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */