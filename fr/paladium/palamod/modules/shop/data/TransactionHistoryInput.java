package fr.paladium.palamod.modules.shop.data;

public class TransactionHistoryInput {
  public String playerName;
  
  public String playerUUID;
  
  public String item;
  
  public int amount;
  
  public double price;
  
  public long date;
  
  public boolean buy;
  
  public TransactionHistoryInput() {}
  
  public TransactionHistoryInput(String playerName, String playerUUID, String item, int amount, double price, long date, boolean buy) {
    this.playerName = playerName;
    this.playerUUID = playerUUID;
    this.item = item;
    this.amount = amount;
    this.price = price;
    this.date = date;
    this.buy = buy;
  }
  
  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }
  
  public void setPlayerUUID(String playerUUID) {
    this.playerUUID = playerUUID;
  }
  
  public void setItem(String item) {
    this.item = item;
  }
  
  public void setAmount(int amount) {
    this.amount = amount;
  }
  
  public void setPrice(double price) {
    this.price = price;
  }
  
  public void setDate(long date) {
    this.date = date;
  }
  
  public void setBuy(boolean buy) {
    this.buy = buy;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof TransactionHistoryInput))
      return false; 
    TransactionHistoryInput other = (TransactionHistoryInput)o;
    if (!other.canEqual(this))
      return false; 
    if (getAmount() != other.getAmount())
      return false; 
    if (Double.compare(getPrice(), other.getPrice()) != 0)
      return false; 
    if (getDate() != other.getDate())
      return false; 
    if (isBuy() != other.isBuy())
      return false; 
    Object this$playerName = getPlayerName(), other$playerName = other.getPlayerName();
    if ((this$playerName == null) ? (other$playerName != null) : !this$playerName.equals(other$playerName))
      return false; 
    Object this$playerUUID = getPlayerUUID(), other$playerUUID = other.getPlayerUUID();
    if ((this$playerUUID == null) ? (other$playerUUID != null) : !this$playerUUID.equals(other$playerUUID))
      return false; 
    Object this$item = getItem(), other$item = other.getItem();
    return !((this$item == null) ? (other$item != null) : !this$item.equals(other$item));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof TransactionHistoryInput;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + getAmount();
    long $price = Double.doubleToLongBits(getPrice());
    result = result * 59 + (int)($price >>> 32L ^ $price);
    long $date = getDate();
    result = result * 59 + (int)($date >>> 32L ^ $date);
    result = result * 59 + (isBuy() ? 79 : 97);
    Object $playerName = getPlayerName();
    result = result * 59 + (($playerName == null) ? 43 : $playerName.hashCode());
    Object $playerUUID = getPlayerUUID();
    result = result * 59 + (($playerUUID == null) ? 43 : $playerUUID.hashCode());
    Object $item = getItem();
    return result * 59 + (($item == null) ? 43 : $item.hashCode());
  }
  
  public String toString() {
    return "TransactionHistoryInput(playerName=" + getPlayerName() + ", playerUUID=" + getPlayerUUID() + ", item=" + getItem() + ", amount=" + getAmount() + ", price=" + getPrice() + ", date=" + getDate() + ", buy=" + isBuy() + ")";
  }
  
  public String getPlayerName() {
    return this.playerName;
  }
  
  public String getPlayerUUID() {
    return this.playerUUID;
  }
  
  public String getItem() {
    return this.item;
  }
  
  public int getAmount() {
    return this.amount;
  }
  
  public double getPrice() {
    return this.price;
  }
  
  public long getDate() {
    return this.date;
  }
  
  public boolean isBuy() {
    return this.buy;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\data\TransactionHistoryInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */