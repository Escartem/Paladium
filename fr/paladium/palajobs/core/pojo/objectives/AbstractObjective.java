package fr.paladium.palajobs.core.pojo.objectives;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AbstractObjective<T> {
  private final Map<T, ObjectiveValue> objectives;
  
  private final ObjectiveType type;
  
  private final JobType jobType;
  
  public Map<T, ObjectiveValue> getObjectives() {
    return this.objectives;
  }
  
  public ObjectiveType getType() {
    return this.type;
  }
  
  public JobType getJobType() {
    return this.jobType;
  }
  
  public AbstractObjective(ObjectiveType type, JobType jobType) {
    this.objectives = new HashMap<>();
    this.type = type;
    this.jobType = jobType;
  }
  
  public AbstractObjective<T> add(ObjectiveValue value, T t) {
    value.setType(this.type);
    this.objectives.put(t, value);
    return this;
  }
  
  @SafeVarargs
  public final AbstractObjective<T> add(ObjectiveValue value, T... t) {
    value.setType(this.type);
    for (T t1 : t)
      this.objectives.put(t1, value); 
    return this;
  }
  
  public static void incrementStats(EntityPlayer player, JobType type, ObjectiveType objectiveType, double experience, int requiredLevel) {
    if (!(player instanceof net.minecraft.entity.player.EntityPlayerMP))
      return; 
    JobsPlayer data = JobsPlayer.get((Entity)player);
    if (data == null || JobExpUtils.getLevel(data.getExperience(type)) < requiredLevel)
      return; 
    data.addXp(type, objectiveType, experience, player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\pojo\objectives\AbstractObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */