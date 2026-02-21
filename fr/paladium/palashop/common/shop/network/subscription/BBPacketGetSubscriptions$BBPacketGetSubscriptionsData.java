package fr.paladium.palashop.common.shop.network.subscription;

import fr.paladium.palashop.server.shop.dto.item.Subscription;
import java.util.List;

public class BBPacketGetSubscriptionsData {
  private final List<Subscription> subscriptionList;
  
  public BBPacketGetSubscriptionsData(List<Subscription> subscriptionList) {
    this.subscriptionList = subscriptionList;
  }
  
  public List<Subscription> getSubscriptionList() {
    return this.subscriptionList;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\shop\network\subscription\BBPacketGetSubscriptions$BBPacketGetSubscriptionsData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */