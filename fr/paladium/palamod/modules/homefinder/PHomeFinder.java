package fr.paladium.palamod.modules.homefinder;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import fr.paladium.palamod.modules.homefinder.common.HCommonProxy;
import fr.paladium.palamod.modules.homefinder.common.events.DataEvent;
import fr.paladium.palamod.modules.homefinder.common.init.HBlocksInit;
import fr.paladium.palamod.modules.homefinder.common.init.HCraftInit;
import fr.paladium.palamod.modules.homefinder.common.init.HInit;
import fr.paladium.palamod.modules.homefinder.common.managers.DisconnectionDataManager;
import fr.paladium.palamod.modules.homefinder.common.managers.HomeFinderManager;
import fr.paladium.palamod.modules.homefinder.common.tiles.TileEntityHomeFinder;
import fr.paladium.palamod.modules.homefinder.server.data.HomeFinderWorldData;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraftforge.common.MinecraftForge;

@ObjectHolder("palamod")
@Pulse(id = "palamod-homefinder", description = "HomeFinder module", forced = true)
@DoNotRename
public class PHomeFinder {
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.homefinder.client.HClientProxy", serverSide = "fr.paladium.palamod.modules.homefinder.common.HCommonProxy")
  public static HCommonProxy proxy;
  
  @Instance("palamod-homefinder")
  public static PHomeFinder instance;
  
  private List<HInit> inits;
  
  private HomeFinderManager manager;
  
  private DisconnectionDataManager dataManager;
  
  public List<HInit> getInits() {
    return this.inits;
  }
  
  public HomeFinderManager getManager() {
    return this.manager;
  }
  
  public DisconnectionDataManager getDataManager() {
    return this.dataManager;
  }
  
  public PHomeFinder() {
    instance = this;
    this.inits = new ArrayList<>();
  }
  
  @Handler
  public void init(FMLInitializationEvent event) {
    proxy.init(event);
  }
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    this.inits = Arrays.asList(new HInit[] { (HInit)new HBlocksInit(), (HInit)new HCraftInit() });
    FMLCommonHandler.instance().bus().register(new DataEvent());
    MinecraftForge.EVENT_BUS.register(new DataEvent());
    GameRegistry.registerTileEntity(TileEntityHomeFinder.class, "common-pillage:tehomefinder");
    this.inits.forEach(HInit::init);
    proxy.preInit(event);
  }
  
  @Handler
  public void serverStarting(FMLServerStartingEvent event) {
    HomeFinderWorldData.get(event.getServer().func_130014_f_());
    this.dataManager = new DisconnectionDataManager();
    this.manager = new HomeFinderManager();
    this.manager.load();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\PHomeFinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */