package fr.paladium.palatrade.lib.odin.modules.extended.internal.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palatrade.lib.odin.modules.extended.OdinExtendedModule;
import fr.paladium.palatrade.lib.odin.modules.extended.internal.utils.ExtendedData;
import fr.paladium.palatrade.lib.odin.modules.extended.lib.ExtendedEntityProperties;
import fr.paladium.palatrade.lib.odin.modules.extended.lib.ExtendedProperty;
import java.util.Map;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class OdinExtendedListener {
  @SubscribeEvent
  public void onClonePlayer(PlayerEvent.Clone event) {
    if (event.wasDeath)
      for (Map.Entry<String, ExtendedData> extended : (Iterable<Map.Entry<String, ExtendedData>>)OdinExtendedModule.getInstance().getExtended().entrySet()) {
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
    for (Map.Entry<String, ExtendedData> extended : (Iterable<Map.Entry<String, ExtendedData>>)OdinExtendedModule.getInstance().getExtended().entrySet()) {
      String propName = extended.getKey();
      ExtendedData data = extended.getValue();
      if (data.getProperties().contains(ExtendedProperty.SYNCHRONIZED) && data.getEntityType().isAssignableFrom(event.entity.getClass()))
        ((ExtendedEntityProperties)event.entity.getExtendedProperties(propName)).sync(); 
    } 
  }
  
  @SubscribeEvent
  public void onEntityConstruct(EntityEvent.EntityConstructing event) {
    for (Map.Entry<String, ExtendedData> extended : (Iterable<Map.Entry<String, ExtendedData>>)OdinExtendedModule.getInstance().getExtended().entrySet()) {
      String propName = extended.getKey();
      ExtendedData data = extended.getValue();
      if (data.getProperties().contains(ExtendedProperty.SELF_CONSTRUCT) && data.getEntityType().isAssignableFrom(event.entity.getClass()) && 
        event.entity.getExtendedProperties(propName) == null)
        try {
          ExtendedEntityProperties instanciedExtended = data.getExtended().newInstance();
          instanciedExtended.setPropName(propName);
          event.entity.registerExtendedProperties(propName, (IExtendedEntityProperties)instanciedExtended);
        } catch (InstantiationException|IllegalAccessException e) {
          System.out.println("[Odin] Unable to register extended " + propName + " to " + event.entity);
          e.printStackTrace();
        }  
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\extended\internal\listener\OdinExtendedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */