package fr.paladium.palaspawner.common.spawner.data.impl;

import fr.paladium.palaspawner.common.spawner.data.ASpawnerEntityData;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpawnerBlazeData extends ASpawnerEntityData {
  public static final String ENTITY_ID = "Blaze";
  
  public SpawnerBlazeData() {
    super("Blaze", EntityBlaze.class);
  }
  
  public EntityLiving initEntity(World world) {
    if (world == null)
      return null; 
    return (EntityLiving)new EntityBlaze(world);
  }
  
  protected void addFewItems(int lootingModifier, List<ItemStack> stacks, Random random) {
    int j = random.nextInt(2 + lootingModifier);
    for (int k = 0; k < j; k++)
      stacks.add(new ItemStack(Items.field_151072_bj, 1)); 
  }
  
  protected Item getDropItem() {
    return Items.field_151072_bj;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawner\data\impl\SpawnerBlazeData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */