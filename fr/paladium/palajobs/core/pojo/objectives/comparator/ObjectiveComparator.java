package fr.paladium.palajobs.core.pojo.objectives.comparator;

import fr.paladium.palajobs.core.pojo.objectives.ObjectiveValue;
import java.util.Comparator;
import java.util.Map;

public class ObjectiveComparator implements Comparator<Map.Entry<Object, ObjectiveValue>> {
  public int compare(Map.Entry<Object, ObjectiveValue> o1, Map.Entry<Object, ObjectiveValue> o2) {
    if (((ObjectiveValue)o1.getValue()).getRequiredLevel() == ((ObjectiveValue)o2.getValue()).getRequiredLevel())
      return Double.compare(((ObjectiveValue)o1.getValue()).getGivedExperience(), ((ObjectiveValue)o2.getValue()).getGivedExperience()); 
    return Integer.compare(((ObjectiveValue)o1.getValue()).getRequiredLevel(), ((ObjectiveValue)o2.getValue()).getRequiredLevel());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\pojo\objectives\comparator\ObjectiveComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */