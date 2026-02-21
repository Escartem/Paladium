package fr.paladium.palajobs.core.pojo.rewards;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palajobs.api.reward.IReward;
import fr.paladium.palajobs.api.type.RewardType;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Reward implements IReward {
  private RewardType type;
  
  private Object logo;
  
  private ItemStack rewardItem;
  
  private Double moneyAmount;
  
  private String name;
  
  private int level;
  
  private List<String> hover;
  
  Reward(RewardType type, Object logo, ItemStack rewardItem, Double moneyAmount, String name, int level, List<String> hover) {
    this.hover = new ArrayList<>();
    this.type = type;
    this.logo = logo;
    this.rewardItem = rewardItem;
    this.moneyAmount = moneyAmount;
    this.name = name;
    this.level = level;
    this.hover = hover;
  }
  
  public static RewardBuilder builder() {
    return new RewardBuilder();
  }
  
  public static class RewardBuilder {
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
  
  public void setType(RewardType type) {
    this.type = type;
  }
  
  public void setLogo(Object logo) {
    this.logo = logo;
  }
  
  public void setRewardItem(ItemStack rewardItem) {
    this.rewardItem = rewardItem;
  }
  
  public void setMoneyAmount(Double moneyAmount) {
    this.moneyAmount = moneyAmount;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setLevel(int level) {
    this.level = level;
  }
  
  public void setHover(List<String> hover) {
    this.hover = hover;
  }
  
  public RewardType getType() {
    return this.type;
  }
  
  public Object getLogo() {
    return this.logo;
  }
  
  public ItemStack getRewardItem() {
    return this.rewardItem;
  }
  
  public Double getMoneyAmount() {
    return this.moneyAmount;
  }
  
  public String getName() {
    return this.name;
  }
  
  public int getLevel() {
    return this.level;
  }
  
  public List<String> getHover() {
    return this.hover;
  }
  
  public void giveReward(EntityPlayer player) {
    if (this.type == RewardType.GIVE && this.rewardItem != null) {
      double stackNumber = this.rewardItem.field_77994_a / this.rewardItem.func_77976_d();
      for (; stackNumber > 1.0D; stackNumber--) {
        ItemStack stack = this.rewardItem.func_77946_l();
        stack.field_77994_a = stack.func_77976_d();
        if (!player.field_71071_by.func_70441_a(stack) && !player.field_70170_p.field_72995_K) {
          EntityItem entityitem = new EntityItem(player.field_70170_p, player.field_70165_t, player.field_70163_u + 2.0D, player.field_70161_v, stack);
          entityitem.field_145804_b = 10;
          player.field_70170_p.func_72838_d((Entity)entityitem);
        } 
      } 
      if (stackNumber > 0.0D) {
        ItemStack stack = this.rewardItem.func_77946_l();
        stack.field_77994_a = (int)(this.rewardItem.func_77976_d() * stackNumber);
        if (!player.field_71071_by.func_70441_a(stack) && !player.field_70170_p.field_72995_K) {
          EntityItem entityitem = new EntityItem(player.field_70170_p, player.field_70165_t, player.field_70163_u + 2.0D, player.field_70161_v, stack);
          entityitem.field_145804_b = 10;
          player.field_70170_p.func_72838_d((Entity)entityitem);
        } 
      } 
    } else if (this.type == RewardType.MONEY_GIVE) {
      CresusManager.getInstance().depositPlayerAsync(player.func_110124_au(), this.moneyAmount.doubleValue(), "PalaJobs Lvl " + this.level + " - " + this.moneyAmount + "$", new CresusCallback<CresusResponse>() {
            public void onFail(CresusResponse arg0, Throwable arg1) {}
            
            public void onSuccess(CresusResponse arg0) {}
          });
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\pojo\rewards\Reward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */