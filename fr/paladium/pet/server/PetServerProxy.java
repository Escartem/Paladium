package fr.paladium.pet.server;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palaconfiguration.server.manager.ConfigurationManager;
import fr.paladium.palaforgeutils.lib.java.map.player.SessionPlayerMap;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandBuilder;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.registry.IRegistry;
import fr.paladium.pet.common.registry.RegistryManager;
import fr.paladium.pet.server.assignement.AssignmentManager;
import fr.paladium.pet.server.assignement.handler.impl.ConnectionAssignmentHandler;
import fr.paladium.pet.server.assignement.handler.impl.DailyJobAssignmentHandler;
import fr.paladium.pet.server.assignement.handler.impl.DailyPalapassAssignmentHandler;
import fr.paladium.pet.server.assignement.handler.impl.DarkAssignmentHandler;
import fr.paladium.pet.server.assignement.handler.impl.ItemAssignmentHandler;
import fr.paladium.pet.server.assignement.handler.impl.LightAssignmentHandler;
import fr.paladium.pet.server.assignement.handler.impl.SleepAssignmentHandler;
import fr.paladium.pet.server.assignement.handler.impl.WalkAssignmentHandler;
import fr.paladium.pet.server.assignement.handler.impl.WaterAssignmentHandler;
import fr.paladium.pet.server.assignement.listener.AssignmentDailyJobListener;
import fr.paladium.pet.server.assignement.listener.AssignmentDailyPalapassListener;
import fr.paladium.pet.server.assignement.listener.AssignmentMoveListener;
import fr.paladium.pet.server.assignement.listener.AssignmentTickListener;
import fr.paladium.pet.server.assignement.task.AssignmentResetTask;
import fr.paladium.pet.server.commands.PetSubCommand;
import fr.paladium.pet.server.config.assignment.AssignmentConfig;
import fr.paladium.pet.server.config.global.GlobalConfig;
import fr.paladium.pet.server.config.skill.SkillConfig;
import fr.paladium.pet.server.listener.CaptureListener;
import fr.paladium.pet.server.listener.ConnectionListener;
import fr.paladium.pet.server.listener.HappinessListener;
import fr.paladium.pet.server.listener.JobListener;
import fr.paladium.pet.server.listener.JoinListener;
import fr.paladium.pet.server.listener.NotificationListener;
import fr.paladium.pet.server.listener.SkillSyncListener;
import fr.paladium.pet.server.listener.TickListener;
import fr.paladium.pet.server.skill.SkillManager;
import fr.paladium.pet.server.skill.handler.impl.active.BedrockDrillSkill;
import fr.paladium.pet.server.skill.handler.impl.active.BlessedExplosionSkill;
import fr.paladium.pet.server.skill.handler.impl.active.EnchantedSkill;
import fr.paladium.pet.server.skill.handler.impl.active.FastChangeSkill;
import fr.paladium.pet.server.skill.handler.impl.active.GravitationalAxeSkill;
import fr.paladium.pet.server.skill.handler.impl.active.MonsterSlayerSkill;
import fr.paladium.pet.server.skill.handler.impl.active.PocketHappinessSkill;
import fr.paladium.pet.server.skill.handler.impl.active.RepairSkill;
import fr.paladium.pet.server.skill.handler.impl.active.UnbreakablePickaxeSkill;
import fr.paladium.pet.server.skill.handler.impl.active.VeterinarySkill;
import fr.paladium.pet.server.skill.handler.impl.active.XRaySkill;
import fr.paladium.pet.server.skill.listener.active.BreakSpeedListener;
import fr.paladium.pet.server.skill.listener.active.DestroyItemListener;
import fr.paladium.pet.server.skill.listener.active.EnchantListener;
import fr.paladium.pet.server.skill.listener.passive.ExperienceListener;
import fr.paladium.pet.server.skill.listener.passive.FeedListener;
import fr.paladium.pet.server.skill.listener.passive.LightWeightListener;
import fr.paladium.pet.server.skill.listener.passive.MonsterDamageListener;
import fr.paladium.pet.server.skill.listener.passive.PotionListener;
import fr.paladium.pet.server.skill.listener.passive.TickPlayerListener;
import fr.paladium.pet.server.skill.listener.tickable.TickableListener;
import java.util.HashSet;

public class PetServerProxy extends PetCommonProxy {
  private static PetServerProxy instance;
  
  private final RegistryManager registryManager;
  
  private AssignmentConfig assignmentConfig;
  
  private SkillConfig skillConfig;
  
  private GlobalConfig globalConfig;
  
  private AssignmentResetTask task;
  
  private SessionPlayerMap<HashSet<String>> playerSkinsMap;
  
  public static PetServerProxy getInstance() {
    return instance;
  }
  
  public RegistryManager getRegistryManager() {
    return this.registryManager;
  }
  
  public AssignmentConfig getAssignmentConfig() {
    return this.assignmentConfig;
  }
  
  public SkillConfig getSkillConfig() {
    return this.skillConfig;
  }
  
  public GlobalConfig getGlobalConfig() {
    return this.globalConfig;
  }
  
  public AssignmentResetTask getTask() {
    return this.task;
  }
  
  public PetServerProxy() {
    instance = this;
    this.registryManager = new RegistryManager();
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    registerListeners();
    registerAssignmentHandlers();
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    SubCommandBuilder.create("pet", "Gérer son familier")
      .string()
      .executable()
      .register(PetSubCommand.class);
    registerConfigs();
    registerSkillHandlers();
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    SkillManager.getInstance().updateSkills();
  }
  
  public void onServerStarting(FMLServerStartingEvent event) {
    super.onServerStarting(event);
    this.registryManager.getRegistries().forEach(registry -> registry.onServerStarting(event));
  }
  
  public void onServerStarted(FMLServerStartedEvent event) {
    super.onServerStarted(event);
    this.registryManager.getRegistries().forEach(registry -> registry.onServerStarted(event));
    this.task = new AssignmentResetTask();
    this.task.start();
  }
  
  private void registerAssignmentHandlers() {
    AssignmentManager manager = AssignmentManager.getInstance();
    manager.registerHandler(ConnectionAssignmentHandler.class);
    manager.registerHandler(DailyJobAssignmentHandler.class);
    manager.registerHandler(DailyPalapassAssignmentHandler.class);
    manager.registerHandler(DarkAssignmentHandler.class);
    manager.registerHandler(ItemAssignmentHandler.class);
    manager.registerHandler(LightAssignmentHandler.class);
    manager.registerHandler(SleepAssignmentHandler.class);
    manager.registerHandler(WalkAssignmentHandler.class);
    manager.registerHandler(WaterAssignmentHandler.class);
  }
  
  private void registerSkillHandlers() {
    SkillManager manager = SkillManager.getInstance();
    manager.registerHandler(MonsterSlayerSkill.class);
    manager.registerHandler(PocketHappinessSkill.class);
    manager.registerHandler(BedrockDrillSkill.class);
    manager.registerHandler(UnbreakablePickaxeSkill.class);
    manager.registerHandler(GravitationalAxeSkill.class);
    manager.registerHandler(XRaySkill.class);
    manager.registerHandler(FastChangeSkill.class);
    manager.registerHandler(EnchantedSkill.class);
    manager.registerHandler(RepairSkill.class);
    manager.registerHandler(VeterinarySkill.class);
    manager.registerHandler(BlessedExplosionSkill.class);
  }
  
  private void registerConfigs() {
    ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    this.assignmentConfig = (AssignmentConfig)configurationManager.register(AssignmentConfig.class);
    this.skillConfig = (SkillConfig)configurationManager.register(SkillConfig.class);
    this.globalConfig = (GlobalConfig)configurationManager.register(GlobalConfig.class);
  }
  
  private void registerListeners() {
    addListener(new Class[] { ExperienceListener.class, BreakSpeedListener.class, LightWeightListener.class, FeedListener.class, PotionListener.class, TickPlayerListener.class, MonsterDamageListener.class, EnchantListener.class, DestroyItemListener.class });
    addListener(new Class[] { AssignmentDailyJobListener.class, AssignmentDailyPalapassListener.class, AssignmentMoveListener.class, AssignmentTickListener.class });
    addListener(new Class[] { CaptureListener.class, ConnectionListener.class, HappinessListener.class, JobListener.class, JoinListener.class, NotificationListener.class, TickListener.class, SkillSyncListener.class, TickableListener.class });
  }
  
  public SessionPlayerMap<HashSet<String>> getPlayerSkinsMap() {
    if (this.playerSkinsMap == null)
      this.playerSkinsMap = new SessionPlayerMap<HashSet<String>>() {
          public HashSet<String> getDefaultValue() {
            return new HashSet<>();
          }
        }; 
    return this.playerSkinsMap;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\PetServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */