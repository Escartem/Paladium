package fr.paladium.palamod.modules.nemesis.module.impl.autoclick;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.nemesis.module.base.NemesisModule;
import fr.paladium.palamod.modules.nemesis.module.impl.autoclick.client.listener.NemesisAutoclickClientListener;
import fr.paladium.palamod.modules.nemesis.module.impl.autoclick.common.packet.CSPacketNemesisAutoclickData;
import fr.paladium.palamod.modules.nemesis.module.impl.autoclick.common.packet.SCPacketNemesisAutoclickToggle;
import net.minecraftforge.common.MinecraftForge;

public class NemesisAutoclickModule extends NemesisModule {
  private static NemesisAutoclickModule instance;
  
  public NemesisAutoclickModule() {
    super("autoclick");
    instance = this;
  }
  
  public void preInit(FMLPreInitializationEvent event) {
    getNetwork().registerPacket(CSPacketNemesisAutoclickData.class);
    getNetwork().registerPacket(SCPacketNemesisAutoclickToggle.class);
    if (event.getSide() == Side.CLIENT)
      MinecraftForge.EVENT_BUS.register(new NemesisAutoclickClientListener()); 
  }
  
  public static NemesisAutoclickModule getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\impl\autoclick\NemesisAutoclickModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */