package fr.paladium.palamod.modules.luckyblock.blocks.easter;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

class null implements Runnable {
  public void run() {
    int chance = ThreadLocalRandom.current().nextInt(1, 5);
    ItemStack itemStack = null;
    switch (chance) {
      case 1:
        itemStack = new ItemStack(ItemsRegister.CHOCOLATE_EGG, 8);
        break;
      case 2:
        itemStack = new ItemStack(ItemsRegister.EASTER_BUNNY_CHESTPLATE);
        break;
      case 3:
        itemStack = new ItemStack(ItemsRegister.EASTER_BUNNY_LEGGINGS);
        break;
      case 4:
        itemStack = new ItemStack(ItemsRegister.EASTER_BUNNY_BOOTS);
        break;
    } 
    if (itemStack != null)
      PlayerUtils.dropItemStack((Entity)entityLivingBase, x, y, z, itemStack); 
    world.func_147468_f(x, y, z);
    PaladiumScheduler.INSTANCE.cancelTask(task.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\easter\BlockEasterGift$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */