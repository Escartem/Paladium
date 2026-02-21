package fr.paladium.palamod.modules.paladium.common.items.sword.ancient.data;

import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import fr.paladium.palaforgeutils.lib.time.UniversalTimeUtils;
import fr.paladium.palamod.modules.paladium.client.ui.overlay.UIOverlayAncientHammerFortuneEffect;
import fr.paladium.palamod.modules.paladium.client.ui.overlay.UIOverlayAncientHammerPowerEffect;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ItemAncientHammerPlayerData extends ExtendedEntityProperties {
  public static final String PROP_NAME = "palamod_ANCIENT_HAMMER";
  
  public static final double MAX_DAMAGE_FOR_EFFECT = 19.5D;
  
  private long chaosLastUse;
  
  private long teleportationLastUse;
  
  private long invisibilityLastUse;
  
  private long powerLastUse;
  
  private long fortuneLastUse;
  
  private long jobLastUse;
  
  private long fortuneEffect;
  
  private long powerEffect;
  
  private float powerEffectDamage;
  
  private long invisibilityEffect;
  
  private long effectCamActivation;
  
  public String toString() {
    return "ItemAncientHammerPlayerData(chaosLastUse=" + getChaosLastUse() + ", teleportationLastUse=" + getTeleportationLastUse() + ", invisibilityLastUse=" + getInvisibilityLastUse() + ", powerLastUse=" + getPowerLastUse() + ", fortuneLastUse=" + getFortuneLastUse() + ", jobLastUse=" + getJobLastUse() + ", fortuneEffect=" + getFortuneEffect() + ", powerEffect=" + getPowerEffect() + ", powerEffectDamage=" + getPowerEffectDamage() + ", invisibilityEffect=" + getInvisibilityEffect() + ", effectCamActivation=" + getEffectCamActivation() + ", powerEffectPlayed=" + isPowerEffectPlayed() + ")";
  }
  
  public long getChaosLastUse() {
    return this.chaosLastUse;
  }
  
  public long getTeleportationLastUse() {
    return this.teleportationLastUse;
  }
  
  public long getInvisibilityLastUse() {
    return this.invisibilityLastUse;
  }
  
  public long getPowerLastUse() {
    return this.powerLastUse;
  }
  
  public long getFortuneLastUse() {
    return this.fortuneLastUse;
  }
  
  public long getJobLastUse() {
    return this.jobLastUse;
  }
  
  public long getFortuneEffect() {
    return this.fortuneEffect;
  }
  
  public long getPowerEffect() {
    return this.powerEffect;
  }
  
  public float getPowerEffectDamage() {
    return this.powerEffectDamage;
  }
  
  public long getInvisibilityEffect() {
    return this.invisibilityEffect;
  }
  
  public long getEffectCamActivation() {
    return this.effectCamActivation;
  }
  
  private boolean powerEffectPlayed = true;
  
  public void setPowerEffectPlayed(boolean powerEffectPlayed) {
    this.powerEffectPlayed = powerEffectPlayed;
  }
  
  public boolean isPowerEffectPlayed() {
    return this.powerEffectPlayed;
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound playerData = new NBTTagCompound();
    playerData.func_74772_a("chaosLastUse", this.chaosLastUse);
    playerData.func_74772_a("teleportationLastUse", this.teleportationLastUse);
    playerData.func_74772_a("invisibilityLastUse", this.invisibilityLastUse);
    playerData.func_74772_a("powerLastUse", this.powerLastUse);
    playerData.func_74772_a("fortuneLastUse", this.fortuneLastUse);
    playerData.func_74772_a("jobLastUse", this.jobLastUse);
    playerData.func_74772_a("effectCamActivation", this.effectCamActivation);
    playerData.func_74772_a("fortuneEffect", this.fortuneEffect);
    playerData.func_74772_a("powerEffect", this.powerEffect);
    playerData.func_74776_a("powerEffectDamage", this.powerEffectDamage);
    playerData.func_74772_a("invisibilityEffect", this.invisibilityEffect);
    playerData.func_74757_a("powerEffectPlayed", this.powerEffectPlayed);
    compound.func_74782_a("palamod_ANCIENT_HAMMER", (NBTBase)playerData);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    NBTTagCompound playerData = compound.func_74775_l("palamod_ANCIENT_HAMMER");
    this.chaosLastUse = playerData.func_74763_f("chaosLastUse");
    this.teleportationLastUse = playerData.func_74763_f("teleportationLastUse");
    this.invisibilityLastUse = playerData.func_74763_f("invisibilityLastUse");
    this.powerLastUse = playerData.func_74763_f("powerLastUse");
    this.fortuneLastUse = playerData.func_74763_f("fortuneLastUse");
    this.jobLastUse = playerData.func_74763_f("jobLastUse");
    this.effectCamActivation = playerData.func_74763_f("effectCamActivation");
    this.fortuneEffect = playerData.func_74763_f("fortuneEffect");
    this.powerEffect = playerData.func_74763_f("powerEffect");
    this.powerEffectDamage = playerData.func_74760_g("powerEffectDamage");
    this.invisibilityEffect = playerData.func_74763_f("invisibilityEffect");
    this.powerEffectPlayed = playerData.func_74764_b("powerEffectPlayed") ? playerData.func_74767_n("powerEffectPlayed") : true;
  }
  
  public boolean isOnCooldown(LegendaryStone.Effect effect) {
    switch (effect) {
      case CHAOS:
        return (UniversalTimeUtils.now() < this.chaosLastUse);
      case TELEPORTATION:
        return (UniversalTimeUtils.now() < this.teleportationLastUse);
      case INVISIBILITY:
        return (UniversalTimeUtils.now() < this.invisibilityLastUse);
      case POWER:
        return (UniversalTimeUtils.now() < this.powerLastUse);
      case FORTUNE:
        return (UniversalTimeUtils.now() < this.fortuneLastUse);
      case JOBS:
        return (UniversalTimeUtils.now() < this.jobLastUse);
    } 
    return true;
  }
  
  public void setLastUse(LegendaryStone.Effect effect) {
    switch (effect) {
      case CHAOS:
        this.chaosLastUse = UniversalTimeUtils.now() + 3600000L;
        sync();
        return;
      case TELEPORTATION:
        this.teleportationLastUse = UniversalTimeUtils.now() + 3600000L;
        sync();
        return;
      case INVISIBILITY:
        this.invisibilityLastUse = UniversalTimeUtils.now() + 43200000L;
        sync();
        return;
      case POWER:
        this.powerLastUse = UniversalTimeUtils.now() + 3600000L;
        setPowerEffect();
        if (getEntity() instanceof EntityPlayerMP)
          ZUIServer.open(UIOverlayAncientHammerPowerEffect.class, (EntityPlayerMP)getEntity()); 
        sync();
        return;
      case FORTUNE:
        this.fortuneLastUse = UniversalTimeUtils.now() + 43200000L;
        this.fortuneEffect = UniversalTimeUtils.now() + 33000L;
        ((EntityPlayer)getEntity()).func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §bVous gagnerez 1% de l'argent des joueurs tués pendant 30 secondes."));
        sync();
        if (getEntity() instanceof EntityPlayerMP)
          ZUIServer.open(UIOverlayAncientHammerFortuneEffect.class, (EntityPlayerMP)getEntity()); 
        return;
      case JOBS:
        this.jobLastUse = UniversalTimeUtils.now() + 3600000L;
        sync();
        break;
    } 
  }
  
  public long getLastUse(LegendaryStone.Effect effect) {
    switch (effect) {
      case CHAOS:
        return getChaosLastUse();
      case TELEPORTATION:
        return getTeleportationLastUse();
      case INVISIBILITY:
        return getInvisibilityLastUse();
      case POWER:
        return getPowerLastUse();
      case FORTUNE:
        return getFortuneLastUse();
      case JOBS:
        return getJobLastUse();
    } 
    return 0L;
  }
  
  public boolean isFortuneEffectActive() {
    return (UniversalTimeUtils.now() < this.fortuneEffect);
  }
  
  public void setPowerEffect() {
    this.powerEffectDamage = 0.0F;
    this.powerEffect = UniversalTimeUtils.now() + 30000L;
    this.powerEffectPlayed = false;
  }
  
  public void disablePowerEffect() {
    this.powerEffectDamage = 0.0F;
    this.powerEffect = 0L;
    this.powerEffectPlayed = true;
    sync();
  }
  
  public void addPowerEffectDamage(float damage) {
    if (!isPowerEffectActive())
      return; 
    this.powerEffectDamage += damage;
    this.powerEffectDamage = (float)Math.min(this.powerEffectDamage, 19.5D);
    sync();
  }
  
  public boolean isPowerEffectActive() {
    return (UniversalTimeUtils.now() < this.powerEffect);
  }
  
  public void setInvisibilityEffect(boolean invisibilityEffect) {
    this.invisibilityEffect = invisibilityEffect ? UniversalTimeUtils.now() : 0L;
    sync();
  }
  
  public boolean isInvisibilityEffect() {
    return (this.invisibilityEffect > 0L);
  }
  
  public boolean isInvisibilityInvulnerable() {
    return (this.invisibilityEffect > 0L && this.invisibilityEffect + 3000L > UniversalTimeUtils.now());
  }
  
  public void setEffectCamActive(boolean effectCamActive) {
    this.effectCamActivation = effectCamActive ? UniversalTimeUtils.now() : 0L;
    sync();
  }
  
  public boolean isEffectCamActive() {
    return (this.effectCamActivation > 0L && this.effectCamActivation + 2000L > UniversalTimeUtils.now());
  }
  
  public static ItemAncientHammerPlayerData me() {
    if ((Minecraft.func_71410_x()).field_71439_g == null)
      return null; 
    return (ItemAncientHammerPlayerData)(Minecraft.func_71410_x()).field_71439_g.getExtendedProperties("palamod_ANCIENT_HAMMER");
  }
  
  public static ItemAncientHammerPlayerData get(EntityPlayer player) {
    return (ItemAncientHammerPlayerData)player.getExtendedProperties("palamod_ANCIENT_HAMMER");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\sword\ancient\data\ItemAncientHammerPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */