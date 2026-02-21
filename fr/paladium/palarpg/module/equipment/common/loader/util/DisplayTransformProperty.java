package fr.paladium.palarpg.module.equipment.common.loader.util;

import java.util.ArrayList;
import java.util.HashMap;

public class DisplayTransformProperty {
  private HashMap<String, TransformProperty> display;
  
  public HashMap<String, TransformProperty> getDisplay() {
    return this.display;
  }
  
  public static class TransformProperty {
    private ArrayList<Double> rotation;
    
    private ArrayList<Double> translation;
    
    private ArrayList<Double> scale;
    
    public ArrayList<Double> getRotation() {
      return this.rotation;
    }
    
    public ArrayList<Double> getTranslation() {
      return this.translation;
    }
    
    public ArrayList<Double> getScale() {
      return this.scale;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loade\\util\DisplayTransformProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */