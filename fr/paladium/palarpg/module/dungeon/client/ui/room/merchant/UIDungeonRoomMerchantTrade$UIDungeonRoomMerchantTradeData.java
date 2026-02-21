package fr.paladium.palarpg.module.dungeon.client.ui.room.merchant;

import fr.paladium.palarpg.module.dungeon.common.entity.room.merchant.EntityDungeonMerchant;

public class UIDungeonRoomMerchantTradeData {
  private final int entityId;
  
  private final EntityDungeonMerchant.IEntityDungeonMerchantTrade[] trades;
  
  public UIDungeonRoomMerchantTradeData(int entityId, EntityDungeonMerchant.IEntityDungeonMerchantTrade[] trades) {
    this.entityId = entityId;
    this.trades = trades;
  }
  
  public int getEntityId() {
    return this.entityId;
  }
  
  public EntityDungeonMerchant.IEntityDungeonMerchantTrade[] getTrades() {
    return this.trades;
  }
  
  public boolean isMephisto() {
    return (this.trades.length > 0 && this.trades[0] instanceof EntityDungeonMerchant.EntityDungeonMerchantMephistoTrade);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\room\merchant\UIDungeonRoomMerchantTrade$UIDungeonRoomMerchantTradeData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */