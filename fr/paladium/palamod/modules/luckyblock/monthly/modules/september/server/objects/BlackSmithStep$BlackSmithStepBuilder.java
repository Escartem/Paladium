package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects;

import java.util.Arrays;
import java.util.List;
import net.minecraft.item.ItemStack;

public class BlackSmithStepBuilder {
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\objects\BlackSmithStep$BlackSmithStepBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */