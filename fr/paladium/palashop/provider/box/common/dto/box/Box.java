package fr.paladium.palashop.provider.box.common.dto.box;

import fr.paladium.palashop.provider.box.common.manager.BoxManager;
import fr.paladium.permissionbridge.common.data.PermissibleEntity;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class Box {
  private String id;
  
  private String description;
  
  private String marketing;
  
  private Integer number;
  
  private List<BoxReward> rewards;
  
  private long totalWeight;
  
  private long[] cumulativeWeights;
  
  private SecureRandom random;
  
  public String toString() {
    return "Box(id=" + getId() + ", description=" + getDescription() + ", marketing=" + getMarketing() + ", number=" + getNumber() + ", rewards=" + getRewards() + ", totalWeight=" + getTotalWeight() + ", cumulativeWeights=" + Arrays.toString(getCumulativeWeights()) + ", random=" + getRandom() + ")";
  }
  
  public Box(String id, String description, String marketing, Integer number, List<BoxReward> rewards, long totalWeight, long[] cumulativeWeights, SecureRandom random) {
    this.id = id;
    this.description = description;
    this.marketing = marketing;
    this.number = number;
    this.rewards = rewards;
    this.totalWeight = totalWeight;
    this.cumulativeWeights = cumulativeWeights;
    this.random = random;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public String getMarketing() {
    return this.marketing;
  }
  
  public Integer getNumber() {
    return this.number;
  }
  
  public List<BoxReward> getRewards() {
    return this.rewards;
  }
  
  public long getTotalWeight() {
    return this.totalWeight;
  }
  
  public long[] getCumulativeWeights() {
    return this.cumulativeWeights;
  }
  
  public SecureRandom getRandom() {
    return this.random;
  }
  
  @NonNull
  public BoxData getData() {
    return BoxManager.getBox(this.id).get();
  }
  
  @NonNull
  public Integer getNumber(EntityPlayer player) {
    return Integer.valueOf(this.number.intValue() + ((Integer)PermissionManager.inst().getPermission(PermissibleEntity.from((Entity)player), "paladium.box.bonus.", Integer.class).orElse(Integer.valueOf(0))).intValue());
  }
  
  @NonNull
  public List<BoxReward> getRewards(@NonNull BoxReward.Type type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return (List<BoxReward>)this.rewards.stream().filter(reward -> (reward.getType() == type)).collect(Collectors.toList());
  }
  
  @NonNull
  public Optional<BoxReward> getShopReward(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return this.rewards.stream().filter(reward -> (reward.getShopItem() != null && reward.getShopItem().getUniqueId().equals(id))).findFirst();
  }
  
  @NonNull
  public List<BoxReward> getRandomRewards(int count) {
    if (this.rewards.isEmpty())
      throw new IllegalArgumentException("Box rewards cannot be empty"); 
    if (this.random == null || this.cumulativeWeights == null || this.totalWeight <= 0L || this.cumulativeWeights.length != this.rewards.size())
      load(); 
    List<BoxReward> result = new ArrayList<>(count);
    for (int i = 0; i < count; i++)
      result.add(this.rewards.get(binarySearch((long)(this.random.nextDouble() * this.totalWeight)))); 
    return new ArrayList<>(result);
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
    this.cumulativeWeights = new long[this.rewards.size()];
    this.random = new SecureRandom();
    long cumulative = 0L;
    for (int i = 0; i < this.rewards.size(); i++) {
      cumulative += ((BoxReward)this.rewards.get(i)).getWeight().intValue();
      this.cumulativeWeights[i] = cumulative;
    } 
    if (cumulative <= 0L)
      throw new IllegalArgumentException("Total weight must be positive"); 
    this.totalWeight = cumulative;
  }
  
  public boolean equals(Object o) {
    if (!(o instanceof Box))
      return false; 
    Box box = (Box)o;
    return Objects.equals(this.id, box.id);
  }
  
  public int hashCode() {
    return Objects.hashCode(this.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\dto\box\Box.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */