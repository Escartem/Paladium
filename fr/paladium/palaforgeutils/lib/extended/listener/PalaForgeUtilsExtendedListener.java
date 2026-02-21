package fr.paladium.palaforgeutils.lib.extended.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.extended.ExtendedData;
import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PalaForgeUtilsExtendedListener {
  @SubscribeEvent
  public void onStartTracking(PlayerEvent.StartTracking event) {
    if (!(event.entityPlayer instanceof EntityPlayerMP))
      return; 
    for (Map.Entry<String, ExtendedData> extended : (Iterable<Map.Entry<String, ExtendedData>>)ExtendedUtils.getExtended().entrySet()) {
      String propName = extended.getKey();
      ExtendedData data = extended.getValue();
      if (data.getProperties().contains(ExtendedProperty.SYNCHRONIZED_TRACKER)) {
        IExtendedEntityProperties prop = event.target.getExtendedProperties(propName);
        if (!(prop instanceof ExtendedEntityProperties))
          continue; 
        ((ExtendedEntityProperties)prop).sync((EntityPlayerMP)event.entityPlayer);
      } 
    } 
  }
  
  @SubscribeEvent
  public void onClonePlayer(PlayerEvent.Clone event) {
    if (event.wasDeath)
      for (Map.Entry<String, ExtendedData> extended : (Iterable<Map.Entry<String, ExtendedData>>)ExtendedUtils.getExtended().entrySet()) {
        String propName = extended.getKey();
        ExtendedData data = extended.getValue();
        if (data.getProperties().contains(ExtendedProperty.PERSISTANT) && data.getEntityType().isAssignableFrom(event.entity.getClass())) {
          NBTTagCompound compound = new NBTTagCompound();
          event.original.getExtendedProperties(propName).saveNBTData(compound);
          event.entityPlayer.getExtendedProperties(propName).loadNBTData(compound);
        } 
      }  
  }
  
  @SubscribeEvent
  public void onEntityJoinWorld(EntityJoinWorldEvent event) {
    for (Map.Entry<String, ExtendedData> extended : (Iterable<Map.Entry<String, ExtendedData>>)ExtendedUtils.getExtended().entrySet()) {
      String propName = extended.getKey();
      ExtendedData data = extended.getValue();
      if (data.getEntityType().isAssignableFrom(event.entity.getClass()))
        ((ExtendedEntityProperties)event.entity.getExtendedProperties(propName)).sync(); 
    } 
  }
  
  @SubscribeEvent
  public void onEntityConstruct(EntityEvent.EntityConstructing event) {
    for (Map.Entry<String, ExtendedData> extended : (Iterable<Map.Entry<String, ExtendedData>>)ExtendedUtils.getExtended().entrySet()) {
      String propName = extended.getKey();
      ExtendedData data = extended.getValue();
      if (data.getProperties().contains(ExtendedProperty.SELF_CONSTRUCT) && data.getEntityType().isAssignableFrom(event.entity.getClass()) && 
        event.entity.getExtendedProperties(propName) == null)
        try {
          ExtendedEntityProperties instanciedExtended = data.getExtended().newInstance();
          instanciedExtended.onRegister();
          instanciedExtended.setPropName(propName);
          event.entity.registerExtendedProperties(propName, (IExtendedEntityProperties)instanciedExtended);
        } catch (InstantiationException|IllegalAccessException e) {
          System.out.println("[Paladium] Unable to register extended " + propName + " to " + event.entity);
          e.printStackTrace();
        }  
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\extended\listener\PalaForgeUtilsExtendedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */