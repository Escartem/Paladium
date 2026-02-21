package fr.paladium.palamod.modules.paladium.common.items.armors.ancient.data;

import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import fr.paladium.palaforgeutils.lib.time.UniversalTimeUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class ItemAncientArmorPlayerData extends ExtendedEntityProperties {
  public static final String PROP_NAME = "palamod_ANCIENT_ARMOR";
  
  private long invisibility;
  
  private long invisibilityCooldown;
  
  private long teleportationCooldown;
  
  private long fortuneCooldown;
  
  public long getInvisibility() {
    return this.invisibility;
  }
  
  public long getInvisibilityCooldown() {
    return this.invisibilityCooldown;
  }
  
  public long getTeleportationCooldown() {
    return this.teleportationCooldown;
  }
  
  public long getFortuneCooldown() {
    return this.fortuneCooldown;
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    this.invisibility = compound.func_74763_f("invisibility");
    this.invisibilityCooldown = compound.func_74763_f("invisibilityCooldown");
    this.teleportationCooldown = compound.func_74763_f("teleportationCooldown");
    this.fortuneCooldown = compound.func_74763_f("fortuneCooldown");
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    compound.func_74772_a("invisibility", this.invisibility);
    compound.func_74772_a("invisibilityCooldown", this.invisibilityCooldown);
    compound.func_74772_a("teleportationCooldown", this.teleportationCooldown);
    compound.func_74772_a("fortuneCooldown", this.fortuneCooldown);
  }
  
  public void setFortune(long duration) {
    this.fortuneCooldown = UniversalTimeUtils.now() + duration;
    sync();
  }
  
  public void setInvisibility(long duration, long cooldown) {
    this.invisibility = UniversalTimeUtils.now() + duration;
    this.invisibilityCooldown = UniversalTimeUtils.now() + cooldown;
    sync();
  }
  
  public void setTeleportationCooldown(long cooldown) {
    this.teleportationCooldown = UniversalTimeUtils.now() + cooldown;
    sync();
  }
  
  public boolean isFortuneOnCooldown() {
    return (UniversalTimeUtils.now() < this.fortuneCooldown);
  }
  
  public boolean isInvisibilityActive() {
    return (UniversalTimeUtils.now() < this.invisibility);
  }
  
  public boolean isInvisibilityOnCooldown() {
    return (UniversalTimeUtils.now() < this.invisibilityCooldown);
  }
  
  public static ItemAncientArmorPlayerData get(EntityPlayer player) {
    return (ItemAncientArmorPlayerData)player.getExtendedProperties("palamod_ANCIENT_ARMOR");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ancient\data\ItemAncientArmorPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */