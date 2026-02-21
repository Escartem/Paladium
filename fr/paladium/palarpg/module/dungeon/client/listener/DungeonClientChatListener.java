package fr.paladium.palarpg.module.dungeon.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamixins.event.client.chat.ClientChatEvent;
import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.module.dungeon.common.network.global.CSPacketRPGDungeonGlobalSpawnCommand;

public class DungeonClientChatListener {
  @SubscribeEvent
  public void onChatMessage(ClientChatEvent event) {
    if (!"/spawn".equals(event.getMessage()) || !PalaRPGMod.proxy.isDungeonWorld())
      return; 
    event.setCanceled(true);
    (new CSPacketRPGDungeonGlobalSpawnCommand()).send();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\client\listener\DungeonClientChatListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */