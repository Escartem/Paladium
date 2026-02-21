package fr.paladium.palarpg.module.dungeon.server.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palarpg.module.dungeon.server.manager.DungeonRankingManager;
import fr.paladium.palarpg.module.profile.server.event.RPGExperienceEvent;

public class DungeonServerRankingListener {
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onExperienceGain(RPGExperienceEvent.GainEvent event) {
    DungeonRankingManager.addWeekly(event.getPlayer(), (int)Math.ceil(event.getAmount()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\listener\DungeonServerRankingListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */