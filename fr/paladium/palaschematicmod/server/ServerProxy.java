package fr.paladium.palaschematicmod.server;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.command.annotated.registry.CommandRegistry;
import fr.paladium.palaschematicmod.common.CommonProxy;
import fr.paladium.palaschematicmod.server.command.PalaSchematicCommand;
import fr.paladium.palaschematicmod.server.listener.PalaSchematicListener;

public class ServerProxy extends CommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { PalaSchematicListener.class });
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    CommandRegistry.register(new Class[] { PalaSchematicCommand.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */