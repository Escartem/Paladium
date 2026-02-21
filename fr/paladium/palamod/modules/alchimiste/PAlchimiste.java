package fr.paladium.palamod.modules.alchimiste;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.client.manager.CraftManager;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.alchimiste.client.betternei.handler.CauldronCraftHandler;
import fr.paladium.palamod.modules.alchimiste.common.events.EventHandlerAlchemist;
import fr.paladium.palamod.modules.alchimiste.common.generator.WorldPlantGenerator;
import fr.paladium.palamod.modules.alchimiste.common.init.BlockMod;
import fr.paladium.palamod.modules.alchimiste.common.init.CauldronController;
import fr.paladium.palamod.modules.alchimiste.common.init.CraftMod;
import fr.paladium.palamod.modules.alchimiste.common.init.EnchantMod;
import fr.paladium.palamod.modules.alchimiste.common.init.EntityInit;
import fr.paladium.palamod.modules.alchimiste.common.init.ItemMod;
import fr.paladium.palamod.modules.alchimiste.common.init.NetworkMod;
import fr.paladium.palamod.modules.alchimiste.common.init.PortalController;
import fr.paladium.palamod.modules.alchimiste.common.init.PotionMod;
import fr.paladium.palamod.modules.alchimiste.common.potions.PotionLevitation;
import fr.paladium.palamod.modules.alchimiste.common.utils.EnumPortalType;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.PortalRecipe;
import fr.paladium.palamod.modules.alchimiste.proxies.CommonProxy;
import fr.paladium.palamod.modules.world.PWorld;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

@ObjectHolder("palamod")
@Pulse(id = "palamod-alchimiste", description = "Alchemist job reworked", forced = true)
@DoNotRename
public class PAlchimiste {
  @Instance("palamod-alchimiste")
  public static PAlchimiste instance;
  
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.alchimiste.proxies.ClientProxy", serverSide = "fr.paladium.palamod.modules.alchimiste.proxies.CommonProxy")
  public static CommonProxy proxy;
  
  public static SimpleNetworkWrapper network;
  
  public static CauldronController cauldronController;
  
  public static PortalController portalController;
  
  public static CreativeTabs TAB = new CreativeTabs("palamod-alchimiste") {
      public Item func_78016_d() {
        return (BlocksRegister.EXTRACTOR == null) ? Item.func_150898_a(Blocks.field_150348_b) : Item.func_150898_a((Block)BlocksRegister.EXTRACTOR);
      }
    };
  
  @Handler
  public void preInit(FMLPreInitializationEvent e) {
    PotionLevitation.levitationId = 30;
    network = NetworkRegistry.INSTANCE.newSimpleChannel("palamod-alchinet");
    BlockMod.register();
    ItemMod.register();
    NetworkMod.register();
    EnchantMod.register();
    cauldronController = new CauldronController();
    portalController = new PortalController();
    cauldronController.addRecipe(new ItemStack((Block)BlocksRegister.SHINY_ERABLE_WOOD, 2), new ItemStack[] { new ItemStack((Item)ItemsRegister.LIGHTNING_POTION), new ItemStack(PWorld.LOG_ERABLE) });
    cauldronController.addRecipe(new ItemStack((Block)BlocksRegister.SHINY_JACARANDA_WOOD, 2), new ItemStack[] { new ItemStack((Item)ItemsRegister.LIGHTNING_POTION), new ItemStack(PWorld.LOG_JACARANDA) });
    cauldronController.addRecipe(new ItemStack((Block)BlocksRegister.SHINY_OSTRYA_WOOD, 2), new ItemStack[] { new ItemStack((Item)ItemsRegister.LIGHTNING_POTION), new ItemStack(PWorld.LOG_OSTRYA) });
    cauldronController.addRecipe(new ItemStack((Block)BlocksRegister.SHINY_JUDEECERCIS_WOOD, 2), new ItemStack[] { new ItemStack((Item)ItemsRegister.LIGHTNING_POTION), new ItemStack(PWorld.LOG_JUDEECERCIS) });
    cauldronController.addRecipe(new ItemStack(Items.field_151074_bl, 3), new ItemStack[] { new ItemStack(Items.field_151114_aO) });
    cauldronController.addGlueballRecipe("treeseve-Ostrya", "", new ItemStack((Item)ItemsRegister.GREEN_GLUEBALL));
    cauldronController.addGlueballRecipe("treeseve-Jacaranda", "", new ItemStack((Item)ItemsRegister.BLUE_GLUEBALL));
    cauldronController.addGlueballRecipe("treeseve-Judeecercis", "", new ItemStack((Item)ItemsRegister.YELLOW_GLUEBALL));
    cauldronController.addGlueballRecipe("treeseve-Erable", "", new ItemStack((Item)ItemsRegister.RED_GLUEBALL));
    cauldronController.addGlueballRecipe("treeseve-Erable", "treeseve-Judeecercis", new ItemStack((Item)ItemsRegister.ORANGE_GLUEBALL));
    cauldronController.addGlueballRecipe("treeseve-Erable", "treeseve-Ostrya", new ItemStack((Item)ItemsRegister.GRAY_GLUEBALL));
    cauldronController.addGlueballRecipe("treeseve-Erable", "treeseve-Jacaranda", new ItemStack((Item)ItemsRegister.PURPLE_GLUEBALL));
    cauldronController.addGlueballRecipe("treeseve-Judeecercis", "treeseve-Ostrya", new ItemStack((Item)ItemsRegister.GREEN_FLASH_GLUEBALL));
    cauldronController.addGlueballRecipe("treeseve-Judeecercis", "treeseve-Jacaranda", new ItemStack((Item)ItemsRegister.GREEN_DARK_GLUEBALL));
    cauldronController.addGlueballRecipe("treeseve-Ostrya", "treeseve-Jacaranda", new ItemStack((Item)ItemsRegister.CYAN_GLUEBALL));
    portalController.addRecipe(EnumPortalType.AMETHYSTE, new PortalRecipe("Jacaranda", new ItemStack(ItemsRegister.AMETHYST_INGOT, 1), 1));
    portalController.addRecipe(EnumPortalType.TITANE, new PortalRecipe("Judeecercis", new ItemStack(ItemsRegister.TITANE_INGOT, 1), 2));
    portalController.addRecipe(EnumPortalType.PALADIUM, new PortalRecipe("Erable", new ItemStack(ItemsRegister.PALADIUM_INGOT, 1), 4));
    portalController.addRecipe(EnumPortalType.ENDIUM, new PortalRecipe("Ostrya", new ItemStack(ItemsRegister.ENDIUM_NUGGET), 3448));
    EntityInit.init();
    FMLCommonHandler.instance().bus().register(new EventHandlerAlchemist());
    MinecraftForge.EVENT_BUS.register(new EventHandlerAlchemist());
  }
  
  @Handler
  public void init(FMLInitializationEvent e) {
    PotionMod.register();
    proxy.regRenders();
    GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldPlantGenerator(), 1);
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent e) {
    CraftMod.init();
    if (e.getSide() == Side.CLIENT)
      try {
        CraftManager.getInstance().registerHandler((ACraftHandler)new CauldronCraftHandler());
      } catch (Exception e1) {
        e1.printStackTrace();
      }  
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\PAlchimiste.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */