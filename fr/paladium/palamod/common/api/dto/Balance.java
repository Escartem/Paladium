package fr.paladium.palamod.common.api.dto;

import com.google.gson.annotations.SerializedName;
import java.util.UUID;

public class Balance {
  @SerializedName("playerUUID")
  private UUID playerUUID;
  
  @SerializedName("amount")
  private double amount;
  
  @SerializedName("id")
  private String id;
  
  public void setPlayerUUID(UUID playerUUID) {
    this.playerUUID = playerUUID;
  }
  
  public void setAmount(double amount) {
    this.amount = amount;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof Balance))
      return false; 
    Balance other = (Balance)o;
    if (!other.canEqual(this))
      return false; 
    if (Double.compare(getAmount(), other.getAmount()) != 0)
      return false; 
    Object this$playerUUID = getPlayerUUID(), other$playerUUID = other.getPlayerUUID();
    if ((this$playerUUID == null) ? (other$playerUUID != null) : !this$playerUUID.equals(other$playerUUID))
      return false; 
    Object this$id = getId(), other$id = other.getId();
    return !((this$id == null) ? (other$id != null) : !this$id.equals(other$id));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof Balance;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    long $amount = Double.doubleToLongBits(getAmount());
    result = result * 59 + (int)($amount >>> 32L ^ $amount);
    Object $playerUUID = getPlayerUUID();
    result = result * 59 + (($playerUUID == null) ? 43 : $playerUUID.hashCode());
    Object $id = getId();
    return result * 59 + (($id == null) ? 43 : $id.hashCode());
  }
  
  public String toString() {
    return "Balance(playerUUID=" + getPlayerUUID() + ", amount=" + getAmount() + ", id=" + getId() + ")";
  }
  
  Balance(UUID playerUUID, double amount, String id) {
    this.playerUUID = playerUUID;
    this.amount = amount;
    this.id = id;
  }
  
  public static BalanceBuilder builder() {
    return new BalanceBuilder();
  }
  
  public static class BalanceBuilder {
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
  
  public UUID getPlayerUUID() {
    return this.playerUUID;
  }
  
  public double getAmount() {
    return this.amount;
  }
  
  public String getId() {
    return this.id;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\api\dto\Balance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */