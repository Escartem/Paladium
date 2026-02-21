package fr.paladium.palamod.modules.paladium.client.manager;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.palamod.modules.paladium.client.listener.AncientHammerEffectListener;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.manager.AncientHammerEffectCamManager;
import net.minecraftforge.common.MinecraftForge;

public class AncientHammerEffectClientCamManager {
  private static AncientHammerEffectClientCamManager instance;
  
  private AncientHammerEffectCamManager.AncientHammerEffectCam cam;
  
  public void setCam(AncientHammerEffectCamManager.AncientHammerEffectCam cam) {
    this.cam = cam;
  }
  
  public AncientHammerEffectCamManager.AncientHammerEffectCam getCam() {
    return this.cam;
  }
  
  public static AncientHammerEffectClientCamManager inst() {
    if (instance == null)
      instance = new AncientHammerEffectClientCamManager(); 
    return instance;
  }
  
  public void init() {
    AncientHammerEffectListener listener = new AncientHammerEffectListener();
    MinecraftForge.EVENT_BUS.register(listener);
    FMLCommonHandler.instance().bus().register(listener);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\manager\AncientHammerEffectClientCamManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */