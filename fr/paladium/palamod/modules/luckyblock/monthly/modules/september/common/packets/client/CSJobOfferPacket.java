package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemJobOffer;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CSJobOfferPacket implements IMessage {
  private int action;
  
  public CSJobOfferPacket() {
    this.action = -1;
  }
  
  public CSJobOfferPacket(int action) {
    this.action = action;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.action = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.action);
  }
  
  public static class Handler implements IMessageHandler<CSJobOfferPacket, IMessage> {
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
      jobOffer.handlePacket((EntityPlayer)player, held, message.action);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\packets\client\CSJobOfferPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */