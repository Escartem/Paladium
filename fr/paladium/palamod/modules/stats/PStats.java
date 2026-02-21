package fr.paladium.palamod.modules.stats;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.modules.back2future.inventory.ContainerEnchantment;
import fr.paladium.palamod.modules.stats.eep.StatsEep;
import fr.paladium.palamod.modules.stats.events.EventsManager;
import fr.paladium.palamod.modules.stats.profile.ModuleStats;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import fr.paladium.profile.common.module.ProfileModules;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

@ObjectHolder("palamod")
@Pulse(id = "palamod-stats", description = "Stats", forced = true)
public class PStats {
  @Instance("palamod-stats")
  public static PStats instance;
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    System.out.println("[Stats] PreInit");
    FMLCommonHandler.instance().bus().register(new EventsManager());
    MinecraftForge.EVENT_BUS.register(new EventsManager());
    if (event.getSide() == Side.SERVER) {
      preInitServer();
    } else {
      ContainerEnchantment.startChecks();
    } 
    ExtendedUtils.registerExtended(EntityPlayer.class, StatsEep.class, "stats_eep", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT });
    ProfileModules.getInstance().registerModule(ModuleStats.class);
  }
  
  @SideOnly(Side.SERVER)
  private void preInitServer() {
    System.out.println("[Stats][Server] PreInit");
    if (ForgeEnv.isDev()) {
      System.err.println("[Stats] Disabled in devmode.");
      return;
    } 
  }
  
  @Handler
  public void serverStarting(FMLServerStartingEvent event) {
    Constants.logger.info("[Stats] Registering commands.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\stats\PStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */