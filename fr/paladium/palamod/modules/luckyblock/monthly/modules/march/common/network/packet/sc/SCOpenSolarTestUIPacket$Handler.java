package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.sc;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.ui.solar.SolarTestUI;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;

@PacketHandler
public class Handler implements IMessageHandler<SCOpenSolarTestUIPacket, IMessage> {
  public IMessage onMessage(SCOpenSolarTestUIPacket message, MessageContext ctx) {
    ZUI.open((UI)new SolarTestUI(SCOpenSolarTestUIPacket.access$000(message), SCOpenSolarTestUIPacket.access$100(message), SCOpenSolarTestUIPacket.access$200(message)));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\network\packet\sc\SCOpenSolarTestUIPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */