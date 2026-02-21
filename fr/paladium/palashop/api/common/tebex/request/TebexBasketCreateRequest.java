package fr.paladium.palashop.api.common.tebex.request;

public class TebexBasketCreateRequest {
  private final String username;
  
  public TebexBasketCreateRequest(String username) {
    this.username = username;
  }
  
  public String getUsername() {
    return this.username;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\common\tebex\request\TebexBasketCreateRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */