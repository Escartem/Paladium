package fr.paladium.pet.server.assignement.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.pet.common.event.PlayerMoveEvent;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.assignement.AssignmentManager;
import fr.paladium.pet.server.config.assignment.fields.AssignmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class AssignmentMoveListener {
  private final AssignmentManager manager = AssignmentManager.getInstance();
  
  @SubscribeEvent
  public void onMove(PlayerMoveEvent event) {
    EntityPlayerMP player = event.getPlayer();
    DoubleLocation[] locations = { event.getLastLocation(), event.getCurrentLocation() };
    this.manager.performAssignments(player, PetPlayer.get((EntityPlayer)player), locations, new AssignmentType[] { AssignmentType.WALK });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\assignement\listener\AssignmentMoveListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */