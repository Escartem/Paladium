package fr.paladium.pet.server.assignement;

import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.assignment.AssignmentData;
import fr.paladium.pet.server.assignement.handler.AAssignmentHandler;
import fr.paladium.pet.server.config.assignment.Assignment;
import fr.paladium.pet.server.config.assignment.AssignmentConfig;
import fr.paladium.pet.server.config.assignment.fields.AssignmentType;
import java.util.HashMap;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayerMP;

public class AssignmentManager {
  private static AssignmentManager instance;
  
  private final HashMap<AssignmentType, AAssignmentHandler> handlers;
  
  private AssignmentConfig config;
  
  public AssignmentManager() {
    instance = this;
    this.handlers = new HashMap<>();
  }
  
  public static AssignmentManager getInstance() {
    if (instance == null)
      instance = new AssignmentManager(); 
    return instance;
  }
  
  public void registerHandler(Class<? extends AAssignmentHandler> clazz) {
    try {
      AAssignmentHandler handler = clazz.newInstance();
      if (this.handlers.containsKey(handler.getType()))
        throw new RuntimeException("Handler already registered for type " + handler.getType().name()); 
      this.handlers.put(handler.getType(), handler);
    } catch (Exception e) {
      throw new RuntimeException(e);
    } 
  }
  
  public AssignmentConfig getConfig() {
    if (this.config != null)
      return this.config; 
    return this.config = AssignmentConfig.get();
  }
  
  public void performAssignments(EntityPlayerMP player, PetPlayer pet, Object target, AssignmentType... types) {
    if (pet == null || !pet.has())
      return; 
    AssignmentConfig config = getConfig();
    if (config == null)
      return; 
    pet.getAssignmentData().getAssignments().values().forEach(data -> {
          Optional<Assignment> result = config.findAssignmentById(data.getAssignmentId());
          if (!result.isPresent())
            return; 
          boolean found = false;
          for (AssignmentType type : types) {
            if (type == ((Assignment)result.get()).getType()) {
              found = true;
              break;
            } 
          } 
          if (!found)
            return; 
          Assignment assignment = result.get();
          handleAssignment(player, pet, assignment, data, target);
        });
  }
  
  public void handleAssignment(EntityPlayerMP player, PetPlayer pet, Assignment assignment, AssignmentData data, Object target) {
    if (data.isCompleted())
      return; 
    AAssignmentHandler handler = this.handlers.get(assignment.getType());
    if (handler == null)
      return; 
    handler.handleAssignment(player, pet, assignment, data, target);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\assignement\AssignmentManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */