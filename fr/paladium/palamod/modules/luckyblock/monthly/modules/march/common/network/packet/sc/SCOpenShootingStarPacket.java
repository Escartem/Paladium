package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.sc;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.ui.start.ShootingStarUI;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import io.netty.buffer.ByteBuf;

public class SCOpenShootingStarPacket implements IMessage {
  public void fromBytes(ByteBuf buf) {}
  
  public void toBytes(ByteBuf buf) {}
  
  @PacketHandler
  public static class Handler implements IMessageHandler<SCOpenShootingStarPacket, IMessage> {
    public IMessage onMessage(SCOpenShootingStarPacket message, MessageContext ctx) {
      ZUI.open((UI)new ShootingStarUI());
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\network\packet\sc\SCOpenShootingStarPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */