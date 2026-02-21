package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;

public class Handler implements IMessageHandler<PacketUpdateBiome, IMessage> {
  public IMessage onMessage(PacketUpdateBiome message, MessageContext ctx) {
    ClientProxy.updateBiome(message.x, message.z, message.biome);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketUpdateBiome$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */