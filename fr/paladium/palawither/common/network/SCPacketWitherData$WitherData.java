package fr.paladium.palawither.common.network;

import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle;
import fr.paladium.palawither.common.wither.base.IWither;
import java.util.Objects;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public final class WitherData {
  private final String uniqueId;
  
  private final String name;
  
  private final float percentage;
  
  private final boolean invocation;
  
  private final boolean paused;
  
  private final boolean armored;
  
  private final long remainingTime;
  
  private final double barViewDistance;
  
  private final String barForeground;
  
  private final String barColor;
  
  private final int posX;
  
  private final int posY;
  
  private final int posZ;
  
  public String toString() {
    return "SCPacketWitherData.WitherData(uniqueId=" + getUniqueId() + ", name=" + getName() + ", percentage=" + getPercentage() + ", invocation=" + isInvocation() + ", paused=" + isPaused() + ", armored=" + isArmored() + ", remainingTime=" + getRemainingTime() + ", barViewDistance=" + getBarViewDistance() + ", barForeground=" + getBarForeground() + ", barColor=" + getBarColor() + ", posX=" + getPosX() + ", posY=" + getPosY() + ", posZ=" + getPosZ() + ")";
  }
  
  private WitherData(String uniqueId, String name, float percentage, boolean invocation, boolean paused, boolean armored, long remainingTime, double barViewDistance, String barForeground, String barColor, int posX, int posY, int posZ) {
    this.uniqueId = uniqueId;
    this.name = name;
    this.percentage = percentage;
    this.invocation = invocation;
    this.paused = paused;
    this.armored = armored;
    this.remainingTime = remainingTime;
    this.barViewDistance = barViewDistance;
    this.barForeground = barForeground;
    this.barColor = barColor;
    this.posX = posX;
    this.posY = posY;
    this.posZ = posZ;
  }
  
  public String getUniqueId() {
    return this.uniqueId;
  }
  
  public String getName() {
    return this.name;
  }
  
  public float getPercentage() {
    return this.percentage;
  }
  
  public boolean isInvocation() {
    return this.invocation;
  }
  
  public boolean isPaused() {
    return this.paused;
  }
  
  public boolean isArmored() {
    return this.armored;
  }
  
  public long getRemainingTime() {
    return this.remainingTime;
  }
  
  public double getBarViewDistance() {
    return this.barViewDistance;
  }
  
  public String getBarForeground() {
    return this.barForeground;
  }
  
  public String getBarColor() {
    return this.barColor;
  }
  
  public int getPosX() {
    return this.posX;
  }
  
  public int getPosY() {
    return this.posY;
  }
  
  public int getPosZ() {
    return this.posZ;
  }
  
  public WitherData(TileEntityWitherReceptacle receptacle) {
    if (receptacle == null)
      throw new NullPointerException("receptacle is marked non-null but is null"); 
    this.name = (receptacle.getWither() != null) ? receptacle.getWither().getDisplayName() : "";
    this.percentage = receptacle.getCooldownPercent();
    this.invocation = true;
    this.paused = !receptacle.isDecreasingCooldown();
    this.armored = false;
    this.remainingTime = receptacle.getCooldown() / 20L;
    this.barViewDistance = (receptacle.getWither() != null) ? receptacle.getWither().getViewDistance() : 0.0D;
    this.barForeground = (receptacle.getWither() != null) ? receptacle.getWither().getBarTexture() : "";
    this.barColor = (receptacle.getWither() != null) ? receptacle.getWither().getColor() : "";
    this.posX = receptacle.field_145851_c;
    this.posY = receptacle.field_145848_d;
    this.posZ = receptacle.field_145849_e;
    this.uniqueId = this.name + "_" + this.posX + "_" + this.posY + "_" + this.posZ;
  }
  
  public WitherData(@NonNull IWither wither, EntityLivingBase entity) {
    if (wither == null)
      throw new NullPointerException("wither is marked non-null but is null"); 
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    this.name = wither.getDisplayName();
    this.percentage = entity.func_110143_aJ() / entity.func_110138_aP();
    this.invocation = false;
    this.paused = false;
    this.armored = wither.isArmored();
    this.remainingTime = 0L;
    this.barViewDistance = wither.getViewDistance();
    this.barForeground = wither.getBarTexture();
    this.barColor = wither.getColor();
    this.posX = MathHelper.func_76128_c(entity.field_70165_t);
    this.posY = MathHelper.func_76128_c(entity.field_70163_u);
    this.posZ = MathHelper.func_76128_c(entity.field_70161_v);
    this.uniqueId = UUIDUtils.toString((Entity)entity);
  }
  
  public double getDistance() {
    return (Minecraft.func_71410_x()).field_71439_g.func_70011_f(this.posX, this.posY, this.posZ);
  }
  
  public boolean isInRange() {
    return (getDistance() <= this.barViewDistance);
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.posX), Integer.valueOf(this.posY), Integer.valueOf(this.posZ), Boolean.valueOf(this.invocation), Boolean.valueOf(this.paused), Float.valueOf(this.percentage), this.name });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    WitherData other = (WitherData)obj;
    return (this.posX == other.posX && this.posY == other.posY && this.posZ == other.posZ && this.invocation == other.invocation && this.paused == other.paused && this.percentage == other.percentage && Objects.equals(this.name, other.name));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\network\SCPacketWitherData$WitherData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */