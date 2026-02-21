package fr.paladium.palashop.common.shop.network.page;

import fr.paladium.palashop.server.shop.dto.page.ShopPage;
import java.util.List;

public class BBPacketGetPagesData {
  private final List<ShopPage> pageList;
  
  public BBPacketGetPagesData(List<ShopPage> pageList) {
    this.pageList = pageList;
  }
  
  public List<ShopPage> getPageList() {
    return this.pageList;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\shop\network\page\BBPacketGetPages$BBPacketGetPagesData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */