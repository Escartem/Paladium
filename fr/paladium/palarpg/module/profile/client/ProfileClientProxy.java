package fr.paladium.palarpg.module.profile.client;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palarpg.module.profile.client.listener.RPGProfileClientListener;
import fr.paladium.palarpg.module.profile.common.ProfileCommonProxy;

public class ProfileClientProxy extends ProfileCommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { RPGProfileClientListener.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\client\ProfileClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */