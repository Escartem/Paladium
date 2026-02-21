package fr.paladium.palashop.api.server.shop.request.user;

public class ShopBuyRequest {
  private final String uuid;
  
  private final String item;
  
  private final String origin;
  
  private final boolean offer;
  
  public ShopBuyRequest(String uuid, String item, String origin, boolean offer) {
    this.uuid = uuid;
    this.item = item;
    this.origin = origin;
    this.offer = offer;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public String getItem() {
    return this.item;
  }
  
  public String getOrigin() {
    return this.origin;
  }
  
  public boolean isOffer() {
    return this.offer;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\server\shop\reques\\user\ShopBuyRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */