package fr.paladium.palajobs.core.pojo.objectives.types;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palajobs.core.achievements.PalaJobsFarmingPlantAchievement;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.ObjectiveValue;
import fr.paladium.palajobs.core.utils.PlacedBlockData;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.palajobs.utils.forge.location.BlockLocation;
import fr.paladium.palajobs.utils.forge.location.Location;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class BreakBlockObjective extends AbstractObjective<ItemStack> {
  public BreakBlockObjective(JobType jobType) {
    super(ObjectiveType.BREAK_BLOCK, jobType);
  }
  
  public static void performCheck(EntityPlayer player, Location location, ItemStack itemStack, boolean include) {
    BlockLocation blockLocation = new BlockLocation(location);
    for (AbstractObjective<?> objective : (Iterable<AbstractObjective<?>>)JobsManager.getInstance().getObjectives(ObjectiveType.BREAK_BLOCK)) {
      if (!(objective instanceof BreakBlockObjective))
        continue; 
      BreakBlockObjective currentObjective = (BreakBlockObjective)objective;
      for (Map.Entry<ItemStack, ObjectiveValue> entry : (Iterable<Map.Entry<ItemStack, ObjectiveValue>>)currentObjective.getObjectives().entrySet()) {
        ObjectiveValue value = entry.getValue();
        double amount = value.getGivedExperience();
        if (((ItemStack)entry.getKey()).func_77969_a(itemStack)) {
          if (((Block.func_149634_a(((ItemStack)entry.getKey()).func_77973_b()) != null && Block.func_149634_a(((ItemStack)entry.getKey()).func_77973_b()) == Blocks.field_150440_ba) || include) && 
            PlacedBlockData.isPlaced(location.getWorld(), blockLocation.x, blockLocation.y, blockLocation.z))
            return; 
          JobsPlayer data = JobsPlayer.get((Entity)player);
          if (data != null && JobExpUtils.getLevel(data.getExperience(currentObjective.getJobType())) >= value.getRequiredLevel() && 
            JobType.FARMER.equals(currentObjective.getJobType()))
            PalaJobsFarmingPlantAchievement.performCheck(player, 1); 
          AbstractObjective.incrementStats(player, currentObjective.getJobType(), value.getType(), amount, value.getRequiredLevel());
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\pojo\objectives\types\BreakBlockObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */