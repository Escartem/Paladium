package fr.paladium.palatrade.lib.odin.modules.extended;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palatrade.lib.odin.modules.AOdinModule;
import fr.paladium.palatrade.lib.odin.modules.extended.internal.listener.OdinExtendedListener;
import fr.paladium.palatrade.lib.odin.modules.extended.internal.network.SCSyncExtendedEntityProperties;
import fr.paladium.palatrade.lib.odin.modules.extended.internal.utils.ExtendedData;
import fr.paladium.palatrade.lib.odin.modules.extended.lib.ExtendedEntityProperties;
import fr.paladium.palatrade.lib.odin.modules.extended.lib.ExtendedProperty;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.Entity;

public class OdinExtendedModule extends AOdinModule {
  private static OdinExtendedModule instance;
  
  private Map<String, ExtendedData> extended;
  
  public OdinExtendedModule(String modId) {
    super(modId, "extended", "1.0.0");
    instance = this;
    this.extended = new HashMap<>();
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    addPacket(SCSyncExtendedEntityProperties.Handler.class, SCSyncExtendedEntityProperties.class, Side.CLIENT);
    addListener(OdinExtendedListener.class);
  }
  
  public void onInit(FMLInitializationEvent event) {}
  
  public void onPostInit(FMLPostInitializationEvent event) {}
  
  public void onServerStarting(FMLServerStartingEvent event) {}
  
  public void onServerStarted(FMLServerStartedEvent event) {}
  
  public void registerExtended(Class<? extends Entity> entity, Class<? extends ExtendedEntityProperties> extended, String propName, ExtendedProperty... properties) {
    ExtendedData data = new ExtendedData(propName, entity, properties, extended);
    this.extended.put(propName, data);
  }
  
  public static OdinExtendedModule getInstance() {
    return instance;
  }
  
  public Map<String, ExtendedData> getExtended() {
    return this.extended;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\extended\OdinExtendedModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */