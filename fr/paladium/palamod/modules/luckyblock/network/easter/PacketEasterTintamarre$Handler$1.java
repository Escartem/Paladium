package fr.paladium.palamod.modules.luckyblock.network.easter;

import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;

class null implements Runnable {
  public void run() {
    try {
      long startTime = System.currentTimeMillis();
      while (System.currentTimeMillis() - startTime < 120000L) {
        ClientProxy.playSound("bell");
        Thread.sleep(5000L);
      } 
    } catch (InterruptedException interruptedException) {}
    ClientProxy.isBellRinging = false;
    PaladiumScheduler.INSTANCE.cancelTask(task.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\easter\PacketEasterTintamarre$Handler$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */