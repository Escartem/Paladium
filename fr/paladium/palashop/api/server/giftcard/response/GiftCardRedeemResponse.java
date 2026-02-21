package fr.paladium.palashop.api.server.giftcard.response;

import fr.paladium.palashop.server.shop.dto.item.IShopItem;

public class GiftCardRedeemResponse {
  private final IShopItem item;
  
  private final Status status;
  
  private final String error;
  
  public String toString() {
    return "GiftCardRedeemResponse(item=" + getItem() + ", status=" + getStatus() + ", error=" + getError() + ")";
  }
  
  public GiftCardRedeemResponse(IShopItem item, Status status, String error) {
    this.item = item;
    this.status = status;
    this.error = error;
  }
  
  public IShopItem getItem() {
    return this.item;
  }
  
  public Status getStatus() {
    return this.status;
  }
  
  public String getError() {
    return this.error;
  }
  
  public static GiftCardRedeemResponse success(IShopItem item) {
    return new GiftCardRedeemResponse(item, Status.SUCCESS, null);
  }
  
  public static GiftCardRedeemResponse status(Status status) {
    return new GiftCardRedeemResponse(null, status, null);
  }
  
  public static GiftCardRedeemResponse error(String error) {
    return new GiftCardRedeemResponse(null, Status.ERROR, error);
  }
  
  public boolean isSuccessful() {
    return (this.status == Status.SUCCESS);
  }
  
  public enum Status {
    SUCCESS, NOT_FOUND, ITEM_NOT_FOUND, USED, EXPIRED, ERROR;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\server\giftcard\response\GiftCardRedeemResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */