package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.sc;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.ui.satellite.SatelliteUI;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;

@PacketHandler
public class Handler implements IMessageHandler<SCOpenSatelliteUIPacket, IMessage> {
  public IMessage onMessage(SCOpenSatelliteUIPacket message, MessageContext ctx) {
    ZUI.open((UI)new SatelliteUI());
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\network\packet\sc\SCOpenSatelliteUIPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */