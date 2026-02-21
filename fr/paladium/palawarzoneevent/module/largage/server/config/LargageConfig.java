package fr.paladium.palawarzoneevent.module.largage.server.config;

import fr.paladium.palaforgeutils.lib.command.CommandUtils;
import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palawarzoneevent.module.largage.LargageModule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;

public class LargageConfig {
  public void setLargageMaxHealth(double largageMaxHealth) {
    this.largageMaxHealth = largageMaxHealth;
  }
  
  public void setStartingY(double startingY) {
    this.startingY = startingY;
  }
  
  public void setFactionCheckRadius(double factionCheckRadius) {
    this.factionCheckRadius = factionCheckRadius;
  }
  
  public void setMinItemDrop(int minItemDrop) {
    this.minItemDrop = minItemDrop;
  }
  
  public void setMaxItemDrop(int maxItemDrop) {
    this.maxItemDrop = maxItemDrop;
  }
  
  public void setMinLargageCount(int minLargageCount) {
    this.minLargageCount = minLargageCount;
  }
  
  public void setMaxLargageCount(int maxLargageCount) {
    this.maxLargageCount = maxLargageCount;
  }
  
  public void setInvulnerabilityFactionThreshold(int invulnerabilityFactionThreshold) {
    this.invulnerabilityFactionThreshold = invulnerabilityFactionThreshold;
  }
  
  public void setRewardElo(int rewardElo) {
    this.rewardElo = rewardElo;
  }
  
  private static final transient Random RANDOM = new Random();
  
  public static final transient String PATH = "palawarzoneevent/largage.json";
  
  private double largageMaxHealth = 500.0D;
  
  public double getLargageMaxHealth() {
    return this.largageMaxHealth;
  }
  
  private double startingY = 200.0D;
  
  public double getStartingY() {
    return this.startingY;
  }
  
  private double factionCheckRadius = 10.0D;
  
  public double getFactionCheckRadius() {
    return this.factionCheckRadius;
  }
  
  private int minItemDrop = 1;
  
  public int getMinItemDrop() {
    return this.minItemDrop;
  }
  
  private int maxItemDrop = 5;
  
  public int getMaxItemDrop() {
    return this.maxItemDrop;
  }
  
  private int minLargageCount = 2;
  
  public int getMinLargageCount() {
    return this.minLargageCount;
  }
  
  private int maxLargageCount = 4;
  
  public int getMaxLargageCount() {
    return this.maxLargageCount;
  }
  
  private int invulnerabilityFactionThreshold = 2;
  
  public int getInvulnerabilityFactionThreshold() {
    return this.invulnerabilityFactionThreshold;
  }
  
  private final List<SpawnPoint> largageSpawnPoints = new ArrayList<>();
  
  public List<SpawnPoint> getLargageSpawnPoints() {
    return this.largageSpawnPoints;
  }
  
  private int rewardElo = 0;
  
  public int getRewardElo() {
    return this.rewardElo;
  }
  
  private final List<ItemReward> rewardItems = new ArrayList<>();
  
  public List<ItemReward> getRewardItems() {
    return this.rewardItems;
  }
  
  private final List<String> rewardCommands = new ArrayList<>();
  
  public List<String> getRewardCommands() {
    return this.rewardCommands;
  }
  
  public void save() {
    try {
      JsonConfigLoader.saveConfig(LargageModule.getServer().getConfigFilePath(), this);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public void executeCommandRewards(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (this.rewardCommands.isEmpty())
      return; 
    CommandUtils.performCommands(player, this.rewardCommands.<String>toArray(new String[0]));
  }
  
  public SpawnPoint getRandomSpawnPoint() {
    if (this.largageSpawnPoints.isEmpty())
      return null; 
    return this.largageSpawnPoints.get(RANDOM.nextInt(this.largageSpawnPoints.size()));
  }
  
  public List<SpawnPoint> getSpawnPointsAround(SpawnPoint spawnPoint, double radius) {
    List<SpawnPoint> spawnPoints = new ArrayList<>();
    for (SpawnPoint point : this.largageSpawnPoints) {
      if (point == spawnPoint)
        continue; 
      double distance = Math.sqrt(Math.pow((point.getX() - spawnPoint.getX()), 2.0D) + Math.pow((point.getZ() - spawnPoint.getZ()), 2.0D));
      if (distance <= radius)
        spawnPoints.add(point); 
    } 
    return spawnPoints;
  }
  
  public void addSpawnPoint(SpawnPoint spawnPoint) {
    this.largageSpawnPoints.add(spawnPoint);
    save();
  }
  
  public SpawnPoint removeSpawnPoint(int index) {
    SpawnPoint removed = this.largageSpawnPoints.remove(index);
    save();
    return removed;
  }
  
  public static class SpawnPoint {
    private final float x;
    
    private final float z;
    
    public String toString() {
      return "LargageConfig.SpawnPoint(x=" + getX() + ", z=" + getZ() + ")";
    }
    
    public float getX() {
      return this.x;
    }
    
    public float getZ() {
      return this.z;
    }
    
    public SpawnPoint(float x, float z) {
      this.x = x;
      this.z = z;
    }
  }
  
  public static class ItemReward {
    private final String item;
    
    private final int weight;
    
    public String getItem() {
      return this.item;
    }
    
    public int getWeight() {
      return this.weight;
    }
    
    public ItemReward(String item, int amount) {
      this.item = item;
      this.weight = amount;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\server\config\LargageConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */