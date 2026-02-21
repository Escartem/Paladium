package fr.paladium.palarpg.module.stat.common.playerdata.capability.impl;

import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import lombok.NonNull;

public class StatBonusLootCapability extends StatNumberCapability {
  private final String lootType;
  
  public String getLootType() {
    return this.lootType;
  }
  
  public StatBonusLootCapability(@NonNull EnumStats stat, @NonNull String lootType) {
    super(stat, Double.valueOf(1.0D), Double.valueOf(1.0D), Double.valueOf(Double.MAX_VALUE));
    if (stat == null)
      throw new NullPointerException("stat is marked non-null but is null"); 
    if (lootType == null)
      throw new NullPointerException("lootType is marked non-null but is null"); 
    this.lootType = lootType;
  }
  
  public boolean isLootType(@NonNull String lootType) {
    if (lootType == null)
      throw new NullPointerException("lootType is marked non-null but is null"); 
    return this.lootType.equalsIgnoreCase(lootType);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\common\playerdata\capability\impl\StatBonusLootCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */