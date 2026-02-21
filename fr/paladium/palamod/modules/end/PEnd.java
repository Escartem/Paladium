package fr.paladium.palamod.modules.end;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palamod.modules.end.client.proxy.ClientProxy;
import fr.paladium.palamod.modules.end.common.proxy.CommonProxy;
import fr.paladium.palamod.modules.end.server.command.CommandEnd;
import fr.paladium.palamod.modules.end.server.proxy.ServerProxy;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import fr.paladium.worldguardbridge.common.dto.flag.impl.StateFlag;
import fr.paladium.worldguardbridge.common.dto.flag.utils.FlagDefinition;
import net.minecraft.command.ICommand;

@Pulse(id = "palamod-end", description = "End event", forced = true)
@DoNotRename
public class PEnd {
  public static final String ID = "palamod-end";
  
  public static final FlagDefinition<StateFlag> EVENT_END_FLAG = new FlagDefinition("event-end", StateFlag.class);
  
  @Instance("palamod-end")
  private static PEnd instance;
  
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.end.client.proxy.ClientProxy", serverSide = "fr.paladium.palamod.modules.end.server.proxy.ServerProxy")
  private static CommonProxy proxy;
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    instance = this;
    proxy.onPreInit(event);
  }
  
  @Handler
  public void init(FMLInitializationEvent event) {
    proxy.onInit(event);
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent event) {
    proxy.onPostInit(event);
  }
  
  @Handler
  public void starting(FMLServerStartingEvent event) {
    event.registerServerCommand((ICommand)new CommandEnd());
  }
  
  @Handler
  public void started(FMLServerStartedEvent event) {
    proxy.onServerStarted(event);
  }
  
  public static PEnd getInstance() {
    return instance;
  }
  
  public static CommonProxy getProxy() {
    return proxy;
  }
  
  public static ServerProxy getServer() {
    return (ServerProxy)proxy;
  }
  
  public static ClientProxy getClient() {
    return (ClientProxy)proxy;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\PEnd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */