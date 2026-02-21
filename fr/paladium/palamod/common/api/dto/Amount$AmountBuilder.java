package fr.paladium.palamod.common.api.dto;

public class AmountBuilder {
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\api\dto\Amount$AmountBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */