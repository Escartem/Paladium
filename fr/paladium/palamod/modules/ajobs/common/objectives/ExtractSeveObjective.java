package fr.paladium.palamod.modules.ajobs.common.objectives;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.ObjectiveValue;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.palamod.modules.world.PWorld;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ExtractSeveObjective extends AbstractObjective<ItemStack> {
  public ExtractSeveObjective(JobType type) {
    super(ObjectiveType.EXTRACT_SEVE, type);
  }
  
  public static void performCheck(EntityPlayer player, String seveType, int metadata) {
    for (AbstractObjective<?> objective : (Iterable<AbstractObjective<?>>)JobsManager.getInstance().getObjectives(ObjectiveType.EXTRACT_SEVE)) {
      if (!(objective instanceof ExtractSeveObjective))
        continue; 
      ExtractSeveObjective currentObjective = (ExtractSeveObjective)objective;
      for (Map.Entry<ItemStack, ObjectiveValue> entry : (Iterable<Map.Entry<ItemStack, ObjectiveValue>>)currentObjective.getObjectives().entrySet()) {
        ObjectiveValue value = entry.getValue();
        double amount = value.getGivedExperience();
        String seveTypeToTest = "";
        if (((ItemStack)entry.getKey()).func_77969_a(new ItemStack(PWorld.LOG_JACARANDA))) {
          seveTypeToTest = "Jacaranda";
        } else if (((ItemStack)entry.getKey()).func_77969_a(new ItemStack(PWorld.LOG_ERABLE))) {
          seveTypeToTest = "Erable";
        } else if (((ItemStack)entry.getKey()).func_77969_a(new ItemStack(PWorld.LOG_JUDEECERCIS))) {
          seveTypeToTest = "Judeecercis";
        } 
        if (!seveTypeToTest.isEmpty() && seveTypeToTest.equalsIgnoreCase(seveType)) {
          double xp = amount * 1.0D * metadata / 6.0D;
          if (xp <= 0.0D)
            return; 
          AbstractObjective.incrementStats(player, currentObjective.getJobType(), value.getType(), xp, value.getRequiredLevel());
          return;
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\objectives\ExtractSeveObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */