package fr.paladium.palavoicechat.server.voip;

import fr.paladium.palavoicechat.server.manager.KeepAliveManager;

public class KeepAliveThread extends Thread {
  public void run() {
    while (true) {
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException interruptedException) {}
      KeepAliveManager.getInstance().sendKeepAlive();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\server\voip\IoNettyVoIPServer$KeepAliveThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */