package fr.paladium.palamod.modules.end.common.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.end.common.entity.EntityEventEndCrystal;
import fr.paladium.palamod.modules.end.common.entity.EntityEventEndDragon;
import fr.paladium.palamod.modules.end.common.event.EventEndWorldGuardListener;
import fr.paladium.palamod.modules.end.common.event.ExtendedEventEndDataHandler;
import fr.paladium.palamod.modules.end.common.network.SCSyncExtendedEventEndEntityData;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
  private SimpleNetworkWrapper network;
  
  public void onPreInit(FMLPreInitializationEvent event) {
    this.network = NetworkRegistry.INSTANCE.newSimpleChannel("palamod-end");
    this.network.registerMessage(SCSyncExtendedEventEndEntityData.Handler.class, SCSyncExtendedEventEndEntityData.class, 0, Side.CLIENT);
    EntityRegistry.registerGlobalEntityID(EntityEventEndCrystal.class, "event_end_crystal", EntityRegistry.findGlobalUniqueEntityId(), 65280, 16711935);
    EntityRegistry.registerGlobalEntityID(EntityEventEndDragon.class, "event_end_dragon", EntityRegistry.findGlobalUniqueEntityId(), 65280, 16711935);
    ExtendedEventEndDataHandler extendedEventEndDataHandler = new ExtendedEventEndDataHandler();
    FMLCommonHandler.instance().bus().register(extendedEventEndDataHandler);
    MinecraftForge.EVENT_BUS.register(extendedEventEndDataHandler);
    EventEndWorldGuardListener eventEndWorldGuardListener = new EventEndWorldGuardListener();
    FMLCommonHandler.instance().bus().register(eventEndWorldGuardListener);
    MinecraftForge.EVENT_BUS.register(eventEndWorldGuardListener);
  }
  
  public void onInit(FMLInitializationEvent event) {}
  
  public void onPostInit(FMLPostInitializationEvent event) {}
  
  public void onServerStarted(FMLServerStartedEvent event) {}
  
  public SimpleNetworkWrapper getNetwork() {
    return this.network;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\common\proxy\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */