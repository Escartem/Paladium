package fr.paladium.palajobs.api.reward;

import fr.paladium.palajobs.api.type.RewardType;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IReward {
  void giveReward(EntityPlayer paramEntityPlayer);
  
  RewardType getType();
  
  Object getLogo();
  
  ItemStack getRewardItem();
  
  String getName();
  
  Double getMoneyAmount();
  
  int getLevel();
  
  void setLevel(int paramInt);
  
  List<String> getHover();
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\api\reward\IReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */