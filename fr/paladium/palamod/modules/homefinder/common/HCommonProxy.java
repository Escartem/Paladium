package fr.paladium.palamod.modules.homefinder.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

public class HCommonProxy {
  public static HCommonProxy instance;
  
  private Side side;
  
  public Side getSide() {
    return this.side;
  }
  
  public HCommonProxy() {
    instance = this;
  }
  
  public void init(FMLInitializationEvent event) {}
  
  public void preInit(FMLPreInitializationEvent event) {}
  
  public void getManager() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\common\HCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */