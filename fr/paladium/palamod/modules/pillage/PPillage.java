package fr.paladium.palamod.modules.pillage;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.alchimiste.PAlchimiste;
import fr.paladium.palamod.modules.alchimiste.common.enchant.EnchantmentBase;
import fr.paladium.palamod.modules.homefinder.common.entities.EntityFakePlayer;
import fr.paladium.palamod.modules.pillage.common.CommonProxy;
import fr.paladium.palamod.modules.pillage.common.blocks.tileentities.TECoordinateJammer;
import fr.paladium.palamod.modules.pillage.common.effects.EntityTNTEffectPrimed;
import fr.paladium.palamod.modules.pillage.common.entities.EntitySpongeSheep;
import fr.paladium.palamod.modules.pillage.network.MessageHandler;
import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import net.minecraft.command.ICommand;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

@ObjectHolder("palamod")
@Pulse(id = "palamod-pillage", description = "The canon is the most powerful weapon", forced = true)
@DoNotRename
public class PPillage {
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.pillage.client.ClientProxy", serverSide = "fr.paladium.palamod.modules.pillage.common.CommonProxy")
  public static CommonProxy proxy;
  
  @Instance("palamod-pillage")
  public static PPillage instance;
  
  public static boolean debugMode = false;
  
  public static EnchantmentBase frost_walker;
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    PPRegisterer.PPBlocks.init();
    PPRegisterer.PPItems.init();
    PAlchimiste.cauldronController.addRecipe(new ItemStack(PPRegisterer.PPBlocks.WATER_SAPLING, 1), new ItemStack[] { new ItemStack(Blocks.field_150345_g, 1, 2), new ItemStack(Blocks.field_150435_aG, 1) });
    setupTabs();
    proxy.preInit();
    MessageHandler.init();
    if (event.getSide().isServer())
      ConfigManager.init(); 
  }
  
  @Handler
  public void init(FMLInitializationEvent event) {
    proxy.init();
    proxy.registerRenders();
    GameRegistry.registerTileEntity(TECoordinateJammer.class, "palamod-pillage:tecoordinatejammer");
    EntityRegistry.registerModEntity(EntitySpongeSheep.class, "SpongeSheep", 101, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityTNTEffectPrimed.class, "EffectTnt", 102, PalaMod.instance, 200, 40, false);
    EntityRegistry.registerModEntity(EntityFakePlayer.class, "FakePlayer", 103, PalaMod.instance, 40, 1, true);
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent event) {
    PPRegisterer.PPItems.registerRecipes();
    PPRegisterer.PPBlocks.registerRecipes();
  }
  
  @Handler
  public void serverStarting(FMLServerStartingEvent event) {
    event.registerServerCommand((ICommand)new SetRespawnHomeCommand());
    event.registerServerCommand((ICommand)new ObsidianDebugCommand());
  }
  
  public void setupTabs() {
    Registry.PILLAGE_TAB.init(new ItemStack(BlocksRegister.OBSI_SLIME, 1, 0));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\PPillage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */