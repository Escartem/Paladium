package fr.paladium.palawarzoneevent.module.capture.common.util;

public class CaptureReward {
  private CaptureRewardType type;
  
  public void setType(CaptureRewardType type) {
    this.type = type;
  }
  
  public void setAmount(double amount) {
    this.amount = amount;
  }
  
  public void setItem(String item) {
    this.item = item;
  }
  
  public void setWeight(int weight) {
    this.weight = weight;
  }
  
  public void setGivenEveryMinute(int givenEveryMinute) {
    this.givenEveryMinute = givenEveryMinute;
  }
  
  public String toString() {
    return "CaptureReward(type=" + getType() + ", amount=" + getAmount() + ", item=" + getItem() + ", weight=" + getWeight() + ", givenEveryMinute=" + getGivenEveryMinute() + ")";
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof CaptureReward))
      return false; 
    CaptureReward other = (CaptureReward)o;
    if (!other.canEqual(this))
      return false; 
    if (Double.compare(getAmount(), other.getAmount()) != 0)
      return false; 
    if (getWeight() != other.getWeight())
      return false; 
    if (getGivenEveryMinute() != other.getGivenEveryMinute())
      return false; 
    Object this$type = getType(), other$type = other.getType();
    if ((this$type == null) ? (other$type != null) : !this$type.equals(other$type))
      return false; 
    Object this$item = getItem(), other$item = other.getItem();
    return !((this$item == null) ? (other$item != null) : !this$item.equals(other$item));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof CaptureReward;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    long $amount = Double.doubleToLongBits(getAmount());
    result = result * 59 + (int)($amount >>> 32L ^ $amount);
    result = result * 59 + getWeight();
    result = result * 59 + getGivenEveryMinute();
    Object $type = getType();
    result = result * 59 + (($type == null) ? 43 : $type.hashCode());
    Object $item = getItem();
    return result * 59 + (($item == null) ? 43 : $item.hashCode());
  }
  
  public CaptureReward(CaptureRewardType type, double amount, String item, int weight, int givenEveryMinute) {
    this.type = type;
    this.amount = amount;
    this.item = item;
    this.weight = weight;
    this.givenEveryMinute = givenEveryMinute;
  }
  
  public CaptureRewardType getType() {
    return this.type;
  }
  
  private double amount = 0.0D;
  
  public double getAmount() {
    return this.amount;
  }
  
  private String item = "";
  
  public String getItem() {
    return this.item;
  }
  
  private int weight = 0;
  
  public int getWeight() {
    return this.weight;
  }
  
  private int givenEveryMinute = 1;
  
  public int getGivenEveryMinute() {
    return this.givenEveryMinute;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\capture\commo\\util\CaptureReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */