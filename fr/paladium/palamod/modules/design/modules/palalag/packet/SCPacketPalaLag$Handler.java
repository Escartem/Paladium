package fr.paladium.palamod.modules.design.modules.palalag.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.design.modules.palalag.ModulePalaLag;

public class Handler implements IMessageHandler<SCPacketPalaLag, IMessage> {
  public IMessage onMessage(SCPacketPalaLag message, MessageContext ctx) {
    ModulePalaLag.getInstance().setEnd(System.currentTimeMillis() + SCPacketPalaLag.access$000(message));
    ModulePalaLag.getInstance().setCooldown(SCPacketPalaLag.access$100(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\design\modules\palalag\packet\SCPacketPalaLag$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */