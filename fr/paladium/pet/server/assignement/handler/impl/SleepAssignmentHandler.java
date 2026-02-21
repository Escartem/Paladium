package fr.paladium.pet.server.assignement.handler.impl;

import fr.paladium.palaforgeutils.lib.java.map.player.SessionPlayerMap;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.assignment.AssignmentData;
import fr.paladium.pet.server.assignement.handler.AAssignmentHandler;
import fr.paladium.pet.server.config.assignment.Assignment;
import fr.paladium.pet.server.config.assignment.fields.AssignmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class SleepAssignmentHandler extends AAssignmentHandler {
  private SessionPlayerMap<DoubleLocation> lastLocations;
  
  public SleepAssignmentHandler() {
    super(AssignmentType.SLEEP);
    this.lastLocations = new SessionPlayerMap<DoubleLocation>() {
        public DoubleLocation getDefaultValue() {
          return null;
        }
      };
  }
  
  public double getAmount(EntityPlayerMP player, PetPlayer pet, Assignment assignment, AssignmentData data, Object object) {
    if (!this.lastLocations.containsKey((Entity)player)) {
      this.lastLocations.put((Entity)player, new DoubleLocation((Entity)player));
      return 0.0D;
    } 
    DoubleLocation currentLocation = new DoubleLocation((Entity)player);
    DoubleLocation lastLocation = (DoubleLocation)this.lastLocations.get((Entity)player);
    this.lastLocations.put((Entity)player, currentLocation);
    if (lastLocation.getBlockX() == currentLocation.getBlockX() && lastLocation
      .getBlockY() == currentLocation.getBlockY() && lastLocation
      .getBlockZ() == currentLocation.getBlockZ())
      return 1.0D; 
    return 0.0D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\assignement\handler\impl\SleepAssignmentHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */