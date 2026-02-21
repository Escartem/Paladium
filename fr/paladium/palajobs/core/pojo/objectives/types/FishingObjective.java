package fr.paladium.palajobs.core.pojo.objectives.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.ObjectiveValue;
import fr.paladium.palajobs.server.managers.JobsManager;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class FishingObjective extends AbstractObjective<ItemStack> {
  public FishingObjective(JobType type) {
    super(ObjectiveType.FISH, type);
  }
  
  public static void performCheck(EntityPlayer player, ItemStack target) {
    for (AbstractObjective<?> objective : (Iterable<AbstractObjective<?>>)JobsManager.getInstance().getObjectives(ObjectiveType.FISH)) {
      if (!(objective instanceof FishingObjective))
        continue; 
      FishingObjective currentObjective = (FishingObjective)objective;
      for (Map.Entry<ItemStack, ObjectiveValue> entry : (Iterable<Map.Entry<ItemStack, ObjectiveValue>>)currentObjective.getObjectives().entrySet()) {
        ObjectiveValue value = entry.getValue();
        double amount = value.getGivedExperience();
        if (target.func_77969_a(entry.getKey())) {
          incrementStats(player, currentObjective.getJobType(), value.getType(), amount, value.getRequiredLevel());
          return;
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\pojo\objectives\types\FishingObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */