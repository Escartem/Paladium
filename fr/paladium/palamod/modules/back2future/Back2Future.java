package fr.paladium.palamod.modules.back2future;

import com.allatori.annotations.DoNotRename;
import com.google.common.collect.UnmodifiableIterator;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.back2future.core.proxy.CommonProxy;
import fr.paladium.palamod.modules.back2future.entities.ModEntityList;
import fr.paladium.palamod.modules.back2future.items.ItemEntityEgg;
import fr.paladium.palamod.modules.back2future.network.ArmourStandInteractHandler;
import fr.paladium.palamod.modules.back2future.network.ArmourStandInteractMessage;
import fr.paladium.palamod.modules.back2future.network.BlackHeartParticlesHandler;
import fr.paladium.palamod.modules.back2future.network.BlackHeartParticlesMessage;
import fr.paladium.palamod.modules.back2future.recipes.BrewingFuelRegistry;
import fr.paladium.palamod.modules.back2future.recipes.ModRecipes;
import fr.paladium.palamod.modules.back2future.world.B2FWorldGenerator;
import fr.paladium.palamod.modules.back2future.world.OceanMonument;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import java.lang.reflect.Field;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

@ObjectHolder("palamod")
@Pulse(id = "palamod-back2future", description = "All the 1.8-1.12 features", forced = true)
@DoNotRename
public class Back2Future {
  @Instance("palamod-back2future")
  public static Back2Future instance;
  
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.back2future.core.proxy.ClientProxy", serverSide = "fr.paladium.palamod.modules.back2future.core.proxy.CommonProxy")
  public static CommonProxy proxy;
  
  public static SimpleNetworkWrapper networkWrapper;
  
  public static CreativeTabs creativeTab = new CreativeTabs("palamode_backtofuture") {
      public Item func_78016_d() {
        return Back2Future.enablePrismarine ? ModItems.prismarine_shard : Items.field_151144_bL;
      }
    };
  
  public static boolean enableStones = false;
  
  public static boolean enableIronTrapdoor = true;
  
  public static boolean enableMutton = true;
  
  public static boolean enableSponge = true;
  
  public static boolean enablePrismarine = true;
  
  public static boolean enableDoors = true;
  
  public static boolean enableInvertedDaylightSensor = true;
  
  public static boolean enableCoarseDirt = true;
  
  public static boolean enableRedSandstone = true;
  
  public static boolean enableEnchants = false;
  
  public static boolean enableAnvil = false;
  
  public static boolean enableFences = true;
  
  public static boolean enableSilkTouchingMushrooms = true;
  
  public static boolean enableBanners = true;
  
  public static boolean enableSlimeBlock = true;
  
  public static boolean enableArmourStand = true;
  
  public static boolean enableRabbit = true;
  
  public static boolean enableOldGravel = true;
  
  public static boolean enableRecipeForPrismarine = true;
  
  public static boolean enableEndermite = true;
  
  public static boolean enableBeetroot = true;
  
  public static boolean enableChorusFruit = true;
  
  public static boolean enableGrassPath = true;
  
  public static boolean enableSticksFromDeadBushes = true;
  
  public static boolean enableBowRendering = true;
  
  public static boolean enableTippedArrows = true;
  
  public static boolean enableLingeringPotions = true;
  
  public static boolean enableBurnableBlocks = true;
  
  public static boolean enableFancySkulls = true;
  
  public static boolean enableSkullDrop = true;
  
  public static boolean enableDmgIndicator = true;
  
  public static boolean enableTransparentAmour = true;
  
  public static boolean enableCryingObsidian = true;
  
  public static boolean enableUpdatedFoodValues = true;
  
  public static boolean enableUpdatedHarvestLevels = true;
  
  public static boolean enableVillagerZombies = true;
  
  public static boolean enableStoneBrickRecipes = true;
  
  public static boolean enableBabyGrowthBoost = true;
  
  public static boolean enableVillagerTurnsIntoWitch = true;
  
  public static boolean enableElytra = true;
  
  public static boolean enableFrostWalker = true;
  
  public static boolean enableMending = true;
  
  public static boolean enableBrewingStands = false;
  
  public static boolean enableDragonRespawn = true;
  
  public static boolean enableRoses = true;
  
  public static boolean enableColourfulBeacons = true;
  
  public static boolean enablePlayerSkinOverlay = true;
  
  public static boolean enableShearableGolems = true;
  
  public static boolean enableShearableCobwebs = true;
  
  public static int maxStonesPerCluster = 33;
  
  public static boolean isTinkersConstructLoaded = false;
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    GameRegistry.registerWorldGenerator((IWorldGenerator)new B2FWorldGenerator(), 0);
    ModBlocks.init();
    ModItems.init();
    ModEnchantments.init();
    OceanMonument.makeMap();
    networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("palamod-back2future");
    networkWrapper.registerMessage(ArmourStandInteractHandler.class, ArmourStandInteractMessage.class, 0, Side.SERVER);
    networkWrapper.registerMessage(BlackHeartParticlesHandler.class, BlackHeartParticlesMessage.class, 1, Side.CLIENT);
  }
  
  @Handler
  public void init(FMLInitializationEvent event) {
    ModRecipes.init();
    proxy.registerEvents();
    proxy.registerEntities();
    proxy.registerRenderers();
    if (ModEntityList.hasEntitiesWithEggs()) {
      ModEntityList.entity_egg = (Item)new ItemEntityEgg();
      GameRegistry.registerItem(ModEntityList.entity_egg, "entity_egg");
      OreDictionary.registerOre("mobEgg", ModEntityList.entity_egg);
    } 
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent event) {
    Items.field_151072_bj.func_77664_n();
    Blocks.field_150447_bR.func_149647_a(CreativeTabs.field_78028_d);
    if (enableUpdatedFoodValues) {
      setFinalField(ItemFood.class, Items.field_151172_bF, Integer.valueOf(3), new String[] { "healAmount", "field_77853_b" });
      setFinalField(ItemFood.class, Items.field_151168_bH, Integer.valueOf(5), new String[] { "healAmount", "field_77853_b" });
    } 
    if (enableUpdatedHarvestLevels) {
      Blocks.field_150403_cj.setHarvestLevel("pickaxe", 0);
      Blocks.field_150468_ap.setHarvestLevel("axe", 0);
      Blocks.field_150440_ba.setHarvestLevel("axe", 0);
    } 
  }
  
  @Handler
  public void processIMCRequests(FMLInterModComms.IMCEvent event) {
    for (UnmodifiableIterator<FMLInterModComms.IMCMessage> unmodifiableIterator = event.getMessages().iterator(); unmodifiableIterator.hasNext(); ) {
      FMLInterModComms.IMCMessage message = unmodifiableIterator.next();
      if (message.key.equals("register-brewing-fuel")) {
        NBTTagCompound nbt = message.getNBTValue();
        ItemStack stack = ItemStack.func_77949_a(nbt.func_74775_l("Fuel"));
        int brews = nbt.func_74762_e("Brews");
        BrewingFuelRegistry.registerFuel(stack, brews);
      } 
    } 
  }
  
  private void setFinalField(Class<?> cls, Object obj, Object newValue, String... fieldNames) {
    try {
      Field field = ReflectionHelper.findField(cls, fieldNames);
      field.setAccessible(true);
      Field modifiersField = Field.class.getDeclaredField("modifiers");
      modifiersField.setAccessible(true);
      modifiersField.setInt(field, field.getModifiers() & 0xFFFFFFEF);
      field.set(obj, newValue);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\Back2Future.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */