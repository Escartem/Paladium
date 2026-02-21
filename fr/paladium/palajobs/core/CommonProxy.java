package fr.paladium.palajobs.core;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.core.pojo.types.MultiAchievement;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.achievements.PalaJobsFarmingPlantAchievement;
import fr.paladium.palajobs.core.achievements.PalaJobsFertilizedDirtHoeAchievement;
import fr.paladium.palajobs.core.achievements.PalaJobsHunterKillAchievement;
import fr.paladium.palajobs.core.achievements.PalaJobsMinerOreAchievement;
import fr.paladium.palajobs.core.achievements.PalaJobsReachLevelAchievement;
import fr.paladium.palajobs.core.entity.EntityBambooBoat;
import fr.paladium.palajobs.core.entity.EntityCustomFishHook;
import fr.paladium.palajobs.core.entity.gecko.network.SCPacketEntityPlayAnimation;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.network.packet.server.SCPacketNotification;
import fr.paladium.palajobs.core.network.packet.server.SCPacketQuest;
import fr.paladium.palajobs.core.network.packet.server.SCPacketSyncExtendedJobsPlayerData;
import fr.paladium.palajobs.core.packets.client.CSPacketChooseLvlTokenReward;
import fr.paladium.palajobs.core.packets.client.CSPacketFishingInteraction;
import fr.paladium.palajobs.core.packets.client.CSPacketOpenBossContainer;
import fr.paladium.palajobs.core.packets.client.CSPacketOpenBossGui;
import fr.paladium.palajobs.core.packets.client.CSPacketOpenLvlTokenGui;
import fr.paladium.palajobs.core.packets.client.CSPacketUseLvlToken;
import fr.paladium.palajobs.core.packets.client.CSPalajobsGiveItemQuest;
import fr.paladium.palajobs.core.packets.server.SCOpenUiJobsHome;
import fr.paladium.palajobs.core.packets.server.SCPacketFishing;
import fr.paladium.palajobs.core.packets.server.SCPacketOpenBossGui;
import fr.paladium.palajobs.core.packets.server.SCPacketOpenLvlTokenGui;
import fr.paladium.palajobs.core.packets.server.SCPacketUpdateLvlTokenGui;
import fr.paladium.palajobs.core.pojo.boss.JobBossEep;
import fr.paladium.palajobs.core.profile.ModuleJobs;
import fr.paladium.palajobs.core.quest.QuestRegistry;
import fr.paladium.palajobs.core.registry.BlocksRegistry;
import fr.paladium.palajobs.core.registry.CraftRegistry;
import fr.paladium.palajobs.core.registry.FishingRegistry;
import fr.paladium.palajobs.core.registry.ItemsRegistry;
import fr.paladium.palajobs.core.registry.JobRegistry;
import fr.paladium.palajobs.core.tokens.LvlTokenRegistry;
import fr.paladium.palajobs.core.utils.GuiHandler;
import fr.paladium.palajobs.server.listener.CustomFishEventHandler;
import fr.paladium.palajobs.server.listener.EventsHandler;
import fr.paladium.profile.common.module.ProfileModules;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
  private SimpleNetworkWrapper network;
  
  public SimpleNetworkWrapper getNetwork() {
    return this.network;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    JobsPlayer.register();
    NetworkRegistry.INSTANCE.registerGuiHandler(PalaJobs.instance, (IGuiHandler)new GuiHandler());
    registerEvent(new EventsHandler());
    registerEvent(new CustomFishEventHandler());
    this.network = new SimpleNetworkWrapper("palajobs");
    this.network.registerMessage(SCPacketSyncExtendedJobsPlayerData.Handler.class, SCPacketSyncExtendedJobsPlayerData.class, 0, Side.CLIENT);
    this.network.registerMessage(SCPacketNotification.Handler.class, SCPacketNotification.class, 1, Side.CLIENT);
    this.network.registerMessage(SCPacketQuest.Handler.class, SCPacketQuest.class, 2, Side.CLIENT);
    this.network.registerMessage(CSPalajobsGiveItemQuest.Handler.class, CSPalajobsGiveItemQuest.class, 3, Side.SERVER);
    this.network.registerMessage(SCPacketEntityPlayAnimation.Handler.class, SCPacketEntityPlayAnimation.class, 5, Side.CLIENT);
    this.network.registerMessage(CSPacketOpenBossGui.Handler.class, CSPacketOpenBossGui.class, 6, Side.SERVER);
    this.network.registerMessage(SCPacketOpenBossGui.Handler.class, SCPacketOpenBossGui.class, 7, Side.CLIENT);
    this.network.registerMessage(CSPacketOpenBossContainer.Handler.class, CSPacketOpenBossContainer.class, 8, Side.SERVER);
    this.network.registerMessage(SCPacketFishing.Handler.class, SCPacketFishing.class, 9, Side.CLIENT);
    this.network.registerMessage(CSPacketFishingInteraction.Handler.class, CSPacketFishingInteraction.class, 10, Side.SERVER);
    this.network.registerMessage(CSPacketOpenLvlTokenGui.Handler.class, CSPacketOpenLvlTokenGui.class, 11, Side.SERVER);
    this.network.registerMessage(SCPacketOpenLvlTokenGui.Handler.class, SCPacketOpenLvlTokenGui.class, 12, Side.CLIENT);
    this.network.registerMessage(CSPacketUseLvlToken.Handler.class, CSPacketUseLvlToken.class, 13, Side.SERVER);
    this.network.registerMessage(SCPacketUpdateLvlTokenGui.Handler.class, SCPacketUpdateLvlTokenGui.class, 14, Side.CLIENT);
    this.network.registerMessage(CSPacketChooseLvlTokenReward.Handler.class, CSPacketChooseLvlTokenReward.class, 15, Side.SERVER);
    this.network.registerMessage(SCOpenUiJobsHome.Handler.class, SCOpenUiJobsHome.class, 16, Side.CLIENT);
    JobRegistry.getInstance().registerJobs();
    QuestRegistry.getInstance().registerQuests();
    BlocksRegistry.registerBlocks();
    ItemsRegistry.registerItems();
    EntityRegistry.registerModEntity(EntityBambooBoat.class, "entityBambooBoat", 0, PalaJobs.instance, 64, 1, true);
    EntityRegistry.registerModEntity(EntityCustomFishHook.class, "entityCustomFishHook", 1, PalaJobs.instance, 64, 1, true);
    CraftRegistry.getInstance().register();
    FishingRegistry.register();
    LvlTokenRegistry.register();
    ExtendedUtils.registerExtended(EntityPlayer.class, JobBossEep.class, "boss_job_eep", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT });
    ProfileModules.getInstance().registerModule(ModuleJobs.class);
  }
  
  public void onInit(FMLInitializationEvent event) {}
  
  public void onPostInit(FMLPostInitializationEvent event) {
    PalaJobsReachLevelAchievement minerLvl2 = PalaJobsReachLevelAchievement.builder().id("palajobs.reachlevel.multi.1").category(AchievementCategory.HOW_TO_START).name("I").description("Atteindre mineur niveau 2").level(2).jobType(JobType.MINER).nbToValidate(1).build();
    PalaJobsReachLevelAchievement hunterLvl2 = PalaJobsReachLevelAchievement.builder().id("palajobs.reachlevel.multi.2").category(AchievementCategory.HOW_TO_START).name("II").description("Atteindre hunter niveau 2").level(2).jobType(JobType.HUNTER).nbToValidate(1).build();
    PalaJobsReachLevelAchievement alchemistLvl2 = PalaJobsReachLevelAchievement.builder().id("palajobs.reachlevel.multi.3").category(AchievementCategory.HOW_TO_START).name("III").description("Atteindre alchimiste niveau 2").level(2).jobType(JobType.ALCHEMIST).nbToValidate(1).build();
    PalaJobsReachLevelAchievement farmerLvl2 = PalaJobsReachLevelAchievement.builder().id("palajobs.reachlevel.multi.4").category(AchievementCategory.HOW_TO_START).name("IV").description("Atteindre farmeur niveau 2").level(2).jobType(JobType.FARMER).nbToValidate(1).build();
    MultiAchievement.builder()
      .id("palajobs.reachlevel.multi.all")
      .category(AchievementCategory.HOW_TO_START)
      .name("Multitâche")
      .description("Atteindre le niveau 2 dans tous vos métiers")
      .icon("MTptaW5lY3JhZnQ6c3RvbmVfaG9lOjAj")
      .build()
      .addSubAchievement((Achievement)minerLvl2)
      .addSubAchievement((Achievement)hunterLvl2)
      .addSubAchievement((Achievement)alchemistLvl2)
      .addSubAchievement((Achievement)farmerLvl2)
      .register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.alchimist.lvl10")
      .category(AchievementCategory.JOBS)
      .name("Alchimiste novice")
      .description("Atteindre le niveau 10 dans le métier d'alchimiste")
      .level(10)
      .jobType(JobType.ALCHEMIST)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6cG90aW9uOjAj")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.alchimist.lvl25")
      .category(AchievementCategory.JOBS)
      .name("Alchimiste novice")
      .description("Atteindre le niveau 25 dans le métier d'alchimiste")
      .level(25)
      .jobType(JobType.ALCHEMIST)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6cG90aW9uOjgxOTMj")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.alchimist.lvl50")
      .category(AchievementCategory.JOBS)
      .name("Alchimiste professionnel")
      .description("Atteindre le niveau 50 dans le métier d'alchimiste")
      .level(50)
      .jobType(JobType.ALCHEMIST)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6cG90aW9uOjgyNjEj")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.alchimist.lvl75")
      .category(AchievementCategory.JOBS)
      .name("Alchimiste spécialiste")
      .description("Atteindre le niveau 75 dans le métier d'alchimiste")
      .level(75)
      .jobType(JobType.ALCHEMIST)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6cG90aW9uOjgyMzgj")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.alchimist.lvl100")
      .category(AchievementCategory.JOBS)
      .name("Alchimiste expert")
      .description("Atteindre le niveau 100 dans le métier d'alchimiste")
      .level(100)
      .jobType(JobType.ALCHEMIST)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6cG90aW9uOjgyNjgj")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.farmer.lvl10")
      .category(AchievementCategory.JOBS)
      .name("Farmeur novice")
      .description("Atteindre le niveau 10 dans le métier de farmeur")
      .level(10)
      .jobType(JobType.FARMER)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6d29vZGVuX2hvZTowIw==")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.farmer.lvl25")
      .category(AchievementCategory.JOBS)
      .name("Farmeur novice")
      .description("Atteindre le niveau 25 dans le métier de farmeur")
      .level(25)
      .jobType(JobType.FARMER)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6c3RvbmVfaG9lOjAj")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.farmer.lvl50")
      .category(AchievementCategory.JOBS)
      .name("Farmeur professionnel")
      .description("Atteindre le niveau 50 dans le métier de farmeur")
      .level(50)
      .jobType(JobType.FARMER)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6aXJvbl9ob2U6MCM=")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.farmer.lvl75")
      .category(AchievementCategory.JOBS)
      .name("Farmeur spécialiste")
      .description("Atteindre le niveau 75 dans le métier de farmeur")
      .level(75)
      .jobType(JobType.FARMER)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6Z29sZGVuX2hvZTowIw==")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.farmer.lvl100")
      .category(AchievementCategory.JOBS)
      .name("Farmeur expert")
      .description("Atteindre le niveau 100 dans le métier de farmeur")
      .level(100)
      .jobType(JobType.FARMER)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6ZGlhbW9uZF9ob2U6MCM=")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.miner.lvl10")
      .category(AchievementCategory.JOBS)
      .name("Mineur novice")
      .description("Atteindre le niveau 10 dans le métier de mineur")
      .level(10)
      .jobType(JobType.MINER)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6d29vZGVuX3BpY2theGU6MCM=")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.miner.lvl25")
      .category(AchievementCategory.JOBS)
      .name("Mineur novice")
      .description("Atteindre le niveau 25 dans le métier de mineur")
      .level(25)
      .jobType(JobType.MINER)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6c3RvbmVfcGlja2F4ZTowIw==")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.miner.lvl50")
      .category(AchievementCategory.JOBS)
      .name("Mineur professionnel")
      .description("Atteindre le niveau 50 dans le métier de mineur")
      .level(50)
      .jobType(JobType.MINER)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6aXJvbl9waWNrYXhlOjAj")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.miner.lvl75")
      .category(AchievementCategory.JOBS)
      .name("Mineur spécialiste")
      .description("Atteindre le niveau 75 dans le métier de mineur")
      .level(75)
      .jobType(JobType.MINER)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6Z29sZGVuX3BpY2theGU6MCM=")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.miner.lvl100")
      .category(AchievementCategory.JOBS)
      .name("Mineur expert")
      .description("Atteindre le niveau 100 dans le métier de mineur")
      .level(100)
      .jobType(JobType.MINER)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6ZGlhbW9uZF9waWNrYXhlOjAj")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.hunter.lvl10")
      .category(AchievementCategory.JOBS)
      .name("Hunter novice")
      .description("Atteindre le niveau 10 dans le métier de hunter")
      .level(10)
      .jobType(JobType.HUNTER)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6d29vZGVuX3N3b3JkOjAj")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.hunter.lvl25")
      .category(AchievementCategory.JOBS)
      .name("Hunter novice")
      .description("Atteindre le niveau 25 dans le métier de hunter")
      .level(25)
      .jobType(JobType.HUNTER)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6c3RvbmVfc3dvcmQ6MCM=")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.hunter.lvl50")
      .category(AchievementCategory.JOBS)
      .name("Hunter professionnel")
      .description("Atteindre le niveau 50 dans le métier de hunter")
      .level(50)
      .jobType(JobType.HUNTER)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6aXJvbl9zd29yZDowIw==")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.hunter.lvl75")
      .category(AchievementCategory.JOBS)
      .name("Hunter spécialiste")
      .description("Atteindre le niveau 75 dans le métier de hunter")
      .level(75)
      .jobType(JobType.HUNTER)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6Z29sZGVuX3N3b3JkOjAj")
      .build().register();
    PalaJobsReachLevelAchievement.builder()
      .id("palajobs.reachlevel.hunter.lvl100")
      .category(AchievementCategory.JOBS)
      .name("Hunter expert")
      .description("Atteindre le niveau 100 dans le métier de hunter")
      .level(100)
      .jobType(JobType.HUNTER)
      .nbToValidate(1)
      .icon("MTptaW5lY3JhZnQ6ZGlhbW9uZF9zd29yZDowIw==")
      .build().register();
    PalaJobsReachLevelAchievement minerLvl100 = PalaJobsReachLevelAchievement.builder().id("palajobs.reachlevel.multi100.1").category(AchievementCategory.HOW_TO_START).name("I").description("Terminer l'achievement \"Mineur expert\"").level(100).jobType(JobType.MINER).nbToValidate(1).build();
    PalaJobsReachLevelAchievement hunterLvl100 = PalaJobsReachLevelAchievement.builder().id("palajobs.reachlevel.multi100.2").category(AchievementCategory.HOW_TO_START).name("II").description("Terminer l'achievement \"Hunter\" expert\"").level(100).jobType(JobType.HUNTER).nbToValidate(1).build();
    PalaJobsReachLevelAchievement alchemistLvl100 = PalaJobsReachLevelAchievement.builder().id("palajobs.reachlevel.multi100.3").category(AchievementCategory.HOW_TO_START).name("III").description("Terminer l'achievement \"Alchimiste\" expert\"").level(100).jobType(JobType.ALCHEMIST).nbToValidate(1).build();
    PalaJobsReachLevelAchievement farmerLvl100 = PalaJobsReachLevelAchievement.builder().id("palajobs.reachlevel.multi100.4").category(AchievementCategory.HOW_TO_START).name("IV").description("Terminer l'achievement \"Farmeur\" expert\"").level(100).jobType(JobType.FARMER).nbToValidate(1).build();
    MultiAchievement.builder()
      .id("palajobs.reachlevel.multi100.all")
      .category(AchievementCategory.HOW_TO_START)
      .name("Expert général")
      .description("Terminer ces quatre achievements")
      .icon("MTptaW5lY3JhZnQ6ZGlhbW9uZDowIw==")
      .build()
      .addSubAchievement((Achievement)minerLvl100)
      .addSubAchievement((Achievement)hunterLvl100)
      .addSubAchievement((Achievement)alchemistLvl100)
      .addSubAchievement((Achievement)farmerLvl100)
      .register();
    PalaJobsFarmingPlantAchievement.builder()
      .id("palajobs.farmingplant.5k")
      .category(AchievementCategory.JOBS)
      .name("Petite exploitation agricole")
      .description("Récolter 5 000 plantations")
      .nbToValidate(5000)
      .icon("MTptaW5lY3JhZnQ6d2hlYXRfc2VlZHM6MCM=")
      .build().register();
    PalaJobsFarmingPlantAchievement.builder()
      .id("palajobs.farmingplant.25k")
      .category(AchievementCategory.JOBS)
      .name("Moyenne exploitation agricole")
      .description("Récolter 25 000 plantations")
      .nbToValidate(25000)
      .icon("MTptaW5lY3JhZnQ6ZHllOjMj")
      .build().register();
    PalaJobsFarmingPlantAchievement.builder()
      .id("palajobs.farmingplant.150k")
      .category(AchievementCategory.JOBS)
      .name("Grande exploitation agricole")
      .description("Récolter 150 000 plantations")
      .nbToValidate(150000)
      .icon("MTptaW5lY3JhZnQ6cmVlZHM6MCM=")
      .build().register();
    PalaJobsFarmingPlantAchievement.builder()
      .id("palajobs.farmingplant.1m")
      .category(AchievementCategory.JOBS)
      .name("Gigantesque exploitation agricole")
      .description("Récolter 1 000 000 plantations")
      .nbToValidate(1000000)
      .icon("MTptaW5lY3JhZnQ6bmV0aGVyX3dhcnQ6MCM=")
      .build().register();
    PalaJobsFertilizedDirtHoeAchievement.builder()
      .id("palajobs.fertilizeddirthoe.5k")
      .category(AchievementCategory.JOBS)
      .name("Un bon fertilisant")
      .description("Transformer 5 000 terre en terre fertilisée")
      .nbToValidate(5000)
      .icon("MTpwYWxham9iczppdGVtLnBhbGFkaXVtX3JhZGl1c19ob2U6MCM=")
      .build().register();
    PalaJobsMinerOreAchievement.builder()
      .id("palajobs.minerore.2k5")
      .category(AchievementCategory.JOBS)
      .name("Petite exploitation minière")
      .description("Casser 2 500 minerais")
      .nbToValidate(2500)
      .icon("MTptaW5lY3JhZnQ6d29vZGVuX3BpY2theGU6MCM=")
      .build().register();
    PalaJobsMinerOreAchievement.builder()
      .id("palajobs.minerore.12k5")
      .category(AchievementCategory.JOBS)
      .name("Moyenne exploitation minière")
      .description("Casser 12 500 minerais")
      .nbToValidate(12500)
      .icon("MTptaW5lY3JhZnQ6aXJvbl9waWNrYXhlOjAj")
      .build().register();
    PalaJobsMinerOreAchievement.builder()
      .id("palajobs.minerore.75k")
      .category(AchievementCategory.JOBS)
      .name("Grande exploitation minière")
      .description("Casser 75 000 minerais")
      .nbToValidate(75000)
      .icon("MTptaW5lY3JhZnQ6Z29sZGVuX3BpY2theGU6MCM=")
      .build().register();
    PalaJobsMinerOreAchievement.builder()
      .id("palajobs.minerore.500k")
      .category(AchievementCategory.JOBS)
      .name("Gigantesque exploitation minière")
      .description("Casser 500 000 minerais")
      .nbToValidate(500000)
      .icon("MTptaW5lY3JhZnQ6ZGlhbW9uZF9waWNrYXhlOjAj")
      .build().register();
    PalaJobsHunterKillAchievement.builder()
      .id("palajobs.hunterkill.7k5")
      .category(AchievementCategory.JOBS)
      .name("Petite exploitation de chasse")
      .description("Tuer 7 500 monstres")
      .nbToValidate(7500)
      .icon("MTpwYWxhbW9kOml0ZW0uYW1ldGh5c3Quc3dvcmQ6MCM=")
      .build().register();
    PalaJobsHunterKillAchievement.builder()
      .id("palajobs.hunterkill.37k5")
      .category(AchievementCategory.JOBS)
      .name("Moyenne exploitation de chasse")
      .description("Tuer 37 500 monstres")
      .nbToValidate(37500)
      .icon("MTpwYWxhbW9kOml0ZW0udGl0YW5lLnN3b3JkOjAj")
      .build().register();
    PalaJobsHunterKillAchievement.builder()
      .id("palajobs.hunterkill.225k")
      .category(AchievementCategory.JOBS)
      .name("Grande exploitation de chasse")
      .description("Tuer 225 000 monstres")
      .nbToValidate(225000)
      .icon("MTpwYWxhbW9kOml0ZW0ucGFsYWRpdW0uc3dvcmQ6MCM=")
      .build().register();
    PalaJobsHunterKillAchievement.builder()
      .id("palajobs.hunterkill.1m5")
      .category(AchievementCategory.JOBS)
      .name("Gigantesque exploitation de chasse")
      .description("Tuer 1 500 000 monstres")
      .nbToValidate(1500000)
      .icon("MTpwYWxhbW9kOml0ZW0uZW5kaXVtLnN3b3JkOjAj")
      .build().register();
  }
  
  public void onServerStarted(FMLServerStartedEvent event) {}
  
  private void registerEvent(Object object) {
    FMLCommonHandler.instance().bus().register(object);
    MinecraftForge.EVENT_BUS.register(object);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */