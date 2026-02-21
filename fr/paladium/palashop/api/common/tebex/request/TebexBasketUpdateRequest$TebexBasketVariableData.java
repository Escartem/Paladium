package fr.paladium.palashop.api.common.tebex.request;

import com.google.gson.annotations.SerializedName;

public class TebexBasketVariableData {
  @SerializedName("username_id")
  private final String usernameId;
  
  public TebexBasketVariableData(String usernameId) {
    this.usernameId = usernameId;
  }
  
  public String getUsernameId() {
    return this.usernameId;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\common\tebex\request\TebexBasketUpdateRequest$TebexBasketVariableData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */