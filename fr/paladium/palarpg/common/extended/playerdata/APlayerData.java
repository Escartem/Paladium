package fr.paladium.palarpg.common.extended.playerdata;

import fr.paladium.palarpg.common.extended.RPGPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

public abstract class APlayerData implements INbt {
  private EntityLivingBase entity;
  
  private String clazzName;
  
  private String name;
  
  public void setEntity(EntityLivingBase entity) {
    this.entity = entity;
  }
  
  public EntityLivingBase getEntity() {
    return this.entity;
  }
  
  public String getName() {
    if (this.name != null)
      return this.name; 
    this.name = ((PlayerData)getClass().<PlayerData>getAnnotation(PlayerData.class)).value();
    return this.name;
  }
  
  public String getClazzName() {
    if (this.clazzName != null)
      return this.clazzName; 
    this.clazzName = getClass().getName();
    return this.clazzName;
  }
  
  public void sync() {
    RPGPlayer.get((Entity)getEntity()).sync();
  }
  
  public void read(NBTTagCompound nbt, boolean saving) {}
  
  public void write(NBTTagCompound nbt, boolean saving) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\common\extended\playerdata\APlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */