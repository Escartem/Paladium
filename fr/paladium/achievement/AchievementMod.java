package fr.paladium.achievement;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.achievement.client.proxy.ClientProxy;
import fr.paladium.achievement.core.profile.ModuleAchievement;
import fr.paladium.achievement.core.proxy.CommonProxy;
import fr.paladium.achievement.server.AchievementServerInit;
import fr.paladium.achievement.server.commands.CommandAchievement;
import fr.paladium.achievement.server.events.EventsManager;
import fr.paladium.achievement.server.proxy.ServerProxy;
import fr.paladium.profile.common.module.ProfileModules;
import net.minecraft.command.ICommand;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "achievement", version = "0.1", acceptableRemoteVersions = "*", dependencies = "required-after:apollon;required-after:palaforge-utils;after:profile")
public class AchievementMod {
  public static final String MODID = "achievement";
  
  public static final String VERSION = "0.1";
  
  @Instance("achievement")
  public static AchievementMod instance;
  
  @SidedProxy(clientSide = "fr.paladium.achievement.client.proxy.ClientProxy", serverSide = "fr.paladium.achievement.server.proxy.ServerProxy")
  public static CommonProxy proxy;
  
  public static CommonProxy getProxy() {
    return proxy;
  }
  
  public static AchievementMod getInstance() {
    if (instance == null)
      instance = new AchievementMod(); 
    return instance;
  }
  
  public static void setInstance(AchievementMod instance) {
    AchievementMod.instance = instance;
  }
  
  public static ServerProxy getServer() {
    return (ServerProxy)proxy;
  }
  
  public static ClientProxy getClient() {
    return (ClientProxy)proxy;
  }
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent e) {
    instance = this;
    proxy.onPreInit(e);
    try {
      ProfileModules.getInstance().registerModule(ModuleAchievement.class);
    } catch (NoClassDefFoundError err) {
      AchievementLogger.warn("ProfileModules can't be loaded.");
    } 
  }
  
  @EventHandler
  public void init(FMLInitializationEvent e) {
    proxy.onInit(e);
  }
  
  @EventHandler
  public void onPostInit(FMLPostInitializationEvent e) {
    proxy.onPostInit(e);
  }
  
  @EventHandler
  public void serverStarting(FMLServerStartingEvent event) {
    FMLCommonHandler.instance().bus().register(new EventsManager());
    MinecraftForge.EVENT_BUS.register(new EventsManager());
    new AchievementServerInit(event);
    event.registerServerCommand((ICommand)new CommandAchievement());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\AchievementMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */