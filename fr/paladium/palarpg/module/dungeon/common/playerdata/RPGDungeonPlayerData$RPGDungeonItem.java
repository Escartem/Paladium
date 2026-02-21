package fr.paladium.palarpg.module.dungeon.common.playerdata;

import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import java.util.Objects;
import java.util.UUID;
import net.minecraft.item.ItemStack;

public class RPGDungeonItem {
  private final UUID uniqueId;
  
  private final String type;
  
  private final ItemStack item;
  
  private final RPGItemRarity rarity;
  
  public String toString() {
    return "RPGDungeonPlayerData.RPGDungeonItem(uniqueId=" + getUniqueId() + ", type=" + getType() + ", item=" + getItem() + ", rarity=" + getRarity() + ")";
  }
  
  public RPGDungeonItem(UUID uniqueId, String type, ItemStack item, RPGItemRarity rarity) {
    this.uniqueId = uniqueId;
    this.type = type;
    this.item = item;
    this.rarity = rarity;
  }
  
  public UUID getUniqueId() {
    return this.uniqueId;
  }
  
  public String getType() {
    return this.type;
  }
  
  public ItemStack getItem() {
    return this.item;
  }
  
  public RPGItemRarity getRarity() {
    return this.rarity;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.uniqueId });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    RPGDungeonItem other = (RPGDungeonItem)obj;
    return Objects.equals(this.uniqueId, other.uniqueId);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\playerdata\RPGDungeonPlayerData$RPGDungeonItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */