package fr.paladium.palashop.common.shop.network.home;

import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import java.util.List;

public class BBPacketGetDailyData {
  private final List<ConditionalBuyableObject<IShopItem>> itemList;
  
  public BBPacketGetDailyData(List<ConditionalBuyableObject<IShopItem>> itemList) {
    this.itemList = itemList;
  }
  
  public List<ConditionalBuyableObject<IShopItem>> getItemList() {
    return this.itemList;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\shop\network\home\BBPacketGetDaily$BBPacketGetDailyData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */