package fr.paladium.palamod.modules.shop.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import fr.paladium.palamod.modules.shop.api.ShopManager;

@PacketHandler
public class Handler implements IMessageHandler<CSPacketBuyShopItem, IMessage> {
  @SideOnly(Side.SERVER)
  public IMessage onMessage(CSPacketBuyShopItem message, MessageContext ctx) {
    if (CSPacketBuyShopItem.access$000(message) <= 0 || CSPacketBuyShopItem.access$100(message) == null)
      return null; 
    ShopManager.getInstance().buy((ctx.getServerHandler()).field_147369_b, CSPacketBuyShopItem.access$100(message), CSPacketBuyShopItem.access$000(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\network\CSPacketBuyShopItem$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */