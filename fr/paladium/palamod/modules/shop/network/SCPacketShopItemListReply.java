package fr.paladium.palamod.modules.shop.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.utils.Packet;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import fr.paladium.palamod.modules.shop.client.ui.UIShop;
import fr.paladium.palamod.modules.shop.data.ShopItem;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import io.netty.buffer.ByteBuf;
import java.util.LinkedHashMap;
import java.util.Map;

@Packet(side = Side.CLIENT)
public class SCPacketShopItemListReply implements IMessage {
  private short itemCount;
  
  private Map<String, ShopItem> items;
  
  private double money;
  
  public SCPacketShopItemListReply(short itemCount, Map<String, ShopItem> items, double money) {
    this.items = new LinkedHashMap<>();
    this.money = 0.0D;
    this.itemCount = itemCount;
    this.items = items;
    this.money = money;
  }
  
  public SCPacketShopItemListReply() {
    this.items = new LinkedHashMap<>();
    this.money = 0.0D;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.items.clear();
    this.itemCount = buf.readShort();
    for (short itr = 0; itr < this.itemCount; itr = (short)(itr + 1)) {
      ShopItem price = ShopItem.parseFromBuffer(buf);
      this.items.put(price.getItem(), price);
    } 
    this.money = buf.readDouble();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeShort((short)this.items.size());
    for (ShopItem price : this.items.values())
      price.writeToBuffer(buf); 
    buf.writeDouble(this.money);
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<SCPacketShopItemListReply, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketShopItemListReply message, MessageContext ctx) {
      UIShop ui = (UIShop)ZUI.getUI(UIShop.class);
      if (ui != null) {
        ui.putItems(message.items);
        ui.setMoney(message.money);
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\network\SCPacketShopItemListReply.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */