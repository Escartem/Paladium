package fr.paladium.palaplaceholder.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palaplaceholder.common.extension.impl.PlayerPlaceholderExtension;
import fr.paladium.palaplaceholder.common.manager.PlaceholderManager;

public abstract class CommonProxy extends AModProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    PlaceholderManager.inst().registerExtension(new Class[] { PlayerPlaceholderExtension.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaplaceholder\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */