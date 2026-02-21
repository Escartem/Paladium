package fr.paladium.palarpg.module.equipment.common.rune;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import fr.paladium.palarpg.module.equipment.common.loader.util.ItemStatMutator;
import fr.paladium.palarpg.module.equipment.common.manager.EquipmentRuneManager;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatMutationType;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;

public class RPGRuneStat {
  private EnumStats stats;
  
  private StatMutationType mutationType;
  
  private List<Float> coefs;
  
  public void setStats(EnumStats stats) {
    this.stats = stats;
  }
  
  public void setMutationType(StatMutationType mutationType) {
    this.mutationType = mutationType;
  }
  
  public void setCoefs(List<Float> coefs) {
    this.coefs = coefs;
  }
  
  public String toString() {
    return "RPGRuneStat(stats=" + getStats() + ", mutationType=" + getMutationType() + ", coefs=" + getCoefs() + ")";
  }
  
  private RPGRuneStat() {}
  
  public static RPGRuneStat create() {
    return new RPGRuneStat();
  }
  
  private RPGRuneStat(EnumStats stats, StatMutationType mutationType, List<Float> coefs) {
    this.stats = stats;
    this.mutationType = mutationType;
    this.coefs = coefs;
  }
  
  public static RPGRuneStat of(EnumStats stats, StatMutationType mutationType, List<Float> coefs) {
    return new RPGRuneStat(stats, mutationType, coefs);
  }
  
  public EnumStats getStats() {
    return this.stats;
  }
  
  public StatMutationType getMutationType() {
    return this.mutationType;
  }
  
  public List<Float> getCoefs() {
    return this.coefs;
  }
  
  public ItemStatMutator getMutator(int tier) {
    return new ItemStatMutator(this.stats, new ItemStatMutator.Mutator((JsonElement)new JsonPrimitive(Double.valueOf(EquipmentRuneManager.getValue(this, tier))), this.mutationType), 0);
  }
  
  public float getSumCoef() {
    return (this.coefs != null) ? ((Float)this.coefs.stream().reduce(Float.valueOf(0.0F), Float::sum)).floatValue() : 0.0F;
  }
  
  public void addCoef(float coef) {
    if (this.coefs == null)
      this.coefs = new ArrayList<>(); 
    this.coefs.add(Float.valueOf(coef));
  }
  
  public void addCoef(List<Float> coefs) {
    if (this.coefs == null)
      this.coefs = new ArrayList<>(); 
    this.coefs.addAll(coefs);
  }
  
  public void read(NBTTagCompound nbt) {
    this.stats = EnumStats.fromOrdinal(nbt.func_74762_e("stats"));
    this.mutationType = StatMutationType.fromOrdinal(nbt.func_74762_e("mutationType"));
    this.coefs = new ArrayList<>();
    NBTTagList coefList = nbt.func_150295_c("coefs", 5);
    for (int i = 0; i < coefList.func_74745_c(); i++)
      this.coefs.add(Float.valueOf(coefList.func_150308_e(i))); 
  }
  
  public void write(NBTTagCompound nbt) {
    nbt.func_74768_a("stats", this.stats.ordinal());
    nbt.func_74768_a("mutationType", this.mutationType.ordinal());
    NBTTagList coefList = new NBTTagList();
    if (this.coefs != null)
      for (Float coef : this.coefs)
        coefList.func_74742_a((NBTBase)new NBTTagFloat(coef.floatValue()));  
    nbt.func_74782_a("coefs", (NBTBase)coefList);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\rune\RPGRuneStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */