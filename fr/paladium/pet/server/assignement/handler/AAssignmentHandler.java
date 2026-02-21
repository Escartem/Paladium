package fr.paladium.pet.server.assignement.handler;

import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.assignment.AssignmentData;
import fr.paladium.pet.server.config.assignment.Assignment;
import fr.paladium.pet.server.config.assignment.AssignmentConfig;
import fr.paladium.pet.server.config.assignment.fields.AssignmentType;
import net.minecraft.entity.player.EntityPlayerMP;

public abstract class AAssignmentHandler {
  public static final double NO_PROGRESS = 0.0D;
  
  public static final double DEFAULT_AMOUNT = 1.0D;
  
  private final AssignmentType type;
  
  public AssignmentType getType() {
    return this.type;
  }
  
  public AAssignmentHandler(AssignmentType type) {
    this.type = type;
  }
  
  public void handleAssignment(EntityPlayerMP player, PetPlayer pet, Assignment assignment, AssignmentData data) {
    handleAssignment(player, pet, assignment, data, null);
  }
  
  public void handleAssignment(EntityPlayerMP player, PetPlayer pet, Assignment assignment, AssignmentData data, Object t) {
    double amount = getAmount(player, pet, assignment, data, t);
    if (amount <= 0.0D)
      return; 
    AssignmentConfig config = AssignmentConfig.get();
    data.incrementProgress(player, pet, config, amount);
  }
  
  public abstract double getAmount(EntityPlayerMP paramEntityPlayerMP, PetPlayer paramPetPlayer, Assignment paramAssignment, AssignmentData paramAssignmentData, Object paramObject);
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\assignement\handler\AAssignmentHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */