package fr.paladium.palaforgeutils.lib.extended;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.PalaForgeUtilsMod;
import fr.paladium.palaforgeutils.lib.extended.network.SCSyncPalaForgeUtilsExtendedEntityProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.IExtendedEntityProperties;

public abstract class ExtendedEntityProperties implements IExtendedEntityProperties {
  private String propName;
  
  private transient Entity entity;
  
  public void init(Entity entity, World world) {
    this.entity = entity;
  }
  
  public void onRegister() {}
  
  public void sync() {
    if (!(this.entity instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP target = (EntityPlayerMP)this.entity;
    NBTTagCompound compound = new NBTTagCompound();
    saveNBTData(compound);
    ExtendedData data = ExtendedUtils.getExtended().get(this.propName);
    SCSyncPalaForgeUtilsExtendedEntityProperties packet = new SCSyncPalaForgeUtilsExtendedEntityProperties(this.entity.func_110124_au(), this.propName, compound);
    if (data.getProperties().contains(ExtendedProperty.SYNCHRONIZED))
      PalaForgeUtilsMod.proxy.getNetwork().sendTo((IMessage)packet, target); 
    if (data.getProperties().contains(ExtendedProperty.SYNCHRONIZED_TRACKER)) {
      EntityTracker tracker = ((WorldServer)target.field_70170_p).func_73039_n();
      for (EntityPlayer entityPlayer : tracker.getTrackingPlayers((Entity)target))
        PalaForgeUtilsMod.proxy.getNetwork().sendTo((IMessage)packet, (EntityPlayerMP)entityPlayer); 
    } 
  }
  
  public void sync(EntityPlayerMP target) {
    NBTTagCompound compound = new NBTTagCompound();
    saveNBTData(compound);
    SCSyncPalaForgeUtilsExtendedEntityProperties packet = new SCSyncPalaForgeUtilsExtendedEntityProperties(this.entity.func_110124_au(), this.propName, compound);
    PalaForgeUtilsMod.proxy.getNetwork().sendTo((IMessage)packet, target);
  }
  
  public void setPropName(String propName) {
    this.propName = propName;
  }
  
  public String getPropName() {
    return this.propName;
  }
  
  public Entity getEntity() {
    return this.entity;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\extended\ExtendedEntityProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */