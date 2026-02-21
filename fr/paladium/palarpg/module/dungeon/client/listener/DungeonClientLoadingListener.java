package fr.paladium.palarpg.module.dungeon.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palarpg.module.dungeon.client.ui.loading.UIDungeonLoading;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.Optional;

public class DungeonClientLoadingListener {
  @SubscribeEvent
  public void onLoading(TickEvent.ClientTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.getClient();
    if (!optionalDungeonWorld.isPresent())
      return; 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (dungeonWorld.getState() != DungeonWorld.DungeonState.READY)
      return; 
    if (!ZUI.isOpen(UIDungeonLoading.class))
      ZUI.open((UI)(new UIDungeonLoading()).start(), true); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\client\listener\DungeonClientLoadingListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */