package fr.paladium.palamod.modules.luckyblock.luckyevents.christmas;

import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements Runnable {
  public void run() {
    try {
      Thread.sleep(60000L);
      if (UsersManager.getConsumerCredit().contains(player))
        UsersManager.getConsumerCredit().remove(player); 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\christmas\ConsumerCredit$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */