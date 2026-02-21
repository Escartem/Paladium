package fr.paladium.palamod.modules.palaboss.common.eep;

import com.allatori.annotations.ControlFlowObfuscation;
import net.minecraft.nbt.NBTTagCompound;

@ControlFlowObfuscation("disable")
public class BossPlayerStat {
  private final String name;
  
  private int kills;
  
  private double damage;
  
  public BossPlayerStat(String name, int kills, double damage) {
    this.name = name;
    this.kills = kills;
    this.damage = damage;
  }
  
  public String getName() {
    return this.name;
  }
  
  public int getKills() {
    return this.kills;
  }
  
  public double getDamage() {
    return this.damage;
  }
  
  public BossPlayerStat() {
    this.name = "";
    this.kills = 0;
    this.damage = 0.0D;
  }
  
  public BossPlayerStat(String name) {
    this.name = name;
    this.kills = 0;
    this.damage = 0.0D;
  }
  
  public BossPlayerStat(NBTTagCompound compound) {
    this.name = compound.func_74779_i("name");
    this.kills = compound.func_74762_e("kills");
    this.damage = compound.func_74769_h("damage");
  }
  
  public NBTTagCompound toNBT() {
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74778_a("name", this.name);
    compound.func_74768_a("kills", this.kills);
    compound.func_74780_a("damage", this.damage);
    return compound;
  }
  
  public void addDamage(double damage) {
    this.damage += damage;
  }
  
  public void addKill() {
    this.kills++;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\eep\BossPlayerStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */