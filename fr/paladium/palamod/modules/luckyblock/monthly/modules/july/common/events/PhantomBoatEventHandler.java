package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.PhantomBoatEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tasks.PhantomBoatRunnable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class PhantomBoatEventHandler {
  @SubscribeEvent
  public void onLivingDeath(LivingDeathEvent event) {
    Entity entity = event.entity;
    if (!(entity instanceof EntitySkeleton))
      return; 
    EntitySkeleton skeleton = (EntitySkeleton)entity;
    PhantomBoatRunnable runnable = PhantomBoatEvent.getBySkeleton(skeleton);
    if (runnable == null)
      return; 
    runnable.incrementKillCount();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\events\PhantomBoatEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */