package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements Runnable {
  public void run() {
    if (WetFirecracker.access$000(WetFirecracker.this) > 30 || player == null) {
      PaladiumScheduler.INSTANCE.cancelTask(task.id);
      return;
    } 
    WetFirecracker.access$002(WetFirecracker.this, WetFirecracker.access$000(WetFirecracker.this) + 1);
    double xPos = player.field_70165_t + (player.field_70170_p.field_73012_v.nextFloat() * 3.0F - 1.0F);
    double yPos = player.field_70163_u + (player.field_70170_p.field_73012_v.nextFloat() * 3.0F - 1.0F);
    double zPos = player.field_70161_v + (player.field_70170_p.field_73012_v.nextFloat() * 3.0F - 1.0F);
    EventUtils.spawnParticle(player.field_70170_p, "explode", xPos, yPos, zPos, 10, 0.1D);
    EventUtils.playSound(player, "random.explode", xPos, yPos, zPos, 1.0F, 1.0F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\WetFirecracker$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */