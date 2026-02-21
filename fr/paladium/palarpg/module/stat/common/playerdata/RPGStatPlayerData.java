package fr.paladium.palarpg.module.stat.common.playerdata;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palarpg.common.extended.playerdata.APlayerData;
import fr.paladium.palarpg.common.extended.playerdata.PlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.AStatCapability;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.impl.StatBonusLootCapability;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.impl.StatNumberCapability;
import fr.paladium.palarpg.module.stat.server.manager.StatsManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.nbt.NBTTagCompound;

@PlayerData("stats")
public class RPGStatPlayerData extends APlayerData {
  public static final String ID = "stats";
  
  public String toString() {
    return "RPGStatPlayerData(syncNonSaved=" + isSyncNonSaved() + ", capabilities=" + getCapabilities() + ", maxHealth=" + getMaxHealth() + ", healthRegeneration=" + getHealthRegeneration() + ", healthRegenerationSpeed=" + getHealthRegenerationSpeed() + ", resistance=" + getResistance() + ", frigoriaResistance=" + getFrigoriaResistance() + ", talikusResistance=" + getTalikusResistance() + ", maneliosResistance=" + getManeliosResistance() + ", vitalysResistance=" + getVitalysResistance() + ", nimbriaResistance=" + getNimbriaResistance() + ", damage=" + getDamage() + ", frigoriaDamage=" + getFrigoriaDamage() + ", talikusDamage=" + getTalikusDamage() + ", maneliosDamage=" + getManeliosDamage() + ", vitalysDamage=" + getVitalysDamage() + ", nimbriaDamage=" + getNimbriaDamage() + ", criticalDamage=" + getCriticalDamage() + ", criticalChance=" + getCriticalChance() + ", dodge=" + getDodge() + ", speed=" + getSpeed() + ", bonusLoot=" + getBonusLoot() + ", bonusLootAncient=" + getBonusLootAncient() + ")";
  }
  
  private final boolean syncNonSaved = false;
  
  public boolean isSyncNonSaved() {
    getClass();
    return false;
  }
  
  private final List<AStatCapability<?>> capabilities = new ArrayList<>();
  
  public List<AStatCapability<?>> getCapabilities() {
    return this.capabilities;
  }
  
  private final StatNumberCapability maxHealth = registerCapability(new StatNumberCapability(EnumStats.MAX_HEALTH, Double.valueOf(20.0D), Double.valueOf(0.1D), Double.valueOf(Double.MAX_VALUE)));
  
  public StatNumberCapability getMaxHealth() {
    return this.maxHealth;
  }
  
  private final StatNumberCapability healthRegeneration = registerCapability(new StatNumberCapability(EnumStats.HEALTH_REGENERATION, Double.valueOf(2.0D), Double.valueOf(0.0D), Double.valueOf(Double.MAX_VALUE)));
  
  public StatNumberCapability getHealthRegeneration() {
    return this.healthRegeneration;
  }
  
  private final StatNumberCapability healthRegenerationSpeed = registerCapability(new StatNumberCapability(EnumStats.HEALTH_REGENERATION_SPEED, Double.valueOf(4.0D), Double.valueOf(1.0D), Double.valueOf(5.0D)));
  
  public StatNumberCapability getHealthRegenerationSpeed() {
    return this.healthRegenerationSpeed;
  }
  
  private final StatNumberCapability resistance = registerCapability(new StatNumberCapability(EnumStats.RESISTANCE, Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(Double.MAX_VALUE)));
  
  public StatNumberCapability getResistance() {
    return this.resistance;
  }
  
  private final StatNumberCapability frigoriaResistance = registerCapability(new StatNumberCapability(EnumStats.FRIGORIA_RESISTANCE, Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(Double.MAX_VALUE)));
  
  public StatNumberCapability getFrigoriaResistance() {
    return this.frigoriaResistance;
  }
  
  private final StatNumberCapability talikusResistance = registerCapability(new StatNumberCapability(EnumStats.TALIKUS_RESISTANCE, Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(Double.MAX_VALUE)));
  
  public StatNumberCapability getTalikusResistance() {
    return this.talikusResistance;
  }
  
  private final StatNumberCapability maneliosResistance = registerCapability(new StatNumberCapability(EnumStats.MANELIOS_RESISTANCE, Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(Double.MAX_VALUE)));
  
  public StatNumberCapability getManeliosResistance() {
    return this.maneliosResistance;
  }
  
  private final StatNumberCapability vitalysResistance = registerCapability(new StatNumberCapability(EnumStats.VITALYS_RESISTANCE, Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(Double.MAX_VALUE)));
  
  public StatNumberCapability getVitalysResistance() {
    return this.vitalysResistance;
  }
  
  private final StatNumberCapability nimbriaResistance = registerCapability(new StatNumberCapability(EnumStats.NIMBRIA_RESISTANCE, Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(Double.MAX_VALUE)));
  
  public StatNumberCapability getNimbriaResistance() {
    return this.nimbriaResistance;
  }
  
  private final StatNumberCapability damage = registerCapability(new StatNumberCapability(EnumStats.DAMAGE, Double.valueOf(1.0D), Double.valueOf(0.0D), Double.valueOf(Double.MAX_VALUE)));
  
  public StatNumberCapability getDamage() {
    return this.damage;
  }
  
  private final StatNumberCapability frigoriaDamage = registerCapability(new StatNumberCapability(EnumStats.FRIGORIA_DAMAGE, Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(Double.MAX_VALUE)));
  
  public StatNumberCapability getFrigoriaDamage() {
    return this.frigoriaDamage;
  }
  
  private final StatNumberCapability talikusDamage = registerCapability(new StatNumberCapability(EnumStats.TALIKUS_DAMAGE, Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(Double.MAX_VALUE)));
  
  public StatNumberCapability getTalikusDamage() {
    return this.talikusDamage;
  }
  
  private final StatNumberCapability maneliosDamage = registerCapability(new StatNumberCapability(EnumStats.MANELIOS_DAMAGE, Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(Double.MAX_VALUE)));
  
  public StatNumberCapability getManeliosDamage() {
    return this.maneliosDamage;
  }
  
  private final StatNumberCapability vitalysDamage = registerCapability(new StatNumberCapability(EnumStats.VITALYS_DAMAGE, Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(Double.MAX_VALUE)));
  
  public StatNumberCapability getVitalysDamage() {
    return this.vitalysDamage;
  }
  
  private final StatNumberCapability nimbriaDamage = registerCapability(new StatNumberCapability(EnumStats.NIMBRIA_DAMAGE, Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(Double.MAX_VALUE)));
  
  public StatNumberCapability getNimbriaDamage() {
    return this.nimbriaDamage;
  }
  
  private final StatNumberCapability criticalDamage = registerCapability(new StatNumberCapability(EnumStats.CRITICAL_DAMAGE, Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(Double.MAX_VALUE)));
  
  public StatNumberCapability getCriticalDamage() {
    return this.criticalDamage;
  }
  
  private final StatNumberCapability criticalChance = registerCapability(new StatNumberCapability(EnumStats.CRITICAL_CHANCE, Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(Math.nextDown(1.0D))));
  
  public StatNumberCapability getCriticalChance() {
    return this.criticalChance;
  }
  
  private final StatNumberCapability dodge = registerCapability(new StatNumberCapability(EnumStats.DODGE, Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(Math.nextDown(1.0D))));
  
  public StatNumberCapability getDodge() {
    return this.dodge;
  }
  
  private final StatNumberCapability speed = registerCapability(new StatNumberCapability(EnumStats.SPEED, Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(40.0D)));
  
  public StatNumberCapability getSpeed() {
    return this.speed;
  }
  
  private final StatBonusLootCapability bonusLoot = registerCapability(new StatBonusLootCapability(EnumStats.BONUS_LOOT, "default"));
  
  public StatBonusLootCapability getBonusLoot() {
    return this.bonusLoot;
  }
  
  private final StatBonusLootCapability bonusLootAncient = registerCapability(new StatBonusLootCapability(EnumStats.BONUS_LOOT_ANCIENT, "ancient"));
  
  public StatBonusLootCapability getBonusLootAncient() {
    return this.bonusLootAncient;
  }
  
  private <T extends AStatCapability<?>> T registerCapability(T capability) {
    this.capabilities.add((AStatCapability<?>)capability);
    return capability;
  }
  
  public <T> AStatCapability<T> getCapabilityByName(String type) {
    return getCapabilityByName(EnumStats.fromString(type));
  }
  
  public <T> AStatCapability<T> getCapabilityByName(EnumStats type) {
    return this.capabilities.stream().filter(capa -> capa.getName().equalsIgnoreCase(type.getStatName())).findFirst().orElse(null);
  }
  
  public <T extends AStatCapability<?>> T getCapabilityByClass(Class<T> type) {
    return (T)this.capabilities.stream().filter(capa -> type.isAssignableFrom(capa.getClass())).map(capa -> capa).findFirst().orElse(null);
  }
  
  public <T extends AStatCapability<?>> List<T> getCapabilitiesByClass(Class<T> type) {
    return (List<T>)this.capabilities.stream().filter(capa -> type.isAssignableFrom(capa.getClass())).map(capa -> capa).collect(Collectors.toList());
  }
  
  public void read(@NonNull NBTTagCompound nbt, boolean saving) {
    if (nbt == null)
      throw new NullPointerException("nbt is marked non-null but is null"); 
    this.capabilities.forEach(capability -> capability.read(nbt));
  }
  
  public void write(@NonNull NBTTagCompound nbt, boolean saving) {
    if (nbt == null)
      throw new NullPointerException("nbt is marked non-null but is null"); 
    this.capabilities.forEach(capability -> capability.write(nbt, !saving));
  }
  
  public void tick() {
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
      this.capabilities.forEach(AStatCapability::tick);
      return;
    } 
    boolean needsSync = false;
    for (AStatCapability<?> capability : this.capabilities) {
      if (capability.tick() && !needsSync)
        needsSync = true; 
    } 
    if (needsSync)
      applyAndSync(); 
  }
  
  public void applyAndSync() {
    if (FMLCommonHandler.instance().getSide() != Side.SERVER)
      return; 
    StatsManager.SPEED.applySpeed(getEntity());
    StatsManager.MAX_HEALTH.applyMaxHealth(getEntity());
    sync();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\common\playerdata\RPGStatPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */