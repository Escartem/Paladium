package fr.paladium.palatrade.lib.odin.modules.extended.lib;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palatrade.lib.odin.modules.extended.OdinExtendedModule;
import fr.paladium.palatrade.lib.odin.modules.extended.internal.network.SCSyncExtendedEntityProperties;
import fr.paladium.palatrade.lib.odin.modules.extended.internal.utils.ExtendedData;
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
  
  private Entity entity;
  
  public void init(Entity entity, World world) {
    this.entity = entity;
  }
  
  public void sync() {
    if (!(this.entity instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP target = (EntityPlayerMP)this.entity;
    NBTTagCompound compound = new NBTTagCompound();
    saveNBTData(compound);
    SCSyncExtendedEntityProperties packet = new SCSyncExtendedEntityProperties(this.propName, compound);
    OdinExtendedModule.getInstance().getNetwork().sendTo((IMessage)packet, target);
    if (((ExtendedData)OdinExtendedModule.getInstance().getExtended().get(this.propName)).getProperties().contains(ExtendedProperty.SYNCHRONIZED_TRACKER)) {
      EntityTracker tracker = ((WorldServer)target.field_70170_p).func_73039_n();
      for (EntityPlayer entityPlayer : tracker.getTrackingPlayers((Entity)target))
        OdinExtendedModule.getInstance().getNetwork().sendTo((IMessage)packet, (EntityPlayerMP)entityPlayer); 
    } 
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


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\extended\lib\ExtendedEntityProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */