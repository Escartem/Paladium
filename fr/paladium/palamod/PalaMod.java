package fr.paladium.palamod;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.paladium.palamod.client.utils.Utils;
import fr.paladium.palamod.common.PProxyCommon;
import fr.paladium.palamod.event.ServerHandler;
import fr.paladium.palamod.kiwi.Kiwi;
import fr.paladium.palamod.metrics.bigbrother.BigBrotherImpl;
import fr.paladium.palamod.metrics.prometheus.MetricEventHandler;
import fr.paladium.palamod.metrics.prometheus.PrometheusController;
import fr.paladium.palamod.modules.ablueprint.BluePrintRegistry;
import fr.paladium.palamod.modules.achievements.AchievementsManager;
import fr.paladium.palamod.modules.automation.AutomationManager;
import fr.paladium.palamod.modules.mount.MountManager;
import fr.paladium.palamod.modules.rank.RankListener;
import fr.paladium.palamod.modules.ranking.RankingManager;
import fr.paladium.palamod.pulsar.PulseManager;
import fr.paladium.palamod.pulsar.configs.IConfiguration;
import fr.paladium.palamod.pulsar.configs.MConfigs;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palamod.util.PalaWatchdog;
import fr.paladium.palamod.util.PlayerUtil;
import fr.paladium.palamod.util.TPSTracker;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.Security;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatBasic;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonSerializableSet;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "palamod", name = "PalaMod", version = "8.0.1.6", dependencies = "required-after:Forge;required-after:guardiangolem;required-after:ariane;required-after:customnpcs;required-after:helios;required-after:palashop;required-after:apollon;required-after:palaforge-utils;required-after:palapass;required-after:palajobs;required-after:achievement;required-after:lindworm;required-after:mount;required-after:factions;required-after:palapet;required-after:palavanillagui;required-after:palaspawner;required-after:palaautomation", acceptedMinecraftVersions = "[1.7.10]")
@DoNotRename
public class PalaMod {
  @Instance("palamod")
  public static PalaMod instance;
  
  @SidedProxy(clientSide = "fr.paladium.palamod.client.PProxyClient", serverSide = "fr.paladium.palamod.common.PProxyCommon")
  public static PProxyCommon proxy;
  
  public static PulseManager pulsar = new PulseManager((IConfiguration)new MConfigs("palamod-modules", "Disable the modules of the mod here"));
  
  public static Kiwi kiwi = new Kiwi(instance, pulsar);
  
  public static SimpleNetworkWrapper network;
  
  public static SimpleNetworkWrapper netHandler;
  
  public static File conf;
  
  public PalaMod() {
    if (FMLCommonHandler.instance().getSide().isClient() && Constants.Environment.RELEASE.equals(Constants.MOD_ENV))
      PlayerUtil.fix(); 
    Constants.logger.info("Paladium V9 to be started! :3 [" + Constants.MOD_ENV + "]");
  }
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    conf = event.getModConfigurationDirectory();
    Security.setProperty("crypto.policy", "unlimited");
    Constants.logger.info("Entering preinitialization phase..");
    Utils.setSide(event.getSide());
    if (Constants.MOD_ENV == Constants.Environment.RELEASE) {
      File versionFile = new File(new File(conf, "palamod"), "mod_version.dat");
      if (!versionFile.exists())
        throw new RuntimeException("Unable to find mod_version file! Please reinstall the mod."); 
      try {
        Constants.CDN_VERSION = (new String(Files.readAllBytes(versionFile.toPath()), StandardCharsets.UTF_8)).trim();
      } catch (IOException e) {
        if (!versionFile.delete())
          throw new RuntimeException("Unable to delete mod_version file! Please reinstall the mod.", e); 
        throw new RuntimeException("Unable to read mod_version file! Please reinstall the mod.", e);
      } 
    } 
    kiwi.preInit(event);
  }
  
  @EventHandler
  public void init(FMLInitializationEvent event) {
    Constants.logger.info("Entering initialization phase..");
    kiwi.init(event);
  }
  
  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
    Constants.logger.info("Entering postinitialization phase..");
    kiwi.postInit(event);
    Constants.logger.info("Successfully loaded " + pulsar.getSize() + " modules!");
    TPSTracker.INSTANCE.runTaskTimer(20L, 20L);
    if (FMLCommonHandler.instance().getSide().isServer()) {
      PrometheusController.INSTANCE.start();
      FMLCommonHandler.instance().bus().register(new MetricEventHandler());
      MinecraftForge.EVENT_BUS.register(new MetricEventHandler());
      new BigBrotherImpl(conf);
    } 
    FMLCommonHandler.instance().bus().register(new ServerHandler());
    MinecraftForge.EVENT_BUS.register(new ServerHandler());
    if (event.getSide().isClient()) {
      PaladiumScheduler.INSTANCE.runTaskLater(this::fixStatandAchivment, 20L);
    } else {
      fixStatandAchivment();
    } 
    AchievementsManager.register();
    MountManager.init();
    RankingManager.init();
    BluePrintRegistry.init();
    AutomationManager.getInstance().registerCrafts();
    RankListener.register();
  }
  
  private void fixStatandAchivment() {
    Class<StatList> myClass = StatList.class;
    try {
      Field f = getField(myClass, "oneShotStats");
      f.setAccessible(true);
      Map map = (Map)f.get((Object)null);
      map.clear();
    } catch (NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException e) {
      try {
        Field f = getField(myClass, "field_75942_a");
        f.setAccessible(true);
        Map map = (Map)f.get((Object)null);
        map.clear();
      } catch (NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException noSuchFieldException) {}
    } 
    StatList.field_75941_c.clear();
    StatList.field_75939_e.clear();
    StatList.field_75938_d.clear();
    AchievementList.field_76007_e.clear();
    StatList.field_75947_j = (new StatBasic("stat.leaveGame", (IChatComponent)new ChatComponentTranslation("stat.leaveGame", new Object[0]))).func_75966_h().func_75971_g();
    StatList.field_75948_k = (new StatBasic("stat.playOneMinute", (IChatComponent)new ChatComponentTranslation("stat.playOneMinute", new Object[0]), StatBase.field_75981_i)).func_75966_h().func_75971_g();
    StatList.field_75945_l = (new StatBasic("stat.walkOneCm", (IChatComponent)new ChatComponentTranslation("stat.walkOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    StatList.field_75946_m = (new StatBasic("stat.swimOneCm", (IChatComponent)new ChatComponentTranslation("stat.swimOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    StatList.field_75943_n = (new StatBasic("stat.fallOneCm", (IChatComponent)new ChatComponentTranslation("stat.fallOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    StatList.field_75944_o = (new StatBasic("stat.climbOneCm", (IChatComponent)new ChatComponentTranslation("stat.climbOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    StatList.field_75958_p = (new StatBasic("stat.flyOneCm", (IChatComponent)new ChatComponentTranslation("stat.flyOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    StatList.field_75957_q = (new StatBasic("stat.diveOneCm", (IChatComponent)new ChatComponentTranslation("stat.diveOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    StatList.field_75956_r = (new StatBasic("stat.minecartOneCm", (IChatComponent)new ChatComponentTranslation("stat.minecartOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    StatList.field_75955_s = (new StatBasic("stat.boatOneCm", (IChatComponent)new ChatComponentTranslation("stat.boatOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    StatList.field_75954_t = (new StatBasic("stat.pigOneCm", (IChatComponent)new ChatComponentTranslation("stat.pigOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    StatList.field_151185_q = (new StatBasic("stat.horseOneCm", (IChatComponent)new ChatComponentTranslation("stat.horseOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
    StatList.field_75953_u = (new StatBasic("stat.jump", (IChatComponent)new ChatComponentTranslation("stat.jump", new Object[0]))).func_75966_h().func_75971_g();
    StatList.field_75952_v = (new StatBasic("stat.drop", (IChatComponent)new ChatComponentTranslation("stat.drop", new Object[0]))).func_75966_h().func_75971_g();
    StatList.field_75951_w = (new StatBasic("stat.damageDealt", (IChatComponent)new ChatComponentTranslation("stat.damageDealt", new Object[0]), StatBase.field_111202_k)).func_75971_g();
    StatList.field_75961_x = (new StatBasic("stat.damageTaken", (IChatComponent)new ChatComponentTranslation("stat.damageTaken", new Object[0]), StatBase.field_111202_k)).func_75971_g();
    StatList.field_75960_y = (new StatBasic("stat.deaths", (IChatComponent)new ChatComponentTranslation("stat.deaths", new Object[0]))).func_75971_g();
    StatList.field_75959_z = (new StatBasic("stat.mobKills", (IChatComponent)new ChatComponentTranslation("stat.mobKills", new Object[0]))).func_75971_g();
    StatList.field_151186_x = (new StatBasic("stat.animalsBred", (IChatComponent)new ChatComponentTranslation("stat.animalsBred", new Object[0]))).func_75971_g();
    StatList.field_75932_A = (new StatBasic("stat.playerKills", (IChatComponent)new ChatComponentTranslation("stat.playerKills", new Object[0]))).func_75971_g();
    StatList.field_75933_B = (new StatBasic("stat.fishCaught", (IChatComponent)new ChatComponentTranslation("stat.fishCaught", new Object[0]))).func_75971_g();
    StatList.field_151183_A = (new StatBasic("stat.junkFished", (IChatComponent)new ChatComponentTranslation("stat.junkFished", new Object[0]))).func_75971_g();
    StatList.field_151184_B = (new StatBasic("stat.treasureFished", (IChatComponent)new ChatComponentTranslation("stat.treasureFished", new Object[0]))).func_75971_g();
    AchievementList.field_76004_f = (new Achievement("achievement.openInventory", "openInventory", 0, 0, Items.field_151122_aG, (Achievement)null)).func_75966_h().func_75971_g();
    AchievementList.field_76005_g = (new Achievement("achievement.mineWood", "mineWood", 2, 1, Blocks.field_150364_r, AchievementList.field_76004_f)).func_75971_g();
    AchievementList.field_76017_h = (new Achievement("achievement.buildWorkBench", "buildWorkBench", 4, -1, Blocks.field_150462_ai, AchievementList.field_76005_g)).func_75971_g();
    AchievementList.field_76018_i = (new Achievement("achievement.buildPickaxe", "buildPickaxe", 4, 2, Items.field_151039_o, AchievementList.field_76017_h)).func_75971_g();
    AchievementList.field_76015_j = (new Achievement("achievement.buildFurnace", "buildFurnace", 3, 4, Blocks.field_150460_al, AchievementList.field_76018_i)).func_75971_g();
    AchievementList.field_76016_k = (new Achievement("achievement.acquireIron", "acquireIron", 1, 4, Items.field_151042_j, AchievementList.field_76015_j)).func_75971_g();
    AchievementList.field_76013_l = (new Achievement("achievement.buildHoe", "buildHoe", 2, -3, Items.field_151017_I, AchievementList.field_76017_h)).func_75971_g();
    AchievementList.field_76014_m = (new Achievement("achievement.makeBread", "makeBread", -1, -3, Items.field_151025_P, AchievementList.field_76013_l)).func_75971_g();
    AchievementList.field_76011_n = (new Achievement("achievement.bakeCake", "bakeCake", 0, -5, Items.field_151105_aU, AchievementList.field_76013_l)).func_75971_g();
    AchievementList.field_76012_o = (new Achievement("achievement.buildBetterPickaxe", "buildBetterPickaxe", 6, 2, Items.field_151050_s, AchievementList.field_76018_i)).func_75971_g();
    AchievementList.field_76026_p = (new Achievement("achievement.cookFish", "cookFish", 2, 6, Items.field_151101_aQ, AchievementList.field_76015_j)).func_75971_g();
    AchievementList.field_76025_q = (new Achievement("achievement.onARail", "onARail", 2, 3, Blocks.field_150448_aq, AchievementList.field_76016_k)).func_75987_b().func_75971_g();
    AchievementList.field_76024_r = (new Achievement("achievement.buildSword", "buildSword", 6, -1, Items.field_151041_m, AchievementList.field_76017_h)).func_75971_g();
    AchievementList.field_76023_s = (new Achievement("achievement.killEnemy", "killEnemy", 8, -1, Items.field_151103_aS, AchievementList.field_76024_r)).func_75971_g();
    AchievementList.field_76022_t = (new Achievement("achievement.killCow", "killCow", 7, -3, Items.field_151116_aA, AchievementList.field_76024_r)).func_75971_g();
    AchievementList.field_76021_u = (new Achievement("achievement.flyPig", "flyPig", 9, -3, Items.field_151141_av, AchievementList.field_76022_t)).func_75987_b().func_75971_g();
    AchievementList.field_76020_v = (new Achievement("achievement.snipeSkeleton", "snipeSkeleton", 7, 0, (Item)Items.field_151031_f, AchievementList.field_76023_s)).func_75987_b().func_75971_g();
    AchievementList.field_76019_w = (new Achievement("achievement.diamonds", "diamonds", -1, 5, Blocks.field_150482_ag, AchievementList.field_76016_k)).func_75971_g();
    AchievementList.field_150966_x = (new Achievement("achievement.diamondsToYou", "diamondsToYou", -1, 2, Items.field_151045_i, AchievementList.field_76019_w)).func_75971_g();
    AchievementList.field_76029_x = (new Achievement("achievement.portal", "portal", -1, 7, Blocks.field_150343_Z, AchievementList.field_76019_w)).func_75971_g();
    AchievementList.field_76028_y = (new Achievement("achievement.ghast", "ghast", -4, 8, Items.field_151073_bk, AchievementList.field_76029_x)).func_75987_b().func_75971_g();
    AchievementList.field_76027_z = (new Achievement("achievement.blazeRod", "blazeRod", 0, 9, Items.field_151072_bj, AchievementList.field_76029_x)).func_75971_g();
    AchievementList.field_76001_A = (new Achievement("achievement.potion", "potion", 2, 8, (Item)Items.field_151068_bn, AchievementList.field_76027_z)).func_75971_g();
    AchievementList.field_76002_B = (new Achievement("achievement.theEnd", "theEnd", 3, 10, Items.field_151061_bv, AchievementList.field_76027_z)).func_75987_b().func_75971_g();
    AchievementList.field_76003_C = (new Achievement("achievement.theEnd2", "theEnd2", 4, 13, Blocks.field_150380_bt, AchievementList.field_76002_B)).func_75987_b().func_75971_g();
    AchievementList.field_75998_D = (new Achievement("achievement.enchantments", "enchantments", -4, 4, Blocks.field_150381_bn, AchievementList.field_76019_w)).func_75971_g();
    AchievementList.field_75999_E = (new Achievement("achievement.overkill", "overkill", -4, 1, Items.field_151048_u, AchievementList.field_75998_D)).func_75987_b().func_75971_g();
    AchievementList.field_76000_F = (new Achievement("achievement.bookcase", "bookcase", -3, 6, Blocks.field_150342_X, AchievementList.field_75998_D)).func_75971_g();
    AchievementList.field_150962_H = (new Achievement("achievement.breedCow", "breedCow", 7, -5, Items.field_151015_O, AchievementList.field_76022_t)).func_75971_g();
    AchievementList.field_150963_I = (new Achievement("achievement.spawnWither", "spawnWither", 7, 12, new ItemStack(Items.field_151144_bL, 1, 1), AchievementList.field_76003_C)).func_75971_g();
    AchievementList.field_150964_J = (new Achievement("achievement.killWither", "killWither", 7, 10, Items.field_151156_bN, AchievementList.field_150963_I)).func_75971_g();
    AchievementList.field_150965_K = (new Achievement("achievement.fullBeacon", "fullBeacon", 7, 8, (Block)Blocks.field_150461_bJ, AchievementList.field_150964_J)).func_75987_b().func_75971_g();
    AchievementList.field_150961_L = (new Achievement("achievement.exploreAllBiomes", "exploreAllBiomes", 4, 8, (Item)Items.field_151175_af, AchievementList.field_76002_B)).func_150953_b(JsonSerializableSet.class).func_75987_b().func_75971_g();
    try {
      StatList.func_151178_a();
    } catch (Exception exception) {}
  }
  
  private static Field getField(Class clazz, String fieldName) throws NoSuchFieldException {
    try {
      return clazz.getDeclaredField(fieldName);
    } catch (NoSuchFieldException e) {
      Class superClass = clazz.getSuperclass();
      if (superClass == null)
        throw e; 
      return getField(superClass, fieldName);
    } 
  }
  
  @EventHandler
  public void serverStarting(FMLServerStartingEvent event) {
    Constants.logger.info("Entering server starting phase..");
    new PalaWatchdog(Thread.currentThread());
    kiwi.serverStarting(event);
  }
  
  @EventHandler
  public void serverStoping(FMLServerStoppingEvent event) {
    PrometheusController.INSTANCE.stop();
    if (BigBrotherImpl.instance != null)
      BigBrotherImpl.instance.stop(); 
    TPSTracker.INSTANCE.cancel();
  }
  
  @EventHandler
  public void serverStart(FMLServerStartedEvent event) {
    kiwi.serverStarted(event);
  }
  
  public static Kiwi getKiwi() {
    return kiwi;
  }
  
  public static PulseManager getPulseManager() {
    return pulsar;
  }
  
  public static SimpleNetworkWrapper getNetwork() {
    return network;
  }
  
  public static SimpleNetworkWrapper getNetHandler() {
    return netHandler;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\PalaMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */