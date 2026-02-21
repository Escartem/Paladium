package fr.paladium.palarpg.module.stat.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamixins.event.server.CriticalHitEvent;
import fr.paladium.palarpg.PalaRPGMod;

public class StatServerPlayerEventListener {
  @SubscribeEvent
  public void onCriticalHit(CriticalHitEvent event) {
    if (PalaRPGMod.proxy.isDungeonWorld() && (event.getType() == CriticalHitEvent.CriticalType.CRITICAL || event.getType() == CriticalHitEvent.CriticalType.ENCHANTMENT))
      event.setCanceled(true); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\server\listener\StatServerPlayerEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */