package fr.paladium.palamod.modules.enderchest;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaconfiguration.server.manager.ConfigurationManager;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palamod.modules.enderchest.commands.EnderChestCommand;
import fr.paladium.palamod.modules.enderchest.config.EnderchestConfig;
import fr.paladium.palamod.modules.enderchest.event.PaladiumEnderChestListener;
import fr.paladium.palamod.modules.enderchest.init.BlockRegistry;
import fr.paladium.palamod.modules.enderchest.init.CraftRegistry;
import fr.paladium.palamod.modules.enderchest.proxy.CommonProxy;
import fr.paladium.palamod.modules.enderchest.rabbitmq.RabbitCredentials;
import fr.paladium.palamod.modules.enderchest.rabbitmq.RabbitListener;
import fr.paladium.palamod.modules.enderchest.rabbitmq.RabbitService;
import fr.paladium.palamod.modules.enderchest.rabbitmq.listener.SyncRawGetResultListener;
import fr.paladium.palamod.modules.enderchest.serial.nbt.INBTSerializer;
import fr.paladium.palamod.modules.enderchest.serial.nbt.objects.NBTByteArraySerializer;
import fr.paladium.palamod.modules.enderchest.serial.nbt.objects.NBTCompoundSerializer;
import fr.paladium.palamod.modules.enderchest.serial.nbt.objects.NBTIntArraySerializer;
import fr.paladium.palamod.modules.enderchest.serial.nbt.objects.NBTListSerializer;
import fr.paladium.palamod.modules.enderchest.serial.nbt.objects.NBTPrimitiveSerializer;
import fr.paladium.palamod.modules.enderchest.serial.nbt.objects.NBTStringSerializer;
import fr.paladium.palamod.modules.enderchest.serial.nbt.utils.NBTSerializer;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.command.ICommand;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@ObjectHolder("palamod")
@Pulse(id = "palamod-enderchest", description = "Paladium EnderChest Module", forced = true)
@DoNotRename
public class PEnderChest {
  @Instance("palamod-enderchest")
  public static PEnderChest instance;
  
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.enderchest.proxy.ClientProxy", serverSide = "fr.paladium.palamod.modules.enderchest.proxy.CommonProxy")
  public static CommonProxy proxy;
  
  public static SimpleNetworkWrapper network;
  
  private final Map<UUID, UUID> enderChests = new ConcurrentHashMap<>();
  
  public Map<UUID, UUID> getEnderChests() {
    return this.enderChests;
  }
  
  private final Map<UUID, Map.Entry<UUID, List<ItemStack>>> waitingSave = new ConcurrentHashMap<>();
  
  private EnderchestConfig config;
  
  private File syncConfigFile;
  
  private RabbitService rabbit;
  
  public Map<UUID, Map.Entry<UUID, List<ItemStack>>> getWaitingSave() {
    return this.waitingSave;
  }
  
  public EnderchestConfig getConfig() {
    return this.config;
  }
  
  public File getSyncConfigFile() {
    return this.syncConfigFile;
  }
  
  public RabbitService getRabbit() {
    return this.rabbit;
  }
  
  @Handler
  public void preInit(FMLPreInitializationEvent e) {
    instance = this;
    NBTSerializer.registerParser((INBTSerializer)new NBTStringSerializer());
    NBTSerializer.registerParser((INBTSerializer)new NBTPrimitiveSerializer());
    NBTSerializer.registerParser((INBTSerializer)new NBTCompoundSerializer());
    NBTSerializer.registerParser((INBTSerializer)new NBTIntArraySerializer());
    NBTSerializer.registerParser((INBTSerializer)new NBTByteArraySerializer());
    NBTSerializer.registerParser((INBTSerializer)new NBTListSerializer());
    BlockRegistry.register();
    CraftRegistry.register();
    FMLCommonHandler.instance().bus().register(new PaladiumEnderChestListener());
    MinecraftForge.EVENT_BUS.register(new PaladiumEnderChestListener());
    proxy.register();
    this.syncConfigFile = new File(e.getModConfigurationDirectory(), "syncserv.cfg");
  }
  
  @Handler
  @SideOnly(Side.SERVER)
  public void init(FMLInitializationEvent e) {
    if (ForgeEnv.isDev()) {
      System.out.println("[PEnderChest] Sync & RabbitMQ are disabled in dev mode.");
      return;
    } 
    try {
      Configuration config = new Configuration(this.syncConfigFile);
      config.load();
      RabbitCredentials rabbitCredentials = new RabbitCredentials(config.getString("host", "rabbit", "localhost", "RabbitMQ host"), config.getInt("port", "rabbit", 5672, 0, 65535, "RabbitMQ port"), config.getString("username", "rabbit", "root", "RabbitMQ username"), config.getString("password", "rabbit", "root", "RabbitMQ password"), "/", 16);
      this.rabbit = new RabbitService(rabbitCredentials);
      this.rabbit.registerListener((RabbitListener)new SyncRawGetResultListener(this.rabbit));
    } catch (Exception ex) {
      ex.printStackTrace();
    } 
  }
  
  @Handler
  @SideOnly(Side.SERVER)
  public void postInit(FMLPostInitializationEvent e) {
    ConfigurationManager manager = ConfigurationManager.getInstance();
    this.config = (EnderchestConfig)manager.register(EnderchestConfig.class);
  }
  
  @Handler
  public void serverStarting(FMLServerStartingEvent event) {
    event.registerServerCommand((ICommand)new EnderChestCommand());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\PEnderChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */