package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements Runnable {
  public void run() {
    if (entity == null || entity.field_70128_L)
      return; 
    if (player == null || entity == null || System.currentTimeMillis() - time >= 30000L) {
      if (entity != null)
        entity.func_70106_y(); 
      PaladiumScheduler.INSTANCE.cancelTask(taskId.get());
    } else {
      entity.func_70606_j(entity.func_110138_aP());
      entity.func_94058_c("§cJe t'aurais ! §e" + (30L - (
          System.currentTimeMillis() - time) / 1000L) + "");
      player.field_71135_a.func_147364_a(x, y, z, 5.0F, -45.0F);
      entity.field_70714_bg.field_75782_a.clear();
      entity.field_70715_bh.field_75782_a.clear();
      entity.func_70624_b(null);
      entity.func_70080_a(player.field_70165_t, player.field_70163_u, player.field_70161_v + 1.0D, -190.0F, -70.0F);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\EyeBattle$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */