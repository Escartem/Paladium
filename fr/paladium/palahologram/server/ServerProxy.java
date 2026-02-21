package fr.paladium.palahologram.server;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.command.annotated.registry.CommandRegistry;
import fr.paladium.palahologram.common.CommonProxy;
import fr.paladium.palahologram.server.command.HologramCommand;
import fr.paladium.palahologram.server.listener.PlayerListener;

public class ServerProxy extends CommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { PlayerListener.class });
    CommandRegistry.register(new Class[] { HologramCommand.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palahologram\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */