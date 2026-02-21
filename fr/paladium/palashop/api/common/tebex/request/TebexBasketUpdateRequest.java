package fr.paladium.palashop.api.common.tebex.request;

import com.google.gson.annotations.SerializedName;

public class TebexBasketUpdateRequest {
  @SerializedName("package_id")
  private final int packageId;
  
  @SerializedName("quantity")
  private final int quantity;
  
  @SerializedName("variable_data")
  private final TebexBasketVariableData variableData;
  
  public TebexBasketUpdateRequest(int packageId, int quantity, TebexBasketVariableData variableData) {
    this.packageId = packageId;
    this.quantity = quantity;
    this.variableData = variableData;
  }
  
  public int getPackageId() {
    return this.packageId;
  }
  
  public int getQuantity() {
    return this.quantity;
  }
  
  public TebexBasketVariableData getVariableData() {
    return this.variableData;
  }
  
  public static class TebexBasketVariableData {
    @SerializedName("username_id")
    private final String usernameId;
    
    public TebexBasketVariableData(String usernameId) {
      this.usernameId = usernameId;
    }
    
    public String getUsernameId() {
      return this.usernameId;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\common\tebex\request\TebexBasketUpdateRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */