package fr.paladium.palavoicechat.server.voip;

import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palavoicechat.common.voip.packet.Packet;
import fr.paladium.palavoicechat.common.voip.packet.impl.UpdatePositionPacket;
import fr.paladium.palavoicechat.common.wgflag.VoiceChatFlagsUtils;
import fr.paladium.palavoicechat.server.ServerProxy;
import fr.paladium.palavoicechat.server.manager.KeepAliveManager;
import fr.paladium.palavoicechat.utils.PlayerLocation;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PlayerUpdateThread extends Thread {
  public void setRunning(boolean running) {
    this.running = running;
  }
  
  public boolean running = true;
  
  public boolean isRunning() {
    return this.running;
  }
  
  public void run() {
    while (this.running) {
      try {
        Thread.sleep(Math.max(50L, ServerProxy.serverConfig.getPlayerPositionRefreshRate()));
      } catch (InterruptedException interruptedException) {}
      if (!KeepAliveManager.getInstance().isAlive())
        continue; 
      List<PlayerLocation> locations = new ArrayList<>();
      PlayerSelector.ALL().apply(player -> {
            if (IoNettyVoIPServer.access$000(IoNettyVoIPServer.this).contains(player.func_110124_au())) {
              PlayerLocation loc = new PlayerLocation(player.func_110124_au(), player.func_70005_c_(), Server.getHostName(), player.field_70170_p.field_73011_w.field_76574_g, (float)player.field_70165_t, (float)player.field_70163_u, (float)player.field_70161_v, VoiceChatFlagsUtils.canUserSpeak((EntityPlayer)player));
              locations.add(loc);
            } 
          });
      if (locations.isEmpty())
        continue; 
      int maxPerPacket = Math.max(1, ServerProxy.serverConfig.getMaxLocationUpdatePerPacket());
      for (int i = 0; i < locations.size(); i += maxPerPacket) {
        List<PlayerLocation> batch = locations.subList(i, Math.min(i + maxPerPacket, locations.size()));
        IoNettyVoIPServer.this.sendPacket((Packet<?>)new UpdatePositionPacket(batch));
      } 
    } 
  }
  
  public void close() {
    this.running = false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\server\voip\IoNettyVoIPServer$PlayerUpdateThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */