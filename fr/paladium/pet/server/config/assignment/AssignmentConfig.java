package fr.paladium.pet.server.config.assignment;

import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import fr.paladium.pet.server.PetServerProxy;
import fr.paladium.pet.server.commands.assignment.AssignmentSubCommand;
import fr.paladium.pet.server.config.assignment.fields.LevelRange;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@ConfigFile(path = "pet/assignment_config.json", blocking = true)
public class AssignmentConfig implements IConfig {
  private float minLightAssignmentLightLevel;
  
  private float maxDarkAssignmentLightLevel;
  
  private int maxAssignmentsPerDay;
  
  public void setMinLightAssignmentLightLevel(float minLightAssignmentLightLevel) {
    this.minLightAssignmentLightLevel = minLightAssignmentLightLevel;
  }
  
  public void setMaxDarkAssignmentLightLevel(float maxDarkAssignmentLightLevel) {
    this.maxDarkAssignmentLightLevel = maxDarkAssignmentLightLevel;
  }
  
  public void setMaxAssignmentsPerDay(int maxAssignmentsPerDay) {
    this.maxAssignmentsPerDay = maxAssignmentsPerDay;
  }
  
  public void setAssignments(List<Assignment> assignments) {
    this.assignments = assignments;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof AssignmentConfig))
      return false; 
    AssignmentConfig other = (AssignmentConfig)o;
    if (!other.canEqual(this))
      return false; 
    if (Float.compare(getMinLightAssignmentLightLevel(), other.getMinLightAssignmentLightLevel()) != 0)
      return false; 
    if (Float.compare(getMaxDarkAssignmentLightLevel(), other.getMaxDarkAssignmentLightLevel()) != 0)
      return false; 
    if (getMaxAssignmentsPerDay() != other.getMaxAssignmentsPerDay())
      return false; 
    Object<Assignment> this$assignments = (Object<Assignment>)getAssignments(), other$assignments = (Object<Assignment>)other.getAssignments();
    return !((this$assignments == null) ? (other$assignments != null) : !this$assignments.equals(other$assignments));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof AssignmentConfig;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + Float.floatToIntBits(getMinLightAssignmentLightLevel());
    result = result * 59 + Float.floatToIntBits(getMaxDarkAssignmentLightLevel());
    result = result * 59 + getMaxAssignmentsPerDay();
    Object<Assignment> $assignments = (Object<Assignment>)getAssignments();
    return result * 59 + (($assignments == null) ? 43 : $assignments.hashCode());
  }
  
  public String toString() {
    return "AssignmentConfig(minLightAssignmentLightLevel=" + getMinLightAssignmentLightLevel() + ", maxDarkAssignmentLightLevel=" + getMaxDarkAssignmentLightLevel() + ", maxAssignmentsPerDay=" + getMaxAssignmentsPerDay() + ", assignments=" + getAssignments() + ")";
  }
  
  public float getMinLightAssignmentLightLevel() {
    return this.minLightAssignmentLightLevel;
  }
  
  public float getMaxDarkAssignmentLightLevel() {
    return this.maxDarkAssignmentLightLevel;
  }
  
  public int getMaxAssignmentsPerDay() {
    return this.maxAssignmentsPerDay;
  }
  
  public List<Assignment> getAssignments() {
    return this.assignments;
  }
  
  private List<Assignment> assignments = new ArrayList<>();
  
  public static AssignmentConfig get() {
    return PetServerProxy.getInstance().getAssignmentConfig();
  }
  
  public boolean isValid() {
    return super.isValid();
  }
  
  public void onLoaded() {
    updateSubCommand();
    reverseWeight();
  }
  
  public void onReloaded() {
    onLoaded();
  }
  
  public void onFailed() {}
  
  private void updateSubCommand() {
    AssignmentSubCommand sub = AssignmentSubCommand.getInstance();
    if (sub == null)
      return; 
    String[] args = (String[])this.assignments.stream().map(Assignment::getId).toArray(x$0 -> new String[x$0]);
    sub.updateAssignments(args);
  }
  
  private void reverseWeight() {
    List<LevelRange> ranges = new ArrayList<>();
    for (Assignment assignment : this.assignments) {
      assignment.setWeight(1.0F / assignment.getWeight());
      if (ranges.contains(assignment.getLevelRange()))
        continue; 
      ranges.add(assignment.getLevelRange());
    } 
    for (LevelRange range : ranges) {
      float sum = 0.0F;
      for (Assignment assignment : this.assignments) {
        if (!assignment.getLevelRange().equals(range))
          continue; 
        sum += assignment.getWeight();
      } 
      for (Assignment assignment : this.assignments) {
        if (!assignment.getLevelRange().equals(range))
          continue; 
        assignment.setWeight(assignment.getWeight() / sum);
      } 
    } 
  }
  
  public List<Assignment> findAssignments(int level) {
    return (List<Assignment>)this.assignments.stream()
      .filter(assignment -> assignment.getLevelRange().isInRange(level))
      .collect(Collectors.toList());
  }
  
  public Assignment getRandomAssignment(Random random, int level) {
    double rand = random.nextDouble();
    double cumulativeProbability = 0.0D;
    List<Assignment> rangeAssignments = findAssignments(level);
    if (rangeAssignments == null || rangeAssignments.isEmpty())
      return getRandomAssignment(random); 
    for (Assignment assignment : rangeAssignments) {
      cumulativeProbability += assignment.getWeight();
      if (rand <= cumulativeProbability)
        return assignment; 
    } 
    return null;
  }
  
  public Assignment getRandomAssignment(Random random) {
    return this.assignments.get(random.nextInt(this.assignments.size()));
  }
  
  public Optional<Assignment> findAssignmentById(String id) {
    return this.assignments.stream().filter(assignment -> assignment.getId().equals(id)).findFirst();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\config\assignment\AssignmentConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */