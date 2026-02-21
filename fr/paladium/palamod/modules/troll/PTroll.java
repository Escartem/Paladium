package fr.paladium.palamod.modules.troll;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import fr.paladium.palamod.modules.troll.commands.BaguetteCommand;
import fr.paladium.palamod.modules.troll.commands.TrollCommand;
import fr.paladium.palamod.modules.troll.config.ConfigManager;
import fr.paladium.palamod.modules.troll.events.BaguetteEventHandler;
import fr.paladium.palamod.modules.troll.events.TrollEventHandler;
import fr.paladium.palamod.modules.troll.modules.ATrollModule;
import fr.paladium.palamod.modules.troll.modules.TrollCrashModule;
import fr.paladium.palamod.modules.troll.modules.TrollFreezeModule;
import fr.paladium.palamod.modules.troll.modules.TrollUnFreezeModule;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.ICommand;
import net.minecraftforge.common.MinecraftForge;

@ObjectHolder("palamod")
@Pulse(id = "palamod-troll", description = "Paladium Troll Module", forced = true)
@DoNotRename
public class PTroll {
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.troll.ClientProxy", serverSide = "fr.paladium.palamod.modules.troll.CommonProxy")
  public static CommonProxy proxy;
  
  @Instance("palamod-troll")
  public static PTroll instance;
  
  public static SimpleNetworkWrapper network;
  
  private static List<ATrollModule> modules = new ArrayList<>();
  
  public static List<ATrollModule> getModules() {
    return modules;
  }
  
  @Handler
  public void preInit(FMLPreInitializationEvent e) {
    ConfigManager.init();
    FMLCommonHandler.instance().bus().register(new TrollEventHandler());
    MinecraftForge.EVENT_BUS.register(new TrollEventHandler());
    FMLCommonHandler.instance().bus().register(new BaguetteEventHandler());
    MinecraftForge.EVENT_BUS.register(new BaguetteEventHandler());
    network = NetworkRegistry.INSTANCE.newSimpleChannel("ptroll");
    modules.add(new TrollCrashModule());
    modules.add(new TrollFreezeModule());
    modules.add(new TrollUnFreezeModule());
    System.out.println("##Troll preInit");
  }
  
  @Handler
  public void init(FMLInitializationEvent e) {
    proxy.registerRenders();
  }
  
  @Handler
  public void serverStarting(FMLServerStartingEvent event) {
    event.registerServerCommand((ICommand)new TrollCommand());
    event.registerServerCommand((ICommand)new BaguetteCommand());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\troll\PTroll.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */