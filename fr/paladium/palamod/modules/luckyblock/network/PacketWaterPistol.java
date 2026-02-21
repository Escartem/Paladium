package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import io.netty.buffer.ByteBuf;

public class PacketWaterPistol implements IMessage, IMessageHandler<PacketWaterPistol, IMessage> {
  public void fromBytes(ByteBuf buf) {}
  
  public void toBytes(ByteBuf buf) {}
  
  public IMessage onMessage(PacketWaterPistol message, MessageContext ctx) {
    ClientProxy.hitmarker = true;
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketWaterPistol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */