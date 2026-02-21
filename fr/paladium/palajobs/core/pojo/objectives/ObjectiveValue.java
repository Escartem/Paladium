package fr.paladium.palajobs.core.pojo.objectives;

import fr.paladium.palajobs.api.type.ObjectiveType;

public class ObjectiveValue {
  private ObjectiveType type;
  
  private double givedExperience;
  
  private int requiredLevel;
  
  public ObjectiveValue(ObjectiveType type, double givedExperience, int requiredLevel) {
    this.type = type;
    this.givedExperience = givedExperience;
    this.requiredLevel = requiredLevel;
  }
  
  public void setType(ObjectiveType type) {
    this.type = type;
  }
  
  public void setGivedExperience(double givedExperience) {
    this.givedExperience = givedExperience;
  }
  
  public void setRequiredLevel(int requiredLevel) {
    this.requiredLevel = requiredLevel;
  }
  
  public static ObjectiveValueBuilder builder() {
    return new ObjectiveValueBuilder();
  }
  
  public static class ObjectiveValueBuilder {
    private ObjectiveType type;
    
    private double givedExperience;
    
    private int requiredLevel;
    
    public ObjectiveValueBuilder type(ObjectiveType type) {
      this.type = type;
      return this;
    }
    
    public ObjectiveValueBuilder givedExperience(double givedExperience) {
      this.givedExperience = givedExperience;
      return this;
    }
    
    public ObjectiveValueBuilder requiredLevel(int requiredLevel) {
      this.requiredLevel = requiredLevel;
      return this;
    }
    
    public ObjectiveValue build() {
      return new ObjectiveValue(this.type, this.givedExperience, this.requiredLevel);
    }
    
    public String toString() {
      return "ObjectiveValue.ObjectiveValueBuilder(type=" + this.type + ", givedExperience=" + this.givedExperience + ", requiredLevel=" + this.requiredLevel + ")";
    }
  }
  
  public ObjectiveType getType() {
    return this.type;
  }
  
  public double getGivedExperience() {
    return this.givedExperience;
  }
  
  public int getRequiredLevel() {
    return this.requiredLevel;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\pojo\objectives\ObjectiveValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */