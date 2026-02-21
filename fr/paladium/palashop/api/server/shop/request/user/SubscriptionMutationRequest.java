package fr.paladium.palashop.api.server.shop.request.user;

import fr.paladium.palashop.server.shop.dto.item.Subscription;

public class SubscriptionMutationRequest {
  private final String uuid;
  
  private final String itemId;
  
  private final Subscription.Status status;
  
  public SubscriptionMutationRequest(String uuid, String itemId, Subscription.Status status) {
    this.uuid = uuid;
    this.itemId = itemId;
    this.status = status;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public String getItemId() {
    return this.itemId;
  }
  
  public Subscription.Status getStatus() {
    return this.status;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\server\shop\reques\\user\SubscriptionMutationRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */