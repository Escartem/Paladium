package fr.paladium.palamod.modules.addons;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.addons.network.networkSniffer;
import fr.paladium.palamod.modules.paladium.network.ClearChatSniffer;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import net.minecraftforge.common.MinecraftForge;

@ObjectHolder("palamod")
@Pulse(id = "palamod-addons", description = "Addons for Paladium", forced = true)
public class PAddons {
  @Instance("palamod-addons")
  public static PAddons instance;
  
  public static SimpleNetworkWrapper network;
  
  @Handler
  public void init(FMLInitializationEvent event) {
    if (event.getSide() == Side.CLIENT) {
      FMLCommonHandler.instance().bus().register(this);
      MinecraftForge.EVENT_BUS.register(this);
      FMLEventChannel event1 = NetworkRegistry.INSTANCE.newEventDrivenChannel("paladium_addon");
      event1.register(new networkSniffer());
      FMLEventChannel event4 = NetworkRegistry.INSTANCE.newEventDrivenChannel("clearchat");
      event4.register(new ClearChatSniffer());
    } 
    System.out.println("##PAddons::init");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\addons\PAddons.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */