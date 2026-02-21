package fr.paladium.palamod.modules.shop.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import fr.paladium.palamod.modules.shop.api.ShopManager;
import net.minecraft.entity.player.EntityPlayerMP;

@PacketHandler
public class Handler implements IMessageHandler<CSPacketSellShopItem, IMessage> {
  @SideOnly(Side.SERVER)
  public IMessage onMessage(CSPacketSellShopItem message, MessageContext ctx) {
    if (CSPacketSellShopItem.access$000(message) <= 0 || CSPacketSellShopItem.access$100(message) == null)
      return null; 
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    ShopManager.getInstance().sell(player, CSPacketSellShopItem.access$100(message), CSPacketSellShopItem.access$000(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\network\CSPacketSellShopItem$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */