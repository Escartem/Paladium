package fr.paladium.palamod.modules.miner.dimminer.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.miner.dimminer.client.overlay.UIDimMinerOverlay;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import io.netty.buffer.ByteBuf;

public class SCPacketDimMinerOverlay implements IMessage {
  public void fromBytes(ByteBuf buf) {}
  
  public void toBytes(ByteBuf buf) {}
  
  public static class Handler implements IMessageHandler<SCPacketDimMinerOverlay, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketDimMinerOverlay message, MessageContext ctx) {
      ZUI.open((UI)new UIDimMinerOverlay());
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\network\SCPacketDimMinerOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */