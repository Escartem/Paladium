package fr.paladium.palashop.common.blackmarket.network;

import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import java.util.List;
import java.util.Objects;

public class BBPacketGetBlackMarketData {
  private final long expiration;
  
  private final List<ConditionalBuyableObject<IShopItem>> items;
  
  public BBPacketGetBlackMarketData(long expiration, List<ConditionalBuyableObject<IShopItem>> items) {
    this.expiration = expiration;
    this.items = items;
  }
  
  public long getExpiration() {
    return this.expiration;
  }
  
  public List<ConditionalBuyableObject<IShopItem>> getItems() {
    return this.items;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.items });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    BBPacketGetBlackMarketData other = (BBPacketGetBlackMarketData)obj;
    return Objects.equals(this.items, other.items);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\blackmarket\network\BBPacketGetBlackMarket$BBPacketGetBlackMarketData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */