package fr.paladium.palaspawner.common.spawner.data.impl;

import fr.paladium.palaspawner.common.spawner.data.ASpawnerEntityData;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpawnerCreeperData extends ASpawnerEntityData {
  public static final String ENTITY_ID = "Creeper";
  
  public SpawnerCreeperData() {
    super("Creeper", EntityCreeper.class);
  }
  
  public EntityLiving initEntity(World world) {
    if (world == null)
      return null; 
    return (EntityLiving)new EntityCreeper(world);
  }
  
  protected Item getDropItem() {
    return Items.field_151016_H;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawner\data\impl\SpawnerCreeperData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */