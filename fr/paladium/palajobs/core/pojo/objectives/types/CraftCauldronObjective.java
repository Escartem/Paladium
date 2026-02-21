package fr.paladium.palajobs.core.pojo.objectives.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.ObjectiveValue;
import fr.paladium.palajobs.server.managers.JobsManager;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CraftCauldronObjective extends AbstractObjective<ItemStack> {
  public CraftCauldronObjective(JobType type) {
    super(ObjectiveType.CRAFT_CAULDRON, type);
  }
  
  public static void performCheck(EntityPlayer player, ItemStack itemStack) {
    for (AbstractObjective<?> objective : (Iterable<AbstractObjective<?>>)JobsManager.getInstance().getObjectives(ObjectiveType.CRAFT_CAULDRON)) {
      if (!(objective instanceof CraftCauldronObjective))
        continue; 
      CraftCauldronObjective currentObjective = (CraftCauldronObjective)objective;
      for (Map.Entry<ItemStack, ObjectiveValue> entry : (Iterable<Map.Entry<ItemStack, ObjectiveValue>>)currentObjective.getObjectives().entrySet()) {
        ObjectiveValue value = entry.getValue();
        double amount = value.getGivedExperience();
        if (((ItemStack)entry.getKey()).func_77969_a(itemStack)) {
          incrementStats(player, currentObjective.getJobType(), value.getType(), amount, value.getRequiredLevel());
          return;
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\pojo\objectives\types\CraftCauldronObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */