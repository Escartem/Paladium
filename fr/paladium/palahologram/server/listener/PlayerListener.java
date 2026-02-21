package fr.paladium.palahologram.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.palahologram.common.worlddata.HologramWorldData;
import net.minecraft.entity.player.EntityPlayerMP;

public class PlayerListener {
  @SubscribeEvent
  public void onEntityJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
    HologramWorldData.get().sync((EntityPlayerMP)event.player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palahologram\server\listener\PlayerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */