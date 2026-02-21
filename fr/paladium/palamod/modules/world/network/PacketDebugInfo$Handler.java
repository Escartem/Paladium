package fr.paladium.palamod.modules.world.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.world.PWorld;

public class Handler implements IMessageHandler<PacketDebugInfo, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(PacketDebugInfo message, MessageContext ctx) {
    PWorld.initBlocksLayers(message.paladiumgreen_enabled, message.endium_enabled, message.doublepaladium_enabled);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\world\network\PacketDebugInfo$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */