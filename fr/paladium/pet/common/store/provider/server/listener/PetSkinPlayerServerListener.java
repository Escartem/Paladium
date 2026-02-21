package fr.paladium.pet.common.store.provider.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.pet.common.store.provider.PetSkinShopProvider;

public class PetSkinPlayerServerListener {
  @SubscribeEvent
  public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
    FMLServerScheduler.getInstance().add(new Runnable[] { () -> PetSkinShopProvider.getInstance().importData(event.player) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\store\provider\server\listener\PetSkinPlayerServerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */