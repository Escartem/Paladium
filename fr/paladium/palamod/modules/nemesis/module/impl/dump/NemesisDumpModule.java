package fr.paladium.palamod.modules.nemesis.module.impl.dump;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palamod.modules.nemesis.module.base.NemesisModule;
import fr.paladium.palamod.modules.nemesis.module.impl.dump.client.listener.NemesisDumpClientListener;
import fr.paladium.palamod.modules.nemesis.module.impl.dump.common.packet.BBPacketNemesisDumpStreamData;
import fr.paladium.palamod.modules.nemesis.module.impl.dump.common.packet.SCPacketNemesisDumpRequest;

public class NemesisDumpModule extends NemesisModule {
  private static NemesisDumpModule instance;
  
  public NemesisDumpModule() {
    super("dump");
    instance = this;
  }
  
  public void preInit(FMLPreInitializationEvent event) {
    addListener(NemesisDumpClientListener.class);
    getNetwork().registerPacket(SCPacketNemesisDumpRequest.class);
    getNetwork().registerPacket(BBPacketNemesisDumpStreamData.class);
  }
  
  public static NemesisDumpModule getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\impl\dump\NemesisDumpModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */