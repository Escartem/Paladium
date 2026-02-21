package fr.paladium.palamod.modules.nemesis.module.impl.autoclick.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.server.internal.event.ServerChangeEvent;
import fr.paladium.palamod.modules.nemesis.module.base.network.NemesisPacket;
import fr.paladium.palamod.modules.nemesis.module.impl.autoclick.NemesisAutoclickModule;
import fr.paladium.palamod.modules.nemesis.module.impl.autoclick.common.packet.CSPacketNemesisAutoclickData;
import net.minecraftforge.client.event.MouseEvent;

public class NemesisAutoclickClientListener {
  private static volatile boolean WATCHED = false;
  
  public static void setWatched(boolean w) {
    WATCHED = w;
  }
  
  @SubscribeEvent
  public void onServerChange(ServerChangeEvent.Pre event) {
    setWatched(false);
  }
  
  @SubscribeEvent
  public void onMouseEvent(MouseEvent event) {
    if (!WATCHED)
      return; 
    try {
      int button = event.button;
      boolean pressed = event.buttonstate;
      if (button >= 0 && pressed) {
        boolean left = (button == 0);
        long ts = System.currentTimeMillis();
        int clickType = 3;
        if (button == 0) {
          clickType = 0;
        } else if (button == 1) {
          clickType = 1;
        } else if (button == 2) {
          clickType = 2;
        } 
        NemesisAutoclickModule.getInstance().getNetwork().send((NemesisPacket)new CSPacketNemesisAutoclickData(left, ts, clickType));
      } 
    } catch (Throwable throwable) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\impl\autoclick\client\listener\NemesisAutoclickClientListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */