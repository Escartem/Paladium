package fr.paladium.palarpg.module.stat.client;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palarpg.module.stat.client.listener.RPGStatsWorldRenderListener;
import fr.paladium.palarpg.module.stat.common.StatCommonProxy;

public class StatClientProxy extends StatCommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { RPGStatsWorldRenderListener.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\client\StatClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */