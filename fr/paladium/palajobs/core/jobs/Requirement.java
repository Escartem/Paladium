package fr.paladium.palajobs.core.jobs;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import java.util.Objects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public abstract class Requirement<T, O> {
  private final int level;
  
  private final JobType type;
  
  private final String description;
  
  private final int value;
  
  private final T target;
  
  public Requirement(int level, JobType type, String description, int value, T target) {
    this.level = level;
    this.type = type;
    this.description = description;
    this.value = value;
    this.target = target;
  }
  
  public int getLevel() {
    return this.level;
  }
  
  public JobType getType() {
    return this.type;
  }
  
  public int getValue() {
    return this.value;
  }
  
  public T getTarget() {
    return this.target;
  }
  
  public void perform(EntityPlayer player, O object) {
    int count = validate(player, object);
    if (count <= 0)
      return; 
    JobsPlayer data = JobsPlayer.get((Entity)player);
    if (data == null)
      return; 
    JobsPlayer.get((Entity)player).incrementRequirementProgress(this.type, count);
  }
  
  public String getDescription() {
    return String.format(this.description, new Object[] { Integer.valueOf(this.value) });
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.level), this.type });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    Requirement<?, ?> other = (Requirement<?, ?>)obj;
    return (this.level == other.level && this.type == other.type);
  }
  
  protected abstract int validate(EntityPlayer paramEntityPlayer, O paramO);
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\jobs\Requirement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */