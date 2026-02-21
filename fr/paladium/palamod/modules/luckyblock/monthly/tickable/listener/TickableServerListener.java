package fr.paladium.palamod.modules.luckyblock.monthly.tickable.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.TickableFeature;

public class TickableServerListener {
  private final TickableFeature feature;
  
  public TickableServerListener(TickableFeature feature) {
    this.feature = feature;
  }
  
  @SubscribeEvent
  public void onTick(TickEvent.ServerTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    long now = System.currentTimeMillis();
    this.feature.getTickables().forEach(t -> t.tick(now));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\tickable\listener\TickableServerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */