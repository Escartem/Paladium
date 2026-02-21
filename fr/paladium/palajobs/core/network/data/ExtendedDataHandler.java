package fr.paladium.palajobs.core.network.data;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ExtendedDataHandler {
  @SubscribeEvent
  public void onClonePlayer(PlayerEvent.Clone e) {
    if (e.wasDeath) {
      NBTTagCompound compound = new NBTTagCompound();
      JobsPlayer.get((Entity)e.original).saveNBTData(compound);
      JobsPlayer.get((Entity)e.entityPlayer).loadNBTData(compound);
    } 
  }
  
  @SubscribeEvent
  public void onEntityJoinWorld(EntityJoinWorldEvent event) {
    if (!event.entity.field_70170_p.field_72995_K && event.entity instanceof net.minecraft.entity.player.EntityPlayerMP) {
      JobsPlayer data = JobsPlayer.get(event.entity);
      data.sync();
    } 
  }
  
  @SubscribeEvent
  public void entityConstruct(EntityEvent.EntityConstructing e) {
    if (e.entity instanceof net.minecraft.entity.player.EntityPlayer && 
      e.entity.getExtendedProperties("palajobs_JOBS") == null)
      e.entity.registerExtendedProperties("palajobs_JOBS", new JobsPlayer()); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\network\data\ExtendedDataHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */