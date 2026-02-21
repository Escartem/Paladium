package fr.paladium.palajobs.core.pojo.objectives.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.ObjectiveValue;
import fr.paladium.palajobs.server.managers.JobsManager;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CraftObjective extends AbstractObjective<ItemStack> {
  public CraftObjective(JobType jobType) {
    super(ObjectiveType.CRAFT, jobType);
  }
  
  public static void performCheck(EntityPlayer player, ItemStack itemStack) {
    for (AbstractObjective<?> objective : (Iterable<AbstractObjective<?>>)JobsManager.getInstance().getObjectives(ObjectiveType.CRAFT)) {
      if (!(objective instanceof CraftObjective))
        continue; 
      CraftObjective currentObjective = (CraftObjective)objective;
      for (Map.Entry<ItemStack, ObjectiveValue> entry : (Iterable<Map.Entry<ItemStack, ObjectiveValue>>)currentObjective.getObjectives().entrySet()) {
        ObjectiveValue value = entry.getValue();
        double amount = value.getGivedExperience();
        if (((ItemStack)entry.getKey()).func_77969_a(itemStack)) {
          if (itemStack.field_77994_a > 0)
            incrementStats(player, currentObjective.getJobType(), value.getType(), amount * ((itemStack.field_77994_a > 0) ? itemStack.field_77994_a : true), value.getRequiredLevel()); 
          return;
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\pojo\objectives\types\CraftObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */