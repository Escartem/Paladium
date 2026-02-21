package fr.paladium.palamod.modules.shop.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import fr.paladium.palamod.modules.shop.client.ui.UIShop;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;

@PacketHandler
public class Handler implements IMessageHandler<SCPacketShopItemListReply, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketShopItemListReply message, MessageContext ctx) {
    UIShop ui = (UIShop)ZUI.getUI(UIShop.class);
    if (ui != null) {
      ui.putItems(SCPacketShopItemListReply.access$000(message));
      ui.setMoney(SCPacketShopItemListReply.access$100(message));
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\network\SCPacketShopItemListReply$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */