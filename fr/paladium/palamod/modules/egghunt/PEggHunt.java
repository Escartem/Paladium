package fr.paladium.palamod.modules.egghunt;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.factions.core.faction.territory.ClaimController;
import fr.paladium.helios.Helios;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.argus.loader.BytecodeListener;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.egghunt.common.CommonProxy;
import fr.paladium.palamod.modules.egghunt.common.config.EggHuntConfig;
import fr.paladium.palamod.modules.egghunt.common.helios.ModuleEggHunt;
import fr.paladium.palamod.modules.egghunt.network.SCPacketArgus;
import fr.paladium.palamod.modules.egghunt.network.SCPacketShowEggPos;
import fr.paladium.palamod.modules.egghunt.server.CommandEgg;
import fr.paladium.palamod.modules.egghunt.server.CommandEggHunt;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntCommonConfig;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntData;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntLocation;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntPlayerEggInput;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntServerUUIDInput;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntStatus;
import fr.paladium.palamod.modules.egghunt.utils.PERegisterer;
import fr.paladium.palamod.modules.end.PEnd;
import fr.paladium.palamod.modules.factions.utils.JsonUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palamod.scheduler.PaladiumTask;
import fr.paladium.palamod.util.BlockLocation;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import net.minecraft.command.ICommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import retrofit2.Response;

@Pulse(id = "palamod-egghunt", description = "Hunt for the dragon egg", forced = true)
@DoNotRename
public class PEggHunt {
  public static final String ID = "palamod-egghunt";
  
  @Instance("palamod-egghunt")
  public static PEggHunt instance;
  
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.egghunt.client.ClientProxy", serverSide = "fr.paladium.palamod.modules.egghunt.server.ServerProxy")
  public static CommonProxy proxy;
  
  public static SimpleNetworkWrapper network;
  
  public static EggHuntCommonConfig commonConfig;
  
  public static EggHuntStatus status;
  
  public static EggHuntData data;
  
  public static File configFile;
  
  public static EggHuntConfig config;
  
  public static EggHuntServerUUIDInput serverInput;
  
  public static long lastReward = -1L;
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    if (event.getSide() == Side.SERVER) {
      try {
        configFile = new File(event.getModConfigurationDirectory(), "egghunt.json");
        config = (EggHuntConfig)JsonUtils.getFileObject(event.getModConfigurationDirectory().getAbsolutePath(), "egghunt.json", EggHuntConfig.class);
      } catch (Exception e1) {
        System.err.println("[EggHunt] Configuration not found: " + event.getModConfigurationDirectory().getAbsolutePath() + "/egghunt.json");
      } 
      Helios.getServer().addModule(ModuleEggHunt.class);
    } else if (event.getSide() == Side.CLIENT) {
      Helios.getClient().addModule(ModuleEggHunt.class);
    } 
    PERegisterer.register();
  }
  
  @Handler
  public void init(FMLInitializationEvent event) {
    proxy.init();
    network = NetworkRegistry.INSTANCE.newSimpleChannel("palamod-egghunt.ch");
    network.registerMessage(SCPacketShowEggPos.Handler.class, SCPacketShowEggPos.class, 2, Side.CLIENT);
    if (event.getSide() == Side.CLIENT) {
      PalaMod.network.registerMessage(SCPacketArgus.Handler.class, SCPacketArgus.class, 398, Side.CLIENT);
      BytecodeListener.rg();
    } 
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent event) {
    if (event.getSide() == Side.CLIENT)
      PaladiumTask.startChecks(); 
  }
  
  @Handler
  public void starting(FMLServerStartingEvent event) {
    event.registerServerCommand((ICommand)new CommandEgg());
    event.registerServerCommand((ICommand)new CommandEggHunt());
  }
  
  @Handler
  public void started(FMLServerStartedEvent event) {
    proxy.serverStarted();
  }
  
  public static void dropEgg(EntityPlayer player, boolean checkCanPlace) {
    World world = player.field_70170_p;
    BlockPos pos = new BlockPos((Entity)player);
    if (isValidLocation(world, pos, player, checkCanPlace)) {
      PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
            try {
              EggHuntPlayerEggInput playerInput = new EggHuntPlayerEggInput(null, null, new EggHuntLocation(pos.getX(), pos.getY(), pos.getZ()));
              setOwner(playerInput, false, ());
            } catch (IOException e1) {
              player.func_145747_a((IChatComponent)new ChatComponentText(getPrefix() + "§cCet oeuf semble venir d'une autre contrée, vous ne pouvez donc rien en faire."));
              e1.printStackTrace();
            } 
          }0L);
    } else {
      PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
            try {
              EggHuntPlayerEggInput playerInput = new EggHuntPlayerEggInput(null, null, data.isEndEvent() ? new EggHuntLocation((PEnd.getServer().getConfig()).egg.get(0)) : config.getEggPosition());
              setOwner(playerInput, false, ());
            } catch (IOException e1) {
              player.func_145747_a((IChatComponent)new ChatComponentText(getPrefix() + "§cCet oeuf semble venir d'une autre contrée, vous ne pouvez donc rien en faire."));
              e1.printStackTrace();
            } 
          }0L);
    } 
  }
  
  public static boolean isValidLocation(World world, BlockPos pos, EntityPlayer player, boolean checkCanPlace) {
    int x = pos.getX();
    int z = pos.getZ();
    int y = world.func_72976_f(x, z);
    if (player.field_70163_u >= 255.0D)
      return false; 
    if (pos.distance(0, 0, 0) > 5000)
      return false; 
    if (checkCanPlace)
      return (ClaimController.getInstance().getClaim(x >> 4, z >> 4).isDefaultClaim() && EventUtils.canInteract(player, x, y, z, PERegisterer.egg, 6)); 
    return ClaimController.getInstance().getClaim(x >> 4, z >> 4).isDefaultClaim();
  }
  
  public static void setOwner(EggHuntPlayerEggInput input, boolean async, Consumer<Response<Void>> callback) throws IOException {
    lastReward = System.currentTimeMillis();
    if (async) {
      PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
            try {
              String oldPlayerName = status.getEggOwner();
              status.setEggOwner(input.getPlayerName());
              status.setCurrentPosition(input.getLocation());
              if (oldPlayerName != null) {
                World world = MinecraftServer.func_71276_C().func_130014_f_();
                EntityPlayer player = world.func_72924_a(oldPlayerName);
                if (player instanceof EntityPlayerMP)
                  ModuleEggHunt.getInstance().sendEggHunt((EntityPlayerMP)player, false, false); 
              } 
              if (input.getPlayerName() != null) {
                World world = MinecraftServer.func_71276_C().func_130014_f_();
                EntityPlayer player = world.func_72924_a(input.getPlayerName());
                if (player instanceof EntityPlayerMP)
                  ModuleEggHunt.getInstance().sendEggHunt((EntityPlayerMP)player, true, false); 
              } 
              callback.accept(ApiServices.Http.getEggHuntService().setOwner(input).execute());
            } catch (IOException e) {
              e.printStackTrace();
            } 
          }0L);
    } else {
      String oldPlayerName = status.getEggOwner();
      status.setEggOwner(input.getPlayerName());
      status.setCurrentPosition(input.getLocation());
      if (oldPlayerName != null) {
        World world = MinecraftServer.func_71276_C().func_130014_f_();
        EntityPlayer player = world.func_72924_a(oldPlayerName);
        if (player instanceof EntityPlayerMP)
          ModuleEggHunt.getInstance().sendEggHunt((EntityPlayerMP)player, false, false); 
      } 
      if (input.getPlayerName() != null) {
        World world = MinecraftServer.func_71276_C().func_130014_f_();
        EntityPlayer player = world.func_72924_a(input.getPlayerName());
        if (player instanceof EntityPlayerMP)
          ModuleEggHunt.getInstance().sendEggHunt((EntityPlayerMP)player, true, false); 
      } 
      callback.accept(ApiServices.Http.getEggHuntService().setOwner(input).execute());
    } 
  }
  
  public static void resetEggBlock(World world) {
    if (data.isEndEvent()) {
      BlockLocation location = (PEnd.getServer().getConfig()).egg.get(0);
      world.func_147465_d(location.getX(), location.getY(), location.getZ(), PERegisterer.egg, 7, 2);
    } else {
      world.func_147465_d(config.getEggPosition().getX(), config.getEggPosition().getY(), config.getEggPosition().getZ(), PERegisterer.egg, 6, 2);
    } 
  }
  
  public static String getPrefix() {
    return data.isEndEvent() ? "§8[§dEnd§8] " : "§8[§cEggHunt§8] ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\PEggHunt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */