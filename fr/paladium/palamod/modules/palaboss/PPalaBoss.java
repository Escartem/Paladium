package fr.paladium.palamod.modules.palaboss;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palamod.modules.factions.utils.JsonUtils;
import fr.paladium.palamod.modules.palaboss.client.models.ModelAzhur;
import fr.paladium.palamod.modules.palaboss.commands.PalaBossCommand;
import fr.paladium.palamod.modules.palaboss.common.attacks.ArachnaRushAttack;
import fr.paladium.palamod.modules.palaboss.common.attacks.AttackExploseWeb;
import fr.paladium.palamod.modules.palaboss.common.attacks.AttacksRegistry;
import fr.paladium.palamod.modules.palaboss.common.attacks.DjinnAttack;
import fr.paladium.palamod.modules.palaboss.common.attacks.EarthQuakeAttack;
import fr.paladium.palamod.modules.palaboss.common.attacks.ElectrocutionAttack;
import fr.paladium.palamod.modules.palaboss.common.attacks.EruptionAttack;
import fr.paladium.palamod.modules.palaboss.common.attacks.IceProjectileAttack;
import fr.paladium.palamod.modules.palaboss.common.attacks.MorsulaChargeAttack;
import fr.paladium.palamod.modules.palaboss.common.attacks.PunchAttack;
import fr.paladium.palamod.modules.palaboss.common.attacks.SandAttack;
import fr.paladium.palamod.modules.palaboss.common.attacks.SpawnSbireAttack;
import fr.paladium.palamod.modules.palaboss.common.attacks.TeethShockwaveAttack;
import fr.paladium.palamod.modules.palaboss.common.attacks.ThrowProjectileAttack;
import fr.paladium.palamod.modules.palaboss.common.attacks.TobaltChargeAttack;
import fr.paladium.palamod.modules.palaboss.common.attacks.WebLaunchAttack;
import fr.paladium.palamod.modules.palaboss.common.block.BlockPoisonousWeb;
import fr.paladium.palamod.modules.palaboss.common.boss.BossRegistry;
import fr.paladium.palamod.modules.palaboss.common.config.PalabossConfig;
import fr.paladium.palamod.modules.palaboss.common.eep.BossEEP;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityArachna;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityAzhur;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBeepBoop;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityMorsula;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityTobalt;
import fr.paladium.palamod.modules.palaboss.common.events.EventHandler;
import fr.paladium.palamod.modules.palaboss.common.init.EntityInit;
import fr.paladium.palamod.modules.palaboss.common.network.MessageSyncTobaltToClient;
import fr.paladium.palamod.modules.palaboss.common.network.PacketSendBossNotification;
import fr.paladium.palamod.modules.palaboss.common.utils.BossCommonConfig;
import fr.paladium.palamod.modules.palaboss.common.utils.ServerBossData;
import fr.paladium.palamod.modules.palaboss.profile.ModuleBoss;
import fr.paladium.palamod.modules.palaboss.proxies.CommonProxy;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import fr.paladium.palamod.util.JsonToNBT;
import fr.paladium.profile.common.module.ProfileModules;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.command.ICommand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

@ObjectHolder("palamod")
@Pulse(id = "palamod-palaboss", description = "Modular boss system", forced = true)
@DoNotRename
public class PPalaBoss {
  @Instance("palamod-palaboss")
  public static PPalaBoss instance;
  
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.palaboss.client.ClientProxy", serverSide = "fr.paladium.palamod.modules.palaboss.proxies.ServerProxy")
  public static CommonProxy proxy;
  
  public static SimpleNetworkWrapper network;
  
  public static BlockPoisonousWeb poisonousWeb;
  
  public static BossCommonConfig commonConfig;
  
  public static File configFile;
  
  public static PalabossConfig config;
  
  public static ServerBossData serverBossData;
  
  public static Map<UUID, Float> bossDamage;
  
  public static float bossTotalDamage;
  
  public static int lastWinPercentage;
  
  public static String lastWinPlayer;
  
  public static String lastWinFaction;
  
  @Handler
  public void preInit(FMLPreInitializationEvent e) {
    bossDamage = new HashMap<>();
    bossTotalDamage = 0.0F;
    if (e.getSide() == Side.SERVER)
      try {
        configFile = new File(e.getModConfigurationDirectory(), "palaboss.json");
        config = (PalabossConfig)JsonUtils.getFileObject(e.getModConfigurationDirectory().getAbsolutePath(), "palaboss.json", PalabossConfig.class);
      } catch (Exception e1) {
        System.err.println("[PalaBoss] Configuration not found: " + e.getModConfigurationDirectory().getAbsolutePath() + "/palaboss.json");
      }  
    FMLCommonHandler.instance().bus().register(new EventHandler());
    MinecraftForge.EVENT_BUS.register(new EventHandler());
    network = NetworkRegistry.INSTANCE.newSimpleChannel("palaboss");
    AttacksRegistry.INSTANCE.registerAttack("throwProjectile", ThrowProjectileAttack.class);
    AttacksRegistry.INSTANCE.registerAttack("earthQuake", EarthQuakeAttack.class);
    AttacksRegistry.INSTANCE.registerAttack("punch", PunchAttack.class);
    AttacksRegistry.INSTANCE.registerAttack("tobaltCharge", TobaltChargeAttack.class);
    AttacksRegistry.INSTANCE.registerAttack("eruption", EruptionAttack.class);
    AttacksRegistry.INSTANCE.registerAttack("spawnSbires", SpawnSbireAttack.class);
    AttacksRegistry.INSTANCE.registerAttack("teethShockwave", TeethShockwaveAttack.class);
    AttacksRegistry.INSTANCE.registerAttack("morsulaCharge", MorsulaChargeAttack.class);
    AttacksRegistry.INSTANCE.registerAttack("exploseWeb", AttackExploseWeb.class);
    AttacksRegistry.INSTANCE.registerAttack("electrocution", ElectrocutionAttack.class);
    AttacksRegistry.INSTANCE.registerAttack("sand", SandAttack.class);
    AttacksRegistry.INSTANCE.registerAttack("djinn", DjinnAttack.class);
    AttacksRegistry.INSTANCE.registerAttack("iceProjectile", IceProjectileAttack.class);
    AttacksRegistry.INSTANCE.registerAttack("arachnaRush", ArachnaRushAttack.class);
    AttacksRegistry.INSTANCE.registerAttack("webLaunch", WebLaunchAttack.class);
    BossRegistry.INSTANCE.registerBoss("beepBoop", 450, EntityBeepBoop.class, true);
    BossRegistry.INSTANCE.registerBoss("tobalt", 451, EntityTobalt.class, false);
    BossRegistry.INSTANCE.registerBoss("azhur", 453, EntityAzhur.class, false);
    BossRegistry.INSTANCE.registerBoss("morsula", 454, EntityMorsula.class, true);
    BossRegistry.INSTANCE.registerBoss("arachna", 455, EntityArachna.class, true);
    ExtendedUtils.registerExtended(EntityPlayer.class, BossEEP.class, "boss_eep", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT });
    ProfileModules.getInstance().registerModule(ModuleBoss.class);
    EntityInit.registerProjectiles();
    if (e.getSide() == Side.CLIENT)
      ModelAzhur.startChecks(); 
    poisonousWeb = (BlockPoisonousWeb)(new BlockPoisonousWeb()).func_149713_g(1).func_149711_c(4.0F).func_149663_c("poisonous_web").func_149658_d("palamod:poisonous_web");
    GameRegistry.registerBlock((Block)poisonousWeb, "poisonous_web");
    network.registerMessage(MessageSyncTobaltToClient.Handler.class, MessageSyncTobaltToClient.class, 0, Side.CLIENT);
    network.registerMessage(PacketSendBossNotification.Handler.class, PacketSendBossNotification.class, 1, Side.CLIENT);
    if (e.getSide() == Side.CLIENT)
      JsonToNBT.startChecks(); 
    proxy.preInit(e);
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent e) {
    proxy.postInit(e);
  }
  
  @Handler
  public void serverStarting(FMLServerStartingEvent e) {
    e.registerServerCommand((ICommand)new PalaBossCommand());
  }
  
  @Handler
  public void serverStarted(FMLServerStartedEvent e) {
    proxy.serverStarted(e);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\PPalaBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */