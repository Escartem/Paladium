package fr.paladium.palashop.common.shop.network.page;

import fr.paladium.palashop.server.shop.dto.page.ShopPageData;

public class BBPacketGetPageDataData {
  private final ShopPageData data;
  
  public BBPacketGetPageDataData(ShopPageData data) {
    this.data = data;
  }
  
  public ShopPageData getData() {
    return this.data;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\shop\network\page\BBPacketGetPageData$BBPacketGetPageDataData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */