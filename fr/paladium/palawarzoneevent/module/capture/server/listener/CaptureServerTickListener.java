package fr.paladium.palawarzoneevent.module.capture.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.palawarzoneevent.module.capture.common.manager.CaptureEventManager;
import net.minecraft.server.MinecraftServer;

public class CaptureServerTickListener {
  @SubscribeEvent
  public void onServerTick(TickEvent.ServerTickEvent event) {
    if (event.phase == TickEvent.Phase.END || !Server.is(new ServerType[] { ServerType.WARZONE }))
      return; 
    CaptureEventManager.inst().syncPlayerAround();
    if (!ForgeEnv.isIDE() && MinecraftServer.func_71276_C().func_71259_af() % 20 != 0)
      return; 
    CaptureEventManager.inst().updateCapture();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\capture\server\listener\CaptureServerTickListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */