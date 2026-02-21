package fr.paladium.palarpg.module.dungeon.server.config;

import fr.paladium.palaforgeutils.lib.command.impl.palagive.manager.PalaGiveManager;
import fr.paladium.palaforgeutils.lib.console.ConsoleUtils;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.mongo.MongoDBCredentials;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palarpg.module.dungeon.common.ranking.DungeonRankingData;
import fr.paladium.palashop.common.utils.server.ServerUtils;
import fr.paladium.palashop.server.shop.dto.item.ShopItem;
import java.util.List;
import net.minecraft.item.ItemStack;

public class DungeonRankingConfig {
  private final MongoDBCredentials database;
  
  private final long neededExperience;
  
  private final List<DungeonRankingRewardConfig> topRewards;
  
  private final List<DungeonRankingRewardConfig> experienceRewards;
  
  public String toString() {
    return "DungeonRankingConfig(database=" + getDatabase() + ", neededExperience=" + getNeededExperience() + ", topRewards=" + getTopRewards() + ", experienceRewards=" + getExperienceRewards() + ")";
  }
  
  public DungeonRankingConfig(MongoDBCredentials database, long neededExperience, List<DungeonRankingRewardConfig> topRewards, List<DungeonRankingRewardConfig> experienceRewards) {
    this.database = database;
    this.neededExperience = neededExperience;
    this.topRewards = topRewards;
    this.experienceRewards = experienceRewards;
  }
  
  public MongoDBCredentials getDatabase() {
    return this.database;
  }
  
  public long getNeededExperience() {
    return this.neededExperience;
  }
  
  public List<DungeonRankingRewardConfig> getTopRewards() {
    return this.topRewards;
  }
  
  public List<DungeonRankingRewardConfig> getExperienceRewards() {
    return this.experienceRewards;
  }
  
  public class DungeonRankingRewardConfig {
    private final ShopItem.ExecutionType type;
    
    private final String execution;
    
    private final String image;
    
    public String toString() {
      return "DungeonRankingConfig.DungeonRankingRewardConfig(type=" + getType() + ", execution=" + getExecution() + ", image=" + getImage() + ")";
    }
    
    public DungeonRankingRewardConfig(ShopItem.ExecutionType type, String execution, String image) {
      this.type = type;
      this.execution = execution;
      this.image = image;
    }
    
    public ShopItem.ExecutionType getType() {
      return this.type;
    }
    
    public String getExecution() {
      return this.execution;
    }
    
    public String getImage() {
      return this.image;
    }
    
    public void apply(DungeonRankingData data) {
      if (this.type == ShopItem.ExecutionType.COMMAND) {
        String command = this.execution.replace("%username%", data.getPlayerName()).replace("%player%", data.getPlayerName()).replace("%uuid%", data.getPlayerUUID()).replace("%server%", ServerUtils.getServerName()).replace("%server_name%", ServerUtils.getServerName()).replace("%server_type%", Server.getServerType().getName()).replace("%date%", String.valueOf(System.currentTimeMillis()));
        if (command.contains("int(") && command.contains(")")) {
          int start = command.indexOf("int(") + 4;
          int end = command.indexOf(")", start);
          String value = command.substring(start, end);
          int quantity = Double.valueOf(value).intValue();
          command = command.replace("int(" + value + ")", String.valueOf(quantity));
        } 
        ConsoleUtils.executeCommand(command);
      } else if (this.type == ShopItem.ExecutionType.ITEM) {
        ItemStack item = ItemUtils.parse(this.execution);
        if (item == null)
          return; 
        PalaGiveManager.inst().give(data.getPlayerUUID(), item);
      } 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\config\DungeonRankingConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */