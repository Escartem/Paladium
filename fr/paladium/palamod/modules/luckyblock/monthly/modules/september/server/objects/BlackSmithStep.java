package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.PracticeMakesPerfectEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.ChestUtils;
import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;

public class BlackSmithStep {
  private List<ItemStack> items;
  
  private long durationMillis;
  
  private String[] messages;
  
  private List<ItemStack> craftList;
  
  private List<ItemStack> rewardList;
  
  BlackSmithStep(List<ItemStack> items, long durationMillis, String[] messages, List<ItemStack> craftList, List<ItemStack> rewardList) {
    this.items = items;
    this.durationMillis = durationMillis;
    this.messages = messages;
    this.craftList = craftList;
    this.rewardList = rewardList;
  }
  
  public static BlackSmithStepBuilder builder() {
    return new BlackSmithStepBuilder();
  }
  
  public static class BlackSmithStepBuilder {
    private List<ItemStack> items;
    
    private long durationMillis;
    
    private String[] messages;
    
    private List<ItemStack> craftList;
    
    private List<ItemStack> rewardList;
    
    public BlackSmithStepBuilder items(List<ItemStack> items) {
      this.items = items;
      return this;
    }
    
    public BlackSmithStepBuilder durationMillis(long durationMillis) {
      this.durationMillis = durationMillis;
      return this;
    }
    
    public BlackSmithStepBuilder messages(String[] messages) {
      this.messages = messages;
      return this;
    }
    
    public BlackSmithStepBuilder craftList(List<ItemStack> craftList) {
      this.craftList = craftList;
      return this;
    }
    
    public BlackSmithStepBuilder rewardList(List<ItemStack> rewardList) {
      this.rewardList = rewardList;
      return this;
    }
    
    public BlackSmithStep build() {
      return new BlackSmithStep(this.items, this.durationMillis, this.messages, this.craftList, this.rewardList);
    }
    
    public String toString() {
      return "BlackSmithStep.BlackSmithStepBuilder(items=" + this.items + ", durationMillis=" + this.durationMillis + ", messages=" + Arrays.deepToString((Object[])this.messages) + ", craftList=" + this.craftList + ", rewardList=" + this.rewardList + ")";
    }
  }
  
  public List<ItemStack> getItems() {
    return this.items;
  }
  
  public long getDurationMillis() {
    return this.durationMillis;
  }
  
  public String[] getMessages() {
    return this.messages;
  }
  
  public List<ItemStack> getCraftList() {
    return this.craftList;
  }
  
  public List<ItemStack> getRewardList() {
    return this.rewardList;
  }
  
  public void fillChest(TileEntityChest chest) {
    ChestUtils.fillChest(chest, getCopies());
  }
  
  public ItemStack[] getCopies() {
    ItemStack[] copies = new ItemStack[this.items.size()];
    for (int i = 0; i < this.items.size(); i++) {
      ItemStack item = ((ItemStack)this.items.get(i)).func_77946_l();
      PracticeMakesPerfectEvent.INSTANCE.addBlackListedTag(item);
      copies[i] = item;
    } 
    return copies;
  }
  
  public boolean isCorrectCraft(ItemStack stack) {
    for (ItemStack item : this.craftList) {
      if (item.func_77973_b() == stack.func_77973_b() && item.func_77960_j() == stack.func_77960_j())
        return true; 
    } 
    return false;
  }
  
  public void giveRewards(EntityPlayer player) {
    if (this.rewardList == null)
      return; 
    for (ItemStack item : this.rewardList) {
      if (item == null)
        continue; 
      ItemUtils.spawnItemAtEntity((Entity)player, item);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\objects\BlackSmithStep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */