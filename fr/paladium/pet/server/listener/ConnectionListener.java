package fr.paladium.pet.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.pet.common.network.data.PetPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ConnectionListener {
  @SubscribeEvent
  public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
    EntityPlayerMP player = (EntityPlayerMP)event.player;
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    if (pet == null || !pet.has())
      return; 
    pet.onPlayerLoggedIn(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\listener\ConnectionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */