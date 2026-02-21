package fr.paladium.palaspawner.common.spawner.data.impl;

import fr.paladium.palaspawner.common.spawner.data.ASpawnerEntityData;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpawnerCaveSpiderData extends ASpawnerEntityData {
  public static final String ENTITY_ID = "CaveSpider";
  
  public SpawnerCaveSpiderData() {
    super("CaveSpider", EntityCaveSpider.class);
  }
  
  public EntityLiving initEntity(World world) {
    if (world == null)
      return null; 
    return (EntityLiving)new EntityCaveSpider(world);
  }
  
  protected void addFewItems(int lootingModifier, List<ItemStack> stacks, Random random) {
    super.addFewItems(lootingModifier, stacks, random);
    if (random.nextInt(3) == 0 || random.nextInt(1 + lootingModifier) > 0)
      stacks.add(new ItemStack(Items.field_151070_bp, 1)); 
  }
  
  protected Item getDropItem() {
    return Items.field_151007_F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawner\data\impl\SpawnerCaveSpiderData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */