package fr.paladium.palamod.common.api.dto;

import java.util.UUID;

public class BalanceBuilder {
  private UUID playerUUID;
  
  private double amount;
  
  private String id;
  
  public BalanceBuilder playerUUID(UUID playerUUID) {
    this.playerUUID = playerUUID;
    return this;
  }
  
  public BalanceBuilder amount(double amount) {
    this.amount = amount;
    return this;
  }
  
  public BalanceBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public Balance build() {
    return new Balance(this.playerUUID, this.amount, this.id);
  }
  
  public String toString() {
    return "Balance.BalanceBuilder(playerUUID=" + this.playerUUID + ", amount=" + this.amount + ", id=" + this.id + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\api\dto\Balance$BalanceBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */