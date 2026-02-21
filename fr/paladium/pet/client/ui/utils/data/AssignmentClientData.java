package fr.paladium.pet.client.ui.utils.data;

import fr.paladium.pet.client.ui.utils.PetUIUtils;
import fr.paladium.pet.common.network.data.additional.assignment.AssignmentData;
import fr.paladium.pet.server.config.assignment.Assignment;
import fr.paladium.pet.server.config.assignment.fields.AssignmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class AssignmentClientData {
  private final String name;
  
  private final String description;
  
  private final AssignmentType type;
  
  private final ResourceLocation logo;
  
  private final double progress;
  
  private final double maxProgress;
  
  public AssignmentClientData(String name, String description, AssignmentType type, ResourceLocation logo, double progress, double maxProgress) {
    this.name = name;
    this.description = description;
    this.type = type;
    this.logo = logo;
    this.progress = progress;
    this.maxProgress = maxProgress;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public AssignmentType getType() {
    return this.type;
  }
  
  public ResourceLocation getLogo() {
    return this.logo;
  }
  
  public double getProgress() {
    return this.progress;
  }
  
  public double getMaxProgress() {
    return this.maxProgress;
  }
  
  public static AssignmentClientData from(EntityPlayer player, Assignment assignment, AssignmentData data) {
    ResourceLocation logo = PetUIUtils.getResourceFromTexturePath(assignment.getLogo());
    return new AssignmentClientData(assignment
        .getName(player), assignment
        .getDescription(player), assignment
        .getType(), logo, data
        
        .getProgress(), assignment
        .getAmount());
  }
  
  public boolean isFinish() {
    return (this.progress >= this.maxProgress);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\u\\utils\data\AssignmentClientData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */