package fr.paladium.palarpg.server;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import fr.paladium.palaforgeutils.lib.command.annotated.registry.CommandRegistry;
import fr.paladium.palarpg.common.CommonProxy;
import fr.paladium.palarpg.server.command.RPGCommand;

public class ServerProxy extends CommonProxy {
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    CommandRegistry.register(new Class[] { RPGCommand.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */