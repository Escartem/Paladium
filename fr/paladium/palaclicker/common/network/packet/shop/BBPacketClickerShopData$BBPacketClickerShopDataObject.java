package fr.paladium.palaclicker.common.network.packet.shop;

import fr.paladium.palaclicker.server.config.shop.dto.ClickerShopItem;
import fr.paladium.palaclicker.server.config.shop.dto.ClickerShopType;
import java.util.List;
import java.util.Map;

public class BBPacketClickerShopDataObject {
  private final Map<ClickerShopType, List<ClickerShopItem>> shopItems;
  
  public BBPacketClickerShopDataObject(Map<ClickerShopType, List<ClickerShopItem>> shopItems) {
    this.shopItems = shopItems;
  }
  
  public Map<ClickerShopType, List<ClickerShopItem>> getShopItems() {
    return this.shopItems;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\common\network\packet\shop\BBPacketClickerShopData$BBPacketClickerShopDataObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */