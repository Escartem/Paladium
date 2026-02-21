package fr.paladium.palajobs.core.pojo.objectives.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.achievements.PalaJobsHunterKillAchievement;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.ObjectiveValue;
import fr.paladium.palajobs.server.managers.JobsManager;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;

public class EntityKillObjective extends AbstractObjective<Class<?>> {
  public EntityKillObjective(JobType type) {
    super(ObjectiveType.ENTITY_KILL, type);
  }
  
  public static void performCheck(EntityPlayer player, Class<?> entity) {
    for (AbstractObjective<?> objective : (Iterable<AbstractObjective<?>>)JobsManager.getInstance().getObjectives(ObjectiveType.ENTITY_KILL)) {
      if (!(objective instanceof EntityKillObjective))
        continue; 
      EntityKillObjective currentObjective = (EntityKillObjective)objective;
      for (Map.Entry<Class<?>, ObjectiveValue> entry : (Iterable<Map.Entry<Class<?>, ObjectiveValue>>)currentObjective.getObjectives().entrySet()) {
        ObjectiveValue value = entry.getValue();
        double amount = value.getGivedExperience();
        if (((Class)entry.getKey()).equals(entity)) {
          PalaJobsHunterKillAchievement.performCheck(player, 1);
          incrementStats(player, currentObjective.getJobType(), value.getType(), amount, value.getRequiredLevel());
          return;
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\pojo\objectives\types\EntityKillObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */