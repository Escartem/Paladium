package fr.paladium.palamod.modules.miner;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.helios.module.coordinates.ModuleCoordinates;
import fr.paladium.helios.module.time.ModuleTime;
import fr.paladium.helios.module.utils.abstracts.AModule;
import fr.paladium.helios.module.utils.interfaces.ModuleCondition;
import fr.paladium.helios.module.weather.ModuleWeather;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.alchimiste.common.enchant.EnchantmentBase;
import fr.paladium.palamod.modules.alchimiste.common.init.EnchantMod;
import fr.paladium.palamod.modules.luckyblock.potions.CustomPotion;
import fr.paladium.palamod.modules.miner.command.CommandGodPickaxe;
import fr.paladium.palamod.modules.miner.command.CommandNether;
import fr.paladium.palamod.modules.miner.dimminer.client.listener.ClientDimMinerEventHandler;
import fr.paladium.palamod.modules.miner.dimminer.common.data.DimMinerPlayer;
import fr.paladium.palamod.modules.miner.dimminer.common.entity.EntityMinerBoss;
import fr.paladium.palamod.modules.miner.dimminer.common.network.CSPacketDimMinerJoin;
import fr.paladium.palamod.modules.miner.dimminer.common.network.SCPacketDimMinerOpen;
import fr.paladium.palamod.modules.miner.dimminer.common.network.SCPacketDimMinerOverlay;
import fr.paladium.palamod.modules.miner.dimminer.common.network.SCPacketDimMinerStats;
import fr.paladium.palamod.modules.miner.dimminer.server.listener.ServerDimMinerEventHandler;
import fr.paladium.palamod.modules.miner.entity.EntityGodVillager;
import fr.paladium.palamod.modules.miner.event.EventsManager;
import fr.paladium.palamod.modules.miner.init.Blocks;
import fr.paladium.palamod.modules.miner.init.Crafts;
import fr.paladium.palamod.modules.miner.init.Items;
import fr.paladium.palamod.modules.miner.networks.CSDrawBridgeSetDirection;
import fr.paladium.palamod.modules.miner.networks.CSSetAutoCrafter;
import fr.paladium.palamod.modules.miner.networks.SCPacketSetInMinage;
import fr.paladium.palamod.modules.miner.proxy.CommonProxy;
import fr.paladium.palamod.modules.miner.utils.AutoCrafterManager;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import java.awt.Color;
import java.lang.reflect.Method;
import net.minecraft.command.ICommand;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import org.spigotmc.SpigotWorldConfig;

@ObjectHolder("palamod")
@Pulse(id = "palamod-miner", description = "Paladium Miner", forced = true)
@DoNotRename
public class PMiner {
  public static int HOPPER_RATE = 8;
  
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.miner.proxy.ClientProxy", serverSide = "fr.paladium.palamod.modules.miner.proxy.CommonProxy")
  public static CommonProxy proxy;
  
  @Instance("palamod-miner")
  public static PMiner instance;
  
  public static EnchantmentBase xp_bottler;
  
  public static CustomPotion[] potions;
  
  public static CustomPotion HARDNESS;
  
  public static SimpleNetworkWrapper network;
  
  @Handler
  public void preInit(FMLPreInitializationEvent e) {
    HARDNESS = new CustomPotion(41, true, Color.CYAN.getRGB(), "hardness");
    potions = new CustomPotion[] { HARDNESS };
    for (CustomPotion potion : potions)
      potion.setIconIndex(0, 0); 
    for (CustomPotion potion : potions) {
      potion.loadEffects();
      potion.register();
    } 
    Blocks.register();
    Items.register();
    EntityRegistry.registerGlobalEntityID(EntityGodVillager.class, "godVillager", EntityRegistry.findGlobalUniqueEntityId(), Color.ORANGE.getRGB(), Color.YELLOW.getRGB());
    EntityRegistry.registerModEntity(EntityGodVillager.class, "godVillager", 700, PalaMod.instance, 200, 40, false);
    EntityRegistry.registerGlobalEntityID(EntityMinerBoss.class, "minerBoss", EntityRegistry.findGlobalUniqueEntityId(), Color.ORANGE.getRGB(), Color.PINK.getRGB());
    EntityRegistry.registerModEntity(EntityMinerBoss.class, "minerBoss", 701, PalaMod.instance, 200, 40, false);
    Object eventsManager = new EventsManager();
    FMLCommonHandler.instance().bus().register(eventsManager);
    MinecraftForge.EVENT_BUS.register(eventsManager);
    ExtendedUtils.registerExtended(EntityPlayer.class, DimMinerPlayer.class, "MinerDimensionPlayerData", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT, ExtendedProperty.SYNCHRONIZED });
    network = NetworkRegistry.INSTANCE.newSimpleChannel("pminer");
    network.registerMessage(CSSetAutoCrafter.Handler.class, CSSetAutoCrafter.class, 0, Side.SERVER);
    network.registerMessage(CSDrawBridgeSetDirection.Handler.class, CSDrawBridgeSetDirection.class, 1, Side.SERVER);
    network.registerMessage(SCPacketSetInMinage.Handler.class, SCPacketSetInMinage.class, 2, Side.CLIENT);
    network.registerMessage(CSPacketDimMinerJoin.Handler.class, CSPacketDimMinerJoin.class, 3, Side.SERVER);
    network.registerMessage(SCPacketDimMinerOpen.Handler.class, SCPacketDimMinerOpen.class, 4, Side.CLIENT);
    network.registerMessage(SCPacketDimMinerOverlay.Handler.class, SCPacketDimMinerOverlay.class, 5, Side.CLIENT);
    network.registerMessage(SCPacketDimMinerStats.Handler.class, SCPacketDimMinerStats.class, 6, Side.CLIENT);
    xp_bottler = (new EnchantmentBase(161, 0, EnumEnchantmentType.all, "xp_bottler", 0, 0, 0, 12)).setMaxLevel(1);
    EnchantMod.enchants.put(xp_bottler, "Cet enchantement permet de mettre<br>l'XP en bouteille");
    if (e.getSide() == Side.CLIENT) {
      Object eventHandler = new ClientDimMinerEventHandler();
      FMLCommonHandler.instance().bus().register(eventHandler);
      MinecraftForge.EVENT_BUS.register(eventHandler);
    } else if (e.getSide() == Side.SERVER) {
      Object eventHandler = new ServerDimMinerEventHandler();
      FMLCommonHandler.instance().bus().register(eventHandler);
      MinecraftForge.EVENT_BUS.register(eventHandler);
    } 
  }
  
  @Handler
  public void init(FMLInitializationEvent e) {
    Registry.MINER_TAB.init(new ItemStack(ItemsRegister.SPAWNER_FINDER));
    proxy.registerRenders();
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent e) {
    AutoCrafterManager.init();
    Crafts.register();
    if (e.getSide() == Side.CLIENT) {
      ModuleCoordinates.getInstance().addCondition(new ModuleCondition(mod -> !proxy.isMinerDimension(), "Ce module n'est pas disponible dans la dimension de mineur"));
      ModuleTime.getInstance().addCondition(new ModuleCondition(mod -> !proxy.isMinerDimension(), "Ce module n'est pas disponible dans la dimension de mineur"));
      ModuleWeather.getInstance().addCondition(new ModuleCondition(mod -> !proxy.isMinerDimension(), "Ce module n'est pas disponible dans la dimension de mineur"));
    } 
  }
  
  @Handler
  public void serverStarting(FMLServerStartingEvent e) {
    e.registerServerCommand((ICommand)new CommandNether());
    e.registerServerCommand((ICommand)new CommandGodPickaxe());
    World world = e.getServer().func_130014_f_();
    try {
      Method getSpigotConfig = world.getClass().getMethod("getSpigotConfig", new Class[0]);
      SpigotWorldConfig spigotWorldConfig = (SpigotWorldConfig)getSpigotConfig.invoke(world, new Object[0]);
      HOPPER_RATE = spigotWorldConfig.hopperTransfer;
    } catch (Exception|NoSuchMethodError err) {
      System.out.println("[PMiner] Attempt to get the hopper transfer rate failed, default value set to " + HOPPER_RATE + " ticks");
      err.printStackTrace();
    } 
  }
  
  @Handler
  public void serverStarted(FMLServerStartedEvent e) {
    if (!proxy.isMinerDimension())
      return; 
    for (WorldServer worldServer : (MinecraftServer.func_71276_C()).field_71305_c) {
      worldServer.func_82736_K().func_82764_b("keepInventory", "true");
      worldServer.func_82736_K().func_82764_b("doMobSpawning", "false");
      worldServer.func_82736_K().func_82764_b("mobGriefing", "false");
      worldServer.func_82736_K().func_82764_b("doFireTick", "false");
      worldServer.func_82736_K().func_82764_b("naturalRegeneration", "false");
      worldServer.func_82736_K().func_82764_b("doDaylightCycle", "false");
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\PMiner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */