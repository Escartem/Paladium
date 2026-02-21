package fr.paladium.palajobs.core.pojo.multiplier;

import fr.paladium.palajobs.api.type.MultiplierType;

public class Multiplier {
  private MultiplierType type;
  
  private double value;
  
  public Multiplier(MultiplierType type, double value) {
    this.type = type;
    this.value = value;
  }
  
  public MultiplierType getType() {
    return this.type;
  }
  
  public double getValue() {
    return this.value;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\pojo\multiplier\Multiplier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */