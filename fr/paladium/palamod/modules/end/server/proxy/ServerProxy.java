package fr.paladium.palamod.modules.end.server.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import fr.paladium.palamod.modules.end.common.proxy.CommonProxy;
import fr.paladium.palamod.modules.end.server.config.EndConfig;
import fr.paladium.palamod.modules.end.server.config.MagicConfig;
import fr.paladium.palamod.modules.end.server.event.ServerEventHandler;
import fr.paladium.palamod.modules.end.server.manager.EndManager;
import fr.paladium.palamod.modules.end.server.utils.EndData;
import fr.paladium.palamod.modules.factions.utils.JsonUtils;
import java.io.File;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ServerProxy extends CommonProxy {
  private File configFile;
  
  private EndConfig config;
  
  private EndData data;
  
  private Map<UUID, MagicConfig> configurators;
  
  public File getConfigFile() {
    return this.configFile;
  }
  
  public EndConfig getConfig() {
    return this.config;
  }
  
  public EndData getData() {
    return this.data;
  }
  
  public Map<UUID, MagicConfig> getConfigurators() {
    return this.configurators;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    try {
      this.configFile = new File(event.getModConfigurationDirectory(), "end.json");
      this.config = (EndConfig)JsonUtils.getFileObject(event.getModConfigurationDirectory().getAbsolutePath(), "end.json", EndConfig.class);
    } catch (Exception e1) {
      System.err.println("[End] Unable to load " + event.getModConfigurationDirectory().getAbsolutePath() + File.separator + "end.json");
      e1.printStackTrace();
      this.config = new EndConfig();
      this.config.save(this.configFile);
    } 
    this.data = new EndData();
    this.configurators = new ConcurrentHashMap<>();
    ServerEventHandler eventHandler = new ServerEventHandler();
    FMLCommonHandler.instance().bus().register(eventHandler);
    MinecraftForge.EVENT_BUS.register(eventHandler);
  }
  
  public void onServerStarted(FMLServerStartedEvent event) {
    super.onServerStarted(event);
    if (this.config != null) {
      World world = MinecraftServer.func_71276_C().func_130014_f_();
      EndManager.getInstance().setBlocks(world, this.config.hole, Blocks.field_150377_bs);
      EndManager.getInstance().setBlocks(world, this.config.portal, Blocks.field_150350_a);
      EndManager.getInstance().setBlocks(world, this.config.endportal, Blocks.field_150350_a);
      EndManager.getInstance().setBlocks(world, this.config.egg, Blocks.field_150350_a);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\server\proxy\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */