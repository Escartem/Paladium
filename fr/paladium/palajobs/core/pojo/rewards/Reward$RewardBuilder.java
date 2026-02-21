package fr.paladium.palajobs.core.pojo.rewards;

import fr.paladium.palajobs.api.type.RewardType;
import java.util.List;
import net.minecraft.item.ItemStack;

public class RewardBuilder {
  private RewardType type;
  
  private Object logo;
  
  private ItemStack rewardItem;
  
  private Double moneyAmount;
  
  private String name;
  
  private int level;
  
  private List<String> hover;
  
  public RewardBuilder type(RewardType type) {
    this.type = type;
    return this;
  }
  
  public RewardBuilder logo(Object logo) {
    this.logo = logo;
    return this;
  }
  
  public RewardBuilder rewardItem(ItemStack rewardItem) {
    this.rewardItem = rewardItem;
    return this;
  }
  
  public RewardBuilder moneyAmount(Double moneyAmount) {
    this.moneyAmount = moneyAmount;
    return this;
  }
  
  public RewardBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public RewardBuilder level(int level) {
    this.level = level;
    return this;
  }
  
  public RewardBuilder hover(List<String> hover) {
    this.hover = hover;
    return this;
  }
  
  public Reward build() {
    return new Reward(this.type, this.logo, this.rewardItem, this.moneyAmount, this.name, this.level, this.hover);
  }
  
  public String toString() {
    return "Reward.RewardBuilder(type=" + this.type + ", logo=" + this.logo + ", rewardItem=" + this.rewardItem + ", moneyAmount=" + this.moneyAmount + ", name=" + this.name + ", level=" + this.level + ", hover=" + this.hover + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\pojo\rewards\Reward$RewardBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */