package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MobTower extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    Random rand = player.field_70170_p.field_73012_v;
    int mobs = rand.nextInt(13) + 34;
    for (int i = 0; i < mobs; i++) {
      EntitySkeleton entitySkeleton;
      if (rand.nextDouble() < 0.5D) {
        EntityZombie entityZombie = new EntityZombie(player.field_70170_p);
        entityZombie.func_70062_b(0, new ItemStack(Items.field_151040_l, 1));
      } else {
        entitySkeleton = new EntitySkeleton(player.field_70170_p);
        entitySkeleton.func_70062_b(0, new ItemStack((Item)Items.field_151031_f, 1));
      } 
      entitySkeleton.func_70062_b(1, new ItemStack((Item)Items.field_151028_Y, 1));
      entitySkeleton.func_70062_b(2, new ItemStack((Item)Items.field_151030_Z, 1));
      entitySkeleton.func_70062_b(3, new ItemStack((Item)Items.field_151165_aa, 1));
      entitySkeleton.func_70062_b(4, new ItemStack((Item)Items.field_151167_ab, 1));
      entitySkeleton.func_110148_a(SharedMonsterAttributes.field_111263_d)
        .func_111128_a(0.43000000417232515D);
      entitySkeleton.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(8.0D);
      entitySkeleton.func_70634_a(x, y, z);
      player.field_70170_p.func_72838_d((Entity)entitySkeleton);
    } 
  }
  
  public String getName() {
    return "Tour à mob";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "tour_a_mob";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\MobTower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */