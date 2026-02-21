package fr.paladium.palarpg.module.dungeon.server.config.level;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import fr.paladium.palarpg.module.dungeon.server.config.room.DungeonRoomConfig;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.NonNull;

public class DungeonLevelConfig {
  @SerializedName("rooms")
  private final List<String> rawRooms;
  
  @SerializedName("room_count")
  private final JsonElement roomCount;
  
  private final Map<DungeonRoomConfig.DungeonRoomType, DungeonLevelGenerationConfig> generation;
  
  private final DungeonLevelRewardsConfig rewards;
  
  private int level;
  
  private Map<DungeonLevelRoomType, DungeonLevelEntitiesConfig> entities;
  
  private Map<DungeonLevelRoomType, DungeonLevelLootsConfig> loots;
  
  private transient List<DungeonRoomConfig> rooms;
  
  private transient List<DungeonLevelLootsConfig.DungeonLevelLootsElementConfig> lootList;
  
  public String toString() {
    return "DungeonLevelConfig(rawRooms=" + getRawRooms() + ", roomCount=" + getRoomCount() + ", generation=" + getGeneration() + ", rewards=" + getRewards() + ", level=" + getLevel() + ", entities=" + getEntities() + ", loots=" + getLoots() + ", rooms=" + getRooms() + ", lootList=" + getLootList() + ")";
  }
  
  public DungeonLevelConfig(List<String> rawRooms, JsonElement roomCount, Map<DungeonRoomConfig.DungeonRoomType, DungeonLevelGenerationConfig> generation, DungeonLevelRewardsConfig rewards, int level, Map<DungeonLevelRoomType, DungeonLevelEntitiesConfig> entities, Map<DungeonLevelRoomType, DungeonLevelLootsConfig> loots, List<DungeonRoomConfig> rooms, List<DungeonLevelLootsConfig.DungeonLevelLootsElementConfig> lootList) {
    this.rawRooms = rawRooms;
    this.roomCount = roomCount;
    this.generation = generation;
    this.rewards = rewards;
    this.level = level;
    this.entities = entities;
    this.loots = loots;
    this.rooms = rooms;
    this.lootList = lootList;
  }
  
  public List<String> getRawRooms() {
    return this.rawRooms;
  }
  
  public JsonElement getRoomCount() {
    return this.roomCount;
  }
  
  public Map<DungeonRoomConfig.DungeonRoomType, DungeonLevelGenerationConfig> getGeneration() {
    return this.generation;
  }
  
  public DungeonLevelRewardsConfig getRewards() {
    return this.rewards;
  }
  
  public int getLevel() {
    return this.level;
  }
  
  public Map<DungeonLevelRoomType, DungeonLevelEntitiesConfig> getEntities() {
    return this.entities;
  }
  
  public Map<DungeonLevelRoomType, DungeonLevelLootsConfig> getLoots() {
    return this.loots;
  }
  
  public List<DungeonRoomConfig> getRooms() {
    return this.rooms;
  }
  
  @NonNull
  public DungeonLevelConfig build(int level, @NonNull Map<DungeonLevelRoomType, DungeonLevelEntitiesConfig> entities, @NonNull Map<DungeonLevelRoomType, DungeonLevelLootsConfig> loots, @NonNull List<DungeonRoomConfig> rooms) {
    if (entities == null)
      throw new NullPointerException("entities is marked non-null but is null"); 
    if (loots == null)
      throw new NullPointerException("loots is marked non-null but is null"); 
    if (rooms == null)
      throw new NullPointerException("rooms is marked non-null but is null"); 
    this.level = level;
    this.entities = entities;
    this.loots = loots;
    this.rooms = rooms;
    return this;
  }
  
  @NonNull
  public Optional<DungeonLevelGenerationConfig> getGenerationConfig(@NonNull DungeonRoomConfig.DungeonRoomType type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return Optional.ofNullable(this.generation.get(type));
  }
  
  public boolean has(@NonNull DungeonRoomConfig.DungeonRoomType type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return this.rooms.stream().anyMatch(room -> (room.getType() == type));
  }
  
  @NonNull
  public Optional<DungeonRoomConfig> getRoom(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return this.rooms.stream().filter(room -> room.getId().equalsIgnoreCase(id)).findFirst();
  }
  
  @NonNull
  public List<DungeonRoomConfig> getRooms(@NonNull DungeonRoomConfig.DungeonRoomType type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return (List<DungeonRoomConfig>)this.rooms.stream().filter(room -> (room.getType() == type)).collect(Collectors.toList());
  }
  
  @NonNull
  public List<DungeonLevelLootsConfig.DungeonLevelLootsElementConfig> getLootList() {
    if (this.lootList == null)
      this
        
        .lootList = (List<DungeonLevelLootsConfig.DungeonLevelLootsElementConfig>)((Map)this.loots.values().stream().flatMap(loot -> loot.getLoots().stream()).collect(Collectors.toMap(DungeonLevelLootsConfig.DungeonLevelLootsElementConfig::getItem, config -> config, (existing, replacement) -> existing))).values().stream().map(config -> {
            config.create();
            return config;
          }).sorted((config1, config2) -> Integer.compare(config2.getRarity().ordinal(), config1.getRarity().ordinal())).collect(Collectors.toCollection(java.util.LinkedList::new)); 
    return this.lootList;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.level) });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    DungeonLevelConfig other = (DungeonLevelConfig)obj;
    return (this.level == other.level);
  }
  
  public class DungeonLevelRewardsConfig {
    private final DungeonLevelRewardsJobsConfig jobs;
    
    public String toString() {
      return "DungeonLevelConfig.DungeonLevelRewardsConfig(jobs=" + getJobs() + ")";
    }
    
    public DungeonLevelRewardsConfig(DungeonLevelRewardsJobsConfig jobs) {
      this.jobs = jobs;
    }
    
    public DungeonLevelRewardsJobsConfig getJobs() {
      return this.jobs;
    }
    
    public class DungeonLevelRewardsJobsConfig {
      private final String job;
      
      private final JsonElement experience;
      
      public DungeonLevelRewardsJobsConfig(String job, JsonElement experience) {
        this.job = job;
        this.experience = experience;
      }
      
      public String getJob() {
        return this.job;
      }
      
      public JsonElement getExperience() {
        return this.experience;
      }
    }
  }
  
  public class DungeonLevelGenerationConfig {
    private final JsonElement min;
    
    private final JsonElement max;
    
    private final JsonElement weight;
    
    public DungeonLevelGenerationConfig(JsonElement min, JsonElement max, JsonElement weight) {
      this.min = min;
      this.max = max;
      this.weight = weight;
    }
    
    public JsonElement getMin() {
      return this.min;
    }
    
    public JsonElement getMax() {
      return this.max;
    }
    
    public JsonElement getWeight() {
      return this.weight;
    }
  }
  
  public enum DungeonLevelRoomType {
    ROOM, MINIBOSS, BOSS;
    
    @NonNull
    public static DungeonLevelRoomType from(@NonNull DungeonRoomConfig.DungeonRoomType type) {
      if (type == null)
        throw new NullPointerException("type is marked non-null but is null"); 
      switch (type) {
        case MINIBOSS:
          return MINIBOSS;
        case BOSS:
          return BOSS;
      } 
      return ROOM;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\config\level\DungeonLevelConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */