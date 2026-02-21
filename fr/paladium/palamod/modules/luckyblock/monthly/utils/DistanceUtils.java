package fr.paladium.palamod.modules.luckyblock.monthly.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class DistanceUtils {
  public static final double MIN_DISTANCE_FROM = 16.0D;
  
  public static boolean isNearTargetLocation(Entity entity, double x, double y, double z) {
    return (getDistanceBetween(entity, x, y, z) <= 16.0D);
  }
  
  public static double getDistanceBetween(Entity entity1, Entity entity2) {
    if (entity1 == null || entity2 == null)
      return Double.MAX_VALUE; 
    return getDistanceBetween(entity1.field_70165_t, entity1.field_70163_u, entity1.field_70161_v, entity2.field_70165_t, entity2.field_70163_u, entity2.field_70161_v);
  }
  
  public static double getDistanceBetween(Entity entity, double x, double y, double z) {
    if (entity == null)
      return Double.MAX_VALUE; 
    return getDistanceBetween(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, x, y, z);
  }
  
  public static double getDistanceBetween(double x1, double y1, double z1, double x2, double y2, double z2) {
    double d0 = x1 - x2;
    double d1 = y1 - y2;
    double d2 = z1 - z2;
    return MathHelper.func_76133_a(d0 * d0 + d1 * d1 + d2 * d2);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthl\\utils\DistanceUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */