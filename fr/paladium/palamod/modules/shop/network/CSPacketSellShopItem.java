package fr.paladium.palamod.modules.shop.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.utils.Packet;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import fr.paladium.palamod.modules.shop.api.ShopManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

@Packet(side = Side.SERVER)
public class CSPacketSellShopItem implements IMessage {
  private String item;
  
  private int amount;
  
  public CSPacketSellShopItem(String item, int amount) {
    this.item = item;
    this.amount = amount;
  }
  
  public CSPacketSellShopItem() {}
  
  public void fromBytes(ByteBuf buf) {
    this.item = ByteBufUtils.readUTF8String(buf);
    this.amount = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.item);
    buf.writeInt(this.amount);
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<CSPacketSellShopItem, IMessage> {
    @SideOnly(Side.SERVER)
    public IMessage onMessage(CSPacketSellShopItem message, MessageContext ctx) {
      if (message.amount <= 0 || message.item == null)
        return null; 
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      ShopManager.getInstance().sell(player, message.item, message.amount);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\network\CSPacketSellShopItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */