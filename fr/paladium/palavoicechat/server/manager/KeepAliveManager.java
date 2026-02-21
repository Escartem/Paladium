package fr.paladium.palavoicechat.server.manager;

import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palavoicechat.common.network.SCPacketReconnectAfterDown;
import fr.paladium.palavoicechat.common.voip.packet.Packet;
import fr.paladium.palavoicechat.common.voip.packet.impl.KeepAlivePacket;
import fr.paladium.palavoicechat.server.voip.IoNettyVoIPServer;

public class KeepAliveManager {
  private static final long CONSIDERED_DISCONNECTED = 10000L;
  
  private static KeepAliveManager INSTANCE;
  
  public void setLastKeepAliveResponse(long lastKeepAliveResponse) {
    this.lastKeepAliveResponse = lastKeepAliveResponse;
  }
  
  private long lastKeepAliveResponse = 0L;
  
  public static KeepAliveManager getInstance() {
    if (INSTANCE == null)
      INSTANCE = new KeepAliveManager(); 
    return INSTANCE;
  }
  
  public boolean isAlive() {
    return (System.currentTimeMillis() - this.lastKeepAliveResponse < 10000L);
  }
  
  public void sendKeepAlive() {
    IoNettyVoIPServer.getInstance().sendPacket((Packet)new KeepAlivePacket(Server.getHostName()));
  }
  
  public void onKeepAliveResponse() {
    if (!isAlive())
      (new SCPacketReconnectAfterDown()).sendToAll(); 
    this.lastKeepAliveResponse = System.currentTimeMillis();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\server\manager\KeepAliveManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */