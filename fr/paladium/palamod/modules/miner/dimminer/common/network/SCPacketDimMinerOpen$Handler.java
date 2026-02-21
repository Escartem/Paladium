package fr.paladium.palamod.modules.miner.dimminer.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.miner.dimminer.client.ui.entry.UIDimMinerEntry;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;

public class Handler implements IMessageHandler<SCPacketDimMinerOpen, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketDimMinerOpen message, MessageContext ctx) {
    ZUI.open((UI)new UIDimMinerEntry());
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\network\SCPacketDimMinerOpen$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */