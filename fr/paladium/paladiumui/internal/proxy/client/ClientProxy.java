package fr.paladium.paladiumui.internal.proxy.client;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.paladiumui.internal.client.gui.UIPaladiumKit;
import fr.paladium.paladiumui.internal.proxy.common.CommonProxy;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.demo.client.gui.UIDemoChoice;
import fr.paladium.zephyrui.internal.mod.ZephyrUI;

public class ClientProxy extends CommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    PaladiumFont.load();
    if (ZephyrUI.isDemoMode())
      UIDemoChoice.LIST.add(UIPaladiumKit.class); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\internal\proxy\client\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */