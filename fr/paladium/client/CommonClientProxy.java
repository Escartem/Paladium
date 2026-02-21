package fr.paladium.client;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.common.CommonModule;

public class CommonClientProxy extends CommonModule {
  private static CommonClientProxy instance;
  
  public static CommonClientProxy getInstance() {
    return instance;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    instance = this;
    super.onPreInit(event);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\client\CommonClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */