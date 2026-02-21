package fr.paladium.palajobs.core.pojo.objectives.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.achievements.PalaJobsHunterKillAchievement;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.ObjectiveValue;
import fr.paladium.palajobs.server.managers.JobsManager;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;

public class EntityKillSpecialObjective extends AbstractObjective<Class<?>> {
  public EntityKillSpecialObjective(JobType type) {
    super(ObjectiveType.ENTITY_KILL_SPECIAL, type);
  }
  
  public static void performCheck(EntityPlayer player, Class<?> entity, double xp) {
    for (AbstractObjective<?> objective : (Iterable<AbstractObjective<?>>)JobsManager.getInstance().getObjectives(ObjectiveType.ENTITY_KILL_SPECIAL)) {
      if (!(objective instanceof EntityKillSpecialObjective))
        continue; 
      EntityKillSpecialObjective currentObjective = (EntityKillSpecialObjective)objective;
      for (Map.Entry<Class<?>, ObjectiveValue> entry : (Iterable<Map.Entry<Class<?>, ObjectiveValue>>)currentObjective.getObjectives().entrySet()) {
        ObjectiveValue value = entry.getValue();
        if (((Class)entry.getKey()).equals(entity)) {
          PalaJobsHunterKillAchievement.performCheck(player, 1);
          incrementStats(player, currentObjective.getJobType(), value.getType(), xp, value.getRequiredLevel());
          return;
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\pojo\objectives\types\EntityKillSpecialObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */