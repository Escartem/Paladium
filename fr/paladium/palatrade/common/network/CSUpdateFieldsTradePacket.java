package fr.paladium.palatrade.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palatrade.common.container.ContainerTrade;
import fr.paladium.palatrade.common.utils.Trade;
import fr.paladium.palatrade.lib.odin.modules.packet.OdinPacketModule;
import fr.paladium.palatrade.lib.odin.modules.packet.lib.ForgePacket;
import fr.paladium.palatrade.server.manager.TradeManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSUpdateFieldsTradePacket extends ForgePacket {
  private boolean teleport;
  
  private double xp;
  
  private double money;
  
  private double pb;
  
  public CSUpdateFieldsTradePacket() {}
  
  public CSUpdateFieldsTradePacket(boolean teleport, double xp, double money, double pb) {
    this.teleport = teleport;
    this.xp = xp;
    this.money = money;
    this.pb = pb;
  }
  
  public void write(ByteBuf buf) {
    writeBoolean(buf, this.teleport);
    writeDouble(buf, this.xp);
    writeDouble(buf, this.money);
    writeDouble(buf, this.pb);
  }
  
  public void read(ByteBuf buf) {
    this.teleport = readBoolean(buf);
    this.xp = readDouble(buf);
    this.money = readDouble(buf);
    this.pb = readDouble(buf);
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    Trade trade = TradeManager.getInstance().getTrade((EntityPlayer)player);
    EntityPlayer target = TradeManager.getInstance().getTradePlayer((EntityPlayer)player);
    Trade tradeTarget = TradeManager.getInstance().getTrade(target);
    if (trade == null || tradeTarget == null || !(target instanceof EntityPlayerMP) || trade.isPlayerValidated() || trade.isProcessing() || tradeTarget.isProcessing())
      return; 
    this.xp = Math.max(0.0D, this.xp);
    this.money = Math.max(0.0D, this.money);
    this.pb = Math.max(0.0D, this.pb);
    this.xp = Math.min(trade.getMaxXp(), this.xp);
    this.money = Math.min(trade.getMaxMoney(), this.money);
    this.pb = Math.min(trade.getMaxPb(), this.pb);
    if (trade.getMaxPb() >= 10.0D && this.pb > 0.0D && this.pb < 10.0D)
      this.pb = 10.0D; 
    trade.setTeleport(this.teleport);
    trade.setXp(this.xp);
    trade.setMoney(this.money);
    trade.setPb(this.pb);
    tradeTarget.setLastEdit(System.currentTimeMillis());
    if (tradeTarget.isPlayerValidated()) {
      tradeTarget.setPlayerValidated(false);
      ((ContainerTrade)target.field_71070_bA).setValidated(false);
      OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCUnvalidateTargetTradePacket(), player);
      OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCUnvalidateTradePacket("Le trade a été modifié", 50, false), (EntityPlayerMP)target);
    } 
    OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCUpdateFieldsTradePacket(this.teleport, this.xp, this.money, this.pb, true), player);
    OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCUpdateFieldsTradePacket(this.teleport, this.xp, this.money, this.pb, false), (EntityPlayerMP)target);
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\common\network\CSUpdateFieldsTradePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */