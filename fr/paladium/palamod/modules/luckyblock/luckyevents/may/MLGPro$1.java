package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import fr.paladium.palamod.scheduler.PaladiumScheduler;

class null implements Runnable {
  public void run() {
    try {
      Thread.sleep(30000L);
      if (UsersManager.getAlmostCancelFallDamagePlayer().contains(playerId))
        UsersManager.getAlmostCancelFallDamagePlayer().remove(playerId); 
    } catch (InterruptedException interruptedException) {}
    PaladiumScheduler.INSTANCE.cancelTask(task.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\MLGPro$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */