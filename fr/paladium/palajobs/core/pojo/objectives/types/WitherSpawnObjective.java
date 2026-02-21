package fr.paladium.palajobs.core.pojo.objectives.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.ObjectiveValue;
import fr.paladium.palajobs.server.managers.JobsManager;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;

public class WitherSpawnObjective extends AbstractObjective<Object> {
  public WitherSpawnObjective(JobType type) {
    super(ObjectiveType.WITHER_SPAWN, type);
  }
  
  public static void performCheck(EntityPlayer player) {
    for (AbstractObjective<?> objective : (Iterable<AbstractObjective<?>>)JobsManager.getInstance().getObjectives(ObjectiveType.WITHER_SPAWN)) {
      if (!(objective instanceof WitherSpawnObjective))
        continue; 
      WitherSpawnObjective currentObjective = (WitherSpawnObjective)objective;
      Iterator<Map.Entry<Object, ObjectiveValue>> iterator = currentObjective.getObjectives().entrySet().iterator();
      if (iterator.hasNext()) {
        Map.Entry<Object, ObjectiveValue> entry = iterator.next();
        ObjectiveValue value = entry.getValue();
        double amount = value.getGivedExperience();
        incrementStats(player, currentObjective.getJobType(), value.getType(), amount, value.getRequiredLevel());
        return;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\pojo\objectives\types\WitherSpawnObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */