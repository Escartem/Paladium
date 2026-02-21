package fr.paladium.palarpg.module.stat.server;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import fr.paladium.palarpg.module.stat.common.StatCommonProxy;
import fr.paladium.palarpg.module.stat.server.listener.StatServerEntityEventListener;
import fr.paladium.palarpg.module.stat.server.listener.StatServerLivingEventListener;
import fr.paladium.palarpg.module.stat.server.listener.StatServerPlayerEventListener;

public class StatServerProxy extends StatCommonProxy {
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    addListener(new Class[] { StatServerLivingEventListener.class, StatServerEntityEventListener.class, StatServerPlayerEventListener.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\server\StatServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */