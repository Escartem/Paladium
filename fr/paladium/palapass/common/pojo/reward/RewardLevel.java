package fr.paladium.palapass.common.pojo.reward;

import java.util.List;

public class RewardLevel {
  private String rewardUUID;
  
  private EnumRewardType type;
  
  private String icon;
  
  private int quantity;
  
  private String text;
  
  private int neededPoints;
  
  private List<String> commands;
  
  public void setRewardUUID(String rewardUUID) {
    this.rewardUUID = rewardUUID;
  }
  
  public void setType(EnumRewardType type) {
    this.type = type;
  }
  
  public void setIcon(String icon) {
    this.icon = icon;
  }
  
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  
  public void setText(String text) {
    this.text = text;
  }
  
  public void setNeededPoints(int neededPoints) {
    this.neededPoints = neededPoints;
  }
  
  public void setCommands(List<String> commands) {
    this.commands = commands;
  }
  
  public RewardLevel() {}
  
  public String getRewardUUID() {
    return this.rewardUUID;
  }
  
  public EnumRewardType getType() {
    return this.type;
  }
  
  public String getIcon() {
    return this.icon;
  }
  
  public int getQuantity() {
    return this.quantity;
  }
  
  public String getText() {
    return this.text;
  }
  
  public int getNeededPoints() {
    return this.neededPoints;
  }
  
  public List<String> getCommands() {
    return this.commands;
  }
  
  public RewardLevel(String rewardUUID) {
    this.rewardUUID = rewardUUID;
  }
  
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + ((this.rewardUUID == null) ? 0 : this.rewardUUID.hashCode());
    return result;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    RewardLevel other = (RewardLevel)obj;
    if (this.rewardUUID == null) {
      if (other.rewardUUID != null)
        return false; 
    } else if (!this.rewardUUID.equals(other.rewardUUID)) {
      return false;
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\pojo\reward\RewardLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */