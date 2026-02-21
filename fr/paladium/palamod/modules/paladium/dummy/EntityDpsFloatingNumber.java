package fr.paladium.palamod.modules.paladium.dummy;

import net.minecraft.world.World;

public class EntityDpsFloatingNumber extends EntityFloatingNumber {
  public EntityDpsFloatingNumber(World world) {
    super(world);
  }
  
  public EntityDpsFloatingNumber(World world, float damage, double x, double y, double z) {
    super(world, damage, x, y, z);
  }
  
  protected void func_70088_a() {
    this.age = 0;
    this.speed = 100;
  }
  
  public void func_70030_z() {
    if (this.age++ > 150)
      func_70106_y(); 
    this.field_70163_u += this.speed / 500.0D;
    if (this.speed > 1) {
      this.speed /= 2;
    } else if (this.speed == 1) {
      this.speed = 0;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\dummy\EntityDpsFloatingNumber.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */