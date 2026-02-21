package fr.paladium.palamod.common.api.dto;

import com.google.gson.annotations.SerializedName;

public class Amount {
  @SerializedName("amount")
  public double amount;
  
  public void setAmount(double amount) {
    this.amount = amount;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof Amount))
      return false; 
    Amount other = (Amount)o;
    return !other.canEqual(this) ? false : (!(Double.compare(getAmount(), other.getAmount()) != 0));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof Amount;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    long $amount = Double.doubleToLongBits(getAmount());
    return result * 59 + (int)($amount >>> 32L ^ $amount);
  }
  
  public String toString() {
    return "Amount(amount=" + getAmount() + ")";
  }
  
  Amount(double amount) {
    this.amount = amount;
  }
  
  public static AmountBuilder builder() {
    return new AmountBuilder();
  }
  
  public static class AmountBuilder {
    private double amount;
    
    public AmountBuilder amount(double amount) {
      this.amount = amount;
      return this;
    }
    
    public Amount build() {
      return new Amount(this.amount);
    }
    
    public String toString() {
      return "Amount.AmountBuilder(amount=" + this.amount + ")";
    }
  }
  
  public double getAmount() {
    return this.amount;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\api\dto\Amount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */