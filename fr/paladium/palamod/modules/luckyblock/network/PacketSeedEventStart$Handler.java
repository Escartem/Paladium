package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.luckyblock.events.ClientSeedEvent;
import net.minecraft.client.Minecraft;

public class Handler implements IMessageHandler<PacketSeedEventStart, IMessage> {
  public IMessage onMessage(PacketSeedEventStart message, MessageContext ctx) {
    if (ctx.side == Side.CLIENT)
      ClientSeedEvent.event = new ClientSeedEvent((Minecraft.func_71410_x()).field_71439_g.field_70170_p, PacketSeedEventStart.access$000(message), PacketSeedEventStart.access$100(message), PacketSeedEventStart.access$200(message)); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketSeedEventStart$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */