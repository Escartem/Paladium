package fr.paladium.palajobs.client.ui.reward.node;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftHexaLevelNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palajobs.api.reward.IReward;
import fr.paladium.palajobs.api.type.RewardType;
import fr.paladium.palajobs.client.ui.node.RewardNode;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class JobRewardLevelNode extends AClickableNode {
  private static final Color COLOR = new Color(0.0F, 0.0F, 0.0F, 0.8F);
  
  private static final Color LOCKED_COLOR = new Color(0.0F, 0.0F, 0.0F, 0.3F);
  
  private final boolean locked;
  
  private final int level;
  
  private final List<IReward> rewards;
  
  public boolean isLocked() {
    return this.locked;
  }
  
  public int getLevel() {
    return this.level;
  }
  
  public List<IReward> getRewards() {
    return this.rewards;
  }
  
  public JobRewardLevelNode(double x, double y, double width, double height, boolean locked, int level, List<IReward> rewards) {
    super(x, y, width, height);
    this.locked = locked;
    this.level = level;
    this.rewards = rewards;
    addChild((new MinecraftHexaLevelNode(x + width(2.2F), y + height / 2.0D - width(1.75F), width(3.5F))).setValue(this.level).setEnabled(!this.locked));
    AtomicInteger index = new AtomicInteger(0);
    this.rewards.stream().filter(reward -> (reward.getType() == RewardType.GIVE || reward.getType() == RewardType.MONEY_GIVE)).limit(7L).forEach(reward -> {
          addChild((ANode)new RewardNode((float)(x + width(15.0F) + (width(3.0F) * index.get())), (float)(y + height(26.0F)), width(2.0F), width(2.0F), reward));
          index.getAndIncrement();
        });
    index.set(0);
    this.rewards.stream().filter(reward -> (reward.getType() == RewardType.CRAFT)).limit(10L).forEach(reward -> {
          addChild((ANode)new RewardNode((float)(x + width(37.0F) + (width(3.0F) * index.get())), (float)(y + height(26.0F)), width(2.0F), width(2.0F), reward));
          index.getAndIncrement();
        });
    index.set(0);
    this.rewards.stream().filter(reward -> (reward.getType() == RewardType.USAGE)).limit(9L).forEach(reward -> {
          addChild((ANode)new RewardNode((float)(x + width(70.5D) + (width(3.0F) * index.get())), (float)(y + height(26.0F)), width(2.0F), width(2.0F), reward));
          index.getAndIncrement();
        });
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    if (this.height == 0.0D)
      return; 
    GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, this.locked ? LOCKED_COLOR : COLOR);
  }
  
  public boolean match(String search) {
    search = search.toLowerCase();
    for (IReward reward : this.rewards) {
      if (reward.getRewardItem() != null && (
        reward.getRewardItem().func_82833_r().toLowerCase().contains(search) || reward.getRewardItem().func_77977_a().toLowerCase().contains(search)))
        return true; 
      if (reward.getLogo() instanceof ItemStack && (
        (ItemStack)reward.getLogo()).func_77973_b() != null && (((ItemStack)reward.getLogo()).func_82833_r().toLowerCase().contains(search) || ((ItemStack)reward.getLogo()).func_77977_a().toLowerCase().contains(search)))
        return true; 
      if (reward.getName() != null && reward.getName().toLowerCase().contains(search))
        return true; 
    } 
    return search.equals(String.valueOf(this.level));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\reward\node\JobRewardLevelNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */