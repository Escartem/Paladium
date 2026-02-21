package fr.paladium.palamod.modules.communityevents.eep;

import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class PlayerCakeEEP extends ExtendedEntityProperties {
  public static final String PROPERTY_NAME = "ExtPropPalaGaletteCake";
  
  public long lastObtainedBlock = 0L;
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound property = new NBTTagCompound();
    property.func_74772_a("lastObtainedBlock", this.lastObtainedBlock);
    compound.func_74782_a("ExtPropPalaGaletteCake", (NBTBase)property);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    NBTTagCompound property = compound.func_74775_l("ExtPropPalaGaletteCake");
    this.lastObtainedBlock = property.func_74762_e("lastObtainedBlock");
  }
  
  public void init(Entity entity, World world) {
    this.lastObtainedBlock = 0L;
    setPropName("ExtPropPalaGaletteCake");
  }
  
  public boolean canObtainBlock() {
    return (System.currentTimeMillis() - this.lastObtainedBlock >= 86400000L);
  }
  
  public void setLastObtainedBlock(long timeObtainedInMillis) {
    this.lastObtainedBlock = timeObtainedInMillis;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\eep\PlayerCakeEEP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */