package fr.paladium.palamod.modules.luckyblock.luckyevents.easter;

import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.hunter.entites.EntityCrab;
import fr.paladium.palamod.modules.hunter.entites.EntityDolphin;
import fr.paladium.palamod.modules.hunter.entites.EntityJellyFish;
import fr.paladium.palamod.modules.hunter.entites.EntityTurtle;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayerMP;

public class PoissonsAvril extends ALuckyEvent {
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    final List<Integer> animalList = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      animalList.add(Integer.valueOf(1));
      animalList.add(Integer.valueOf(2));
      animalList.add(Integer.valueOf(3));
      animalList.add(Integer.valueOf(4));
      animalList.add(Integer.valueOf(5));
    } 
    Collections.shuffle(animalList);
    final LuckyTask task = new LuckyTask();
    task
      
      .id = PaladiumScheduler.INSTANCE.runTaskAsyncLater(new Runnable() {
          public void run() {
            try {
              for (Integer animalId : animalList) {
                EntitySquid entitySquid;
                EntityCrab entityCrab;
                EntityDolphin entityDolphin;
                EntityJellyFish entityJellyFish;
                EntityTurtle entityTurtle;
                BlockPos pos = EventUtils.getRandomCoordsWithinRadius((int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 10);
                EntityLiving entity = null;
                switch (animalId.intValue()) {
                  case 1:
                    entitySquid = new EntitySquid(player.field_70170_p);
                    break;
                  case 2:
                    entityCrab = new EntityCrab(player.field_70170_p);
                    break;
                  case 3:
                    entityDolphin = new EntityDolphin(player.field_70170_p);
                    break;
                  case 4:
                    entityJellyFish = new EntityJellyFish(player.field_70170_p);
                    break;
                  case 5:
                    entityTurtle = new EntityTurtle(player.field_70170_p);
                    break;
                } 
                if (entityTurtle != null) {
                  entityTurtle.func_94058_c("§b");
                  entityTurtle.func_70634_a(pos.getX(), 100.0D, pos.getZ());
                  player.field_70170_p.func_72838_d((Entity)entityTurtle);
                } 
                Thread.sleep(500L);
              } 
            } catch (Exception e) {
              e.printStackTrace();
            } 
            PaladiumScheduler.INSTANCE.cancelTask(task.id);
          }
        }0L).getTaskId();
  }
  
  public String getName() {
    return "Poissons d’avril";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 120;
  }
  
  public String getTexture() {
    return "easter/poisson_avril";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\PoissonsAvril.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */