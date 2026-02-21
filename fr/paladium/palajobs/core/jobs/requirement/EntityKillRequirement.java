package fr.paladium.palajobs.core.jobs.requirement;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.jobs.Requirement;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class EntityKillRequirement extends Requirement<Class<? extends Entity>[], Class<? extends Entity>> {
  public EntityKillRequirement(int level, JobType type, String description, int value, Class<? extends Entity> target) {
    super(level, type, description, value, new Class[] { target });
  }
  
  public EntityKillRequirement(int level, JobType type, String description, int value, Class<? extends Entity>[] target) {
    super(level, type, description, value, target);
  }
  
  protected int validate(EntityPlayer player, Class<? extends Entity> object) {
    if (object == null)
      return 0; 
    for (Class<? extends Entity> target : (Class[])getTarget()) {
      if (target.equals(object))
        return 1; 
    } 
    return 0;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\jobs\requirement\EntityKillRequirement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */