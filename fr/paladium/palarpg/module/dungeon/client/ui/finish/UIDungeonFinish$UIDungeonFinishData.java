package fr.paladium.palarpg.module.dungeon.client.ui.finish;

import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.palarpg.module.dungeon.common.world.generator.DungeonGenerator;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonConfig;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class UIDungeonFinishData {
  private final DungeonConfig dungeon;
  
  private final List<DungeonGenerator.DungeonRoomPath> paths;
  
  private final Map<String, RPGItemRarity> rawItems;
  
  private final long time;
  
  private final int jobLevel;
  
  private final JobType jobType;
  
  private final double jobExperience;
  
  private final double jobProgress;
  
  private final double dungeonExperience;
  
  private final int level;
  
  private transient Set<RPGDungeonPlayerData.RPGDungeonItem> items;
  
  public UIDungeonFinishData(DungeonConfig dungeon, List<DungeonGenerator.DungeonRoomPath> paths, Map<String, RPGItemRarity> rawItems, long time, int jobLevel, JobType jobType, double jobExperience, double jobProgress, double dungeonExperience, int level) {
    this.dungeon = dungeon;
    this.paths = paths;
    this.rawItems = rawItems;
    this.time = time;
    this.jobLevel = jobLevel;
    this.jobType = jobType;
    this.jobExperience = jobExperience;
    this.jobProgress = jobProgress;
    this.dungeonExperience = dungeonExperience;
    this.level = level;
  }
  
  public DungeonConfig getDungeon() {
    return this.dungeon;
  }
  
  public List<DungeonGenerator.DungeonRoomPath> getPaths() {
    return this.paths;
  }
  
  public Map<String, RPGItemRarity> getRawItems() {
    return this.rawItems;
  }
  
  public long getTime() {
    return this.time;
  }
  
  public int getJobLevel() {
    return this.jobLevel;
  }
  
  public JobType getJobType() {
    return this.jobType;
  }
  
  public double getJobExperience() {
    return this.jobExperience;
  }
  
  public double getJobProgress() {
    return this.jobProgress;
  }
  
  public double getDungeonExperience() {
    return this.dungeonExperience;
  }
  
  public int getLevel() {
    return this.level;
  }
  
  public Set<RPGDungeonPlayerData.RPGDungeonItem> getItems() {
    if (this.items != null)
      return this.items; 
    this.items = new LinkedHashSet<>();
    for (Map.Entry<String, RPGItemRarity> entry : this.rawItems.entrySet()) {
      RPGDungeonPlayerData.RPGDungeonItem item = new RPGDungeonPlayerData.RPGDungeonItem(UUID.randomUUID(), "default", ItemStackSerializer.read64(entry.getKey()), entry.getValue());
      this.items.add(item);
    } 
    return this
      
      .items = (Set<RPGDungeonPlayerData.RPGDungeonItem>)this.items.stream().sorted((o1, o2) -> {
          int rarityCompare = o2.getRarity().compareTo((Enum)o1.getRarity());
          if (rarityCompare != 0)
            return rarityCompare; 
          int nameCompare = o1.getItem().func_82833_r().compareToIgnoreCase(o2.getItem().func_82833_r());
          return (nameCompare != 0) ? nameCompare : Integer.compare((o2.getItem()).field_77994_a, (o1.getItem()).field_77994_a);
        }).collect(Collectors.toCollection(LinkedHashSet::new));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\finish\UIDungeonFinish$UIDungeonFinishData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */