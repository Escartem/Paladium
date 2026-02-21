package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class IceCreamSellerData {
  private UUID entityUniqueId;
  
  private UUID playerUniqueId;
  
  private int sellAmount;
  
  private IceCreamType firstIceCreamType;
  
  private IceCreamType secondIceCreamType;
  
  public void setEntityUniqueId(UUID entityUniqueId) {
    this.entityUniqueId = entityUniqueId;
  }
  
  public void setPlayerUniqueId(UUID playerUniqueId) {
    this.playerUniqueId = playerUniqueId;
  }
  
  public void setSellAmount(int sellAmount) {
    this.sellAmount = sellAmount;
  }
  
  public void setFirstIceCreamType(IceCreamType firstIceCreamType) {
    this.firstIceCreamType = firstIceCreamType;
  }
  
  public void setSecondIceCreamType(IceCreamType secondIceCreamType) {
    this.secondIceCreamType = secondIceCreamType;
  }
  
  public UUID getEntityUniqueId() {
    return this.entityUniqueId;
  }
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public int getSellAmount() {
    return this.sellAmount;
  }
  
  public IceCreamType getFirstIceCreamType() {
    return this.firstIceCreamType;
  }
  
  public IceCreamType getSecondIceCreamType() {
    return this.secondIceCreamType;
  }
  
  public IceCreamSellerData(Entity entity, EntityPlayerMP player) {
    this.entityUniqueId = entity.func_110124_au();
    this.playerUniqueId = player.func_110124_au();
    this.sellAmount = 0;
  }
  
  public void sell() {
    this.sellAmount++;
    this.firstIceCreamType = null;
    this.secondIceCreamType = null;
  }
  
  public boolean isOwner(EntityPlayerMP player) {
    return this.playerUniqueId.equals(player.func_110124_au());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\objects\IceCreamSellerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */