package fr.paladium.palamod.modules.discord;

import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import fr.paladium.palamod.client.utils.OperatingSystem;
import fr.paladium.palamod.client.utils.Utils;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;

@ObjectHolder("palamod")
@Pulse(id = "palamod-discord", description = "Paladium Discord Rich Presence.", forced = true, clientOnly = true)
public class PDiscord {
  @Instance("palamod-discord")
  public static PDiscord instance;
  
  private static Rpc rpc;
  
  private static String rpc_id = "475720078088732672";
  
  private static String rpc_largeImageKey = "logo";
  
  private static String rpc_largeImageText = "www.paladium-pvp.fr";
  
  private static String rpc_smallImageKey = "logo2";
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    if (OperatingSystem.getCurrentPlatform() == OperatingSystem.WINDOWS && event.getSide().isClient())
      init(); 
  }
  
  public static void init() {
    rpc = new Rpc();
    rpc.init(rpc_id, rpc_largeImageKey, rpc_largeImageText, rpc_smallImageKey, Utils.getPlayerUsername());
  }
  
  public static Rpc getRPC() {
    return rpc;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\discord\PDiscord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */