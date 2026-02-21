package fr.paladium.palamod.modules.pillage.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.pillage.network.packets.PacketBindSkin;
import fr.paladium.palamod.modules.pillage.network.packets.PacketCreateEffect;

public class MessageHandler {
  public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("palamod-pillage");
  
  public static void init() {
    NETWORK.registerMessage(PacketCreateEffect.ClientHandler.class, PacketCreateEffect.class, 7, Side.CLIENT);
    NETWORK.registerMessage(PacketBindSkin.ClientHandler.class, PacketBindSkin.class, 9, Side.CLIENT);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\network\MessageHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */