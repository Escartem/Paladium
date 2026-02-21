package fr.paladium.palavoicechat.client.voip.client;

import fr.paladium.palavoicechat.common.voip.packet.Packet;
import fr.paladium.palavoicechat.common.voip.packet.impl.AuthenticatePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;

public class AuthThread extends Thread {
  public AuthThread() {
    setDaemon(true);
    setName("VoiceChatAuthenticationThread");
  }
  
  public void run() {
    while (!IoNettyVoIPClient.getInstance().isAuthenticated() && IoNettyVoIPClient.getInstance().isAuthenticating()) {
      EntityClientPlayerMP player = (Minecraft.func_71410_x()).field_71439_g;
      if (player == null || IoNettyVoIPClient.getInstance().getSecret() == null)
        continue; 
      IoNettyVoIPClient.getInstance().sendPacket((Packet<?>)new AuthenticatePacket(player.func_110124_au(), IoNettyVoIPClient.getInstance().getSecret()));
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException e) {}
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\client\voip\client\AuthThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */