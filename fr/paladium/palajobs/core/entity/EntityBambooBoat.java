package fr.paladium.palajobs.core.entity;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.world.World;

public class EntityBambooBoat extends EntityBoat {
  public EntityBambooBoat(World world) {
    super(world);
    ReflectionHelper.setPrivateValue(EntityBoat.class, this, Double.valueOf(0.21000000000000002D), new String[] { "speedMultiplier", "field_70276_b" });
    try {
      ReflectionHelper.setPrivateValue(EntityBoat.class, this, Double.valueOf(1.0499999999999998D), new String[] { "maxSpeed", "field_82638_b" });
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public EntityBambooBoat(World world, double x, double y, double z) {
    super(world, x, y, z);
    ReflectionHelper.setPrivateValue(EntityBoat.class, this, Double.valueOf(0.21000000000000002D), new String[] { "speedMultiplier", "field_70276_b" });
    try {
      ReflectionHelper.setPrivateValue(EntityBoat.class, this, Double.valueOf(1.0499999999999998D), new String[] { "maxSpeed", "field_82638_b" });
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\EntityBambooBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */