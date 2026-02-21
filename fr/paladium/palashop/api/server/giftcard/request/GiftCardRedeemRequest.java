package fr.paladium.palashop.api.server.giftcard.request;

public class GiftCardRedeemRequest {
  private final String code;
  
  private final String uuid;
  
  public String toString() {
    return "GiftCardRedeemRequest(code=" + getCode() + ", uuid=" + getUuid() + ")";
  }
  
  public GiftCardRedeemRequest(String code, String uuid) {
    this.code = code;
    this.uuid = uuid;
  }
  
  public String getCode() {
    return this.code;
  }
  
  public String getUuid() {
    return this.uuid;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\server\giftcard\request\GiftCardRedeemRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */