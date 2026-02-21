package fr.paladium.palamod.modules.luckyblock.luckyevents.easter;

import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.entity.easter.EntityEasterEgg;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class PluieOeufs extends ALuckyEvent {
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    final LuckyTask task = new LuckyTask();
    task
      
      .id = PaladiumScheduler.INSTANCE.runTaskAsyncLater(new Runnable() {
          public void run() {
            try {
              for (int i = 0; i < 50; i++) {
                BlockPos pos = EventUtils.getRandomCoordsWithinRadius((int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 10);
                player.field_70170_p.func_72838_d((Entity)new EntityEasterEgg(player.field_70170_p, pos.getX(), (pos.getY() + 3), pos.getZ()));
                Thread.sleep(1000L);
              } 
            } catch (Exception e) {
              e.printStackTrace();
            } 
            PaladiumScheduler.INSTANCE.cancelTask(task.id);
          }
        }0L).getTaskId();
  }
  
  public String getName() {
    return "Pluie d’œufs";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 70;
  }
  
  public String getTexture() {
    return "easter/pluie_oeufs";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\PluieOeufs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */