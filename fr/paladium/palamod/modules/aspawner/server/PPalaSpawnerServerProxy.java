package fr.paladium.palamod.modules.aspawner.server;

import fr.paladium.palamod.modules.aspawner.common.PPalaSpawnerCommonProxy;

public class PPalaSpawnerServerProxy extends PPalaSpawnerCommonProxy {
  private static PPalaSpawnerServerProxy instance;
  
  public static PPalaSpawnerServerProxy getInstance() {
    return instance;
  }
  
  public PPalaSpawnerServerProxy() {
    instance = this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\aspawner\server\PPalaSpawnerServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */