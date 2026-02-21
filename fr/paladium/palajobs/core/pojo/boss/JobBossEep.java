package fr.paladium.palajobs.core.pojo.boss;

import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class JobBossEep extends ExtendedEntityProperties {
  public static final String PROP_NAME = "boss_job_eep";
  
  private Map<String, BossPlayerStat> stats;
  
  public Map<String, BossPlayerStat> getStats() {
    return this.stats;
  }
  
  public static JobBossEep get(Entity entity) {
    return (JobBossEep)entity.getExtendedProperties("boss_job_eep");
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    if (this.stats == null) {
      this.stats = new HashMap<>();
      return;
    } 
    NBTTagList list = new NBTTagList();
    this.stats.forEach((boss, stat) -> list.func_74742_a((NBTBase)stat.toNBT()));
    compound.func_74782_a("boss_job_eep", (NBTBase)list);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (this.stats == null)
      this.stats = new HashMap<>(); 
    this.stats.clear();
    if (!compound.func_74764_b("boss_job_eep"))
      return; 
    NBTTagList list = compound.func_150295_c("boss_job_eep", 10);
    for (int i = 0; i < list.func_74745_c(); i++) {
      NBTTagCompound statCompound = list.func_150305_b(i);
      BossPlayerStat bossStat = new BossPlayerStat(statCompound);
      this.stats.put(bossStat.getName(), bossStat);
    } 
  }
  
  @Nonnull
  public BossPlayerStat getBossStat(String name) {
    if (this.stats == null)
      this.stats = new HashMap<>(); 
    return this.stats.computeIfAbsent(name, s -> new BossPlayerStat(name));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\pojo\boss\JobBossEep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */