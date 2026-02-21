package fr.paladium.palajobs;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lindworm.Lindworm;
import fr.paladium.palajobs.client.proxy.ClientProxy;
import fr.paladium.palajobs.core.CommonProxy;
import fr.paladium.palajobs.core.entity.boss.EntityJobAlchimistBoss;
import fr.paladium.palajobs.core.entity.boss.EntityJobFarmerBoss;
import fr.paladium.palajobs.core.entity.boss.EntityJobFarmerPlantBoss;
import fr.paladium.palajobs.core.entity.boss.EntityJobHunterBoss;
import fr.paladium.palajobs.core.entity.boss.EntityJobMinerBoss;
import fr.paladium.palajobs.core.entity.boss.EntityJobMinerDigBoss;
import fr.paladium.palajobs.server.command.CommandJobs;
import fr.paladium.palajobs.server.proxy.ServerProxy;
import net.minecraft.command.ICommand;

@Mod(modid = "palajobs", version = "0.1", acceptableRemoteVersions = "*", dependencies = "required-after:apollon;required-after:chronos")
public class PalaJobs {
  @Instance("palajobs")
  public static PalaJobs instance;
  
  public static final String MODID = "palajobs";
  
  public static final String VERSION = "0.1";
  
  @SidedProxy(clientSide = "fr.paladium.palajobs.client.proxy.ClientProxy", serverSide = "fr.paladium.palajobs.server.proxy.ServerProxy")
  public static CommonProxy proxy;
  
  public static Side side;
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent e) {
    if (proxy != null)
      proxy.onPreInit(e); 
    instance = this;
    side = e.getSide();
    int entityID = EntityRegistry.findGlobalUniqueEntityId();
    EntityRegistry.registerGlobalEntityID(EntityJobMinerBoss.class, "entityJobMinerBoss", entityID, (new Color(0, 0, 0))
        
        .getRGB(), (new Color(0, 0, 0))
        .getRGB());
    EntityRegistry.registerModEntity(EntityJobMinerBoss.class, "entityJobMinerBoss", entityID, Lindworm.getInstance(), 100, 1, true);
    entityID = EntityRegistry.findGlobalUniqueEntityId();
    EntityRegistry.registerGlobalEntityID(EntityJobMinerDigBoss.class, "entityJobMinerDigBoss", entityID, (new Color(255, 255, 255))
        
        .getRGB(), (new Color(255, 255, 255))
        .getRGB());
    EntityRegistry.registerModEntity(EntityJobMinerDigBoss.class, "entityJobMinerDigBoss", entityID, Lindworm.getInstance(), 100, 1, true);
    entityID = EntityRegistry.findGlobalUniqueEntityId();
    EntityRegistry.registerGlobalEntityID(EntityJobHunterBoss.class, "entityJobHunterBoss", entityID, (new Color(0, 0, 0))
        
        .getRGB(), (new Color(0, 0, 0))
        .getRGB());
    EntityRegistry.registerModEntity(EntityJobHunterBoss.class, "entityJobHunterBoss", entityID, Lindworm.getInstance(), 100, 1, true);
    entityID = EntityRegistry.findGlobalUniqueEntityId();
    EntityRegistry.registerGlobalEntityID(EntityJobFarmerBoss.class, "entityJobFarmerBoss", entityID, (new Color(0, 0, 0))
        
        .getRGB(), (new Color(0, 0, 0))
        .getRGB());
    EntityRegistry.registerModEntity(EntityJobFarmerBoss.class, "entityJobFarmerBoss", entityID, Lindworm.getInstance(), 100, 1, true);
    entityID = EntityRegistry.findGlobalUniqueEntityId();
    EntityRegistry.registerGlobalEntityID(EntityJobFarmerPlantBoss.class, "entityJobFarmerPlantBoss", entityID, (new Color(255, 255, 255))
        
        .getRGB(), (new Color(255, 255, 255))
        .getRGB());
    EntityRegistry.registerModEntity(EntityJobFarmerPlantBoss.class, "entityJobFarmerPlantBoss", entityID, Lindworm.getInstance(), 100, 1, true);
    entityID = EntityRegistry.findGlobalUniqueEntityId();
    EntityRegistry.registerGlobalEntityID(EntityJobAlchimistBoss.class, "entityJobAlchimistBoss", entityID, (new Color(0, 0, 0))
        
        .getRGB(), (new Color(0, 0, 0))
        .getRGB());
    EntityRegistry.registerModEntity(EntityJobAlchimistBoss.class, "entityJobAlchimistBoss", entityID, Lindworm.getInstance(), 100, 1, true);
  }
  
  @EventHandler
  public void init(FMLInitializationEvent e) {
    proxy.onInit(e);
  }
  
  @EventHandler
  public void postInit(FMLPostInitializationEvent e) {
    proxy.onPostInit(e);
  }
  
  @EventHandler
  public void serverStarting(FMLServerStartingEvent event) {
    event.registerServerCommand((ICommand)new CommandJobs());
  }
  
  public static PalaJobs getInstance() {
    if (instance == null)
      instance = new PalaJobs(); 
    return instance;
  }
  
  public static void setInstance(PalaJobs instance) {
    PalaJobs.instance = instance;
  }
  
  public static ServerProxy getServer() {
    return (ServerProxy)proxy;
  }
  
  public static ClientProxy getClient() {
    return (ClientProxy)proxy;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\PalaJobs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */