package fr.paladium.palajobs.core.pojo.objectives.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.ObjectiveValue;
import fr.paladium.palajobs.server.managers.JobsManager;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;

public class ArrowKillObjective extends AbstractObjective<Class<?>> {
  public ArrowKillObjective(JobType type) {
    super(ObjectiveType.ARROW_KILL, type);
  }
  
  public static void performCheck(EntityPlayer player, Class<?> entityType) {
    for (AbstractObjective<?> objective : (Iterable<AbstractObjective<?>>)JobsManager.getInstance().getObjectives(ObjectiveType.ARROW_KILL)) {
      if (!(objective instanceof ArrowKillObjective))
        continue; 
      ArrowKillObjective currentObjective = (ArrowKillObjective)objective;
      for (Map.Entry<Class<?>, ObjectiveValue> entry : (Iterable<Map.Entry<Class<?>, ObjectiveValue>>)currentObjective.getObjectives().entrySet()) {
        ObjectiveValue value = entry.getValue();
        double amount = value.getGivedExperience();
        if (((Class)entry.getKey()).equals(entityType)) {
          incrementStats(player, currentObjective.getJobType(), value.getType(), amount, value.getRequiredLevel());
          return;
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\pojo\objectives\types\ArrowKillObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */