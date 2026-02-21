package fr.paladium.palashop.common.tebex.dto;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class TebexBasketData {
  @SerializedName("ident")
  private String ident;
  
  @SerializedName("username_id")
  private String usernameId;
  
  @SerializedName("username")
  private String username;
  
  @SerializedName("links")
  private Map<String, String> links;
  
  public String toString() {
    return "TebexBasket.TebexBasketData(ident=" + getIdent() + ", usernameId=" + getUsernameId() + ", username=" + getUsername() + ", links=" + getLinks() + ")";
  }
  
  public String getIdent() {
    return this.ident;
  }
  
  public String getUsernameId() {
    return this.usernameId;
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public Map<String, String> getLinks() {
    return this.links;
  }
  
  public String getCheckoutLink() {
    return this.links.get("checkout");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\tebex\dto\TebexBasket$TebexBasketData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */