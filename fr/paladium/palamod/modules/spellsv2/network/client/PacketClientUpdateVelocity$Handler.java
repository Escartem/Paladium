package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;

public class Handler implements IMessageHandler<PacketClientUpdateVelocity, IMessage> {
  public IMessage onMessage(PacketClientUpdateVelocity message, MessageContext ctx) {
    (Minecraft.func_71410_x()).field_71439_g.func_70016_h(message.x, message.y, message.z);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientUpdateVelocity$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */