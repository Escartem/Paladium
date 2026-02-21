package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;

class null implements Runnable {
  public void run() {
    try {
      Thread.sleep(5000L);
      EventUtils.spawnStructure(player.field_70170_p, x, y + 4, z, 7, 7, 7, Blocks.field_150350_a, player);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Princess$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */