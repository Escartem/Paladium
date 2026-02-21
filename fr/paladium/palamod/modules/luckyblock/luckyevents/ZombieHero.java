package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ZombieHero extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityZombie zombie = new EntityZombie(player.field_70170_p);
    zombie.func_70634_a(x, y, z);
    zombie.func_110148_a(SharedMonsterAttributes.field_111267_a)
      .func_111121_a(new AttributeModifier("Leader zombie bonus", 9.0D, 2));
    zombie.func_70606_j(200.0F);
    zombie.func_70062_b(1, new ItemStack((Item)Items.field_151161_ac));
    zombie.func_70062_b(2, new ItemStack((Item)Items.field_151163_ad));
    zombie.func_70062_b(3, new ItemStack((Item)Items.field_151173_ae));
    zombie.func_70062_b(4, new ItemStack((Item)Items.field_151175_af));
    zombie.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 500000, 9));
    zombie.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 500000, 3));
    zombie.func_94058_c("Zombie Hero");
    player.field_70170_p.func_72838_d((Entity)zombie);
  }
  
  public String getName() {
    return "Zombie Hero";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 40;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\ZombieHero.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */