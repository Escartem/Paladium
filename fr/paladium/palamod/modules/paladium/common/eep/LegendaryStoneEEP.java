package fr.paladium.palamod.modules.paladium.common.eep;

import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class LegendaryStoneEEP extends ExtendedEntityProperties {
  public static final String PROP_NAME = "legendarystone_eep";
  
  private final Map<String, Long> uses = new HashMap<>();
  
  public Map<String, Long> getUses() {
    return this.uses;
  }
  
  public static LegendaryStoneEEP get(Entity entity) {
    return (LegendaryStoneEEP)entity.getExtendedProperties("legendarystone_eep");
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound usesCompound = new NBTTagCompound();
    this.uses.forEach(usesCompound::func_74772_a);
    compound.func_74782_a("legendarystone_eep", (NBTBase)usesCompound);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    NBTTagCompound usesCompound = compound.func_74775_l("legendarystone_eep");
    this.uses.clear();
    for (Object k : usesCompound.func_150296_c()) {
      String key = (String)k;
      this.uses.put(key, Long.valueOf(usesCompound.func_74763_f(key)));
    } 
  }
  
  public void increaseUse(String name) {
    this.uses.put(name, Long.valueOf(((Long)this.uses.getOrDefault(name, Long.valueOf(0L))).longValue() + 1L));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\eep\LegendaryStoneEEP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */