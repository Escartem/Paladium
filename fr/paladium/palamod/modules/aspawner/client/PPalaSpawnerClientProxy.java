package fr.paladium.palamod.modules.aspawner.client;

import fr.paladium.palamod.modules.aspawner.common.PPalaSpawnerCommonProxy;

public class PPalaSpawnerClientProxy extends PPalaSpawnerCommonProxy {
  private static PPalaSpawnerClientProxy instance;
  
  public static PPalaSpawnerClientProxy getInstance() {
    return instance;
  }
  
  public PPalaSpawnerClientProxy() {
    instance = this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\aspawner\client\PPalaSpawnerClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */