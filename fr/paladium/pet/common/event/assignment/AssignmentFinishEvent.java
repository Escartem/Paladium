package fr.paladium.pet.common.event.assignment;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.pet.common.event.global.PetStatChangeEvent;
import fr.paladium.pet.common.event.global.UpdateStatType;
import fr.paladium.pet.server.config.assignment.Assignment;
import net.minecraft.entity.player.EntityPlayer;

public class AssignmentFinishEvent extends Event {
  private final EntityPlayer player;
  
  private final Assignment assignment;
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public Assignment getAssignment() {
    return this.assignment;
  }
  
  public AssignmentFinishEvent(EntityPlayer player, Assignment assignment) {
    this.player = player;
    this.assignment = assignment;
    PetStatChangeEvent.call(player, UpdateStatType.ASSIGNMENT_FINISHED);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\event\assignment\AssignmentFinishEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */