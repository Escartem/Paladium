package fr.paladium.palamod.util;

import fr.paladium.palamod.modules.common.CommonBridge;

public class MixinBuilderHelper {
  private static int minX = -600;
  
  private static int maxX = 600;
  
  private static int minZ = -600;
  
  private static int maxZ = 600;
  
  public static boolean isInArea(int x, int z) {
    return ((x > minX && x < maxX && z > minZ && z < maxZ) || CommonBridge.isMinage() || CommonBridge.isEvent() || CommonBridge.isDimMiner() || CommonBridge.is("noel"));
  }
  
  public static boolean isInArea(double x, double z) {
    return ((x > minX && x < maxX && z > minZ && z < maxZ) || CommonBridge.isMinage() || CommonBridge.isEvent() || CommonBridge.isDimMiner() || CommonBridge.is("noel"));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\MixinBuilderHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */