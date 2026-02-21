package fr.paladium.palamod.modules.pillage.common;

import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import java.util.ArrayList;
import java.util.List;

public enum ComponentType {
  UTILS(PPRegisterer.PPBlocks.CanonTypes.BASE),
  ANTI_PERSO(PPRegisterer.PPBlocks.CanonTypes.BASE),
  ANTI_MATERIAL(PPRegisterer.PPBlocks.CanonTypes.BASE),
  ENDIUM(PPRegisterer.PPBlocks.CanonTypes.BASE),
  RANGE(PPRegisterer.PPBlocks.CanonTypes.CANNON),
  POWER(PPRegisterer.PPBlocks.CanonTypes.CANNON),
  COOLDOWN(PPRegisterer.PPBlocks.CanonTypes.CANNON);
  
  private PPRegisterer.PPBlocks.CanonTypes canonType;
  
  public PPRegisterer.PPBlocks.CanonTypes getCanonType() {
    return this.canonType;
  }
  
  ComponentType(PPRegisterer.PPBlocks.CanonTypes canonTypes) {
    this.canonType = canonTypes;
  }
  
  public static List<ComponentType> getComponentTypeByCanonType(PPRegisterer.PPBlocks.CanonTypes canonType) {
    List<ComponentType> componentTypes = new ArrayList<>();
    for (int i = 0; i < (values()).length; i++) {
      if ((values()[i]).canonType.equals(canonType))
        componentTypes.add(values()[i]); 
    } 
    return componentTypes;
  }
  
  public static ComponentType getComponentByName(String name) {
    return valueOf(name);
  }
  
  public static List<ComponentType> getBaseTypes() {
    return getComponentTypeByCanonType(PPRegisterer.PPBlocks.CanonTypes.BASE);
  }
  
  public static List<ComponentType> getCanonTypes() {
    return getComponentTypeByCanonType(PPRegisterer.PPBlocks.CanonTypes.CANNON);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\ComponentType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */