package fr.paladium.palaspawner.common.spawner.data.impl;

import fr.paladium.palaspawner.common.spawner.data.ASpawnerEntityData;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpawnerSkeletonData extends ASpawnerEntityData {
  public static final String ENTITY_ID = "Skeleton";
  
  public SpawnerSkeletonData() {
    super("Skeleton", EntitySkeleton.class);
  }
  
  public EntityLiving initEntity(World world) {
    if (world == null)
      return null; 
    EntitySkeleton skeleton = new EntitySkeleton(world);
    skeleton.func_82201_a(0);
    return (EntityLiving)skeleton;
  }
  
  protected void addFewItems(int lootingModifier, List<ItemStack> stacks, Random random) {
    int j = random.nextInt(3 + lootingModifier);
    int k;
    for (k = 0; k < j; k++)
      stacks.add(new ItemStack(Items.field_151032_g, 1)); 
    j = random.nextInt(3 + lootingModifier);
    for (k = 0; k < j; k++)
      stacks.add(new ItemStack(Items.field_151103_aS, 1)); 
  }
  
  protected void addRareDrops(List<ItemStack> stacks, int lootingLevel, Random random) {}
  
  protected Item getDropItem() {
    return Items.field_151032_g;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawner\data\impl\SpawnerSkeletonData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */