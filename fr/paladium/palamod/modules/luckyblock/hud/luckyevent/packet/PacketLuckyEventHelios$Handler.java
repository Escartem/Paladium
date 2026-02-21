package fr.paladium.palamod.modules.luckyblock.hud.luckyevent.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.ModuleLuckyEvent;

public class Handler implements IMessageHandler<PacketLuckyEventHelios, IMessage> {
  public IMessage onMessage(PacketLuckyEventHelios message, MessageContext ctx) {
    ModuleLuckyEvent.getInstance().updateState(message);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\hud\luckyevent\packet\PacketLuckyEventHelios$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */