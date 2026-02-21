package fr.paladium.palaforgeutils.lib.server.internal.network;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.internal.event.ServerChangeEvent;
import net.minecraftforge.common.MinecraftForge;

public class Handler implements IMessageHandler<SCPacketServerInformations, IMessage> {
  public IMessage onMessage(SCPacketServerInformations message, MessageContext ctx) {
    MinecraftForge.EVENT_BUS.post((Event)new ServerChangeEvent.Pre(Side.CLIENT, Server.getServerType()));
    Server.set(SCPacketServerInformations.access$000(message), SCPacketServerInformations.access$100(message), SCPacketServerInformations.access$200(message));
    MinecraftForge.EVENT_BUS.post((Event)new ServerChangeEvent.Post(Side.CLIENT, Server.getServerType()));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\server\internal\network\SCPacketServerInformations$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */