package fr.paladium.palarpg.module.stat.server.manager.impl;

import fr.paladium.palarpg.module.entity.common.entity.RPGElementType;
import fr.paladium.palarpg.module.stat.common.network.SCRPGDamagePacket;
import net.minecraft.entity.player.EntityPlayerMP;

public class DamageObject {
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


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\server\manager\impl\DamageManager$DamageObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */