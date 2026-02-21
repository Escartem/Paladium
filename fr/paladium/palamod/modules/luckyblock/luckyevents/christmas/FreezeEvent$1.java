package fr.paladium.palamod.modules.luckyblock.luckyevents.christmas;

import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements Runnable {
  public void run() {
    try {
      Thread.sleep(30000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } 
    ServerManager.removeFreeze(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\christmas\FreezeEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */