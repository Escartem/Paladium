package fr.paladium.palamod.modules.egghunt.common;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy {
  public void init() {
    CommonEventHandler eventHandler = new CommonEventHandler();
    MinecraftForge.EVENT_BUS.register(eventHandler);
    FMLCommonHandler.instance().bus().register(eventHandler);
  }
  
  public void serverStarted() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */