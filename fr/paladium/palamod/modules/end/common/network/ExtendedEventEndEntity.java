package fr.paladium.palamod.modules.end.common.network;

import fr.paladium.palamod.modules.end.PEnd;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedEventEndEntity implements IExtendedEntityProperties {
  public static final String PROP_NAME = "palamod_END_EVENT";
  
  private Entity entity;
  
  private boolean pickup;
  
  private int eggCount;
  
  public void init(Entity entity, World world) {
    this.entity = entity;
  }
  
  public static ExtendedEventEndEntity get(Entity p) {
    return (ExtendedEventEndEntity)p.getExtendedProperties("palamod_END_EVENT");
  }
  
  public void dataChanged(Entity e) {
    if (e instanceof EntityPlayerMP)
      dataChanged((EntityPlayerMP)e); 
  }
  
  public void dataChanged(EntityPlayerMP player) {
    if (!player.field_70170_p.field_72995_K) {
      NBTTagCompound nbt = new NBTTagCompound();
      saveNBTData(nbt);
      PEnd.getInstance();
      PEnd.getProxy().getNetwork().sendTo(new SCSyncExtendedEventEndEntityData(nbt), player);
    } 
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    compound.func_74768_a("eggCount", this.eggCount);
    compound.func_74757_a("pickup", this.pickup);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (compound.func_74764_b("eggCount"))
      this.eggCount = compound.func_74762_e("eggCount"); 
    if (compound.func_74764_b("pickup"))
      this.pickup = compound.func_74767_n("pickup"); 
  }
  
  public int getEggCount() {
    return this.eggCount;
  }
  
  public boolean isPickup() {
    return this.pickup;
  }
  
  public void incrementEggCount(int value) {
    this.eggCount += value;
  }
  
  public void setPickup(boolean pickup) {
    this.pickup = pickup;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\common\network\ExtendedEventEndEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */