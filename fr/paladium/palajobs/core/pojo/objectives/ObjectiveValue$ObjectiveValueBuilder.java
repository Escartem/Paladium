package fr.paladium.palajobs.core.pojo.objectives;

import fr.paladium.palajobs.api.type.ObjectiveType;

public class ObjectiveValueBuilder {
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


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\pojo\objectives\ObjectiveValue$ObjectiveValueBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */