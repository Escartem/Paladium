package fr.paladium.palaschematicmod.client;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaschematicmod.client.listener.ClientSchematicSessionListener;
import fr.paladium.palaschematicmod.common.CommonProxy;
import fr.paladium.palaschematicmod.common.pojo.schematic.Schematic;
import fr.paladium.palaschematicmod.common.pojo.schematic.SchematicCopySession;

public class ClientProxy extends CommonProxy {
  public static Schematic schematicToExport;
  
  public static SchematicCopySession schematicCopySession = new SchematicCopySession();
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { ClientSchematicSessionListener.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\client\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */