package fr.paladium.palaspawner.common.spawner.data.impl;

import fr.paladium.palaspawner.common.spawner.data.ASpawnerEntityData;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpawnerPigData extends ASpawnerEntityData {
  public static final String ENTITY_ID = "Pig";
  
  public SpawnerPigData() {
    super("Pig", EntityPig.class);
  }
  
  public EntityLiving initEntity(World world) {
    if (world == null)
      return null; 
    return (EntityLiving)new EntityPig(world);
  }
  
  protected void addFewItems(int lootingModifier, List<ItemStack> stacks, Random random) {
    int j = random.nextInt(3) + 1 + random.nextInt(1 + lootingModifier);
    for (int k = 0; k < j; k++)
      stacks.add(new ItemStack(getDropItem(), 1)); 
  }
  
  protected Item getDropItem() {
    return Items.field_151147_al;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawner\data\impl\SpawnerPigData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */