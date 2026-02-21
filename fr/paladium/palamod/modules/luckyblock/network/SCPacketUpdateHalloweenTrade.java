package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.config.ClientHalloweenTradeConfig;
import fr.paladium.palamod.modules.luckyblock.gui.halloween.UIHalloweenTrade;
import fr.paladium.palamod.modules.luckyblock.utils.HalloweenConfig;
import fr.paladium.palamod.modules.luckyblock.utils.HalloweenTradeConfig;
import fr.paladium.palamod.modules.luckyblock.utils.HalloweenTradeItemConfig;
import fr.paladium.palamod.modules.paladium.utils.Packet;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

@Packet(side = Side.CLIENT)
public class SCPacketUpdateHalloweenTrade implements IMessage {
  private String cosmeticName;
  
  private String cosmeticImage;
  
  private int remainingCosmetics;
  
  private int cosmeticCost;
  
  public int tradeAmount;
  
  public HalloweenTradeConfig trade;
  
  public SCPacketUpdateHalloweenTrade() {}
  
  public SCPacketUpdateHalloweenTrade(HalloweenConfig config, int tradeAmount) {
    this.cosmeticName = config.getCosmeticName();
    this.cosmeticImage = config.getCosmeticImage();
    this.remainingCosmetics = config.getCosmeticRemainings();
    this.cosmeticCost = config.getCosmeticCost();
    this.trade = config.getCurrentTrade();
    this.tradeAmount = tradeAmount;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.cosmeticName = ByteBufUtils.readUTF8String(buf);
    this.cosmeticImage = ByteBufUtils.readUTF8String(buf);
    this.remainingCosmetics = buf.readInt();
    this.cosmeticCost = buf.readInt();
    this.tradeAmount = buf.readInt();
    if (buf.isReadable()) {
      String uuid = ByteBufUtils.readUTF8String(buf);
      long start = buf.readLong();
      long end = buf.readLong();
      int maxTrade = buf.readInt();
      HalloweenTradeItemConfig itemConfig = new HalloweenTradeItemConfig(buf.readInt(), buf.readByte(), buf.readInt());
      this.trade = new HalloweenTradeConfig(uuid, start, end, maxTrade, itemConfig);
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.cosmeticName);
    ByteBufUtils.writeUTF8String(buf, this.cosmeticImage);
    buf.writeInt(this.remainingCosmetics);
    buf.writeInt(this.cosmeticCost);
    buf.writeInt(this.tradeAmount);
    if (this.trade != null) {
      ByteBufUtils.writeUTF8String(buf, this.trade.getUuid());
      buf.writeLong(this.trade.getStart());
      buf.writeLong(this.trade.getEnd());
      buf.writeInt(this.trade.getMaxTrade());
      buf.writeInt(this.trade.getInput().getId());
      buf.writeByte(this.trade.getInput().getMeta());
      buf.writeInt(this.trade.getInput().getAmount());
    } 
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<SCPacketUpdateHalloweenTrade, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketUpdateHalloweenTrade message, MessageContext ctx) {
      ClientProxy.configHalloween = new ClientHalloweenTradeConfig(message.trade, message.tradeAmount, message.cosmeticName, message.cosmeticImage, message.remainingCosmetics, message.cosmeticCost);
      if ((Minecraft.func_71410_x()).field_71462_r instanceof UIHalloweenTrade)
        ((UIHalloweenTrade)(Minecraft.func_71410_x()).field_71462_r).setConfig(ClientProxy.configHalloween); 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\SCPacketUpdateHalloweenTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */