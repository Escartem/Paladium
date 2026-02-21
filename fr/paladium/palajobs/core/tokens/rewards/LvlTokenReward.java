package fr.paladium.palajobs.core.tokens.rewards;

import fr.paladium.palajobs.api.type.JobType;
import net.minecraft.item.ItemStack;

public class LvlTokenReward {
  public int lvl;
  
  public String id;
  
  public String name;
  
  public EnumLvlTokenRewardCategory category;
  
  public EnumLvlTokenRewardRarity rarity;
  
  public ItemStack itemStack;
  
  public int quantity;
  
  public JobType job;
  
  public LvlTokenReward(int lvl, String id, String name, EnumLvlTokenRewardCategory category, EnumLvlTokenRewardRarity rarity, ItemStack itemStack, int quantity, JobType job) {
    this.lvl = lvl;
    this.id = id;
    this.name = name;
    this.category = category;
    this.rarity = rarity;
    this.itemStack = itemStack;
    this.quantity = quantity;
    this.job = job;
  }
  
  public String toString() {
    return "LvlTokenReward [id=" + this.id + "]";
  }
  
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + ((this.id == null) ? 0 : this.id.hashCode());
    return result;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null)
      return false; 
    if (getClass() != obj.getClass())
      return false; 
    LvlTokenReward other = (LvlTokenReward)obj;
    if (this.id == null) {
      if (other.id != null)
        return false; 
    } else if (!this.id.equals(other.id)) {
      return false;
    } 
    return true;
  }
  
  public LvlTokenReward(String id) {
    this.id = id;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\tokens\rewards\LvlTokenReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */