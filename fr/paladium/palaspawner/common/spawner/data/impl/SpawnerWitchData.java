package fr.paladium.palaspawner.common.spawner.data.impl;

import fr.paladium.palaspawner.common.spawner.data.ASpawnerEntityData;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpawnerWitchData extends ASpawnerEntityData {
  public static final String ENTITY_ID = "Witch";
  
  private final Item[] witchDrops;
  
  public SpawnerWitchData() {
    super("Witch", EntityWitch.class);
    this.witchDrops = new Item[] { Items.field_151114_aO, Items.field_151102_aT, Items.field_151137_ax, Items.field_151070_bp, Items.field_151069_bo, Items.field_151016_H, Items.field_151055_y, Items.field_151055_y };
  }
  
  public EntityLiving initEntity(World world) {
    if (world == null)
      return null; 
    return (EntityLiving)new EntityWitch(world);
  }
  
  protected void addFewItems(int lootingModifier, List<ItemStack> stacks, Random random) {
    int j = random.nextInt(3) + 1;
    for (int k = 0; k < j; k++) {
      int l = random.nextInt(3);
      Item item = this.witchDrops[random.nextInt(this.witchDrops.length)];
      if (lootingModifier > 0)
        l += random.nextInt(lootingModifier + 1); 
      for (int i1 = 0; i1 < l; i1++)
        stacks.add(new ItemStack(item, 1)); 
    } 
  }
  
  protected Item getDropItem() {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawner\data\impl\SpawnerWitchData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */