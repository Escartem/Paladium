package fr.paladium.palashop.api.server.shop.response.user;

import fr.paladium.palashop.server.shop.dto.item.Subscription;

public class SubscriptionMutationResponse {
  private final String uuid;
  
  private final String itemId;
  
  private final Subscription subscription;
  
  private final Status status;
  
  private final String error;
  
  public String toString() {
    return "SubscriptionMutationResponse(uuid=" + getUuid() + ", itemId=" + getItemId() + ", subscription=" + getSubscription() + ", status=" + getStatus() + ", error=" + getError() + ")";
  }
  
  public SubscriptionMutationResponse(String uuid, String itemId, Subscription subscription, Status status, String error) {
    this.uuid = uuid;
    this.itemId = itemId;
    this.subscription = subscription;
    this.status = status;
    this.error = error;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public String getItemId() {
    return this.itemId;
  }
  
  public Subscription getSubscription() {
    return this.subscription;
  }
  
  public Status getStatus() {
    return this.status;
  }
  
  public String getError() {
    return this.error;
  }
  
  public boolean isSuccessful() {
    return (this.status == Status.SUCCESS);
  }
  
  public boolean isErrored() {
    return (this.status != Status.SUCCESS);
  }
  
  public enum Status {
    SUCCESS, ERROR, NOT_FOUND, ALREADY_ACTIVE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\server\shop\respons\\user\SubscriptionMutationResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */