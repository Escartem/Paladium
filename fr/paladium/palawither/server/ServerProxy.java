package fr.paladium.palawither.server;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.RabbitNetwork;
import fr.paladium.palawither.common.CommonProxy;
import fr.paladium.palawither.common.network.RBPacketWitherSpawn;
import fr.paladium.palawither.server.listener.ServerFactionClaimListener;
import fr.paladium.palawither.server.listener.ServerVanillaWitherListener;
import fr.paladium.palawither.server.listener.ServerWitherListener;
import fr.paladium.palawither.server.listener.ServerWitherReceptacleListener;

public class ServerProxy extends CommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { ServerWitherListener.class });
    addListener(new Class[] { ServerVanillaWitherListener.class });
    addListener(new Class[] { ServerFactionClaimListener.class });
    addListener(new Class[] { ServerWitherReceptacleListener.class });
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    RabbitNetwork.createNetwork("palawither").registerPacket(RBPacketWitherSpawn.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */