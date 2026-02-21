package fr.paladium.palarpg.module.dungeon.common.entity.room.merchant;

import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;

public class EntityDungeonMerchantMephistoTradePart {
  private final String name;
  
  private final EnumStats stat;
  
  private final double value;
  
  public EntityDungeonMerchantMephistoTradePart(String name, EnumStats stat, double value) {
    this.name = name;
    this.stat = stat;
    this.value = value;
  }
  
  public String getName() {
    return this.name;
  }
  
  public EnumStats getStat() {
    return this.stat;
  }
  
  public double getValue() {
    return this.value;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\entity\room\merchant\EntityDungeonMerchant$EntityDungeonMerchantMephistoTrade$EntityDungeonMerchantMephistoTradePart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */