package fr.paladium.palamod.modules.pvp;

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
import fr.paladium.palamod.modules.pvp.eep.PvpEEP;
import fr.paladium.palamod.modules.pvp.events.EventsManager;
import fr.paladium.palamod.modules.pvp.profile.ModulePvp;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import fr.paladium.profile.common.module.ProfileModules;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

@ObjectHolder("palamod")
@Pulse(id = "palamod-pvp", description = "PVP", forced = true)
public class PPvp {
  @Instance("palamod-pvp")
  public static PPvp instance;
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    System.out.println("[PVP] PreInit");
    FMLCommonHandler.instance().bus().register(new EventsManager());
    MinecraftForge.EVENT_BUS.register(new EventsManager());
    if (event.getSide() == Side.SERVER) {
      preInitServer();
    } else {
      ContainerEnchantment.startChecks();
    } 
    ExtendedUtils.registerExtended(EntityPlayer.class, PvpEEP.class, "pvp_eep", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT });
    ProfileModules.getInstance().registerModule(ModulePvp.class);
  }
  
  @SideOnly(Side.SERVER)
  private void preInitServer() {
    System.out.println("[PVP][Server] PreInit");
    if (ForgeEnv.isDev()) {
      System.err.println("[PPvp] Disabled in devmode.");
      return;
    } 
  }
  
  @Handler
  public void serverStarting(FMLServerStartingEvent event) {
    Constants.logger.info("[PVP] Registering commands.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pvp\PPvp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */