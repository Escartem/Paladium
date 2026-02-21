package fr.paladium.palarpg.module.stat.server.manager.impl;

import fr.paladium.palaforgeutils.lib.cooldown.CooldownUtils;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.entity.common.entity.RPGElementType;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.stat.StatModuleConstants;
import fr.paladium.palarpg.module.stat.common.network.SCRPGDamagePacket;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatMutationType;
import fr.paladium.palarpg.module.stat.server.manager.StatsManager;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

public final class DamageManager {
  public void applyDamageToEntity(EntityLivingBase source, EntityLivingBase target) {
    applyDamageToEntity(source, target, 0.0F);
  }
  
  public void applyDamageToEntity(EntityLivingBase source, EntityLivingBase target, float attackDamage) {
    RPGElementType sourceElement = RPGElementType.NEUTRAL;
    if (source instanceof RPGMobEntity)
      sourceElement = ((RPGMobEntity)source).getRPGType(); 
    RPGElementType targetElement = RPGElementType.NEUTRAL;
    if (target instanceof RPGMobEntity)
      targetElement = ((RPGMobEntity)target).getRPGType(); 
    Optional<DamageObject> optSourceDmg = getDamage(source, (target instanceof EntityPlayerMP) ? sourceElement : targetElement, attackDamage);
    if (!optSourceDmg.isPresent())
      return; 
    DamageObject dmgObject = optSourceDmg.get();
    float targetResistance = StatsManager.RESISTANCE.getResistance(target, sourceElement);
    dmgObject.setResistance(targetResistance);
    if (source instanceof EntityPlayerMP)
      dmgObject.sendDamagePacket((EntityPlayerMP)source, target.func_145782_y()); 
    target.func_70606_j(target.func_110143_aJ() - dmgObject.getApplicableDamage().floatValue());
    if (target instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)target;
      CooldownUtils.setCooldown((Entity)player, "PALARPG:COMBAT", StatModuleConstants.COMBAT_COOLDOWN.longValue());
    } 
  }
  
  public Optional<DamageObject> getDamage(EntityLivingBase source) {
    return getDamage(source, RPGElementType.NEUTRAL, 0.0D);
  }
  
  public Optional<DamageObject> getDamage(EntityLivingBase source, RPGElementType typeAgainst, double attackDamage) {
    RPGStatPlayerData sourceStats = (RPGStatPlayerData)RPGPlayer.getData((Entity)source, "stats");
    if (sourceStats == null)
      return Optional.empty(); 
    double damage = ((Number)sourceStats.getDamage().getDefaultValue()).doubleValue() + ((Number)sourceStats.getDamage().getTotalValue(StatMutationType.ADDITIVE)).doubleValue();
    damage *= 1.0D + ((Number)sourceStats.getDamage().getTotalValue(StatMutationType.MULTIPLICATIVE)).doubleValue();
    double typedDamage = (typeAgainst != RPGElementType.NEUTRAL) ? getRPGTypeDamage(typeAgainst, sourceStats) : 0.0D;
    DamageObject dmgObject = DamageObject.of(damage, typedDamage + attackDamage, typeAgainst);
    if (isCriticalHit(sourceStats)) {
      dmgObject.setCritical(true);
      dmgObject.setCriticalMultiplier(getCriticalMultiplier(sourceStats));
    } 
    return Optional.of(dmgObject);
  }
  
  public boolean isCriticalHit(@NonNull EntityLivingBase source) {
    if (source == null)
      throw new NullPointerException("source is marked non-null but is null"); 
    RPGStatPlayerData sourceStats = (RPGStatPlayerData)RPGPlayer.getData((Entity)source, "stats");
    if (sourceStats == null)
      return false; 
    return isCriticalHit(sourceStats);
  }
  
  public boolean isCriticalHit(@NonNull RPGStatPlayerData sourceStats) {
    if (sourceStats == null)
      throw new NullPointerException("sourceStats is marked non-null but is null"); 
    return (((Number)sourceStats.getCriticalChance().getValue()).doubleValue() >= sourceStats.getEntity().func_70681_au().nextDouble());
  }
  
  public double getCriticalChance(@NonNull EntityLivingBase source) {
    if (source == null)
      throw new NullPointerException("source is marked non-null but is null"); 
    RPGStatPlayerData sourceStats = (RPGStatPlayerData)RPGPlayer.getData((Entity)source, "stats");
    if (sourceStats == null)
      return 1.0D; 
    return getCriticalChance(sourceStats);
  }
  
  public double getCriticalChance(@NonNull RPGStatPlayerData sourceStats) {
    if (sourceStats == null)
      throw new NullPointerException("sourceStats is marked non-null but is null"); 
    return ((Number)sourceStats.getCriticalChance().getValue()).doubleValue();
  }
  
  public double getCriticalMultiplier(@NonNull EntityLivingBase source) {
    if (source == null)
      throw new NullPointerException("source is marked non-null but is null"); 
    RPGStatPlayerData sourceStats = (RPGStatPlayerData)RPGPlayer.getData((Entity)source, "stats");
    if (sourceStats == null)
      return 1.0D; 
    return getCriticalMultiplier(sourceStats);
  }
  
  public double getCriticalMultiplier(@NonNull RPGStatPlayerData sourceStats) {
    if (sourceStats == null)
      throw new NullPointerException("sourceStats is marked non-null but is null"); 
    return 1.0D + ((Number)sourceStats.getCriticalDamage().getValue()).doubleValue();
  }
  
  public double getRPGTypeDamage(RPGElementType elementType, RPGStatPlayerData entityStats) {
    switch (elementType) {
      case TALIKUS:
        return ((Number)entityStats.getTalikusDamage().getValue()).doubleValue();
      case MANELIOS:
        return ((Number)entityStats.getManeliosDamage().getValue()).doubleValue();
      case VITALYS:
        return ((Number)entityStats.getVitalysDamage().getValue()).doubleValue();
      case NIMBRIA:
        return ((Number)entityStats.getNimbriaDamage().getValue()).doubleValue();
      case FRIGORIA:
        return ((Number)entityStats.getFrigoriaDamage().getValue()).doubleValue();
      case NEUTRAL:
        return ((Number)entityStats.getDamage().getValue()).doubleValue();
    } 
    return 0.0D;
  }
  
  public static class DamageObject {
    private final double neutralDamage;
    
    private final double typedDamage;
    
    private final RPGElementType typeOfTypedDamage;
    
    public void setCriticalMultiplier(double criticalMultiplier) {
      this.criticalMultiplier = criticalMultiplier;
    }
    
    public void setCritical(boolean critical) {
      this.critical = critical;
    }
    
    public void setResistance(double resistance) {
      this.resistance = resistance;
    }
    
    public String toString() {
      return "DamageManager.DamageObject(neutralDamage=" + getNeutralDamage() + ", typedDamage=" + getTypedDamage() + ", typeOfTypedDamage=" + getTypeOfTypedDamage() + ", criticalMultiplier=" + getCriticalMultiplier() + ", critical=" + isCritical() + ", resistance=" + getResistance() + ")";
    }
    
    private DamageObject(double neutralDamage, double typedDamage, RPGElementType typeOfTypedDamage) {
      this.neutralDamage = neutralDamage;
      this.typedDamage = typedDamage;
      this.typeOfTypedDamage = typeOfTypedDamage;
    }
    
    public double getNeutralDamage() {
      return this.neutralDamage;
    }
    
    public double getTypedDamage() {
      return this.typedDamage;
    }
    
    public RPGElementType getTypeOfTypedDamage() {
      return this.typeOfTypedDamage;
    }
    
    private double criticalMultiplier = 1.0D;
    
    public double getCriticalMultiplier() {
      return this.criticalMultiplier;
    }
    
    private boolean critical = false;
    
    public boolean isCritical() {
      return this.critical;
    }
    
    private double resistance = 0.0D;
    
    public double getResistance() {
      return this.resistance;
    }
    
    public static DamageObject of(double neutral, double typed, RPGElementType element) {
      return new DamageObject((neutral <= 0.0D) ? 0.0D : (Math.round(neutral * 100.0D) / 100.0D), (typed <= 0.0D) ? 0.0D : (Math.round(typed * 100.0D) / 100.0D), element);
    }
    
    public Double getRawDamage() {
      double rawDamage = this.neutralDamage + this.typedDamage;
      if (this.critical)
        rawDamage *= this.criticalMultiplier; 
      return Double.valueOf(Math.round(rawDamage * 100.0D) / 100.0D);
    }
    
    public Double getApplicableDamage() {
      double applicableDamage = getRawDamage().doubleValue();
      if (this.resistance > 0.0D)
        applicableDamage *= 1.0D - this.resistance / (this.resistance + 100.0D); 
      return Double.valueOf(Math.round(applicableDamage * 100.0D) / 100.0D);
    }
    
    public void sendDamagePacket(EntityPlayerMP player, int entityId) {
      SCRPGDamagePacket packet = new SCRPGDamagePacket();
      packet.setEntityId(entityId);
      packet.setDamage(this);
      packet.send(player);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\server\manager\impl\DamageManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */