package fr.paladium.pet.common.network.data.additional.assignment;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.pet.common.event.assignment.AssignmentFinishEvent;
import fr.paladium.pet.common.event.assignment.AssignmentIncrementEvent;
import fr.paladium.pet.common.event.experience.PetExperienceSource;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.config.assignment.Assignment;
import fr.paladium.pet.server.config.assignment.AssignmentConfig;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;

public class AssignmentData {
  private String assignmentId;
  
  private double progress;
  
  private boolean completed;
  
  public void setAssignmentId(String assignmentId) {
    this.assignmentId = assignmentId;
  }
  
  public void setProgress(double progress) {
    this.progress = progress;
  }
  
  public void setCompleted(boolean completed) {
    this.completed = completed;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof AssignmentData))
      return false; 
    AssignmentData other = (AssignmentData)o;
    if (!other.canEqual(this))
      return false; 
    if (Double.compare(getProgress(), other.getProgress()) != 0)
      return false; 
    if (isCompleted() != other.isCompleted())
      return false; 
    Object this$assignmentId = getAssignmentId(), other$assignmentId = other.getAssignmentId();
    return !((this$assignmentId == null) ? (other$assignmentId != null) : !this$assignmentId.equals(other$assignmentId));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof AssignmentData;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    long $progress = Double.doubleToLongBits(getProgress());
    result = result * 59 + (int)($progress >>> 32L ^ $progress);
    result = result * 59 + (isCompleted() ? 79 : 97);
    Object $assignmentId = getAssignmentId();
    return result * 59 + (($assignmentId == null) ? 43 : $assignmentId.hashCode());
  }
  
  public String toString() {
    return "AssignmentData(assignmentId=" + getAssignmentId() + ", progress=" + getProgress() + ", completed=" + isCompleted() + ")";
  }
  
  public String getAssignmentId() {
    return this.assignmentId;
  }
  
  public double getProgress() {
    return this.progress;
  }
  
  public boolean isCompleted() {
    return this.completed;
  }
  
  public AssignmentData(String id) {
    this.assignmentId = id;
    this.progress = 0.0D;
    this.completed = false;
  }
  
  public void complete(EntityPlayerMP player, PetPlayer pet, Assignment assignment) {
    this.progress = assignment.getAmount();
    this.completed = true;
    pet.earnExperience((EntityPlayer)player, PetExperienceSource.ASSIGNMENT, assignment.getGivenExp());
    pet.earnHappiness(player, assignment.getGivenPoints());
    MinecraftForge.EVENT_BUS.post((Event)new AssignmentFinishEvent((EntityPlayer)player, assignment));
  }
  
  public void incrementProgress(EntityPlayerMP player, PetPlayer pet, AssignmentConfig config, double amount) {
    Optional<Assignment> result = config.findAssignmentById(this.assignmentId);
    if (!result.isPresent())
      return; 
    AssignmentIncrementEvent incrementEvent = new AssignmentIncrementEvent((EntityPlayer)player, result.get(), this.progress, amount);
    if (MinecraftForge.EVENT_BUS.post((Event)incrementEvent))
      return; 
    Assignment assignment = result.get();
    double maximumProgress = assignment.getAmount();
    this.progress += amount;
    if (this.progress >= maximumProgress) {
      this.progress = maximumProgress;
      complete(player, pet, assignment);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\data\additional\assignment\AssignmentData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */