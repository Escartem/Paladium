package fr.paladium.palamod.modules.ajobs.common.registerer.requirement;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.jobs.Requirement;
import net.minecraft.entity.player.EntityPlayer;

public class ExtractSeveRequirement extends Requirement<String, String> {
  public ExtractSeveRequirement(int level, JobType type, String description, int value, String target) {
    super(level, type, description, value, target);
  }
  
  protected int validate(EntityPlayer player, String object) {
    return (object != null && object.equalsIgnoreCase((String)getTarget())) ? 1 : 0;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\registerer\requirement\ExtractSeveRequirement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */