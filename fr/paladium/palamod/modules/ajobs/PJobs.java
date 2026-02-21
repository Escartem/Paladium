package fr.paladium.palamod.modules.ajobs;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.ajobs.common.JCommonProxy;
import fr.paladium.palamod.modules.ajobs.common.eep.PalaEnchantEEP;
import fr.paladium.palamod.modules.ajobs.common.entities.EntitySittableBlock;
import fr.paladium.palamod.modules.ajobs.common.manager.JobsManager;
import fr.paladium.palamod.modules.ajobs.common.registerer.BlocksRegistry;
import fr.paladium.palamod.modules.ajobs.common.registerer.CraftRegistry;
import fr.paladium.palamod.modules.ajobs.common.registerer.ItemsRegistry;
import fr.paladium.palamod.modules.ajobs.common.tiles.TileEntityAlchemistThrone;
import fr.paladium.palamod.modules.ajobs.common.tiles.TileEntityFarmerThrone;
import fr.paladium.palamod.modules.ajobs.common.tiles.TileEntityHunterThrone;
import fr.paladium.palamod.modules.ajobs.common.tiles.TileEntityMinerThrone;
import fr.paladium.palamod.modules.ajobs.common.tiles.TileEntityRoot;
import fr.paladium.palamod.modules.ajobs.server.AJobsEventHandler;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

@ObjectHolder("palamod")
@Pulse(id = "palamod-ajobs", description = "Paladium Jobs registerer.", forced = true)
@DoNotRename
public class PJobs {
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.ajobs.client.JClientProxy", serverSide = "fr.paladium.palamod.modules.ajobs.server.JServerProxy")
  public static JCommonProxy proxy;
  
  public static SimpleNetworkWrapper networkWrapper;
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    FMLCommonHandler.instance().bus().register(new AJobsEventHandler());
    MinecraftForge.EVENT_BUS.register(new AJobsEventHandler());
    GameRegistry.registerTileEntity(TileEntityRoot.class, "tileEntityRoot");
    GameRegistry.registerTileEntity(TileEntityAlchemistThrone.class, "tileEntityAlchThrone");
    GameRegistry.registerTileEntity(TileEntityHunterThrone.class, "tileEntityHunterThrone");
    GameRegistry.registerTileEntity(TileEntityMinerThrone.class, "tileEntityMinerThrone");
    GameRegistry.registerTileEntity(TileEntityFarmerThrone.class, "tileEntityFarmerThrone");
    BlocksRegistry.registerBlocks();
    ItemsRegistry.registerItems();
    CraftRegistry.getInstance().register();
    ExtendedUtils.registerExtended(EntityPlayer.class, PalaEnchantEEP.class, "enchant_eep", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT, ExtendedProperty.SYNCHRONIZED, ExtendedProperty.SYNCHRONIZED_TRACKER });
    EntityRegistry.registerModEntity(EntitySittableBlock.class, "MountableBlock", 500, PalaMod.instance, 80, 1, false);
    proxy.preInit(event);
  }
  
  @Handler
  public void init(FMLInitializationEvent event) {
    proxy.init(event);
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent event) {
    try {
      JobsManager.getInstance().register();
      if (event.getSide() == Side.CLIENT)
        (PalaJobs.getClient()).moneyIcon = new ItemStack(ItemsRegister.money); 
      proxy.postInit(event);
    } catch (Throwable $ex) {
      throw $ex;
    } 
  }
  
  @Handler
  public void serverStarting(FMLServerStartingEvent event) {
    proxy.serverStarting(event);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\PJobs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */