package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import net.minecraft.entity.player.EntityPlayerMP;

public class WetFirecracker extends ALuckyEvent {
  private int count;
  
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    this.count = 1;
    final LuckyTask task = new LuckyTask();
    task
      
      .id = PaladiumScheduler.INSTANCE.runTaskTimer(new Runnable() {
          public void run() {
            if (WetFirecracker.this.count > 30 || player == null) {
              PaladiumScheduler.INSTANCE.cancelTask(task.id);
              return;
            } 
            WetFirecracker.this.count = WetFirecracker.this.count + 1;
            double xPos = player.field_70165_t + (player.field_70170_p.field_73012_v.nextFloat() * 3.0F - 1.0F);
            double yPos = player.field_70163_u + (player.field_70170_p.field_73012_v.nextFloat() * 3.0F - 1.0F);
            double zPos = player.field_70161_v + (player.field_70170_p.field_73012_v.nextFloat() * 3.0F - 1.0F);
            EventUtils.spawnParticle(player.field_70170_p, "explode", xPos, yPos, zPos, 10, 0.1D);
            EventUtils.playSound(player, "random.explode", xPos, yPos, zPos, 1.0F, 1.0F);
          }
        }0L, 20L).getTaskId();
  }
  
  public String getName() {
    return "Pétard mouillé";
  }
  
  public String getTexture() {
    return "wet_firecracker";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 150;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\WetFirecracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */