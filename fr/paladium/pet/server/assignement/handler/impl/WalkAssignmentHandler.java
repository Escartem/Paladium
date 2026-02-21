package fr.paladium.pet.server.assignement.handler.impl;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.assignment.AssignmentData;
import fr.paladium.pet.server.assignement.handler.AAssignmentHandler;
import fr.paladium.pet.server.config.assignment.Assignment;
import fr.paladium.pet.server.config.assignment.fields.AssignmentType;
import net.minecraft.entity.player.EntityPlayerMP;

public class WalkAssignmentHandler extends AAssignmentHandler {
  private static final double MINIMUM_DISTANCE = 1.0D;
  
  private static final double MAXIMUM_DISTANCE = 3.0D;
  
  public WalkAssignmentHandler() {
    super(AssignmentType.WALK);
  }
  
  public double getAmount(EntityPlayerMP player, PetPlayer pet, Assignment assignment, AssignmentData data, Object object) {
    if (!(object instanceof DoubleLocation[]))
      return 0.0D; 
    DoubleLocation[] locations = (DoubleLocation[])object;
    if (locations.length != 2)
      return 0.0D; 
    DoubleLocation lastLocation = locations[0];
    DoubleLocation currentLocation = locations[1];
    if (lastLocation == null || currentLocation == null)
      return 0.0D; 
    double distance = lastLocation.distance(currentLocation);
    if (distance <= 3.0D)
      return distance; 
    return 0.0D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\assignement\handler\impl\WalkAssignmentHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */