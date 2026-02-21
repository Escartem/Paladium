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
import fr.paladium.palamod.modules.shop.data.ItemCategory;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

@Packet(side = Side.SERVER)
public class CSPacketShopItemListRequest implements IMessage {
  private ItemCategory category;
  
  private int at;
  
  private String search;
  
  public CSPacketShopItemListRequest(ItemCategory category, int at, String search) {
    this.category = category;
    this.at = at;
    this.search = search;
  }
  
  public CSPacketShopItemListRequest() {}
  
  public void fromBytes(ByteBuf buf) {
    try {
      this.category = ItemCategory.values()[buf.readInt()];
    } catch (Exception exception) {
      this.category = ItemCategory.ALL;
    } 
    this.at = buf.readInt();
    if (buf.isReadable())
      this.search = ByteBufUtils.readUTF8String(buf); 
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.category.ordinal());
    buf.writeInt(this.at);
    if (this.search != null)
      ByteBufUtils.writeUTF8String(buf, this.search); 
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<CSPacketShopItemListRequest, IMessage> {
    @SideOnly(Side.SERVER)
    public IMessage onMessage(CSPacketShopItemListRequest message, MessageContext ctx) {
      if (message.category == null || message.at < 0)
        return null; 
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      ShopManager.getInstance().sendItems(player, message.category, message.at, message.search);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\network\CSPacketShopItemListRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */