package fr.paladium.palamod.modules.factions;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palamod.modules.factions.impl.FactionImpl;
import fr.paladium.palamod.modules.factions.impl.IFactionImpl;
import fr.paladium.palamod.modules.factions.listeners.PlayerJobLevelUpListener;
import fr.paladium.palamod.modules.factions.rabbit.RabbitCredentials;
import fr.paladium.palamod.modules.factions.rabbit.RabbitService;
import fr.paladium.palamod.modules.factions.utils.JsonUtils;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import java.io.File;
import net.minecraftforge.common.MinecraftForge;

@Pulse(id = "palamod-factions", description = "Faction linker", forced = true)
public class PFactions {
  public static final String ID = "palamod-factions";
  
  @Instance("palamod-factions")
  public static PFactions instance;
  
  private IFactionImpl impl;
  
  public IFactionImpl getImpl() {
    return this.impl;
  }
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    if (event.getSide().isServer()) {
      instance = this;
      loadRabbit(event.getModConfigurationDirectory());
    } 
    registerEvents();
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent event) {
    Crafts.register();
  }
  
  private void registerEvents() {
    FMLCommonHandler.instance().bus().register(new PlayerJobLevelUpListener());
    MinecraftForge.EVENT_BUS.register(new PlayerJobLevelUpListener());
  }
  
  private void loadRabbit(File dir) {
    if (ForgeEnv.isDev()) {
      System.out.println("[PFactions] RabbitMQ is disabled in devmode.");
      return;
    } 
    try {
      RabbitCredentials credentials = (RabbitCredentials)JsonUtils.getFileObject(dir.getAbsolutePath(), "factions-rabbit.json", RabbitCredentials.class);
      RabbitService service = new RabbitService(credentials);
      this.impl = (IFactionImpl)new FactionImpl(service);
    } catch (Exception error) {
      System.out.println("Unable to load factions impl.");
      error.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\factions\PFactions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */