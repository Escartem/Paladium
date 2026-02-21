package fr.paladium.palamod.modules.paladium.cheat.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamixins.event.common.entity.EntityIsInsideOpaqueBlockEvent;

public class ClientNoClipListener {
  @SubscribeEvent
  public void onEntityInsideBlock(EntityIsInsideOpaqueBlockEvent event) {
    if ((event.getEntity()).field_70145_X)
      event.setCanceled(true); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\cheat\client\ClientNoClipListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */