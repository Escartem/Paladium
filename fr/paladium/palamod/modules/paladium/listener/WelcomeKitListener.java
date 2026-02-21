package fr.paladium.palamod.modules.paladium.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.palamod.modules.paladium.common.eep.WelcomeEEP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class WelcomeKitListener {
  @SubscribeEvent
  public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
    EntityPlayerMP player = (EntityPlayerMP)event.player;
    if (player == null)
      return; 
    WelcomeEEP eep = WelcomeEEP.get((Entity)player);
    if (eep != null && !eep.isWelcomeKit())
      eep.giveWelcomeKit(player); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\listener\WelcomeKitListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */