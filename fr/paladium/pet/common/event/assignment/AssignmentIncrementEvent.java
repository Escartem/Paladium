package fr.paladium.pet.common.event.assignment;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.pet.common.event.global.PetStatChangeEvent;
import fr.paladium.pet.common.event.global.UpdateStatType;
import fr.paladium.pet.server.config.assignment.Assignment;
import net.minecraft.entity.player.EntityPlayer;

@Cancelable
public class AssignmentIncrementEvent extends Event {
  private final EntityPlayer player;
  
  private final Assignment assignment;
  
  private final double currentProgress;
  
  private final double amount;
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public Assignment getAssignment() {
    return this.assignment;
  }
  
  public double getCurrentProgress() {
    return this.currentProgress;
  }
  
  public double getAmount() {
    return this.amount;
  }
  
  public AssignmentIncrementEvent(EntityPlayer player, Assignment assignment, double currentProgress, double amount) {
    this.player = player;
    this.assignment = assignment;
    this.currentProgress = currentProgress;
    this.amount = amount;
    PetStatChangeEvent.call(player, UpdateStatType.ASSIGNMENT_CHANGED);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\event\assignment\AssignmentIncrementEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */