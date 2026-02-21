package fr.paladium.palamod.modules.luckyblock.luckyevents.easter;

import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.hunter.entites.EntityCrab;
import fr.paladium.palamod.modules.hunter.entites.EntityDolphin;
import fr.paladium.palamod.modules.hunter.entites.EntityJellyFish;
import fr.paladium.palamod.modules.hunter.entites.EntityTurtle;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements Runnable {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\PoissonsAvril$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */