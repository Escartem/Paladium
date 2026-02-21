package fr.paladium.palashop.provider.cosmetic.common.network;

import fr.paladium.palashop.provider.cosmetic.server.dto.cosmetic.ShopCosmeticData;
import java.util.List;

public class BBPacketGetCosmeticsData {
  private final List<ShopCosmeticData> data;
  
  public BBPacketGetCosmeticsData(List<ShopCosmeticData> data) {
    this.data = data;
  }
  
  public List<ShopCosmeticData> getData() {
    return this.data;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\network\BBPacketGetCosmetics$BBPacketGetCosmeticsData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */