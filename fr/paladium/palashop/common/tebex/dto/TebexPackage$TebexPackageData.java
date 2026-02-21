package fr.paladium.palashop.common.tebex.dto;

import com.google.gson.annotations.SerializedName;

public class TebexPackageData {
  @SerializedName("id")
  private int id;
  
  @SerializedName("name")
  private String name;
  
  @SerializedName("description")
  private String description;
  
  @SerializedName("type")
  private String type;
  
  @SerializedName("total_price")
  private double totalPrice;
  
  @SerializedName("currency")
  private String currency;
  
  public String toString() {
    return "TebexPackage.TebexPackageData(id=" + getId() + ", name=" + getName() + ", description=" + getDescription() + ", type=" + getType() + ", totalPrice=" + getTotalPrice() + ", currency=" + getCurrency() + ")";
  }
  
  public int getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public String getType() {
    return this.type;
  }
  
  public double getTotalPrice() {
    return this.totalPrice;
  }
  
  public String getCurrency() {
    return this.currency;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\tebex\dto\TebexPackage$TebexPackageData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */