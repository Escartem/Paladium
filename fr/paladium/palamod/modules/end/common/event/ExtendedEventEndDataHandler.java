package fr.paladium.palamod.modules.end.common.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.end.common.network.ExtendedEventEndEntity;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ExtendedEventEndDataHandler {
  @SubscribeEvent
  public void onClonePlayer(PlayerEvent.Clone e) {
    if (e.wasDeath) {
      NBTTagCompound compound = new NBTTagCompound();
      ExtendedEventEndEntity.get((Entity)e.original).saveNBTData(compound);
      ExtendedEventEndEntity.get((Entity)e.entityPlayer).loadNBTData(compound);
    } 
  }
  
  @SubscribeEvent
  public void onEntityJoinWorld(EntityJoinWorldEvent event) {
    if (!event.entity.field_70170_p.field_72995_K && event.entity instanceof net.minecraft.entity.player.EntityPlayerMP) {
      ExtendedEventEndEntity data = ExtendedEventEndEntity.get(event.entity);
      data.dataChanged(event.entity);
    } 
  }
  
  @SubscribeEvent
  public void entityConstruct(EntityEvent.EntityConstructing e) {
    if (e.entity instanceof net.minecraft.entity.player.EntityPlayer && 
      e.entity.getExtendedProperties("palamod_END_EVENT") == null)
      e.entity.registerExtendedProperties("palamod_END_EVENT", (IExtendedEntityProperties)new ExtendedEventEndEntity()); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\common\event\ExtendedEventEndDataHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */