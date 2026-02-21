package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements Runnable {
  public void run() {
    if (StormLighting.access$000(StormLighting.this) > 10 || player == null) {
      PaladiumScheduler.INSTANCE.cancelTask(task.id);
      return;
    } 
    StormLighting.access$100(StormLighting.this, player);
    StormLighting.access$200(StormLighting.this, player);
    StormLighting.access$002(StormLighting.this, StormLighting.access$000(StormLighting.this) + 1);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\StormLighting$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */