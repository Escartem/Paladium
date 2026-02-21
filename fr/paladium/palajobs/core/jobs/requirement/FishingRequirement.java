package fr.paladium.palajobs.core.jobs.requirement;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.jobs.Requirement;
import net.minecraft.entity.player.EntityPlayer;

public class FishingRequirement extends Requirement<Void, Void> {
  public FishingRequirement(int level, JobType type, String description, int value) {
    super(level, type, description, value, null);
  }
  
  protected int validate(EntityPlayer player, Void object) {
    return 1;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\jobs\requirement\FishingRequirement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */