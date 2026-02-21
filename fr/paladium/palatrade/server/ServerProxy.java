package fr.paladium.palatrade.server;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palatrade.common.CommonProxy;
import fr.paladium.palatrade.lib.odin.modules.command.OdinCommandModule;
import fr.paladium.palatrade.lib.odin.modules.command.lib.SenderType;
import fr.paladium.palatrade.server.command.TradeCommand;
import fr.paladium.palatrade.server.listener.ServerEventHandler;

public class ServerProxy extends CommonProxy {
  public void loadModules() {
    super.loadModules();
    OdinCommandModule.getInstance().getCommandBuilder()
      .name("trade")
      .permission("paladium.cmd.trade")
      .only(new SenderType[] { SenderType.PLAYER }).build(TradeCommand.class);
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { ServerEventHandler.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */