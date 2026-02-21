package fr.paladium.palashop.provider.cosmetic.common.network;

import fr.paladium.palashop.server.shop.dto.ShopRarity;
import java.util.Map;

public class BBPacketGetCosmeticsCountData {
  private final Map<ShopRarity, Integer> data;
  
  public BBPacketGetCosmeticsCountData(Map<ShopRarity, Integer> data) {
    this.data = data;
  }
  
  public Map<ShopRarity, Integer> getData() {
    return this.data;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\network\BBPacketGetCosmeticsCount$BBPacketGetCosmeticsCountData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */