package fr.paladium.palaspawner.common.spawner.data.impl;

import cpw.mods.fml.relauncher.ReflectionHelper;
import fr.paladium.palaspawner.common.spawner.data.ASpawnerEntityData;
import java.lang.reflect.Method;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpawnerSlimeData extends ASpawnerEntityData {
  public static final String ENTITY_ID = "Slime";
  
  public SpawnerSlimeData() {
    super("Slime", EntitySlime.class);
  }
  
  public EntityLiving initEntity(World world) {
    if (world == null)
      return null; 
    EntitySlime slime = new EntitySlime(world);
    setSlimeSizeByReflection(slime, 2);
    return (EntityLiving)slime;
  }
  
  private void setSlimeSizeByReflection(EntitySlime slime, int size) {
    try {
      Method setSizeMethod = ReflectionHelper.findMethod(EntitySlime.class, slime, new String[] { "setSlimeSize", "func_70799_a" }, new Class[] { int.class });
      setSizeMethod.setAccessible(true);
      setSizeMethod.invoke(slime, new Object[] { Integer.valueOf(size) });
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  protected Item getDropItem() {
    return Items.field_151123_aH;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawner\data\impl\SpawnerSlimeData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */