package fr.paladium.palaspawner.common.spawner.data.impl;

import fr.paladium.palaspawner.common.spawner.data.ASpawnerEntityData;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpawnerZombieData extends ASpawnerEntityData {
  public static final String ENTITY_ID = "Zombie";
  
  public SpawnerZombieData() {
    super("Zombie", EntityZombie.class);
  }
  
  public EntityLiving initEntity(World world) {
    if (world == null)
      return null; 
    EntityZombie zombie = new EntityZombie(world);
    zombie.func_82227_f(false);
    return (EntityLiving)zombie;
  }
  
  protected void addRareDrops(List<ItemStack> stacks, int lootingLevel, Random random) {
    switch (random.nextInt(3)) {
      case 0:
        stacks.add(new ItemStack(Items.field_151042_j, 1));
      case 1:
        stacks.add(new ItemStack(Items.field_151172_bF, 1));
        break;
      case 2:
        stacks.add(new ItemStack(Items.field_151174_bG, 1));
        break;
    } 
  }
  
  protected Item getDropItem() {
    return Items.field_151078_bh;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawner\data\impl\SpawnerZombieData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */