package fr.paladium.palamod.modules.luckyblock.luckyevents.easter;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

class null implements Runnable {
  public void run() {
    try {
      int cpt = 0;
      while (cpt != 60) {
        cpt++;
        Thread.sleep(1000L);
        if (((Integer)UsersManager.getBlueEasterEggCaught().get(playerId)).intValue() == 10)
          cpt = 60; 
      } 
      for (BlockPos blockPos : blockPosList)
        player.field_70170_p.func_147468_f(blockPos.getX(), blockPos.getY(), blockPos.getZ()); 
      int eggCaught = ((Integer)UsersManager.getBlueEasterEggCaught().get(playerId)).intValue();
      switch (eggCaught) {
        case 0:
          player.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 400, 2));
          break;
        case 1:
          player.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 400, 1));
          break;
        case 2:
          ServerManager.addFreeze(player, player.field_70165_t, player.field_70163_u, player.field_70161_v);
          Thread.sleep(30000L);
          ServerManager.removeFreeze(player);
          break;
        case 3:
          break;
        case 10:
          PlayerUtils.dropItemStack((Entity)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, new ItemStack(BlocksRegister.BLOCK_PALADIUM, 2));
          break;
        default:
          PlayerUtils.dropItemStack((Entity)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, new ItemStack(ItemsRegister.PALADIUM_INGOT, eggCaught));
          break;
      } 
      UsersManager.getBlueEasterEggCaught().put(playerId, Integer.valueOf(0));
      PaladiumScheduler.INSTANCE.cancelTask(task.id);
    } catch (InterruptedException interruptedException) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\ChasseOeufs$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */