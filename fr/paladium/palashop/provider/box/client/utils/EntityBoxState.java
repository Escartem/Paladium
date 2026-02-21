package fr.paladium.palashop.provider.box.client.utils;

import fr.paladium.palashop.provider.box.common.dto.box.BoxReward;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;

public class EntityBoxState {
  public BooleanSignal getFast() {
    return this.fast;
  }
  
  public BooleanSignal getWaiting() {
    return this.waiting;
  }
  
  public BooleanSignal getActive() {
    return this.active;
  }
  
  public Signal<String> getPlayer() {
    return this.player;
  }
  
  public ListSignal<BoxReward> getAvailableRewards() {
    return this.availableRewards;
  }
  
  public ListSignal<List<BoxReward>> getRewards() {
    return this.rewards;
  }
  
  public IntegerSignal getCurrentSpin() {
    return this.currentSpin;
  }
  
  public long getTotalWeight() {
    return this.totalWeight;
  }
  
  public long[] getCumulativeWeights() {
    return this.cumulativeWeights;
  }
  
  private final BooleanSignal fast = new BooleanSignal(false);
  
  private final BooleanSignal waiting = new BooleanSignal(false);
  
  private final BooleanSignal active = new BooleanSignal(false);
  
  private final Signal<String> player = new Signal();
  
  private final ListSignal<BoxReward> availableRewards = new ListSignal(new ArrayList());
  
  private final ListSignal<List<BoxReward>> rewards = new ListSignal(new ArrayList());
  
  private final IntegerSignal currentSpin = new IntegerSignal(0);
  
  private long totalWeight;
  
  private long[] cumulativeWeights;
  
  private SecureRandom random;
  
  public void waiting(@NonNull String player, boolean fast, @NonNull List<BoxReward> availableRewards, @NonNull List<List<BoxReward>> rewards, int currentSpin) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (availableRewards == null)
      throw new NullPointerException("availableRewards is marked non-null but is null"); 
    if (rewards == null)
      throw new NullPointerException("rewards is marked non-null but is null"); 
    String oldPlayer = (String)this.player.getOrDefault();
    this.fast.set(Boolean.valueOf(fast));
    this.waiting.set(Boolean.valueOf(true));
    this.active.set(Boolean.valueOf(false));
    this.player.set(player);
    this.availableRewards.set(availableRewards);
    this.rewards.set(rewards);
    this.currentSpin.set(Integer.valueOf(currentSpin));
    if ((oldPlayer == null && this.player.getOrDefault() == null) || (oldPlayer != null && oldPlayer.equals(this.player.getOrDefault())))
      this.player.publish(); 
  }
  
  public void start(@NonNull String player, boolean fast, @NonNull List<BoxReward> availableRewards, @NonNull List<List<BoxReward>> rewards, int currentSpin) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (availableRewards == null)
      throw new NullPointerException("availableRewards is marked non-null but is null"); 
    if (rewards == null)
      throw new NullPointerException("rewards is marked non-null but is null"); 
    String oldPlayer = (String)this.player.getOrDefault();
    this.fast.set(Boolean.valueOf(fast));
    this.waiting.set(Boolean.valueOf(false));
    this.active.set(Boolean.valueOf(true));
    this.player.set(player);
    this.availableRewards.set(availableRewards);
    this.rewards.set(rewards);
    this.currentSpin.set(Integer.valueOf(currentSpin));
    if ((oldPlayer == null && this.player.getOrDefault() == null) || (oldPlayer != null && oldPlayer.equals(this.player.getOrDefault())))
      this.player.publish(); 
  }
  
  public void stop() {
    String oldPlayer = (String)this.player.getOrDefault();
    this.fast.set(Boolean.valueOf(false));
    this.waiting.set(Boolean.valueOf(false));
    this.active.set(Boolean.valueOf(false));
    this.player.set(null);
    this.availableRewards.set(new ArrayList());
    this.rewards.set(new ArrayList());
    this.currentSpin.set(Integer.valueOf(0));
    if ((oldPlayer == null && this.player.getOrDefault() == null) || (oldPlayer != null && oldPlayer.equals(this.player.getOrDefault())))
      this.player.publish(); 
  }
  
  public boolean isFast() {
    return ((Boolean)this.fast.getOrDefault()).booleanValue();
  }
  
  public boolean isWaiting() {
    return ((Boolean)this.waiting.getOrDefault()).booleanValue();
  }
  
  public boolean isActive() {
    return ((Boolean)this.active.getOrDefault()).booleanValue();
  }
  
  public int getSpin() {
    return this.rewards.size();
  }
  
  public int getCount(int spin) {
    return ((List)this.rewards.get(spin)).size();
  }
  
  @NonNull
  public BoxReward getRandom() {
    if (this.random == null || this.cumulativeWeights == null || this.totalWeight <= 0L || this.cumulativeWeights.length != this.availableRewards.size())
      load(); 
    return (BoxReward)this.availableRewards.get(binarySearch((long)(this.random.nextDouble() * this.totalWeight)));
  }
  
  @NonNull
  public BoxReward getRandom(@NonNull BoxReward.Type type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    if (this.random == null || this.cumulativeWeights == null || this.totalWeight <= 0L || this.cumulativeWeights.length != this.availableRewards.size())
      load(); 
    BoxReward reward = (BoxReward)this.availableRewards.get(binarySearch((long)(this.random.nextDouble() * this.totalWeight)));
    while (reward.getType() != type)
      reward = (BoxReward)this.availableRewards.get(binarySearch((long)(this.random.nextDouble() * this.totalWeight))); 
    return reward;
  }
  
  private int binarySearch(long value) {
    int low = 0;
    int high = this.cumulativeWeights.length - 1;
    while (low < high) {
      int mid = low + (high - low >>> 1);
      if (value < this.cumulativeWeights[mid]) {
        high = mid;
        continue;
      } 
      low = mid + 1;
    } 
    return low;
  }
  
  private void load() {
    this.cumulativeWeights = new long[this.availableRewards.size()];
    this.random = new SecureRandom();
    long cumulative = 0L;
    for (int i = 0; i < this.availableRewards.size(); i++) {
      cumulative += ((BoxReward)this.availableRewards.get(i)).getWeight().intValue();
      this.cumulativeWeights[i] = cumulative;
    } 
    if (cumulative <= 0L)
      throw new IllegalArgumentException("Total weight must be positive"); 
    this.totalWeight = cumulative;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\clien\\utils\EntityBoxState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */