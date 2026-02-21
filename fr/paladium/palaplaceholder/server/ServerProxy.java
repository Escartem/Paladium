package fr.paladium.palaplaceholder.server;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.command.annotated.registry.CommandRegistry;
import fr.paladium.palaplaceholder.common.CommonProxy;
import fr.paladium.palaplaceholder.server.command.PlaceholderCommand;

public class ServerProxy extends CommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    CommandRegistry.register(new Class[] { PlaceholderCommand.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaplaceholder\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */