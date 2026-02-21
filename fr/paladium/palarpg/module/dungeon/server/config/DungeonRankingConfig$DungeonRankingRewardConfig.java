package fr.paladium.palarpg.module.dungeon.server.config;

import fr.paladium.palaforgeutils.lib.command.impl.palagive.manager.PalaGiveManager;
import fr.paladium.palaforgeutils.lib.console.ConsoleUtils;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palarpg.module.dungeon.common.ranking.DungeonRankingData;
import fr.paladium.palashop.common.utils.server.ServerUtils;
import fr.paladium.palashop.server.shop.dto.item.ShopItem;
import net.minecraft.item.ItemStack;

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


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\config\DungeonRankingConfig$DungeonRankingRewardConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */