package fr.paladium.palamod.modules.miner.dimminer.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.miner.dimminer.client.ui.stats.UIDimMinerStats;
import fr.paladium.palamod.modules.miner.dimminer.common.data.DimMinerPlayer;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import io.netty.buffer.ByteBuf;

public class SCPacketDimMinerStats implements IMessage {
  public void fromBytes(ByteBuf buf) {}
  
  public void toBytes(ByteBuf buf) {}
  
  public static class Handler implements IMessageHandler<SCPacketDimMinerStats, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketDimMinerStats message, MessageContext ctx) {
      ZUI.open((UI)new UIDimMinerStats(DimMinerPlayer.get()));
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\network\SCPacketDimMinerStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */