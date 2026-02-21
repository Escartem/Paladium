package fr.paladium.palarpg.module.dungeon.common.network.room.merchant;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palarpg.module.dungeon.client.ui.room.merchant.UIDungeonRoomMerchantTrade;
import fr.paladium.palarpg.module.dungeon.common.entity.room.merchant.EntityDungeonMerchant;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

public class SCPacketRPGDungeonRoomMerchantOpen extends ForgePacket {
  private boolean mephisto;
  
  private UIDungeonRoomMerchantTrade.UIDungeonRoomMerchantTradeData data;
  
  public SCPacketRPGDungeonRoomMerchantOpen() {}
  
  public SCPacketRPGDungeonRoomMerchantOpen(boolean mephisto, UIDungeonRoomMerchantTrade.UIDungeonRoomMerchantTradeData data) {
    this.mephisto = mephisto;
    this.data = data;
  }
  
  public void read(ByteBuf buf) {
    super.read(buf);
    this.mephisto = buf.readBoolean();
    int entityId = buf.readInt();
    int tradeCount = buf.readInt();
    EntityDungeonMerchant.IEntityDungeonMerchantTrade[] trades = new EntityDungeonMerchant.IEntityDungeonMerchantTrade[tradeCount];
    for (int i = 0; i < tradeCount; i++) {
      EntityDungeonMerchant.IEntityDungeonMerchantTrade trade = this.mephisto ? (EntityDungeonMerchant.IEntityDungeonMerchantTrade)new EntityDungeonMerchant.EntityDungeonMerchantMephistoTrade() : (EntityDungeonMerchant.IEntityDungeonMerchantTrade)new EntityDungeonMerchant.EntityDungeonMerchantMammonTrade();
      NBTTagCompound tag = ByteBufUtils.readTag(buf);
      trade.readFromNBT(tag);
      trades[i] = trade;
    } 
    this.data = new UIDungeonRoomMerchantTrade.UIDungeonRoomMerchantTradeData(entityId, trades);
  }
  
  public void write(ByteBuf buf) {
    super.write(buf);
    buf.writeBoolean(this.mephisto);
    buf.writeInt(this.data.getEntityId());
    buf.writeInt((this.data.getTrades()).length);
    for (EntityDungeonMerchant.IEntityDungeonMerchantTrade trade : this.data.getTrades()) {
      NBTTagCompound tag = new NBTTagCompound();
      trade.writeToNBT(tag);
      ByteBufUtils.writeTag(buf, tag);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    ZUI.open((UI)new UIDungeonRoomMerchantTrade(this.data));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\room\merchant\SCPacketRPGDungeonRoomMerchantOpen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */