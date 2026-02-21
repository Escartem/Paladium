package fr.paladium.palajobs.core.jobs.type;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.jobs.AbstractJob;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.BreakBlockObjective;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class MinerJob extends AbstractJob {
  public static MinerJobBuilder builder() {
    return new MinerJobBuilder();
  }
  
  public static class MinerJobBuilder {
    public MinerJob build() {
      return new MinerJob();
    }
    
    public String toString() {
      return "MinerJob.MinerJobBuilder()";
    }
  }
  
  public MinerJob() {
    super(JobType.MINER);
  }
  
  public void registerObjectives() {
    BreakBlockObjective objective = new BreakBlockObjective(JobType.MINER);
    objective.add(buildValue(0.5D, 0), new ItemStack(Blocks.field_150348_b));
    addObjectives(new AbstractObjective[] { (AbstractObjective)objective });
  }
  
  public void registerRewards() {}
  
  public void registerBlacklistedCrafts() {}
  
  public void registerBlacklistedUsages() {}
  
  public void registerLvlTokensRewards() {}
  
  public void registerRequirements() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\jobs\type\MinerJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */