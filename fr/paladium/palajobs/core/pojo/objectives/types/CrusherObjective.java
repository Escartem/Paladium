package fr.paladium.palajobs.core.pojo.objectives.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.ObjectiveValue;
import fr.paladium.palajobs.server.managers.JobsManager;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CrusherObjective extends AbstractObjective<ItemStack> {
  public CrusherObjective(JobType type) {
    super(ObjectiveType.CRUSHER_GENERATE, type);
  }
  
  public static void performCheck(EntityPlayer player, ItemStack itemStack, int quantity) {
    for (AbstractObjective<?> objective : (Iterable<AbstractObjective<?>>)JobsManager.getInstance().getObjectives(ObjectiveType.CRUSHER_GENERATE)) {
      if (!(objective instanceof CrusherObjective))
        continue; 
      CrusherObjective currentObjective = (CrusherObjective)objective;
      for (Map.Entry<ItemStack, ObjectiveValue> entry : (Iterable<Map.Entry<ItemStack, ObjectiveValue>>)currentObjective.getObjectives().entrySet()) {
        ObjectiveValue value = entry.getValue();
        double amount = value.getGivedExperience();
        if (((ItemStack)entry.getKey()).func_77969_a(itemStack)) {
          incrementStats(player, currentObjective.getJobType(), value.getType(), amount * quantity, value.getRequiredLevel());
          return;
        } 
      } 
    } 
  }
  
  public static void performCheck(EntityPlayer player, ItemStack itemStack) {
    for (AbstractObjective<?> objective : (Iterable<AbstractObjective<?>>)JobsManager.getInstance().getObjectives(ObjectiveType.CRUSHER_GENERATE)) {
      if (!(objective instanceof CrusherObjective))
        continue; 
      CrusherObjective currentObjective = (CrusherObjective)objective;
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


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\pojo\objectives\types\CrusherObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */