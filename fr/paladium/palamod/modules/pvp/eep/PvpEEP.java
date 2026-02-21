package fr.paladium.palamod.modules.pvp.eep;

import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import fr.paladium.palamod.modules.pvp.dto.PvpStat;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class PvpEEP extends ExtendedEntityProperties {
  public static final String PROP_NAME = "pvp_eep";
  
  private Map<String, PvpStat> stats;
  
  public Map<String, PvpStat> getStats() {
    return this.stats;
  }
  
  public static PvpEEP get(Entity entity) {
    return (PvpEEP)entity.getExtendedProperties("pvp_eep");
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    if (this.stats == null) {
      this.stats = new HashMap<>();
      return;
    } 
    NBTTagList list = new NBTTagList();
    this.stats.forEach((scope, stat) -> list.func_74742_a(stat.toNBT()));
    compound.func_74782_a("pvp_eep", (NBTBase)list);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (this.stats == null)
      this.stats = new HashMap<>(); 
    this.stats.clear();
    if (!compound.func_74764_b("pvp_eep"))
      return; 
    NBTTagList list = compound.func_150295_c("pvp_eep", 10);
    for (int i = 0; i < list.func_74745_c(); i++) {
      NBTTagCompound statCompound = list.func_150305_b(i);
      PvpStat pvpStat = new PvpStat(statCompound);
      this.stats.put(pvpStat.getScope(), pvpStat);
    } 
  }
  
  @Nonnull
  public PvpStat getGlobalScope() {
    return getOrCreateStat("global");
  }
  
  @Nonnull
  public PvpStat getOrCreateStat(String scope) {
    if (this.stats == null)
      this.stats = new HashMap<>(); 
    return this.stats.computeIfAbsent(scope, s -> new PvpStat(scope));
  }
  
  @Nullable
  public PvpStat getStat(String scope) {
    return (this.stats != null) ? this.stats.get(scope) : null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pvp\eep\PvpEEP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */