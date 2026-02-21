package fr.paladium.palamod.modules.pvp.dto;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class PvpStat {
  private String scope;
  
  private long kills;
  
  private long deaths;
  
  private long killStreak;
  
  private long currentKillStreak;
  
  private long combo;
  
  private long currentCombo;
  
  private long lastDamageDealt;
  
  private double damageDealt;
  
  private double damageTaken;
  
  public PvpStat(String scope, long kills, long deaths, long killStreak, long currentKillStreak, long combo, long currentCombo, long lastDamageDealt, double damageDealt, double damageTaken) {
    this.scope = scope;
    this.kills = kills;
    this.deaths = deaths;
    this.killStreak = killStreak;
    this.currentKillStreak = currentKillStreak;
    this.combo = combo;
    this.currentCombo = currentCombo;
    this.lastDamageDealt = lastDamageDealt;
    this.damageDealt = damageDealt;
    this.damageTaken = damageTaken;
  }
  
  public String getScope() {
    return this.scope;
  }
  
  public long getKills() {
    return this.kills;
  }
  
  public long getDeaths() {
    return this.deaths;
  }
  
  public long getKillStreak() {
    return this.killStreak;
  }
  
  public long getCurrentKillStreak() {
    return this.currentKillStreak;
  }
  
  public long getCombo() {
    return this.combo;
  }
  
  public long getCurrentCombo() {
    return this.currentCombo;
  }
  
  public long getLastDamageDealt() {
    return this.lastDamageDealt;
  }
  
  public double getDamageDealt() {
    return this.damageDealt;
  }
  
  public double getDamageTaken() {
    return this.damageTaken;
  }
  
  public PvpStat(NBTTagCompound compound) {
    this.scope = compound.func_74779_i("scope");
    this.kills = compound.func_74763_f("kills");
    this.deaths = compound.func_74763_f("deaths");
    this.killStreak = compound.func_74763_f("killStreak");
    this.currentKillStreak = compound.func_74763_f("currentKillStreak");
    this.damageDealt = compound.func_74769_h("damageDealt");
    this.damageTaken = compound.func_74769_h("damageTaken");
    this.combo = compound.func_74763_f("combo");
    this.currentCombo = compound.func_74763_f("currentCombo");
    this.lastDamageDealt = compound.func_74763_f("lastDamageDealt");
  }
  
  public PvpStat(String scope) {
    this();
    this.scope = scope;
  }
  
  public PvpStat() {
    this.kills = 0L;
    this.deaths = 0L;
    this.killStreak = 0L;
    this.currentKillStreak = 0L;
    this.damageDealt = 0.0D;
    this.damageTaken = 0.0D;
    this.combo = 0L;
    this.currentCombo = 0L;
    this.lastDamageDealt = 0L;
  }
  
  public void incrementKills() {
    this.kills++;
  }
  
  public void incrementDeaths() {
    this.deaths++;
  }
  
  public void increaseKillStreak() {
    this.currentKillStreak++;
    this.killStreak = Math.max(this.killStreak, this.currentKillStreak);
  }
  
  public void incrementDamageDealt(double damage) {
    this.damageDealt += damage;
  }
  
  public void incrementDamageTaken(double damage) {
    this.damageTaken += damage;
  }
  
  public void setLastDamageDealt(long lastDamageDealt) {
    this.lastDamageDealt = lastDamageDealt;
  }
  
  public void increaseCombo() {
    this.currentCombo++;
    this.combo = Math.max(this.combo, this.currentCombo);
  }
  
  public void resetCombo() {
    this.currentCombo = 0L;
  }
  
  public void resetKillStreak() {
    this.currentKillStreak = 0L;
  }
  
  public NBTBase toNBT() {
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74778_a("scope", this.scope);
    compound.func_74772_a("kills", this.kills);
    compound.func_74772_a("deaths", this.deaths);
    compound.func_74772_a("killStreak", this.killStreak);
    compound.func_74772_a("currentKillStreak", this.currentKillStreak);
    compound.func_74780_a("damageDealt", this.damageDealt);
    compound.func_74780_a("damageTaken", this.damageTaken);
    compound.func_74772_a("combo", this.combo);
    compound.func_74772_a("currentCombo", this.currentCombo);
    compound.func_74772_a("lastDamageDealt", this.lastDamageDealt);
    return (NBTBase)compound;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pvp\dto\PvpStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */