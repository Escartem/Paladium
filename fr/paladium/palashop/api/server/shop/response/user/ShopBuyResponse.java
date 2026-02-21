package fr.paladium.palashop.api.server.shop.response.user;

import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import java.util.List;

public class ShopBuyResponse {
  private final String uuid;
  
  private final String item;
  
  private final boolean offer;
  
  private final long balance;
  
  private final Status status;
  
  private final List<IShopItem> items;
  
  private final String error;
  
  public String toString() {
    return "ShopBuyResponse(uuid=" + getUuid() + ", item=" + getItem() + ", offer=" + isOffer() + ", balance=" + getBalance() + ", status=" + getStatus() + ", items=" + getItems() + ", error=" + getError() + ")";
  }
  
  public ShopBuyResponse(String uuid, String item, boolean offer, long balance, Status status, List<IShopItem> items, String error) {
    this.uuid = uuid;
    this.item = item;
    this.offer = offer;
    this.balance = balance;
    this.status = status;
    this.items = items;
    this.error = error;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public String getItem() {
    return this.item;
  }
  
  public boolean isOffer() {
    return this.offer;
  }
  
  public long getBalance() {
    return this.balance;
  }
  
  public Status getStatus() {
    return this.status;
  }
  
  public List<IShopItem> getItems() {
    return this.items;
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
  
  public boolean hasError() {
    return (this.error != null && !this.error.isEmpty());
  }
  
  public enum Status {
    SUCCESS, CONDITIONS_FAILED, NOT_ENOUGH_MONEY, ITEM_NOT_FOUND, ITEM_NOT_AVAILABLE, ITEM_ALREADY_OWNED, ERROR;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\server\shop\respons\\user\ShopBuyResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */