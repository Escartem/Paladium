package fr.paladium.palarpg.module.stat.common.playerdata.capability;

public enum EnumStats {
  MAX_HEALTH("maxHealth", StatMutationType.ADDITIVE),
  HEALTH_REGENERATION("healthRegeneration", StatMutationType.ADDITIVE),
  HEALTH_REGENERATION_SPEED("healthRegenerationSpeed", StatMutationType.ADDITIVE),
  RESISTANCE("resistance", StatMutationType.ADDITIVE),
  TALIKUS_RESISTANCE("talikusResistance", StatMutationType.ADDITIVE),
  MANELIOS_RESISTANCE("maneliosResistance", StatMutationType.ADDITIVE),
  VITALYS_RESISTANCE("vitalysResistance", StatMutationType.ADDITIVE),
  NIMBRIA_RESISTANCE("nimbriaResistance", StatMutationType.ADDITIVE),
  DAMAGE("damage", StatMutationType.ADDITIVE),
  TALIKUS_DAMAGE("talikusDamage", StatMutationType.ADDITIVE),
  MANELIOS_DAMAGE("maneliosDamage", StatMutationType.ADDITIVE),
  VITALYS_DAMAGE("vitalysDamage", StatMutationType.ADDITIVE),
  NIMBRIA_DAMAGE("nimbriaDamage", StatMutationType.ADDITIVE),
  CRITICAL_DAMAGE("criticalDamage", StatMutationType.MULTIPLICATIVE),
  CRITICAL_CHANCE("criticalChance", StatMutationType.MULTIPLICATIVE),
  DODGE("dodge", StatMutationType.MULTIPLICATIVE),
  SPEED("speed", StatMutationType.ADDITIVE),
  BONUS_LOOT("bonusLoot", StatMutationType.MULTIPLICATIVE),
  BONUS_LOOT_ANCIENT("bonusLootAncient", StatMutationType.MULTIPLICATIVE),
  FRIGORIA_RESISTANCE("frigoriaResistance", StatMutationType.ADDITIVE),
  FRIGORIA_DAMAGE("frigoriaDamage", StatMutationType.ADDITIVE);
  
  EnumStats(String statName, StatMutationType applyType) {
    this.statName = statName;
    this.applyType = applyType;
  }
  
  public static final EnumStats[] values;
  
  private final String statName;
  
  private final StatMutationType applyType;
  
  static {
    values = values();
  }
  
  public String getStatName() {
    return this.statName;
  }
  
  public StatMutationType getApplyType() {
    return this.applyType;
  }
  
  public String getFormattedStatName() {
    return this.statName.replaceAll("([A-Z])", " $1").toLowerCase().trim();
  }
  
  public static EnumStats fromOrdinal(int ordinal) {
    return values[ordinal];
  }
  
  public static EnumStats fromStatName(String statName) {
    for (EnumStats type : values) {
      if (type.getStatName().equalsIgnoreCase(statName))
        return type; 
    } 
    return null;
  }
  
  public static EnumStats fromString(String name) {
    for (EnumStats type : values) {
      if (type.name().equalsIgnoreCase(name))
        return type; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\common\playerdata\capability\EnumStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */