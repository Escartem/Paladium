package fr.paladium.palashop.common.shop.network.home;

import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.offer.ShopOffer;

public class BBPacketGetOffersData {
  private final ConditionalBuyableObject<ShopOffer> monthlyOffer;
  
  private final ConditionalBuyableObject<ShopOffer> currentOffer;
  
  public BBPacketGetOffersData(ConditionalBuyableObject<ShopOffer> monthlyOffer, ConditionalBuyableObject<ShopOffer> currentOffer) {
    this.monthlyOffer = monthlyOffer;
    this.currentOffer = currentOffer;
  }
  
  public ConditionalBuyableObject<ShopOffer> getMonthlyOffer() {
    return this.monthlyOffer;
  }
  
  public ConditionalBuyableObject<ShopOffer> getCurrentOffer() {
    return this.currentOffer;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\shop\network\home\BBPacketGetOffers$BBPacketGetOffersData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */