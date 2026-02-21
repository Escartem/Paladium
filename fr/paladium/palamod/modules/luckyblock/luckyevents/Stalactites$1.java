package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements Runnable {
  public void run() {
    if (Stalactites.access$000(Stalactites.this) > 20) {
      player.func_82170_o(PLuckyBlock.STALACTITES.field_76415_H);
      PaladiumScheduler.INSTANCE.cancelTask(task.id);
      return;
    } 
    Stalactites.this.tryUpdateBlock(player, (int)player.field_70165_t, (int)player.field_70163_u + 4, (int)player.field_70161_v);
    Stalactites.this.tryUpdateBlock(player, (int)player.field_70165_t, (int)player.field_70163_u + 5, (int)player.field_70161_v);
    Stalactites.access$002(Stalactites.this, Stalactites.access$000(Stalactites.this) + 1);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Stalactites$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */