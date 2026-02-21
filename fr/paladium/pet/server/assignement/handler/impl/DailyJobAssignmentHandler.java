package fr.paladium.pet.server.assignement.handler.impl;

import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.assignment.AssignmentData;
import fr.paladium.pet.server.assignement.handler.AAssignmentHandler;
import fr.paladium.pet.server.config.assignment.Assignment;
import fr.paladium.pet.server.config.assignment.fields.AssignmentType;
import net.minecraft.entity.player.EntityPlayerMP;

public class DailyJobAssignmentHandler extends AAssignmentHandler {
  public DailyJobAssignmentHandler() {
    super(AssignmentType.DAILY_JOB);
  }
  
  public double getAmount(EntityPlayerMP player, PetPlayer pet, Assignment assignment, AssignmentData data, Object t) {
    return 1.0D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\assignement\handler\impl\DailyJobAssignmentHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */