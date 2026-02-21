package fr.paladium.palacommunityevent.server;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palacommunityevent.common.CommonProxy;
import fr.paladium.palacommunityevent.server.api.ICommunityEventAPI;
import fr.paladium.palacommunityevent.server.commands.PalaCommunityEventCommand;
import fr.paladium.palaforgeutils.lib.api.gateway.GatewayUtils;
import fr.paladium.palaforgeutils.lib.command.CommandBuilder;
import java.io.File;

public class ServerProxy extends CommonProxy {
  private static ServerProxy instance;
  
  private File configDirectory;
  
  private ICommunityEventAPI api;
  
  public File getConfigDirectory() {
    return this.configDirectory;
  }
  
  public ICommunityEventAPI getApi() {
    return this.api;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    instance = this;
    CommandBuilder.create()
      .name("pce")
      .build(PalaCommunityEventCommand.class);
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    this.api = (ICommunityEventAPI)GatewayUtils.registerAPI(ICommunityEventAPI.class);
  }
  
  public static ServerProxy getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */