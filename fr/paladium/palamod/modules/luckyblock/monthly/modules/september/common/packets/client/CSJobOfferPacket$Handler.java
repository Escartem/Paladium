package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemJobOffer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Handler implements IMessageHandler<CSJobOfferPacket, IMessage> {
  public IMessage onMessage(CSJobOfferPacket message, MessageContext ctx) {
    if (ctx.side == Side.CLIENT)
      return null; 
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    ItemStack held = player.func_70694_bm();
    if (held == null || held.func_77973_b() == null)
      return null; 
    Item item = held.func_77973_b();
    if (!(item instanceof ItemJobOffer))
      return null; 
    ItemJobOffer jobOffer = (ItemJobOffer)item;
    jobOffer.handlePacket((EntityPlayer)player, held, CSJobOfferPacket.access$000(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\packets\client\CSJobOfferPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */